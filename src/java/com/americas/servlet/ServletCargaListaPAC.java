/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 *
 * @author omaalvca
 */
public class ServletCargaListaPAC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
        BeanLista Bean = new BeanLista();
        ArrayList<BeanLista> ListaTipoDocumento =  Bean.llenarListaTipoDocumento();
        ArrayList<BeanLista> ListaRequerimiento =  Bean.llenarListaRequerimientoPAC();
        ArrayList<BeanLista> ListaRequerimientoPAC =  Bean.llenarListaRequerimientoPAC();
        request.setAttribute("selectDocumento", "-1");
        request.setAttribute("IdCliente", "");
        request.setAttribute("listaDocumentoValida", ListaTipoDocumento);
        request.setAttribute("listaRequerimientoPAC", ListaRequerimientoPAC);
        request.setAttribute("listaRequerimiento", ListaRequerimiento);
        
        IFaceIngresaUsuario Iface = new ImpIngresaUsuario();
        boolean Horario = Iface.validaHorario("2");
        request.setAttribute("Horario",Horario);
        
        request.getRequestDispatcher("/Pac.jsp").forward(request, response);
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
    }

}
