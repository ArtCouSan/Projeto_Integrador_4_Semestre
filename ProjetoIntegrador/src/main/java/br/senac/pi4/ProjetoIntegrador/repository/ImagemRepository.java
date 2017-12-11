
package br.senac.pi4.ProjetoIntegrador.repository;
import br.senac.pi4.ProjetoIntegrador.entity.Imagem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joliveira
 */
@Repository
public interface ImagemRepository extends CrudRepository<Imagem, Long> {

}
