import {NgModule} from "@angular/core";
import {
    MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
    MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule
} from '@angular/material';

@NgModule({
    imports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule],
    exports: [MatButtonModule, MatCheckboxModule, MatTableModule, MatIconModule,
        MatDatepickerModule, MatNativeDateModule, MatCardModule, MatInputModule],
})
export class CustomMaterialModule {}
