package com.americas.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.americas.bean.BeanCliente;
import com.americas.dao.ImpIngresaUsuario;
import com.americas.log.RegistroError;
import com.americas.dao.IFaceIngresaUsuario;

/**
 * Servlet implementation class ServletIngresaUsuarioPOS
 */
public class ServletIngresaUsuarioPOS extends HttpServlet {
	//private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletIngresaUsuarioPOS() {
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
        {
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
                    Bean.setServicio(1);
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
                                "http://200.122.252.187:8070/webapi/WEBAPI85/Famisanar/Chat/HtmlChatFrameSetPos_Limp.jsp"
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
                                "idcliente="+ idContacto.replace(" ","")+ "&"+
                                "idContacto="+idContacto.replace(" ","")
                        );

                    }
                    else
                    {
                        response.sendRedirect(
                                "http://200.122.252.187:8070/webapi/WEBAPI85/Famisanar/Chat/HtmlChatFrameSetPos_Limp.jsp"
                                + "?" + 
                                "FirstName=" + nombreUsuario
                        );
                    }
                    
                }
                catch(RuntimeException e)
                {
                    RegistroError Error = new RegistroError();
                    Error.Page_Error(e.toString(),"doPost","Redirección de Usuario POS",e);
                }

        }

}
