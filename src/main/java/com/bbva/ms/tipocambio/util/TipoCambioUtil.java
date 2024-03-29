package com.bbva.ms.tipocambio.util;

import com.google.gson.Gson;

/**
 * Clase TipoCambioUtil.
 * @author Ronald Baron.
 * @version 1.0
 */
public class TipoCambioUtil {
    
    private TipoCambioUtil() {
    }
    
    /**
     * Convierte un objeto origen a un destino con campos con el mismo nombre.
     * @param destino la clase destino a convertir
     * @param origen objeto origen a transformar
     * @return el objeto de destino de la clase <code>destino</code>
     */
    public static <D> D mapperClass(Class<D> destino, Object origen) {
        try {
            if (origen == null) {
                return null;
            }
            Gson gson = new Gson();
            return gson.fromJson(gson.toJson(origen), destino);
        } catch (Exception e) {
            
        }
        return null;
    }
}