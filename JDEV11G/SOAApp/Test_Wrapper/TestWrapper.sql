create or replace
package                   BPEL_COM_SRVC_LOOKUP_PKG
 as


 TYPE B_BASE_REC IS RECORD ( COMMON_SERVICE_NAME BASE_COMMON_SERVICE_LOOKUP.COMMON_SERVICE_NAME%TYPE,
                 COMMON_SERVICE_URL BASE_COMMON_SERVICE_LOOKUP.COMMON_SERVICE_URL%TYPE,
                 PAYLOAD_REQUIRED BASE_COMMON_SERVICE_LOOKUP.PAYLOAD_REQUIRED%TYPE,
                 STOPONFAILURE1 BASE_COMMON_SERVICE_LOOKUP.STOPONFAILURE1%TYPE,
                 STOPONFAILURE2 BASE_COMMON_SERVICE_LOOKUP.STOPONFAILURE2%TYPE,
                 STOPONFAILURE3 BASE_COMMON_SERVICE_LOOKUP.STOPONFAILURE3%TYPE,
                 STOPONFAILURE4 BASE_COMMON_SERVICE_LOOKUP.STOPONFAILURE4%TYPE,
                 STOPONFAILURE5 BASE_COMMON_SERVICE_LOOKUP.STOPONFAILURE5%TYPE);
 Type B_list is table of B_BASE_REC;
 
 PROCEDURE BPEL_COMM_SRVC_LOOKUP_PRC    (P_CALLER_NAME         IN VARCHAR2,                               
                                        P_SENDER_ID           IN VARCHAR2,
                                        P_TRXN_TYPE           IN VARCHAR2,
                                        P_TRXN_SUBTYPE        IN VARCHAR2,
                                        P_SOA_INSTANCE_ID     IN VARCHAR2,
                                        p_txn_record_id       IN NUMBER, --Added in phase3 by Kalpana S on 01-OCT-2012 Version 1.1
					p_temp_id       IN NUMBER,
                                        P_SERVER_NAME         OUT VARCHAR2,
                                        P_OUT                 OUT B_list,
                                        P_RESPONSECODE        OUT NUMBER,
                         P_ERRORCODENUMBER     OUT VARCHAR2,
                         P_ERRORDESCRIPTION    OUT VARCHAR2);
 END BPEL_COM_SRVC_LOOKUP_PKG; 







create or replace
PACKAGE BODY                   BPEL_COM_SRVC_LOOKUP_PKG
  AS


  V_TXN_DEF_RECORD_ID NUMBER(10) :=NULL;
  V_TRADING_PARTNER_RECORD_ID NUMBER(10) := NULL;
  UE1 EXCEPTION;
  l_source_object         VARCHAR2(40) := 'ETH_COM_SRVC_LOOKUP_PKG';
  x_retcode             VARCHAR2(40) := NULL;
  x_errbuf              VARCHAR2(500) := NULL ;
  l_error_subtype       base_errorlookup.error_subtype%type := NULL;  
  l_error_subsource     process_error.error_subsource%type := NULL;
  l_RESPONSECODE        NUMBER   :=0;
  l_ERRORCODENUMBER     base_errorlookup.ERROR_NUMBER%type := NULL;
  l_ERRORDESCRIPTION    base_errorlookup.error_description%type:= NULL ;
  l_RESPONSECODE_t1        NUMBER   :=0;
  l_ERRORCODENUMBER_t1     base_errorlookup.ERROR_NUMBER%type := NULL;
  l_ERRORDESCRIPTION_t1    base_errorlookup.error_description%type:= NULL ;

  l_master_record_id    process_master.master_record_id%type := NULL;
 
 
  
  PROCEDURE BPEL_COMM_SRVC_LOOKUP_PRC (P_CALLER_NAME         IN VARCHAR2,                               
                                         P_SENDER_ID           IN VARCHAR2,
                                         P_TRXN_TYPE           IN VARCHAR2,
                                         P_TRXN_SUBTYPE        IN VARCHAR2,
                                         P_SOA_INSTANCE_ID     IN VARCHAR2,
                                         p_txn_record_id       IN NUMBER, --Added in phase3 by Kalpana S on 01-OCT-2012 Version 1.1
					 p_temp_id       IN NUMBER,
                                         P_SERVER_NAME         OUT VARCHAR2,
                                         P_OUT                 OUT B_list,
                                         P_RESPONSECODE        OUT NUMBER,
                                         P_ERRORCODENUMBER     OUT VARCHAR2,
                                         P_ERRORDESCRIPTION    OUT VARCHAR2)
  AS
    BEGIN
        BEGIN
          SELECT TXN_DEF_RECORD_ID
          INTO V_TXN_DEF_RECORD_ID
          FROM BASE_TRANSACTIONDEFINITION
          WHERE TRANSACTION_TYPE  = NVL(P_TRXN_TYPE,TRANSACTION_TYPE)
          AND TRANSACTION_SUBTYPE = P_TRXN_SUBTYPE;
          --DBMS_OUTPUT.PUT_LINE('TXN_DEF_RECORD_ID:='||V_TXN_DEF_RECORD_ID);
        EXCEPTION
            WHEN OTHERS THEN
            p_OUT:=NULL;
            p_SERVER_NAME:=NULL;
            l_RESPONSECODE :=2;
            l_ERRORCODENUMBER  := SQLCODE;
            l_ERRORDESCRIPTION := SQLERRM||'-Error While Validating Transaction Type: '||P_TRXN_TYPE||', and Transaction Subtype: '||P_TRXN_SUBTYPE ||' From  BASE_TRANSACTIONDEFINITION';
            L_ERROR_SUBSOURCE := 'BASE_TRANSACTIONDEFINITION';
            --RETURN;
        END;
         
         IF l_RESPONSECODE=0 THEN
       
              BEGIN
                 SELECT TRADING_PARTNER_RECORD_ID
                 INTO V_TRADING_PARTNER_RECORD_ID
                 FROM BASE_TRADINGPARTNERINFO
                 WHERE TRADING_PARTNER_ID  = NVL(P_SENDER_ID,'ANY');
                --DBMS_OUTPUT.PUT_LINE('RADING_PARTNER_RECORD_ID:='||V_TRADING_PARTNER_RECORD_ID);
              EXCEPTION
                 WHEN OTHERS THEN
                   p_OUT:=NULL;
                   p_SERVER_NAME:=NULL;
                   l_RESPONSECODE :=2;
                   l_ERRORCODENUMBER  := SQLCODE;
                   l_ERRORDESCRIPTION := SQLERRM||'-Error While Validating Trading Partner: '||P_SENDER_ID||' From  BASE_TRADINGPARTNERINFO';
                   L_ERROR_SUBSOURCE := 'BASE_TRADINGPARTNERINFO';
              --    RETURN;
               END;
         
               IF l_RESPONSECODE=0 THEN
                
                 BEGIN
        
                   BEGIN
                      SELECT SERVER_NAME
                      INTO P_SERVER_NAME
                      FROM BASE_SERVER_DETAILS
                      WHERE TRUNC(SYSDATE) BETWEEN TRUNC(EFFECTIVEDATE_FROM) AND TRUNC(NVL(EFFECTIVEDATE_TO,SYSDATE+1))
                      AND ROWNUM=1; 
                   EXCEPTION
                    WHEN OTHERS THEN
                       p_OUT:=NULL;
                       p_SERVER_NAME:=NULL;
                       l_RESPONSECODE :=2;
                       l_ERRORCODENUMBER := SQLCODE;
                       l_ERRORDESCRIPTION :=SQLERRM||'-Error While Fetching Server Name From BASE_SERVER_DETAILS for server name: '||P_SERVER_NAME;
                       L_ERROR_SUBSOURCE := 'BASE_SERVER_DETAILS';
              --     RETURN;
                     END;
                    
                     IF l_RESPONSECODE=0 THEN--select BASE_COMMON_SERVICE_LOOKUP
                
                        SELECT COMMON_SERVICE_NAME,P_SERVER_NAME||COMMON_SERVICE_URL,PAYLOAD_REQUIRED,STOPONFAILURE1,STOPONFAILURE2,STOPONFAILURE3,STOPONFAILURE4,STOPONFAILURE5
                        BULK COLLECT INTO P_OUT
                        FROM BASE_COMMON_SERVICE_LOOKUP
                        WHERE TRADINGPARTNERID_FK=V_TRADING_PARTNER_RECORD_ID
                        AND TRANSACTIONID_FK=V_TXN_DEF_RECORD_ID
                        AND TRUNC(SYSDATE) BETWEEN TRUNC(EFFECTIVEDATE_FROM) AND TRUNC(NVL(EFFECTIVEDATE_TO,SYSDATE+1))
                        order by calling_sequence;

                         --DBMS_OUTPUT.PUT_LINE(P_OUT.COUNT);
          
                           IF P_OUT.COUNT < 1 THEN
                            RAISE UE1;
                          END IF;


                          l_RESPONSECODE :=0;
                          l_ERRORCODENUMBER:= NULL;
                          l_ERRORDESCRIPTION := NULL;
                        END IF;--select BASE_COMMON_SERVICE_LOOKUP

                        EXCEPTION
                          WHEN UE1 THEN
                           p_OUT:=NULL;
                           p_SERVER_NAME:=NULL;
                           l_RESPONSECODE :=2;
                           l_ERRORCODENUMBER := SQLCODE;
                           l_ERRORDESCRIPTION :=SQLERRM||'- No Data Found While Fetching Lookup Details From BASE_COMMON_SERVICE_LOOKUP';
                            ---RETURN;
           
                          WHEN OTHERS THEN
                               p_OUT:=NULL;
                               p_SERVER_NAME:=NULL;
                               l_RESPONSECODE :=2;
                               l_ERRORCODENUMBER := SQLCODE;
                               l_ERRORDESCRIPTION :=SQLERRM||'-Error While Fetching Base Service Lookup Details From BASE_COMMON_SERVICE_LOOKUP';
                               L_ERROR_SUBSOURCE := 'BASE_COMMON_SERVICE_LOOKUP';
                                -- RETURN;
                        END;
                    END IF;
                 END IF;

        
                 
                 
                 IF l_RESPONSECODE=2 AND L_ERROR_SUBSOURCE IS NOT NULL THEN
                 
                 --Call error routine and update status error prc
                        SELECT SUBSTR(l_ErrorDescription ,1,INSTR(l_ErrorDescription, '-', 1, 2)-1)
 		          INTO l_ERROR_SUBTYPE
 		          FROM dual;
 		          
                  
                 
                 SELECT header_id_fk 
                 INTO   l_master_record_id
                 from process_transaction 
                 where  transaction_record_id=p_txn_record_id;
                 
                              ETH_UPDATE_STATUS_PKG.UPDATE_TRXN_MSTR_ERR_PRC (p_caller_name,
		                                                              p_soa_instance_id,
		                                                              p_txn_record_id,
		                                                              l_master_record_id,
		                                                            'ERROR',
		                                                            l_Responsecode_t1,
		                                                            l_ErrorCodeNumber_t1,
		                                                            l_ErrorDescription_t1);
		                   IF l_Responsecode_t1 =0 THEN
		                      COMMIT;
		                    ELSE 
		                       ROLLBACK;
		 	              l_Responsecode :=2;
		 	              l_ErrorCodeNumber :=l_ErrorCodeNumber_t1;
		 	              l_ErrorDescription := l_ErrorDescription_t1;
		 	              l_error_subsource := 'PROCESS_MASTR OR PROCESS_TRANSACTION'; 
		 	              l_ERROR_SUBTYPE :=l_ErrorDescription;
	                            END IF; 
  
		          l_ErrorCodeNumber := 'DB-'||ABS(l_ErrorCodeNumber);--Added on 07th July
                 -------------------------------------------------------------------------------------
                   --Calling ETH_insert_error_prc procedure to insert errors into PROCESS_ERROR table --
                   -------------------------------------------------------------------------------------
                   ----DBMS_OUTPUT.PUT_LINE ('Call ETH_insert_error_prc procedure to insert errors into PROCESS_ERROR table for PROCESS_MASTER table');		           	     
         	      ETH_insert_error_prc (NULL,--l_trading_partner_id,
                                            NULL, -- Added By Yamini p_b2b_inbd_rec_id
         	                            l_master_record_id,--l_master_record_id_t,
         	                            p_txn_record_id,
         	                            NULL,--p_recipient_id,
         	                            l_ErrorCodeNumber,
         	                            l_ERROR_SUBTYPE,
         	   		            l_ErrorDescription, 
         	     		            NULL, --error_exception
         	       		            l_source_object||'.ETH_COMM_SRVC_LOOKUP_PRC',--ERROR SOURCE
         	       		            l_error_subsource,--ERROR SUBSOURCE
                                            NULL, -- Added By Yamini p_payload
         	       		            p_caller_name, 
         	       		            NULL,--p_soa_instance_id,
         	       		            x_retcode ,				 
         		                    x_errbuf );
         		                    
                    ----DBMS_OUTPUT.PUT_LINE('x_retcode from insert_error routine :'||x_retcode);
                    ----DBMS_OUTPUT.PUT_LINE('x_errbuf from insert_error routine :'||x_errbuf); 
                              IF x_retcode IS NOT NULL THEN 
     		                l_Responsecode := 98;
     		                l_ErrorDescription := l_ErrorDescription ||x_errbuf ;
     		                IF  x_retcode = 'DB-999999' THEN --Error is logged into PROCESS_ERROR table
     		 	           l_ErrorCodeNumber := x_retcode;
     		                END IF;
                             END IF;   
     	                            
	           
	           
                 END IF;
                 
            p_RESPONSECODE :=l_RESPONSECODE;
	    p_ERRORCODENUMBER :=l_ERRORCODENUMBER;
            p_ERRORDESCRIPTION := l_ERRORDESCRIPTION;
      
      
      EXCEPTION
               WHEN OTHERS THEN   
                       ROLLBACK;
                       p_Responsecode := 99;
                       l_ErrorCodeNumber := SQLCODE;
                       l_ErrorDescription := SQLERRM || ' -SYSTEM FAILURE'||'. While Fetching Lookup Details From BASE_COMMON_SERVICE_LOOKUP for trxn id: '||p_txn_record_id;
                       p_ErrorCodeNumber := l_ErrorCodeNumber;
                       p_ErrorDescription := l_ErrorDescription;     		
                                                             
                       SELECT SUBSTR(l_ErrorDescription ,1,INSTR(l_ErrorDescription, '-', 1, 2)-1)
                       INTO l_ERROR_SUBTYPE
                       FROM dual;   	   	       
                    	 -------------------------------------------------------------------------------------
                    	 --Calling ETH_insert_error_prc procedure to insert errors into PROCESS_ERROR table --
                    	 -------------------------------------------------------------------------------------		   	                         
                    	 ETH_insert_error_prc (NULL,--l_trading_partner_id,
                                             NULL, -- Added By Yamini p_b2b_inbd_rec_id
                    	                       l_master_record_id,--p_master_record_id,
                    	                       p_txn_record_id,--p_txn_record_id,
                    	                       NULL,--p_recipient_id,--p_recipient_id,
                    	                       'DB-'||ABS(l_ErrorCodeNumber), 
                    	                       l_ERROR_SUBTYPE,
                    	                       l_ErrorDescription, 
                    	                       NULL,
                    	                       l_source_object||'.ETH_COMM_SRVC_LOOKUP_PRC',--ERROR SOURCE
                    	                       'BASE_COMMON_SERVICE_LOOKUP', --ERROR SUBSOURCE
                                                 NULL, -- Added By Yamini p_payload
                    	                       p_caller_name,
                    	                       p_soa_instance_id,
                    	                       x_retcode , 
                    	                       x_errbuf  );  
                         x_errbuf:= l_ErrorDescription ||x_errbuf;   		   
                         
                     
                 
   
    END BPEL_COMM_SRVC_LOOKUP_PRC;
  
  
END BPEL_COM_SRVC_LOOKUP_PKG; 