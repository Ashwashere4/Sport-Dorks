import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  username = localStorage.getItem("user")

  public logout() {
    localStorage.removeItem("user")
    this.username = " ";
  }

}