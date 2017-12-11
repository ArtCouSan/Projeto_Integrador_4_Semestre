package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "TB_CLIENTE")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CLIENTE")
    private Long codigoCliente;

    @Size(min = 1, message = "{cliente.emailCliente.erro}")
    @Column(name = "EM_CLIENTE", length = 100, nullable = false)
    private String emailCliente;

    @Column(name = "PW_CLIENTE", length = 200, nullable = false)
    private String senhaCliente;

    @CPF
    @Size(min = 14, max = 14, message = "{cliente.cpfCliente.erro}")
    @Column(name = "CPF_CLIENTE", length = 14, nullable = false, unique = true)
    private String cpfCliente;

    @Size(min = 10, max = 10, message = "{cliente.nascCliente.erro}")
    @Column(name = "NS_CLIENTE", length = 10, nullable = false)
    private String nascCliente;

    @OrderBy(value = "dataPedido")
    @OneToMany(mappedBy = "clientePedido", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "clienteEnderecos", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Endereco> enderecos;

    @OneToMany(mappedBy = "clienteTelefone", fetch = FetchType.LAZY, cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE})
    private List<Telefone> telefones;

    @Size(min = 1, message = "{cliente.nomeCompletoCliente.erro}")
    @Column(name = "NC_CLIENTE", length = 255, nullable = false)
    private String nomeCompletoCliente;

    @Size(min = 1)
    @Column(name = "SX_CLIENTE", length = 50, nullable = false)
    private String sexoCliente;

    @Size(min = 1, message = "{cliente.apelidoCliente.erro}")
    @Column(name = "AP_CLIENTE", length = 50, nullable = false)
    private String apelidoCliente;

    @Column(name = "RO_CLIENTE", length = 13, nullable = true)
    private String roleCliente;

    @Transient
    private List<Papel> papeis;

    @Column(name = "EN_CLIENTE", nullable = true)
    private Boolean enabled;

    public Cliente() {
    }

    public Cliente(Long codigoCliente, String emailCliente, String senhaCliente, String cpfCliente, String nascCliente, List<Pedido> pedidos, List<Endereco> enderecos, List<Telefone> telefones, String nomeCompletoCliente, String sexoCliente, String apelidoCliente, String roleCliente, List<Papel> papeis, Boolean enabled) {
        this.codigoCliente = codigoCliente;
        this.emailCliente = emailCliente;
        this.senhaCliente = senhaCliente;
        this.cpfCliente = cpfCliente;
        this.nascCliente = nascCliente;
        this.pedidos = pedidos;
        this.enderecos = enderecos;
        this.telefones = telefones;
        this.nomeCompletoCliente = nomeCompletoCliente;
        this.sexoCliente = sexoCliente;
        this.apelidoCliente = apelidoCliente;
        this.roleCliente = roleCliente;
        this.papeis = papeis;
        this.enabled = enabled;
    }

    public Long getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(Long codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getSenhaCliente() {
        return senhaCliente;
    }

    public void setSenhaCliente(String senhaCliente) {
        this.senhaCliente = senhaCliente;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public String getNascCliente() {
        return nascCliente;
    }

    public void setNascCliente(String nascCliente) {
        this.nascCliente = nascCliente;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Endereco> getEnderecos() {
        return enderecos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public List<Telefone> getTelefones() {
        return telefones;
    }

    public void setTelefones(List<Telefone> telefones) {
        this.telefones = telefones;
    }

    public String getNomeCompletoCliente() {
        return nomeCompletoCliente;
    }

    public void setNomeCompletoCliente(String nomeCompletoCliente) {
        this.nomeCompletoCliente = nomeCompletoCliente;
    }

    public String getSexoCliente() {
        return sexoCliente;
    }

    public void setSexoCliente(String sexoCliente) {
        this.sexoCliente = sexoCliente;
    }

    public String getApelidoCliente() {
        return apelidoCliente;
    }

    public void setApelidoCliente(String apelidoCliente) {
        this.apelidoCliente = apelidoCliente;
    }

    public String getRoleCliente() {
        return roleCliente;
    }

    public void setRoleCliente(String roleCliente) {
        this.roleCliente = roleCliente;
    }

    public List<Papel> getPapeis() {
        return papeis;
    }

    public void setPapeis(List<Papel> papeis) {
        this.papeis = papeis;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
    
}
