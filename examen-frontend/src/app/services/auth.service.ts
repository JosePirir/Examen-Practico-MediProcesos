import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginRequest, LoginResponse } from '../models/login.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private baseUrl = 'http://localhost:8080/examen-backend/api/login';

  constructor(private http: HttpClient) { }

  login(credenciales: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(this.baseUrl, credenciales).pipe(
      tap(respuesta => {
        if (respuesta.success) {
          sessionStorage.setItem('usuario', respuesta.usuario);
          sessionStorage.setItem('rol', respuesta.rol);
        }
      })
    );
  }

  logout(): void {
    sessionStorage.removeItem('usuario');
    sessionStorage.removeItem('rol');
  }

  estaLogueado(): boolean {
    return sessionStorage.getItem('usuario') !== null;
  }

  getUsuarioActual(): string | null {
    return sessionStorage.getItem('usuario');
  }

  getRolActual(): string | null {
    return sessionStorage.getItem('rol');
  }
}