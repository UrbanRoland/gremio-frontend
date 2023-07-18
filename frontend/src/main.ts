import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import './index.css'
import en from "../src/locales/en.json";
import { createI18n } from "vue-i18n";
import { library } from '@fortawesome/fontawesome-svg-core'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'
import { faUserSecret, faRocket } from '@fortawesome/free-solid-svg-icons'

const i18n = createI18n({
    locale: "en",
    fallbackLocale: "en",
    messages: { en },
  });

library.add(faUserSecret, faRocket)

createApp(App)
.use(store)
.use(router)
.use(i18n)
.component('font-awesome-icon', FontAwesomeIcon)
.mount("#app");
