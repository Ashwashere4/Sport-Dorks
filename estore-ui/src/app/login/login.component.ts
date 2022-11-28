import { Component, OnInit } from '@angular/core';
import { RouterEvent } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor() {
   }

  username: String = '';

  public saveData() {
    let name: string = this.username as string;
    localStorage.setItem("key", name);
    // console.log(localStorage.getItem("key"))
    console.log(this.username)

  }

  login(username: string, password:string):void{

  }

  ngOnInit(): void {
    
  }

}
