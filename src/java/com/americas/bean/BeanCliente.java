package com.americas.bean;

import com.americas.conexion.conexion;
import com.americas.log.RegistroError;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BeanCliente extends BeanRequerimiento{
    
    private int id;
    private int idPk;
    private String nombreUsuario;
    private String apellidoUsuario;
    private String TipoDocumento;
    private String identificacion;
    private String email;
    private int servicio;
    private String numContacto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPk() {
        return idPk;
    }

    public void setIdPk(int idPk) {
        this.idPk = idPk;
    }

    public String getNumContacto() {
        return numContacto;
    }

    public void setNumContacto(String numContacto) {
        this.numContacto = numContacto;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getServicio() {
        return servicio;
    }

    public void setServicio(int servicio) {
        this.servicio = servicio;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getIdentificacion() {
		return identificacion;
	}
    
    public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}
    
    public String getTipoDocumento() {
		return TipoDocumento;
    }
    
    public void setTipoDocumento(String tipoDocumento) {
		TipoDocumento = tipoDocumento;
	}
        
    public String getApellidoUsuario() {
        return apellidoUsuario;
    }

    public void setApellidoUsuario(String apellidoUsuario) {
        this.apellidoUsuario = apellidoUsuario;
    }

    public void calificaEncuesta(String IdRequerimiento, String Pregunta1, 
            String Pregunta2, String Pregunta3, String Pregunta4, String IdContacto,
            String IdGenesys)
    {

        try 
        {
            int i = 0;
            try (Connection Con = new conexion().getConnection()) {
                CallableStatement cs = Con.prepareCall("{call Crm_SpIngresaCalificacion(?,?,?,?,?,?,?)}");
                cs.setString(++i, IdRequerimiento);
                cs.setString(++i, Pregunta1);
                cs.setString(++i, Pregunta2);
                cs.setString(++i, Pregunta3);
                cs.setString(++i, Pregunta4);
                cs.setString(++i, IdContacto);
                cs.setString(++i, IdGenesys);
                cs.execute();
            }
        } 
        catch (SQLException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"calificaEncuesta","Calificando la encuenta",e);
        }
    }
    
    public BeanCliente validaCliente(String tipoDocumento,String numDocumento)
    {
        BeanCliente Cliente = new BeanCliente();
        try {
            try (Connection Con = new conexion().getConnection()) {
                CallableStatement cs = Con.prepareCall("{call Crm_SpValidarCliente(?,?)}");
                int i = 0;
                cs.setString(++i, tipoDocumento);
                cs.setString(++i, numDocumento);
                cs.execute();
                ResultSet rs = cs.getResultSet();
                
                if (rs.next())
                {
                    int idCliente = Integer.parseInt(rs.getString("cli_IdClientesPk"));
                    Cliente.setId(idCliente);
                    Cliente.setTipoDocumento(tipoDocumento);
                    Cliente.setIdentificacion(numDocumento);
                    Cliente.setNombreUsuario(rs.getString("cli_Nombre"));
                    Cliente.setApellidoUsuario(rs.getString("cli_Apellido"));
                    Cliente.setEmail(rs.getString("cli_Email"));
                    Cliente.setNumContacto(rs.getString("cli_NumeroContacto"));
                    Cliente.setServicio(Integer.parseInt(rs.getString("cli_IdTipoClienteFk")));
                    
                }
                else
                {
                    return null;
                }
            }
        }
        catch (SQLException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"validaCliente","Valida si se encuentra el cliente",e);
        }
        
        return Cliente;
    }
    
}
