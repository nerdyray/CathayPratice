import { bootstrapApplication } from '@angular/platform-browser';
import { App } from './app/app';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';


bootstrapApplication(App, {
  providers: [provideRouter(routes), // <--- 必須加上這一行！
  provideAnimationsAsync()
  ]
}).catch(err => console.error(err));
