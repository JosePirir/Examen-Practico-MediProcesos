import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeNoAdminComponent } from './home-no-admin.component';

describe('HomeNoAdminComponent', () => {
  let component: HomeNoAdminComponent;
  let fixture: ComponentFixture<HomeNoAdminComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [HomeNoAdminComponent]
    });
    fixture = TestBed.createComponent(HomeNoAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
