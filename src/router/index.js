import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import TaskInit from '../components/TaskInit.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'landing-page',
      component: HomeView
    },
    {
      path: '/task',
      name: 'task',
      component: TaskInit
    }
  ]
})

export default router
