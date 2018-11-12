package com.gssamerica.mdm.services.db;

import com.gssamerica.mdm.constants.MDMConstants;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSourceFactory;
import com.gssamerica.mdm.services.exceptions.MDMInvalidRepositoryException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.RepositoryInformationType;
import javax.sql.DataSource;

public class DataRepository {
	
	private final String ORACLE_PROTOCOL_VALUE = "jdbc:oracle:thin";
	private String ORACLE_USER_VALUE = MDMConstants.MDM_HUB_USERNAME;
	private String ORACLE_PASSWORD_VALUE = MDMConstants.MDM_HUB_PASSWORD;
	private final String JDBCDriver_VALUE = "oracle.jdbc.driver.OracleDriver";
	private String ORACLE_URL_VALUE = null;
	private String ORACLE_HOST_VALUE = null;
	private String ORACLE_SID_VALUE = null;
	private String ORACLE_PORT_VALUE = null;
	private DataSource dataSource = null;
		
	public DataRepository(RepositoryInformationType repositoryInformation, int i) throws MDMInvalidRepositoryException{
		
            String dbcp_USERNAME_KEY="username";
            String dbcp_PASSWORD_KEY="password";
            String dbcp_URL_KEY="url";
            String dbcp_DRIVER_KEY="driverClassName";
            String dbconnectString = null;
            String dbURL = null;
            Properties serviceProperties = null;
            
            this.ORACLE_HOST_VALUE = repositoryInformation.getServerName();
            this.ORACLE_SID_VALUE = repositoryInformation.getRepositoryName();
            this.ORACLE_PORT_VALUE = repositoryInformation.getRepositoryPort().toString();
            
            dbconnectString = "(DESCRIPTION=(ADDRESS=(HOST="+ this.ORACLE_HOST_VALUE + ")(PROTOCOL=tcp)(PORT="+ this.ORACLE_PORT_VALUE +"))(CONNECT_DATA=(SID="+this.ORACLE_SID_VALUE+")))";
            dbURL = this.ORACLE_PROTOCOL_VALUE+":@"+ dbconnectString;
            serviceProperties = new Properties();
            serviceProperties.setProperty(dbcp_URL_KEY, dbURL);
            serviceProperties.setProperty(dbcp_DRIVER_KEY, JDBCDriver_VALUE);
            serviceProperties.setProperty(dbcp_USERNAME_KEY, ORACLE_USER_VALUE);
            serviceProperties.setProperty(dbcp_PASSWORD_KEY, ORACLE_PASSWORD_VALUE);
            serviceProperties.setProperty("initialSize","4");
            serviceProperties.setProperty("maxActive","4");
            serviceProperties.setProperty("maxIdle","2");
            try {
                dataSource = BasicDataSourceFactory.createDataSource(serviceProperties);
            } catch (Exception e) {
                MDMInvalidRepositoryException mdmInvalidRepositoryException = null;
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while creating Datasource for the Repository":e.getMessage());
                mdmInvalidRepositoryException = new MDMInvalidRepositoryException(message,e.getCause(),e.getStackTrace());
                mdmInvalidRepositoryException.setDriverClass(JDBCDriver_VALUE);
                mdmInvalidRepositoryException.setPassword(ORACLE_PASSWORD_VALUE);
                mdmInvalidRepositoryException.setRepositoryName(this.ORACLE_SID_VALUE);
                mdmInvalidRepositoryException.setRepositoryPort(this.ORACLE_PORT_VALUE);
                mdmInvalidRepositoryException.setServerName(this.ORACLE_HOST_VALUE);
                mdmInvalidRepositoryException.setUserName(ORACLE_USER_VALUE);
                throw (mdmInvalidRepositoryException);
            }
        }
	
	public Connection getConnection(int i) throws MDMRepositoryConnectionException{
            Connection connection = null;
            if(this.dataSource!=null){
                try {
                    connection = this.dataSource.getConnection();
                } catch (Exception e) {
                    MDMRepositoryConnectionException repositoryConnectionException = null;
                    String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while getting Connection from Repository":e.getMessage());
                    repositoryConnectionException = new MDMRepositoryConnectionException(message, e.getCause(),e.getStackTrace());
                    repositoryConnectionException.setDriverClass(JDBCDriver_VALUE);
                    repositoryConnectionException.setPassword(ORACLE_PASSWORD_VALUE);
                    repositoryConnectionException.setRepositoryName(this.ORACLE_SID_VALUE);
                    repositoryConnectionException.setRepositoryPort(this.ORACLE_PORT_VALUE);
                    repositoryConnectionException.setServerName(this.ORACLE_HOST_VALUE);
                    repositoryConnectionException.setUserName(ORACLE_USER_VALUE);
                    throw (repositoryConnectionException);
                }
            }
            else{
                MDMRepositoryConnectionException repositoryConnectionException = null;
                repositoryConnectionException = new MDMRepositoryConnectionException("While getting Connection from Repository - DATASOURCE Found NULL");
                repositoryConnectionException.setDriverClass(JDBCDriver_VALUE);
                repositoryConnectionException.setPassword(ORACLE_PASSWORD_VALUE);
                repositoryConnectionException.setRepositoryName(this.ORACLE_SID_VALUE);
                repositoryConnectionException.setRepositoryPort(this.ORACLE_PORT_VALUE);
                repositoryConnectionException.setServerName(this.ORACLE_HOST_VALUE);
                repositoryConnectionException.setUserName(ORACLE_USER_VALUE);
                throw (repositoryConnectionException);
            }
            return connection;
	}
	
	
        public DataRepository(RepositoryInformationType repositoryInformation){
		
		String dbconnectString = null;
		this.ORACLE_HOST_VALUE = repositoryInformation.getServerName();
		this.ORACLE_SID_VALUE = repositoryInformation.getRepositoryName();
		this.ORACLE_PORT_VALUE = repositoryInformation.getRepositoryPort().toString();
		//jdbc:oracle:thin:@MyOracleHost:1521:MyDB
		//dbconnectString = "(DESCRIPTION=(ADDRESS=(HOST="+ this.ORACLE_HOST_VALUE + ")(PROTOCOL=tcp)(PORT="+ this.ORACLE_PORT_VALUE +"))(CONNECT_DATA=(SID="+this.ORACLE_SID_VALUE+")))";
		dbconnectString = this.ORACLE_HOST_VALUE +":"+ this.ORACLE_PORT_VALUE +":"+this.ORACLE_SID_VALUE;
		this.ORACLE_URL_VALUE = this.ORACLE_PROTOCOL_VALUE+":@"+ dbconnectString;
	}
        
        public DataRepository(RepositoryInformationType repositoryInformation, String user, String password){
            
            String dbconnectString = null;   
            this.ORACLE_USER_VALUE = user;
            this.ORACLE_PASSWORD_VALUE = password;
            this.ORACLE_HOST_VALUE = repositoryInformation.getServerName();
            this.ORACLE_SID_VALUE = repositoryInformation.getRepositoryName();
            this.ORACLE_PORT_VALUE = repositoryInformation.getRepositoryPort().toString();
            dbconnectString = this.ORACLE_HOST_VALUE +":"+ this.ORACLE_PORT_VALUE +":"+this.ORACLE_SID_VALUE;
            this.ORACLE_URL_VALUE = this.ORACLE_PROTOCOL_VALUE+":@"+ dbconnectString;
        }
	
	public Connection getConnection() throws MDMRepositoryConnectionException {
		
            Connection connection = null;
            try {
                //System.out.println("Getting for JDBC - "+this.JDBCDriver_VALUE);
                Class.forName(this.JDBCDriver_VALUE);
                connection = DriverManager.getConnection(this.ORACLE_URL_VALUE,this.ORACLE_USER_VALUE,this.ORACLE_PASSWORD_VALUE);
                //System.out.println("Got Connection...for URL - "+this.ORACLE_URL_VALUE+"  "+connection);
            } catch (Exception e) {
                e.printStackTrace();
                MDMRepositoryConnectionException repositoryConnectionException = null;
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while getting Connection for the Repository":e.getMessage());
                repositoryConnectionException = new MDMRepositoryConnectionException(message, e.getCause(), e.getStackTrace());
                repositoryConnectionException.setDriverClass(JDBCDriver_VALUE);
                repositoryConnectionException.setPassword(ORACLE_PASSWORD_VALUE);
                repositoryConnectionException.setRepositoryName(this.ORACLE_SID_VALUE);
                repositoryConnectionException.setRepositoryPort(this.ORACLE_PORT_VALUE);
                repositoryConnectionException.setServerName(this.ORACLE_HOST_VALUE);
                repositoryConnectionException.setUserName(ORACLE_USER_VALUE);
                throw (repositoryConnectionException);
            }
            return connection;
	}
}
