package com.americas.servlet;

import com.americas.bean.BeanCliente;
import com.americas.ws.obtenerContacto;
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
import org.datacontract.schemas._2004._07.ws_famisanar.Contacto;

/**
 *
 * @author omaalvca
 */
public class ServletValidaUsuarioPAC extends HttpServlet {

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
        
        try{
            BeanLista Bean = new BeanLista();
            BeanCliente Usuario = new BeanCliente();
            
            ArrayList<BeanLista> ListaTipoDocumento =  Bean.llenarListaTipoDocumento();
            ArrayList<BeanLista> ListaRequerimiento =  Bean.llenarListaRequerimientoPOS();
            ArrayList<BeanLista> ListaRequerimientoPAC =  Bean.llenarListaRequerimientoPAC();
            String tipoDocumento = request.getParameter("tipoDocumentoValida");
            String identificacion = request.getParameter("identificacionValida");
            
            request.setAttribute("listaDocumentoValida", ListaTipoDocumento);
            request.setAttribute("listaRequerimiento", ListaRequerimiento);
            request.setAttribute("listaRequerimientoPAC", ListaRequerimientoPAC);
            
            if (tipoDocumento == null) 
            {
                response.sendRedirect("http://200.122.252.187:8085/ChatFamisanar/ServletCargaListaPAC");
                return;
            }
            else
            {
                Usuario = Usuario.validaCliente(tipoDocumento, identificacion);
            }
            
            if (Usuario!= null) 
            {
                request.setAttribute("hide", "Mostrar");
                request.setAttribute("IdCli",Usuario.getId());
                request.setAttribute("selectDocumento", tipoDocumento);
                request.setAttribute("identificacionValidado", identificacion);
                request.setAttribute("cli_Nombre1", Usuario.getNombreUsuario());
                request.setAttribute("cli_Email", Usuario.getEmail());

            }
            else
            {
                obtenerContacto obtener = new obtenerContacto();
                Contacto Client = obtener.obtenerContacto(tipoDocumento, identificacion);
                String primerNombre = Client.getPrimerNombre().getValue();
                String segundoNombre = Client.getSegundoNombre().getValue();
                String primerApellido = Client.getPrimerApellido().getValue();
                String segundoApellido = Client.getSegundoApellido().getValue();
            
                if (primerNombre == null) {
                    primerNombre = "";
                }
                if (segundoNombre == null) {
                    segundoNombre = "";
                }
                if (primerApellido == null) {
                    primerApellido = "";
                }
                if (segundoApellido == null) {
                    segundoApellido = "";
                }
            
                String Respuesta = Client.getResultado().getValue();
                String Nombre = primerNombre.trim() +" "+ 
                                segundoNombre.trim() +" "+ 
                                primerApellido.trim() +" "+ 
                                segundoApellido.trim();
                Nombre=Nombre.trim();
                request.setAttribute("hide", "Mostrar");
                request.setAttribute("selectDocumento", tipoDocumento);
                request.setAttribute("identificacionValidado", identificacion);
                request.setAttribute("listaDocumentoValida", ListaTipoDocumento);
                request.setAttribute("listaRequerimiento", ListaRequerimiento);
            
            
                if ("OK".equals(Respuesta)) 
                {
                    request.setAttribute("cli_Nombre1", Nombre);
                    request.setAttribute("cli_Email", "");
                    
                }
                else
                {
                    request.setAttribute("cli_Nombre1", "");
                    request.setAttribute("cli_Email", "");
                }
                 request.setAttribute("IdCli","0");
            }
            IFaceIngresaUsuario Iface = new ImpIngresaUsuario();
            boolean Session = Iface.validaSesion(tipoDocumento, identificacion);
            request.setAttribute("Sesion",Session);
            request.getRequestDispatcher("/Pac.jsp").forward(request, response);
        }
        catch(IOException | ServletException ex)
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(ex.toString(),"doPost","Validacion de Usuario Pac",ex);
        }  
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
