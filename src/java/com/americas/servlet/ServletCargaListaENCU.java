package com.americas.servlet;

import com.americas.bean.BeanLista;
import com.americas.log.RegistroError;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author omaalvca
 */
@WebServlet(urlPatterns = {"/ChatFamisanar"})
public class ServletCargaListaENCU extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try
        {
        BeanLista Bean = new BeanLista();
        ArrayList<BeanLista> ListaEncuesta =  Bean.llenarListaRequerimientoENCU();
        //Se cabturar los datos recibidos de la URL
        String idContacto = request.getParameter("Iden");
        String idRequerimiento = request.getParameter("cf");
        String IdGenesys = request.getParameter("IdGen");
        
        request.setAttribute("idContacto", idContacto);
        request.setAttribute("idRequerimiento", idRequerimiento);
        request.setAttribute("IdGenesys", IdGenesys);
        
        request.setAttribute("listaEncuesta", ListaEncuesta);
        request.getRequestDispatcher("/Encuesta.jsp").forward(request, response);
        }
        catch(IOException | ServletException e)
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"processRequest","Cargando información de la encuesta",e);
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
