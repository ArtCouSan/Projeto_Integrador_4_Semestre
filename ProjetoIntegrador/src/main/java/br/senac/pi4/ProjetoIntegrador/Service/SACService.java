
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.SAC;
import java.io.Serializable;

public interface SACService extends Serializable{
    
    public SAC obter(Long codigoSAC);

    public void incluir(SAC sac);
    
    public void alterar(SAC sac);
    
    public void remover(Integer codigoSAC);
    
}
