/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jonat
 */
public interface CategoriaService extends Serializable {
    
    public List<Categoria> listar();
    
    public Categoria obter(Long id);
    
    public void incluir(Categoria categoria);
    
    public void alterar(Categoria categoria);
    
    public void remover(Long codigoCategoria);
}
