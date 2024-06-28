package com.proyecto_titulacion.assettrack.client;

import com.proyecto_titulacion.assettrack.dto.SucursalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class ClienteService {
    private final WebClient webClient;

    @Autowired
    public ClienteService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("lb://customer-branches-service/api/v1/customers").build();
    }

    public Mono<SucursalDTO> getSucursalById(Long sucursalId) {
        return webClient.get()
                .uri("/sucursales/{id}", sucursalId)
                .retrieve()
                .bodyToMono(SucursalDTO.class);
    }
}
