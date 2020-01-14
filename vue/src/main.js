import Vue from 'vue'
import App from './App.vue'
import router from './router'
import './plugins/iview.js'
import './plugins/element.js'
import axios from 'axios'
import qs from "qs"
import $ from 'jquery'


Vue.prototype.$qs = qs
// 网络请求框架
axios.defaults.withCredentials=true //让ajax携带cookie
Vue.prototype.$http = axios     // 这样设置就可以在组件内用 this.$http 使用axios了
axios.defaults.baseURL = 'http://localhost:8080' //初始化基础地址

Vue.config.productionTip = false

Vue.prototype.$dateFormat = function time(time = +new Date()) {
  var date = new Date(time + 8 * 3600 * 1000);
  return date.toJSON().substr(0, 10).replace('T', ' ').replace(/-/g, '-');
};

router.beforeEach((to, from, next) => {
  if(to.path === '/login' || to.path === '/refresh' || to.path === '/') return next()
  //登录查询
  axios.post('/user/checkLogin')
                    .then(function(respond){
                       if(respond.data == false) return next('/login')
                    })
                    .catch(function(error){
                        console.log(error);
                        return next('/login')
                    });

  // 权限查询
  axios.post('/user/getPosition')
                    .then(function(respond){
                      var position = respond.data;
                      if(position === 'SALESMAN' && to.path.match('/main/salesman/*')) return next();
                      else if(position === 'SALES_KEEPER' && to.path.match('/main/salesKeeper/*')) return next();
                      else if(position === 'WAREHOUSE_KEEPER' && to.path.match('/main/warehouseKeeper/*')) return next();
                      else return next('/main/403');
                    })
                    .catch(function(error){
                        console.log(error);
                        return next('/login')
                    });
  next()
})

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
