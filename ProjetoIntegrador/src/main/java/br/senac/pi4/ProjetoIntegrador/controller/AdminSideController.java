package br.senac.pi4.ProjetoIntegrador.controller;

import br.senac.pi4.ProjetoIntegrador.Service.ClienteService;
import br.senac.pi4.ProjetoIntegrador.entity.Cliente;
import br.senac.pi4.ProjetoIntegrador.entity.Endereco;
import br.senac.pi4.ProjetoIntegrador.entity.Pedido;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import br.senac.pi4.ProjetoIntegrador.entity.Telefone;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/admin")
public class AdminSideController {

    @Autowired
    private ClienteService clienteService;

    public Cliente sessaoCliente() {

        SecurityContext context = SecurityContextHolder.getContext();
        if (context instanceof SecurityContext) {

            Authentication authentication = context.getAuthentication();
            if (authentication instanceof Authentication) {

                String cpf = authentication.getName();

                Cliente cliente = clienteService.obterClienteByCPF(cpf);
                return cliente;
            }
        }

        return null;
    }

    @RequestMapping(value = "/perfil", method = RequestMethod.GET)
    public ModelAndView clientePefil() {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessao = request.getSession();
        Cliente cliente = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));
        List<Endereco> enderecos = cliente.getEnderecos();
        List<Telefone> telefones = cliente.getTelefones();
        boolean vazio = true;
        List<Pedido> pedidos = new ArrayList<>();

        if (cliente.getPedidos() != null) {

            pedidos = cliente.getPedidos();
            vazio = false;

            if (pedidos.isEmpty()) {

                vazio = true;
            }

        }

        return new ModelAndView("clientside/clientePerfil")
                .addObject("pedidos", pedidos)
                .addObject("vazio", vazio)
                .addObject("cliente", cliente)
                .addObject("enderecos", enderecos)
                .addObject("telefones", telefones);
    }

    @RequestMapping(value = "/checkoutPagamento", method = RequestMethod.GET)
    public String clienteCheckoutPagamento() {
        return "clientside/checkoutPagamento";
    }

    @RequestMapping(value = "/checkoutConfirmacao", method = RequestMethod.GET)
    public String clienteCheckoutConfirmacao() {
        return "clientside/checkoutConfirmacao";
    }
}
