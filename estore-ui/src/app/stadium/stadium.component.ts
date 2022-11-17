import { Component, OnInit } from '@angular/core';
import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "../app-routing.module";
import { AppComponent } from "../app.component";
import { Router } from '@angular/router';

@Component({
  selector: 'app-stadium',
  templateUrl: './stadium.component.html',
  styleUrls: ['./stadium.component.css']
})

export class StadiumComponent implements OnInit {

  constructor(private router:Router){}

  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }
  ngOnInit(): void {
  }

}
