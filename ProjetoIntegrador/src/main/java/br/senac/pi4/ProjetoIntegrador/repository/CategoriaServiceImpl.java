/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.CategoriaService;
import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joliveira
 */
@Repository
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private CategoriaRepository repo;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Categoria> listar() {
        Iterable<Categoria> categorias = repo.findAll();
        Iterator it = categorias.iterator();
        List<Categoria> lista = new ArrayList<Categoria>();
        while (it.hasNext()) {
            Categoria c = (Categoria) it.next();
            lista.add(c);
        }
        return lista;
    }
    
    @Override
    public Categoria obter(Long id) {
        return repo.findOne(id);
    }

    @Override
    public void incluir(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Categoria categoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Long codigoCategoria) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
