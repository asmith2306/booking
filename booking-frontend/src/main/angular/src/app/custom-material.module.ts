import {NgModule} from "@angular/core";
import {
    MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
    MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
    MatTooltipModule
} from '@angular/material';

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule],
    exports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule],
})
export class CustomMaterialModule {}
