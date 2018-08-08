import { Component, OnInit } from '@angular/core';
import {DynamicComponent} from "../dynamic-interface";

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.scss']
})
export class CenterComponent implements OnInit, DynamicComponent {

  constructor() { }

  ngOnInit() {
  }

}
