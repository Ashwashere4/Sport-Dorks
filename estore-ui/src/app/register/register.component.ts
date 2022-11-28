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

  constructor() {}

  ngOnInit(): void {
  }

  username: String = '';

  public saveData() {
    let name: string = this.username as string;
    localStorage.setItem("key", name);
    // console.log(localStorage.getItem("key"))
    console.log(this.username)
  }

  users: User[] = [];

}
