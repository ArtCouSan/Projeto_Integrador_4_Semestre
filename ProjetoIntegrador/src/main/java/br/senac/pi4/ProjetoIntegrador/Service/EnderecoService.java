/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Endereco;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author jonat
 */
public interface EnderecoService extends Serializable {
    
    public Endereco obter(Long codigoEndereco);

    public Endereco incluir(Endereco produto);
    
    public void alterar(Endereco produto);
    
    public void remover(Integer codigoEndereco);
}

