import {Component, OnInit, Inject} from '@angular/core';
import {RoomsService} from '../../../http/rest/rooms.service';
import {RoomType} from '../../../models/RoomType';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';

@Component({
    selector: 'app-room-description-dialog',
    templateUrl: './room-description-dialog.component.html',
    styleUrls: ['./room-description-dialog.component.scss']
})
export class RoomDescriptionDialogComponent implements OnInit {

    allRoomTypes: Array<RoomType>;

    constructor(public dialogRef: MatDialogRef<RoomDescriptionDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: Array<RoomType>) {}

    ngOnInit() {
        
        this.allRoomTypes = this.data["rooms"];
        console.log(this.allRoomTypes)
    }

}
