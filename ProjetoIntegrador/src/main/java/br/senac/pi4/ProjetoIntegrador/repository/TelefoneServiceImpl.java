/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.TelefoneService;
import br.senac.pi4.ProjetoIntegrador.entity.Telefone;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonata.hmoliveira
 */
@Repository
public class TelefoneServiceImpl implements TelefoneService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private TelefoneRepository repo;

    @Override
    public Telefone obter(Long codigoTelefone) {
        return repo.findOne(codigoTelefone);
    }

    @Override
    @Transactional
    public void incluir(Telefone telefone) {
        entityManager.persist(telefone);
    }

    @Override
    @Transactional
    public void alterar(Telefone telefone) {
        entityManager.merge(telefone);
    }

    @Override
    public void remover(Integer codigoTelefone) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
