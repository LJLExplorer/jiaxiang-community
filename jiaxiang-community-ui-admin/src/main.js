import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './plugins/element.js'
import './assets/css/global.css'
import axios from 'axios'
import VueQuillEditor from "vue-quill-editor"
import 'quill/dist/quill.core.css' // import styles
import 'quill/dist/quill.snow.css' // for snow theme
import 'quill/dist/quill.bubble.css' // for bubble theme

Vue.config.productionTip = false
axios.defaults.baseURL = "http://localhost:3000/"
axios.interceptors.request.use(config => {
  config.headers.Authorization = window.sessionStorage.getItem('token')
  return config;
})
Vue.use(VueQuillEditor)

Vue.prototype.$http = axios;
Vue.filter("dataFormat",function (originVal) {
  const date = new Date(originVal * 1000);
  const year = date.getFullYear();
  const mouth = (date.getMonth() + 1 + '').padStart(2,'0');
  const day = (date.getDate() + 1 + '').padStart(2,'0');
  const hour = (date.getHours() + 1 + '').padStart(2,'0');
  const minutes = (date.getMinutes() + 1 + '').padStart(2,'0');
  const second = (date.getSeconds() + 1 + '').padStart(2,'0');
  return `${year}-${mouth}-${day} ${hour}:${minutes}:${second}`;
})


//配置请求的根路

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
