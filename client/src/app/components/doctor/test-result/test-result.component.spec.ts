import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TestResultComponent } from './test-result.component';

describe('TestResultComponent', () => {
  let component: TestResultComponent;
  let fixture: ComponentFixture<TestResultComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [TestResultComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TestResultComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
