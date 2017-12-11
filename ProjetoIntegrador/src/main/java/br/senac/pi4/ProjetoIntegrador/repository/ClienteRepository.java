/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author eloisa.asilva2
 */
public interface ClienteRepository extends CrudRepository<Cliente, Long> {
    
}
