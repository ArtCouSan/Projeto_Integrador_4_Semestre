/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.controller;

import br.senac.pi4.ProjetoIntegrador.Service.CategoriaService;
import br.senac.pi4.ProjetoIntegrador.Service.ProdutoService;
import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import br.senac.pi4.ProjetoIntegrador.entity.Imagem;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author jonat
 */
@Controller
@RequestMapping("/backoffice/produto")
public class BackofficeVisualizaProdutoController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private CategoriaService categoriaService;
    
    private boolean vazio = false;

    @RequestMapping
    public ModelAndView listar() {
        List<Produto> lista = service.listar(0, 100);
        return new ModelAndView("backoffice/produto/listaDeProdutos")
                .addObject("prod", lista)
                .addObject("vazio", vazio)
                .addObject("categorias", categoriaService.listar());
    }

    @RequestMapping(value = "/BackOfficePesquisa")
    public ModelAndView pesquisa(@ModelAttribute("pesquisa") String pesquisa) {
        List<Produto> produtos = service.pesquisar(pesquisa);

        if (produtos.isEmpty()) {
            vazio = true;

        } else {
            vazio = false;
        }
        return new ModelAndView("backoffice/produto/listaDeProdutos")
                .addObject("prod", produtos)
                .addObject("vazio", vazio);
    }

    @RequestMapping("/filtro/{idCat}")
    public ModelAndView listarPorCategoria(
            @PathVariable("idCat") Long idCategoria) {
        Categoria c = categoriaService.obter(idCategoria);
        List<Produto> lista = service.listarPorCategoria(c, 0, 100);
        return new ModelAndView("backoffice/produto/listaDeProdutos")
                .addObject("prod", lista)
                .addObject("categorias", categoriaService.listar());
    }

    @RequestMapping("/{id}")
    public ModelAndView detalhe(@PathVariable("id") Long idProduto) {
        Produto p = service.obter(idProduto);
        return new ModelAndView("backoffice/produto/detalheProduto")
                .addObject("produto", p)
                .addObject("categorias", p.getCategorias())
                .addObject("marca", p.getMarcaProduto())
                .addObject("imagens", p.getImagens());
    }
}
