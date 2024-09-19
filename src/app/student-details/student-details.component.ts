import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { StudentService } from '../service/student/student.service';

@Component({
  selector: 'app-student-details',
  templateUrl: './student-details.component.html',
  styleUrl: './student-details.component.css'
})
export class StudentDetailsComponent implements OnInit {

  studentCode!: string;

  studentPayment!: any;

  constructor(private activatedRoute: ActivatedRoute, private studentService: StudentService) {}

  ngOnInit(): void {
    this.studentCode = this.activatedRoute.snapshot.params['code']; // je récupère le code ici que je stocke dans la variable 'studentCode'
    this.studentService.getStudentPayment(this.studentCode)
      .subscribe({
        next: data => {
          this.studentPayment = data;
        },
        error: err => {console.log(err)}
      })
  }




}
