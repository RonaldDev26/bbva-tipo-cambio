package com.bbva.ms.tipocambio.expose;

import com.bbva.ms.tipocambio.business.dto.TipoCambioDto;
import com.bbva.ms.tipocambio.jwt.JwtTokenProvider;
import com.bbva.ms.tipocambio.model.JwtRequest;
import com.bbva.ms.tipocambio.model.JwtResponse;
import com.bbva.ms.tipocambio.model.TipoCambioMontoRequest;
import com.bbva.ms.tipocambio.model.TipoCambioRequest;
import com.bbva.ms.tipocambio.model.TipoCambioResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    
    @Autowired
    private AuthenticationManager authenticationManager;
    
    @Autowired
    private JwtTokenProvider jwtTokenUtil;
    
    @Autowired
    private UserDetailsService userDetailsService;
    
    @GetMapping("/")
    public Mono<String> getMensaje() {
        return Mono.just("Hola BBVA");
    }
    
	 /**
     * Metodo para registrar tipoCambio.
     */
    //@PreAuthorize("hasRole('ADMIN')")
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
     * Metodo para obtener monto por tipo de cambio Id.
     */
    @GetMapping(value = "/tipocambio/{id}", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
    public Mono<TipoCambioResponse> obtenerTipoCambioId(
            @RequestBody TipoCambioMontoRequest request ,@PathVariable Long id) {
        log.info("TipoCambioController.obtenerTipoCambioId");
        
        Mono<TipoCambioDto> tipoCambioDto = tipoCambioService.obtenerTipoCambioId(request, id);
        
        return tipoCambioDto.flatMap(p -> {
            TipoCambioResponse response = TipoCambioUtil.mapperClass(TipoCambioResponse.class, p);
            return Mono.just(response);
        });
    }
    
    /**
     * Metodo para obtener monto por tipo de cambio.
     * 
     */
    @GetMapping(value = "/tipocambio", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
    public Mono<TipoCambioResponse> obtenerTipoCambio(
            @RequestBody TipoCambioMontoRequest request) {
        log.info("TipoCambioController.obtenerTipoCambio");
        
        Mono<TipoCambioDto> tipoCambioDto = tipoCambioService.obtenerTipoCambio(request);
        
        return tipoCambioDto.flatMap(p -> {
            TipoCambioResponse response = TipoCambioUtil.mapperClass(TipoCambioResponse.class, p);
            return Mono.just(response);
        });
    }
    
     /**
     * Metodo para actualizar tipoCambio.
     */
    //@PreAuthorize("hasRole('ADMIN')")
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
    
	 /**
     * Metodo para obtener token.
     */
    @PostMapping(value = "/token", produces = TipoCambioConstantes.APPLICATION_JSON_UTF8_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest jwtRequest)
            throws Exception {
        log.info("TipoCambioController.createAuthenticationToken");
        authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
    
    private void authenticate(String username, String password) throws Exception {
        log.info("TipoCambioController.authenticate");
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            log.info("TipoCambioController.DisabledException");
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            log.info("TipoCambioController.BadCredentialsException");
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
    
}
