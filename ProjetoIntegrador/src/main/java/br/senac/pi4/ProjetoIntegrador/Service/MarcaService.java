/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Marca;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author joliveira
 */
public interface MarcaService extends Serializable {
    
    public List<Marca> listar();
    
    public Marca obter(Long id);
    
    public void incluir(Marca marca);
    
    public void alterar(Marca marca);
    
    public void remover(Long codigoMarca);
}
