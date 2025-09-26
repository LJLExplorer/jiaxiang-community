<script>
const imageUpload = false;    //表示当前页面是否包含图片上传功能

export default {
  data() {
    // 网格时间验证
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

      activityList: [], // 网格列表数据
      total: 0, // 总条数
      // 控制对话框显示
      dialogVisible: false, // 添加网格
      editVisible: false, // 编辑网格
      // 表单数据
      addForm: {
        "communityCn": "",
        "icon": "",
        "description": ""
      },
      // 两个上传组件的文件列表（独立维护，避免冲突）
      activityFileList: [],  // 网格图片的文件列表
      coverFileList: [],      // 封面图片的文件列表

      // 表单验证规则
      addFormRules: {},
      imageName2URL: new Map(),

      editForm: {
        "id": 0,
        "communityCN": "",
        "description": ""
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
    // 获取网格列表
    async getActivityList() {
      const {data: res} = await this.$http.get('/api/jiahe/grid_management');

      if (res.code !== 200) {
        this.$message.error(res.errorMessage);
      } else {
        /*
        "id": 1,
            "community": "嘉和社区",
            "icon": "",
            "meta": {
                "profile": "嘉和社区是一个充满温暖与活力的居住区域，致力于为居民提供舒适、安全、便捷的生活环境。 社区定期举办各类网格，包括健康讲座、体育赛事、环保行动等，以增强邻里关系，促进社区文化建设。",
                "title": "嘉和社区简介"
            }
            +
                "id": 6,
            "communityCn": "哈哈哈哈哈哈哈哈哈哈哈社区",
            "icon": "http",
            "description": "哈哈社区是个好社区"
            =
            id
            communityCN
            description
        * */
        this.activityList = res.data.map(value => {
          return {
            id: value.id,
            communityCN: value.community,
            description: value.meta.profile
          }
        });
        console.log(this.activityList)
      }

    },

    handleActivityExceed(files, fileList) {
      this.$message.warning(`网格图片最多只能上传${files.length}张，已自动忽略多余文件`);
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

        // 同步更新网格图片数组（从文件列表中提取所有URL）
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
      // 同步更新网格图片数组
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

      this.$message.info(`已移除网格图片："${file.name}"`);
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
    // 添加网格
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

      const confirmResult = await this.$confirm('此操作将永久删除该网格, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).catch(err => err);

      if (confirmResult !== 'confirm') {
        return this.$message.info("已取消删除");
      }

      const {data: res} = await this.$http.delete(`/api/jiahe/delete_grid_management`,
          {
            params: {id: row.id}  // 通过 params 设置查询参数，等价于 ?id=xxx
          });

      this.$message.success("删除网格成功");
      this.getActivityList();
    },
    async handleEdit(row) {

      const info = this.activityList.filter(value => value.id === row.id)[0];
      // console.log(info)
      /*content: "网格描述内容"id: (...)images: (...)startTime: (...)title: (...)*/

      for (let name in info) {
        this.editForm[name] = info[name];
      }
      const that = this;

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

      console.log(this.editForm)
      this.editVisible = true;
    },
// 编辑网格
    editActivity() {
      this.$refs.editFormRef.validate(async valid => {
        if (!valid) {
          this.$message.error("有填写错误");
          return;
        }
        console.log(this.editForm)

        const {data: res} = await this.$http.put("/api/jiahe/update_community_activity_detail/" + this.editForm.id, this.editForm);

        this.$message.success("修改网格成功");
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
            <el-button type="primary" @click="dialogVisible=true">添加网格</el-button>
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
        <!-- 网格表格 -->
        <el-table :data="activityList" border stripe>
          <!--                      id
                      communityCN
                      description-->
          <el-table-column type="index" label="#"></el-table-column>
          <el-table-column label="ID" prop="id"></el-table-column>
          <el-table-column label="社区" prop="communityCN"></el-table-column>
          <el-table-column label="介绍" prop="description"></el-table-column>

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
        title="添加网格"
        :visible.sync="dialogVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="addForm" :rules="addFormRules" ref="addFormRef" label-width="100px">
        <el-form-item label="社区" prop="communityCn">
          <el-input v-model="addForm.communityCn"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="description">
          <el-input type="textarea" v-model="addForm.description" rows="4"></el-input>
        </el-form-item>
      </el-form>


      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="addActivity">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑网格对话框 -->
    <el-dialog
        title="修改网格"
        :visible.sync="editVisible"
        width="50%"
        @close="DialogIsClosed"
    >
      <el-form :model="editForm" :rules="editFormRules" ref="editFormRef" label-width="100px">
        <!--     "id": 0,
                "communityCN": "",
                "description": ""       -->
        <el-form-item label="id" prop="id">
          <!-- 1. 设置 type="number" 限制输入类型 -->
          <el-input
              v-model.number="editForm.id"
              type="number"
              placeholder="请输入数字"
              oninput="value = value.replace(/[^\d]/g, '')"
          ></el-input>
        </el-form-item>
        <el-form-item label="社区" prop="communityCN">
          <el-input v-model="editForm.communityCN"></el-input>
        </el-form-item>
        <el-form-item label="介绍" prop="description">
          <el-input type="textarea" v-model="editForm.description" rows="4"></el-input>
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