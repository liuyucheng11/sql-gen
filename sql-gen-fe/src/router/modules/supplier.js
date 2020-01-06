import Layout from '@/layout'
const supplierRouter = {
  path: '/supplier',
  component: Layout,
  redirect: 'noRedirect',
  name: 'Supplier',
  meta: {
    title: 'Supplier',
    icon: 'chart'
  },
  children:[
    {
      path: '/sql-gen',
      component: () => import('@/views/supplier/index'),
      name: 'supplier',
      meta: { title: 'supplier-sql-gen' }
    },
    {
      path:'/supplier-bank-relate',
      component: () => import('@/views/supplier/components/supplierBank.vue'),
      name:'supplier-bank',
      meta: { title: 'supplier-bank' }

    }
  ]
}
export default  supplierRouter;
