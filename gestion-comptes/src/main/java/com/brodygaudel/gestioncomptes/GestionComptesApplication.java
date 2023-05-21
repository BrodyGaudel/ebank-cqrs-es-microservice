package com.brodygaudel.gestioncomptes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@EnableFeignClients
@SpringBootApplication
public class GestionComptesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionComptesApplication.class, args);
    }

}
