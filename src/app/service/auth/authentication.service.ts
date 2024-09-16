import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  public username: any;
  public roles: any;
  public authenticated: boolean = false;
  public users = {
    "admin": ["STUDENT", "ADMIN"],
    "user1": ["STUDENT"]
  }

  constructor(private router: Router) { }

  public login(username: string, password: string): boolean {
    if (username == "admin" && password == "1234") {
      this.username = username;
      this.roles = ['ADMIN'];
      this.authenticated = true;
      return true;
    } else {
      return false;
    }
  }

  public logout(): void {
    this.authenticated = false;
    this.username = undefined;
    this.roles = undefined;
    this.router.navigateByUrl("/login");
  }
}
