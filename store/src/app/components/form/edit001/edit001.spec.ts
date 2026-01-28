import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Edit001 } from './edit001';

describe('Edit001', () => {
  let component: Edit001;
  let fixture: ComponentFixture<Edit001>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Edit001]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Edit001);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
