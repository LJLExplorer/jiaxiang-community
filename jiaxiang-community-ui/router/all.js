/**
 * @version v1.0
 * @ClassNmae: all
 * @Description: desc
 * @Author: SYANNPE
 */
const express = require('express');

const router = express.Router();

router.use(express.urlencoded({extended: false}));
router.get("/jiahe/community_profile", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "title": "嘉禾社区",
            "images": "../img/社区大门.jpg",
            "profile": "嘉和社区地处嘉祥县城北，管理范围为东至武路、西至老嘉梁路，南至曾子大道，北至嘉北路。嘉和社区下辖财苑小区、博悦城、碧城华园、曾子园、金都花苑、红旗花园、国际商贸城、嘉和苑西区、圣贤世家等9个小区，居民3333333户、972人，党员259名，商铺532家，拥有嘉和园幼儿园、嘉祥县实验小学、嘉祥县第四中学3所学校，教育和商贸资源丰富。\n 嘉和社区党群中心位于演武路与嘉北路交叉口的临街位置，于2024年8月建成投入使用，服务范围广、功能全，阵地面积1500余平方米。社区立足便民利民、务实管用，科学设置和睦嘉议事厅、暖新嘉驿站、邻里同行坊等12处特色功能阵地，构建一站式社区生活“服务圈”。目前累计开展各类服务活动180余次，服务居民1.2万余人次。\n 嘉和社区坚持“党建为核、治理为基、服务为本”，积极整合社区资源、搭建治理平台、凝聚社会力量，打造了“幸福嘉和邻里”的社区品牌，引导各方力量共同参与社区治理，不断提升居民在社区生活的获得感、幸福感。\n 党建赋能，领航“幸福舫”。完善“社区大党委一网格（小区）党支部一楼栋党小组一党员中心户”四级组织架构，开展“支部进小区、党员进楼栋”行动，构建横向到边、纵向到底的组织体系。持续推进智慧社区建设，完善数据采集、信息发布、网上议事、志愿服务等应用场景，提升服务居民的能力和水平，打造网上服务群众平台。\n 居民参与，启航“幸福舫”。秉承“把居民当家人、把社区当家建”的初衷，打造开放式、互动式办公区域，为居民提供事项办理、政策咨询等服务。充分利用党组织的政治优势和组织优势，引导居民参与社区治理，启航“幸福舫” \n 多方协同，护航“幸福舫”。建立社区“红色合伙人”资源库，招募嘉祥口腔医院、银泰商圈等“红色合伙人”10个，领资源、服务项目24个。定期邀请驻区单位、社会组织、志愿者等参与社区服务，多方协同，护航“幸福舫”. \n"
        }
    })
});

router.get("/jiahe/community_honor", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "records": [
                "2018年，荣获“全国文明社区”称号。",
                "2019年，被评为“优秀基层党组织",
                "2020年，获得“社区治理创新奖"
            ],
            "total": 9,
            "currentPage": 1,
            "size": 10,
            "pages": 1
        }
    })
});
router.get("/jiahe/grid_management", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 0,
        "data": [
            {
                "community": "string",
                "meta": {
                    "title": "",
                    "profile": "string"
                }
            },
            {
                "community": "string",
                "meta": {
                    "title": "",
                    "profile": "string2"
                }
            }
        ]
    })
});
router.get("/jiahe/list_committees_members", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        "data": {
            "currentPage": 1,
            "size": 10,
            "total": 9,
            "pages": 0,
            "records": [
                {
                    "id": 1,
                    "name": "于燕\n",
                    "position": "社区党委书记 居委会主任",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/yuyan",
                    "duty": "管理社区所有小区",
                    "dutyDescription": "",
                    "createTime": "2025-04-11T12:48:07.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 2,
                    "name": "郭亚芳",
                    "position": "社区书记助理 社区两委成员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/guoyafang.png",
                    "duty": "管理红旗花园小区",
                    "dutyDescription": "协助书记处理社区日常事务，确保社区工作的顺利进行。\n\n在书记的指导下，负责社区重要项目的推进和实施。\n\n协调社区内各部门之间的沟通，确保信息畅通。\n\n参与社区重要会议，提出建设性意见。\n\n负责社区居民的反馈收集，及时向书记汇报。",
                    "createTime": "2025-04-11T12:48:57.000+00:00",
                    "updateTime": "2025-05-29T08:37:03.000+00:00"
                },
                {
                    "id": 3,
                    "name": "郭振南",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/guozhennan.png",
                    "duty": "管理祥曾园小区",
                    "dutyDescription": "负责社区组织建设，制定组织工作计划并组织实施。\n\n组织社区党员和居民开展各类活动，增强社区凝聚力。\n\n负责社区党员的教育和管理，提高党员素质。\n\n协调社区内外部资源，推动社区组织发展。\n\n完成上级组织交办的其他任务。",
                    "createTime": "2025-04-11T12:49:22.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 4,
                    "name": "黄威",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/huangwei.png",
                    "duty": "管理嘉和苑西区",
                    "dutyDescription": "负责社区宣传工作，制定宣传计划并组织实施。\n\n通过多种渠道宣传社区活动和政策，提高居民知晓率。\n\n负责社区宣传材料的制作和分发，确保宣传效果。\n\n组织社区居民开展宣传活动，提升社区形象。\n\n完成上级宣传部门交办的其他任务。",
                    "createTime": "2025-04-11T12:49:44.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 5,
                    "name": "江婷婷",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/jiangtingting.png",
                    "duty": "管理财苑小区、碧城华园小区",
                    "dutyDescription": "负责社区纪律检查工作，确保社区工作廉洁高效。\n\n监督社区工作人员遵守纪律，防止违规行为。\n\n处理居民投诉和举报，维护社区良好秩序。\n\n定期开展廉政教育活动，提高社区工作人员廉洁意识。\n\n完成上级纪检部门交办的其他任务。",
                    "createTime": "2025-04-11T12:50:07.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 6,
                    "name": "蒋彦臣",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/jiangyanchen.png",
                    "duty": "管理金都花苑小区",
                    "dutyDescription": "负责社区青年工作，制定青年工作计划并组织实施。\n\n组织青年开展各类活动，促进青年成长和发展。\n\n关注青年需求，为青年提供就业创业指导和服务。\n\n引导青年参与社区建设，发挥青年积极作用。\n\n完成上级青年组织交办的其他任务。",
                    "createTime": "2025-04-11T12:50:54.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 7,
                    "name": "孟如",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/mengru.png.png",
                    "duty": "管理国际商贸城小区",
                    "dutyDescription": "负责社区妇女工作，制定妇女工作计划并组织实施。\n\n组织妇女开展各类活动，提升妇女素质和能力。\n\n维护妇女合法权益，关注妇女需求。\n\n引导妇女参与社区建设，发挥妇女积极作用。\n\n完成上级妇女组织交办的其他任务。",
                    "createTime": "2025-04-11T12:51:18.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 8,
                    "name": "张传祥",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/zhangchuanxiang.png",
                    "duty": "管理红旗花园小区",
                    "dutyDescription": "负责社区统战工作，制定统战工作计划并组织实施。\n\n组织统战对象开展各类活动，促进社区和谐稳定。\n\n加强与统战对象的沟通和联系，了解他们的需求和意见。\n\n引导统战对象参与社区建设，发挥积极作用。\n\n完成上级统战部门交办的其他任务。",
                    "createTime": "2025-04-11T12:51:45.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                },
                {
                    "id": 9,
                    "name": "赵劲松",
                    "position": "一般社区工作人员",
                    "images": "http:/127.0.0.1:9000/jiaxiang/2025/04/11/zhaojinsong.png",
                    "duty": "管理博悦城小区",
                    "dutyDescription": "负责社区统战工作，制定统战工作计划并组织实施。\n\n组织统战对象开展各类活动，促进社区和谐稳定。\n\n加强与统战对象的沟通和联系，了解他们的需求和意见。\n\n引导统战对象参与社区建设，发挥积极作用。\n\n完成上级统战部门交办的其他任务。",
                    "createTime": "2025-04-11T12:52:11.000+00:00",
                    "updateTime": "2025-05-29T08:30:13.000+00:00"
                }
            ]
        }
    })
});

router.get("/jiahe/list_serve_people", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "records": [
                {
                    "id": 1,
                    "title": "string",
                    "images": "string",
                    "phone": "电话号码",
                    "openDaysAndTime": "string",
                    "location": "string"
                },
                {
                    "id": 2,
                    "title": "string",
                    "images": "string",
                    "phone": "电话号码",
                    "openDaysAndTime": "string",
                    "location": "string"
                }
            ],
            "total": 9,
            "currentPage": 1,
            "size": 10,
            "pages": 10
        }
    })
});

router.get("/jiahe/serve_people_info", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "id": 1,
            "title": "string",
            "images": "string",
            "phone": "电话号码",
            "openDaysAndTime": "string",
            "location": "string",
            "serviceIntro": "string",
            "serviceContent": "string",
            "serviceRules": "string"
        }
    })
});

router.get("/jiahe/list_matters", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "records": [
                {
                    "id": 1,
                    "title": "村（农村社区）依法履职事项指导清单",
                    "content": "内容"
                },
                {
                    "id": 2,
                    "title": "村（农村社区）依法履职事项指导清单",
                    "content": "内容"
                },
                {
                    "id": 3,
                    "title": "城市社区依法履职事项指导清单",
                    "content": "内容"
                },
                {
                    "id": 4,
                    "title": "村（农村社区）依法履职事项指导清单",
                    "content": "内容"
                }
            ],
            "total": 9,
            "currentPage": 1,
            "size": 10,
            "pages": 10
        }
    })
});

router.get("/jiahe/proof_documents", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "records": [
                {
                    "id": 1,
                    "title": "证明1",
                    "images": "string"
                },
                {
                    "id": 2,
                    "title": "证明2",
                    "images": "string"
                }
            ],
            "total": 9,
            "currentPage": 1,
            "size": 10,
            "pages": 10
        }
    })
});

router.get("/jiahe/proof_info", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "响应成功",
        "code": 200,
        "data": {
            "title": "证明1",
            "content": "巴拉巴拉"
        }
    })
});

router.get("/jiahe/list_community_activities", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": [
            {
                "id": 1,
                "title": "string",
                "startTime": "2025-04-10T12:34:56",
                "images": "string"
            },
            {
                "id": 2,
                "title": "string",
                "startTime": "2025-04-10T12:34:56",
                "images": "string"
            }
        ]
    })
});

router.get("/jiahe/community_activity_detail", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "errorMessage": "string",
        "code": 200,
        "data": {
            "title": "嘉禾社区",
            "images": [
                "https://example.com/img1.jpg",
                "https://example.com/img2.jpg",
                "https://example.com/img3.jpg"
            ],
            "content": "活动描述内容"
        }
    })
});

module.exports = router;