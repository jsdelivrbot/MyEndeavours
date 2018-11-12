package com.gssamerica.mdm.services.queries.execution;

import com.gssamerica.mdm.constants.MDMConstants;

import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.queries.MDMQuery;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;


public class MDMDeleteQueryExecution extends MDMQueryExecution {

    public MDMDeleteQueryExecution(DataRepository dataRepository) {
        super(dataRepository);
    }


    public void execute(List deleteQueries) throws MDMRepositoryConnectionException, 
                                                   MDMDatabaseException {
        Connection globalConnection = null;
        PreparedStatement preparestmt = null;

        String deletequery = null;

        try {
            globalConnection = getGlobalConnection();
            for (int i = 0; i < deleteQueries.size(); i++) {
                deletequery = (String)deleteQueries.get(i);
                //System.out.println("[MDMDeleteQueryExecution]: Delete query to be executed - " + deletequery);
                preparestmt = globalConnection.prepareStatement(deletequery);
                preparestmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MDMDatabaseException databaseException = 
                new MDMDatabaseException("Exception while executing the Query: " + 
                                         e.getMessage(), e.getCause(), 
                                         e.getStackTrace());
            databaseException.setOriginalErrorCode("" + e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(globalConnection + "");
            databaseException.setStatement(preparestmt + "");
            databaseException.setQuery(deletequery);
            throw (databaseException);
        } finally {
            try {
                if (preparestmt != null) {
                    preparestmt.close();
                }
            } catch (SQLException e) {
                MDMDatabaseException databaseException = 
                    new MDMDatabaseException("Exception while closing the Database Resources: " + 
                                             e.getMessage(), e.getCause(), 
                                             e.getStackTrace());
                databaseException.setOriginalErrorCode("" + e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setStatement(preparestmt + "");
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
    }

    @Override
    public List execute(String query) throws MDMRepositoryConnectionException, 
                                             MDMDatabaseException{
        Connection globalConnection = null;
        List resultList = null;
        List resultRow = null;
        PreparedStatement preparestmt = null;
        String resultColumn = null;
        int resultColumnCount = 0;
        ResultSet resultSet = null;
        globalConnection = getGlobalConnection();
        try {
            resultList = new ArrayList();
            preparestmt = globalConnection.prepareStatement(query);
            boolean resultSetExist = preparestmt.execute();
            if (resultSetExist) {
                resultSet = preparestmt.getResultSet();
                resultColumnCount = resultSet.getMetaData().getColumnCount();
                while (resultSet.next()) {
                    resultRow = new ArrayList();
                    for (int i = 1; i <= resultColumnCount; i++)  {
                        resultColumn = resultSet.getString(i);
                        resultRow.add(resultColumn);
                    }
                    resultList.add(resultRow);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MDMDatabaseException databaseException = 
                new MDMDatabaseException("Exception while executing the Query: " + 
                                         e.getMessage(), e.getCause(), 
                                         e.getStackTrace());
            databaseException.setOriginalErrorCode("" + e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(globalConnection + "");
            databaseException.setStatement(preparestmt + "");
            databaseException.setResultSet(resultSet + "");
            databaseException.setQuery(query);
            throw (databaseException);
        }
        return resultList;
    }

    public ServiceReturnType execute(String query, List resultDefList) {
        return null;
    }

    public String execute(String tableName, Column primaryKey, 
                          MDMQuery query) {
        return null;
    }
}

