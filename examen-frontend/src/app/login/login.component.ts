import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { LoginRequest } from '../models/login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {

  credenciales: LoginRequest = {
    usuario: '',
    password: ''
  };

  error: string = '';
  cargando: boolean = false;

  constructor(private authService: AuthService, private router: Router) { }

  onSubmit(): void {
    this.error = '';
    this.cargando = true;

    this.authService.login(this.credenciales).subscribe({
      next: (respuesta) => {
        this.cargando = false;
        if (respuesta.success) {
          if (respuesta.rol === 'Administrador') {
            this.router.navigate(['/usuarios']);
          } else {
            this.router.navigate(['/home']);
          }
        } else {
          this.error = respuesta.mensaje;
        }
      },
      error: (err) => {
        this.cargando = false;
        if (err.status === 401) {
          this.error = 'Usuario o contraseña incorrectos';
        } else {
          this.error = 'Error al conectar con el servidor';
        }
      }
    });
  }
}