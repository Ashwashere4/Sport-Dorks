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

  constructor(
    private userService: UserService,
    private messageService: MessageService) { }

  json = require('../users.json')

  ngOnInit(): void {
    this.getUsers();
  }

  users: User[] = [];

  getUsers(): void {
      this.userService.getUsers().subscribe(users => this.users = users);
  }

  add(userName: string, pass: string, admin: boolean, towner: boolean): void {
    userName = userName.trim();
    if (!userName) { return; }  
    if (!pass) { return; }
    const newUser = this.userService.createUser(userName, pass, admin, towner);;
    this.userService.addUser(newUser)
      .subscribe(newUser => {
        this.users.push(newUser);
      });
  }
}
