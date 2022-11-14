import { Component, OnInit } from '@angular/core';

import { FACILITIES } from '../mock_facilities';

@Component({
  selector: 'app-facilities',
  templateUrl: './facilities.component.html',
  styleUrls: ['./facilities.component.css']
})
export class facilitiesComponent implements OnInit {

  facilities = FACILITIES;


    ngOnInit(): void {

      throw new Error('Method not implemented.');
    }


  

}