import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Payment } from '../../model/payment.model';
import { environment } from '../../../environments/environment.development';

@Injectable({
  providedIn: 'root'
})
export class PaymentService {

  constructor(private http: HttpClient) { }

  public getAllPayments(): Observable<Payment[]> {
    
    return this.http.get<Payment[]>(`${environment.backendHost}/payments`);
  }
}
