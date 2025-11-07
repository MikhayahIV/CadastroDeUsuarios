import { BASE_URL, Usuario, Atividade } from "@/types/api";

// ========== SERVIÇOS DE ATIVIDADES ==========

export const atividadesApi = {
  // Listar todas as atividades
  listar: async (): Promise<Atividade[]> => {
    console.log("🔄 Fazendo requisição para:", `${BASE_URL}/atividades/listar`);
    const response = await fetch(`${BASE_URL}/atividades/listar`);
    console.log("📡 Status da resposta:", response.status, response.statusText);
    if (!response.ok) throw new Error("Erro ao listar atividades");
    const data = await response.json();
    console.log("📦 Dados recebidos (atividades):", data);
    return data;
  },

  // Buscar atividade por ID
  buscarPorId: async (id: number): Promise<Atividade> => {
    const response = await fetch(`${BASE_URL}/atividades/listar/${id}`);
    if (!response.ok) throw new Error("Erro ao buscar atividade");
    return response.json();
  },

  // Criar nova atividade
  criar: async (atividade: Atividade): Promise<Atividade> => {
    console.log("📤 POST /atividades/adicionar:", atividade);
    const response = await fetch(`${BASE_URL}/atividades/adicionar`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(atividade),
    });
    console.log("📥 Resposta POST:", response.status, response.statusText);
    if (!response.ok) {
      const errorText = await response.text();
      console.error("❌ Erro na resposta:", errorText);
      throw new Error("Erro ao criar atividade");
    }

    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
      const data = await response.json();
      console.log("✅ Atividade criada:", data);
      return data;
    } else {
      const text = await response.text();
      console.log("✅ Resposta texto:", text);
      // Retorna objeto com os dados enviados já que o backend não retorna JSON
      return atividade;
    }
  },

  // Atualizar atividade existente
  atualizar: async (id: number, atividade: Atividade): Promise<Atividade> => {
    const response = await fetch(`${BASE_URL}/atividades/atualizar/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(atividade),
    });
    if (!response.ok) throw new Error("Erro ao atualizar atividade");
    return response.json();
  },

  // Deletar atividade
  deletar: async (id: number): Promise<void> => {
    const response = await fetch(`${BASE_URL}/atividades/deletar/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) throw new Error("Erro ao deletar atividade");
  },
};

// ========== SERVIÇOS DE USUÁRIOS ==========

export const usuariosApi = {
  // Listar todos os usuários
  listar: async (): Promise<Usuario[]> => {
    console.log("🔄 Fazendo requisição para:", `${BASE_URL}/usuarios/listar`);
    const response = await fetch(`${BASE_URL}/usuarios/listar`);
    console.log("📡 Status da resposta:", response.status, response.statusText);
    if (!response.ok) throw new Error("Erro ao listar usuários");
    const data = await response.json();
    console.log("📦 Dados recebidos (usuários):", data);
    return data;
  },

  // Buscar usuário por ID
  buscarPorId: async (id: number): Promise<Usuario> => {
    const response = await fetch(`${BASE_URL}/usuarios/listar/${id}`);
    if (!response.ok) throw new Error("Erro ao buscar usuário");
    return response.json();
  },

  // Criar novo usuário
  criar: async (usuario: Usuario): Promise<Usuario> => {
    console.log("📤 POST /usuarios/adicionar:", usuario);
    const response = await fetch(`${BASE_URL}/usuarios/adicionar`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(usuario),
    });
    console.log("📥 Resposta POST:", response.status, response.statusText);
    if (!response.ok) {
      const errorText = await response.text();
      console.error("❌ Erro na resposta:", errorText);
      throw new Error("Erro ao criar usuário");
    }

    const contentType = response.headers.get("content-type");
    if (contentType && contentType.includes("application/json")) {
      const data = await response.json();
      console.log("✅ Usuário criado:", data);
      return data;
    } else {
      const text = await response.text();
      console.log("✅ Resposta texto:", text);
      // Retorna objeto com os dados enviados já que o backend não retorna JSON
      return usuario;
    }
  },

  // Atualizar usuário existente
  atualizar: async (id: number, usuario: Usuario): Promise<Usuario> => {
    const response = await fetch(`${BASE_URL}/usuarios/atualizar/${id}`, {
      method: "PUT",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(usuario),
    });
    if (!response.ok) throw new Error("Erro ao atualizar usuário");
    return response.json();
  },

  // Deletar usuário
  deletar: async (id: number): Promise<void> => {
    const response = await fetch(`${BASE_URL}/usuarios/deletar/${id}`, {
      method: "DELETE",
    });
    if (!response.ok) throw new Error("Erro ao deletar usuário");
  },
};
