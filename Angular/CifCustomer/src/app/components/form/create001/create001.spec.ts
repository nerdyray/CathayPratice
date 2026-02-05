import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Create001 } from './create001';

describe('Create001', () => {
  let component: Create001;
  let fixture: ComponentFixture<Create001>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Create001]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Create001);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
