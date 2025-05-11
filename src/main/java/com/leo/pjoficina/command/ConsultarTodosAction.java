/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;

/**
 *
 * @author levas
 */

import com.leo.pjoficina.dao.CarroDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class ConsultarTodosAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        request.setAttribute("lcarro", carroDAO.consultarTodos());
        return "index.jsp";
    }
}
