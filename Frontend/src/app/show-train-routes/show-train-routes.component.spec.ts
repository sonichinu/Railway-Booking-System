import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowTrainRoutesComponent } from './show-train-routes.component';

describe('ShowTrainRoutesComponent', () => {
  let component: ShowTrainRoutesComponent;
  let fixture: ComponentFixture<ShowTrainRoutesComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ShowTrainRoutesComponent]
    });
    fixture = TestBed.createComponent(ShowTrainRoutesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
