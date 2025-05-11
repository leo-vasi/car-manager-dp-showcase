/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.command;


import com.leo.pjoficina.dao.CarroDAO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public interface ICommand {
    
    String executar(HttpServletRequest request, HttpServletResponse response, CarroDAO carroDAO) 
        throws ServletException, IOException, SQLException;
}
