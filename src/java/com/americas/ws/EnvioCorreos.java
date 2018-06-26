/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.americas.ws;

import javax.jws.WebService;

/**
 *
 * @author omaalvca
 */
@WebService(serviceName = "ServicioCorreo", portName = "ServicioCorreoSoap", endpointInterface = "org.tempuri.ServicioCorreoSoap", targetNamespace = "http://tempuri.org/", wsdlLocation = "WEB-INF/wsdl/EnvioCorreos/americasapp.americasbps.com/WSCorreo/ServicioCorreo.asmx.wsdl")
public class EnvioCorreos {

    public java.lang.String enviar(java.lang.String de, java.lang.String para, java.lang.String cc, java.lang.String cco, java.lang.String asunto, java.lang.String mensaje, java.lang.String id) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }
    
}
