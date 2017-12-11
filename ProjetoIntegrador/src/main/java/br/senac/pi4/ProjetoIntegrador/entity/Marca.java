/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "TB_MARCA")
public class Marca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MARCA")
    private Long codigoMarca;

    @Size(min = 1, max = 100, message = "{marca.tituloMarca.erro}")
    @Column(name = "TL_MARCA", length = 100, nullable = false)
    private String tituloMarca;

    @Size(min = 1, max = 1000, message = "{marca.descricaoMarca.erro}")
    @Column(name = "DS_MARCA", length = 1000, nullable = false)
    private String descricaoMarca;

    @OneToMany(mappedBy = "marcaProduto", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Produto> produtos;

    public Marca() {
    }

    public Marca(Long codigoMarca, String tituloMarca, String descricaoMarca, List<Produto> produtos) {
        this.codigoMarca = codigoMarca;
        this.tituloMarca = tituloMarca;
        this.descricaoMarca = descricaoMarca;
        this.produtos = produtos;
    }

    public Long getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(Long codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getTituloMarca() {
        return tituloMarca;
    }

    public void setTituloMarca(String tituloMarca) {
        this.tituloMarca = tituloMarca;
    }

    public String getDescricaoMarca() {
        return descricaoMarca;
    }

    public void setDescricaoMarca(String descricaoMarca) {
        this.descricaoMarca = descricaoMarca;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}
