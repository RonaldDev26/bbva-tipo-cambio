package com.bbva.ms.tipocambio.business.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorDetalle {
    
    private Date marcaDeTiempo;
    private String mensaje;
    private String detalles;
    
    public ErrorDetalle(Date marcaDeTiempo, String mensaje, String detalles) {
        super();
        this.marcaDeTiempo = marcaDeTiempo;
        this.mensaje = mensaje;
        this.detalles = detalles;
    }
    
}
