import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConfirDelete } from './confir-delete';

describe('ConfirDelete', () => {
  let component: ConfirDelete;
  let fixture: ComponentFixture<ConfirDelete>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConfirDelete]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ConfirDelete);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
