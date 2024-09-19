import { HttpClient } from '@angular/common/http';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { PaymentService } from '../service/payment/payment.service';

@Component({
  selector: 'app-payment',
  templateUrl: './payment.component.html',
  styleUrl: './payment.component.css'
})
export class PaymentComponent implements OnInit{

  public dataPayments: any; // je crée une liste vide
  public dataSource: any;
  public displayedColumns: string[] = ['id', 'amount', 'type', 'student'];

  // on va chercher dans la partie html un élément de type MatPaginator que j'affecte à la variable paginator!
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  @ViewChild(MatSort) sort!: MatSort;

  constructor(private paymentService: PaymentService) {}

  ngOnInit(): void {
    this.paymentService.getAllPayments()
      .subscribe({
        next: data => {
          this.dataPayments = data; // je stock les données du back dans la liste
          this.dataSource = new MatTableDataSource(this.dataPayments);
          this.dataSource.paginator = this.paginator; // j'associe le la vaiable au datasource
          this.dataSource.sort = this.sort;
        },
        error: err => {
          console.log(err);
        }
      });
  }

}
