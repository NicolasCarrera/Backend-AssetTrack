package com.proyecto_titulacion.assettrack;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class CustomerAssetsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerAssetsServiceApplication.class, args);
	}

}
