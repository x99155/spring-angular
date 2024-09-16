import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, Sort, MatSortModule} from '@angular/material/sort';
import { Router } from '@angular/router';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent implements OnInit, AfterViewInit{

  public students: any;
  public dataSource: any;
  public displayedColumns: string[] = ['id', 'firstName', 'lastName', 'payment'];

  public keyword: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private router: Router) {}
  

  ngOnInit(): void {
    this.students = [];

    for (let i: number = 1; i < 100; i++) {
      this.students.push(
        {
          id: i,
          firstName: Math.random().toString(20),
          lastName: Math.random().toString(20)
        }
      );
      
    }

    // MatTableDataSource est une classe fournie par Angular Material pour faciliter la gestion et l'affichage des données dans les tables (mat-table)
    this.dataSource = new MatTableDataSource(this.students);
  }


  ngAfterViewInit(): void {
    
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  filterStudent(event: Event): void {
    let value: string = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value;
  }

  getPayment(student: any): void {
    this.router.navigateByUrl('/admin/payment');
  }

  

}
