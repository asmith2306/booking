import {Component, OnInit} from '@angular/core';
import {Dynamic} from "../dynamic-interface";

@Component({
    selector: 'app-left',
    templateUrl: './left.component.html',
    styleUrls: ['./left.component.scss']
})
export class LeftComponent implements OnInit, Dynamic {

    constructor() {}

    ngOnInit() {
    }

}
