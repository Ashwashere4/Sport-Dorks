import { Component, OnInit } from '@angular/core';
import { RouterEvent } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  public kalen = "kalen123";
  public password = "pass";

  constructor() {
   }

  username: String = '';

  public saveData() {
    let name: string = this.username as string;
    localStorage.setItem("key", name);
    // console.log(localStorage.getItem("key"))
    console.log(this.username)

  }

  login(name: string, pass: string): boolean{
    if(name == this.kalen && pass == this.password){
      this.saveData;
      return true;
    }
    else{
      window.alert('username or password does not compute');
      return false;
    }
  }

  ngOnInit(): void {
    
  }

}
