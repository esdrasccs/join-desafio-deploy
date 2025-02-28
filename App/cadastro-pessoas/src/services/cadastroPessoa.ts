export interface Pessoa {
    nome: string;
    cpf: string;
    email: string;
    telefone: string;
  }
  
  export const cadastrarPessoa = async (pessoa: Pessoa): Promise<string> => {
    try {
      const apiUrl = process.env.REACT_APP_API_URL || 'http://localhost:8080/cadastro/pessoa';
      const response = await fetch(apiUrl || '', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(pessoa)
      });
  
      if (response.ok) {
        return 'Cadastro realizado com sucesso!';
      } else {
        const error = await response.text();
        throw new Error(`${error}`);
      }
    } catch (error) {
      console.error('Erro ao enviar requisição:', error);
      throw new Error(`${error}`);
    }
  };