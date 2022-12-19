import { createRouter, createWebHistory } from 'vue-router'
import HomeComponent from '../components/HomeComponent.vue'
import MyTaskComponent from '@/components/MyTaskComponent.vue'
import InboxComponent from '@/components/InboxComponent.vue'
import GoalsComponent from '@/components/GoalsComponent.vue'
import PortfoliosComponent from '@/components/PortfoliosComponent.vue'
import ReportingComponent from '@/components/ReportingComponent.vue'

const routes = [

  {
    path: '/home',
    name: 'Home',
    component: HomeComponent
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
    path: '/about',
    name: 'about',
    component: () => import('../views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
