import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InventoryFrontComponent } from './inventory-front.component';

describe('InventoryFrontComponent', () => {
  let component: InventoryFrontComponent;
  let fixture: ComponentFixture<InventoryFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InventoryFrontComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InventoryFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
