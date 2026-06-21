import { Component, OnInit } from '@angular/core';
import { RolService } from '../services/rol.service';
import { Rol } from '../models/rol.model';
import { AuthService } from '../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-roles',
  templateUrl: './roles.component.html',
  styleUrls: ['./roles.component.css']
})
export class RolesComponent implements OnInit {

  roles: Rol[] = [];
  rolActual: Rol = this.rolVacio();
  editando: boolean = false;
  error: string = '';

  constructor(
    private rolService: RolService,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.cargarRoles();
  }

  rolVacio(): Rol {
    return { nombre: '', descripcion: '' };
  }

  cargarRoles(): void {
    this.rolService.listar().subscribe({
      next: (data) => this.roles = data,
      error: () => this.error = 'Error al cargar roles'
    });
  }

  onSubmit(): void {
    this.error = '';
    if (this.editando && this.rolActual.idRol) {
      this.rolService.actualizar(this.rolActual.idRol, this.rolActual).subscribe({
        next: () => {
          this.cargarRoles();
          this.cancelar();
        },
        error: () => this.error = 'Error al actualizar el rol'
      });
    } else {
      this.rolService.crear(this.rolActual).subscribe({
        next: () => {
          this.cargarRoles();
          this.cancelar();
        },
        error: () => this.error = 'Error al crear el rol'
      });
    }
  }

  editar(rol: Rol): void {
    this.rolActual = { ...rol };
    this.editando = true;
  }

  eliminar(id: number | undefined): void {
    if (!id) return;
    if (!confirm('¿Seguro que deseas eliminar este rol?')) return;

    this.rolService.eliminar(id).subscribe({
      next: () => this.cargarRoles(),
      error: () => this.error = 'No se puede eliminar: el rol tiene usuarios asignados'
    });
  }

  cancelar(): void {
    this.rolActual = this.rolVacio();
    this.editando = false;
  }

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}