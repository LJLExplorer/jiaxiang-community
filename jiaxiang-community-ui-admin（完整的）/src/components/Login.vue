<script>
export default {
  data() {
    return {
      //登录表单的数据绑定对象
      loginForm: {
        username: "admin",
        password: "123456"
      },
      loginFormRules: {
        //验证用户名是否合法
        username: [
          {required: true, message: '请输入登录名称', trigger: 'blur'},
          {min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur'}
        ],
        //验证密码是否合法
        password: [
          {required: true, message: '请输入登录密码', trigger: 'blur'},
          {min: 6, max: 15, message: '长度在 6 到 15 个字符', trigger: 'blur'}
        ],
      }
    }
  },
  methods: {
    resetLoginForm() {
      this.$refs.loginFormRef.resetFields();
    },
    login() {
      this.$refs.loginFormRef.validate(async valid => {
        if (!valid) return;
        const {data: res} = await this.$http.post("/api/auth/login", this.loginForm);
        if (res.code !== 200) {
          this.$message.error(res.msg || "登录失败");
        } else {
          this.$message.success("登录成功");
          window.sessionStorage.setItem("token", res.token);
          await this.$router.push('/home');
        }
      });
    }
  }
}
</script>

<template>
  <div class="login_container">
    <div class="login_box">
      <!--头像部分-->
      <div class="avatar-box">
        <img src="../assets/logo.png" alt="image">
      </div>
      <!--登录表单部分-->
      <el-form label-width="0" class="login_form" :model="loginForm" :rules="loginFormRules" ref="loginFormRef">
        <!--登录用户名输入部分-->
        <el-form-item prop="username">
          <el-input prefix-icon="el-icon-user" v-model="loginForm.username"></el-input>
        </el-form-item>
        <!--登录密码输入部分-->
        <el-form-item prop="password">
          <el-input prefix-icon="el-icon-lock" v-model="loginForm.password" type="password"></el-input>
        </el-form-item>
        <!--登录按钮部分-->
        <el-form-item class="buttons">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="resetLoginForm">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;

  .avatar-box {
    height: 130px;
    width: 130px;
    border-radius: 50%;
    border: 1px solid #eee;
    padding: 10px;
    box-shadow: 0 0 10px #ddd;
    position: absolute;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #fff;

    img {
      width: 100%;
      height: 100%;
      border-radius: 50%;
      background-color: #eee;
    }
  }
}

.login_box {
  width: 450px;
  height: 300px;
  background-color: #fff;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.login_form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.buttons {
  display: flex;
  justify-content: flex-end;
}
</style>
