import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

bootstrapApplication(App, {
  providers: [
    provideAnimationsAsync()
  ]
}).catch(err => console.error(err));
