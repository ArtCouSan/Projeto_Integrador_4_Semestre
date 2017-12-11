package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "TB_SAC")
public class SAC implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SAC")
    private Long codigoSAC;

    @Size(min = 1)
    @Column(name = "EM_SAC", length = 100, nullable = false)
    private String email;

    @Size(min = 1)
    @Column(name = "NC_SAC", length = 255, nullable = false)
    private String nomeCompleto;

    @Size(min = 1)
    @Column(name = "MS_SAC", length = 500, nullable = false)
    private String mensagem;

    public SAC() {
    }

    public SAC(Long codigoSAC, String email, String nomeCompleto, String mensagem) {
        this.codigoSAC = codigoSAC;
        this.email = email;
        this.nomeCompleto = nomeCompleto;
        this.mensagem = mensagem;
    }

    public Long getCodigoSAC() {
        return codigoSAC;
    }

    public void setCodigoSAC(Long codigoSAC) {
        this.codigoSAC = codigoSAC;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
