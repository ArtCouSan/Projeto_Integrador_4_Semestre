/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.controller;

import br.senac.pi4.ProjetoIntegrador.Service.ClienteService;
import br.senac.pi4.ProjetoIntegrador.Service.PedidoService;
import br.senac.pi4.ProjetoIntegrador.Service.ProdutoService;
import br.senac.pi4.ProjetoIntegrador.entity.Cliente;
import br.senac.pi4.ProjetoIntegrador.entity.Pedido;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.text.DateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
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
 * @author eloisa.asilva2
 */
@Controller
@RequestMapping("/backoffice/pedido")
public class BackofficeVisualizaPedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private ProdutoService produtoService;

    @RequestMapping
    public ModelAndView listar() {
        List<Pedido> pedidos = pedidoService.listar(0, 100);
        return new ModelAndView("backoffice/pedido/listarPedidos")
                .addObject("pedidos", pedidos);

    }

    @RequestMapping("/detalhe/{id}")
    public ModelAndView abrirDetalhe(@PathVariable("id") Long idPedido) {
        Pedido p = pedidoService.obter(idPedido);
        return new ModelAndView("backoffice/pedido/detalhePedido").addObject("pedido", p)
                .addObject("cliente", p.getClientePedido()).addObject("produtos", p.getProdutos());
    }

    @RequestMapping(value = "/salvarStatus", method = RequestMethod.POST)
    public ModelAndView salvar(
            @ModelAttribute("pedido") @Valid Pedido p,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        Pedido pedidoEnvio = pedidoService.obter(p.getCodigoPedido());
        pedidoEnvio.setStatusPedido(p.getStatusPedido());
        pedidoEnvio.setUltimaAtualizacao(new Date());
        if (bindingResult.hasErrors()) {
            return new ModelAndView("backoffice/pedido/detalhePedido")
                    .addObject("pedido", pedidoEnvio);
        }

        pedidoService.alterar(pedidoEnvio);

        redirectAttributes.addFlashAttribute("msgSucesso",
                "Produto " + pedidoEnvio.getCodigoPedido() + " cadastrado com sucesso");
        return new ModelAndView("redirect:/backoffice/pedido");
    }
}
