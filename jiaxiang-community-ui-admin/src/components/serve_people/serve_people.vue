<script>

export default {
  data() {
    // 履职信息时间验证
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

      // 获取履职信息列表参数
      queryInfo: {
        query: "",
        pagenum: 1,
        pagesize: 2,
      },
      queryInfo1: {
        query: "",
        pageNum: 1,
        pageSize: 2,
      },
      activityList: [], // 履职信息列表数据
      total: 0, // 总条数
      // 控制对话框显示
      dialogVisible: false, // 添加履职信息
      editVisible: false, // 编辑履职信息
      // 表单数据
      addForm: {
        "title": "",
        "images": "",
        "phone": "",
        "openDaysAndTime": "",
        "location": "",
        "serviceIntro": "",
        "serviceContent": "",
        "serviceRules": ""
      },
      // 两个上传组件的文件列表（独立维护，避免冲突）
      activityFileList: [],  // 履职信息图片的文件列表
      coverFileList: [],      // 封面图片的文件列表

      // 表单验证规则
      addFormRules: {},
      imageName2URL: new Map(),

      editForm: {
        "title": "",
        "images": "",
        "phone": "",
        "openDaysAndTime": "",
        "location": "",
        "serviceIntro": "",
        "serviceContent": "",
        "serviceRules": ""
      }, // 编辑表单数据

      // 表单验证规则
      editFormRules: {
        id: [
          {required: true, message: '请输入ID', trigger: 'blur'},
          {type: 'number', message: '请输入有效的数字', trigger: 'blur'},
        ]
      },
    };
  },
  created() {
    this.getActivityList();
  },
  methods: {
    // 获取履职信息列表
    async getActivityList() {
      const {data: res} = await this.$http.get('/api/jiahe/list_serve_people', {params: this.queryInfo1});

      if (res.code !== 200) {
        this.$message.error(res.errorMessage);
      } else {
        this.activityList = res.data.records;
      }
    },

    handleActivityExceed(files, fileList) {
      this.$message.warning(`履职信息图片最多只能上传${files.length}张，已自动忽略多余文件`);
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

        // 同步更新履职信息图片数组（从文件列表中提取所有URL）
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
      // 同步更新履职信息图片数组
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

      this.$message.info(`已移除履职信息图片："${file.name}"`);
    },
    handleCoverExceed(files, fileList) {
      this.$message.warning("封面图片只能上传1张");
    },
    // 上传成功：将URL绑定到images
    handleCoverImageSuccess(response, file, fileList) {
      if (response.code === 200) {
        this.$message.success("封面上传成功");
        // 只保留最新的一张图片（覆盖旧图）
        this.coverFileList = [file];
        // 存储接口返回的URL
        this.addForm.images = response.data;
        this.editForm.images = response.data;
      } else {
        this.$message.error(`封面上传失败：${response.msg || "未知错误"}`);
      }
    },

    // 上传失败
    handleCoverImageError(err, file) {
      this.$message.error("封面上传失败：网络错误");
    },

    // 移除图片：清空images
    handleCoverImageRemove(file) {
      this.addForm.images = "";
      this.editForm.images = "";
      this.$message.info("已移除封面图片");
    },

    // 履职信息状态变更
    /*async activityStateChanged(activity) {
      const {data: res} = await this.$http.put(`activities/${activity.id}/state/${activity.status}`);
      if (res.meta.status !== 200) {
        activity.status = !activity.status;
        this.$message.error("更新履职信息状态失败");
      } else {
        this.$message.success("更新履职信息状态成功");
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
      this.addForm.images = "";
      this.editForm.images = [];
      this.editForm.images = "";
    },
    // 添加履职信息
    addActivity() {
      this.$refs.addFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }
        console.log(this.addForm)

        const {data: res} = await this.$http.post("/api/jiahe/add_serve_people_info", this.addForm);

        this.$message.success("添加履职信息成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

    async handleDelete(row) {

      const confirmResult = await this.$confirm('此操作将永久删除该履职信息, 是否继续?', '提示', {
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

      this.$message.success("删除履职信息成功");
      this.getActivityList();
    },
    async handleEdit(row) {

      const info = this.activityList.filter(value => value.id === row.id)[0];
      // console.log(info)
      /*content: "履职信息描述内容"id: (...)images: (...)startTime: (...)title: (...)*/

      for (let name in info) {
        if (name in this.editForm)
          this.editForm[name] = info[name];
      }
      console.log(this.editForm)
      // const that = this;

      this.imageName2URL.set(`image-1.jpg`, this.editForm.images)
      this.activityFileList = [{
        uid: `-0`, // 生成唯一标识（负数避免与新上传文件的uid冲突）
        name: `image-1.jpg`, // 显示的文件名（可自定义）
        url: this.editForm.images, // 图片的URL地址（核心）
        status: "success" // 标记为已上传状态
      }];

      this.editVisible = true;
    },
// 编辑履职信息
    editActivity() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }
        console.log(this.editForm)

        const {data: res} = await this.$http.put("/api/jiahe/update_community_activity_detail/" + this.editForm.id, this.editForm);

        this.$message.success("修改履职信息成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

  }
};
</script>

<template>
  <div>

    <!-- 卡片区域 -->
    <el-card class="box-card">
      <div>
        <el-row :gutter="20">
          <el-col :span="4">
            <el-button type="primary" @click="dialogVisible=true">添加履职信息</el-button>
          </el-col>
        </el-row>

        <el-table :data="activityList" border stripe>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="id" prop="id"></el-table-column>
          <el-table-column label="标题" prop="title"></el-table-column>
          <!-- 概要图列（适配 images 为 URL 字符串） -->
          <el-table-column label="图" min-width="200">
            <template slot-scope="scope">
              <!-- 先判断 images 字符串是否存在且非空 -->
              <div v-if="scope.row.images" class="single-image">
                <el-image
                    :src="scope.row.images"
                    :preview-src-list="[scope.row.images]"
                    class="table-image"
                    lazy
                >
                  <!-- 图片加载失败时的占位 -->
                  <div slot="error" class="image-placeholder">
                    <i class="el-icon-picture-outline"></i>
                  </div>
                </el-image>
              </div>
              <!-- 当 images 为空/undefined/null 时显示占位符 -->
              <div v-else class="image-placeholder">
                <i class="el-icon-picture-outline"></i>
              </div>
            </template>
          </el-table-column>
          <el-table-column label="手机" prop="phone"></el-table-column>
          <!-- 概要图列（处理图片数组） -->

          <el-table-column label="开放时间" prop="openDaysAndTime"></el-table-column>
          <el-table-column label="地点" prop="location"></el-table-column>
          <el-table-column label="服务介绍" prop="serviceIntro" width="150"></el-table-column>
          <el-table-column label="服务内容" prop="serviceContent"></el-table-column>
          <el-table-column label="服务规则" prop="serviceRules"></el-table-column>
          <el-table-column label="开始时间" prop="startTime"></el-table-column>
          <el-table-column label="更新时间" prop="updateTime"></el-table-column>
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

    <el-dialog
        title="添加履职信息"
        :visible.sync="dialogVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="addForm.title"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="images">
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
        <el-form-item label="电话" prop="phone">
          <el-input v-model="addForm.phone" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="开放时间" prop="openDaysAndTime">
          <el-input v-model="addForm.openDaysAndTime" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="地点" prop="location">
          <el-input v-model="addForm.location" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="服务介绍" prop="serviceIntro">
          <el-input v-model="addForm.serviceIntro" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="服务内容" prop="serviceContent">
          <el-input v-model="addForm.serviceContent" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="服务规则" prop="serviceRules">
          <el-input v-model="addForm.serviceRules" placeholder=""></el-input>
        </el-form-item>
      </el-form>


      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addActivity">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑履职信息对话框 -->
    <el-dialog
        title="修改履职信息"
        :visible.sync="editVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <el-form-item label="标题" prop="title">
          <el-input v-model="editForm.title"></el-input>
        </el-form-item>
        <el-form-item label="图片" prop="images">
          <el-upload
              class="upload-demo"
              :action="uploadAction"
              :limit="1"
              :multiple="false"
              list-type="picture-card"
              :file-list="activityFileList"
              :on-exceed="handleCoverExceed"
              :on-success="handleCoverImageSuccess"
              :on-error="handleCoverImageError"
              :on-remove="handleCoverImageRemove"
          >
            <i class="el-icon-plus"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model="editForm.phone" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="开放时间" prop="openDaysAndTime">
          <el-input v-model="editForm.openDaysAndTime" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="地点" prop="location">
          <el-input v-model="editForm.location" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="服务介绍" prop="serviceIntro">
          <el-input v-model="editForm.serviceIntro" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="服务内容" prop="serviceContent">
          <el-input v-model="editForm.serviceContent" placeholder=""></el-input>
        </el-form-item>
        <el-form-item label="服务规则" prop="serviceRules">
          <el-input v-model="editForm.serviceRules" placeholder=""></el-input>
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