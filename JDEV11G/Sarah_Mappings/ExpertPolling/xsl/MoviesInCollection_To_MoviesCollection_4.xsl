<?xml version="1.0" encoding="UTF-8" ?>
<?oracle-xsl-mapper
  <!-- SPECIFICATION OF MAP SOURCES AND TARGETS, DO NOT MODIFY. -->
  <mapSources>
    <source type="WSDL">
      <schema location="../NoAfterRead.wsdl"/>
      <rootElement name="MoviesInCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/NoAfterRead"/>
    </source>
  </mapSources>
  <mapTargets>
    <target type="WSDL">
      <schema location="../Merge.wsdl"/>
      <rootElement name="MoviesCollection" namespace="http://xmlns.oracle.com/pcbpel/adapter/db/top/Merge"/>
    </target>
  </mapTargets>
  <!-- GENERATED BY ORACLE XSL MAPPER 11.1.1.0.0(build 090527.1400.5130) AT [FRI JUN 19 16:32:05 PDT 2009]. -->
?>
<xsl:stylesheet version="1.0"
                xmlns:xpath20="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.Xpath20"
                xmlns:bpws="http://schemas.xmlsoap.org/ws/2003/03/business-process/"
                xmlns:mhdr="http://www.oracle.com/XSL/Transform/java/oracle.tip.mediator.service.common.functions.MediatorExtnFunction"
                xmlns:oraext="http://www.oracle.com/XSL/Transform/java/oracle.tip.pc.services.functions.ExtFunc"
                xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                xmlns:dvm="http://www.oracle.com/XSL/Transform/java/oracle.tip.dvm.LookupValue"
                xmlns:hwf="http://xmlns.oracle.com/bpel/workflow/xpath"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:med="http://schemas.oracle.com/mediator/xpath"
                xmlns:ids="http://xmlns.oracle.com/bpel/services/IdentityService/xpath"
                xmlns:xdk="http://schemas.oracle.com/bpel/extension/xpath/function/xdk"
                xmlns:ns0="http://xmlns.oracle.com/pcbpel/adapter/db/AdaptersDB/ExpertPolling/Merge%2F"
                xmlns:xref="http://www.oracle.com/XSL/Transform/java/oracle.tip.xref.xpath.XRefXPathFunctions"
                xmlns:plt="http://schemas.xmlsoap.org/ws/2003/05/partner-link/"
                xmlns:xsd="http://www.w3.org/2001/XMLSchema"
                xmlns:ns1="http://xmlns.oracle.com/pcbpel/adapter/db/top/Merge"
                xmlns:top="http://xmlns.oracle.com/pcbpel/adapter/db/top/NoAfterRead"
                xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                xmlns:ora="http://schemas.oracle.com/xpath/extension"
                xmlns:socket="http://www.oracle.com/XSL/Transform/java/oracle.tip.adapter.socket.ProtocolTranslator"
                xmlns:tns="http://xmlns.oracle.com/pcbpel/adapter/db/AdaptersDB/ExpertPolling/NoAfterRead%2F"
                exclude-result-prefixes="xsi xsl plt xsd top wsdl tns ns0 ns1 xpath20 bpws mhdr oraext dvm hwf med ids xdk xref ora socket">
  <xsl:template match="/">
    <ns1:MoviesCollection>
      <xsl:for-each select="/top:MoviesInCollection/top:MoviesIn">
        <ns1:Movies>
          <ns1:title>
            <xsl:value-of select="top:title"/>
          </ns1:title>
          <xsl:if test="top:director">
            <ns1:director>
              <xsl:if test="top:director/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:director/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:director"/>
            </ns1:director>
          </xsl:if>
          <xsl:if test="top:starring">
            <ns1:starring>
              <xsl:if test="top:starring/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:starring/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:starring"/>
            </ns1:starring>
          </xsl:if>
          <xsl:if test="top:synopsis">
            <ns1:synopsis>
              <xsl:if test="top:synopsis/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:synopsis/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:synopsis"/>
            </ns1:synopsis>
          </xsl:if>
          <xsl:if test="top:genre">
            <ns1:genre>
              <xsl:if test="top:genre/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:genre/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:genre"/>
            </ns1:genre>
          </xsl:if>
          <xsl:if test="top:runTime">
            <ns1:runTime>
              <xsl:if test="top:runTime/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:runTime/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:runTime"/>
            </ns1:runTime>
          </xsl:if>
          <xsl:if test="top:releaseDate">
            <ns1:releaseDate>
              <xsl:if test="top:releaseDate/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:releaseDate/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:releaseDate"/>
            </ns1:releaseDate>
          </xsl:if>
          <xsl:if test="top:rated">
            <ns1:rated>
              <xsl:if test="top:rated/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:rated/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:rated"/>
            </ns1:rated>
          </xsl:if>
          <xsl:if test="top:rating">
            <ns1:rating>
              <xsl:if test="top:rating/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:rating/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:rating"/>
            </ns1:rating>
          </xsl:if>
          <xsl:if test="top:viewerRating">
            <ns1:viewerRating>
              <xsl:if test="top:viewerRating/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:viewerRating/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:viewerRating"/>
            </ns1:viewerRating>
          </xsl:if>
          <xsl:if test="top:status">
            <ns1:status>
              <xsl:if test="top:status/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:status/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:status"/>
            </ns1:status>
          </xsl:if>
          <xsl:if test="top:totalGross">
            <ns1:totalGross>
              <xsl:if test="top:totalGross/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:totalGross/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:totalGross"/>
            </ns1:totalGross>
          </xsl:if>
          <xsl:if test="top:deleted">
            <ns1:deleted>
              <xsl:if test="top:deleted/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:deleted/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:deleted"/>
            </ns1:deleted>
          </xsl:if>
          <xsl:if test="top:sequenceno">
            <ns1:sequenceno>
              <xsl:if test="top:sequenceno/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:sequenceno/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:sequenceno"/>
            </ns1:sequenceno>
          </xsl:if>
          <xsl:if test="top:lastUpdated">
            <ns1:lastUpdated>
              <xsl:if test="top:lastUpdated/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:lastUpdated/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:lastUpdated"/>
            </ns1:lastUpdated>
          </xsl:if>
          <xsl:if test="top:pollingStrategy">
            <ns1:pollingStrategy>
              <xsl:if test="top:pollingStrategy/@xsi:nil">
                <xsl:attribute name="xsi:nil">
                  <xsl:value-of select="top:pollingStrategy/@xsi:nil"/>
                </xsl:attribute>
              </xsl:if>
              <xsl:value-of select="top:pollingStrategy"/>
            </ns1:pollingStrategy>
          </xsl:if>
          <ns1:movieReviewsCollection>
            <xsl:for-each select="top:movieReviewsInCollection/top:MovieReviewsIn">
              <ns1:MovieReviews>
                <ns1:critic>
                  <xsl:value-of select="top:critic"/>
                </ns1:critic>
                <xsl:if test="top:source">
                  <ns1:source>
                    <xsl:if test="top:source/@xsi:nil">
                      <xsl:attribute name="xsi:nil">
                        <xsl:value-of select="top:source/@xsi:nil"/>
                      </xsl:attribute>
                    </xsl:if>
                    <xsl:value-of select="top:source"/>
                  </ns1:source>
                </xsl:if>
                <xsl:if test="top:grade">
                  <ns1:grade>
                    <xsl:if test="top:grade/@xsi:nil">
                      <xsl:attribute name="xsi:nil">
                        <xsl:value-of select="top:grade/@xsi:nil"/>
                      </xsl:attribute>
                    </xsl:if>
                    <xsl:value-of select="top:grade"/>
                  </ns1:grade>
                </xsl:if>
                <xsl:if test="top:quote">
                  <ns1:quote>
                    <xsl:if test="top:quote/@xsi:nil">
                      <xsl:attribute name="xsi:nil">
                        <xsl:value-of select="top:quote/@xsi:nil"/>
                      </xsl:attribute>
                    </xsl:if>
                    <xsl:value-of select="top:quote"/>
                  </ns1:quote>
                </xsl:if>
                <xsl:if test="top:deleted">
                  <ns1:deleted>
                    <xsl:if test="top:deleted/@xsi:nil">
                      <xsl:attribute name="xsi:nil">
                        <xsl:value-of select="top:deleted/@xsi:nil"/>
                      </xsl:attribute>
                    </xsl:if>
                    <xsl:value-of select="top:deleted"/>
                  </ns1:deleted>
                </xsl:if>
                <xsl:if test="top:sequenceno">
                  <ns1:sequenceno>
                    <xsl:if test="top:sequenceno/@xsi:nil">
                      <xsl:attribute name="xsi:nil">
                        <xsl:value-of select="top:sequenceno/@xsi:nil"/>
                      </xsl:attribute>
                    </xsl:if>
                    <xsl:value-of select="top:sequenceno"/>
                  </ns1:sequenceno>
                </xsl:if>
                <xsl:if test="top:lastUpdated">
                  <ns1:lastUpdated>
                    <xsl:if test="top:lastUpdated/@xsi:nil">
                      <xsl:attribute name="xsi:nil">
                        <xsl:value-of select="top:lastUpdated/@xsi:nil"/>
                      </xsl:attribute>
                    </xsl:if>
                    <xsl:value-of select="top:lastUpdated"/>
                  </ns1:lastUpdated>
                </xsl:if>
              </ns1:MovieReviews>
            </xsl:for-each>
          </ns1:movieReviewsCollection>
        </ns1:Movies>
      </xsl:for-each>
    </ns1:MoviesCollection>
  </xsl:template>
</xsl:stylesheet>