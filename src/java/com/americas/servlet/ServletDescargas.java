/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.americas.servlet;

import com.americas.log.RegistroError;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author omaalvca
 */
@WebServlet(urlPatterns = {"/ChatFamisanar/Descargas"})
public class ServletDescargas extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = 1L;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		performTask(request, response);
	}

        @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException {
		performTask(request, response);
	}

	private void performTask(HttpServletRequest request, HttpServletResponse response) throws ServletException,
			IOException 
        {
            String documento = request.getParameter("doc");
            
            try
            {
                
		String pdfFileName = documento;
		String contextPath = getServletContext().getRealPath(File.separator).replace("build\\web\\", "");
                
                File pdfFile = new File(contextPath +"\\Descargas\\"+ pdfFileName);

		response.setContentType("Descargas/"+documento+"");
		response.addHeader("Content-Disposition", "attachment; filename=" + pdfFileName);
		response.setContentLength((int) pdfFile.length());

		FileInputStream fileInputStream = new FileInputStream(pdfFile);
		OutputStream responseOutputStream = response.getOutputStream();
		int bytes;
		while ((bytes = fileInputStream.read()) != -1) {
			responseOutputStream.write(bytes);
		}
            }
            catch(IOException ex)
            {
                RegistroError Error = new RegistroError();
                Error.Page_Error(ex.toString(),"performTask","Descargando PDF Nombre de Archivo: " + documento + "", ex );
            }

	}

}
