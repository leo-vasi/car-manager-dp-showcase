
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.controller;

import com.leo.pjoficina.command.CommandFactory;
import com.leo.pjoficina.command.ICommand;
import com.leo.pjoficina.dao.CarroDAO;
import com.leo.pjoficina.util.ConnectionFactory;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author levas
 */
@WebServlet(name = "CarroController", urlPatterns = {"/CarroController"})
public class CarroController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String op = request.getParameter("op") != null ? 
                   request.getParameter("op") : "ConsultarTodos";
        
        Connection con = null;
        try {
            con = ConnectionFactory.getConnection();
            CarroDAO carroDAO = new CarroDAO();
            
            ICommand command = CommandFactory.create(op);
            String pagina = command.executar(request, response, carroDAO);
            
            request.getRequestDispatcher(pagina).forward(request, response);
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("erro", "Ocorreu um erro inesperado");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } finally {
            if (con != null) {
                ConnectionFactory.closeConnection(con);
            }
        }
    }
}