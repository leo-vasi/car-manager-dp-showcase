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
public class NovoCarroAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        
        try {
            request.setAttribute("carro", new Carro());
           
            request.setAttribute("modo", "novo");
            
            return "cadastro.jsp";
            
        } catch (Exception e) {
            request.setAttribute("erro", "Não foi possível preparar o formulário de cadastro");
            return "index.jsp";
        }
    }
}
