<script>
const imageUpload = false;    //表示当前页面是否包含图片上传功能

export default {
  data() {
    // 事项时间验证
    let checkDateTime = (rule, value, callback) => {
      const regDateTime = /^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}$/;
      if (regDateTime.test(value)) {
        callback();
      } else {
        callback(new Error("请输入正确的时间格式(YYYY-MM-DD HH:MM:SS)"));
      }
    };
    return {
      uploadAction: `${this.$http.defaults.baseURL}api/jiahe/add_item_matters`,

      activityList: [], // 事项列表数据
      total: 0, // 总条数
      // 控制对话框显示
      dialogVisible: false, // 添加事项
      editVisible: false, // 编辑事项
      // 表单数据
      addForm: {
        file: null,
      },
      // 两个上传组件的文件列表（独立维护，避免冲突）
      coverFileList: [],      // 封面图片的文件列表

      // 表单验证规则
      addFormRules: {},
      imageName2URL: new Map(),

      editForm: {
        "id": 0,
        "communityId": 0,
        "honorDetail": ""
      }, // 编辑表单数据

      // 表单验证规则
      editFormRules: {
        id: [
          {required: true, message: '请输入ID', trigger: 'blur'},
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
    // 获取事项列表
    async getActivityList() {
      const {data: res} = await this.$http.get('/api/jiahe/list_matters');

      if (res.code !== 200) {
        this.$message.error(res.errorMessage);
      } else {
        /*
        * [
        * {num:1,content:xxx},....]
        * */
        this.activityList = res.data.records;
      }

      // console.log(this.activityList)
    },

    handleCoverExceed(files, fileList) {
      this.$message.warning("封面图片只能上传1张");
    },
    // 上传成功：将URL绑定到coverImage
    handleCoverImageSuccess(response, file, fileList) {
      this.$message.success("上传成功");
      // 只保留最新的一张图片（覆盖旧图）
      this.coverFileList = [file];
      // 存储接口返回的URL
      this.addForm.file = file;
      this.editForm.file = file;

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
      // this.activityFileList = [];
      // this.coverFileList = [];
      // this.addForm.images = [];
      // this.addForm.coverImage = "";
      // this.editForm.images = [];
      // this.editForm.coverImage = "";
    },
    // 添加事项
    async addActivity() {

      this.$refs.addFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }

        // 3. 构建FormData（用于包含文件和其他表单数据）
        const formData = new FormData();

        // 3.2 添加文件（取fileList中第一个文件的原始对象）
        // file.raw 是原生File对象，必须用它才能正常上传

        formData.append('file', this.coverFileList[0].raw);

        // 4. 发送请求（Content-Type会自动设为multipart/form-data）
        const {data: res} = await this.$http.post(
            '/api/jiahe/add_item_matters',  // 接收表单和文件的接口
            formData
        );

        this.$message.success("添加事项成功");
        this.dialogVisible = false;
        this.getActivityList();

      });
    },

    handleFileChange(file, fileList) {
      // fileList 是当前最新的文件列表，直接赋值给 coverFileList
      this.coverFileList = fileList;
    },

    async handleDelete(row) {

      const confirmResult = await this.$confirm('此操作将永久删除该事项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err);

      if (confirmResult !== 'confirm') {
        return this.$message.info("已取消删除");
      }

      const {data: res} = await this.$http.delete(`/api/jiahe/delete_matters`,
          {
            params: {id: row.id}  // 通过 params 设置查询参数，等价于 ?id=xxx
          });

      this.$message.success("删除事项成功");
      this.getActivityList();
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
            <el-button type="primary" @click="dialogVisible=true">添加事项</el-button>
          </el-col>
        </el-row>

        <el-table :data="activityList" border stripe>
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="社区ID" prop="communityId"></el-table-column>
          <el-table-column label="标题" prop="title"></el-table-column>
          <el-table-column label="内容" width="500">
            <!-- 使用自定义模板 + v-html 渲染 HTML 内容 -->
            <template slot-scope="scope">
              <!-- 用 v-html 解析 HTML 字符串，注意内容安全 -->
              <div v-html="scope.row.content" class="content-html"></div>
            </template>
          </el-table-column>
          <!-- 新增：操作列（修改/删除按钮） -->
          <el-table-column label="操作" min-width="180" align="center">
            <template slot-scope="scope">
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
        title="添加事项"
        :visible.sync="dialogVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="文件" prop="file">
          <el-upload
              class="upload-file"
              ref="upload"
              :action="''"
              :limit="1"
              :file-list="coverFileList"
              :on-success="handleCoverImageSuccess"
              :on-error="handleCoverImageError"
              :on-remove="handleCoverImageRemove"
              :on-change="handleFileChange"
              :auto-upload="false"
              accept=".doc,.docx,.pdf,.xls,.xlsx,.md"
          >
            <!-- 上传按钮 -->
            <el-button size="small" type="primary">选择文件</el-button>
            <!-- 上传提示 -->
            <div slot="tip" class="el-upload__tip">
              支持上传 doc/docx/pdf/xls/xlsx/md 格式文件
            </div>
          </el-upload>
        </el-form-item>
      </el-form>


      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addActivity">确 定</el-button>
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