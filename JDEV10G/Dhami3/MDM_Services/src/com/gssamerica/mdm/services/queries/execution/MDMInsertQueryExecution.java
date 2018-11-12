package com.gssamerica.mdm.services.queries.execution;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.constants.MDMConstants;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;

import java.sql.ResultSetMetaData;

public class MDMInsertQueryExecution extends MDMQueryExecution{

	public MDMInsertQueryExecution(DataRepository dataRepository) {
                super(dataRepository);
	}
	
	@Override
	public ServiceReturnType execute(String query, List resultDefList){
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public String execute(String tableName, Column primaryKey, MDMQuery query) throws MDMRepositoryConnectionException, MDMDatabaseException, 
                                             MDMQueryFormationException {
        Connection connection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        String pkValue = null;
        String insertquery = null;
        MDMDatabaseException databaseException = null;
        
        connection = getGlobalConnection();
        try {
            preparestmt=connection.prepareStatement(MDMConstants.MDM_DB_SEQUENCE_QUERY);
            resultSet = preparestmt.executeQuery();
            if(resultSet.next())
            pkValue = resultSet.getString(1);
            resultSet.close();
            preparestmt.close();
        } catch (SQLException e) {
             e.printStackTrace();
             String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while executing the Query":e.getMessage());
             databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
             databaseException.setOriginalErrorCode(""+e.getErrorCode());
             databaseException.setSqlState(e.getSQLState());
             databaseException.setConnection(connection+"");
             databaseException.setStatement(preparestmt+"");
             databaseException.setResultSet(resultSet+"");
             databaseException.setQuery(MDMConstants.MDM_DB_SEQUENCE_QUERY);
             throw (databaseException);
        }
    
        try {
            query.addQueryItem(tableName, primaryKey.getType().toString(),primaryKey.getName(),pkValue);
            insertquery = query.getQuery();
            //System.out.println("[execute]: query to be executed - "+insertquery);
            preparestmt=connection.prepareStatement(insertquery);
            preparestmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while executing the Insert Query":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(),e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setQuery(insertquery);
            throw (databaseException);
        }
        finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while closing the Database Resources":e.getMessage());
                databaseException = new MDMDatabaseException(message,e.getCause(),e.getStackTrace());
                databaseException.setOriginalErrorCode(""+e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setConnection(connection+"");
                databaseException.setStatement(preparestmt+"");
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
        return pkValue;
    }

    @Override
    public List execute(String query) {
        return Collections.EMPTY_LIST;
    }

    public void execute(List queriesList) {}
}
