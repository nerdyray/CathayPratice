import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Create001 } from "./components/form/create001/create001";
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Create001, FormsModule],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('CifCustomer');
}
