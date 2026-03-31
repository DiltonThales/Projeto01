package com.fai.aprendendospring;

import com.fai.aprendendospring.infrastructure.entity.Usuario;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AprendendoSpringApplication {

	public static void main(String[] args)  {
		SpringApplication.run(AprendendoSpringApplication.class, args);

//		Usuario usuario = new Usuario("João","joao@gmail.com","1584848");
//		System.out.println(usuario.getNome());
//		usuario.setNome("Abigail");
//		System.out.println(usuario.getNome());

	}

}
