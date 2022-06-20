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
    
    //@NotEmpty(message = "El campo moneda de Origen no debe ser vacio o nulo")
    private String monedaOrigen;
    
    //@NotEmpty(message = "El campo moneda de Destino no debe ser vacio o nulo")
    private String monedaDestino;
}
