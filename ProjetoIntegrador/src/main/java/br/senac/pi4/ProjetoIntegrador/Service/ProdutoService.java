
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.io.Serializable;
import java.util.List;


public interface ProdutoService extends Serializable {
    
    public List<Produto> listar(int offset, int quantidade);
    
    public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade);
    
    public Produto obter(Long codigoProduto);
    
    public void incluir(Produto produto);
    
    public void alterar(Produto produto);
    
    public void remover(Long codigoProduto);
    
    public List<Produto> pesquisar(String pesquisa);
}
