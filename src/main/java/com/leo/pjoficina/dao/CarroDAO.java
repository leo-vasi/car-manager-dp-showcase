/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.dao;

/**
 *
 * @author levas
 */
import com.leo.pjoficina.model.Carro;
import com.leo.pjoficina.util.ConnectionFactory;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CarroDAO {

    private static final String COLUNAS_CARRO = "id, marca, modelo, ano, cor, motor, placa, dono, cnh, telefone, cadastrado_em, alterado_em";

    public void cadastrar(Carro carro) throws SQLException, IllegalArgumentException {
        validarCarro(carro);
        String sql = "INSERT INTO carros (marca, modelo, ano, cor, motor, placa, dono, cnh, telefone) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {

            setarParametrosBasicos(stmt, carro);
            stmt.executeUpdate();

            setarIdGerado(stmt, carro);
        }
    }

    public void atualizar(Carro carro) throws SQLException, IllegalArgumentException {
        validarCarro(carro);
        String sql = "UPDATE carros SET marca=?, modelo=?, ano=?, cor=?, motor=?, placa=?, "
                + "dono=?, cnh=?, telefone=?, alterado_em=CURRENT_TIMESTAMP WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            setarParametrosBasicos(stmt, carro);
            stmt.setInt(10, carro.getId());
            stmt.executeUpdate();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM carros WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    public Carro consultarPorId(int id) throws SQLException {
        String sql = "SELECT " + COLUNAS_CARRO + " FROM carros WHERE id=?";

        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? construirCarro(rs) : null;
            }
        }
    }

    // Métodos de consulta
    public List<Carro> buscarPorPlaca(String placa) throws SQLException {
        return buscarComFiltro("SELECT * FROM carros WHERE placa LIKE ?", "%" + placa + "%");
    }

    public List<Carro> buscarPorCnh(String cnh) throws SQLException {
        return buscarComFiltro("SELECT * FROM carros WHERE cnh LIKE ?", "%" + cnh + "%");
    }

    public List<Carro> buscarPorTelefone(String telefone) throws SQLException {
        return buscarComFiltro("SELECT * FROM carros WHERE telefone LIKE ?", "%" + telefone + "%");
    }

    public List<Carro> consultarTodos() throws SQLException {
        String sql = "SELECT " + COLUNAS_CARRO + " FROM carros";

        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            List<Carro> carros = new ArrayList<>();
            while (rs.next()) {
                carros.add(construirCarro(rs));
            }
            return carros;
        }
    }

    private List<Carro> buscarComFiltro(String sql, String parametro) throws SQLException {
        try (Connection con = ConnectionFactory.getConnection(); PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, parametro);
            try (ResultSet rs = stmt.executeQuery()) {
                List<Carro> carros = new ArrayList<>();
                while (rs.next()) {
                    carros.add(construirCarro(rs));
                }
                return carros;
            }
        }
    }

    private void setarParametrosBasicos(PreparedStatement stmt, Carro carro) throws SQLException {
        stmt.setString(1, carro.getMarca());
        stmt.setString(2, carro.getModelo());
        stmt.setInt(3, carro.getAno());
        stmt.setString(4, carro.getCor() != null ? carro.getCor() : "");
        stmt.setString(5, carro.getMotor());
        stmt.setString(6, carro.getPlaca());
        stmt.setString(7, carro.getDono() != null ? carro.getDono() : "");
        stmt.setString(8, carro.getCnh() != null ? carro.getCnh() : "");
        stmt.setString(9, carro.getTelefone() != null ? carro.getTelefone() : "");
    }

    private void setarIdGerado(PreparedStatement stmt, Carro carro) throws SQLException {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                carro.setId(rs.getInt(1));
            }
        }
    }

    private Carro construirCarro(ResultSet rs) throws SQLException {
        return new Carro.CarroBuilder()
                .comId(rs.getInt("id"))
                .comMarca(rs.getString("marca"))
                .comModelo(rs.getString("modelo"))
                .comAno(rs.getInt("ano"))
                .comCor(rs.getString("cor"))
                .comMotor(rs.getString("motor"))
                .comPlaca(rs.getString("placa"))
                .comDono(rs.getString("dono"))
                .comCnh(rs.getString("cnh"))
                .comTelefone(rs.getString("telefone"))
                .comCadastradoEm(converterParaLocalDateTime(rs.getTimestamp("cadastrado_em")))
                .comAlteradoEm(converterParaLocalDateTime(rs.getTimestamp("alterado_em")))
                .build();
    }

    private LocalDateTime converterParaLocalDateTime(Timestamp timestamp) {
        return timestamp != null ? timestamp.toLocalDateTime() : null;
    }

    private void validarCarro(Carro carro) throws IllegalArgumentException {
        if (carro == null) {
            throw new IllegalArgumentException("Carro não pode ser nulo");
        }
        if (carro.getMarca() == null || carro.getMarca().trim().isEmpty()) {
            throw new IllegalArgumentException("Marca do carro é obrigatória");
        }
        if (carro.getModelo() == null || carro.getModelo().trim().isEmpty()) {
            throw new IllegalArgumentException("Modelo do carro é obrigatório");
        }
        if (carro.getAno() <= 0) {
            throw new IllegalArgumentException("Ano do carro é obrigatório");
        }
        if (carro.getMotor() == null || carro.getMotor().trim().isEmpty()) {
            throw new IllegalArgumentException("Motor do carro é obrigatório");
        }
        if (carro.getPlaca() == null || carro.getPlaca().trim().isEmpty()) {
            throw new IllegalArgumentException("Placa do carro é obrigatória");
        }
    }
}
