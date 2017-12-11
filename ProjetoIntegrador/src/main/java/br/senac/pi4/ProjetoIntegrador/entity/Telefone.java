/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author thales.dsouza
 */
@Entity
@Table(name = "TB_TELEFONE")
public class Telefone implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_TELEFONE")
    private Long codigoTelefone;

    @Size(min = 1, max = 3, message = "{telefone.dddTelefone.erro}")
    @Column(name = "DD_TELEFONE", length = 3, nullable = false)
    private String dddTelefone;

    @Size(min = 9, max = 9, message = "{telefone.numeroTelefone.erro}")
    @Column(name = "NM_TELEFONE", length = 3, nullable = false)
    private String numeroTelefone;

    @Size(min = 1, max = 3, message = "{telefone.dddCelular.erro}")
    @Column(name = "DC_TELEFONE", length = 3, nullable = false)
    private String dddCelular;

    @Size(min = 10, max = 10, message = "{telefone.numeroCelular.erro}")
    @Column(name = "NC_TELEFONE", length = 3, nullable = false)
    private String numeroCelular;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente clienteTelefone;

    public Telefone(Long codigoTelefone, String dddTelefone, String numeroTelefone, String dddCelular, String numeroCelular, Cliente clienteTelefone) {
        this.codigoTelefone = codigoTelefone;
        this.dddTelefone = dddTelefone;
        this.numeroTelefone = numeroTelefone;
        this.dddCelular = dddCelular;
        this.numeroCelular = numeroCelular;
        this.clienteTelefone = clienteTelefone;
    }

    public Telefone() {
    }

    public Long getCodigoTelefone() {
        return codigoTelefone;
    }

    public void setCodigoTelefone(Long codigoTelefone) {
        this.codigoTelefone = codigoTelefone;
    }

    public String getDddTelefone() {
        return dddTelefone;
    }

    public void setDddTelefone(String dddTelefone) {
        this.dddTelefone = dddTelefone;
    }

    public String getNumeroTelefone() {
        return numeroTelefone;
    }

    public void setNumeroTelefone(String numeroTelefone) {
        this.numeroTelefone = numeroTelefone;
    }

    public String getDddCelular() {
        return dddCelular;
    }

    public void setDddCelular(String dddCelular) {
        this.dddCelular = dddCelular;
    }

    public String getNumeroCelular() {
        return numeroCelular;
    }

    public void setNumeroCelular(String numeroCelular) {
        this.numeroCelular = numeroCelular;
    }

    public Cliente getClienteTelefone() {
        return clienteTelefone;
    }

    public void setClienteTelefone(Cliente clienteTelefone) {
        this.clienteTelefone = clienteTelefone;
    }
}
