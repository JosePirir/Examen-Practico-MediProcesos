import { Component, OnInit } from '@angular/core';
import { UsuarioService } from '../services/usuario.service';
import { RolService } from '../services/rol.service';
import { Usuario } from '../models/usuario.model';
import { Rol } from '../models/rol.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usuarios',
  templateUrl: './usuarios.component.html',
  styleUrls: ['./usuarios.component.css']
})
export class UsuariosComponent implements OnInit {

  usuarios: Usuario[] = [];
  roles: Rol[] = [];
  usuarioActual: Usuario = this.usuarioVacio();
  idRolSeleccionado: number | null = null;
  editando: boolean = false;
  error: string = '';

  constructor(
    private usuarioService: UsuarioService,
    private rolService: RolService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarUsuarios();
    this.cargarRoles();
  }

  usuarioVacio(): Usuario {
    return { nombre: '', correo: '', usuario: '', password: '' };
  }

  cargarUsuarios(): void {
    this.usuarioService.listar().subscribe({
      next: (data) => this.usuarios = data,
      error: () => this.error = 'Error al cargar usuarios'
    });
  }

  cargarRoles(): void {
    this.rolService.listar().subscribe({
      next: (data) => this.roles = data,
      error: () => this.error = 'Error al cargar roles'
    });
  }

  onSubmit(): void {
    this.error = '';

    if (!this.idRolSeleccionado) {
      this.error = 'Debe seleccionar un rol';
      return;
    }

    const payload: Usuario = {
      ...this.usuarioActual,
      rol: { idRol: this.idRolSeleccionado }
    };

    if (this.editando && this.usuarioActual.idUsuario) {
      if (!payload.password) {
        delete payload.password;
      }
      this.usuarioService.actualizar(this.usuarioActual.idUsuario, payload).subscribe({
        next: () => {
          this.cargarUsuarios();
          this.cancelar();
        },
        error: () => this.error = 'Error al actualizar el usuario'
      });
    } else {
      this.usuarioService.crear(payload).subscribe({
        next: () => {
          this.cargarUsuarios();
          this.cancelar();
        },
        error: (err) => {
          this.error = err.status === 400
            ? 'Datos inválidos o rol no existe'
            : 'Error al crear el usuario';
        }
      });
    }
  }

  editar(usuario: Usuario): void {
    this.usuarioActual = { ...usuario, password: '' };
    this.idRolSeleccionado = usuario.idRol ?? null;
    this.editando = true;
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (!confirm('¿Seguro que deseas eliminar este usuario?')) return;

    this.usuarioService.eliminar(id).subscribe({
      next: () => this.cargarUsuarios(),
      error: () => this.error = 'Error al eliminar el usuario'
    });
  }

  cancelar(): void {
    this.usuarioActual = this.usuarioVacio();
    this.idRolSeleccionado = null;
    this.editando = false;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}