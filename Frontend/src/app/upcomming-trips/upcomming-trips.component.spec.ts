import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpcommingTripsComponent } from './upcomming-trips.component';

describe('UpcommingTripsComponent', () => {
  let component: UpcommingTripsComponent;
  let fixture: ComponentFixture<UpcommingTripsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UpcommingTripsComponent]
    });
    fixture = TestBed.createComponent(UpcommingTripsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
