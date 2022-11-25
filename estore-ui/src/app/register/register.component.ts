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

  ngOnInit(): void {
    this.getUsers();
  }

  users: User[] = [];

  getUsers(): void {
      this.userService.getUsers().subscribe(users => this.users = users);
  }

  add(username: string, password: string, admin: boolean, owner: boolean): void {
    username = username.trim();
    if (!username) { return; }  
    const newUser = this.userService.createUser(username, password, admin, owner);;
    this.userService.addUser(newUser)
      .subscribe(newUser => {
        this.users.push(newUser);
      });
  }
}
