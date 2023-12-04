import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ProductsComponent} from "./products/products.component";
import {CustomersComponent} from "./customers/customers.component";
import {OrdersComponent} from "./orders/orders.component";
import {getSupportedNodeTargets} from "@angular-devkit/build-angular/src/tools/esbuild/utils";
import {OrderDetatilsComponent} from "./order-detatils/order-detatils.component";

const routes: Routes = [
  {
    path : "products",component : ProductsComponent
  },
  {
    path : "customers",component : CustomersComponent
  },
  {
    path : "orders/:customerId",component : OrdersComponent
  },
  {
    path : "order-detatils/:orderId",component : OrderDetatilsComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
