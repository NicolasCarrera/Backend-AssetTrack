package com.proyecto_titulacion.assettrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerBranchesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerBranchesServiceApplication.class, args);
	}

}
