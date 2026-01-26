import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { Create001 } from "./components/form/create001/create001";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, Create001],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('store');
}
