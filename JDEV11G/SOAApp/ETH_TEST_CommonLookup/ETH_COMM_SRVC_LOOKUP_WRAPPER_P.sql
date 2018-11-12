-- Declare the SQL type for the PL/SQL type ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC
-- uncomment to overwrite existing types
-- CREATE OR REPLACE TYPE ETH_COMM_SRVC_LOOX143989X1X8 AS OBJECT (
--       COMMON_SERVICE_NAME VARCHAR2(150),
--       COMMON_SERVICE_URL VARCHAR2(500),
--       PAYLOAD_REQUIRED VARCHAR2(5),
--       STOPONFAILURE1 VARCHAR2(10),
--       STOPONFAILURE2 VARCHAR2(10),
--       STOPONFAILURE3 VARCHAR2(10),
--       STOPONFAILURE4 VARCHAR2(10),
--       STOPONFAILURE5 VARCHAR2(10)-- 
-- );
-- /
-- show errors
-- uncomment to overwrite existing types
-- CREATE OR REPLACE TYPE ETH_COMM_SRVC_LOOX143989X1X7 AS TABLE OF ETH_CUSTOM_SCHEMA.ETH_COMM_SRVC_LOOX143989X1X8; 
-- /
-- show errors
-- Declare package containing conversion functions between SQL and PL/SQL types
CREATE OR REPLACE PACKAGE ETH_COMM_SRVC_LOOKUP_WRAPPER_P AS
	-- Declare the conversion functions the PL/SQL type ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC
	FUNCTION PL_TO_SQL1(aPlsqlItem ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC)
 	RETURN ETH_COMM_SRVC_LOOX143989X1X8;
	FUNCTION SQL_TO_PL1(aSqlItem ETH_COMM_SRVC_LOOX143989X1X8)
	RETURN ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC;
	-- Declare the conversion functions the PL/SQL type ETH_COMM_SRVC_LOOKUP_PKG.T_LIST
	FUNCTION PL_TO_SQL0(aPlsqlItem ETH_COMM_SRVC_LOOKUP_PKG.T_LIST)
 	RETURN ETH_COMM_SRVC_LOOX143989X1X7;
	FUNCTION SQL_TO_PL0(aSqlItem ETH_COMM_SRVC_LOOX143989X1X7)
	RETURN ETH_COMM_SRVC_LOOKUP_PKG.T_LIST;
   PROCEDURE eth_comm_srvc_lookup_pkg$eth_ (P_CALLER_NAME VARCHAR2,
	P_SENDER_ID VARCHAR2,
	P_TRXN_TYPE VARCHAR2,
	P_TRXN_SUBTYPE VARCHAR2,
	P_SOA_INSTANCE_ID VARCHAR2,
	P_SERVER_NAME OUT VARCHAR2,
	P_OUT OUT ETH_CUSTOM_SCHEMA.ETH_COMM_SRVC_LOOX143989X1X7,
	P_RESPONSECODE OUT NUMBER,
	P_ERRORCODENUMBER OUT VARCHAR2,
	P_ERRORDESCRIPTION OUT VARCHAR2
	);
END ETH_COMM_SRVC_LOOKUP_WRAPPER_P;
/
show errors
CREATE OR REPLACE PACKAGE BODY ETH_COMM_SRVC_LOOKUP_WRAPPER_P IS
	FUNCTION PL_TO_SQL1(aPlsqlItem ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC)
 	RETURN ETH_COMM_SRVC_LOOX143989X1X8 IS 
	aSqlItem ETH_COMM_SRVC_LOOX143989X1X8; 
	BEGIN 
		-- initialize the object
		aSqlItem := ETH_COMM_SRVC_LOOX143989X1X8(NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
		aSqlItem.COMMON_SERVICE_NAME := aPlsqlItem.COMMON_SERVICE_NAME;
		aSqlItem.COMMON_SERVICE_URL := aPlsqlItem.COMMON_SERVICE_URL;
		aSqlItem.PAYLOAD_REQUIRED := aPlsqlItem.PAYLOAD_REQUIRED;
		aSqlItem.STOPONFAILURE1 := aPlsqlItem.STOPONFAILURE1;
		aSqlItem.STOPONFAILURE2 := aPlsqlItem.STOPONFAILURE2;
		aSqlItem.STOPONFAILURE3 := aPlsqlItem.STOPONFAILURE3;
		aSqlItem.STOPONFAILURE4 := aPlsqlItem.STOPONFAILURE4;
		aSqlItem.STOPONFAILURE5 := aPlsqlItem.STOPONFAILURE5;
		RETURN aSqlItem;
	END PL_TO_SQL1;
	FUNCTION SQL_TO_PL1(aSqlItem ETH_COMM_SRVC_LOOX143989X1X8) 
	RETURN ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC IS 
	aPlsqlItem ETH_COMM_SRVC_LOOKUP_PKG.T_BASE_REC; 
	BEGIN 
		aPlsqlItem.COMMON_SERVICE_NAME := aSqlItem.COMMON_SERVICE_NAME;
		aPlsqlItem.COMMON_SERVICE_URL := aSqlItem.COMMON_SERVICE_URL;
		aPlsqlItem.PAYLOAD_REQUIRED := aSqlItem.PAYLOAD_REQUIRED;
		aPlsqlItem.STOPONFAILURE1 := aSqlItem.STOPONFAILURE1;
		aPlsqlItem.STOPONFAILURE2 := aSqlItem.STOPONFAILURE2;
		aPlsqlItem.STOPONFAILURE3 := aSqlItem.STOPONFAILURE3;
		aPlsqlItem.STOPONFAILURE4 := aSqlItem.STOPONFAILURE4;
		aPlsqlItem.STOPONFAILURE5 := aSqlItem.STOPONFAILURE5;
		RETURN aPlsqlItem;
	END SQL_TO_PL1;
	FUNCTION PL_TO_SQL0(aPlsqlItem ETH_COMM_SRVC_LOOKUP_PKG.T_LIST)
 	RETURN ETH_COMM_SRVC_LOOX143989X1X7 IS 
	aSqlItem ETH_COMM_SRVC_LOOX143989X1X7; 
	BEGIN 
		-- initialize the table 
		aSqlItem := ETH_COMM_SRVC_LOOX143989X1X7();
		IF aPlsqlItem IS NOT NULL THEN
		aSqlItem.EXTEND(aPlsqlItem.COUNT);
		IF aPlsqlItem.COUNT>0 THEN
		FOR I IN aPlsqlItem.FIRST..aPlsqlItem.LAST LOOP
			aSqlItem(I + 1 - aPlsqlItem.FIRST) := PL_TO_SQL1(aPlsqlItem(I));
		END LOOP; 
		END IF; 
		END IF; 
		RETURN aSqlItem;
	END PL_TO_SQL0;
	FUNCTION SQL_TO_PL0(aSqlItem ETH_COMM_SRVC_LOOX143989X1X7) 
	RETURN ETH_COMM_SRVC_LOOKUP_PKG.T_LIST IS 
	aPlsqlItem ETH_COMM_SRVC_LOOKUP_PKG.T_LIST; 
	BEGIN 
		aPlsqlItem := ETH_COMM_SRVC_LOOKUP_PKG.T_LIST();
		aPlsqlItem.EXTEND(aSqlItem.COUNT);
		IF aSqlItem.COUNT>0 THEN
		FOR I IN 1..aSqlItem.COUNT LOOP
			aPlsqlItem(I) := SQL_TO_PL1(aSqlItem(I));
		END LOOP; 
		END IF;
		RETURN aPlsqlItem;
	END SQL_TO_PL0;

   PROCEDURE eth_comm_srvc_lookup_pkg$eth_ (P_CALLER_NAME VARCHAR2,
	P_SENDER_ID VARCHAR2,
	P_TRXN_TYPE VARCHAR2,
	P_TRXN_SUBTYPE VARCHAR2,
	P_SOA_INSTANCE_ID VARCHAR2,
	P_SERVER_NAME OUT VARCHAR2,
	P_OUT OUT ETH_CUSTOM_SCHEMA.ETH_COMM_SRVC_LOOX143989X1X7,
	P_RESPONSECODE OUT NUMBER,
	P_ERRORCODENUMBER OUT VARCHAR2,
	P_ERRORDESCRIPTION OUT VARCHAR2
	) IS
 P_OUT_ ETH_CUSTOM_SCHEMA.ETH_COMM_SRVC_LOOKUP_PKG.T_LIST;
   BEGIN
      ETH_CUSTOM_SCHEMA.ETH_COMM_SRVC_LOOKUP_PKG.ETH_COMM_SRVC_LOOKUP_PRC(P_CALLER_NAME,
	P_SENDER_ID,
	P_TRXN_TYPE,
	P_TRXN_SUBTYPE,
	P_SOA_INSTANCE_ID,
	P_SERVER_NAME,
	P_OUT_,
	P_RESPONSECODE,
	P_ERRORCODENUMBER,
	P_ERRORDESCRIPTION
	);
 P_OUT := ETH_COMM_SRVC_LOOKUP_WRAPPER_P.PL_TO_SQL0(P_OUT_);
   END eth_comm_srvc_lookup_pkg$eth_;

END ETH_COMM_SRVC_LOOKUP_WRAPPER_P;
/
show errors
exit