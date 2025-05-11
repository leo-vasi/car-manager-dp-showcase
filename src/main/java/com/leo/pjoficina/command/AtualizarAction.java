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
public class AtualizarAction implements ICommand {
    @Override
    public String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
            throws ServletException, IOException, SQLException {
        
        try {
            Carro carro = criarCarro(request);
            carro.setId(Integer.parseInt(request.getParameter("txtid")));
            validarCarro(carro);
            
            carroDAO.atualizar(carro);
            request.setAttribute("mensagem", "Carro atualizado com sucesso");
            return "CarroController?op=ConsultarTodos";
            
        } catch (IllegalArgumentException e) {
            request.setAttribute("erro", e.getMessage());
            return "index.jsp";
        }
    }
    
    private Carro criarCarro(HttpServletRequest request) {
        Carro carro = new Carro();
        carro.setMarca(request.getParameter("txtmarca"));
        carro.setModelo(request.getParameter("txtmodelo"));
        carro.setAno(Integer.parseInt(request.getParameter("txtano")));
        carro.setCor(request.getParameter("txtcor"));
        carro.setMotor(request.getParameter("txtmotor"));
        carro.setPlaca(request.getParameter("txtplaca"));
        carro.setDono(request.getParameter("txtdono"));
        carro.setCnh(request.getParameter("txtcnh"));
        carro.setTelefone(request.getParameter("txttelefone"));
        return carro;
    }
    
    private void validarCarro(Carro carro) {
        if (carro.getPlaca() == null || carro.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa é obrigatória");
        }
        if (carro.getAno() <= 0) {
            throw new IllegalArgumentException("Ano deve ser maior que zero");
        }
    }
}
