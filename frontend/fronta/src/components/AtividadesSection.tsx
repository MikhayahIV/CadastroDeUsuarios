import { useState, useEffect } from "react";
import { Atividade } from "@/types/api";
import { atividadesApi } from "@/services/api";
import { Button } from "@/components/ui/button";
import { Input } from "@/components/ui/input";
import { Label } from "@/components/ui/label";
import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card";
import { Select, SelectContent, SelectItem, SelectTrigger, SelectValue } from "@/components/ui/select";
import { toast } from "sonner";
import { Pencil, Trash2, Activity, Loader2 } from "lucide-react";

export const AtividadesSection = () => {
  const [atividades, setAtividades] = useState<Atividade[]>([]);
  const [loading, setLoading] = useState(false);
  const [editando, setEditando] = useState<Atividade | null>(null);
  
  const [formData, setFormData] = useState<Atividade>({
    nome: "",
    dificuldade: "",
  });

  // Carregar atividades ao montar o componente
  useEffect(() => {
    carregarAtividades();
  }, []);

  const carregarAtividades = async () => {
    setLoading(true);
    try {
      console.log("Carregando atividades...");
      const data = await atividadesApi.listar();
      console.log("✅ Atividades recebidas:", data);
      console.log("Tipo de data:", Array.isArray(data) ? "array" : typeof data);
      setAtividades(data);
    } catch (error) {
      console.error("❌ Erro ao carregar atividades:", error);
      toast.error("Erro ao carregar atividades");
    } finally {
      setLoading(false);
    }
  };

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setLoading(true);

    try {
      if (editando?.id) {
        await atividadesApi.atualizar(editando.id, formData);
        toast.success("Atividade atualizada com sucesso!");
      } else {
        await atividadesApi.criar(formData);
        toast.success("Atividade criada com sucesso!");
      }
      
      resetForm();
      await carregarAtividades();
    } catch (error) {
      toast.error("Erro ao salvar atividade");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const handleEditar = (atividade: Atividade) => {
    setEditando(atividade);
    setFormData({
      nome: atividade.nome,
      dificuldade: atividade.dificuldade,
    });
  };

  const handleDeletar = async (id: number) => {
    if (!confirm("Tem certeza que deseja deletar esta atividade?")) return;
    
    setLoading(true);
    try {
      await atividadesApi.deletar(id);
      toast.success("Atividade deletada com sucesso!");
      await carregarAtividades();
    } catch (error) {
      toast.error("Erro ao deletar atividade");
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  const resetForm = () => {
    setFormData({
      nome: "",
      dificuldade: "",
    });
    setEditando(null);
  };

  return (
    <div className="space-y-6">
      {/* Formulário de Criação/Edição */}
      <Card>
        <CardHeader>
          <CardTitle className="flex items-center gap-2">
            <Activity className="h-5 w-5" />
            {editando ? "Editar Atividade" : "Nova Atividade"}
          </CardTitle>
        </CardHeader>
        <CardContent>
          <form onSubmit={handleSubmit} className="space-y-4">
            <div className="grid grid-cols-1 md:grid-cols-2 gap-4">
              <div className="space-y-2">
                <Label htmlFor="nome">Nome da Atividade</Label>
                <Input
                  id="nome"
                  value={formData.nome}
                  onChange={(e) => setFormData({ ...formData, nome: e.target.value })}
                  required
                  placeholder="Ex: Corrida"
                />
              </div>

              <div className="space-y-2">
                <Label htmlFor="dificuldade">Dificuldade</Label>
                <Select
                  value={formData.dificuldade}
                  onValueChange={(value) =>
                    setFormData({ ...formData, dificuldade: value })
                  }
                >
                  <SelectTrigger>
                    <SelectValue placeholder="Selecione a dificuldade" />
                  </SelectTrigger>
                  <SelectContent>
                    <SelectItem value="Fácil">Fácil</SelectItem>
                    <SelectItem value="Média">Média</SelectItem>
                    <SelectItem value="Difícil">Difícil</SelectItem>
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

      {/* Lista de Atividades */}
      <Card>
        <CardHeader>
          <CardTitle>Lista de Atividades</CardTitle>
        </CardHeader>
        <CardContent>
          {loading && !atividades.length ? (
            <div className="flex justify-center py-8">
              <Loader2 className="h-8 w-8 animate-spin text-primary" />
            </div>
          ) : atividades.length === 0 ? (
            <p className="text-center text-muted-foreground py-8">
              Nenhuma atividade cadastrada
            </p>
          ) : (
            <div className="overflow-x-auto">
              <table className="w-full">
                <thead>
                  <tr className="border-b">
                    <th className="text-left p-3">ID</th>
                    <th className="text-left p-3">Nome</th>
                    <th className="text-left p-3">Dificuldade</th>
                    <th className="text-right p-3">Ações</th>
                  </tr>
                </thead>
                <tbody>
                  {atividades.map((atividade) => (
                    <tr key={atividade.id} className="border-b hover:bg-muted/50">
                      <td className="p-3 text-muted-foreground">{atividade.id}</td>
                      <td className="p-3 font-medium">{atividade.nome}</td>
                      <td className="p-3">
                        <span
                          className={`inline-flex items-center rounded-full px-2.5 py-0.5 text-xs font-medium ${
                            atividade.dificuldade === "Fácil"
                              ? "bg-accent/10 text-accent"
                              : atividade.dificuldade === "Média"
                              ? "bg-primary/10 text-primary"
                              : "bg-destructive/10 text-destructive"
                          }`}
                        >
                          {atividade.dificuldade}
                        </span>
                      </td>
                      <td className="p-3">
                        <div className="flex justify-end gap-2">
                          <Button
                            variant="outline"
                            size="sm"
                            onClick={() => handleEditar(atividade)}
                          >
                            <Pencil className="h-4 w-4" />
                          </Button>
                          <Button
                            variant="destructive"
                            size="sm"
                            onClick={() => handleDeletar(atividade.id!)}
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
