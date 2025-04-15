/// <reference lib="webworker" />

const apiSimulateData = {
    "/api/jiahe/community_profile": {
        "errorMessage": "string",
        "code": 200,
        "data": {
            "title": "嘉和社区",
            "images": "./image/社区大门.jpg",
            "profile": "嘉和社区地处嘉祥县城北，管理范围为东至武路、西至老嘉梁路，南至曾子大道，北至嘉北路。嘉和社区下辖财苑小区、博悦城、碧城华园、曾子园、金都花苑、红旗花园、国际商贸城、嘉和苑西区、圣贤世家等9个小区，居民3333333户、972人，党员259名，商铺532家，拥有嘉和园幼儿园、嘉祥县实验小学、嘉祥县第四中学3所学校，教育和商贸资源丰富。\n 嘉和社区党群中心位于演武路与嘉北路交叉口的临街位置，于2024年8月建成投入使用，服务范围广、功能全，阵地面积1500余平方米。社区立足便民利民、务实管用，科学设置和睦嘉议事厅、暖新嘉驿站、邻里同行坊等12处特色功能阵地，构建一站式社区生活“服务圈”。目前累计开展各类服务活动180余次，服务居民1.2万余人次。\n 嘉和社区坚持“党建为核、治理为基、服务为本”，积极整合社区资源、搭建治理平台、凝聚社会力量，打造了“幸福嘉和邻里”的社区品牌，引导各方力量共同参与社区治理，不断提升居民在社区生活的获得感、幸福感。\n 党建赋能，领航“幸福舫”。完善“社区大党委一网格（小区）党支部一楼栋党小组一党员中心户”四级组织架构，开展“支部进小区、党员进楼栋”行动，构建横向到边、纵向到底的组织体系。持续推进智慧社区建设，完善数据采集、信息发布、网上议事、志愿服务等应用场景，提升服务居民的能力和水平，打造网上服务群众平台。\n 居民参与，启航“幸福舫”。秉承“把居民当家人、把社区当家建”的初衷，打造开放式、互动式办公区域，为居民提供事项办理、政策咨询等服务。充分利用党组织的政治优势和组织优势，引导居民参与社区治理，启航“幸福舫” \n 多方协同，护航“幸福舫”。建立社区“红色合伙人”资源库，招募嘉祥口腔医院、银泰商圈等“红色合伙人”10个，领资源、服务项目24个。定期邀请驻区单位、社会组织、志愿者等参与社区服务，多方协同，护航“幸福舫”. \n"
        }
    },
    "/api/jiahe/list_committees_members": {
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        getData: (pageNum: number, pageSize: number) => {
            const records = [
                {
                    "id": 1,
                    "name": "于燕",
                    "position": "社区党委书记 居委会主任",
                    "images": "./image/yuyan.png",
                    "duty": "管理社区所有小区"
                },
                {
                    "id": 2,
                    "name": "郭亚芳",
                    "position": "社区书记助理 社区两委成员",
                    "images": "./image/guoyafang.png",
                    "duty": "管理红旗花园小区"
                },
                {
                    "id": 3,
                    "name": "郭振南",
                    "position": "一般社区工作人员",
                    "images": "./image/guozhennan.png",
                    "duty": "管理祥曾园小区"
                },
                {
                    "id": 4,
                    "name": "黄威",
                    "position": "一般社区工作人员",
                    "images": "./image/huangwei.png",
                    "duty": "管理嘉和苑西区"
                },
                {
                    "id": 5,
                    "name": "江婷婷",
                    "position": "一般社区工作人员",
                    "images": "./image/jiangtingting.png",
                    "duty": "管理财苑小区、碧城华园小区"
                }
            ];

            return {
                "currentPage": pageNum,   // 当前页码
                "size": pageSize,   // 每页记录数
                "total": records.length,     // 总记录数
                "pages": 2,   // 总页数
                "records": records.slice((pageNum - 1) * pageSize, pageNum * pageSize)
            }
        }
    },
    "/api/jiahe/personal_info": {
        "errorMessage": "string",
        "code": 200,
        getData(id: string) {
            const members = [{
                "id": 1,
                "name": "于燕",
                "position": "社区党委书记 居委会主任",
                "images": "./image/yuyan.png",
                "duty": "管理社区所有小区"
            },
                {
                    "id": 2,
                    "name": "郭亚芳",
                    "position": "社区书记助理 社区两委成员",
                    "images": "./image/guoyafang.png",
                    "duty": "管理红旗花园小区"
                },
                {
                    "id": 3,
                    "name": "郭振南",
                    "position": "一般社区工作人员",
                    "images": "./image/guozhennan.png",
                    "duty": "管理祥曾园小区"
                },
                {
                    "id": 4,
                    "name": "黄威",
                    "position": "一般社区工作人员",
                    "images": "./image/huangwei.png",
                    "duty": "管理嘉和苑西区"
                },
                {
                    "id": 5,
                    "name": "江婷婷",
                    "position": "一般社区工作人员",
                    "images": "./image/jiangtingting.png",
                    "duty": "管理财苑小区、碧城华园小区"
                }]
            const dutyContent = [
                [],
                [
                    "协助书记处理社区日常事务，确保社区工作的顺利进行。",
                    "在书记的指导下，负责社区重要项目的推进和实施。",
                    "协调社区内各部门之间的沟通，确保信息畅通。",
                    "参与社区重要会议，提出建设性意见。",
                    "负责社区居民的反馈收集，及时向书记汇报。"
                ],
                [
                    "负责社区组织建设，制定组织工作计划并组织实施。",
                    "组织社区党员和居民开展各类活动，增强社区凝聚力。",
                    "负责社区党员的教育和管理，提高党员素质。",
                    "协调社区内外部资源，推动社区组织发展。",
                    "完成上级组织交办的其他任务。"
                ],
                [
                    "负责社区宣传工作，制定宣传计划并组织实施。",
                    "通过多种渠道宣传社区活动和政策，提高居民知晓率。",
                    "负责社区宣传材料的制作和分发，确保宣传效果。",
                    "组织社区居民开展宣传活动，提升社区形象。",
                    "完成上级宣传部门交办的其他任务。"
                ],
                [
                    "负责社区纪律检查工作，确保社区工作廉洁高效。",
                    "监督社区工作人员遵守纪律，防止违规行为。",
                    "处理居民投诉和举报，维护社区良好秩序。",
                    "定期开展廉政教育活动，提高社区工作人员廉洁意识。",
                    "完成上级纪检部门交办的其他任务。"
                ]
            ]
            const resID = parseInt(id) - 1;
            return Object.assign(members[resID], {dutyContent: dutyContent[resID]});
        }
    }
}

const debug = true;
self.addEventListener("fetch", function (ev: FetchEvent) {
    if (!debug) {
        ev.respondWith(fetch(ev.request));
        return;
    }

    const url = new URL(ev.request.url);

    if (!apiSimulateData[url.pathname]) {
        ev.respondWith(fetch(ev.request));
    } else {
        console.log("将获取api测试数据：", url.pathname, url.search);

        let data;

        if (apiSimulateData[url.pathname].getData) {
            data = apiSimulateData[url.pathname].getData(...Array.from(url.searchParams.values()))
        } else {
            data = apiSimulateData[url.pathname].data;
        }

        apiSimulateData[url.pathname].data = data;
        ev.respondWith(new Response(JSON.stringify(apiSimulateData[url.pathname])));
    }
});

self.addEventListener("install", function () {
    if (debug) console.log("API接口进入调试模式");
})
self.addEventListener("activate", function () {
    if (debug) console.log("API接口进入调试模式");
})