import { Directive, ElementRef, Input } from '@angular/core';

@Directive({
  selector: '[appClickableIndicator]'
})
export class ClickableIndicatorDirective {

  constructor(el: ElementRef) {
      let element: HTMLElement = el.nativeElement;
      element.style.cursor = "pointer";
  }
    
}
