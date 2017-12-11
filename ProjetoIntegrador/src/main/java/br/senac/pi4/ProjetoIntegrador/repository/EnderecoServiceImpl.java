package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.EnderecoService;
import br.senac.pi4.ProjetoIntegrador.entity.Endereco;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class EnderecoServiceImpl implements EnderecoService {
    
    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private EnderecoRepository repo;


    @Override
    public Endereco obter(Long codigoEndereco) {
        return repo.findOne(codigoEndereco);
    }
    
    @Override
    @Transactional
    public Endereco incluir(Endereco endereco) {
        entityManager.persist(endereco);
        entityManager.flush();
        return endereco;
    }

    @Override
    @Transactional
    public void alterar(Endereco endereco) {
        entityManager.merge(endereco);
    }

    @Override
    public void remover(Integer codigoEndereco) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}