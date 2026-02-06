import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Cif001 } from './cif001';

describe('Cif001', () => {
  let component: Cif001;
  let fixture: ComponentFixture<Cif001>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Cif001]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Cif001);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
