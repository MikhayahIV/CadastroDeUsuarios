import { useState, useEffect } from "react";
import { Usuario, Atividade } from "@/types/api";
import { usuariosApi, atividadesApi } from "@/services/api";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { toast } from "sonner";
import { Pencil, Trash2, UserPlus, Loader2 } from "lucide-react";

export const UsuariosSection = () => {
  const [usuarios, setUsuarios] = useState<Usuario[]>([]);
  const [atividades, setAtividades] = useState<Atividade[]>([]);
  const [loading, setLoading] = useState(false);
  const [editando, setEditando] = useState<Usuario | null>(null);
  
  const [formData, setFormData] = useState<Usuario>({
    nome: "",
    imgUrl: "",
    email: "",
    cargo: "",
    idade: 0,
    atividades: null,
  });

  // Carregar usuários e atividades ao montar o componente
  useEffect(() => {
    carregarDados();
  }, []);

  const carregarDados = async () => {
    setLoading(true);
    try {
      console.log("Iniciando carregamento de dados...");
      const [usuariosData, atividadesData] = await Promise.all([
        usuariosApi.listar(),
        atividadesApi.listar(),
      ]);
      console.log("✅ Usuários recebidos:", usuariosData);
      console.log("✅ Atividades recebidas:", atividadesData);
      console.log("Tipo de usuariosData:", Array.isArray(usuariosData) ? "array" : typeof usuariosData);
      console.log("Tipo de atividadesData:", Array.isArray(atividadesData) ? "array" : typeof atividadesData);
      setUsuarios(usuariosData);
      setAtividades(atividadesData);
    } catch (error) {
      console.error("❌ Erro ao carregar dados:", error);
      toast.error("Erro ao carregar dados");
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      console.log("💾 Salvando usuário:", formData);
      if (editando?.id) {
        const result = await usuariosApi.atualizar(editando.id, formData);
        console.log("✅ Usuário atualizado:", result);
        toast.success("Usuário atualizado com sucesso!");
      } else {
        const result = await usuariosApi.criar(formData);
        console.log("✅ Usuário criado:", result);
        toast.success("Usuário criado com sucesso!");
      }

      resetForm();
      console.log("🔄 Recarregando dados...");
      await carregarDados();
      console.log("✅ Dados recarregados");
    } catch (error) {
      console.error("❌ Erro ao salvar usuário:", error);
      toast.error("Erro ao salvar usuário");
    } finally {
      setLoading(false);
    }
  };

  const handleEditar = (usuario: Usuario) => {
    setEditando(usuario);
    setFormData({
      ...usuario,
      atividades: usuario.atividades || null,
    });
  };

  const handleDeletar = async (id: number) => {
    if (!confirm("Tem certeza que deseja deletar este usuário?")) return;

    setLoading(true);
    try {
      await usuariosApi.deletar(id);
      toast.success("Usuário deletado com sucesso!");
      await carregarDados();
    } catch (error) {
      toast.error("Erro ao deletar usuário");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const resetForm = () => {
    setFormData({
      nome: "",
      imgUrl: "",
      email: "",
      cargo: "",
      idade: 0,
      atividades: null,
    });
    setEditando(null);
  };

  return (
    <div className="space-y-6">
      {/* Formulário de Criação/Edição */}
      <Card>
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <UserPlus className="h-5 w-5" />
            {editando ? "Editar Usuário" : "Novo Usuário"}
          </CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label htmlFor="nome">Nome</Label>
                <Input
                  id="nome"
                  value={formData.nome}
                  onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                  required
                  placeholder="Ex: Jake"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="email">Email</Label>
                <Input
                  id="email"
                  type="email"
                  value={formData.email}
                  onChange={(e) => setFormData({ ...formData, email: e.target.value })}
                  required
                  placeholder="jake@email.com"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="cargo">Cargo</Label>
                <Input
                  id="cargo"
                  value={formData.cargo}
                  onChange={(e) => setFormData({ ...formData, cargo: e.target.value })}
                  required
                  placeholder="Ex: Desenvolvedor"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="idade">Idade</Label>
                <Input
                  id="idade"
                  type="number"
                  value={formData.idade || ""}
                  onChange={(e) => setFormData({ ...formData, idade: Number(e.target.value) })}
                  required
                  min="1"
                  placeholder="25"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="imgUrl">URL da Imagem</Label>
                <Input
                  id="imgUrl"
                  value={formData.imgUrl}
                  onChange={(e) => setFormData({ ...formData, imgUrl: e.target.value })}
                  required
                  placeholder="https://..."
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="atividade">Atividade (opcional)</Label>
                <Select
                  value={formData.atividades?.id?.toString() || undefined}
                  onValueChange={(value) =>
                    setFormData({
                      ...formData,
                      atividades: value ? { id: Number(value) } : null,
                    })
                  }
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione uma atividade (opcional)" />
                  </SelectTrigger>
                  <SelectContent>
                    {atividades.map((atividade) => (
                      <SelectItem key={atividade.id} value={atividade.id!.toString()}>
                        {atividade.nome} - {atividade.dificuldade}
                      </SelectItem>
                    ))}
                  </SelectContent>
                </Select>
              </div>
            </div>

            <div className="flex gap-2">
              <Button type="submit" disabled={loading}>
                {loading && <Loader2 className="mr-2 h-4 w-4 animate-spin" />}
                {editando ? "Atualizar" : "Criar"}
              </Button>
              {editando && (
                <Button type="button" variant="outline" onClick={resetForm}>
                  Cancelar
                </Button>
              )}
            </div>
          </form>
        </CardContent>
      </Card>

      {/* Lista de Usuários */}
      <Card>
        <CardHeader>
          <CardTitle>Lista de Usuários</CardTitle>
        </CardHeader>
        <CardContent>
          {loading && !usuarios.length ? (
            <div className="flex justify-center py-8">
              <Loader2 className="h-8 w-8 animate-spin text-primary" />
            </div>
          ) : usuarios.length === 0 ? (
            <p className="text-center text-muted-foreground py-8">
              Nenhum usuário cadastrado
            </p>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full">
                <thead>
                  <tr className="border-b">
                    <th className="text-left p-3">Imagem</th>
                    <th className="text-left p-3">Nome</th>
                    <th className="text-left p-3">Email</th>
                    <th className="text-left p-3">Cargo</th>
                    <th className="text-left p-3">Idade</th>
                    <th className="text-left p-3">Atividade</th>
                    <th className="text-right p-3">Ações</th>
                  </tr>
                </thead>
                <tbody>
                  {usuarios.map((usuario) => (
                    <tr key={usuario.id} className="border-b hover:bg-muted/50">
                      <td className="p-3">
                        <div className="h-10 w-10 rounded-full bg-primary/10 flex items-center justify-center overflow-hidden">
                          {usuario.imgUrl ? (
                            <img
                              src={usuario.imgUrl}
                              alt={usuario.nome}
                              className="h-full w-full object-cover"
                              onError={(e) => {
                                e.currentTarget.style.display = 'none';
                                e.currentTarget.parentElement!.innerHTML = `<span class="text-primary font-medium text-sm">${usuario.nome.charAt(0).toUpperCase()}</span>`;
                              }}
                            />
                          ) : (
                            <span className="text-primary font-medium text-sm">
                              {usuario.nome.charAt(0).toUpperCase()}
                            </span>
                          )}
                        </div>
                      </td>
                      <td className="p-3 font-medium">{usuario.nome}</td>
                      <td className="p-3 text-muted-foreground">{usuario.email}</td>
                      <td className="p-3">{usuario.cargo}</td>
                      <td className="p-3">{usuario.idade}</td>
                      <td className="p-3">
                        {usuario.atividades?.id
                          ? atividades.find((a) => a.id === usuario.atividades?.id)?.nome ||
                            "N/A"
                          : "-"}
                      </td>
                      <td className="p-3">
                        <div className="flex justify-end gap-2">
                          <Button
                            variant="outline"
                            size="sm"
                            onClick={() => handleEditar(usuario)}
                          >
                            <Pencil className="h-4 w-4" />
                          </Button>
                          <Button
                            variant="destructive"
                            size="sm"
                            onClick={() => handleDeletar(usuario.id!)}
                          >
                            <Trash2 className="h-4 w-4" />
                          </Button>
                        </div>
                      </td>
                    </tr>
                  ))}
                </tbody>
              </table>
            </div>
          )}
        </CardContent>
      </Card>
    </div>
  );
};
