package com.americas.conexion;

import com.americas.log.RegistroError;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    public Connection getConnection()
    {
        Connection Con = null;
        try 
        {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Con = DriverManager.getConnection("jdbc:sqlserver://172.27.30.163;databaseName=Famisanar;user=usrfamisanar;password=mntpN7NI;");
        } 
        catch (SQLException | ClassNotFoundException sql) 
        {
            RegistroError Error = new RegistroError();
            Error.Page_Error(sql.toString(),"getConnection","Establecer Conexion con Base de Datos",sql);
        }
        return Con;
    }
}
