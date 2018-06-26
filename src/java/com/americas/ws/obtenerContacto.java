/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.americas.ws;

import javax.jws.WebService;
import org.datacontract.schemas._2004._07.ws_famisanar.Contacto;

/**
 *
 * @author omaalvca
 */
@WebService(serviceName = "WSContacto", portName = "BasicHttpBinding_IContacto", endpointInterface = "org.tempuri.IContacto", targetNamespace = "http://tempuri.org/", wsdlLocation = "WEB-INF/wsdl/obtenerContacto/serverweb1.dmasterdorado.net/WS_Famisanar/WSContacto.svc.wsdl")
public class obtenerContacto {

    public Contacto obtenerContacto(java.lang.String identificacion, java.lang.String numeroIdentificacion) {
        //TODO implement this method
        Contacto Client = new Contacto();
        
        org.tempuri.WSContacto Service = new org.tempuri.WSContacto();
        org.tempuri.IContacto Port = Service.getBasicHttpBindingIContacto();
        
        Client = Port.obtenerContacto(identificacion, numeroIdentificacion);
        
        return Client;
    }

    public java.lang.String obtenerContactoIVR(java.lang.String identificacion, java.lang.String numeroIdentificacion) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfstring obtenerContactoIPS(java.lang.String idIPS) {
        //TODO implement this method
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    public org.datacontract.schemas._2004._07.ws_famisanar.Contacto obtenerContactoChat(java.lang.String tipoIdentificacion, java.lang.String numeroIdentificacion, java.lang.String nombre, java.lang.String apellido, java.lang.String email, java.lang.String numContacto) {
        //TODO implement this method
        Contacto Client = new Contacto();
        
        org.tempuri.WSContacto Service = new org.tempuri.WSContacto();
        org.tempuri.IContacto Port = Service.getBasicHttpBindingIContacto();
        
        Client = Port.obtenerContactoChat(tipoIdentificacion, numeroIdentificacion, nombre, apellido, email, numContacto);
        
        return Client;
    
    }
    
}
