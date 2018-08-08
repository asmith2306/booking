import {NgModule} from "@angular/core";
import {
    MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
    MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
    MatTooltipModule, MatSnackBarModule, MatSelectModule, MatToolbarModule,
    MatButtonToggleModule
} from '@angular/material';

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule, MatSnackBarModule, MatSelectModule, MatToolbarModule,
        MatButtonToggleModule],
    exports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule, MatSnackBarModule, MatSelectModule, MatToolbarModule,
        MatButtonToggleModule]
})
export class CustomMaterialModule {}
