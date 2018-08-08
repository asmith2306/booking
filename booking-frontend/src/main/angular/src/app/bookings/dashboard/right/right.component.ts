import { Component, OnInit } from '@angular/core';
import {Dynamic} from "../dynamic-interface";

@Component({
  selector: 'app-right',
  templateUrl: './right.component.html',
  styleUrls: ['./right.component.scss']
})
export class RightComponent implements OnInit, Dynamic {

  constructor() { }

  ngOnInit() {
  }

}
