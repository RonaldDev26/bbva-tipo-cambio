package com.bbva.ms.tipocambio.expose;

import com.bbva.ms.tipocambio.business.dto.TipoCambioDto;
import com.bbva.ms.tipocambio.model.TipoCambioMontoRequest;
import com.bbva.ms.tipocambio.model.TipoCambioRequest;
import com.bbva.ms.tipocambio.model.TipoCambioResponse;
import org.springframework.http.HttpStatus;
import com.bbva.ms.tipocambio.service.TipoCambioService;
import com.bbva.ms.tipocambio.util.TipoCambioConstantes;
import com.bbva.ms.tipocambio.util.TipoCambioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseStatus;

import reactor.core.publisher.Mono;

/**
 * TipoCambioController.
 * @author Ronald Baron.
 * @version 1.0
 */
@Slf4j
@RestController
@RequestMapping("/api")
public class TipoCambioController {
    
    @Autowired
    private TipoCambioService tipoCambioService;
    
    @GetMapping("/")
    public Mono<String> getMensaje() {
        return Mono.just("Hola BBVA");
    }
    
	 /**
     * Metodo para registrar tipoCambio.
     */
    @PostMapping(value = "/tipocambio", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TipoCambioResponse> registrarTipoCambio(@RequestBody TipoCambioRequest request) {
        log.info("TipoCambioController.registrarTipoCambio");
        
        Mono<TipoCambioDto> tipoCambioDto = tipoCambioService.guardarTipoCambio(request);
        
        return tipoCambioDto.flatMap(p -> {
            TipoCambioResponse response = TipoCambioUtil.mapperClass(TipoCambioResponse.class, p);
            return Mono.just(response);
        });
    }
    
    /**
     * Metodo para obtener monto por tipo de cambio.
     */
    @GetMapping(value = "/tipocambio/{id}", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
    public Mono<TipoCambioResponse> obtenerTipoCambio(
            @RequestBody TipoCambioMontoRequest request ,@PathVariable Long id) {
        log.info("TipoCambioController.obtenerTipoCambio");
        
        Mono<TipoCambioDto> tipoCambioDto = tipoCambioService.obtenerTipoCambio(request, id);
        
        return tipoCambioDto.flatMap(p -> {
            TipoCambioResponse response = TipoCambioUtil.mapperClass(TipoCambioResponse.class, p);
            return Mono.just(response);
        });
    }
    
     /**
     * Metodo para actualizar tipoCambio.
     */
    @PutMapping(value = "/tipocambio/{id}", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<TipoCambioResponse> actualizarTipoCambio(
            @RequestBody TipoCambioRequest request ,@PathVariable Long id) {
        log.info("TipoCambioController.actualizarTipoCambio");
        
        Mono<TipoCambioDto> tipoCambioDto = tipoCambioService.actualizarTipoCambio(request, id);
        
        return tipoCambioDto.flatMap(p -> {
            TipoCambioResponse response = TipoCambioUtil.mapperClass(TipoCambioResponse.class, p);
            return Mono.just(response);
        });
    }
}
