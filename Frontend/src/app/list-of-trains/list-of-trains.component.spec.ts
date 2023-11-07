import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfTrainsComponent } from './list-of-trains.component';

describe('ListOfTrainsComponent', () => {
  let component: ListOfTrainsComponent;
  let fixture: ComponentFixture<ListOfTrainsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListOfTrainsComponent]
    });
    fixture = TestBed.createComponent(ListOfTrainsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
