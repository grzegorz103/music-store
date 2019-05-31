import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscPageComponent } from './disc-page.component';

describe('DiscPageComponent', () => {
  let component: DiscPageComponent;
  let fixture: ComponentFixture<DiscPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
