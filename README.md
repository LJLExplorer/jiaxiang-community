# 嘉祥社区（jiaxiang-community）

本仓库包含三部分：

- `jiaxiang-community/`：后端微服务（Spring Boot 3.2.x + Spring Cloud 2023.x / Alibaba）
- `jiaxiang-community-ui/`：门户/前台静态站点（public 目录为主，附带 Node 静态服务器）
- `jiaxiang-community-ui-admin/`：后台管理前端（Vue2 + Vue CLI）

> 说明：以下内容以当前仓库目录结构为准；不同环境（dev/prod）需要配合 Nacos 配置与各模块的 `bootstrap*.yml` 使用。

---

## 1. 目录结构

```
.
├── jiaxiang-community/                # 后端（Maven 多模块聚合）
├── jiaxiang-community-ui/             # 前台/门户（静态资源 + Node server）
└── jiaxiang-community-ui-admin/       # 后台管理（Vue2）
```

---

## 2. 后端：`jiaxiang-community/`

### 2.1 技术栈与版本

- Java：17（根 `pom.xml` 指定 `maven.compiler.source/target=17`）
- Spring Boot：3.2.12
- Spring Cloud：2023.0.5
- Spring Cloud Alibaba：2023.0.3.2
- 配置中心/注册中心：Nacos（`bootstrap.yml` 通过 `spring.config.import` 拉取 Nacos 配置）
- 网关：Spring Cloud Gateway
- 限流/熔断：Sentinel（gateway 侧有依赖与配置）
- 数据访问：MyBatis
- 数据库：MySQL 8.x（依赖声明为 `mysql-connector-j`）

### 2.2 Maven 模块划分（根聚合）

根模块 `jiaxiang-community/pom.xml` 下包含：

- `jiaxiang-community-model`：模型/实体定义
- `jiaxiang-community-utils`：通用工具（如 JWT、Redis、Markdown 相关依赖等）
- `jiaxiang-community-common`：通用公共依赖/组件
- `jiaxiang-community-auth`：认证微服务
- `jiaxiang-community-feign-api`：Feign 接口/客户端定义
- `jiaxiang-community-gateway`：网关聚合（按社区拆分子网关）
- `jiaxiang-community-service`：业务服务聚合
- `jiaxiang-community-basic`：基础模块
- `jiaxiang-community-test`：测试模块

### 2.3 网关模块：`jiaxiang-community/jiaxiang-community-gateway/`

该模块是多模块聚合（`packaging=pom`），包含：

- `community-gateway`
- `jiahe-community-gateway`
- `test-gateway`

典型配置位于各子网关 `src/main/resources/bootstrap.yml` / `bootstrap-prod.yml`：

- `spring.cloud.nacos.server-addr`：Nacos 地址
- `spring.cloud.nacos.discovery/config.namespace`：命名空间
- `spring.config.import`：导入 `${spring.application.name}-${profile}.yaml`（Nacos 配置）
- Sentinel dashboard 相关：`spring.cloud.sentinel.transport.*`

### 2.4 业务服务聚合：`jiaxiang-community/jiaxiang-community-service/`

该模块也是聚合（`packaging=pom`），当前启用模块（pom 中可见）包括：

- `jiaxiang-community-content`
- `jiaxiang-community-portal`（门户后端）
- `jiaxiang-community-activity`

（另有 `jiaxiang-community-admin`、`jiaxiang-community-search` 目录存在，但在聚合 pom 中当前被注释未启用。）

---

## 3. 前台/门户：`jiaxiang-community-ui/`

### 3.1 结构特点

该目录以 `public/` 下的静态资源为主：

- `public/index.html` 及 `public/index/`：站点主入口与页面资源
- `public/jiaxiang/`：站点业务页面与配置

### 3.2 本地启动（静态服务器）

该目录提供 Node 静态服务器脚本：

- `npm run run`：启动 `static-server.js`（默认端口 8083，入口 `http://localhost:8083/index.html`）
- `npm run dev`：`nodemon test-server.js`（用于开发/测试场景，具体以脚本内容为准）

### 3.3 API 地址配置

前台接口地址集中在：

- `jiaxiang-community-ui/public/jiaxiang/config/api-urls.js`

其中 `prefix` 为后端网关/服务地址（例如 `http://<host>:8001`），页面通过 `window.API_URLS.*` 调用。

---

## 4. 后台管理：`jiaxiang-community-ui-admin/`

### 4.1 技术栈

- Vue 2.6.x
- Vue Router 3.x
- Vuex 3.x
- Element UI 2.x
- Axios
- Vue CLI（`vue-cli-service serve/build`）

### 4.2 常用命令

在 `jiaxiang-community-ui-admin/` 目录执行：

```bash
npm i
npm run serve
npm run build
```

项目自带 mock server：

```bash
npm run mock-server
```

### 4.3 后端接口地址

该项目的 `README.md` 提示可在 `src/main.js` 修改：

- `axios.defaults.baseURL = "http://localhost:3000/"`

修改后需要重新构建/发布。

---

## 5. 常见联调方式（建议）

- 后端（网关 + 业务服务）启动并注册到 Nacos
- 前台：启动 `jiaxiang-community-ui` 的静态服务器或直接用静态资源
- 后台：`jiaxiang-community-ui-admin` 用 `npm run serve` 启动开发服务器
- 分别在前台 `api-urls.js`、后台 `src/main.js`（axios baseURL）指向对应的网关/服务地址
