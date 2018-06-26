package com.americas.dao;
import java.sql.Connection;
import java.sql.SQLException;
import com.americas.bean.BeanCliente;
import com.americas.conexion.conexion;
import com.americas.log.RegistroError;
import com.americas.ws.obtenerContacto;
import java.sql.CallableStatement;
import org.datacontract.schemas._2004._07.ws_famisanar.Contacto;

public class ImpIngresaUsuario implements IFaceIngresaUsuario{
    
    Connection Con = null;
    CallableStatement proc = null;
    
    @Override
    public String LogIngreso(BeanCliente obj)
    {
        try {
            
            Con = new conexion().getConnection();

                    String qry = "{call spLogIngreso(?,?,?,?,?,?,?)}";
                    proc = Con.prepareCall(qry);
                    proc.setString(1, obj.getNombreUsuario());
                    proc.setString(2, obj.getTipoDocumento());
                    proc.setString(3, obj.getIdentificacion());
                    proc.setString(4, obj.getEmail());
                    proc.setInt(5, obj.getRequerimiento());
                    proc.setBoolean(6, obj.isAutorizaEnvioInfo());
                    proc.setString(7, "POS");
                    proc.executeUpdate();

                    Con.close();
        } catch (RuntimeException | SQLException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"LogIngreso","spLogIngreso",e);
            
        }
        return "ok";
    }
    
    @Override
    public BeanCliente IngresoUsuario(BeanCliente obj)
    {
        
        try {
            
            Con = new conexion().getConnection();
                    //String qry = "{call Crm_SpCrearCliente(?,?,?,?,?,?,?,?,?)}";
                    CallableStatement cs = Con.prepareCall("{call Crm_SpCrearCliente(?,?,?,?,?,?,?,?,?,?)}"); 
                    //proc = Con.prepareCall(qry);
                    cs.setString(1, obj.getTipoDocumento());
                    cs.setString(2, obj.getIdentificacion());
                    cs.setInt(3, obj.getServicio());
                    cs.setString(4, obj.getNombreUsuario());
                    cs.setString(5, obj.getApellidoUsuario());
                    cs.setString(6, obj.getNumContacto());
                    cs.setString(7, obj.getEmail());
                    cs.setBoolean(8, obj.isAutorizaEnvioInfo());
                    cs.setInt(9, obj.getRequerimiento());
                    cs.registerOutParameter(10, java.sql.Types.INTEGER);
                    cs.executeUpdate();
                    
                    int pk = cs.getInt(10);
                    
                    Con.close();
                    obj.setIdPk(pk);
        } 
        catch (RuntimeException | SQLException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"IngresoUsuario","Crm_SpCrearCliente",e);
        }
        return obj;
    }
    
    
    @Override
    public Boolean validaSesion(String tipoIdentificacion,String numeroIdentificacion)
    {
        boolean result = false;
        try 
        {
            Con = new conexion().getConnection();
            CallableStatement cs = Con.prepareCall("{call Crm_SpGestionarSesion(?,?,?,?)}"); 
            cs.setString(1, tipoIdentificacion);
            cs.setString(2, numeroIdentificacion);
            cs.setString(3, "0");
            cs.registerOutParameter(4, java.sql.Types.INTEGER);
            cs.executeUpdate();
            result = cs.getBoolean(4);
            Con.close();
        } 
        catch (SQLException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"validaSesion","Validando la Sesion de Usuario",e);
        }
        return result;
    }
    
    @Override
    public Boolean validaHorario(String Sala)
    {
        boolean result = false;
        try 
        {
            Con = new conexion().getConnection();
            CallableStatement cs = Con.prepareCall("{call Crm_SpAtencionChat(?,?)}"); 
            cs.setString(1, Sala);
            cs.registerOutParameter(2, java.sql.Types.INTEGER);
            cs.executeUpdate();
            result = cs.getBoolean(2);
            Con.close();
        } 
        catch (SQLException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"validaHorario","Validando el Horario del Chat",e);
        }
        return result;
    }
    
    @Override
    public String IdContacto(String tipoIdentificacion,String numeroIdentificacion, String nombre, String apellido, String email, String numContacto)
    {
            obtenerContacto obtener = new obtenerContacto();
            Contacto Client = obtener.obtenerContactoChat(
                        tipoIdentificacion, numeroIdentificacion, 
                        nombre, apellido, 
                        email, numContacto);
                
            return Client.getIdContacto().getValue();
    }
    
   
}
