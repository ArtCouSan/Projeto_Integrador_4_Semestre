/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.controller;

import br.senac.pi4.ProjetoIntegrador.Service.CategoriaService;
import br.senac.pi4.ProjetoIntegrador.Service.ClienteService;
import br.senac.pi4.ProjetoIntegrador.Service.MarcaService;
import br.senac.pi4.ProjetoIntegrador.Service.ProdutoService;
import br.senac.pi4.ProjetoIntegrador.entity.Categoria;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author jonat
 */
@Controller
@RequestMapping("/gerenciamento/produto")
public class BackoficeGerenciaProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;
    

    @RequestMapping
    public ModelAndView abrirFormulario() {
        return new ModelAndView("backoffice/produto/cadastroDeProdutos")
                .addObject("produto", new Produto())
                .addObject("categorias", categoriaService.listar())
                .addObject("marcas", marcaService.listar());
    }

    @RequestMapping("/{id}")
    public ModelAndView abrirAlteracao(@PathVariable("id") Long idProduto) {
        Produto produto = produtoService.obter(idProduto);
        Set<Long> idCategorias = new LinkedHashSet<Long>();
        for (Categoria c : produto.getCategorias()) {
            idCategorias.add(c.getCodigoCategoria());
        }
        produto.setIdCategorias(idCategorias);
        return new ModelAndView("backoffice/produto/cadastroDeProdutos")
                .addObject("produto", produto)
                .addObject("categorias", categoriaService.listar())
                .addObject("marcas", marcaService.listar());
    }

    @RequestMapping(value = "/{id}/remover", method = RequestMethod.POST)
    public ModelAndView removerProduto(@PathVariable("id") Long idProduto) {
        Produto p = produtoService.obter(idProduto);
        p.setEnabledProduto(false);
        produtoService.alterar(p);
        return new ModelAndView("redirect:/backoffice/produto");
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public ModelAndView salvar(
            @ModelAttribute("produto") @Valid Produto p,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("backoffice/produto/cadastroDeProdutos")
                    .addObject("produto", p)
                    .addObject("categorias", categoriaService.listar())
                    .addObject("marcas", marcaService.listar());
        }
        boolean inclusao = (p.getCodigoProduto() == null);
        p.setEnabledProduto(true);
        p.setDtCadastroProduto(new Date());
        p.setMarcaProduto(marcaService.obter(p.getIdMarca()));

        if (p.getIdCategorias() != null && !p.getIdCategorias().isEmpty()) {
            Set<Categoria> categorias = new LinkedHashSet<Categoria>();
            for (Long i : p.getIdCategorias()) {
                Categoria c = categoriaService.obter(i);
                Set<Produto> produtos = new LinkedHashSet<Produto>();
                produtos.add(p);
                c.setProdutos(produtos);
                categorias.add(c);
            }
            p.setCategorias(categorias);
        }
        if (inclusao) {
            produtoService.incluir(p);
        } else {
            produtoService.alterar(p);
        }

        redirectAttributes.addFlashAttribute("msgSucesso",
                "Produto " + p.getTituloProduto() + " cadastrado com sucesso");
        return new ModelAndView("redirect:/backoffice/produto");
    }

}
