package com.bbva.ms.tipocambio.business.repository;

import com.bbva.ms.tipocambio.business.entity.TipoCambioEntity;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoCambioRepository extends CrudRepository<TipoCambioEntity,Long> {
    
}