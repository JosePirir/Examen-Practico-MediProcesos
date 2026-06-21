import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-home-no-admin',
  templateUrl: './home-no-admin.component.html',
  styleUrls: ['./home-no-admin.component.css']
})
export class HomeNoAdminComponent {

  constructor(private authService: AuthService, private router: Router) { }

  get nombreUsuario(): string | null {
    return this.authService.getUsuarioActual();
  }

  get rolUsuario(): string | null {
    return this.authService.getRolActual();
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}