/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author joliveira
 */
@Entity
@Table(name = "TB_CATEGORIA")
@NamedQueries({
    @NamedQuery(name = "Categoria.findAll",
            query = "SELECT c FROM Categoria c")
    ,
  @NamedQuery(name = "Categoria.findById",
            query = "SELECT c FROM Categoria c WHERE c.id = :idCat")
})
public class Categoria implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CATEGORIA")
    private Long codigoCategoria;

    @Column(name = "NM_CATEGORIA", length = 100, nullable = false, unique = true)
    private String nomeCategoria;
    
    @ManyToMany(mappedBy = "categorias")
    private Set<Produto> produtos;

    public Categoria() {
    }

    public Categoria(Long codigoCategoria, String nomeCategoria, Set<Produto> produtos) {
        this.codigoCategoria = codigoCategoria;
        this.nomeCategoria = nomeCategoria;
        this.produtos = produtos;
    }

    public Long getCodigoCategoria() {
        return codigoCategoria;
    }

    public void setCodigoCategoria(Long codigoCategoria) {
        this.codigoCategoria = codigoCategoria;
    }

    public String getNomeCategoria() {
        return nomeCategoria;
    }

    public void setNomeCategoria(String nomeCategoria) {
        this.nomeCategoria = nomeCategoria;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }
}
