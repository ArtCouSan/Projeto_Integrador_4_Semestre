/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.repository;

import br.senac.pi4.ProjetoIntegrador.Service.MarcaService;
import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import br.senac.pi4.ProjetoIntegrador.entity.Marca;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author joliveira
 */
@Repository
public class MarcaServiceImpl implements MarcaService {

    @Autowired
    private MarcaRepository repoMarca;

    @Override
    public List<Marca> listar() {
        Iterable<Marca> marcas = repoMarca.findAll();
        Iterator it = marcas.iterator();
        List<Marca> lista = new ArrayList<Marca>();
        while (it.hasNext()) {
            Marca m = (Marca) it.next();
            lista.add(m);
        }
        return lista;
    }

    @Override
    public Marca obter(Long id) {
        Marca marca = repoMarca.findOne(id);
        
        return marca;
    }

    @Override
    public void incluir(Marca marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void alterar(Marca marca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void remover(Long codigoMarca) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
