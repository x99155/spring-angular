import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../../environments/environment.development';
import { Observable } from 'rxjs';
import { Payment } from '../../model/payment.model';
import { Student } from '../../model/student.model';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  public getAllStudents(): Observable<Array<Student>> {
    
    return this.http.get<Array<Student>>(`${environment.backendHost}/students`);
  }

  public getStudentPayment(code: string): Observable<Payment> {
    
    return this.http.get<Payment>(`${environment.backendHost}/students/${code}/payment`);
  }
}
