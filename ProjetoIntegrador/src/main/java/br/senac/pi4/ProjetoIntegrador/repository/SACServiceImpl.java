/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.SACService;
import br.senac.pi4.ProjetoIntegrador.Service.TelefoneService;
import br.senac.pi4.ProjetoIntegrador.entity.SAC;
import br.senac.pi4.ProjetoIntegrador.entity.Telefone;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SACServiceImpl implements SACService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SACRepository repo;

    @Override
    public SAC obter(Long codigoSAC) {
        return repo.findOne(codigoSAC);
    }
    
    @Override
    @Transactional
    public void incluir(SAC sac) {
        entityManager.persist(sac);
    }
    
    @Override
    @Transactional
    public void alterar(SAC sac) {
        entityManager.merge(sac);
    }

    @Override
    public void remover(Integer codigoSAC) {
    }

}
