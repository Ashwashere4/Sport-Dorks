import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  username = localStorage.getItem("key")

  public logout() {
    localStorage.removeItem("key")
    this.username = " ";
  }

}