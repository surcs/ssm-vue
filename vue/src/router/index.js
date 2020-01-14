import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../components/Login.vue'
import Main from '../components/Main.vue'
import Refresh from '../components/Refresh.vue'
import Page403 from '../components/403.vue'

import AllOrder from '../components/salesKeeper/AllOrder.vue'
import InsertOrder from '../components/salesKeeper/InsertOrder.vue'
import UpdateOrder from '../components/salesKeeper/UpdateOrder.vue'
import AllClient from '../components/salesKeeper/AllClient.vue'
import InsertClient from '../components/salesKeeper/InsertClient.vue'
import UpdateClient from '../components/salesKeeper/UpdateClient.vue'
import AllSalesman from '../components/salesKeeper/AllSalesman.vue'
import InsertSalesman from '../components/salesKeeper/InsertSalesman.vue'
import UpdateSalesman from '../components/salesKeeper/UpdateSalesman.vue'


import SalesSalesKeeper from '../components/salesKeeper/Sales.vue'
import SalesSalesman from '../components/salesman/Sales.vue'
import DispatchOrderSalesKeeper from '../components/salesKeeper/DispatchOrder.vue'
import DispatchOrderWarehouseKeeper from '../components/warehouseKeeper/DispatchOrder.vue'
import PurchaseOrder from '../components/warehouseKeeper/PurchaseOrder.vue'
import PurchaseOrderClose from '../components/warehouseKeeper/PurchaseOrderClose.vue'
import Order from '../components/salesman/Order.vue'




Vue.use(VueRouter)

const routes = [
  { path: '/', redirect: '/login' },
  { path: '/refresh' ,component: Refresh },
  { path: '/login', component: Login },
  { path: '/main', redirect: '/main/salesKeeper/allOrder',
    component: Main,
    children: [
      { path: '/main/salesKeeper/insertOrder', component: InsertOrder},
      { path: '/main/salesKeeper/updateOrder', component: UpdateOrder},
      { path: '/main/salesKeeper/allOrder', component: AllOrder},
      { path: '/main/salesKeeper/insertClient', component: InsertClient},
      { path: '/main/salesKeeper/updateClient', component: UpdateClient},
      { path: '/main/salesKeeper/allClient', component: AllClient},
      { path: '/main/salesKeeper/insertSalesman', component: InsertSalesman},
      { path: '/main/salesKeeper/updateSalesman', component: UpdateSalesman},
      { path: '/main/salesKeeper/allSalesman', component: AllSalesman},

      { path: '/main/403' , component: Page403 },

      { path: '/main/salesKeeper/sales', component: SalesSalesKeeper},
      { path: '/main/salesKeeper/dispatchOrder', component: DispatchOrderSalesKeeper},

      { path: '/main/salesman/order', component: Order},
      { path: '/main/salesman/sales', component: SalesSalesman},

      { path: '/main/warehouseKeeper/dispatchOrder', component: DispatchOrderWarehouseKeeper},
      { path: '/main/warehouseKeeper/purchaseOrder', component: PurchaseOrder},
      { path: '/main/warehouseKeeper/PurchaseOrderClose', component: PurchaseOrderClose}

    ]  
  }
]


const router = new VueRouter({
  mode: 'history',
  routes
})



export default router
