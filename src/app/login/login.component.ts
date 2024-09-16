import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  /* Ici on utilise le ReactFormsModule
    Le principe des formulaires réactifs est de créer un objet qui représente le formulaire. 
    Cet objet est ensuite lié à un <form> dans le template.
  */

  // on l'objet qui va representer notre formulaire 
  public loginForm!: FormGroup;
  
  constructor(private fb: FormBuilder) {}
  
  ngOnInit(): void {
    this.loginForm = this.fb.group(
      {
        username: this.fb.control(''),
        password: this.fb.control('')
      }
    )
  }

  login(): void {
    // récupère les valeur du formulaire
    let username = this.loginForm.value.username;
    let password = this.loginForm.value.password;
    console.log(username, password);
  }

}
