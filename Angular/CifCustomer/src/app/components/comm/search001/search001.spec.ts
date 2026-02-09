import { ComponentFixture, TestBed } from '@angular/core/testing';

import { Search001 } from './search001';

describe('Search001', () => {
  let component: Search001;
  let fixture: ComponentFixture<Search001>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [Search001]
    })
    .compileComponents();

    fixture = TestBed.createComponent(Search001);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
