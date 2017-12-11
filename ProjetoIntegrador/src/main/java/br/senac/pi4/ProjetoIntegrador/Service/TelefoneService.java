/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Telefone;
import java.io.Serializable;

/**
 *
 * @author jonata.hmoliveira
 */
public interface TelefoneService extends Serializable {
    
    public Telefone obter(Long codigoTelefone);

    public void incluir(Telefone telefone);
    
    public void alterar(Telefone telefone);
    
    public void remover(Integer codigoTelefone);
}
