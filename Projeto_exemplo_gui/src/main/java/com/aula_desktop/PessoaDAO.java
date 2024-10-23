package com.aula_desktop;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PessoaDAO {

    private Connection conexao;

    public PessoaDAO() {
        try {
            // Conectar ao banco SQLite
            String url = "jdbc:sqlite:pessoas.db";
            conexao = DriverManager.getConnection(url);
            criarTabelaPessoas();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
    }

    // Método para criar a tabela 'pessoas' caso não exista
    private void criarTabelaPessoas() {
        String sql = "CREATE TABLE IF NOT EXISTS pessoas ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nome TEXT NOT NULL, "
                + "idade INTEGER NOT NULL, "
                + "altura REAL NOT NULL)";
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.err.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    // Método para inserir uma nova pessoa no banco de dados
    public void inserirPessoa(Pessoa pessoa) {
        String sql = "INSERT INTO pessoas (nome, idade, altura) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, pessoa.getNome());
            pstmt.setInt(2, pessoa.getIdade());
            pstmt.setFloat(3, pessoa.getAltura());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao inserir pessoa: " + e.getMessage());
        }
    }

    // Método para alterar uma pessoa existente no banco de dados
    public void alterarPessoa(int id, String nome, Integer idade, Float altura) {
        String sql = "UPDATE pessoas SET nome = ?, idade = ?, altura = ? WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, nome);
            pstmt.setInt(2, idade);
            pstmt.setFloat(3, altura);
            pstmt.setInt(4, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar pessoa: " + e.getMessage());
        }
    }

    // Método para deletar uma pessoa pelo ID
    public void apagarPessoa(int id) {
        String sql = "DELETE FROM pessoas WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Erro ao deletar pessoa: " + e.getMessage());
        }
    }

    // Método para obter uma lista de todas as pessoas do banco de dados
    public List<Pessoa> obterTodasPessoas() {
        List<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT id, nome, idade, altura FROM pessoas";
        try (Statement stmt = conexao.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Pessoa pessoa = new Pessoa(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getInt("idade"),
                        rs.getFloat("altura"));
                pessoas.add(pessoa);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter pessoas: " + e.getMessage());
        }
        return pessoas;
    }

    // Método para obter uma pessoa específica pelo ID
    public Pessoa obterPessoaPorId(int id) {
        String sql = "SELECT id, nome, idade, altura FROM pessoas WHERE id = ?";
        try (PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return new Pessoa(
                            rs.getInt("id"),
                            rs.getString("nome"),
                            rs.getInt("idade"),
                            rs.getFloat("altura"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Erro ao obter pessoa por ID: " + e.getMessage());
        }
        return null;
    }
}
