/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.senac.pi4.ProjetoIntegrador.controller;

import br.senac.pi4.ProjetoIntegrador.Service.ImagemService;
import br.senac.pi4.ProjetoIntegrador.Service.ProdutoService;
import br.senac.pi4.ProjetoIntegrador.entity.Imagem;
import br.senac.pi4.ProjetoIntegrador.entity.Produto;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/upload")
public class UploadController {
    
    @Autowired
    private ImagemService imagemService;
    
    @Autowired
    private ProdutoService prodService;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView salvar(
            @ModelAttribute("codigo") @Valid Long codigo,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes) {
        return new ModelAndView("backoffice/produto/cadastroDeImagens").addObject("codigo", codigo).addObject("imagem", new Imagem());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ModelAndView uploadArquivo(
            @RequestParam("arquivo") MultipartFile arquivo,
            RedirectAttributes redirectAttributes,
            @ModelAttribute("imagem") @Valid Imagem imagem) {
        if (arquivo.isEmpty()) {
            redirectAttributes.addFlashAttribute("mensagemErro",
                    "Erro ao carregar arquivo");
            return new ModelAndView("redirect:/upload").addObject("codigo", imagem.getIdProduto());
        }

        try {
            byte[] bytes = arquivo.getBytes();
            Path destino = Paths.get("C:/Imagens/"
                    + arquivo.getOriginalFilename());
            Files.write(destino, bytes);

            redirectAttributes.addFlashAttribute("mensagem",
                    "Arquivo " + arquivo.getOriginalFilename()
                    + " carregado com sucesso");
            imagem.setProduto(prodService.obter(imagem.getIdProduto()));
            imagemService.incluir(imagem);
            return new ModelAndView("redirect:/upload").addObject("codigo", imagem.getIdProduto());
        } catch (IOException ex) {
            redirectAttributes.addFlashAttribute("mensagemErro",
                    "Erro ao carregar arquivo - " + ex.getMessage());
            return new ModelAndView("redirect:/upload").addObject("codigo", imagem.getIdProduto());
        }
    }
}
