import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Create001 } from "./components/form/create001/create001";
import { FormsModule } from '@angular/forms';
import { Cif001 } from "./components/list/cif001/cif001";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Create001, FormsModule, Cif001],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CifCustomer');
}
