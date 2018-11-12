package com.emerson.icoe.supplierconnectivity.services.database;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager
{
    private static Connection connRef = null;
    
    private ConnectionManager(){}
 
    public static Connection getDBConnection() throws Exception
    {
        if(connRef == null)
        {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
            connRef = DriverManager.getConnection("jdbc:odbc:ora","user1","user1");
        }
        return connRef;
    }
    
}
