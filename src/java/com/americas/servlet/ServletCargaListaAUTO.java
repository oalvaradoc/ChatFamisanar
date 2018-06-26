package com.americas.servlet;

import com.americas.bean.BeanLista;
import com.americas.dao.IFaceIngresaUsuario;
import com.americas.dao.ImpIngresaUsuario;
import com.americas.log.RegistroError;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author omaalvca
 */
public class ServletCargaListaAUTO extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
            BeanLista Bean = new BeanLista();
            ArrayList<BeanLista> ListaTipoDocumento =  Bean.llenarListaTipoDocumento();
            ArrayList<BeanLista> ListaRequerimiento =  Bean.llenarListaRequerimientoAUTO();
            request.setAttribute("selectDocumento", "-1");
            request.setAttribute("IdCliente", "");
            request.setAttribute("listaDocumentoValida", ListaTipoDocumento);
            request.setAttribute("listaRequerimiento", ListaRequerimiento);
            
            IFaceIngresaUsuario Iface = new ImpIngresaUsuario();
            boolean Horario = Iface.validaHorario("3");
            request.setAttribute("Horario",Horario);
            
            request.getRequestDispatcher("/Autorizaciones.jsp").forward(request, response);
        }
        catch(IOException | ServletException e)
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"processRequest","Cargando información de las listas",e);
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
