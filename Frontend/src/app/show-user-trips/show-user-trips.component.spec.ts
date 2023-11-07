import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowUserTripsComponent } from './show-user-trips.component';

describe('ShowUserTripsComponent', () => {
  let component: ShowUserTripsComponent;
  let fixture: ComponentFixture<ShowUserTripsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowUserTripsComponent]
    });
    fixture = TestBed.createComponent(ShowUserTripsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
