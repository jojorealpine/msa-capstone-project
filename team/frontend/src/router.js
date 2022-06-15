
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import DeliveryManager from "./components/listers/DeliveryCards"

import OrderManager from "./components/listers/OrderCards"

import PaymentManager from "./components/listers/PaymentCards"


import OrderDetails from "./components/OrderDetails"
export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/deliveries',
                name: 'DeliveryManager',
                component: DeliveryManager
            },

            {
                path: '/orders',
                name: 'OrderManager',
                component: OrderManager
            },

            {
                path: '/payments',
                name: 'PaymentManager',
                component: PaymentManager
            },


            {
                path: '/orderDetails',
                name: 'OrderDetails',
                component: OrderDetails
            },


    ]
})
