// ⚠️ IMPORTANTE: O backend deve permitir CORS para que as requisições funcionem
// Configure no Spring Boot: @CrossOrigin(origins = "*") nos controllers

export interface Atividade {
  id?: number;
  nome: string;
  dificuldade: string;
}

export interface Usuario {
  id?: number;
  nome: string;
  imgUrl: string;
  email: string;
  cargo: string;
  idade: number;
  atividades?: { id: number } | null;
}

export const BASE_URL = "http://localhost:8080";
