package com.proyecto_titulacion.assettrack.util.feign;

import feign.FeignException;
import java.util.function.Supplier;

public class FeignUtil {
    public static <T> T safeFeignCall(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (FeignException e) {
            // Manejar el error de Feign (por ejemplo, servicio no disponible)
            if (e.status() == 503) {
                return null; // Retornar nulo si el servicio no está disponible
            }
            throw e; // Lanza la excepción si es un error diferente
        }
    }
}
