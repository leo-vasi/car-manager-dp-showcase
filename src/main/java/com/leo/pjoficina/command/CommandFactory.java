/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;

/**
 *
 * @author levas
 */

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Map<String, ICommand> COMMANDS = new HashMap<>();
    
    static {
        COMMANDS.put("ConsultarTodos", new ConsultarTodosAction());
        COMMANDS.put("Cadastrar", new CadastrarAction());
        COMMANDS.put("Filtrar", new FiltrarAction());
        COMMANDS.put("Buscar", new BuscarAction());
        COMMANDS.put("Deletar", new DeletarAction());
        COMMANDS.put("Editar", new EditarAction());
        COMMANDS.put("Atualizar", new AtualizarAction());
        COMMANDS.put("Novo Carro", new NovoCarroAction());
    }
    
    public static ICommand create(String op) {
        ICommand command = COMMANDS.get(op);
        if (command == null) {
            return COMMANDS.get("ConsultarTodos");
        }
        return command;
    }
}