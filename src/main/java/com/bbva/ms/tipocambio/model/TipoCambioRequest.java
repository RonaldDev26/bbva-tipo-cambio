package com.bbva.ms.tipocambio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase TipoCambioRequest.
 * @author Ronald Baron.
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioRequest {
    
    private Long id;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
    private String usuario;
    
}