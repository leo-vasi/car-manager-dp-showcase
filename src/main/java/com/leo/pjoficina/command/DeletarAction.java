/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;

import com.leo.pjoficina.dao.CarroDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author levas
 */
public class DeletarAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        
        String idStr = request.getParameter("txtid");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                carroDAO.deletar(Integer.parseInt(idStr));
                request.setAttribute("mensagem", "Carro deletado com sucesso");
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "ID inválido");
            }
        }
        
        return "CarroController?op=ConsultarTodos";
    }
}
