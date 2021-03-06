<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ page import="java.util.*, java.text.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>
<%@ page import="gov.nih.nci.caintegrator.dto.query.QueryDTO,
				 gov.nih.nci.rembrandt.cache.RembrandtPresentationTierCache,
				 gov.nih.nci.rembrandt.web.bean.SessionQueryBag,
				 gov.nih.nci.rembrandt.web.factory.ApplicationFactory,
				 org.apache.log4j.Logger"%> 

<fieldset class="gray">
<legend class="red">

		<s:if test="principalComponentForm != null">
		Step 4: 
		</s:if>
		<s:if test="classComparisonForm != null">
		Step 4: 
		</s:if>
		<s:if test="gpIntegrationForm != null">
		Step 3: 
		</s:if>
		<s:if test="igvIntegrationForm != null">
		Step 4: 
		</s:if>	
		<s:if test="hierarchicalClusteringForm != null">
		Step 5: 
		</s:if>
		<label for="analysisResultName">Name Analysis Result</label>
<b class="req">*</b>
<%
	String act = request.getParameter("act") + "_Name_tooltip";
%>
<!--  <app:help help="Enter a unique name for the query." /> -->
<app:cshelp topic="<%=act%>" text="[?]"/>   
</legend>
<br>
	<s:fielderror fieldName="analysisResultName"/>
<s:textfield id="analysisResultName" name="form.analysisResultName" size="50" theme="simple" /> (should be unique)
<br />
<s:fielderror fieldName="queryName"/><br>
</fieldset>
<%
		final Logger logger = Logger.getLogger("analysisResultName_tile");
		String returnQueryNames = "";
		try	{
			RembrandtPresentationTierCache presentationTierCache = ApplicationFactory.getPresentationTierCache();
	
			if(presentationTierCache!=null){
				String sessionId = request.getSession().getId();
		  		SessionQueryBag queryCollection = presentationTierCache.getSessionQueryBag(sessionId);
				if (queryCollection != null) {
					   Collection queryDTOKeys = queryCollection.getQueryDTONames();
					   if(queryDTOKeys != null){
					   Iterator iter = queryDTOKeys.iterator();
					   while(iter.hasNext()){
						  String queryDTOKey = (String) iter.next();
						  QueryDTO queryDTO = queryCollection.getQueryDTO(queryDTOKey);
						  if(queryDTO!=null){
						  	String queryName = queryDTO.getQueryName();
						  	if (returnQueryNames.length() > 0){
						  	 returnQueryNames += ",";
						  	}
							if (queryName != null && queryName.trim().length() > 0){
								returnQueryNames += '"'+queryName+'"';
							}
						  }
					}
				}
			}
		}
	}
	catch(Exception e)	{
		logger.error("HalfPage: Try catch for analysisResultName_tile");
		logger.error(e);
		returnQueryNames = "";
	}

%>

<SCRIPT>
function checkQueryName(){

	var thisQueryName = document.forms[0].analysisResultName.value;
	
	<%
		out.println("\t\t\tvar queryNameArray = new Array("+returnQueryNames+");");
	%>
	var found = false;
	if (!(thisQueryName == null || thisQueryName == "")) {
		thisQueryName = encodeURIComponent(thisQueryName);
		for(var t=0;t<queryNameArray.length; t++)	{
		  if (thisQueryName == queryNameArray[t]) found = true;
		}
		if (found) {
			  if (confirm("Query Name exists  in system.  This action will overwrite existing query")) {
		  		return true;
			  }
	 	}else {return true;}
	 }
	 	
	 	return false;
 }
</SCRIPT>

