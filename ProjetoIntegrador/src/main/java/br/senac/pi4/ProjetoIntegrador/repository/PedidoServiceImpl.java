/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.PedidoService;
import br.senac.pi4.ProjetoIntegrador.entity.Pedido;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author thales.dsouza
 */
@Repository
public class PedidoServiceImpl implements PedidoService{
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Autowired
    private PedidoRepository repo;
    
    @Override
    public List<Pedido> listar(int offset, int quantidade) {
        Iterable<Pedido> pedidos = repo.findAll();
        Iterator it = pedidos.iterator();
        List<Pedido> lista = new ArrayList<Pedido>();
        while (it.hasNext()) {
            Pedido p = (Pedido) it.next();
            lista.add(p);
        }
        return lista;
    }
    
    @Override
    public Pedido obter(Long codigoPedido) {
        return repo.findOne(codigoPedido);
    }

    @Override
    @Transactional
    public void incluir(Pedido pedido) {
        entityManager.persist(pedido);
    }

    @Override
    @Transactional
    public void alterar(Pedido pedido) {
        entityManager.merge(pedido);
    }

    @Override
    public List<Pedido> pesquisarDatas(Date dataDe, Date dataAte) {
        Query query = entityManager.createQuery
        ("SELECT p FROM Pedido p WHERE p.dataPedido BETWEEN :startDate AND :endDate").setParameter("startDate", dataDe).setParameter("endDate", dataAte);
        List<Pedido> pedidos = query.getResultList();
        if (pedidos == null) {
            return null;
        }
        return pedidos;
    }
}
