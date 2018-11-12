package com.gssamerica.mdm.services.queries.execution;

import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.constants.MDMConstants;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collections;
import java.util.List;

public class MDMUpdateQueryExecution extends MDMQueryExecution{
    public MDMUpdateQueryExecution(DataRepository dataRepository) {
        super(dataRepository);
    }
    
    @Override
    public ServiceReturnType execute(String query, List resultDefList) throws MDMRepositoryConnectionException, MDMDatabaseException{
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public String execute(String tableName, Column primaryKey, MDMQuery query) throws MDMRepositoryConnectionException, MDMDatabaseException, 
                                                 MDMQueryFormationException {
        Connection connection = null;
        PreparedStatement preparestmt = null;
        String pkValue = null;
        String updatequery = null;
        MDMDatabaseException databaseException = null;
                    
        try {
            connection = getGlobalConnection();
            updatequery = query.getQuery();
            preparestmt=connection.prepareStatement(updatequery);
            preparestmt.executeUpdate();
        }
        catch (SQLException e) {
            e.printStackTrace();
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while executing the Update Query":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setQuery(updatequery);
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
                databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
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
