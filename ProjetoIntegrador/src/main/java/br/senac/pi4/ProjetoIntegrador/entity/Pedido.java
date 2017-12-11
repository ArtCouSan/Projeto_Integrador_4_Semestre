/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author jonat
 */
@Entity
@Table(name = "TB_PEDIDO")
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PEDIDO")
    private Long codigoPedido;

    @ManyToMany
    @JoinTable(name = "TB_PRODUTO_PEDIDO",
            joinColumns = {
                @JoinColumn(name = "ID_PRODUTO")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ID_PEDIDO")
            })
    private Set<Produto> produtos;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_CLIENTE", nullable = false)
    private Cliente clientePedido;

    @Column(name = "DT_PEDIDO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPedido;

    @Column(name = "UA_PEDIDO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "dd-MM-YYYY HH:mm:ss")
    private Date ultimaAtualizacao;

    @Size(min = 1, max = 100, message = "{pedido.statusPedido.erro}")
    @Column(name = "ST_PEDIDO", length = 100, nullable = false)
    private String statusPedido;

    @Digits(integer = 6, fraction = 2, message = "{pedido.valorPedido.erro}")
    @Column(name = "VL_PEDIDO", precision = 6, scale = 2, nullable = false)
    private BigDecimal valorPedido;

    @Size(min = 1, max = 50, message = "{pedido.formaPagamentoPedido.erro}")
    @Column(name = "FP_PEDIDO", length = 100, nullable = false)
    private String formaPagamentoPedido;
    
    @Column(name = "PR_PEDIDO", length = 5, nullable = true)
    private String protocoloPedido;

    @Transient
    private Long idCliente;
    
    @Transient
    private String nomeCliente;
    
    @Transient
    private Set<Long> idProdutos;

    public Pedido() {
    }

    public Pedido(Long codigoPedido, Set<Produto> produtos, String protocoloPedido, Cliente clientePedido, Date dataPedido, Date ultimaAtualizacao, String statusPedido, BigDecimal valorPedido, String formaPagamentoPedido, Long idCliente, Set<Long> idProdutos) {
        this.codigoPedido = codigoPedido;
        this.produtos = produtos;
        this.clientePedido = clientePedido;
        this.dataPedido = dataPedido;
        this.ultimaAtualizacao = ultimaAtualizacao;
        this.statusPedido = statusPedido;
        this.valorPedido = valorPedido;
        this.formaPagamentoPedido = formaPagamentoPedido;
        this.idCliente = idCliente;
        this.idProdutos = idProdutos;
        this.protocoloPedido = protocoloPedido;
    }

    public String getProtocoloPedido() {
        return protocoloPedido;
    }

    public void setProtocoloPedido(String protocoloPedido) {
        this.protocoloPedido = protocoloPedido;
    }  

    public Long getCodigoPedido() {
        return codigoPedido;
    }

    public void setCodigoPedido(Long codigoPedido) {
        this.codigoPedido = codigoPedido;
    }

    public Set<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(Set<Produto> produtos) {
        this.produtos = produtos;
    }

    public Cliente getClientePedido() {
        return clientePedido;
    }

    public void setClientePedido(Cliente clientePedido) {
        this.clientePedido = clientePedido;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(String statusPedido) {
        this.statusPedido = statusPedido;
    }

    public BigDecimal getValorPedido() {
        return valorPedido;
    }

    public void setValorPedido(BigDecimal valorPedido) {
        this.valorPedido = valorPedido;
    }

    public String getFormaPagamentoPedido() {
        return formaPagamentoPedido;
    }

    public void setFormaPagamentoPedido(String formaPagamentoPedido) {
        this.formaPagamentoPedido = formaPagamentoPedido;
    }

    public Date getUltimaAtualizacao() {
        return ultimaAtualizacao;
    }

    public void setUltimaAtualizacao(Date ultimaAtualizacao) {
        this.ultimaAtualizacao = ultimaAtualizacao;
    }

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }
    
    public Set<Long> getIdProdutos() {
        return idProdutos;
    }

    public void setIdProdutos(Set<Long> idProdutos) {
        this.idProdutos = idProdutos;
    }
}
