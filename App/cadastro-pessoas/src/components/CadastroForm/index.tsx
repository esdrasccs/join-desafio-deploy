import React, { useState, ChangeEvent, FormEvent } from 'react';
import { cadastrarPessoa, Pessoa } from '../../services/cadastroPessoa';
import { validarCPF } from '../../services/validaCpf';
import Popover from '../Popover';
import './CadastroForm.css';

function CadastroForm() {
  const [nome, setNome] = useState<string>('');
  const [cpf, setCpf] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [telefone, setTelefone] = useState<string>('');
  const [message, setMessage] = useState<string | null>(null);

  const handleSubmit = async (event: FormEvent) => {
    event.preventDefault();

    const pessoa: Pessoa = {
      nome,
      cpf,
      email,
      telefone
    };

    if (!validarCPF(cpf)) {
      setMessage('CPF invalido');
      return;
    }

    try {
      const successMessage = await cadastrarPessoa(pessoa);
      setMessage(successMessage);      
      setNome('');
      setCpf('');
      setEmail('');
      setTelefone('');
    } catch (error) {
      if (error instanceof Error) {
        setMessage(error.message);
      } else {
        setMessage('Ocorreu um erro desconhecido.');
      }
    }
  };

  const handleChange = (event: ChangeEvent<HTMLInputElement>, setter: (value: string) => void) => {
    setter(event.target.value);
  };

  const handleBlur = (event: React.FocusEvent<HTMLInputElement>) => {
    if (event.target.id === 'cpf' && !validarCPF(event.target.value)) {
      setMessage('CPF inválido.');
    }
  };

  return (
    <div className="cadastro-form-container">
      {message && <Popover message={message} onClose={() => setMessage(null)} />}
      <form className="cadastro-form" onSubmit={handleSubmit}>
        <h2>Cadastro de Pessoa</h2>
        <div className="form-group">
          <label htmlFor="nome">Nome:</label>
          <input
            type="text"
            id="nome"
            value={nome}
            onChange={(e) => handleChange(e, setNome)}
            required
            className="form-control"
          />
        </div>
        <div className="form-group">
          <label htmlFor="cpf">CPF:</label>
          <input
            type="text"
            id="cpf"
            value={cpf}
            onChange={(e) => handleChange(e, setCpf)}
            onBlur={handleBlur}
            required
            className="form-control"
          />
        </div>
        <div className="form-group">
          <label htmlFor="email">Email:</label>
          <input
            type="email"
            id="email"
            value={email}
            onChange={(e) => handleChange(e, setEmail)}
            required
            className="form-control"
          />
        </div>
        <div className="form-group">
          <label htmlFor="telefone">Telefone:</label>
          <input
            type="tel"
            id="telefone"
            value={telefone}
            onChange={(e) => handleChange(e, setTelefone)}
            required
            className="form-control"
          />
        </div>
        <button type="submit" className="btn btn-primary">Cadastrar</button>
      </form>
    </div>
  );
}

export default CadastroForm;