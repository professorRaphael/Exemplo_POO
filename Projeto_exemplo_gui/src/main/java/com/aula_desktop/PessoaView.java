package com.aula_desktop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

public class PessoaView {

    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> pessoaList;
    private JButton btnAdicionar, btnEditar, btnDeletar;

    public PessoaView() {
        frame = new JFrame("Gerenciamento de Pessoas");

        // Centraliza a janela
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Layout e componentes
        listModel = new DefaultListModel<>();
        pessoaList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(pessoaList);

        btnAdicionar = new JButton("Adicionar Pessoa");
        btnEditar = new JButton("Editar Pessoa");
        btnDeletar = new JButton("Deletar Pessoa");

        JPanel panelButtons = new JPanel();
        panelButtons.add(btnAdicionar);
        panelButtons.add(btnEditar);
        panelButtons.add(btnDeletar);

        frame.setLayout(new BorderLayout());
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(panelButtons, BorderLayout.SOUTH);
    }

    public void mostrarTela() {
        frame.setVisible(true);
    }

    public void atualizarListaPessoas(List<Pessoa> pessoas) {
        listModel.clear();
        for (Pessoa pessoa : pessoas) {
            listModel.addElement(pessoa.getId() + " - " + pessoa.getNome() + " (" + pessoa.getIdade() + " anos)");
        }
    }

    public int getPessoaSelecionada() {
        int selectedIndex = pessoaList.getSelectedIndex();
        if (selectedIndex != -1) {
            String selectedValue = listModel.get(selectedIndex);
            return Integer.parseInt(selectedValue.split(" - ")[0]);
        }
        return -1;
    }

    public Pessoa getPessoaForm(String titulo) {
        return getPessoaForm(titulo, null);
    }

    public Pessoa getPessoaForm(String titulo, Pessoa pessoaExistente) {
        JTextField nomeField = new JTextField(pessoaExistente != null ? pessoaExistente.getNome() : "");
        JTextField idadeField = new JTextField(pessoaExistente != null ? String.valueOf(pessoaExistente.getIdade()) : "");
        JTextField alturaField = new JTextField(pessoaExistente != null ? String.valueOf(pessoaExistente.getAltura()) : "");

        Object[] inputFields = {
            "Nome:", nomeField,
            "Idade:", idadeField,
            "Altura:", alturaField
        };

        int option = JOptionPane.showConfirmDialog(frame, inputFields, titulo, JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            String nome = nomeField.getText();
            int idade = Integer.parseInt(idadeField.getText());
            float altura = Float.parseFloat(alturaField.getText());

            if (pessoaExistente != null) {
                pessoaExistente.setNome(nome);
                pessoaExistente.setIdade(idade);
                pessoaExistente.setAltura(altura);
                return pessoaExistente;
            } else {
                return new Pessoa(0, nome, idade, altura);
            }
        }
        return null;
    }

    public void mostrarMensagem(String mensagem) {
        JOptionPane.showMessageDialog(frame, mensagem);
    }

    // Métodos para adicionar listeners aos botões
    public void adicionarListener(ActionListener listener) {
        btnAdicionar.addActionListener(listener);
    }

    public void editarListener(ActionListener listener) {
        btnEditar.addActionListener(listener);
    }

    public void deletarListener(ActionListener listener) {
        btnDeletar.addActionListener(listener);
    }
}
