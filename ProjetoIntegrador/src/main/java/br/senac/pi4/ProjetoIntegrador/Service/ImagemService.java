/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import br.senac.pi4.ProjetoIntegrador.entity.Imagem;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author thales.dsouza
 */
public interface ImagemService extends Serializable {
    
    public List<Imagem> listar(int offset, int quantidade);
    
    public List<Imagem> listarPorCategoria(Categoria categoria, int offset, int quantidade);
    
    public Imagem obter(Long codigoImagem);
    
    public List<Imagem> obterCodigoProduto(Long codigoProduto);
    
    public void incluir(Imagem imagem);
    
    public void alterar(Imagem imagem);
    
    public void remover(Integer codigoImagem);
}
