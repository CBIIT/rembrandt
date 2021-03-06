<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>
<%@ page import="java.util.*"%>
<%@ page import="gov.nih.nci.caintegrator.application.analysis.gp.GenePatternIntegrationHelper" %>
	 

<%
int ccAnalysisNum = 0;
String ccAnalysisString = "0";
int hcAnalysisNum = 0;
String hcAnalysisString = "0";
int pcaAnalysisNum = 0;
String pcaAnalysisString = "0";
String sessionId = request.getSession().getId();
String gpHomeURL = GenePatternIntegrationHelper.gpHomeURL(request);
gpHomeURL = gpHomeURL + "&target=new";
%>
<br clear="both"/>
<div>
  <fieldset>
		<legend>High Order Analysis:</legend>
		<br clear="both"/>
		<s:fielderror fieldName="org.apache.struts.action.GLOBAL_ERROR"/>
		<app:cshelp topic="HOA_overview" />
		
			<table border="0" cellpadding="10" cellspacing="3">
				<tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Class Comparison Analysis" onclick="javascript:location.href='classcomparisonInit.action';"></td></tr>				
				<tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Principal Component Analysis (PCA)" onclick="javascript:location.href='principalcomponentInit.action';"></td></tr>				
				<tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Hierarchical Clustering Analysis" onclick="javascript:location.href='hierarchicalclusteringInit.action';"></td></tr>			
				<tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Send Data to IGV Viewer" onclick="javascript:location.href='igvintegrationInit.action';"></td></tr>
			    <tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Launch IGV Viewer" onclick="javascript:location.href='igvProcess.action';"></td></tr>
				<tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Send Data to GenePattern" onclick="javascript:location.href='gpintegrationInit.action';"></td></tr>			
			    <tr><td><input type="button" class="xbutton" style="width:200px;margin-bottom: 5px;" value="Launch GenePattern Application" onclick="window.open( '<%= gpHomeURL %>');"></td></tr>	
		</table>
	</fieldset>
</div>
<br/><br/>