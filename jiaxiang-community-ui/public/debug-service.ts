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
    },
    "/api/jiahe/community_honor": {
        "errorMessage": "string",
        "code": 200,
        getData: (pageNum: number, pageSize: number) => {
            const records = [
                "2018年，荣获“全国文明社区”称号。",
                "2019年，被评为“优秀基层党组织",
                "2020年，获得“社区治理创新奖",
                "2021年，荣获“绿色环保社区”称号",
                "2022年，被评为“最具幸福感社区”。",
            ];

            return {
                "currentPage": pageNum,   // 当前页码
                "size": pageSize,   // 每页记录数
                "total": records.length,     // 总记录数
                "pages": 1,   // 总页数
                "records": records.slice((pageNum - 1) * pageSize, pageNum * pageSize)
            }
        }
    },
    "/api/jiahe/list_serve_people": {
        "errorMessage": "string",
        "code": 200,
        getData: (pageNum: number, pageSize: number) => {
            const records = [
                {
                    id: "service1",
                    title: "暖心嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin2.jpg",
                },
                {
                    id: "service2",
                    title: "和睦嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin3.jpg",
                },
                {
                    id: "service3",
                    title: "和美嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin4.jpg",
                },
                {
                    id: "service4",
                    title: "聆听嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin5.jpg",
                },
                {
                    id: "service5",
                    title: "爱上嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin6.jpg",
                },
                {
                    id: "service6",
                    title: "敬业嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin7.jpg",
                },
                {
                    id: "service7",
                    title: "邻里童乐坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin8.jpg",
                },
                {
                    id: "service8",
                    title: "邻里同心坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin9.jpg",
                },
                {
                    id: "service9",
                    title: "邻里同行坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin10.jpg",
                },
                {
                    id: "service10",
                    title: "邻里同悦坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin11.jpg",
                },
                {
                    id: "service11",
                    title: "幸福学堂",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin12.jpg",
                },

            ];

            return {
                "currentPage": pageNum,   // 当前页码
                "size": pageSize,   // 每页记录数
                "total": records.length,     // 总记录数
                "pages": Math.ceil(records.length / pageSize),   // 总页数
                "records": records.slice((pageNum - 1) * pageSize, pageNum * pageSize)
            }
        },
    },
    "/api/jiahe/serve_people_info": {
        "errorMessage": "string",
        "code": 200,
        getData: (id: string) => {
            const records = [
                {
                    id: "service1",
                    title: "暖心嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin2.jpg",
                    serviceIntro: "暖心嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service2",
                    title: "和睦嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin3.jpg",
                    serviceIntro: "和睦嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service3",
                    title: "和美嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin4.jpg",
                    serviceIntro: "和美嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service4",
                    title: "聆听嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin5.jpg",
                    serviceIntro: "聆听嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service5",
                    title: "爱上嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin6.jpg",
                    serviceIntro: "爱上嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service6",
                    title: "敬业嘉",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin7.jpg",
                    serviceIntro: "敬业嘉是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service7",
                    title: "邻里童乐坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin8.jpg",
                    serviceIntro: "邻里童乐坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service8",
                    title: "邻里同心坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin9.jpg",
                    serviceIntro: "邻里同心坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service9",
                    title: "邻里同行坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin10.jpg",
                    serviceIntro: "邻里同行坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service10",
                    title: "邻里同悦坊",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin11.jpg",
                    serviceIntro: "邻里同悦坊是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },
                {
                    id: "service11",
                    title: "幸福学堂",
                    phone: "0550-1234567",
                    openDaysAndTime: "工作日08:00-17:30",
                    location: "丰山街道社区服务中心3楼",
                    images: "./image/weimin12.jpg",
                    serviceIntro: "幸福学堂是社区为居民提供各类活动的重要场所，配备了先进的音响设备和投影仪，可容纳50人同时活动。",
                    serviceContent: "",
                    serviceRules: ""
                },

            ];

            return records.find((value, index, array) => {
                return value.id === id;
            });
        },
    },
    "/api/jiahe/proof_documents": {
        "errorMessage": "string",
        "code": 200,
        getData: (pageNum: number, pageSize: number) => {
            const records = [
                {id: "proof-1", title: "证明一", images: ""},
                {id: "proof-2", title: "证明二", images: ""},
                {id: "proof-3", title: "证明三", images: ""},
                {id: "proof-4", title: "证明四", images: ""},
                {id: "proof-5", title: "证明五", images: ""},
                {id: "proof-6", title: "证明六", images: ""},
                {id: "proof-7", title: "证明七", images: ""}
            ];

            return {
                "currentPage": pageNum,   // 当前页码
                "size": pageSize,   // 每页记录数
                "total": records.length,     // 总记录数
                "pages": Math.ceil(records.length / pageSize),   // 总页数
                "records": records.slice((pageNum - 1) * pageSize, pageNum * pageSize)
            }
        },
    },
    "/api/jiahe/proof_info": {
        "errorMessage": "string",
        "code": 200,
        getData: (id: string) => {
            const records = [
                {
                    id: "proof-1",
                    title: "证明一",
                    content: `
                        <h3>一、事项名称、内容及用途</h3>
                        <p>亲属关系证明（仅限公证）。在无法通过公安、民政、卫生健康等部门信息共享方式核对当事人亲属关系，且当事人居民户口簿、结婚证、《出生医学证明》、历史户籍档案等均不能够反映当事人亲属关系的情况下，司法行政部门办理涉及当事人有关公证事项时，村（社区）对属于自身职责范围内且能够核实的事项，依法据实出具证明当事人亲属关系的材料。</p>

                        <h3>二、开具单位</h3>
                        <p>村（社区）。</p>

                        <h3>三、法律政策依据</h3>
                        <p>《中华人民共和国公证法》第十一条根据自然人、法人或者其他组织的申请，公证机构办理下列公证事项：（一）合同；（二）继承；（三）委托、声明、赠与、遗嘱；（四）财产分割；（五）招标投标、拍卖；（六）婚姻状况、亲属关系、收养关系；（七）出生、生存、死亡、身份、经历、学历、学位、职务、职称、有无违法犯罪记录；（八）公司章程；（九）保全证据；（十）文书上的签名、印鉴、日期，文书的副本、影印本与原本相符；（十一）自然人、法人或者其他组织自愿申请办理的其他公证事项。法律、行政法规规定应当公证的事项，有关自然人、法人或者其他组织应当向公证机构申请办理公证。《遗赠扶养协议公证细则》第八条 申办遗赠扶养协议公证，当事人应向公证处提交以下证件和材料：（一）当事人遗赠扶养协议公证申请表；（二）当事人的居民身份证或其他身份证明；（三）扶养人为组织的，应提交资格证明、法定代表人身份证明，代理人应提交授权委托书；（四）村民委员会、居民委员会或所在单位出具的遗赠人的家庭成员情况证明；（五）遗赠财产清单和所有权证明；（六）村民委员会、居民委员会或所在单位出具的扶养人的经济情况和家庭成员情况证明；（七）扶养人有配偶的，应提交其配偶同意订立遗赠扶养协议的书面意见；（八）遗赠扶养协议；（九）公证人员认为应当提交的其他材料。《民政部国家发展改革委公安部司法部人力资源社会保障部国家卫生健康委关于改进和规范基层群众性自治组织出具证明工作的指导意见》（民发〔2020〕20号）附件第1项例外规定。</p>

                        <h3>四、办理（申请）条件、材料及流程</h3>
                        <p>（一）办理（申请）条件</p>
                        <p>当事人到户籍所在地或原户籍所在地村（社区）提出申请，并按要求提供相关材料。</p>
                        <p>（二）办理（申请）材料</p>
                        <ul>
                            <li>当事人身份证</li>
                            <li>当事人户口本</li>
                        </ul>
                        <p>（三）办理（申请）流程</p>
                        <ol>
                            <li>办理（申请）。当事人到户籍所在地或原户籍所在地村（社区）提出申请，并按要求提供相关材料。</li>
                            <li>受理。村（社区）经审查，当事人一方户籍或原户籍在本村（社区）且提交材料齐全的，予以受理，双方当事人户籍或原户籍均不在本村（社区）的，不予受理。受理后，对当事人与相关人员是否存在亲属关系的事实进行调查核实。</li>
                            <li>出具。经调查核实，出具对当事人与相关人员存在亲属关系的事实予以证明的材料。</li>
                        </ol>

                        <h3>五、办理时限</h3>
                        <p>5个工作日。</p>

                        <h3>六、要求开具单位</h3>
                        <p>司法行政部门。</p>

                        <h3>七、证明式样</h3>
                        <p>亲属关系证明式样（仅限公证）</p>
                    `
                },
                {id: "proof-2", title: "证明二", content: "这是证明二的具体内容。"},
                {id: "proof-3", title: "证明三", content: "这是证明三的具体内容。"},
                {id: "proof-4", title: "证明四", content: "这是证明四的具体内容。"},
                {id: "proof-5", title: "证明五", content: "这是证明五的具体内容。"},
                {id: "proof-6", title: "证明六", content: "这是证明六的具体内容。"},
                {id: "proof-7", title: "证明七", content: "这是证明七的具体内容。"}
            ];

            const target = records.find((value, index, array) => {
                return value.id === id;
            });

            return {title: target.title, content: target.content};
        },
    },

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
            console.log("获取api测试数据：", data);
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