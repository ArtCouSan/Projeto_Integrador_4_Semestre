package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.ClienteService;
import br.senac.pi4.ProjetoIntegrador.entity.Cliente;
import br.senac.pi4.ProjetoIntegrador.entity.Pedido;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository
public class ClienteServiceImpl implements ClienteService {
    
    @Autowired
    private ClienteRepository repo;
    
    @PersistenceContext
    private EntityManager entityManager;
    
    @Override
    public List<Cliente> listar(int offset, int quantidade) {
        Iterable<Cliente> clientes = repo.findAll();
        Iterator it = clientes.iterator();
        List<Cliente> lista = new ArrayList<Cliente>();
        while (it.hasNext()) {
            Cliente c = (Cliente) it.next();
            lista.add(c);
        }
        return lista;
    }
    
    @Override
    public Cliente obter(Long codigoCliente) {
        return repo.findOne(codigoCliente);
    }
    
    @Override
    public Cliente obterClienteByCPF(String cpf) { 
        Query query = entityManager.createQuery("SELECT c FROM Cliente c WHERE c.cpfCliente = :cpf").setParameter("cpf", cpf);     
        Cliente cliente = (Cliente) query.getSingleResult();        
        if (cliente == null) {
            return null;
        }      
        return cliente;        
    }
    
    @Transactional
    @Override
    public Cliente incluir(Cliente cliente) {
        entityManager.persist(cliente);
        entityManager.flush();
        return cliente;
    }
    
    @Transactional
    @Override
    public void alterar(Cliente cliente) {
        entityManager.merge(cliente);
    }
    
    @Override
    public void remover(Long codigoCliente) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Cliente ultimoAdicionado() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
