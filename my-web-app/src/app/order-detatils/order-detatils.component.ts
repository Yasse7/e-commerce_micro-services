import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-order-detatils',
  templateUrl: './order-detatils.component.html',
  styleUrl: './order-detatils.component.css'
})
export class OrderDetatilsComponent implements OnInit{
  orderDetails : any;
  orderId!: number ;
  constructor(private http:HttpClient , private router : Router,private route : ActivatedRoute) {
    this.orderId=route.snapshot.params['orderId'];
  }
  ngOnInit(): void {
    this.http.get("http://localhost:9999/order-service/fullOrder/"+this.orderId).subscribe({
      next :(data)=>{
        this.orderDetails = data;
      },
      error :(err)=>{}
    });
  }
  getOrdersDetails(o: any) {

    this.router.navigateByUrl("/order-details/"+o.id)
  }


}
