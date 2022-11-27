import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FacilityBackComponent } from './facility-back.component';

describe('FacilityBackComponent', () => {
  let component: FacilityBackComponent;
  let fixture: ComponentFixture<FacilityBackComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FacilityBackComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FacilityBackComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
