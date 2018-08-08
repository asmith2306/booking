import { Component, OnInit } from '@angular/core';
import {Dynamic} from "../dynamic-interface";

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.scss']
})
export class CenterComponent implements OnInit, Dynamic {

  constructor() { }

  ngOnInit() {
  }

}
