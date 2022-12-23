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
import NotFound from '@/views/NotFound'
import MyProfileComponent from '@/views/MyProfile.vue'

function userIsAuthecticated(){
  if(store.getters.user == null){
    return "signin"
  }
}

 const routes = [

  {
    path: '/home',
    name: 'Home',
    component: HomeComponent,
    beforeEnter: userIsAuthecticated
  },
  {
    path: '/inbox',
    name: 'Inbox',
    component: InboxComponent,
    beforeEnter: userIsAuthecticated
  },
  {
    path: '/my-tasks',
    name: 'MyTasks',
    component: MyTaskComponent,
    beforeEnter: userIsAuthecticated
  },
  {
    path: '/goals',
    name: 'Goals',
    component: GoalsComponent,
    beforeEnter: userIsAuthecticated
  },
  {
    path: '/portfolios',
    name: 'Portfolios',
    component: PortfoliosComponent,
    beforeEnter: userIsAuthecticated
  },
  {
    path: '/reporting',
    name: 'Reporting',
    component: ReportingComponent,
    beforeEnter: userIsAuthecticated
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
  },
  {
    path: '/myprofile',
    name: 'MyProfile',
    component: MyProfileComponent
  },
  {
    path:'/:pathMatch(.*)*',
    name : 'NotFoundPage',
    component: NotFound
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
