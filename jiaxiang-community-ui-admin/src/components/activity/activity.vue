<script>

export default {
  data() {
    // 活动时间验证
    let checkDateTime = (rule, value, callback) => {
      const regDateTime = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
      if (regDateTime.test(value)) {
        callback();
      } else {
        callback(new Error("请输入正确的时间格式(YYYY-MM-DD HH:MM:SS)"));
      }
    };
    return {
      uploadAction: `${this.$http.defaults.baseURL}api/jiahe/upload_file`,

      // 获取活动列表参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 2,
      },
      activityList: [], // 活动列表数据
      total: 0, // 总条数
      // 控制对话框显示
      dialogVisible: false, // 添加活动
      editVisible: false, // 编辑活动
      // 表单数据
      addForm: {
        "id": 1,
        "communityId": 1,
        "title": "嘉祥运动会",
        "location": "社区体育馆",
        "startTime": "2025-07-15T09:00:00",
        "endTime": "2025-07-15T17:30:00",
        "organizer": "社区居委会",
        "participantCount": 200,
        "participantType": "居民",
        "theme": "健康生活",
        "introduction": "一年一度的社区运动会，欢迎大家积极参与！",
        "content": "本次运动会设有多项赛事，包括篮球、羽毛球、乒乓球等，设有丰富奖品。",
        "address": "北京市朝阳区幸福路88号",
        "images": [],
        "coverImage": ""

      },
      // 两个上传组件的文件列表（独立维护，避免冲突）
      activityFileList: [],  // 活动图片的文件列表
      coverFileList: [],      // 封面图片的文件列表

      // 表单验证规则
      addFormRules: {
        title: [
          {required: true, message: '请输入活动标题', trigger: 'blur'},
          {min: 2, max: 50, message: '标题长度在 2 到 50 个字符', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '请输入活动地点', trigger: 'blur'},
          {min: 2, max: 100, message: '地点长度在 2 到 100 个字符', trigger: 'blur'}
        ],
        start_time: [
          {required: true, message: '请输入开始时间', trigger: 'blur'},
          {validator: checkDateTime, trigger: 'blur'}
        ],
        end_time: [
          {required: true, message: '请输入结束时间', trigger: 'blur'},
          {validator: checkDateTime, trigger: 'blur'}
        ],
        participantCount: [
          {required: true, message: '请输入参加人数', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ],
        id: [
          {required: true, message: '请输入ID', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ],
        communityId: [
          {required: true, message: '请输入communityId', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ]
      },
      imageName2URL: new Map(),

      editForm: {
        "id": 1,
        "activateId": 1,
        "communityId": 1,
        "title": "",
        "location": "",
        "startTime": (new Date).toISOString().slice(0, 19),
        "endTime": (new Date).toISOString().slice(0, 19),
        "organizer": "",
        "participantCount": 0,
        "participantType": "",
        "theme": "",
        "introduction": "",
        "content": "",
        "address": "",
        "images": [],
        "coverImage": ""

      }, // 编辑表单数据

      // 表单验证规则
      editFormRules: {
        title: [
          {required: true, message: '请输入活动标题', trigger: 'blur'},
          {min: 2, max: 50, message: '标题长度在 2 到 50 个字符', trigger: 'blur'}
        ],
        address: [
          {required: true, message: '请输入活动地点', trigger: 'blur'},
          {min: 2, max: 100, message: '地点长度在 2 到 100 个字符', trigger: 'blur'}
        ],
        start_time: [
          {required: true, message: '请输入开始时间', trigger: 'blur'},
          {validator: checkDateTime, trigger: 'blur'}
        ],
        end_time: [
          {required: true, message: '请输入结束时间', trigger: 'blur'},
          {validator: checkDateTime, trigger: 'blur'}
        ],
        participantCount: [
          {required: true, message: '请输入参加人数', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ],
        id: [
          {required: true, message: '请输入ID', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ],
        activateId: [
          {required: true, message: '请输入activateId', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ],
        communityId: [
          {required: true, message: '请输入communityId', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ]
      },
    };
  },
  created() {
    this.getActivityList();
  },
  methods: {
    // 获取活动列表
    async getActivityList() {
      const {data: res} = await this.$http.get('/api/jiahe/list_community_activities', {params: this.queryInfo});

      if (res.code !== 200) {
        this.$message.error(res.errorMessage);
      } else {
        this.activityList = res.data;

        for (let item of res.data) {
          item.images = undefined;
        }

        for await (let item of res.data) {
          const { data: res } = await this.$http.get('/api/jiahe/community_activity_detail', { params: { id: item.id } });

          // const {data: res} = await this.$http.get('/api/jiahe/community_activity_detail', {id: item.id});
          item.title = res.data.title;
          item.images = res.data.images;
          item.content = res.data.content;
        }
      }

      /*
      * {
        "data": [
          {
            "id": 1,
            "title": "string",
            "startTime": "2025-04-10T12:34:56",
            "images": "string",
            "content": "string",
          }...
        ]
      }
      * */
    },

    handleActivityExceed(files, fileList) {
      this.$message.warning(`活动图片最多只能上传${files.length}张，已自动忽略多余文件`);
    },

    // 上传失败
    handleActivityImageError(err, file) {
      this.$message.error(`"${file.name}" 上传失败：网络错误`);
    },
    // 上传成功：将URL添加到images数组
    handleActivityImageSuccess(response, file, fileList) {
      if (response.code === 200) {
        this.$message.success(`"${file.name}" 上传成功`);
        // 存储接口返回的URL（假设接口返回格式为 { code: 200, data: { url: "xxx" } }）

        // 同步更新活动图片数组（从文件列表中提取所有URL）
        // this.addForm.images = fileList.map(item => item.url);
        // this.editForm.images = fileList.map(item => item.url);
        this.imageName2URL.set(file.name, response.data)
        this.addForm.images.push(response.data);
        this.editForm.images.push(response.data);
        this.activityFileList.push(file)
        console.log(this.editForm.images)

      } else {
        this.$message.error(`"${file.name}" 上传失败：${response.msg || "未知错误"}`);
      }
    },
    // 移除图片：从images数组中删除对应URL
    handleActivityImageRemove(file, fileList) {
      // 同步更新活动图片数组
      // this.addForm.images = fileList.map(item => item.url);
      // this.editForm.images = fileList.map(item => item.url);
      //根据映射判断是否可以获取到URL，如果可以获取则删除，否则什么也不做
      if (this.imageName2URL.has(file.name)) {
        this.addForm.images.splice(this.imageName2URL.get(file.name), 1);
        this.editForm.images.splice(this.imageName2URL.get(file.name), 1);
        this.imageName2URL.delete(file.name)
      }
      this.activityFileList.splice(file, 1)
      console.log(this.imageName2URL, this.editForm.images)

      this.$message.info(`已移除活动图片："${file.name}"`);
    },
    handleCoverExceed(files, fileList) {
      this.$message.warning("封面图片只能上传1张");
    },
    // 上传成功：将URL绑定到coverImage
    handleCoverImageSuccess(response, file, fileList) {
      if (response.code === 200) {
        this.$message.success("封面上传成功");
        // 只保留最新的一张图片（覆盖旧图）
        this.coverFileList = [file];
        // 存储接口返回的URL
        this.addForm.coverImage = response.data;
        this.editForm.coverImage = response.data;
      } else {
        this.$message.error(`封面上传失败：${response.msg || "未知错误"}`);
      }
    },

    // 上传失败
    handleCoverImageError(err, file) {
      this.$message.error("封面上传失败：网络错误");
    },

    // 移除图片：清空coverImage
    handleCoverImageRemove(file) {
      this.addForm.coverImage = "";
      this.editForm.coverImage = "";
      this.$message.info("已移除封面图片");
    },

    // 活动状态变更
    /*async activityStateChanged(activity) {
      const {data: res} = await this.$http.put(`activities/${activity.id}/state/${activity.status}`);
      if (res.meta.status !== 200) {
        activity.status = !activity.status;
        this.$message.error("更新活动状态失败");
      } else {
        this.$message.success("更新活动状态成功");
      }
    },*/
    // 重置添加表单
    DialogIsClosed() {
      // this.$refs.addFormRef.resetFields();
      // this.$refs.editFormRef.resetFields();
      // 清空文件列表和绑定的URL
      this.activityFileList = [];
      this.coverFileList = [];
      this.addForm.images = [];
      this.addForm.coverImage = "";
      this.editForm.images = [];
      this.editForm.coverImage = "";
    },
    // 添加活动
    addActivity() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }
        console.log(this.addForm)

        const {data: res} = await this.$http.post("/api/jiahe/add_community_activity_detail", this.addForm);

        this.$message.success("添加活动成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

    async handleDelete(row) {

      const confirmResult = await this.$confirm('此操作将永久删除该活动, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err);

      if (confirmResult !== 'confirm') {
        return this.$message.info("已取消删除");
      }

      const {data: res} = await this.$http.delete(`/api/jiahe/delete_community_activity_detail`,
          {
            params: {id: row.id}  // 通过 params 设置查询参数，等价于 ?id=xxx
          });

      this.$message.success("删除活动成功");
      this.getActivityList();
    },
    async handleEdit(row) {

      const info = this.activityList.filter(value => value.id === row.id)[0];
      // console.log(info)
      /*content: "活动描述内容"id: (...)images: (...)startTime: (...)title: (...)*/

      for (let name in info) {
        this.editForm[name] = info[name];
      }
      const that = this;

      // 遍历URL数组，生成file-list所需的对象数组
      this.activityFileList = this.editForm.images.map((url, index) => {
        that.imageName2URL.set(`image-${index + 1}.jpg`, url)
        return {
          uid: `-${index}`, // 生成唯一标识（负数避免与新上传文件的uid冲突）
          name: `image-${index + 1}.jpg`, // 显示的文件名（可自定义）
          url: url, // 图片的URL地址（核心）
          status: "success" // 标记为已上传状态
        };
      });

      this.editVisible = true;
    },
// 编辑活动
    editActivity() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }
        console.log(this.editForm)

        const {data: res} = await this.$http.put("/api/jiahe/update_community_activity_detail/" + this.editForm.id, this.editForm);

        this.$message.success("修改活动成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

  }
};
</script>

<template>
  <div>
    <!-- 面包屑导航 -->
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/Home' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>活动管理</el-breadcrumb-item>
      <el-breadcrumb-item>活动列表</el-breadcrumb-item>
    </el-breadcrumb>

    <!-- 卡片区域 -->
    <el-card class="box-card">
      <div>
        <el-row :gutter="20">
          <el-col :span="4">
            <el-button type="primary" @click="dialogVisible=true">添加活动</el-button>
          </el-col>
        </el-row>

        <!--
        {
                "data": [
                  {
                    "id": 1,
                    "title": "string",
                    "startTime": "2025-04-10T12:34:56",
                    "images": "string",
                    "content": "string",
                  }...
                ]
              }-->
        <!-- 活动表格 -->
        <el-table :data="activityList" border stripe>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="id" prop="id"></el-table-column>
          <el-table-column label="title" prop="title"></el-table-column>
          <el-table-column label="startTime" prop="startTime"></el-table-column>
          <!-- 概要图列（处理图片数组） -->
          <el-table-column label="概要图" min-width="200">
            <template slot-scope="scope">
              <div class="image-group">
                <el-image
                    v-for="(imgUrl, index) in scope.row.images"
                    :key="index"
                    :src="imgUrl"
                    :preview-src-list="scope.row.images"
                    class="table-image"
                    lazy
                ></el-image>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="content" prop="content"></el-table-column>
          <!-- 新增：操作列（修改/删除按钮） -->
          <el-table-column label="操作" min-width="180" align="center">
            <template slot-scope="scope">
              <!-- 修改按钮 -->
              <el-button
                  type="primary"
                  size="mini"
                  @click="handleEdit(scope.row)"
                  style="margin-right: 5px;"
              >
                修改
              </el-button>

              <!-- 删除按钮 -->
              <el-button
                  type="danger"
                  size="mini"
                  @click="handleDelete(scope.row)"
              >
                删除
              </el-button>
            </template>
          </el-table-column>
        </el-table>


      </div>
    </el-card>

    <!-- 添加活动对话框 -->
    <!--
        {
          "data": [
            {
              "id": 1,
              "title": "string",
              "startTime": "2025-04-10T12:34:56",
              "images": "string",
              "content": "string",
            }...
          ]
        }-->
    <el-dialog
        title="添加活动"
        :visible.sync="dialogVisible"
        width="50%"
        @close="DialogIsClosed"
    >

      <!--
           {
          "id": 1,
          "communityId": 1,
          "title": "嘉祥运动会",
          "location": "社区体育馆",
          "startTime": "2025-07-15T09:00:00",
          "endTime": "2025-07-15T17:30:00",
          "organizer": "社区居委会",
          "participantCount": 200,
          "participantType": "居民",
          "theme": "健康生活",
          "introduction": "一年一度的社区运动会，欢迎大家积极参与！",
          "content": "本次运动会设有多项赛事，包括篮球、羽毛球、乒乓球等，设有丰富奖品。",
          "address": "北京市朝阳区幸福路88号",
          "images": [
              "http://127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo1.jpg",
              "http://127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo2.jpg"
          ],
          "coverImage": "http://127.0.0.1:9000/jiaxiang/2025/04/09/xinghuo2.jpg"
      }-->
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="ID" prop="id">
          <el-input
              v-model.number="addForm.id"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="communityId" prop="communityId">
          <!-- 1. 设置 type="number" 限制输入类型 -->
          <el-input
              v-model.number="addForm.communityId"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="活动标题" prop="title">
          <el-input v-model="addForm.title"></el-input>
        </el-form-item>
        <el-form-item label="活动地点" prop="location">
          <el-input v-model="addForm.location"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-input v-model="addForm.startTime" placeholder="格式: YYYY-MM-DD HH:MM:SS"></el-input>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-input v-model="addForm.endTime" placeholder="格式: YYYY-MM-DD HH:MM:SS"></el-input>
        </el-form-item>
        <el-form-item label="组织者" prop="organizer">
          <el-input v-model="addForm.organizer"></el-input>
        </el-form-item>
        <el-form-item label="参加人数" prop="participantCount">
          <!-- 1. 设置 type="number" 限制输入类型 -->
          <el-input
              v-model.number="addForm.participantCount"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="参加类型" prop="participantType">
          <el-input v-model="addForm.participantType"></el-input>
        </el-form-item>
        <el-form-item label="主题" prop="theme">
          <el-input v-model="addForm.theme"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="introduction">
          <el-input v-model="addForm.introduction"></el-input>
        </el-form-item>
        <el-form-item label="活动内容" prop="content">
          <el-input type="textarea" v-model="addForm.content" rows="3"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="addForm.address"></el-input>
        </el-form-item>

        <!-- 1. 活动图片（批量上传） -->
        <el-form-item label="活动图片" prop="images">
          <el-upload
              class="upload-demo"
              :action="uploadAction"
              :multiple="true"
              :limit="5"
              list-type="picture-card"
              :file-list="activityFileList"
              :on-exceed="handleActivityExceed"
              :on-success="handleActivityImageSuccess"
              :on-error="handleActivityImageError"
              :on-remove="handleActivityImageRemove"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>

        <!-- 2. 封面图片（单张上传） -->
        <el-form-item label="封面" prop="coverImage">
          <el-upload
              class="upload-demo"
              :action="uploadAction"
              :limit="1"
              :multiple="false"
              list-type="picture-card"
              :file-list="coverFileList"
              :on-exceed="handleCoverExceed"
              :on-success="handleCoverImageSuccess"
              :on-error="handleCoverImageError"
              :on-remove="handleCoverImageRemove"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
      </el-form>


      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addActivity">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑活动对话框 -->
    <el-dialog
        title="修改活动"
        :visible.sync="editVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <el-form-item label="ID" prop="id">
          <el-input
              v-model.number="editForm.id"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="activateId" prop="activateId">
          <el-input
              v-model.number="editForm.activateId"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="communityId" prop="communityId">
          <!-- 1. 设置 type="number" 限制输入类型 -->
          <el-input
              v-model.number="editForm.communityId"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="活动标题" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="活动地点" prop="location">
          <el-input v-model="editForm.location"></el-input>
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-input v-model="editForm.startTime" placeholder="格式: YYYY-MM-DD HH:MM:SS"></el-input>
        </el-form-item>
        <el-form-item label="结束时间" prop="endTime">
          <el-input v-model="editForm.endTime" placeholder="格式: YYYY-MM-DD HH:MM:SS"></el-input>
        </el-form-item>
        <el-form-item label="组织者" prop="organizer">
          <el-input v-model="editForm.organizer"></el-input>
        </el-form-item>
        <el-form-item label="参加人数" prop="participantCount">
          <!-- 1. 设置 type="number" 限制输入类型 -->
          <el-input
              v-model.number="editForm.participantCount"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="参加类型" prop="participantType">
          <el-input v-model="editForm.participantType"></el-input>
        </el-form-item>
        <el-form-item label="主题" prop="theme">
          <el-input v-model="editForm.theme"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="introduction">
          <el-input v-model="editForm.introduction"></el-input>
        </el-form-item>
        <el-form-item label="活动内容" prop="content">
          <el-input type="textarea" v-model="editForm.content" rows="3"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="editForm.address"></el-input>
        </el-form-item>

        <!-- 1. 活动图片（批量上传） -->
        <el-form-item label="活动图片" prop="images">
          <el-upload
              class="upload-demo"
              :action="uploadAction"
              :multiple="true"
              :limit="5"
              list-type="picture-card"
              :file-list="activityFileList"
              :on-exceed="handleActivityExceed"
              :on-success="handleActivityImageSuccess"
              :on-error="handleActivityImageError"
              :on-remove="handleActivityImageRemove"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>

        <!-- 2. 封面图片（单张上传） -->
        <el-form-item label="封面" prop="coverImage">
          <el-upload
              class="upload-demo"
              :action="uploadAction"
              :limit="1"
              :multiple="false"
              list-type="picture-card"
              :file-list="coverFileList"
              :on-exceed="handleCoverExceed"
              :on-success="handleCoverImageSuccess"
              :on-error="handleCoverImageError"
              :on-remove="handleCoverImageRemove"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
      </el-form>


      <span slot="footer" class="dialog-footer">
        <el-button @click="editVisible = false">取 消</el-button>
        <el-button type="primary" @click="editActivity">确 定</el-button>
      </span>
    </el-dialog>


  </div>
</template>

<style scoped lang="less">
/* 调整上传组件样式 */
::v-deep .el-upload--picture-card {
  width: 100px;
  height: 100px;
  line-height: 100px;
}

::v-deep .el-upload-list__item {
  width: 100px;
  height: 100px;
  margin-right: 10px;
}</style>