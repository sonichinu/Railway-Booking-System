import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowBookingDetailsComponent } from './show-booking-details.component';

describe('ShowBookingDetailsComponent', () => {
  let component: ShowBookingDetailsComponent;
  let fixture: ComponentFixture<ShowBookingDetailsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowBookingDetailsComponent]
    });
    fixture = TestBed.createComponent(ShowBookingDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
