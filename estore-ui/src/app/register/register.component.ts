import { Component, OnInit } from '@angular/core';
import { MessageService } from '../message.service';
import { User } from '../user';
import { UserService } from '../user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  title: any;
  json = require('../users.json')
  users: User[] = [];

  constructor(
    private userService: UserService
  ) {}

  ngOnInit(): void {
    this.getUsers();
  }

  username: String = '';

  public saveData() {
    let name: string = this.username as string;
    localStorage.setItem("user", name);
    // console.log(localStorage.getItem("key"))
    console.log(this.username)
  }

  register(name: string, pass: string){
    name = name.trim();
    pass = pass.trim();
    if (!name) { window.alert('need username') }
    if (!pass) { window.alert('need password') }
    const newUser = this.userService.createUser(name, pass, false, false);
    this.userService.addUser(newUser).subscribe();
    //this.userService.addUser(newUser).subscribe(newUser => {this.users.push(newUser);});
    this.saveData();
  }

  getUsers(){
    this.userService.getUsers().subscribe(users => this.users = users);
  }
}
