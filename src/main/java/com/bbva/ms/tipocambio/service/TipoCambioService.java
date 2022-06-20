package com.bbva.ms.tipocambio.service;

import com.bbva.ms.tipocambio.business.dto.TipoCambioDto;
import com.bbva.ms.tipocambio.model.TipoCambioMontoRequest;
import com.bbva.ms.tipocambio.model.TipoCambioRequest;
import reactor.core.publisher.Mono;

public interface TipoCambioService {
    
    Mono<TipoCambioDto> guardarTipoCambio(TipoCambioRequest tipoCambioRequest);
    
    Mono<TipoCambioDto> obtenerTipoCambioId(TipoCambioMontoRequest tipoCambioRequest , Long id);
    
    Mono<TipoCambioDto> obtenerTipoCambio(TipoCambioMontoRequest tipoCambioRequest);
    
    Mono<TipoCambioDto> actualizarTipoCambio(TipoCambioRequest tipoCambioRequest , Long id);
    
}
