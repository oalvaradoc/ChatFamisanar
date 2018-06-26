package com.americas.servlet;

import com.americas.bean.BeanCliente;
import com.americas.log.RegistroError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author omaalvca
 */

public class ServletIngresaEncuesta extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        doPost(request, response);    
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        String IdRequerimiento = request.getParameter("identiRequeri");
        String IdContacto = request.getParameter("identiContacto");
        String IdGenesys = request.getParameter("identiGenes");
      
        BeanCliente Client = new BeanCliente();
        String pregunta1 = request.getParameter("1"+"-question");
        String pregunta2 = request.getParameter("2"+"-question");
        String pregunta3 = request.getParameter("3"+"-question");
        String pregunta4 = request.getParameter("4"+"-question");
        Client.calificaEncuesta(IdRequerimiento, pregunta1, pregunta2, pregunta3, pregunta4,IdContacto,IdGenesys);

        response.sendRedirect("http://www.famisanar.com.co/");
        
        }
        catch (IOException e) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(e.toString(),"processRequest","Cargando información de la encuesta",e);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
