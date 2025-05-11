/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.leo.pjoficina.model;

/**
 *
 * @author levas
 */
import java.time.LocalDateTime;

public class Carro {

    private int id;
    private String marca;
    private String modelo;
    private int ano;
    private String cor;
    private String motor;
    private String placa;
    private String dono;
    private String cnh;
    private String telefone;
    private LocalDateTime cadastradoEm;
    private LocalDateTime alteradoEm;

    public Carro() {
    }

    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAno() {
        return ano;
    }

    public String getCor() {
        return cor;
    }

    public String getMotor() {
        return motor;
    }

    public String getPlaca() {
        return placa;
    }

    public String getDono() {
        return dono;
    }

    public String getCnh() {
        return cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public LocalDateTime getCadastradoEm() {
        return cadastradoEm;
    }

    public LocalDateTime getAlteradoEm() {
        return alteradoEm;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setDono(String dono) {
        this.dono = dono;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setCadastradoEm(LocalDateTime cadastradoEm) {
        this.cadastradoEm = cadastradoEm;
    }

    public void setAlteradoEm(LocalDateTime alteradoEm) {
        this.alteradoEm = alteradoEm;
    }

    public static class CarroBuilder {

        private int id;
        private String marca;
        private String modelo;
        private int ano;
        private String cor;
        private String motor;
        private String placa;
        private String dono;
        private String cnh;
        private String telefone;
        private LocalDateTime cadastradoEm;
        private LocalDateTime alteradoEm;

        public CarroBuilder comId(int id) {
            this.id = id;
            return this;
        }

        public CarroBuilder comMarca(String marca) {
            this.marca = marca;
            return this;
        }

        public CarroBuilder comModelo(String modelo) {
            this.modelo = modelo;
            return this;
        }

        public CarroBuilder comAno(int ano) {
            this.ano = ano;
            return this;
        }

        public CarroBuilder comCor(String cor) {
            this.cor = cor;
            return this;
        }

        public CarroBuilder comMotor(String motor) {
            this.motor = motor;
            return this;
        }

        public CarroBuilder comPlaca(String placa) {
            this.placa = placa;
            return this;
        }

        public CarroBuilder comDono(String dono) {
            this.dono = dono;
            return this;
        }

        public CarroBuilder comCnh(String cnh) {
            this.cnh = cnh;
            return this;
        }

        public CarroBuilder comTelefone(String telefone) {
            this.telefone = telefone;
            return this;
        }

        public CarroBuilder comCadastradoEm(LocalDateTime cadastradoEm) {
            this.cadastradoEm = cadastradoEm;
            return this;
        }

        public CarroBuilder comAlteradoEm(LocalDateTime alteradoEm) {
            this.alteradoEm = alteradoEm;
            return this;
        }

        public Carro build() {
            Carro carro = new Carro();
            carro.setId(this.id);
            carro.setMarca(this.marca);
            carro.setModelo(this.modelo);
            carro.setAno(this.ano);
            carro.setCor(this.cor);
            carro.setMotor(this.motor);
            carro.setPlaca(this.placa);
            carro.setDono(this.dono);
            carro.setCnh(this.cnh);
            carro.setTelefone(this.telefone);
            carro.setCadastradoEm(this.cadastradoEm);
            carro.setAlteradoEm(this.alteradoEm);
            return carro;
        }
    }

}
