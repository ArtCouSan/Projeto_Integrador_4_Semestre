package br.senac.pi4.ProjetoIntegrador.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BDController {
      
   @RequestMapping("/salvarPedido")
    public String Inserir() {
        

        return "clientside/clientePedidos";
    }
}
