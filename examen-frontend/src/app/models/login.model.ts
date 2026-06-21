export interface LoginRequest {
  usuario: string;
  password: string;
}

export interface LoginResponse {
  success: boolean;
  usuario: string;
  rol: string;
  mensaje: string;
}

export interface LoginRequest {
  usuario: string;
  password: string;
}

export interface LoginResponse {
  success: boolean;
  usuario: string;
  rol: string;
  mensaje: string;
  token: string;
}