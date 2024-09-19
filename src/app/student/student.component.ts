import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource } from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {MatSort, Sort, MatSortModule} from '@angular/material/sort';
import { Router } from '@angular/router';
import { StudentService } from '../service/student/student.service';
import { Student } from '../model/student.model';

@Component({
  selector: 'app-student',
  templateUrl: './student.component.html',
  styleUrl: './student.component.css'
})
export class StudentComponent implements OnInit{

  public students: any;
  public dataSource: any;
  public displayedColumns: string[] = ['id', 'firstName', 'lastName', 'code', 'payment'];

  public keyword: any;

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  constructor(private router: Router, private studentService: StudentService) {}
  

  ngOnInit(): void {
    this.studentService.getAllStudents()
      .subscribe({ // je souscris au flux de données que je stocke dans la variable 'data'
        next: data => {
          this.students = data; // je stocke les données de 'data' dans 'students
          // data binding
          this.dataSource = new MatTableDataSource(this.students);
          this.dataSource.paginator = this.paginator;
          this.dataSource.sort = this.sort;
        },
        error: err => {
          console.log(err);
        }
      })
  }


  filterStudent(event: Event): void {
    let value: string = (event.target as HTMLInputElement).value;
    this.dataSource.filter = value;
  }


  public getPayment(student: Student): void {
    this.router.navigateByUrl(`/admin/student-details/${student.code}`); // j'envoie le code à cette route
  }

  

}
