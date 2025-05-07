import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewUserRecordComponent } from './view-user-record.component';

describe('ViewUserRecordComponent', () => {
  let component: ViewUserRecordComponent;
  let fixture: ComponentFixture<ViewUserRecordComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ViewUserRecordComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewUserRecordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
