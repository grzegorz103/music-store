import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DiscEditComponent } from './disc-edit.component';

describe('DiscEditComponent', () => {
  let component: DiscEditComponent;
  let fixture: ComponentFixture<DiscEditComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DiscEditComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DiscEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
