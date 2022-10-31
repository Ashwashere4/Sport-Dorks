import { ComponentFixture, TestBed } from '@angular/core/testing';

import { facilitiesComponent } from './facilities.component';

describe('FacilitiesComponent', () => {
  let component: facilitiesComponent;
  let fixture: ComponentFixture<facilitiesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ facilitiesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(facilitiesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});