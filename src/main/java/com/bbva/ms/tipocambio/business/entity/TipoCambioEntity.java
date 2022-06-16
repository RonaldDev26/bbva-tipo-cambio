package com.bbva.ms.tipocambio.business.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

/**
 * Clase TipoCambioEntity.
 * @author Ronald Baron.
 * @version 1.0
 */
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tipocambio")
public class TipoCambioEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "num_id")
    private Long id;
    
    @Column(name = "mon_origen")
    private String monedaOrigen;
    
    @Column(name = "mon_destino")
    private String monedaDestino;
    
    @Column(name = "tip_cambio")
    private Double tipoCambio;
    
    @Column(name = "usuario")
    private String usuario;
    
}

