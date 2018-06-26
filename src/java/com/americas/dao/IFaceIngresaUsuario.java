package com.americas.dao;
import com.americas.bean.BeanCliente;

public interface IFaceIngresaUsuario {
	String LogIngreso(BeanCliente obj);
        BeanCliente IngresoUsuario(BeanCliente obj);
        Boolean validaSesion(String tipoIdentificacion,String numeroIdentificacion);
        Boolean validaHorario(String Sala);
        String IdContacto(String tipoIdentificacion,String numeroIdentificacion, String nombre, String apellido, String email, String numContacto);
}
