import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facilities } from '../facilities';

import { FACILITIES } from '../mock_facilities';

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css']
})

export class facilitiesComponent implements OnInit {

  facilities = FACILITIES;
  selectedFacility?: Facilities;

  constructor(private router:Router){}

  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
  }

  onSelect(facility: Facilities): void {
    this.selectedFacility = facility;
  }
}