import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserProductCardComponent } from './user-product-card.component';

describe('UserProductCardComponent', () => {
  let component: UserProductCardComponent;
  let fixture: ComponentFixture<UserProductCardComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ UserProductCardComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserProductCardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
