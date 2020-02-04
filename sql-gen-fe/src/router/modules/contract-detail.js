import Layout from '@/layout'
const contractRouter = {
  path: '/contract-detail',
  component: Layout,
  redirect: 'noRedirect',
  name: 'contractDetail',
  meta: {
    title: 'contractDetail',
    icon: 'chart'
  },
  children:[
    {
      path: '/sql',
      component: () => import('@/views/contract-detail/index.vue'),
      name: 'contractDetail',
      meta: { title: 'contract-detail' }
    },

  ]
}
export default  contractRouter;
