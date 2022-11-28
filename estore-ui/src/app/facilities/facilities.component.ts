import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facilities } from '../facilities';
import { MessageService } from '../message.service';

import { FacilityService } from '../facilities.service';

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css']
})

export class facilitiesComponent implements OnInit {

  selectedFacility?: Facilities;

  json = require('../flist.json')
  Facilities: Facilities[] = [];


  constructor(
    private router:Router,
    private FacilityService : FacilityService,
    private messageSerivce: MessageService
    ){}

  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }

  ngOnInit(): void {
    this.getFacility();
  }

  getFacility(): void {
    this.FacilityService.getFacilities().subscribe(facilities => this.Facilities = facilities);
  }

  deleteFacility(facility: Facilities): void{
    this.Facilities = this.Facilities.filter(i => i !== facility);
    this.FacilityService.deleteFacility(facility.facility_id).subscribe()
  }

  onSelect(facility: Facilities): void {
    this.selectedFacility = facility;
  }

  searchFacilities(): void {

    let Facility = prompt("Search Facility");
    
    
    if (Facility != null){
      
    this.FacilityService.searchFacilities(Facility).subscribe(facilities => this.Facilities = facilities)
  
    }
  
    else {
      this.FacilityService.getFacilities().subscribe(facilities => this.Facilities = facilities)
    }
  
    }
}