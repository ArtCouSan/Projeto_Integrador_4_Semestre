package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.ProdutoService;
import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ProdutoServiceImpl implements ProdutoService {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ProdutoRepository repo;

    @Override
    public List<Produto> listar(int offset, int quantidade) {
        Query query = entityManager.createQuery(
                "SELECT DISTINCT p FROM Produto p "
                + "LEFT JOIN FETCH p.categorias "
                + "LEFT JOIN FETCH p.imagens "
                + "WHERE en_produto = 1");
        return query.getResultList();
    }

    @Override
    public List<Produto> listarPorCategoria(Categoria categoria, int offset, int quantidade) {
        throw new UnsupportedOperationException(""); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Produto obter(Long idProduto) {
        return repo.findOne(idProduto);
    }

    @Override
    @Transactional
    public void incluir(Produto produto) {
        entityManager.persist(produto);
    }

    @Override
    @Transactional
    public void alterar(Produto produto) {
        entityManager.merge(produto);
    }

    @Override
    public List<Produto> pesquisar(String pesquisa) {
        Query query = entityManager.createQuery(
                "SELECT p FROM Produto p "
              + "WHERE p.tituloProduto LIKE :pesquisa")
                .setParameter("pesquisa", "%"+pesquisa+"%");
        return query.getResultList();
    }

    @Override
    public void remover(Long idProduto) {
        Query query = entityManager.createQuery(
                " UPDATE Produto p "
                + "SET EN_PRODUTO = 0 "
                + "WHERE p.id = :idProd")
                .setParameter("idProd", idProduto);
    }
}
