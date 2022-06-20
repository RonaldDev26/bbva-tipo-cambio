package com.bbva.ms.tipocambio.business.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Clase TipoCambioDto.
 * @author Ronald Baron.
 * @version 1.0
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TipoCambioDto {
    
    private Long id;
    private String monedaOrigen;
    private String monedaDestino;
    private Double tipoCambio;
    private String usuario;
    private Double monto;
    private Double montoTipoCambio;
    
}
