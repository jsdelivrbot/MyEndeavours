#!/bin/ksh
WORKING_DIR=/apps/elshas1/aelshas1/OFM_Scripts/HttpAccess_Scripts
FAILED_URLs_FILE=FailedURLs.txt
CURRENT_DATE=`date +"%d %B, %Y %H:%M %p %Z"`
echo "Changing to Working Directory"
cd $WORKING_DIR
echo "Removing the old file"
rm -r $FAILED_URLs_FILE
echo "Execute the JAR"
java -jar Http_Check.jar
echo "Test if file is created"

if test -f "$FAILED_URLs_FILE"
then 
 echo "$FAILED_URLs_FILE does exist - Sending Mail"
 cat "$FAILED_URLs_FILE" | mailx -s "Alert :ICoE Script Report for HTTP URLs ACCESS: $CURRENT_DATE" -r "HTTP_ACCESS@elshap1.emerson.com" DLITSSFusionSupport@Emerson.com Anand.Natarajan@Emerson.com
 echo "Successfully sent email"
else 
 echo "$FAILED_URLs does not exist - No Action"
fi