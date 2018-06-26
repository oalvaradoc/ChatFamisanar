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
public class ServletIngresaUsuarioPAC extends HttpServlet {

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
                    //int idCliente = Integer.parseInt(request.getParameter("IdCliente"));
                    int requerimiento;
                    String nombreUsuario = request.getParameter("nombreUsuario");
                    String identificacion = request.getParameter("identificacionValidas");
                    String TipoDocumento = request.getParameter("tipoDocumentoValidas");
                    String email = request.getParameter("correo");
                    String envioConversacionEmail = request.getParameter("envioConversacionEmail");
                    String req = request.getParameter("ddl_requerimiento");
                    if(null == req)
                    {
                        requerimiento = 0;
                    }
                    else switch (req) {
                    case "1":
                        requerimiento =  Integer.parseInt(request.getParameter("ddl_requerimientos"));
                    break;
                    default:
                        requerimiento = Integer.parseInt(req);
                    break;
                    }
                    
                    BeanCliente Bean = new BeanCliente();
                    //Bean.setId(idCliente);
                    Bean.setTipoDocumento(TipoDocumento);
                    Bean.setIdentificacion(identificacion);
                    Bean.setServicio(2);
                    Bean.setNombreUsuario(nombreUsuario);
                    Bean.setApellidoUsuario("");
                    Bean.setNumContacto("");
                    Bean.setEmail(email);
                    Bean.setRequerimiento(requerimiento);
                    Bean.setAutorizaEnvioInfo(Boolean.parseBoolean(envioConversacionEmail));

                    BeanCliente BeanRetorno = new BeanCliente();
                    IFaceIngresaUsuario Iface = new ImpIngresaUsuario();
                    //Iface.LogIngreso(Bean);
                    BeanRetorno = Iface.IngresoUsuario(Bean);
                    
                    String idContacto = Iface.IdContacto( TipoDocumento,  identificacion, nombreUsuario,  "",  email, "");
                    
                    
                    
                    if (BeanRetorno != null) {
                        
                        response.sendRedirect(
                                "http://200.122.252.187:8070/webapi/WEBAPI85/Famisanar/Chat_PAC/HtmlChatFrameSet_Limp.jsp"
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
                                "http://200.122.252.187:8070/webapi/WEBAPI85/Famisanar/Chat_PAC/HtmlChatFrameSet_Limp.jsp"
                                + "?" + 
                                "FirstName=" + nombreUsuario
                        );
                    }
                    
                }
                catch(RuntimeException e)
                {
                    RegistroError Error = new RegistroError();
                    Error.Page_Error(e.toString(),"doPost","Redirección de Usuario PAC",e);
                }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
