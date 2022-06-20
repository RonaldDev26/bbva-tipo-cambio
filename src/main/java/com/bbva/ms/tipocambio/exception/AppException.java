package com.bbva.ms.tipocambio.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class AppException  extends RuntimeException {
    
    private static final long serialVersionUID = 1L;
    
    private HttpStatus estado;
    private String mensaje;
    
    public AppException(HttpStatus estado, String mensaje) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
    }
    
    public AppException(HttpStatus estado, String mensaje , String mensaje1) {
        super();
        this.estado = estado;
        this.mensaje = mensaje;
        this.mensaje = mensaje1;
    }
    
}
