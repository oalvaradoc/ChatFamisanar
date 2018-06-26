/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.americas.log;

import com.americas.conexion.conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author omaalvca
 */

public class RegistroError {
    
    Connection Con = null;
    CallableStatement proc = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    
    public void Page_Error(String MensageError,String Metodo, String Proceso,Exception e)
    {
        try{
            Con = new conexion().getConnection();
            
            String qry = "{call Crm_SpInsertaLogError(?,?,?,?)}";
                    proc = Con.prepareCall(qry);
                    proc.setString(1, MensageError);
                    proc.setString(2, Metodo);
                    proc.setString(3, Proceso);
                    proc.setString(4, getStackTraceString(e));
                    proc.executeUpdate();
            Con.close();
        }
        catch(SQLException ex)
        {
            System.out.print("Error: " + ex);
        }    
        //Envia Correo - Consulta Informacón de Envio
        String[] consultaDatosCorreo = MailError();
        //Consumir el WS de envio de correo
        System.out.print("Envio de Correo de Error " + WsError(consultaDatosCorreo,MensageError,e,Metodo,Proceso));
        
    }
    
    public String WsError(String[] DatosCorreo, String MensageError, Exception e, String Metodo,String Proceso)
    {
        java.lang.String Respuesta="";
        try {
                org.tempuri.ServicioCorreo Service = new org.tempuri.ServicioCorreo();
                org.tempuri.ServicioCorreoSoap Port = Service.getServicioCorreoSoap();
                Respuesta = Port.enviar(
                DatosCorreo[0], 
                DatosCorreo[1], 
                "", 
                "",
                "Error En FamiSanar Chat",
                "<b>DETALLE DEL ERROR </b> <br/> " +
                "Metodo: "+ Metodo + "<br/>" +
                "Proceso: "+ Proceso + "<br/>" +
                "Descripción: "+ MensageError + "<br/>" +
                "<div align='justify'><i>Log del Sistema: </i>" + getStackTraceString(e),
                "12");
        } 
        catch (Exception ex) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"WsError","Envia Correo de Notificacion de Error",ex);
        }

        return Respuesta;
    }
    
    public String[] MailError()
    {
        String De = "";
        String Para = "";
        String CC = "";
        String CCO = "";

        
        try {
            Con = new conexion().getConnection();
            CallableStatement cs = Con.prepareCall("{call Crm_SpEnvioMailsErrores(?,?)}");  
            int i = 0;
            cs.setString(++i, "");
            cs.setString(++i, "");
                    
            boolean isRs = cs.execute();
            
            while (!isRs && (cs.getUpdateCount() != -1)) {
                isRs = cs.getMoreResults(); 
            }
            
            rs = cs.getResultSet();
            
            if (rs.next()) 
            {
                De = rs.getString("Not_De");
                Para = rs.getString("Not_Email");
                //CC = rs.getString("CC");
                //CCO = rs.getString("CCO");
            }
            
            Con.close();
        } catch (SQLException e) {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"MailError","Crm_SpEnvioMailsErrores",e);
        }
        return new String[] { De, Para, CC, CCO };
    }
    
    public static String getStackTraceString(Throwable e) 
    {
    return getStackTraceString(e, "");
}

    private static String getStackTraceString(Throwable e, String indent) 
    {
    StringBuilder sb = new StringBuilder();
    sb.append(e.toString());
    sb.append("\n");

    StackTraceElement[] stack = e.getStackTrace();
    if (stack != null) {
        for (StackTraceElement stackTraceElement : stack) {
            sb.append(indent);
            sb.append("\tat ");
            sb.append(stackTraceElement.toString());
            sb.append("\n");
        }
    }

    Throwable[] suppressedExceptions = e.getSuppressed();
    // Print suppressed exceptions indented one level deeper.
    if (suppressedExceptions != null) {
        for (Throwable throwable : suppressedExceptions) {
            sb.append(indent);
            sb.append("\tSuppressed: ");
            sb.append(getStackTraceString(throwable, indent + "\t"));
        }
    }

    Throwable cause = e.getCause();
    if (cause != null) {
        sb.append(indent);
        sb.append("Caused by: ");
        sb.append(getStackTraceString(cause, indent));
    }

    return sb.toString();
}

}
