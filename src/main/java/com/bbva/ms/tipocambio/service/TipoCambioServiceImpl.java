package com.bbva.ms.tipocambio.service;

import com.bbva.ms.tipocambio.business.dto.TipoCambioDto;
import com.bbva.ms.tipocambio.business.entity.TipoCambioEntity;
import com.bbva.ms.tipocambio.business.repository.TipoCambioRepository;
import com.bbva.ms.tipocambio.exception.ResourceNotFoundException;
import com.bbva.ms.tipocambio.model.TipoCambioMontoRequest;
import com.bbva.ms.tipocambio.model.TipoCambioRequest;
import com.bbva.ms.tipocambio.util.TipoCambioUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

/**
 * Clase TipoCambioService.
 * @author Ronald Baron.
 * @version 1.0
 */
@Slf4j
@Service
public class TipoCambioServiceImpl implements TipoCambioService {
    
    @Autowired
    private TipoCambioRepository tipoCambioRespository;
    
    @Override
    public Mono<TipoCambioDto> guardarTipoCambio(TipoCambioRequest tipoCambioRequest) {
        log.info("TipoCambioServiceImpl.guardarTipoCambio");
        
        TipoCambioEntity tipoCambioEntity = TipoCambioUtil.mapperClass(TipoCambioEntity.class, tipoCambioRequest);
        
        TipoCambioDto dto = TipoCambioUtil.mapperClass(TipoCambioDto.class,
                tipoCambioRespository.save(tipoCambioEntity));
        
        return Mono.justOrEmpty(dto);
    }
    
    @Override
    public Mono<TipoCambioDto> obtenerTipoCambioId(TipoCambioMontoRequest tipoCambioMontoRequest, Long id) {
        log.info("TipoCambioServiceImpl.obtenerTipoCambio");
        
        TipoCambioEntity tipoCambioEntity = tipoCambioRespository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ID", "id", id));
        
        Mono<TipoCambioEntity> monTipoCambioEntity = Mono.just(tipoCambioEntity);
        
        return monTipoCambioEntity.flatMap(p -> {
            
            TipoCambioMontoRequest request = this.obtenerTipoCambioMontoRequest(tipoCambioMontoRequest);
            
            String monedaOrigen = request.getMonedaOrigen();
            String monedaDestino = request.getMonedaDestino();
            
            TipoCambioDto tipoCambioDto = new TipoCambioDto();
            tipoCambioDto.setTipoCambio(request.getMonto());
            
            if (tipoCambioEntity.getMonedaOrigen().equalsIgnoreCase(monedaOrigen)
                    && tipoCambioEntity.getMonedaDestino().equalsIgnoreCase(monedaDestino)) {
                // Calculo del monto con el tipo de cambio.
                tipoCambioDto.setMontoTipoCambio(request.getMonto() * p.getTipoCambio());
                tipoCambioDto.setTipoCambio(p.getTipoCambio());
            }
            
            tipoCambioDto.setMonedaOrigen(request.getMonedaOrigen());
            tipoCambioDto.setMonedaDestino(request.getMonedaDestino());
            
            return Mono.just(tipoCambioDto);
        });
        
    }
    
    @Override
    public Mono<TipoCambioDto> obtenerTipoCambio(TipoCambioMontoRequest tipoCambioMontoRequest) {
        log.info("TipoCambioServiceImpl.obtenerTipoCambio");
        
        TipoCambioEntity tipoCambioEntity = tipoCambioRespository.findByMonedaOrigenAndMonedaDestino(
                tipoCambioMontoRequest.getMonedaOrigen(), 
                tipoCambioMontoRequest.getMonedaDestino());
        
        Mono<TipoCambioEntity> monTipoCambioEntity = Mono.just(tipoCambioEntity);
        
        return monTipoCambioEntity.flatMap(p -> {
            
            TipoCambioMontoRequest request = this.obtenerTipoCambioMontoRequest(tipoCambioMontoRequest);
            
            String monedaOrigen = request.getMonedaOrigen();
            String monedaDestino = request.getMonedaDestino();
            
            TipoCambioDto tipoCambioDto = new TipoCambioDto();
            tipoCambioDto.setTipoCambio(request.getMonto());
            
            if (tipoCambioEntity.getMonedaOrigen().equalsIgnoreCase(monedaOrigen)
                    && tipoCambioEntity.getMonedaDestino().equalsIgnoreCase(monedaDestino)) {
                // Calculo del monto con el tipo de cambio.
                tipoCambioDto.setMontoTipoCambio(request.getMonto() * p.getTipoCambio());
                tipoCambioDto.setTipoCambio(p.getTipoCambio());
            }
            
            tipoCambioDto.setMonedaOrigen(request.getMonedaOrigen());
            tipoCambioDto.setMonedaDestino(request.getMonedaDestino());
            
            return Mono.just(tipoCambioDto);
        });
    }
    
    @Override
    public Mono<TipoCambioDto> actualizarTipoCambio(TipoCambioRequest tipoCambioRequest, Long id) {
       log.info("TipoCambioServiceImpl.actualizarTipoCambio");
       
       TipoCambioEntity tipoCambioEntity = tipoCambioRespository
               .findById(id)
               .orElseThrow(() -> new ResourceNotFoundException("ID", "id", id));
       
       Mono<TipoCambioEntity> monTipoCambioEntityActual = Mono.just(tipoCambioEntity);
       
       return monTipoCambioEntityActual.flatMap(p -> {
           
           p.setId(tipoCambioRequest.getId());
           p.setMonedaOrigen(tipoCambioRequest.getMonedaOrigen());
           p.setMonedaDestino(tipoCambioRequest.getMonedaDestino());
           p.setTipoCambio(tipoCambioRequest.getTipoCambio());
           
           tipoCambioRespository.save(p);
           
           TipoCambioDto tipoCambioDto = TipoCambioUtil.mapperClass(TipoCambioDto.class , p);
           
           return Mono.just(tipoCambioDto);
       });
   }
   
   private TipoCambioMontoRequest obtenerTipoCambioMontoRequest(TipoCambioMontoRequest request) {
       TipoCambioMontoRequest tipoCambioMontoRequest = new TipoCambioMontoRequest();
       tipoCambioMontoRequest.setMonto(request.getMonto());
       tipoCambioMontoRequest.setMonedaOrigen(request.getMonedaOrigen());
       tipoCambioMontoRequest.setMonedaDestino(request.getMonedaDestino());
       return tipoCambioMontoRequest;
   }
   
}
