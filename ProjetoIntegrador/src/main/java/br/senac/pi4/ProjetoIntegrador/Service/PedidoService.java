
package br.senac.pi4.ProjetoIntegrador.Service;

import br.senac.pi4.ProjetoIntegrador.entity.Pedido;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


public interface PedidoService extends Serializable {
    
    public List<Pedido> listar(int offset, int quantidade);
       
    public Pedido obter(Long codigoPedido);
    
    public void incluir(Pedido pedido);
    
    public void alterar(Pedido pedido);
    
    public List<Pedido> pesquisarDatas(Date dataDe, Date dataAte);
            }

