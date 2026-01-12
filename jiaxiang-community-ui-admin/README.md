# 嘉祥后台

## 项目部署方法

跳转至当前目录后，安装依赖

```
npm i
```

在/src/main.js中可以修改这行代码来更改后端服务器的domain，修改后应重新打包成静态页面（指令如下）
```
axios.defaults.baseURL = "http://localhost:3000/"
```

跳转至dist目录

```
cd dist
```

发布静态页面

```
python -m http.server 8000
```

### 打开测试用服务器

```
nodemon mock-server.js
```

### 使用服务器打开

```
vue-cli-service serve
```

### 打包成静态页面

```
vue-cli-service build
```