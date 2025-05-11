/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;

import com.leo.pjoficina.dao.CarroDAO;
import com.leo.pjoficina.model.Carro;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author levas
 */
public class EditarAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        
        String idStr = request.getParameter("txtid");
        if (idStr != null && !idStr.isEmpty()) {
            try {
                Carro carro = carroDAO.consultarPorId(Integer.parseInt(idStr));
                if (carro != null) {
                    request.setAttribute("carro", carro);
                    return "index.jsp";
                }
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "ID inválido");
            }
        }
        
        return "CarroController?op=ConsultarTodos";
    }
}
