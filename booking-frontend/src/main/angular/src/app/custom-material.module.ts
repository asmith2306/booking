import {NgModule} from "@angular/core";
import {
    MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
    MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
    MatTooltipModule, MatSnackBarModule, MatSelectModule, MatToolbarModule
} from '@angular/material';

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule, MatSnackBarModule, MatSelectModule, MatToolbarModule],
    exports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule,
        MatTooltipModule, MatSnackBarModule, MatSelectModule, MatToolbarModule]
})
export class CustomMaterialModule {}
