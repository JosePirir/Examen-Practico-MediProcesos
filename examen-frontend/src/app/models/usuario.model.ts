export interface Usuario {
  idUsuario?: number;
  nombre: string;
  correo: string;
  usuario: string;
  password?: string;
  idRol?: number;
  nombreRol?: string;
  rol?: { idRol: number };
}