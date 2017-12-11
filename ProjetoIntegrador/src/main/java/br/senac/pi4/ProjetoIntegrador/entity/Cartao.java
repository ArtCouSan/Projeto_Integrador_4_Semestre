package br.senac.pi4.ProjetoIntegrador.entity;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

public class Cartao {
    
    @Size(min = 19, max = 19, message = "{cartao.numero.erro}")
    private String numero;
    
    @Size(min = 1, max = 100, message = "{cartao.nome.erro}")
    private String nome;
    
    @Size(min = 5, max = 5, message = "{cartao.vencimento.erro}")
    private String vencimento;
    
    @Size(min = 3, max = 3, message = "{cartao.codigo.erro}")
    private String codigo;
    
    @Min(value = 1)
    @Max(value = 12)
    @Digits(integer = 2, fraction = 0, message = "{cartao.parcelas.erro}")
    private int parcelas;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getVencimento() {
        return vencimento;
    }

    public void setVencimento(String vencimento) {
        this.vencimento = vencimento;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }
    
    
      
}
