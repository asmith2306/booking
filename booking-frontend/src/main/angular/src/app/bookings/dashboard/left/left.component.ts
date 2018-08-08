import {Component, OnInit} from '@angular/core';
import {DynamicComponent} from "../dynamic-interface";

@Component({
    selector: 'app-left',
    templateUrl: './left.component.html',
    styleUrls: ['./left.component.scss']
})
export class LeftComponent implements OnInit, DynamicComponent {

    constructor() {}

    ngOnInit() {
    }

}
