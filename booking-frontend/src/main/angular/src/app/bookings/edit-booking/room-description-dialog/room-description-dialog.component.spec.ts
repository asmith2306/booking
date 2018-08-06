import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RoomDescriptionDialogComponent } from './room-description-dialog.component';

describe('RoomDescriptionDialogComponent', () => {
  let component: RoomDescriptionDialogComponent;
  let fixture: ComponentFixture<RoomDescriptionDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RoomDescriptionDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RoomDescriptionDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
