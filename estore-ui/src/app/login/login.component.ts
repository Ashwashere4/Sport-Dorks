import { Component, OnInit } from '@angular/core';
import { RouterEvent } from '@angular/router';
import { Observable } from 'rxjs';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  title: any;
  json = require('../users.json')
  users: User[] = [];
  passwords: string[] = ["p","a"]

  constructor(
    private userService: UserService
  ) {}
  ngOnInit(): void {
    this.getUsers();
  }

  username: String = '';

  getUsers(){
    this.userService.getUsers().subscribe(users => this.users = users);
  }

  public saveData() {
    let name: string = this.username as string;
    localStorage.setItem("user", name);
    console.log(this.username)
  }

  login(username: string, password:string):void{
    if (username && this.passwords.includes(password)){
    this.saveData();
    }
    else{
      window.alert('incorrect username or password')
    }
  }
}
