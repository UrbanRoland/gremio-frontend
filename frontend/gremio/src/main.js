import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { Quasar } from 'quasar'
import quasarUserOptions from './quasar-user-options'
import './axios'
import store from './store/vuex'
createApp(App).use(Quasar, quasarUserOptions).use(router).use(store).mount('#app')
