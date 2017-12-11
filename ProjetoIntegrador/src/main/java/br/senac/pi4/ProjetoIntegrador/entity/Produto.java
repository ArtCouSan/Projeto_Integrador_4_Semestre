package br.senac.pi4.ProjetoIntegrador.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "TB_PRODUTO")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_PRODUTO")
    private Long codigoProduto;

    @Digits(integer = 6, fraction = 0, message = "{produto.qntCarrinho.erro}")
    @Column(name = "QTC_PRODUTO", length = 3, nullable = true)
    private int qntCarrinho;

    @NotBlank(message = "O título do produto é obrigatório!")
    @Size(max = 100, message = "O título deve conter no máximo 100 caractéres!")
    @Column(name = "TL_PRODUTO", length = 100, nullable = false)
    private String tituloProduto;

    @NotNull(message = "A quantidade em estoque do produto é obrigatória!")
    @Min(value = 1, message = "A quantidade em estoque do produto deve ser maior que zero!")
    @Digits(integer = 11, fraction = 0, message = "Quantidade em estoque inválida!")
    @Column(name = "QT_PRODUTO", nullable = false)
    private int quantEstoqueProduto;

    @NotNull(message = "A avaliação do produto é obrigatória!")
    @Min(value = 1, message = "A avaliação do produto deve ser maior que zero!")
    @Digits(integer = 11, fraction = 0, message = "Avaliação do produto inválida!")
    @Column(name = "AV_PRODUTO")
    private int avaliacaoProduto;

    @NotBlank(message = "O modelo do produto é obrigatório!")
    @Size(max = 50, message = "O modelo deve conter no máximo 50 caractéres!")
    @Column(name = "MD_PRODUTO", length = 50, nullable = false)
    private String modeloProduto;

    @NotBlank(message = "A cor do produto obrigatória!")
    @Size(max = 20, message = "A cor do produto deve conter no máximo 20 caractéres!")
    @Column(name = "CO_PRODUTO", length = 20, nullable = false)
    private String corProduto;

    @NotBlank(message = "O gênero do produto é obrigatório!")
    @Size(max = 10, message = "O gênero deve ser somente masculino ou feminino!")
    @Column(name = "GE_PRODUTO", length = 20, nullable = false)
    private String generoProduto;

    @NotNull(message = "O tamanho do produto é obrigatório!")
    @Column(name = "TM_PRODUTO", length = 5, nullable = false)
    private char tamanhoProduto;

    @NotBlank(message = "A cor da lente é obrigatória!")
    @Size(max = 20, message = "A cor da lente deve conter no máximo 20 caractéres!")
    @Column(name = "CL_PRODUTO", length = 20, nullable = false)
    private String lenteCorProduto;

    @NotBlank(message = "O material do produto é obrigatório!")
    @Size(max = 100, message = "O material do produto deve conter no máximo 100 caractéres!")
    @Column(name = "MT_PRODUTO", length = 20, nullable = false)
    private String materialProduto;

    @NotBlank(message = "O tipo da lente é obrigatório!")
    @Size(max = 100, message = "O tipo da lente deve conter no máximo 100 caractéres!")
    @Column(name = "LT_PRODUTO", length = 100, nullable = false)
    private String lenteTipoProduto;

    @NotBlank(message = "A descrição do produto é obrigatória!")
    @Size(max = 1000, message = "A descrição deve conter no máximo 1000 caractéres!")
    @Column(name = "DS_PRODUTO", length = 1000, nullable = false)
    private String descricaoProduto;

    @NotNull(message = "O preço do produto é obrigatório!")
    @DecimalMin(value = "0.01", message = "Preco do produto inválido!")
    @Min(value = 1, message = "O valor do produto deve ser maior que zero!")
    @Digits(integer = 6, fraction = 2, message = "Preço do produto inválido!")
    @Column(name = "VL_PRODUTO", precision = 6, scale = 2, nullable = false)
    private BigDecimal precoProduto;

    @Column(name = "PR_PRODUTO", nullable = false)
    private boolean promocaoProduto;

    //@Digits(integer = 6, fraction = 2, message = "{produto.precoProduto.erro}")
    //@Column(name = "VP_PRODUTO", precision = 6, scale = 2, nullable = false)
    //private int porcentagemPromocaoProduto;
    
    
    @Column(name = "DT_PRODUTO", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtCadastroProduto;

    @Column(name = "EN_PRODUTO", nullable = false)
    private Boolean enabledProduto;

    @DecimalMin(value = "0.1", message = "Altura do protuto inválida!")
    @Digits(integer = 6, fraction = 1, message = "Altura do produto inválida!")
    @Column(name = "AT_PRODUTO", precision = 10, scale = 2)
    private double alturaProduto;

    @DecimalMin(value = "0.1", message = "Largura do protuto inválida!")
    @Digits(integer = 6, fraction = 1, message = "Largura do produto inválida!")
    @Column(name = "LG_PRODUTO", precision = 10, scale = 2)
    private double larguraProduto;

    @DecimalMin(value = "0.1", message = "Comprimento do protuto inválido!")
    @Digits(integer = 6, fraction = 1, message = "Comprimento do produto inválido!")
    @Column(name = "CM_PRODUTO", precision = 10, scale = 2)
    private double comprimentoProduto;

    @DecimalMin(value = "0.1", message = "Peso do produto inválido!")
    @Digits(integer = 6, fraction = 1, message = "Peso do produto inválido!")
    @Column(name = "PS_PRODUTO", precision = 10, scale = 2)
    private double pesoProduto;

    @OneToMany(mappedBy = "produto", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<Imagem> imagens;

    @ManyToMany
    @JoinTable(name = "TB_PRODUTO_CATEGORIA",
            joinColumns = {
                @JoinColumn(name = "ID_PRODUTO")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "ID_CATEGORIA")
            })
    private Set<Categoria> categorias;

    @Transient
    private Set<Long> idCategorias;

    @Transient
    private Long idMarca;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ID_MARCA", nullable = false)
    private Marca marcaProduto;

    @Digits(integer = 11, fraction = 0, message = "{produto.quantEstoque.erro}")
    @Column(name = "CT_PRODUTO")
    private int contadorProduto;

    @ManyToMany(mappedBy = "produtos")
    private List<Pedido> pedidos;

    @Transient
    private boolean estoque;
    
    public Produto() {
    }

    public Produto(Long codigoProduto, int qntCarrinho, String tituloProduto, int quantEstoqueProduto, int avaliacaoProduto, String modeloProduto, String corProduto, String generoProduto, char tamanhoProduto, String lenteCorProduto, String materialProduto, String lenteTipoProduto, String descricaoProduto, BigDecimal precoProduto, boolean promocaoProduto, Date dtCadastroProduto, Boolean enabledProduto, double alturaProduto, double larguraProduto, double comprimentoProduto, double pesoProduto, List<Imagem> imagens, Set<Categoria> categorias, Set<Long> idCategorias, Long idMarca, Marca marcaProduto, int contadorProduto, List<Pedido> pedidos) {
        this.codigoProduto = codigoProduto;
        this.qntCarrinho = qntCarrinho;
        this.tituloProduto = tituloProduto;
        this.quantEstoqueProduto = quantEstoqueProduto;
        this.avaliacaoProduto = avaliacaoProduto;
        this.modeloProduto = modeloProduto;
        this.corProduto = corProduto;
        this.generoProduto = generoProduto;
        this.tamanhoProduto = tamanhoProduto;
        this.lenteCorProduto = lenteCorProduto;
        this.materialProduto = materialProduto;
        this.lenteTipoProduto = lenteTipoProduto;
        this.descricaoProduto = descricaoProduto;
        this.precoProduto = precoProduto;
        this.promocaoProduto = promocaoProduto;
        this.dtCadastroProduto = dtCadastroProduto;
        this.enabledProduto = enabledProduto;
        this.alturaProduto = alturaProduto;
        this.larguraProduto = larguraProduto;
        this.comprimentoProduto = comprimentoProduto;
        this.pesoProduto = pesoProduto;
        this.imagens = imagens;
        this.categorias = categorias;
        this.idCategorias = idCategorias;
        this.idMarca = idMarca;
        this.marcaProduto = marcaProduto;
        this.contadorProduto = contadorProduto;
        this.pedidos = pedidos;
    }

    public boolean isEstoque() {
        return estoque;
    }

    public void setEstoque(boolean estoque) {
        this.estoque = estoque;
    }
    
    public int getQntCarrinho() {
        return qntCarrinho;
    }

    public void setQntCarrinho(int qntCarrinho) {
        this.qntCarrinho = qntCarrinho;
    }

    public int getContadorProduto() {
        return contadorProduto;
    }

    public void setContadorProduto(int contadorProduto) {
        this.contadorProduto = contadorProduto;
    }

    public Long getCodigoProduto() {
        return codigoProduto;
    }

    public void setCodigoProduto(Long codigoProduto) {
        this.codigoProduto = codigoProduto;
    }

    public String getTituloProduto() {
        return tituloProduto;
    }

    public void setTituloProduto(String tituloProduto) {
        this.tituloProduto = tituloProduto;
    }

    public int getQuantEstoqueProduto() {
        return quantEstoqueProduto;
    }

    public void setQuantEstoqueProduto(int quantEstoqueProduto) {
        this.quantEstoqueProduto = quantEstoqueProduto;
    }

    public int getAvaliacaoProduto() {
        return avaliacaoProduto;
    }

    public void setAvaliacaoProduto(int avaliacaoProduto) {
        this.avaliacaoProduto = avaliacaoProduto;
    }

    public String getModeloProduto() {
        return modeloProduto;
    }

    public void setModeloProduto(String modeloProduto) {
        this.modeloProduto = modeloProduto;
    }

    public String getCorProduto() {
        return corProduto;
    }

    public void setCorProduto(String corProduto) {
        this.corProduto = corProduto;
    }

    public String getGeneroProduto() {
        return generoProduto;
    }

    public void setGeneroProduto(String generoProduto) {
        this.generoProduto = generoProduto;
    }

    public char getTamanhoProduto() {
        return tamanhoProduto;
    }

    public void setTamanhoProduto(char tamanhoProduto) {
        this.tamanhoProduto = tamanhoProduto;
    }

    public String getLenteCorProduto() {
        return lenteCorProduto;
    }

    public void setLenteCorProduto(String lenteCorProduto) {
        this.lenteCorProduto = lenteCorProduto;
    }

    public String getLenteTipoProduto() {
        return lenteTipoProduto;
    }

    public String getMaterialProduto() {
        return materialProduto;
    }

    public void setMaterialProduto(String materialProduto) {
        this.materialProduto = materialProduto;
    }

    public void setLenteTipoProduto(String lenteTipoProduto) {
        this.lenteTipoProduto = lenteTipoProduto;
    }

    public String getDescricaoProduto() {
        return descricaoProduto;
    }

    public void setDescricaoProduto(String descricaoProduto) {
        this.descricaoProduto = descricaoProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public void setPrecoProduto(BigDecimal precoProduto) {
        this.precoProduto = precoProduto;
    }

    public boolean isPromocaoProduto() {
        return promocaoProduto;
    }

    public void setPromocaoProduto(boolean promocaoProduto) {
        this.promocaoProduto = promocaoProduto;
    }

//    public BigDecimal getPorcentagemPromocaoProduto() {
//        return porcentagemPromocaoProduto;
//    }
//
//    public void setPorcentagemPromocaoProduto(BigDecimal porcentagemPromocaoProduto) {
//        this.porcentagemPromocaoProduto = porcentagemPromocaoProduto;
//    }
    public Date getDtCadastroProduto() {
        return dtCadastroProduto;
    }

    public void setDtCadastroProduto(Date dtCadastroProduto) {
        this.dtCadastroProduto = dtCadastroProduto;
    }

    public Boolean getEnabledProduto() {
        return enabledProduto;
    }

    public void setEnabledProduto(Boolean enabledProduto) {
        this.enabledProduto = enabledProduto;
    }

    public double getAlturaProduto() {
        return alturaProduto;
    }

    public void setAlturaProduto(double alturaProduto) {
        this.alturaProduto = alturaProduto;
    }

    public double getLarguraProduto() {
        return larguraProduto;
    }

    public void setLarguraProduto(double larguraProduto) {
        this.larguraProduto = larguraProduto;
    }

    public double getComprimentoProduto() {
        return comprimentoProduto;
    }

    public void setComprimentoProduto(double comprimentoProduto) {
        this.comprimentoProduto = comprimentoProduto;
    }

    public double getPesoProduto() {
        return pesoProduto;
    }

    public void setPesoProduto(double pesoProduto) {
        this.pesoProduto = pesoProduto;
    }

    public List<Imagem> getImagens() {
        return imagens;
    }

    public void setImagens(List<Imagem> imagens) {
        this.imagens = imagens;
    }

    public Set<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(Set<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Long> getIdCategorias() {
        return idCategorias;
    }

    public void setIdCategorias(Set<Long> idCategorias) {
        this.idCategorias = idCategorias;
    }

    public Long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Long idMarca) {
        this.idMarca = idMarca;
    }

    public Marca getMarcaProduto() {
        return marcaProduto;
    }

    public void setMarcaProduto(Marca marcaProduto) {
        this.marcaProduto = marcaProduto;
    }
}
