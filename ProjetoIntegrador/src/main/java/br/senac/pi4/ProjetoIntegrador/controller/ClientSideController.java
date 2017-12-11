package br.senac.pi4.ProjetoIntegrador.controller;

import br.senac.pi4.ProjetoIntegrador.Service.ClienteService;
import br.senac.pi4.ProjetoIntegrador.Service.EnderecoService;
import br.senac.pi4.ProjetoIntegrador.Service.SACService;
import br.senac.pi4.ProjetoIntegrador.Service.TelefoneService;
import br.senac.pi4.ProjetoIntegrador.entity.Cliente;
import br.senac.pi4.ProjetoIntegrador.entity.Endereco;
import br.senac.pi4.ProjetoIntegrador.entity.Imagem;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import br.senac.pi4.ProjetoIntegrador.entity.SAC;
import br.senac.pi4.ProjetoIntegrador.entity.Telefone;
import br.senac.pi4.ProjetoIntegrador.repository.CategoriaServiceImpl;
import br.senac.pi4.ProjetoIntegrador.repository.ImagemServiceImpl;
import br.senac.pi4.ProjetoIntegrador.repository.ProdutoServiceImpl;
import br.senac.pi4.ProjetoIntegrador.repository.SACServiceImpl;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Scope("session")
public class ClientSideController {

    @Autowired
    private CategoriaServiceImpl serviceCategoria;

    @Autowired
    private ImagemServiceImpl serviceImagem;

    @Autowired
    private ProdutoServiceImpl serviceProduto;

    @Autowired
    private SACServiceImpl serviceSAC;

    @Autowired
    private SACService sacService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private TelefoneService telefoneService;

    private Long idCliente;

    public Long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Long idCliente) {
        this.idCliente = idCliente;
    }

    /**
     * Armazenamento de cliente para cadastro *
     */
    private Cliente clienteCadastro;

    private Telefone telefoneCadastro;

    private Endereco enderecoCadastro;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView home() {
        List<Produto> produtos = serviceProduto.listar(0, 100);
        List<Imagem> imagens = new ArrayList<>();

        boolean vazio = false;

        if (produtos.isEmpty()) {
            vazio = true;
        }

        for (Produto p : produtos) {
            if (p.getQuantEstoqueProduto() == 0) {
                p.setEstoque(false);
            } else {
                p.setEstoque(true);
            }
            List<Imagem> img = p.getImagens();
            for (Imagem i : img) {
                if (i.getSequenciaImagem() == 1) {
                    i.setIdProduto(p.getCodigoProduto());
                    imagens.add(i);
                }
            }
        }

        return new ModelAndView("clientside/home")
                .addObject("produtos", produtos)
                .addObject("imagens", imagens)
                .addObject("vazio", vazio);
    }

    @RequestMapping(value = "/sobreEmpresa", method = RequestMethod.GET)
    public ModelAndView sobreEmpresa() {
        return new ModelAndView("clientside/sobreEmpresa");
    }
    
    @RequestMapping(value = "/sac", method = RequestMethod.GET)
    public ModelAndView sac() {
        return new ModelAndView("clientside/clienteSAC").addObject("sac", new SAC());
    }

    @RequestMapping(value = "/sacAdd", method = RequestMethod.POST)
    public ModelAndView cadastrarSAC(
            @ModelAttribute(value = "sac") @Valid SAC sac, BindingResult sacR,
            RedirectAttributes attributes) {

        if (sacR.hasErrors()) {
            return new ModelAndView("clientside/clienteSAC");
        }

        sacService.incluir(sac);

        attributes.addFlashAttribute("mensagem", "Enviado com sucesso");
        return new ModelAndView("redirect:/sac");
    }

    @RequestMapping(value = "/pesquisa")
    public ModelAndView pesquisa(@ModelAttribute("pesquisa") String pesquisa) {
        List<Produto> produtos = serviceProduto.pesquisar(pesquisa);
        List<Imagem> imagens = new ArrayList<>();
        boolean vazio = false;

        if (produtos.isEmpty()) {
            vazio = true;
        } else {

            for (Produto p : produtos) {
                if (p.getQuantEstoqueProduto() == 0) {
                    p.setEstoque(false);
                } else {
                    p.setEstoque(true);
                }
                List<Imagem> img = p.getImagens();
                for (Imagem i : img) {
                    if (i.getSequenciaImagem() == 1) {
                        i.setIdProduto(p.getCodigoProduto());
                        imagens.add(i);
                    }
                }
            }
        }
        return new ModelAndView("clientside/home")
                .addObject("produtos", produtos)
                .addObject("imagens", imagens)
                .addObject("vazio", vazio);
    }

    @RequestMapping(value = "/descricao/{id}")
    public ModelAndView descricao(@PathVariable("id") Long idProduto) {
        Produto produto = serviceProduto.obter(idProduto);
        List<Imagem> imagens = produto.getImagens();

        if (produto.getQuantEstoqueProduto() == 0) {
            produto.setEstoque(false);
        } else {
            produto.setEstoque(true);
        }

        return new ModelAndView("clientside/descricao")
                .addObject("produto", produto).addObject("imagens", imagens);
    }

    @RequestMapping(value = "/cadastroC")
    public ModelAndView cadastroC() {
        return new ModelAndView("clientside/clienteCadastro")
                .addObject("cliente", new Cliente());
    }

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

    @RequestMapping(value = "/salvarCliente", method = RequestMethod.POST)
    public ModelAndView cadastrarCliente(
            @ModelAttribute(value = "cliente") @Valid Cliente cliente, BindingResult clienteR,
            RedirectAttributes attributes) {

        boolean inclusao = (cliente.getCodigoCliente() == null);

        if (clienteR.hasErrors()) {
            return new ModelAndView("clientside/clienteCadastro");
        }

        if (inclusao) {
            cliente.setRoleCliente("ROLE_JOSELITO");
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            String hashedPassword = passwordEncoder.encode(cliente.getSenhaCliente());
            cliente.setSenhaCliente(hashedPassword);
            cliente.setEnabled(true);
            clienteCadastro = clienteService.incluir(cliente);
        } else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            HttpSession sessao = request.getSession();
            Cliente clienteAlt = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));
            cliente.setSenhaCliente(clienteAlt.getSenhaCliente());
            cliente.setEnabled(true);
            cliente.setRoleCliente("ROLE_JOSELITO");
            clienteService.alterar(cliente);
        }

        if (inclusao) {
            return new ModelAndView("clientside/clienteCadastroTelefone")
                    .addObject("telefone", new Telefone());
        } else {
            attributes.addFlashAttribute("mensagem", "Cliente alterado com sucesso");
            return new ModelAndView("redirect:/admin/perfil");

        }

    }

    @RequestMapping(value = "/novoCliente/telefone", method = RequestMethod.POST)
    public ModelAndView cadastrarTelefoneCliente(
            @ModelAttribute(value = "telefone") @Valid Telefone telefone, BindingResult bindingResult,
            RedirectAttributes attributes) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessao = request.getSession();
        Cliente cliente = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));

        boolean inclusao = (telefone.getCodigoTelefone() == null);

        if (bindingResult.hasErrors()) {
            if (inclusao) {
                return new ModelAndView("clientside/clienteCadastroTelefone");
            } else {
                return new ModelAndView("clientside/clientePerfil");
            }

        }
        if (inclusao) {
            telefone.setClienteTelefone(clienteCadastro);
            telefoneService.incluir(telefone);
        } else {
            telefone.setClienteTelefone(cliente);
            telefoneService.alterar(telefone);
        }

        if (inclusao) {
            return new ModelAndView("clientside/clienteCadastroEndereco")
                    .addObject("endereco", new Endereco());
        } else {
            ModelAndView mv = new ModelAndView("redirect:/admin/perfil");
            attributes.addFlashAttribute("mensagem", "Telefones alterados com sucesso");
            return mv;
        }
    }

    @RequestMapping(value = "/novoCliente/endereco", method = RequestMethod.POST)
    public ModelAndView cadastrarEnderecoCliente(
            @ModelAttribute(value = "endereco") @Valid Endereco endereco, BindingResult bindingResult,
            RedirectAttributes attributes) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessao = request.getSession();
        Cliente cliente = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));

        boolean inclusao = (endereco.getCodigoEndereco() == null);

        if (bindingResult.hasErrors()) {
            if (inclusao) {
                return new ModelAndView("clientside/clienteCadastroEndereco");
            } else {
                return new ModelAndView("clientside/clientePerfil");
            }

        }
        if (inclusao) {
            endereco.setClienteEnderecos(clienteCadastro);
            enderecoCadastro = enderecoService.incluir(endereco);
        } else {
            endereco.setClienteEnderecos(cliente);
            enderecoService.alterar(endereco);
        }

        if (inclusao) {
            return new ModelAndView("redirect:/finalizarCadastro");
        } else {
            ModelAndView mv = new ModelAndView("redirect:/admin/perfil");
            attributes.addFlashAttribute("mensagem", "Enderecos alterados com sucesso");
            return mv;
        }
    }

    @RequestMapping(value = "/finalizarCadastro")
    public ModelAndView finalizarCadastro(RedirectAttributes redirectAttributes) {

        clienteService.alterar(clienteCadastro);

        redirectAttributes.addFlashAttribute("mensagem", "Cliente cadastrado com sucesso");
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/login")

    public String login() {
        return "clientside/login";
    }

    @RequestMapping("/login-error")
    public ModelAndView loginError() {
        return new ModelAndView("clientside/login").addObject("erro", true);
    }

    @RequestMapping("/editDados")
    public ModelAndView abrirDados() throws ParseException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessao = request.getSession();

        Cliente cliente = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));

        return new ModelAndView("clientside/clienteCadastro").addObject("cliente", cliente);
    }

    @RequestMapping("/editDadosEndereco")
    public ModelAndView abrirEndereco() throws ParseException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessao = request.getSession();

        Cliente cliente = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));

        List<Endereco> enderecos = cliente.getEnderecos();

        return new ModelAndView("clientside/clienteCadastroEndereco").addObject("endereco", enderecos.get(0));
    }

    @RequestMapping("/editDadosTelefone")
    public ModelAndView abrirTelefone() throws ParseException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession sessao = request.getSession();

        Cliente cliente = clienteService.obter((Long) sessao.getAttribute("idDoCliente"));

        List<Telefone> telefones = cliente.getTelefones();

        return new ModelAndView("clientside/clienteCadastroTelefone").addObject("telefone", telefones.get(0));
    }

}
