<script>
const imageUpload = false;    //表示当前页面是否包含图片上传功能

export default {
  data() {
    // 简介时间验证
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

      activityList: {
        "artiProfileId": 0,
        "title": "",
        "images": "",
        "profile": ""
      }, // 简介列表数据
      total: 0, // 总条数
      // 控制对话框显示
      dialogVisible: false, // 添加简介
      editVisible: false, // 编辑简介
      // 表单数据
      addForm: {
        "communityId": 1,
        "honorDetail": "",
      },
      // 两个上传组件的文件列表（独立维护，避免冲突）
      activityFileList: [],  // 简介图片的文件列表
      coverFileList: [],      // 封面图片的文件列表

      // 表单验证规则
      addFormRules: {
        communityId: [
          {required: true, message: '请输入communityId', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ]
      },
      imageName2URL: new Map(),

      editForm: {
        "artiProfileId": 123,
        "title": "",
        "profile": ""
      }, // 编辑表单数据

      // 表单验证规则
      editFormRules: {
        artiProfileId: [
          {required: true, message: '请输入ID', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ]
      },
    };
  },
  async created() {
    await this.getActivityList();
  },
  methods: {
    // 获取简介列表
    async getActivityList() {
      const {data: res} = await this.$http.get('/api/jiahe/community_profile');

      if (res.code !== 200) {
        this.$message.error(res.errorMessage);
      } else {
        this.activityList = res.data;
      }

    },

    handleActivityExceed(files, fileList) {
      this.$message.warning(`简介图片最多只能上传${files.length}张，已自动忽略多余文件`);
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

        // 同步更新简介图片数组（从文件列表中提取所有URL）
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
      // 同步更新简介图片数组
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

      this.$message.info(`已移除简介图片："${file.name}"`);
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

    // 重置添加表单
    DialogIsClosed() {
      // this.$refs.addFormRef.resetFields();
      // this.$refs.editFormRef.resetFields();
      // 清空文件列表和绑定的URL
      if (!imageUpload) return;
      this.activityFileList = [];
      this.coverFileList = [];
      this.addForm.images = [];
      this.addForm.coverImage = "";
      this.editForm.images = [];
      this.editForm.coverImage = "";
    },
    // 添加简介
    addActivity() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }

        const {data: res} = await this.$http.post("/api/jiahe/add_community_honor", this.addForm);

        this.$message.success("添加荣誉成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

    async handleDelete(row) {

      const confirmResult = await this.$confirm('此操作将永久删除该简介, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err);

      if (confirmResult !== 'confirm') {
        return this.$message.info("已取消删除");
      }

      const {data: res} = await this.$http.delete(`/api/jiahe/delete_community_honor`,
          {
            params: {id: row.id}  // 通过 params 设置查询参数，等价于 ?id=xxx
          });

      this.$message.success("删除简介成功");
      this.getActivityList();
    },
    async handleEdit(row) {

      const info = this.activityList;
      // console.log(info)
      /*content: "简介描述内容"id: (...)images: (...)startTime: (...)title: (...)*/

      for (let name in info) {
        this.editForm[name] = info[name];
      }
      const that = this;

      console.log(info)
      if (imageUpload) {
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
      }

      this.editVisible = true;
    },
// 编辑简介
    editActivity() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }

        const {data: res} = await this.$http.put("/api/jiahe/update_community_profile", this.editForm);

        this.$message.success("修改成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

  }
};
</script>

<template>
  <div>
    <el-row :gutter="20">
      <el-col :span="4">
        <el-button type="primary" @click="handleEdit()">修改简介</el-button>
      </el-col>
    </el-row>
    <el-container style="padding: 20px; background-color: #f5f7fa;">
      <!-- 核心数据展示卡片 -->
      <el-card shadow="hover" style="margin-bottom: 20px;">
        <div slot="header" class="card-header">
          <span style="font-size: 16px; font-weight: bold;">社区基础信息</span>
        </div>

        <!-- 基础信息描述列表 -->
        <el-descriptions border style="margin-bottom: 20px;">
          <el-descriptions-item label="ID" prop="artiProfileId">
            {{ activityList.artiProfileId }}
          </el-descriptions-item>
          <el-descriptions-item label="社区名称" prop="title">
            {{ activityList.title }}
          </el-descriptions-item>
        </el-descriptions>

        <!-- 社区图片展示 -->
        <el-descriptions border>
          <el-descriptions-item label="社区图片">
            <el-image
                :src="activityList.images"
                :preview-src-list="[activityList.images]"
                style="width: 400px; height: 250px; object-fit: cover; border-radius: 4px;"
                lazy
            >
              <div slot="error" class="image-error">
                <i class="el-icon-picture-outline"></i>
              </div>
            </el-image>
          </el-descriptions-item>
        </el-descriptions>
      </el-card>

      <!-- 社区详情文本展示 -->
      <el-card shadow="hover">
        <div slot="header" class="card-header">
          <span style="font-size: 16px; font-weight: bold;">社区详细介绍</span>
        </div>
        <div class="profile-content">
          <!-- 保留文本换行格式 -->
          {{ activityList.profile }}
        </div>
      </el-card>
    </el-container>


    <!-- 编辑简介对话框 -->
    <el-dialog
        title="修改简介"
        :visible.sync="editVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <el-form-item label="artiProfileId" prop="artiProfileId">
          <!-- 1. 设置 type="number" 限制输入类型 -->
          <!--          <el-input
                        v-model.number="editForm.artiProfileId"
                        type="number"
                        placeholder="请输入数字"
                        oninput="value = value.replace(/[^\d]/g, '')"
                    ></el-input>-->
          <p style="margin: 0">{{ editForm.artiProfileId }}</p>

        </el-form-item>
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="profile">
          <el-input v-model="editForm.profile"></el-input>
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
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.profile-content {
  line-height: 1.8;
  font-size: 14px;
  color: #303133;
  white-space: pre-wrap; /* 保留文本中的换行和空格 */
  word-wrap: break-word;
  max-height: 500px; /* 限制最大高度 */
  overflow-y: auto; /* 超出部分滚动 */
  padding: 10px 0;
}

/* 图片加载错误样式 */
.image-error {
  width: 100%;
  height: 100%;
  background-color: #f5f7fa;
  display: flex;
  align-items: center;
  justify-content: center;
  color: #ccc;
}
</style>