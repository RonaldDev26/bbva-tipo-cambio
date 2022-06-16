package com.bbva.ms.tipocambio.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Clase TipoCambioMontoRequest.
 * @author Ronald Baron.
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoCambioMontoRequest {
    
    private Double monto;
    private String monedaOrigen;
    private String monedaDestino;
}
