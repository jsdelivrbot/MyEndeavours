package com.gssamerica.mdm.services.queries.execution;

import com.gssamerica.mdm.constants.MDMConstants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMQuery;


public class MDMAddKeyQueryExecution extends MDMQueryExecution {

    public MDMAddKeyQueryExecution(DataRepository dataRepository) {
        //this.dataRepository = dataRepository;
        super(dataRepository);
    }

    @Override
    public List execute(String query) throws MDMRepositoryConnectionException, 
                                             MDMDatabaseException {
        Connection connection = null;
        List resultList = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        String item = null;
        boolean result = false;

        connection = getGlobalConnection();
        try {
            resultList = new ArrayList();
            preparestmt = connection.prepareStatement(query);
            result = preparestmt.execute();
            if (result) {
                resultSet = preparestmt.getResultSet();
                while (resultSet.next()) {
                    item = resultSet.getString(1);
                    resultList.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MDMDatabaseException databaseException = new MDMDatabaseException("Exception while executing the Query: "+e.getMessage(),e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setResultSet(resultSet+"");
            databaseException.setQuery(query);
            throw (databaseException);
        }finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }				
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = new MDMDatabaseException("Exception while closing the Database Resources: "+e.getMessage(),e.getCause(),e.getStackTrace());
                databaseException.setOriginalErrorCode(""+e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setConnection(connection+"");
                databaseException.setStatement(preparestmt+"");
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
        
        return resultList;

    }

    @Override
    public ServiceReturnType execute(String query, List resultDefList) {
        return null;
    }

    @Override
    public String execute(String tableName, Column primaryKey, MDMQuery query) {
        return null;
    }

    public void execute(List queriesList) {}
}

