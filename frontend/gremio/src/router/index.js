import { createRouter, createWebHistory } from 'vue-router'
import HomeComponent from '../views/HomeView.vue'
import MyTaskComponent from '@/views/MyTaskView.vue'
import InboxComponent from '@/views/InboxView.vue'
import GoalsComponent from '@/views/GoalsView.vue'
import PortfoliosComponent from '@/views/PortfoliosView.vue'
import ReportingComponent from '@/views/ReportingView.vue'
import SignInComponent from '@/views/SignIn.vue'
import SignUpComponent from '@/views/SignUp.vue'
import store from '../store/vuex'
 const routes = [

  {
    path: '/home',
    name: 'Home',
    component: HomeComponent,
    beforeEnter(){
      if(store.getters.user == null){
        return "signin"
      }
    }

  },
  {
    path: '/inbox',
    name: 'Inbox',
    component: InboxComponent
  },
  {
    path: '/my-tasks',
    name: 'MyTasks',
    component: MyTaskComponent
  },
  {
    path: '/goals',
    name: 'Goals',
    component: GoalsComponent
  },
  {
    path: '/portfolios',
    name: 'Portfolios',
    component: PortfoliosComponent
  },
  {
    path: '/reporting',
    name: 'Reporting',
    component: ReportingComponent
  },
  {
    path: '/signin',
    name: 'SignIn',
    component: SignInComponent
  },
  {
    path: '/signup',
    name: 'SignUp',
    component: SignUpComponent
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
