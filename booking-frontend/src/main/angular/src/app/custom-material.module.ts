import {NgModule} from "@angular/core";
import {
    MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
    MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
    MatTooltipModule, MatSnackBarModule, MatSelectModule
} from '@angular/material';

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule, MatSnackBarModule, MatSelectModule],
    exports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule, MatSnackBarModule, MatSelectModule]
})
export class CustomMaterialModule {}
