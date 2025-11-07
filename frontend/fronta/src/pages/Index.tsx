import { useState } from "react";
import { Tabs, TabsContent, TabsList, TabsTrigger } from "@/components/ui/tabs";
import { UsuariosSection } from "@/components/UsuariosSection";
import { AtividadesSection } from "@/components/AtividadesSection";
import { Users, Activity } from "lucide-react";
import { Alert, AlertDescription } from "@/components/ui/alert";

const Index = () => {
  return (
    <div className="min-h-screen bg-background">
      {/* Header */}
      <header className="border-b bg-card">
        <div className="container mx-auto px-4 py-6">
          <h1 className="text-3xl font-bold text-foreground">
            Cadastro de Usuários e Atividades
          </h1>
          <p className="text-muted-foreground mt-1">
            Sistema de gerenciamento completo com CRUD
          </p>
        </div>
      </header>

      {/* Main Content */}
      <main className="container mx-auto px-4 py-8">
        {/* Alerta sobre CORS */}
     { /* <Alert className="mb-6">
          <AlertDescription>
            <strong>⚠️ Importante:</strong> Certifique-se de que o backend Spring Boot está rodando em{" "}
            <code className="px-1 py-0.5 bg-muted rounded">http://localhost:8080</code> e que o CORS
            está configurado corretamente com <code className="px-1 py-0.5 bg-muted rounded">@CrossOrigin</code>.
          </AlertDescription>
        </Alert> *?}

        {/* Tabs para alternar entre Usuários e Atividades */}
        <Tabs defaultValue="usuarios" className="w-full">
          <TabsList className="grid w-full max-w-md grid-cols-2 mb-6">
            <TabsTrigger value="usuarios" className="flex items-center gap-2">
              <Users className="h-4 w-4" />
              Usuários
            </TabsTrigger>
            <TabsTrigger value="atividades" className="flex items-center gap-2">
              <Activity className="h-4 w-4" />
              Atividades
            </TabsTrigger>
          </TabsList>

          <TabsContent value="usuarios">
            <UsuariosSection />
          </TabsContent>

          <TabsContent value="atividades">
            <AtividadesSection />
          </TabsContent>
        </Tabs>
      </main>

      {/* Footer */}
      <footer className="border-t mt-12">
        <div className="container mx-auto px-4 py-6 text-center text-sm text-muted-foreground">
          Sistema desenvolvido com React + TypeScript + Tailwind CSS
        </div>
      </footer>
    </div>
  );
};

export default Index;
