package com.americas.bean;

import com.americas.conexion.conexion;
import com.americas.log.RegistroError;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author omaalvca
 */
public class BeanLista {
    
    private String id;
    private String valor;
    private String descripcion;
    
    Connection Con = null;
    CallableStatement proc = null;
    PreparedStatement ps = null;
    ResultSet rs = null;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public ArrayList<BeanLista>  llenarListaTipoDocumento() {
        ArrayList<BeanLista> Lista = null;
        try {
            Con = new conexion().getConnection();
            Lista = new ArrayList<BeanLista>();
            CallableStatement cs = Con.prepareCall("{call Crm_SpCargaLista(?)}");  
            cs.setString(1, "Documentos");
                    
            boolean isRs = cs.execute();
            
            while (!isRs && (cs.getUpdateCount() != -1)) {
                isRs = cs.getMoreResults(); 
            }
            
            rs = cs.getResultSet();

            while(rs.next())
            {
                BeanLista p = new BeanLista();
                p.setId(rs.getString("tip_IdtipoDocumentoPk"));
                p.setValor(rs.getString("tip_TipoDocumento"));
                Lista.add(p);
            }
            
            Con.close();
        } catch (SQLException | NumberFormatException e ) {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"llenarLista","Crm_SpCargaLista",e);
        }
        
        return Lista;
    }       
    
    public ArrayList<BeanLista>  llenarListaRequerimientoPOS() {
        ArrayList<BeanLista> Lista = null;
        try {
            Con = new conexion().getConnection();
            Lista = new ArrayList<BeanLista>();
            CallableStatement cs = Con.prepareCall("{call Crm_SpCargaLista(?)}");  
            cs.setString(1, "Pos");
                    
            boolean isRs = cs.execute();
             
            while (!isRs && (cs.getUpdateCount() != -1)) {
                isRs = cs.getMoreResults(); 
            }
            
            rs = cs.getResultSet();

            while(rs.next())
            {
                BeanLista p = new BeanLista();
                p.setId(rs.getString("dire_idreqPk"));
                p.setValor(rs.getString("dire_NombreRequerimiento"));
                Lista.add(p);
            }
            
            Con.close();
        } catch (SQLException | NumberFormatException e ) {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"llenarLista","Crm_SpCargaLista",e);
        }
        
        return Lista;
    }   
    
    public ArrayList<BeanLista>  llenarListaRequerimientoPAC() {
        ArrayList<BeanLista> Lista = null;
        try {
            Con = new conexion().getConnection();
            Lista = new ArrayList<BeanLista>();
            CallableStatement cs = Con.prepareCall("{call Crm_SpCargaLista(?)}");  
            cs.setString(1, "Pac");
                    
            boolean isRs = cs.execute();
            
            while (!isRs && (cs.getUpdateCount() != -1)) {
                isRs = cs.getMoreResults(); 
            }
            
            rs = cs.getResultSet();

            while(rs.next())
            {
                BeanLista p = new BeanLista();
                p.setId(rs.getString("dire_idreqPk"));
                p.setValor(rs.getString("dire_NombreRequerimiento"));
                Lista.add(p);
            }
            
            Con.close();
        } catch (SQLException | NumberFormatException e ) {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"llenarLista","Crm_SpCargaLista",e);
        }
        
        return Lista;
    } 
    
    public ArrayList<BeanLista>  llenarListaRequerimientoAUTO() {
        ArrayList<BeanLista> Lista = null;
        try {
            Con = new conexion().getConnection();
            Lista = new ArrayList<BeanLista>();
            CallableStatement cs = Con.prepareCall("{call Crm_SpCargaLista(?)}");  
            cs.setString(1, "Autorizaciones");
                    
            boolean isRs = cs.execute();
            
            while (!isRs && (cs.getUpdateCount() != -1)) {
                isRs = cs.getMoreResults(); 
            }
            
            rs = cs.getResultSet();

            while(rs.next())
            {
                BeanLista p = new BeanLista();
                p.setId(rs.getString("dire_idreqPk"));
                p.setValor(rs.getString("dire_NombreRequerimiento"));
                Lista.add(p);
            }
            
            Con.close();
        } catch (SQLException | NumberFormatException e ) {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"llenarLista","Crm_SpCargaLista",e);
        }
        
        return Lista;
    } 
    
    public ArrayList<BeanLista>  llenarListaRequerimientoENCU() {
        ArrayList<BeanLista> Lista = null;
        try {
            Con = new conexion().getConnection();
            Lista = new ArrayList<BeanLista>();
            CallableStatement cs = Con.prepareCall("{call Crm_SpCargaLista(?)}");  
            cs.setString(1, "Encuesta");
                    
            boolean isRs = cs.execute();
            
            while (!isRs && (cs.getUpdateCount() != -1)) {
                isRs = cs.getMoreResults(); 
            }
            
            rs = cs.getResultSet();

            while(rs.next())
            {
                BeanLista p = new BeanLista();
                p.setId(rs.getString("enc_IdEncuestaPk"));
                p.setValor(rs.getString("enc_Pregunta"));
                Lista.add(p);
            }
            
            Con.close();
        } catch (SQLException | NumberFormatException e ) {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"llenarListaRequerimientoENCU","Crm_SpCargaLista",e);
        }
        
        return Lista;
    } 
}
