package com.gssamerica.mdm.services.queries.execution;

import com.gssamerica.mdm.services.queries.MDMQueryResult;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.gssamerica.mdm.services.ServiceReturnType;
import com.gssamerica.mdm.services.db.DataRepository;
import com.gssamerica.mdm.services.db.castor.Column;
import com.gssamerica.mdm.services.exceptions.MDMDatabaseException;
import com.gssamerica.mdm.services.exceptions.MDMRepositoryConnectionException;
import com.gssamerica.mdm.services.queries.MDMQuery;
import com.gssamerica.mdm.services.search.MDMRecordsBean;
import com.gssamerica.mdm.services.search.SourceRecordsBean;
import com.gssamerica.mdm.constants.MDMConstants;

import com.gssamerica.mdm.services.exceptions.MDMQueryFormationException;

import com.gssamerica.mdm.services.exceptions.MDMRuntimeException;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

import java.util.Hashtable;

public abstract class MDMQueryExecution {

    protected static final String START_SAVEPOINT = "START";       
    private Hashtable configData = null;
    private Connection globalConnection = null;
    private DataRepository dataRepository = null;
    
    public MDMQueryExecution(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
        startExecution();
    }
    
    
    public abstract List execute(String query) throws MDMRepositoryConnectionException, MDMDatabaseException;
    
    public abstract ServiceReturnType execute(String query, List resultDefList) throws MDMRepositoryConnectionException, MDMDatabaseException, MDMRuntimeException ;
    
    public abstract String execute(String tableName, Column primaryKey, MDMQuery query) throws MDMRepositoryConnectionException, MDMDatabaseException, MDMQueryFormationException;
    
    public abstract void execute(List queriesList) throws MDMDatabaseException, MDMRepositoryConnectionException;
    
    
    public String getAutoID(String recordID) throws MDMRepositoryConnectionException, 
                                                MDMDatabaseException {
        Connection localConnection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        String queryString = null;
        String autoID = null;
        localConnection = getLocalConnection();
        
        try {
            queryString = "select PERSON from "+MDMConstants.MDM_CUSTOMER_TABLE+" where ACCOUNTID = (select ENTITYID from "+MDMConstants.MDM_ENTITY_LINK_TABLE+" where RECORDID="+recordID+")";
            preparestmt=localConnection.prepareStatement(queryString);
            resultSet = preparestmt.executeQuery();
            if(resultSet.next())
            autoID = resultSet.getString(1);
            resultSet.close();
            preparestmt.close();
        } catch (SQLException e) {
             e.printStackTrace();
             MDMDatabaseException databaseException = null;
             databaseException = new MDMDatabaseException("Exception while searching for AUTOID: "+e.getMessage(),e.getCause(), e.getStackTrace());
             databaseException.setOriginalErrorCode(""+e.getErrorCode());
             databaseException.setSqlState(e.getSQLState());
             databaseException.setConnection(localConnection+"");
             databaseException.setStatement(preparestmt+"");
             databaseException.setResultSet(resultSet+"");
             databaseException.setQuery(queryString);
             throw (databaseException);
        }finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }
                closeConnection(localConnection);
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = null;
                databaseException = new MDMDatabaseException("Exception while closing the Database Resources: "+e.getMessage(),e.getCause(),e.getStackTrace());
                databaseException.setOriginalErrorCode(""+e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setConnection(localConnection+"");
                databaseException.setStatement(preparestmt+"");
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
        return autoID;
    }
    public String insertMDMRecord(String entityId, String srcID, String srcKey) throws MDMRepositoryConnectionException, 
                                                    MDMDatabaseException {
        Connection connection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        String customerAccPk = null, mdmRecordPk = null, entityLinkPF = null, mdmMasterPk = null, srcTBID = null;
        String queryString = null;
        
        connection = getGlobalConnection();
        try {
                
            preparestmt=connection.prepareStatement(MDMConstants.MDM_DB_SEQUENCE_QUERY);
            resultSet = preparestmt.executeQuery();
            if(resultSet.next())
            customerAccPk = resultSet.getString(1);
            resultSet.close();
            preparestmt.close();
            
            queryString = "insert into "+MDMConstants.MDM_CUSTOMER_TABLE+" (ACCOUNTID, TYPE, PERSON) values ("+customerAccPk+", 1, "+entityId+")";
            preparestmt=connection.prepareStatement(queryString);
            preparestmt.executeUpdate();
            preparestmt.close();
            
            
            preparestmt=connection.prepareStatement(MDMConstants.MDM_DB_SEQUENCE_QUERY);
            resultSet = preparestmt.executeQuery();
            if(resultSet.next())
            mdmRecordPk = resultSet.getString(1);
            resultSet.close();
            preparestmt.close();
            
            queryString = "insert into "+MDMConstants.MDM_RECORD_TABLE+" (RECORDID, TABLEID) values ("+mdmRecordPk+", 2)";
            preparestmt=connection.prepareStatement(queryString);
            preparestmt.executeUpdate();
            preparestmt.close();
            
            queryString = MDMConstants.MDM_DB_SEQUENCE_QUERY;
            preparestmt=connection.prepareStatement(queryString);
            resultSet = preparestmt.executeQuery();
            if(resultSet.next())
            entityLinkPF = resultSet.getString(1);
            resultSet.close();
            preparestmt.close();
            
            queryString = "insert into "+MDMConstants.MDM_ENTITY_LINK_TABLE+" (LINKID, RECORDID, ENTITYID) values ("+entityLinkPF+", "+mdmRecordPk+", "+customerAccPk+")";
            preparestmt=connection.prepareStatement(queryString);
            preparestmt.executeUpdate();
            preparestmt.close();
            
            queryString = "select SOURCEID from "+MDMConstants.MDM_SOURCE_TABLE+" where SOURCESYSTEMID = '"+srcID+"'";
            preparestmt=connection.prepareStatement(queryString);
            resultSet = preparestmt.executeQuery();
            if(!resultSet.next()){
                resultSet.close();
                preparestmt.close();
                
                queryString = MDMConstants.MDM_DB_SEQUENCE_QUERY;
                preparestmt=connection.prepareStatement(queryString);
                resultSet = preparestmt.executeQuery();
                if(resultSet.next())
                srcTBID = resultSet.getString(1);
                resultSet.close();
                preparestmt.close();
                
                queryString = "insert into "+MDMConstants.MDM_SOURCE_TABLE+" (SOURCEID, SOURCESYSTEMID, SOURCENAME) values ("+srcTBID+", '"+srcID+"', '"+srcID+"')";
                preparestmt=connection.prepareStatement(queryString);
                preparestmt.executeUpdate();
                preparestmt.close();    
            }
            else{
                srcTBID = resultSet.getString(1);
                resultSet.close();
                preparestmt.close();
            }
                                
            queryString = MDMConstants.MDM_DB_SEQUENCE_QUERY;
            preparestmt=connection.prepareStatement(queryString);
            resultSet = preparestmt.executeQuery();
            if(resultSet.next())
            mdmMasterPk = resultSet.getString(1);
            resultSet.close();
            preparestmt.close();
            
            queryString = "insert into "+MDMConstants.MDM_MASTER_TABLE+" (MASTERID, RECORDID, SOURCEID, SOURCEKEY) values ("+mdmMasterPk+", "+mdmRecordPk+", "+srcTBID+", '"+srcKey+"')";
            preparestmt=connection.prepareStatement(queryString);
            preparestmt.executeUpdate();
            preparestmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
            MDMDatabaseException databaseException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while inserting MDMRecord":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setResultSet(resultSet+"");
            databaseException.setQuery(queryString);
            throw (databaseException);
        } 
        finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }                               
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = null;
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
        return mdmRecordPk;
    }
    
    public MDMRecordsBean searchMDMRecord(MDMRecordsBean mdmRecordsBean) throws MDMRepositoryConnectionException, 
                                                                            MDMDatabaseException {
        Connection connection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        List<String> entityIds = null;
        List<String> tables = null;
        String entityID = null, tableName = null;
        String accountid = null, recordid = null;
        String query = null;
        
        connection = getGlobalConnection();
        try {
                entityIds = mdmRecordsBean.getEntityID();
                tables = mdmRecordsBean.getTables();
                for(int i=0;i<entityIds.size();i++){
                    entityID = entityIds.get(i);
                    tableName = tables.get(i);
                    
                    query = "select "+MDMConstants.MDM_CUSTOMER_PK_COLUMN+" from "+MDMConstants.MDM_CUSTOMER_TABLE+" where "+MDMConstants.MDM_CUSTOMER_PERSON_COLUMN+" = "+entityID;
                    //System.out.println("[MDMQueryExecution] Executing MDMRecord Query 1 - "+query);
                    preparestmt=connection.prepareStatement(query);
                    resultSet = preparestmt.executeQuery();
                    if(resultSet.next())
                        accountid = resultSet.getString(1);
                    resultSet.close();
                    preparestmt.close();
                    
                    query = "select "+MDMConstants.MDM_ENTITY_LINK_RECORD_COLUMN+" from "+MDMConstants.MDM_ENTITY_LINK_TABLE+" where"+" "+MDMConstants.MDM_ENTITY_LINK_COLUMN+" = "+accountid;
                    //System.out.println("[MDMQueryExecution] Executing MDMRecord Query 2 - "+query);
                    preparestmt=connection.prepareStatement(query);
                    resultSet = preparestmt.executeQuery();
                    if(resultSet.next())
                        recordid = resultSet.getString(1);
                    resultSet.close();
                    preparestmt.close();
                    
                    mdmRecordsBean.addAccountID(accountid);
                    mdmRecordsBean.addRecordID(recordid);
                    mdmRecordsBean.setSourceRecords(getRemoteIds(recordid));
                }                    
        }catch(SQLException e){
            e.printStackTrace();
            MDMDatabaseException databaseException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while searching MDMRecord":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setResultSet(resultSet+"");
            databaseException.setQuery(query);
            throw (databaseException);
        }
        finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = null;
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
       return mdmRecordsBean;
    }
    private SourceRecordsBean getRemoteIds(String recordId) throws MDMRepositoryConnectionException, 
                                                               MDMDatabaseException {
        Connection connection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        SourceRecordsBean sourceRecordsBean = null;
        String query = null;
        String masterId = null, sourceId = null, sourceKey = null;
        try {
            sourceRecordsBean = new SourceRecordsBean();
            connection = getGlobalConnection();
            query = "select "+MDMConstants.MDM_MASTER_PK_COLUMN+", SOURCESYSTEMID, SOURCEKEY from "+
            MDMConstants.MDM_MASTER_TABLE+" NATURAL JOIN "+MDMConstants.MDM_SOURCE_TABLE+" where recordId = "+recordId;
            
            //System.out.println("[MDMQueryExecution] Executing Remote IDs Query - "+query);
            preparestmt=connection.prepareStatement(query);
            resultSet = preparestmt.executeQuery();
            while(resultSet.next()){
                masterId = resultSet.getString(1);
                sourceId = resultSet.getString(2);
                sourceKey = resultSet.getString(3);
                sourceRecordsBean.addMasterID(masterId);
                sourceRecordsBean.addSourceID(sourceId);
                sourceRecordsBean.addSourceKey(sourceKey);
            }                
        }catch(SQLException e){
            e.printStackTrace();
            MDMDatabaseException databaseException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while getting Remote IDs":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(connection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setResultSet(resultSet+"");
            databaseException.setQuery(query);
            throw (databaseException);
        } 
        finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = null;
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
        return sourceRecordsBean;
    }
    
    public Hashtable getLinkIDs(MDMQuery query) throws MDMQueryFormationException, 
                                                   MDMRepositoryConnectionException, 
                                                   MDMDatabaseException {
        Connection localConnection = null;
        PreparedStatement preparestmt = null;
        ResultSet resultSet = null;
        List queryResultColumnList = null;
        String queryString = null;
        MDMQueryResult queryResultColumn = null;
        String resultData = null;
        Hashtable linkIds = null;
        try {
            linkIds = new Hashtable();
            queryResultColumnList = query.getQueyResult();
            queryString = query.getQuery();
            if (queryString!=null && !queryString.equalsIgnoreCase("")) {
                //System.out.println("[MDMQueryExecution.getLinkIDs] query - "+queryString);
                localConnection = getLocalConnection();
                preparestmt=localConnection.prepareStatement(queryString);
                resultSet = preparestmt.executeQuery();
                while(resultSet.next()){
                    for (int i = 0; i < queryResultColumnList.size(); i++)  {
                        resultData = null;
                        queryResultColumn = (MDMQueryResult)queryResultColumnList.get(i);
                        resultData = resultSet.getString(i+1);
                        linkIds.put(queryResultColumn.getColumnName().toLowerCase(),resultData);
                    }          
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            MDMDatabaseException databaseException = null;
            String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while getting Remote IDs":e.getMessage());
            databaseException = new MDMDatabaseException(message,e.getCause(), e.getStackTrace());
            databaseException.setOriginalErrorCode(""+e.getErrorCode());
            databaseException.setSqlState(e.getSQLState());
            databaseException.setConnection(localConnection+"");
            databaseException.setStatement(preparestmt+"");
            databaseException.setResultSet(resultSet+"");
            databaseException.setQuery(queryString);
            throw (databaseException);
        }finally{
            try {
                if(preparestmt!=null){
                    preparestmt.close();
                }
                closeConnection(localConnection);
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = null;
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while closing the Database Resources":e.getMessage());
                databaseException = new MDMDatabaseException(message,e.getCause(),e.getStackTrace());
                databaseException.setOriginalErrorCode(""+e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setConnection(localConnection+"");
                databaseException.setStatement(preparestmt+"");
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
        return linkIds;
    }
    
    protected Connection getLocalConnection() throws MDMRepositoryConnectionException {
        Connection connection = null;
        if(dataRepository==null){
                
        }
        connection = dataRepository.getConnection();
        return connection;
    }
    
    protected Connection getGlobalConnection() throws MDMRepositoryConnectionException {
        if(globalConnection==null){
            MDMRepositoryConnectionException repoException = new MDMRepositoryConnectionException("[MDMQueryExecution] Global Connection Found NULL... Error might occured while creating the connection");
            throw (repoException);
        }
        return globalConnection;
    }
    
    //protected void closeConnection(Connection connection){}
    
    protected void closeConnection(Connection connection){
        if(connection!=null){
            try {
                connection.close();
                System.out.println("Connection Closed...");    
            } catch (SQLException e) {
                e.printStackTrace();
                MDMDatabaseException databaseException = null;
                String message = ((e.getMessage()==null)||(e.getMessage().equalsIgnoreCase(""))?"Exception while closing DB Connection":e.getMessage());
                databaseException = new MDMDatabaseException(message,e.getCause(),e.getStackTrace());
                databaseException.setOriginalErrorCode(""+e.getErrorCode());
                databaseException.setSqlState(e.getSQLState());
                databaseException.setConnection(connection+"");
                databaseException.setSeverity(MDMConstants.SEVERITY_LOW);
                // not throwing
            }
        }
    }
    public void commitExecution() throws MDMDatabaseException {
        try {
            this.globalConnection.commit();
            System.out.println("Execution Commited...");
        } catch (SQLException e) {
             e.printStackTrace();
             MDMDatabaseException databaseException = new MDMDatabaseException("Exception while Commiting the GlobalExecution: "+e.getMessage(),e.getCause(), e.getStackTrace());
             databaseException.setOriginalErrorCode(""+e.getErrorCode());
             databaseException.setSqlState(e.getSQLState());
             databaseException.setConnection(globalConnection+"");
             throw (databaseException);
        }
    }
    public void rollbackExecution() throws MDMDatabaseException {
        try {
            this.globalConnection.rollback();
            System.out.println("Execution Rollback...");
        } catch (SQLException e) {
             e.printStackTrace();
             MDMDatabaseException databaseException = new MDMDatabaseException("Exception while Rollback the GlobalExecution: "+e.getMessage(),e.getCause(), e.getStackTrace());
             databaseException.setOriginalErrorCode(""+e.getErrorCode());
             databaseException.setSqlState(e.getSQLState());
             databaseException.setConnection(globalConnection+"");
             throw (databaseException);
        }
    }
    public void startExecution(){
        try {
            this.globalConnection = this.dataRepository.getConnection();
            this.globalConnection.setAutoCommit(false);
            System.out.println("Execution Started..."); 
        } catch (Exception e) {
            e.printStackTrace();
            // can't throw so cant' log
            // If generated, later errors will be generated and will be logged
        }
    }
    
    public void closeExecution(){
        try {
            closeConnection(this.globalConnection);
            System.out.println("Execution Closed..."); 
        }
        catch (Exception e) {
            e.printStackTrace();
            // no action
            // should not throw any exception
        }
    }

    public void setConfigData(Hashtable configData) {
        this.configData = configData;
    }

    public Hashtable getConfigData() {
        return this.configData;
    }
}
