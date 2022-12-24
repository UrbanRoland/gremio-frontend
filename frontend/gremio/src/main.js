import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import './axios'
import store from './store/vuex'
import './index.css'
createApp(App).use(router).use(store).mount('#app')
