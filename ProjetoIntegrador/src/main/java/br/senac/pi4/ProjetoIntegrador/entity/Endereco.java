
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


@Entity
@Table(name = "TB_ENDERECO")
public class Endereco implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ENDERECO")
    private Long codigoEndereco;
    
    @Size(min = 1, max = 500, message = "{endereco.logradouroEndereco.erro}")
    @Column(name = "LO_ENDERECO", length = 100, nullable = false)
    private String logradouroEndereco;
    
    @Size(min = 1, max = 11, message = "{endereco.numeroEndereco.erro}")
    @Column(name = "NU_ENDERECO", length = 100, nullable = false)
    private String numeroEndereco;
    
    @Size(max = 100, message = "{endereco.complementoEndereco.erro}")
    @Column(name = "CP_ENDERECO", length = 100, nullable = true)
    private String complementoEndereco;
    
    @Size(min = 1, max = 100, message = "{endereco.bairroEndereco.erro}")
    @Column(name = "BR_ENDERECO", length = 100, nullable = false)
    private String bairroEndereco;
    
    @Size(min = 1, max = 100, message = "{endereco.cidadeEndereco.erro}")
    @Column(name = "CD_ENDERECO", length = 100, nullable = false)
    private String cidadeEndereco;
    
    @Size(min = 1, max = 2, message = "{endereco.estadoEndereco.erro}")
    @Column(name = "ES_ENDERECO", length = 2, nullable = false)
    private String estadoEndereco;
    
    @Column(name = "CE_ENDERECO", length = 9, nullable = false)
    private String cepEndereco;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente clienteEnderecos;

    public Endereco() {
    }

    public Endereco(Long codigoEndereco, String logradouroEndereco, String numeroEndereco, String complementoEndereco, String bairroEndereco, String cidadeEndereco, String estadoEndereco, String cepEndereco, Cliente clienteEnderecos) {
        this.codigoEndereco = codigoEndereco;
        this.logradouroEndereco = logradouroEndereco;
        this.numeroEndereco = numeroEndereco;
        this.complementoEndereco = complementoEndereco;
        this.bairroEndereco = bairroEndereco;
        this.cidadeEndereco = cidadeEndereco;
        this.estadoEndereco = estadoEndereco;
        this.cepEndereco = cepEndereco;
        this.clienteEnderecos = clienteEnderecos;
    }

    public Long getCodigoEndereco() {
        return codigoEndereco;
    }

    public void setCodigoEndereco(Long codigoEndereco) {
        this.codigoEndereco = codigoEndereco;
    }

    public String getLogradouroEndereco() {
        return logradouroEndereco;
    }

    public void setLogradouroEndereco(String logradouroEndereco) {
        this.logradouroEndereco = logradouroEndereco;
    }

    public String getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(String numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public String getComplementoEndereco() {
        return complementoEndereco;
    }

    public void setComplementoEndereco(String complementoEndereco) {
        this.complementoEndereco = complementoEndereco;
    }

    public String getBairroEndereco() {
        return bairroEndereco;
    }

    public void setBairroEndereco(String bairroEndereco) {
        this.bairroEndereco = bairroEndereco;
    }

    public String getCidadeEndereco() {
        return cidadeEndereco;
    }

    public void setCidadeEndereco(String cidadeEndereco) {
        this.cidadeEndereco = cidadeEndereco;
    }

    public String getEstadoEndereco() {
        return estadoEndereco;
    }

    public void setEstadoEndereco(String estadoEndereco) {
        this.estadoEndereco = estadoEndereco;
    }

    public String getCepEndereco() {
        return cepEndereco;
    }

    public void setCepEndereco(String cepEndereco) {
        this.cepEndereco = cepEndereco;
    }

    public Cliente getClienteEnderecos() {
        return clienteEnderecos;
    }

    public void setClienteEnderecos(Cliente clienteEnderecos) {
        this.clienteEnderecos = clienteEnderecos;
    }

    
}
