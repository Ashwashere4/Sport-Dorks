import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";

import { AppRoutingModule } from "../app-routing.module";
import { AppComponent } from "../app.component";
import { StadiumComponent } from "../stadium/stadium.component";


@NgModule({
    declarations: [
        AppComponent,
        StadiumComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule
    ],
    providers: [],
    bootstrap: [AppComponent]
    })
    export class AppModule{}
