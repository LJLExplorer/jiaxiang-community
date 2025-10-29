// mock-server/server.js
const express = require('express');
const bodyParser = require('body-parser');
const cors = require('cors');
const multer = require('multer');

const app = express();
const PORT = 3000;

app.use(cors());
app.use(bodyParser.json());

app.use(function (req, res, next) {
    console.log(`${req.method} ${req.url}`);
    next();
})

// 模拟登录接口
app.post('/api/auth/login', (req, res) => {
    const {username, password} = req.body;
    res.json({
        "msg": "操作成功",
        "code": 200,
        "token": "eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6ImJiNGIzMGIyLWM1YTctNDJlZC05ZmUzLWNlNjg0MjU2ZDE5NiJ9.TXacUWjetTzVzvwmlPU40RMp-ivpBA1gDOwo1okDq3TUL7-vLuSMlqbFKqvk-D5CHKZrUnIXbjtSYAY_QZfbJA"
    });
    /*if (username === 'admin' && password === '123456') {

    } else {
        res.status(401).json({ message: '用户名或密码错误' });
    }*/
});
app.post("/api/jiahe/upload_file", (req, res) => {
    res.json({
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        "data": "http://127.0.0.1:9000/jiaxiang/2025/07/03/9439aad9fd9a455b9fe428221378afd2.png"
    });
})
app.put('/api/jiahe/update_community_activity_detail/:id', (req, res) => {
    console.log(req.body);

    res.json({});
});

app.post('/api/jiahe/add_community_activity_detail', (req, res) => {
    console.log(req.body);

    res.json({});
});

app.delete('/api/jiahe/delete_community_activity_detail', (req, res) => {
    res.json({});
});
app.put('/api/jiahe/update_community_profile', (req, res) => {
    console.log(req.body);

    res.json({});
});
app.post('/api/jiahe/add_community_honor', (req, res) => {
    console.log(req.body);

    res.json({});
});

app.put('/api/jiahe/update_community_honor', (req, res) => {
        console.log(req.body);

    res.json({});
});

app.delete('/api/jiahe/delete_community_honor', (req, res) => {
    res.json({});
});
app.post('/api/jiahe/add_grid_management', (req, res) => {
    console.log(req.body);

    res.json({});
});

app.put('/api/jiahe/update_grid_management', (req, res) => {
    console.log(req.body);

    res.json({});
});

app.delete('/api/jiahe/delete_grid_management', (req, res) => {
    res.json({});
});
app.post('/api/jiahe/add_personal_info', (req, res) => {
    console.log(req.body);
    res.json({});
});

app.put('/api/jiahe/update_personal_info', (req, res) => {
    console.log(req.body);
    res.json({});
});


app.delete('/api/jiahe/delete_personal_info', (req, res) => {
    console.log(req.body);
    res.json({});
});
app.post('/api/jiahe/add_serve_people_info', (req, res) => {
    console.log(req.body)

    res.json({});
});

app.put('/api/jiahe/update_serve_people_info', (req, res) => {
    console.log(req.body)

    res.json({});
});


app.delete('/api/jiahe/delete_serve_people_info', (req, res) => {
    console.log(req.body)
    res.json({});
});
const storage = multer.memoryStorage();

const upload = multer({ storage: storage });

app.post('/api/jiahe/add_item_matters', upload.single('file'), (req, res) => {
    console.log(req.file, req.body)
    res.json({});
});
app.delete('/api/jiahe/delete_matters', (req, res) => {
    res.json({});
});

app.post('/api/jiahe/add_proof_info', (req, res) => {
    console.log(req.body)
    res.json({});
});

app.delete('/api/jiahe/delete_proof_info', (req, res) => {
    res.json({});
});


const router = express.Router();

router.use(express.urlencoded({extended: false}));
router.get("/jiahe/community_profile", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
    "host": null,
    "code": 200,
    "errorMessage": "操作成功",
    "data": {
        "artiProfileId": 1,
        "title": "嘉和社区",
        "images": "http://127.0.0.1:9000/jiaxiang/2025/04/10/community_gate.jpg",
        "profile": "1嘉和社区地处嘉祥县城北，管理范围为东至武路、西至老嘉梁路，南至曾子大道，北至嘉北路。嘉和社区下辖财苑小区、博悦城、碧城华园、曾子园、金都花苑、红旗花园、国际商贸城、嘉和苑西区、圣贤世家等9个小区，居民3333333户、972人，党员259名，商铺532家，拥有嘉和园幼儿园、嘉祥县实验小学、嘉祥县第四中学3所学校，教育和商贸资源丰富。\n 嘉和社区党群中心位于演武路与嘉北路交叉口的临街位置，于2024年8月建成投入使用，服务范围广、功能全，阵地面积1500余平方米。社区立足便民利民、务实管用，科学设置和睦嘉议事厅、暖新嘉驿站、邻里同行坊等12处特色功能阵地，构建一站式社区生活“服务圈”。目前累计开展各类服务活动180余次，服务居民1.2万余人次。\n 嘉和社区坚持“党建为核、治理为基、服务为本”，积极整合社区资源、搭建治理平台、凝聚社会力量，打造了“幸福嘉和邻里”的社区品牌，引导各方力量共同参与社区治理，不断提升居民在社区生活的获得感、幸福感。\n 党建赋能，领航“幸福舫”。完善“社区大党委一网格（小区）党支部一楼栋党小组一党员中心户”四级组织架构，开展“支部进小区、党员进楼栋”行动，构建横向到边、纵向到底的组织体系。持续推进智慧社区建设，完善数据采集、信息发布、网上议事、志愿服务等应用场景，提升服务居民的能力和水平，打造网上服务群众平台。\n 居民参与，启航“幸福舫”。秉承“把居民当家人、把社区当家建”的初衷，打造开放式、互动式办公区域，为居民提供事项办理、政策咨询等服务。充分利用党组织的政治优势和组织优势，引导居民参与社区治理，启航“幸福舫” \n 多方协同，护航“幸福舫”。建立社区“红色合伙人”资源库，招募嘉祥口腔医院、银泰商圈等“红色合伙人”10个，领资源、服务项目24个。定期邀请驻区单位、社会组织、志愿者等参与社区服务，多方协同，护航“幸福舫”. \n"
    }
})
});
router.get("/jiahe/community_honor", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        "data": {
            "currentPage": 1,
            "size": 10,
            "total": 5,
            "pages": 1,
            "records": [
                {
                    "id": 1,
                    "communityId": 1,
                    "honorDetail": "2018年，荣获“全国文明社区”称号。"
                },
                {
                    "id": 2,
                    "communityId": 1,
                    "honorDetail": "2019年，被评为“优秀基层党组织"
                },
                {
                    "id": 3,
                    "communityId": 1,
                    "honorDetail": "2020年，获得“社区治理创新奖"
                },
                {
                    "id": 4,
                    "communityId": 1,
                    "honorDetail": "2021年，荣获\"绿色环保社区\"称号。\n\n"
                },
                {
                    "id": 5,
                    "communityId": 1,
                    "honorDetail": "2022年，被评为\"最具幸福感社区\"。"
                }
            ]
        }
    })
});
router.get("/jiahe/grid_management", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        "data": [
            {
                "id": 1,
                "community": "嘉和社区",
                "icon": "",
                "meta": {
                    "profile": "嘉和社区是一个充满温暖与活力的居住区域，致力于为居民提供舒适、安全、便捷的生活环境。 社区定期举办各类活动，包括健康讲座、体育赛事、环保行动等，以增强邻里关系，促进社区文化建设。",
                    "title": "嘉和社区简介"
                }
            },
            {
                "id": 2,
                "community": "祥城北苑社区",
                "icon": "",
                "meta": {
                    "profile": "祥城北苑社区是一个充满温暖与活力的居住区域，致力于为居民提供舒适、安全、便捷的生活环境。 社区定期举办各类活动，包括健康讲座、体育赛事、环保行动等，以增强邻里关系，促进社区文化建设。",
                    "title": "祥城北苑社区简介"
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
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        "data": {
            "currentPage": 1,
            "size": 10,
            "total": 12,
            "pages": 2,
            "records": [
                {
                    "id": 2,
                    "title": "和睦嘉",
                    "images": "https://ts1.tc.mm.bing.net/th/id/OIP-C.3bZM-asS17t29A4c2mlzQQAAAA?rs=1&pid=ImgDetMain&o=7&rm=3",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "和睦嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-04-11T21:17:25",
                    "updateTime": "2025-06-14T19:15:10"
                },
                {
                    "id": 3,
                    "title": "和美嘉\n                       ",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/04/11/weimin4.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "和美嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。\n\n                       ",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-04-11T21:17:25",
                    "updateTime": "2025-06-14T19:15:10"
                },
                {
                    "id": 4,
                    "title": "聆听嘉",
                    "images": "https://ts1.tc.mm.bing.net/th/id/OIP-C.3bZM-asS17t29A4c2mlzQQAAAA?rs=1&pid=ImgDetMain&o=7&rm=3",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "聆听嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-06-15T19:51:16"
                },
                {
                    "id": 5,
                    "title": "爱上嘉",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/06/15/weimin6.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "爱上嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-06-15T19:55:47"
                },
                {
                    "id": 6,
                    "title": "敬业嘉",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/07/24/weimin7.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "敬业嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-07-24T16:51:44"
                },
                {
                    "id": 7,
                    "title": "邻里童乐坊",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/06/15/weimin8.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "邻里童乐坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-06-15T19:58:20"
                },
                {
                    "id": 8,
                    "title": "邻里同心坊",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/06/15/weimin9.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "邻里同心坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-06-15T19:58:20"
                },
                {
                    "id": 9,
                    "title": "邻里同行坊",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/06/15/weimin10.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "邻里同行坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-06-15T19:58:20"
                },
                {
                    "id": 10,
                    "title": "邻里同悦坊",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/06/15/weimin11.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "邻里同悦坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-14T19:55:58",
                    "updateTime": "2025-06-15T19:58:20"
                },
                {
                    "id": 11,
                    "title": "幸福学堂",
                    "images": "http://127.0.0.1:9000/jiaxiang/2025/06/15/weimin12.jpg",
                    "phone": "0550-1234567",
                    "openDaysAndTime": "工作日08:00-17:30",
                    "location": "丰山街道社区服务中心3楼",
                    "serviceIntro": "幸福学堂是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    "serviceContent": null,
                    "serviceRules": null,
                    "startTime": "2025-06-15T19:47:59",
                    "updateTime": "2025-06-15T19:58:20"
                }
            ]
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
        "host": null,
        "code": 200,
        "errorMessage": "操作成功",
        "data": {
            "currentPage": 1,
            "size": 4,
            "total": 2,
            "pages": 1,
            "records": [
                {
                    "id": "4ad93868-6aaa-460f-8d09-cc7e8ad7de2c",
                    "communityId": 1,
                    "title": "城市社区依法协助政府事项指导清单.md",
                    "content": "<ol>\n<li>​<strong>协助维护社会治安。​</strong><br />依据：</li>\n</ol>\n<ul>\n<li>依据：《中华人民共和国城市居民委员会组织法》第三条</li>\n<li>《安徽省实施《中华人民共和国城市居民委员会组织法》办法》第三条</li>\n<li>《安徽省社会治安综合治理条例》第十一条</li>\n</ul>\n<ol start=\"2\">\n<li>​<strong>协助开展反恐怖主义工作。​</strong><br />依据：</li>\n</ol>\n<ul>\n<li>依据：《中华人民共和国反恐怖主义法》第十七条、第七十四条</li>\n</ul>\n<ol start=\"3\">\n<li>​<strong>协助做好禁毒防范和社区戒毒等工作。​</strong><br />依据：</li>\n</ol>\n<ul>\n<li>依据：《中华人民共和国禁毒法》第十七条</li>\n<li>《安徽省禁毒条例》第八条</li>\n</ul>\n<ol start=\"4\">\n<li>​<strong>协助做好常住和暂(寄)住人口等工作。​</strong><br />依据：</li>\n</ol>\n<ul>\n<li>依据：《安徽省社会治安综合治理条例》第十一条</li>\n<li>《居住证暂行条例》第八条</li>\n</ul>\n"
                },
                {
                    "id": "2d82a6f5-6883-46f0-b55f-120b7a7c1def",
                    "communityId": 1,
                    "title": "城市社区依法履职事项指导清单.md",
                    "content": "<ol>\n<li>\n<p><strong>开展便民便利的社区服务活动，可以兴办有关的服务事业。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第四条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法〉 办法》第三条</p>\n</li>\n<li>\n<p><strong>管理本居民委员会的财产</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第四条<br />《安徽省实施(中华人民共和国城市居民委员会组织法〉办法》<br />第二十三条《中华人民共和国民法典》第一百零一条、第二百六十二 条、第二百六十四条、第二百六十五条</p>\n</li>\n<li>\n<p><strong>召集居民会议并报告工作，执行其所作出的决定、决议。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第十条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法〉 办法》第三条、第十三条</p>\n</li>\n<li>\n<p><strong>设立人民调解、治安保卫、公共卫生、 社区服务等委员会。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第十三条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法〉办法》第十六条</p>\n</li>\n<li>\n<p><strong>召开居民会议制定、修改居民公约和 有关规章制度。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第十五条<br />《安徽省实施《中华人民共和国城市居民委员会组织法〉 办法》第十三条</p>\n</li>\n<li>\n<p><strong>办理本居住地区居民的公共事务和公 益事业。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第三条<br />《安徽省实施《中华人民共和国城市居民委员会组织法〉办法》第三条</p>\n</li>\n<li>\n<p><strong>调解民间纠纷，促进居民家庭和谐、 邻里团结。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第三条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法〉办法》第三条<br />《中华人民共和国人民调解法》第八条</p>\n</li>\n<li>\n<p><strong>向人民政府或者它的派出机构反映居 民的意见、要求和提出建议。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第三条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法〉 办法》第三条</p>\n</li>\n<li>\n<p><strong>宣传宪法、法律、法规和国家的政策维护居民合法权益，教育居民履行依 法应尽的义务。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第三条<br />《安徽省实施(中华人民共和国城市居民委员会组织法 办法》第三条</p>\n</li>\n<li>\n<p><strong>组织居民开展创建文明城市、文明小 区和文明家庭等多种形式的社会主义 精神文明建设活动。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第三条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法办法》第三条</p>\n</li>\n<li>\n<p><strong>居民委员会及其成员应当遵守宪法、法律、法规和国家的政策，办事公道  热心为居民服务。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第十二条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法 办法》第六条</p>\n</li>\n<li>\n<p><strong>收支项目应当及时公布，接受居民监督。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第十六条<br />《安徽省实施〈中华人民共和国城市居民委员会组织法) 办法》第十八条</p>\n</li>\n<li>\n<p><strong>多民族居住地区的居民委员会，应当教育居民互相帮助，互相尊重，加强民族团结。</strong><br />依据：<br />《中华人民共和国城市居民委员会组织法》第五条<br />《安徽省实施(中华人民共和国城市居民委员会组织法) 办法》第三条</p>\n</li>\n</ol>\n"
                }
            ]
        }
    })
});
router.get("/jiahe/proof_documents", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
    "host": null,
    "code": 200,
    "errorMessage": "操作成功",
    "data": {
        "currentPage": 1,
        "size": 6,
        "total": 7,
        "pages": 2,
        "records": [
            {
                "id": 1,
                "communityId": 1,
                "title": "证明一"
            },
            {
                "id": 2,
                "communityId": 1,
                "title": "证明二"
            },
            {
                "id": 3,
                "communityId": 1,
                "title": "证明三"
            },
            {
                "id": 4,
                "communityId": 1,
                "title": "证明四"
            },
            {
                "id": 5,
                "communityId": 1,
                "title": "证明五"
            },
            {
                "id": 6,
                "communityId": 1,
                "title": "证明六"
            }
        ]
    }
})
});
router.get("/jiahe/proof_info", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
    "host": null,
    "code": 200,
    "errorMessage": "操作成功",
    "data": {
        "id": 1,
        "communityId": 1,
        "title": "证明一",
        "images": null,
        "content": "<h3>一、事项名称、内容及用途</h3>\n                        <p>亲属关系证明（仅限公证）。在无法通过公安、民政、卫生健康等部门信息共享方式核对当事人亲属关系，且当事人居民户口簿、结婚证、《出生医学证明》、历史户籍档案等均不能够反映当事人亲属关系的情况下，司法行政部门办理涉及当事人有关公证事项时，村（社区）对属于自身职责范围内且能够核实的事项，依法据实出具证明当事人亲属关系的材料。</p>\n\n                        <h3>二、开具单位</h3>\n                        <p>村（社区）。</p>\n\n                        <h3>三、法律政策依据</h3>\n                        <p>《中华人民共和国公证法》第十一条根据自然人、法人或者其他组织的申请，公证机构办理下列公证事项：（一）合同；（二）继承；（三）委托、声明、赠与、遗嘱；（四）财产分割；（五）招标投标、拍卖；（六）婚姻状况、亲属关系、收养关系；（七）出生、生存、死亡、身份、经历、学历、学位、职务、职称、有无违法犯罪记录；（八）公司章程；（九）保全证据；（十）文书上的签名、印鉴、日期，文书的副本、影印本与原本相符；（十一）自然人、法人或者其他组织自愿申请办理的其他公证事项。法律、行政法规规定应当公证的事项，有关自然人、法人或者其他组织应当向公证机构申请办理公证。《遗赠扶养协议公证细则》第八条 申办遗赠扶养协议公证，当事人应向公证处提交以下证件和材料：（一）当事人遗赠扶养协议公证申请表；（二）当事人的居民身份证或其他身份证明；（三）扶养人为组织的，应提交资格证明、法定代表人身份证明，代理人应提交授权委托书；（四）村民委员会、居民委员会或所在单位出具的遗赠人的家庭成员情况证明；（五）遗赠财产清单和所有权证明；（六）村民委员会、居民委员会或所在单位出具的扶养人的经济情况和家庭成员情况证明；（七）扶养人有配偶的，应提交其配偶同意订立遗赠扶养协议的书面意见；（八）遗赠扶养协议；（九）公证人员认为应当提交的其他材料。《民政部国家发展改革委公安部司法部人力资源社会保障部国家卫生健康委关于改进和规范基层群众性自治组织出具证明工作的指导意见》（民发〔2020〕20号）附件第1项例外规定。</p>\n\n                        <h3>四、办理（申请）条件、材料及流程</h3>\n                        <p>（一）办理（申请）条件</p>\n                        <p>当事人到户籍所在地或原户籍所在地村（社区）提出申请，并按要求提供相关材料。</p>\n                        <p>（二）办理（申请）材料</p>\n                        <ul>\n                            <li>当事人身份证</li>\n                            <li>当事人户口本</li>\n                        </ul>\n                        <p>（三）办理（申请）流程</p>\n                        <ol>\n                            <li>办理（申请）。当事人到户籍所在地或原户籍所在地村（社区）提出申请，并按要求提供相关材料。</li>\n                            <li>受理。村（社区）经审查，当事人一方户籍或原户籍在本村（社区）且提交材料齐全的，予以受理，双方当事人户籍或原户籍均不在本村（社区）的，不予受理。受理后，对当事人与相关人员是否存在亲属关系的事实进行调查核实。</li>\n                            <li>出具。经调查核实，出具对当事人与相关人员存在亲属关系的事实予以证明的材料。</li>\n                        </ol>\n\n                        <h3>五、办理时限</h3>\n                        <p>5个工作日。</p>\n\n                        <h3>六、要求开具单位</h3>\n                        <p>司法行政部门。</p>\n\n                        <h3>七、证明式样</h3>\n                        <p>亲属关系证明式样（仅限公证）</p>"
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
                "images": "aaa"
            },
            {
                "id": 2,
                "title": "string",
                "startTime": "2025-04-10T12:34:56",
                "images": "aaa"
            }
        ]
    })
});
router.get("/jiahe/community_activity_detail", (req, res) => {
    const query = req.query;
    console.log(`接收到请求。方法：${req.method}，路径：${req.url}，请求参数：${JSON.stringify(query)}`)


    res.send({
    "host": null,
    "code": 200,
    "errorMessage": "操作成功",
    "data": {
        "id": 2,
        "communityId": 23,
        "title": "嘉祥县“祥城慧老”健身气功八段锦公益讲堂活动\r",
        "location": "嘉祥街道嘉和社区党群服务中心一楼大厅",
        "startTime": "2024-11-13T09:00:00",
        "endTime": "2024-11-13T10:00:00",
        "organizer": null,
        "participantCount": 20,
        "participantType": "居民",
        "theme": "“祥城慧老”健身气功八段锦课堂",
        "introduction": "为推动全民健身工作，提高全民健康素质，营造全民健身的氛围，老年大学联合嘉和社区开展“八段锦”健身公益讲堂活动，旨在为居民们带来身心的放松和愉悦，更有助于居民探索健康生活的新路径。",
        "content": "1、嘉和社区负责布置活动场地、招募居民、宣传报道\n2、老年大学专业老师现场教学\n",
        "address": "嘉祥街道嘉和社区党群服务中心一楼大厅",
        "images": [
            "http://127.0.0.1:9000/jiaxiang/2025/04/09/baduanjin1.jpg",
            "http://127.0.0.1:9000/jiaxiang/2025/04/09/baduanjin2.jpg"
        ],
        "createTime": "2025-04-09T21:26:33",
        "updateTime": "2025-04-10T18:57:25"
    }
})
});
app.use("/api", router);


app.listen(PORT, () => console.log(`Mock server running at http://localhost:${PORT}`));
