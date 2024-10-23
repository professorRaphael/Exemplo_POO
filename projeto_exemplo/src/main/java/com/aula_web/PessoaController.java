package com.aula_web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PessoaController {

    @Autowired
    private PessoaDAO pessoaDAO;

    // Serve a página inicial
    @GetMapping("/")
    public String index() {
        return "index.html";
    }

    // Serve a página de listagem
    @GetMapping("/pessoas")
    public String listarPessoas() {
        return "listar.html";
    }

    // Serve a API de listagem de pessoas (para JavaScript)
    @GetMapping("/pessoas/api")
    @ResponseBody
    public List<Pessoa> listarPessoasApi() {
        return pessoaDAO.obterTodasPessoas();
    }

    // Serve a API para buscar uma pessoa específica pelo ID
    @GetMapping("/pessoas/api/{id}")
    @ResponseBody
    public Pessoa obterPessoaPorIdApi(@PathVariable("id") Integer id) {
        return pessoaDAO.obterPessoaPorId(id);
    }

    // Serve a página de formulário para adicionar uma nova pessoa
    @GetMapping("/pessoas/nova")
    public String novaPessoa(Model model) {
        model.addAttribute("pessoa", new Pessoa());
        return "formulario.html";
    }

    // Salva uma nova pessoa ou atualiza uma existente
    @PostMapping("/pessoas/salvar")
    public String salvarPessoa(@ModelAttribute Pessoa pessoa) {
        if (pessoa.getId() == null || pessoa.getId() == 0) {
            // Se o ID for nulo ou 0, insere uma nova pessoa
            pessoaDAO.inserirPessoa(pessoa);
        } else {
            // Caso contrário, atualiza a pessoa existente
            pessoaDAO.alterarPessoa(pessoa.getId(), pessoa.getNome(), pessoa.getIdade(), pessoa.getAltura());
        }
        return "redirect:/pessoas";
    }

    // Serve a página de formulário para editar uma pessoa existente
    @GetMapping("/pessoas/editar/{id}")
    public String editarPessoa(@PathVariable("id") Integer id, Model model) {
        // Verifique o ID recebido
        System.out.println("Recebido ID: " + id); // Isso deve imprimir o ID correto (um número)

        Pessoa pessoa = pessoaDAO.obterPessoaPorId(id);
        if (pessoa == null) {
            throw new RuntimeException("Pessoa não encontrada");
        }

        // Verifique se a pessoa foi encontrada corretamente
        System.out.println("Pessoa encontrada: " + pessoa);

        model.addAttribute("pessoa", pessoa);

        // Retorna diretamente o arquivo HTML da pasta static
        return "/pessoas/formulario.html";
    }

    // Exclui uma pessoa pelo ID
    @PostMapping("/pessoas/deletar/{id}")
    public String excluirPessoa(@PathVariable int id) {
        pessoaDAO.apagarPessoa(id);
        return "redirect:/pessoas";
    }
}