<script>

export default {
  data() {
    return {
      menuList: [],
      iconObj: {
        '1': 'el-icon-user-solid',
        '2': 'el-icon-s-cooperation',
        '3': 'el-icon-s-goods',
        '4': 'el-icon-s-order',
        '5': 'el-icon-s-marketing',
        '6': 'el-icon-document',
        '7': 'el-icon-setting',
        '8': 'el-icon-house'
      },
      //
      // 是否折叠的属性
      isTurn: false,
      //被激活的链接地址(用于二级菜单高亮)
      activePath: '',
    }
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push('/login');
    },
    async getMenuList() {
      this.menuList = [
        {id: 1, name: "社区活动", path: "activity"},
        {id: 2, name: "社区荣誉", path: "honor"},
        {id: 3, name: "社区简介", path: "profile"},
        {id: 4, name: "网格管理", path: "grid"},
        {id: 5, name: "履职信息", path: "personal_info"},
        {id: 6, name: "为人民服务", path: "serve_people"},
        {id: 7, name: "事项清单", path: "matters"},
        {id: 8, name: "证明出具", path: "proof"},
      ]
    },
    toggle() {
      this.isTurn = !this.isTurn;
    },
    saveNavState(activePath) {
      this.activePath = activePath;
    }
  },
  created() {
    this.getMenuList();
  },

}
</script>

<template>
  <el-container class="home-container">
    <!--头部区域-->
    <el-header>
      <div>
        <!--        <img src="../assets/heima.png" alt="image">-->
        <span>后台管理系统</span>
      </div>
      <el-button type="info" @click="logout">退出登录</el-button>
    </el-header>
    <!--主体区域-->
    <el-container>
      <el-aside :width="isTurn ? '64px' : '200px'">
        <!-- 折叠按钮 -->
        <div class="toggle-button" @click="toggle">|||</div>
        <!-- 侧边菜单区域 -->
        <el-menu
            background-color="#333744"
            text-color="#fff"
            :router="true"
            :default-active="activePath"
            active-text-color="#409eff"
        >
          <!-- 一级菜单（直接使用el-menu-item实现无二级菜单的导航） -->
          <el-menu-item
              :index="'/' + item.path"
              v-for="item in menuList"
              :key="item.id"
              @click="saveNavState('/' + item.path)"
          >
            <i :class="iconObj[item.id]"></i>
            <span>{{ item.name }}</span>
          </el-menu-item>
        </el-menu>
      </el-aside>

      <!--主体内的主体区域-->
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>

</template>

<style lang="less" scoped>
.home-container {
  height: 100%;
}

.el-header {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #fff;
  font-size: 20px;

  > div {
    display: flex;
    align-items: center;
  }

  span {
    margin-left: 15px;
  }
}

.el-aside {
  background-color: #333744;

  .el-menu {
    border-right: none;
  }

  .toggle-button {
    background-color: #4a5064;
    text-align: center;
    font-size: 10px;
    line-height: 24px;
    color: white;
    letter-spacing: 0.2em;
    cursor: pointer;
  }
}

.el-main {
  background-color: #eaedf1;
}

</style>
