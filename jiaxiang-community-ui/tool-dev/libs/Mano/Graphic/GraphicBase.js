import { TextFormat } from "./TextFormat.js";
import { FILL_TYPE } from "./FILL_TYPE.js";
import { RGBA } from "../Fillable/ColorFormat/RGBA.js";
import { RenderEvent } from "../Event/RenderEvent.js";
import { AfterRenderEvent } from "../Event/AfterRenderEvent.js";
import { ContextChangeEvent } from "../Event/ContextChangeEvent.js";
import { FILL_RULE } from "./FILL_RULE.js";
import { GraphicEventRegister } from "./GraphicEventRegister.js";
// GraphicBase类继承自GraphicEventRegister类，提供图形基类的实现
class GraphicBase extends GraphicEventRegister {
    // 定义路径属性，用于存储图形路径信息
    path = null;
    // 定义Mano对象引用，可能与canvas上下文或其他图形渲染相关
    mano;
    // DOM内置getBoundingClientRect()方法执行时间过长，所以要自己做一个
    #__boundingRect__;
    getBoundingClientRect() {
        this.updateBoundingRect();
        return this.#__boundingRect__;
    }
    setBoundingClientRect({ x, y, width, height }) {
        this.#__boundingRect__ = new DOMRect(x, y, width, height);
    }
    // 更新边界矩形的方法
    // public updateBoundingRect(): void
    updateBoundingRect() {
        // 此处应实现更新图形边界矩形的具体逻辑
    }
    // 私有方法，用于触发重新绘制
    #redraw(options) {
        // 创建一个ContextChangeEvent事件，并设置其源为"graphic base"
        let ev = new ContextChangeEvent("contextchange", {
            bubbles: true,
            cancelable: true,
        });
        ev.source = "graphic base";
        // 根据当前动画状态设定清除选项
        if (this.#__animation__)
            ev.clearOptions = "dynamic";
        else
            ev.clearOptions = "static";
        // 若传入了options参数，则覆盖默认清除选项
        if (options)
            ev.clearOptions = options;
        // 将事件发送到关联的canvas元素上
        this.mano?.canvas?.dispatchEvent(ev);
    }
    // 私有内容字符串属性
    #__content__;
    // 内容属性的getter方法
    get content() {
        return this.#__content__;
    }
    // 内容属性的setter方法，设置内容后会删除现有子元素并触发重新绘制
    set content(content) {
        this.#__content__ = content;
        // 删除已存在的第一个子元素（可能是文本元素）
        this.children[0] && this.removeChild(this.children[0]);
        // 触发重新绘制
        this.#redraw();
    }
    // 私有的文本格式化对象
    #__textFormat__ = new TextFormat({ textBaseline: "hanging" });
    // 获取文本格式化对象的方法
    get textFormat() {
        return this.#__textFormat__;
    }
    // 设置文本格式化对象的方法，当对象属性变化时会触发重新绘制
    set textFormat(v) {
        let that = this;
        // 如果v是一个对象，则创建代理以在属性更改时自动触发重新绘制
        if (v && typeof v === "object") {
            this.#__textFormat__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        }
        else if (!v) {
            this.#__textShadow__ = null;
        }
        //重新设置内容样式并重新绘制
        //与that.#redraw()的区别在于redraw不会设置子元素<text>的样式直接重新渲染
        // 此写法会触发衍生类的set content方法，会重新创建一个text
        //删除已有的text之后插入
        this.content = this.content;
    }
    // 私有的box-shadow对象
    #__boxShadow__;
    // 获取box-shadow对象的方法
    get boxShadow() {
        return this.#__boxShadow__;
    }
    // 设置box-shadow对象的方法，当对象属性变化时会触发重新绘制
    set boxShadow(v) {
        let that = this;
        if (v && typeof v === "object")
            this.#__boxShadow__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        else if (!v) {
            this.#__boxShadow__ = null;
            that.#redraw();
        }
        this.#redraw();
    }
    // 私有的text-shadow对象
    #__textShadow__;
    // 获取text-shadow对象的方法
    get textShadow() {
        return this.#__textShadow__;
    }
    // 设置text-shadow对象的方法，当对象属性变化时会触发重新绘制及文本元素的重新创建和绘制
    set textShadow(v) {
        const that = this;
        // 如果v是一个对象，则创建代理以在属性更改时自动触发重新绘制
        if (v && typeof v === "object") {
            this.#__textShadow__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        }
        else if (!v) {
            this.#__textShadow__ = null;
        }
        //重新设置内容样式并重新绘制
        //与that.#redraw()的区别在于redraw不会设置子元素<text>的样式直接重新渲染
        // 此写法会触发衍生类的set content方法，会重新创建一个text
        //删除已有的text之后插入
        this.content = this.content;
    }
    // 私有的边框对象
    #__border__;
    // 获取边框对象的方法
    get border() {
        return this.#__border__;
    }
    // 设置边框对象的方法，当对象属性变化时会触发重新绘制
    set border(v) {
        const that = this;
        // 如果v是一个对象，则创建代理以在属性更改时自动触发重新绘制
        if (v && typeof v === "object")
            this.#__border__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        else if (!v) {
            this.#__border__ = null;
        }
        this.#redraw();
    }
    // 私有的字体对象
    #__font__;
    // 获取字体对象的方法
    get font() {
        return this.#__font__;
    }
    // 设置字体对象的方法，当对象属性变化时会触发重新绘制及文本元素的重新创建和绘制
    set font(v) {
        const that = this;
        // 如果v是一个对象，则创建代理以在属性更改时自动触发重新绘制
        if (v && typeof v === "object") {
            this.#__font__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        }
        else if (!v) {
            this.#__font__ = null;
        }
        //重新设置内容样式并重新绘制
        //与that.#redraw()的区别在于redraw不会设置子元素<text>的样式直接重新渲染
        // 此写法会触发衍生类的set content方法，会重新创建一个text
        //删除已有的text之后插入
        this.content = this.content;
    }
    // 当前框体变换矩阵属性
    currentBoxTransform = new DOMMatrixReadOnly([1, 0, 0, 1, 0, 0]);
    // 继承框体变换矩阵属性
    inheritBoxTransform = new DOMMatrixReadOnly([1, 0, 0, 1, 0, 0]);
    // 获取组合后的框体变换矩阵（当前框体变换与继承框体变换相乘）
    get boxTransform() {
        return this.inheritBoxTransform.multiply(this.currentBoxTransform);
    }
    // 设置当前框体变换矩阵，并触发重新绘制
    set boxTransform(v) {
        this.currentBoxTransform = v;
        this.#redraw();
    }
    // 当前文本变换矩阵属性
    currentTextTransform = new DOMMatrixReadOnly([1, 0, 0, 1, 0, 0]);
    // 继承文本变换矩阵属性
    inheritTextTransform = new DOMMatrixReadOnly([1, 0, 0, 1, 0, 0]);
    // 获取组合后的文本变换矩阵（当前文本变换、当前框体变换与继承文本变换相乘）
    get textTransform() {
        return this.inheritTextTransform.multiply(this.currentTextTransform).multiply(this.currentBoxTransform);
    }
    // 设置当前文本变换矩阵，并强制重新创建和绘制文本元素
    set textTransform(v) {
        this.currentTextTransform = v;
        this.content = this.content;
    }
    // 私有填充类型枚举属性
    #__fillType__ = FILL_TYPE.GRAPHIC_FILL;
    // 获取填充类型的方法
    get fillType() {
        return this.#__fillType__;
    }
    // 设置填充类型的方法，设置后触发重新绘制
    set fillType(v) {
        this.#__fillType__ = v;
        this.#redraw();
    }
    // 私有填充规则枚举属性
    #__fillRule__ = FILL_RULE.NONZERO;
    // 获取填充规则的方法
    get fillRule() {
        return this.#__fillRule__;
    }
    // 设置填充规则的方法，设置后触发重新绘制
    set fillRule(v) {
        this.#__fillRule__ = v;
        this.#redraw();
    }
    // 私有背景颜色属性
    #__backgroundColor__ = new RGBA(0, 0, 0);
    // 获取背景颜色的方法
    get backgroundColor() {
        return this.#__backgroundColor__;
    }
    // 设置背景颜色的方法，当对象属性变化时会触发重新绘制
    set backgroundColor(v) {
        let that = this;
        // 如果v是一个对象，则创建代理以在属性更改时自动触发重新绘制
        if (v && typeof v === "object")
            this.#__backgroundColor__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        // 不论如何都触发一次重新绘制
        this.#redraw();
    }
    // 私有颜色属性
    #__color__ = new RGBA(0, 0, 0);
    // 获取颜色的方法
    get color() {
        return this.#__color__;
    }
    // 设置颜色的方法，当对象属性变化时会触发重新创建和绘制文本元素
    set color(v) {
        let that = this;
        if (v && typeof v === "object") {
            this.#__color__ = new Proxy(v, {
                set(target, p, newValue) {
                    that.#redraw();
                    return target[p] = newValue;
                }
            });
        }
        else if (!v) {
            this.#__color__ = null;
        }
        //重新设置内容样式并重新绘制
        //与that.#redraw()的区别在于redraw不会设置子元素<text>的样式直接重新渲染
        // 此写法会触发衍生类的set content方法，会重新创建一个text
        //删除已有的text之后插入
        this.content = this.content;
    }
    // 私有动画对象引用属性
    #__animation__;
    // 获取动画对象的方法
    get animation() {
        return this.#__animation__;
    }
    // 设置动画对象的方法，添加动画效果并根据动画状态触发相应的重新绘制操作
    set animation(v) {
        this.#__animation__ = v;
        if (!v) {
            this.#redraw("both");
            return;
        }
        else {
            this.#redraw("dynamic");
        }
        this.#__animation__.target.push(this);
        this.#__animation__.replay();
    }
    // 获取适合当前图形渲染上下文的方法，根据动画状态决定使用静态还是动态canvas
    getContext(canvas) {
        // 遍历父级元素直到body节点，查找包含动画的对象
        for (let i = this; i !== this.mano && i; i = i.parentElement) {
            let graphic = i;
            if (graphic.animation) {
                return canvas.dynamicsCanvas;
            }
        }
        return canvas.staticCanvas;
    }
    render(canvas, clearOption) {
        // 创建并触发一个RenderEvent事件，表示开始渲染过程
        let ev = new RenderEvent("manorender");
        this.dispatchEvent(ev);
        // 获取父级元素的Mano对象引用
        this.mano = this.parentElement.mano;
        // 根据动画状态或其他条件选择合适的canvas上下文并返回
        return this.getContext(canvas);
    }
    // 渲染当前图形元素的所有子元素到指定的canvas上
    renderChildren(canvas) {
        // 遍历所有子元素并将它们转换为GraphicBase类型实例
        Array.from(this.children).forEach(element => {
            let graphic = element;
            // 更新图形的边界矩形
            const boundingRect = graphic.getBoundingClientRect();
            // 如果图形的边界矩形超出了画布的范围，则跳过此图形
            if (boundingRect.x + boundingRect.width < 0 ||
                boundingRect.x > this.mano.canvas.canvasOptions.width ||
                boundingRect.y + boundingRect.height < 0 ||
                boundingRect.y > this.mano.canvas.canvasOptions.height) {
                return;
            }
            // 调用子元素的render方法进行渲染
            graphic.render(canvas);
            // 创建并触发一个AfterRenderEvent事件，表示子元素已完成渲染
            let ev = new AfterRenderEvent("manoafterrender", {
                bubbles: true,
                cancelable: true,
            });
            graphic.dispatchEvent(ev);
        });
    }
    // GraphicBase类的构造函数
    constructor() {
        // 调用父类构造函数以完成初始化
        super();
    }
}
export { GraphicBase };
