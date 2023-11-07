import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOfStationsComponent } from './list-of-stations.component';

describe('ListOfStationsComponent', () => {
  let component: ListOfStationsComponent;
  let fixture: ComponentFixture<ListOfStationsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ListOfStationsComponent]
    });
    fixture = TestBed.createComponent(ListOfStationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
