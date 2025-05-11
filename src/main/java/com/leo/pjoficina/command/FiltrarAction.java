/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;

import com.leo.pjoficina.dao.CarroDAO;
import com.leo.pjoficina.model.Carro;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author levas
 */
public class FiltrarAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        
        String idStr = request.getParameter("txtid");
        List<Carro> listaFiltrada = new ArrayList<>();
        
        if (idStr != null && !idStr.isEmpty()) {
            try {
                Carro carro = carroDAO.consultarPorId(Integer.parseInt(idStr));
                if (carro != null) {
                    listaFiltrada.add(carro);
                }
            } catch (NumberFormatException e) {
                request.setAttribute("erro", "ID inválido");
            }
        }
        
        request.setAttribute("lcarro", listaFiltrada);
        return "index.jsp";
    }
}
