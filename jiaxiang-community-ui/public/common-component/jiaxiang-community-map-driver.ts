/**
 * SVG路径操作指令接口，描述路径命令及其参数
 */
interface PathOperation {
    /** 路径命令字母（如 M、L、C 等） */
    operation: string;
    /** 该命令对应的参数数组 */
    params: number[];
}

/**
 * 表示二维尺寸的接口
 */
interface Dimensions {
    /** 宽度值 */
    width: number;
    /** 高度值 */
    height: number;
}

/**
 * 社区地图数据返回类型接口
 */
interface MapDriverReturnType {
    /** 社区名称 */
    name: string;
    /** 对应的 SVG 组元素 */
    svg: SVGElement;
    /** 图案路径的解析数据 */
    patternPath: Array<PathOperation>;
    /** 边缘路径的解析数据 */
    edgePath: Array<PathOperation>;
}


/**
 * 社区地图解析驱动类，用于解析和操作 SVG 地图数据
 */
class JiaxiangCommunityMapDriver {
    /** SVG 根元素 */
    public svgElement: SVGSVGElement;
    /** 存储解析后的社区数据项数组 */
    public items: Array<MapDriverReturnType>;

    /**
     * 构造函数
     * @param svgElement - 需要解析的 SVG 根元素
     */
    constructor(svgElement: SVGSVGElement) {
        this.svgElement = svgElement;
    }


    /**
     * 解析 SVG 路径的 d 属性为操作指令数组
     * @param d - 路径的 d 属性字符串
     * @returns 解析后的路径操作指令数组
     * @example
     * parsePathData("M10 20L30 40")
     * // 返回 [{operation: 'M', params: [10,20]}, {operation: 'L', params: [30,40]}]
     */
    private parsePathData(d: string): PathOperation[] {
        const operations: PathOperation[] = [];
        // 正则匹配一个命令字母以及后面的所有参数字符串
        const commandRegex = /([MmLlHhVvCcSsQqTtAaZz])([^MmLlHhVvCcSsQqTtAaZz]*)/g;
        let match: RegExpExecArray | null;

        while ((match = commandRegex.exec(d)) !== null) {
            const command = match[1];
            // 去除两端空白，并按空白或逗号分割参数字符串
            const paramsString = match[2].trim();
            const params = paramsString.length > 0
                ? paramsString.split(/[\s,]+/).filter(s => s.length > 0).map(parseFloat)
                : [];

            operations.push({operation: command, params});
        }

        return operations;
    }

    /**
     * @deprecated 已废弃 - 计算路径操作的边界尺寸（该方法存在精度问题，建议使用 SVG 原生方法）
     * @param ops - 路径操作指令数组
     * @returns 包含宽度和高度的尺寸对象
     */
    private estimateBoundary(ops: PathOperation[]): Dimensions {
        // 初始化边界
        let minX = Infinity, minY = Infinity;
        let maxX = -Infinity, maxY = -Infinity;
        // 当前坐标，针对相对命令
        let currentX = 0, currentY = 0;

        // 更新边界的辅助函数
        const updateBounds = (x: number, y: number) => {
            if (x < minX) {
                minX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y > maxY) {
                maxY = y;
            }
        };

        ops.forEach(op => {
            const {operation, params} = op;
            switch (operation) {
                // M 和 L 命令（绝对坐标）
                case 'M':
                case 'L':
                    currentX = params[0];
                    currentY = params[1];
                    updateBounds(currentX, currentY);
                    break;
                // m 和 l 命令（相对坐标）
                case 'm':
                case 'l':
                    currentX += params[0];
                    currentY += params[1];
                    updateBounds(currentX, currentY);
                    break;
                // H 命令（绝对水平线，y 不变）
                case 'H':
                    currentX = params[0];
                    updateBounds(currentX, currentY);
                    break;
                // h 命令（相对水平线）
                case 'h':
                    currentX += params[0];
                    updateBounds(currentX, currentY);
                    break;
                // V 命令（绝对垂直线，x 不变）
                case 'V':
                    currentY = params[0];
                    updateBounds(currentX, currentY);
                    break;
                // v 命令（相对垂直线）
                case 'v':
                    currentY += params[0];
                    updateBounds(currentX, currentY);
                    break;
                // C 命令（三次贝塞尔曲线，参数为：x1,y1, x2,y2, x,y）
                case 'C':
                    // 更新所有控制点以及终点
                    for (let i = 0; i < params.length; i += 2) {
                        updateBounds(params[i], params[i + 1]);
                    }
                    currentX = params[4];
                    currentY = params[5];
                    break;
                // c 命令（相对三次贝塞尔曲线）
                case 'c':
                    for (let i = 0; i < params.length; i += 2) {
                        updateBounds(currentX + params[i], currentY + params[i + 1]);
                    }
                    currentX += params[4];
                    currentY += params[5];
                    break;
                // 其他命令也可以类似处理，或者根据需要扩展
                case 'Z':
                case 'z':
                    // Z 或 z 命令表示闭合路径，不改变当前坐标
                    break;
                default:
                    // 对于不支持的命令，可在此扩展处理
                    break;
            }
        });

        return {
            width: maxX - minX,
            height: maxY - minY
        };
    }

    /**
     * 解析 SVG 结构，提取社区数据
     * 功能说明：
     * 1. 清空现有数据
     * 2. 遍历 SVG 中的所有社区组（<g> 元素）
     * 3. 从每个社区组中提取：
     *    - 图案路径
     *    - 边缘路径
     *    - 社区名称文本
     * 4. 将解析结果存入 items 数组
     */
    public parse: () => void = function (this: JiaxiangCommunityMapDriver) {
        const svg = this.svgElement;

        // 清空之前的数据
        this.items = [];

        const communityGroups = svg.querySelectorAll('svg>g');
        communityGroups.forEach((group: SVGElement) => {
            const pathGroup = group.querySelector('g') as SVGElement;
            const textGroup = group.querySelector('g:nth-of-type(2)') as SVGElement;

            if (!pathGroup || !textGroup) return;

            const patternPath = pathGroup.querySelector('path');
            const edgePath = pathGroup.querySelector('path:nth-of-type(2)');
            // 在每个社区组内查找 <text> 标签来获取社区名称
            const textElement = textGroup.querySelector('text');

            if (textElement && textElement.textContent) {
                // 去掉多余的空格
                const name = textElement.textContent.trim();
                if (name) {
                    // 将名称和整个 <g> 元素存入 map 中
                    this.items.push({
                        name,
                        svg: group,
                        patternPath: this.parsePathData(patternPath.getAttribute('d')),
                        edgePath: this.parsePathData(edgePath.getAttribute('d')),
                    });
                }


            }
        });
    };
}

export {MapDriverReturnType, JiaxiangCommunityMapDriver};