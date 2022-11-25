import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Facilities } from '../facilities';
import { MessageService } from '../message.service';

import { FACILITIES } from '../mock_facilities';
import { FacilityService } from './facilities.service';

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css']
})

export class facilitiesComponent implements OnInit {

  facilities = FACILITIES;
  selectedFacility?: Facilities;

  json = require('../flist.json')
  Facilities: Facilities[] = [];


  constructor(
    private router:Router,
    private facilityService : FacilityService,
    private messageSerivce: MessageService
    ){}

  goToPage(pageName:string):void{
    this.router.navigate([`${pageName}`]);
  }
  

  ngOnInit(): void {
  }

  getFacility(): void {
    this.facilityService.getFacilities().subscribe(facilities => this.facilities = facilities);
  }

  deleteFacility(facility: Facilities): void{
    this.Facilities = this.Facilities.filter(i => i !== facility);
    this.facilityService.deleteFacility(facility.facility_id).subscribe()
  }

  onSelect(facility: Facilities): void {
    this.selectedFacility = facility;
  }
}