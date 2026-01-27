import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Store001 } from './store001';

describe('Store001', () => {
  let component: Store001;
  let fixture: ComponentFixture<Store001>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Store001]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Store001);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
