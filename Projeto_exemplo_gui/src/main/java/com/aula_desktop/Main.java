package com.aula_desktop;

import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PessoaView view = new PessoaView();
            PessoaController controller = new PessoaController(view);
            controller.iniciar();
        });
    }
}
