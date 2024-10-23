package com.aula_desktop;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class PessoaController {

    private PessoaView view;
    private PessoaDAO pessoaDAO;

    public PessoaController(PessoaView view) {
        this.view = view;
        this.pessoaDAO = new PessoaDAO();
        configurarListeners();
    }

    public void iniciar() {
        carregarPessoas();
        view.mostrarTela(); // Mostra a interface gráfica
    }

    // Configura os listeners dos botões
    private void configurarListeners() {
        view.adicionarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarPessoa();
            }
        });

        view.editarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarPessoa();
            }
        });

        view.deletarListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletarPessoa();
            }
        });
    }

    // Carrega a lista de pessoas da base de dados e atualiza a view
    private void carregarPessoas() {
        List<Pessoa> pessoas = pessoaDAO.obterTodasPessoas();
        view.atualizarListaPessoas(pessoas);
    }

    private void adicionarPessoa() {
        Pessoa novaPessoa = view.getPessoaForm("Adicionar Pessoa");
        if (novaPessoa != null) {
            pessoaDAO.inserirPessoa(novaPessoa);
            carregarPessoas(); // Atualiza a lista de pessoas na view
            view.mostrarMensagem("Pessoa adicionada com sucesso.");
        }
    }

    private void editarPessoa() {
        int idPessoaSelecionada = view.getPessoaSelecionada();
        if (idPessoaSelecionada != -1) {
            Pessoa pessoaExistente = pessoaDAO.obterPessoaPorId(idPessoaSelecionada);
            Pessoa pessoaEditada = view.getPessoaForm("Editar Pessoa", pessoaExistente);
            if (pessoaEditada != null) {
                pessoaDAO.alterarPessoa(pessoaEditada.getId(), pessoaEditada.getNome(), pessoaEditada.getIdade(),
                        pessoaEditada.getAltura());
                carregarPessoas(); // Atualiza a lista de pessoas na view
                view.mostrarMensagem("Pessoa editada com sucesso.");
            }
        } else {
            view.mostrarMensagem("Selecione uma pessoa para editar.");
        }
    }

    private void deletarPessoa() {
        int idPessoaSelecionada = view.getPessoaSelecionada();
        if (idPessoaSelecionada != -1) {
            // Exibe uma caixa de confirmação antes de excluir
            int confirmacao = javax.swing.JOptionPane.showConfirmDialog(null,
                    "Tem certeza que deseja deletar a pessoa selecionada?",
                    "Confirmar Exclusão",
                    javax.swing.JOptionPane.YES_NO_OPTION);

            if (confirmacao == javax.swing.JOptionPane.YES_OPTION) {
                pessoaDAO.apagarPessoa(idPessoaSelecionada);
                carregarPessoas(); // Atualiza a lista de pessoas na view
                view.mostrarMensagem("Pessoa deletada com sucesso.");
            }
        } else {
            view.mostrarMensagem("Selecione uma pessoa para deletar.");
        }
    }
}
