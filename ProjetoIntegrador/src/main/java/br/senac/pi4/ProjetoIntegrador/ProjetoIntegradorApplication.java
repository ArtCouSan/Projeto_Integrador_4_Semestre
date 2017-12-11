package br.senac.pi4.ProjetoIntegrador;

import javax.persistence.Persistence;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class ProjetoIntegradorApplication {

    public static void main(String[] args) {

        SpringApplication.run(ProjetoIntegradorApplication.class, args);

    }
}
