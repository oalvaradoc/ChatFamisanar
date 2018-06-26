package com.americas.servlet;

import com.americas.bean.BeanCliente;
import com.americas.dao.IFaceIngresaUsuario;
import com.americas.dao.ImpIngresaUsuario;
import com.americas.log.RegistroError;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author omaalvca
 */
public class ServletIngresaUsuarioAUTO extends HttpServlet {

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
        	try
                {    
//                    int idCliente = Integer.parseInt(request.getParameter("IdCliente"));
                    String nombreUsuario = request.getParameter("nombreUsuario");
                    String identificacion = request.getParameter("identificacionValidas");
                    String TipoDocumento = request.getParameter("tipoDocumentoValidas");
                    String email = request.getParameter("correo");
                    String envioConversacionEmail = request.getParameter("envioConversacionEmail");
                    
                    String apellidoUsuario = request.getParameter("apellidosUsuario");
                    String numContacto = request.getParameter("numContacto");
                    
                    //int requerimiento = 0;

                    BeanCliente Bean = new BeanCliente();
//                    Bean.setId(idCliente);
                    Bean.setTipoDocumento(TipoDocumento);
                    Bean.setIdentificacion(identificacion);
                    Bean.setServicio(3);
                    Bean.setNombreUsuario(nombreUsuario);
                    Bean.setApellidoUsuario("");
                    Bean.setNumContacto("");
                    Bean.setEmail(email);
                    //Bean.setRequerimiento(null);
                    Bean.setAutorizaEnvioInfo(Boolean.parseBoolean(envioConversacionEmail));

                    Bean.setApellidoUsuario(apellidoUsuario);
                    Bean.setNumContacto(numContacto);
                    
                    BeanCliente BeanRetorno = new BeanCliente();
                    IFaceIngresaUsuario Iface = new ImpIngresaUsuario();
                    //Iface.LogIngreso(Bean);
                    BeanRetorno = Iface.IngresoUsuario(Bean);
                    
                    String idContacto = Iface.IdContacto( TipoDocumento,  identificacion, nombreUsuario,  apellidoUsuario,  email, numContacto);
                    
                    if (BeanRetorno != null) {
                        
                        response.sendRedirect(
                                "http://200.122.252.187:8070/webapi/WEBAPI85/Famisanar/Chat_Autorizaciones/HtmlChatFrameSet_Limp.jsp"
                                + "?" + 
                                "FirstName=" + BeanRetorno.getNombreUsuario() + "&" +
                                "IDNumber=" + BeanRetorno.getIdentificacion() + "&" +
                                "EmailAddress=" + BeanRetorno.getEmail()  + "&" +
                                "PhoneNumber=" + BeanRetorno.getNumContacto() + "&" +
                                "IDType=" +BeanRetorno.getTipoDocumento() + "&" +
                                "PhoneNumber2="+ "" + "&" +
                                "City="+ "" + "&" +
                                "Departamento="+ "" + "&" +
                                "Programa="+ BeanRetorno.getServicio() + "&" +
                                "ConsecutivoFormulario="+ BeanRetorno.getIdPk() + "&" +
                                "msg2send="+ BeanRetorno.isAutorizaEnvioInfo() + "&" +
                                "Edad="+ "" + "&" +
                                "idcliente="+ idContacto.replace(" ","") + "&"+
                                "idContacto="+idContacto.replace(" ","")
                        );

                    }
                    else
                    {
                        response.sendRedirect(
                                "http://200.122.252.187:8070/webapi/WEBAPI85/Famisanar/Chat_Autorizaciones/HtmlChatFrameSet_Limp.jsp"
                                + "?" + 
                                "FirstName=" + nombreUsuario
                        );
                    }
                    
                }
                catch(RuntimeException e)
                {
                    RegistroError Error = new RegistroError();
                    Error.Page_Error(e.toString(),"doPost","Redirección de Usuario Encuesta",e);
                }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
