/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;

import com.leo.pjoficina.dao.CarroDAO;
import com.leo.pjoficina.model.Carro;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 *
 * @author levas
 */
public class BuscarAction implements ICommand {

    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        
        try {
            String tipoBusca = request.getParameter("tipoBusca");
            String valorBusca = request.getParameter("valorBusca");
            
            if (tipoBusca == null || valorBusca == null) {
                request.setAttribute("erro", "Parâmetros de busca inválidos");
                return "index.jsp";
            }
            
            List<Carro> resultados = realizarBusca(carroDAO, tipoBusca, valorBusca.trim());
            
            request.setAttribute("lcarro", resultados);
            
            if (resultados.isEmpty()) {
                request.setAttribute("aviso", "Nenhum carro encontrado com os critérios informados");
            }
            
            return "index.jsp";
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            return "index.jsp";
        }
    }
    
    private List<Carro> realizarBusca(CarroDAO carroDAO, String tipoBusca, String valor) 
            throws SQLException, IllegalArgumentException {
        
        if (valor.isEmpty()) {
            return Collections.emptyList();
        }
        
        switch (tipoBusca) {
            case "id":
                try {
                    Carro carro = carroDAO.consultarPorId(Integer.parseInt(valor));
                    return carro != null ? Collections.singletonList(carro) : Collections.emptyList();
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException("ID deve ser um número válido");
                }
                
            case "placa":
                return carroDAO.buscarPorPlaca(valor);
                
            case "cnh":
                return carroDAO.buscarPorCnh(valor);
                
            case "telefone":
                return carroDAO.buscarPorTelefone(valor);
                
            default:
                throw new IllegalArgumentException("Tipo de busca inválida: " + tipoBusca);
        }
    }
}