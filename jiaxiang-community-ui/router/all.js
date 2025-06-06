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
            "total": 41,
            "pages": 1,
            "records": [
                {
                    "id": 1,
                    "nameAndPosition": "string",
                    "images": "../img/liangwei-photo/yuyan.png",
                    "duty": "string"
                },
                {
                    "id": 2,
                    "nameAndPosition": "string",
                    "images": "../img/liangwei-photo/yuyan.png",
                    "duty": "string"
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