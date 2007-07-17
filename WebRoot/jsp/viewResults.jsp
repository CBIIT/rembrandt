<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%@ page import="java.util.*, java.lang.*, java.io.*, java.net.URLEncoder " %>
<%@ page import="gov.nih.nci.rembrandt.web.factory.ApplicationFactory" %>
<%@ page import="gov.nih.nci.caintegrator.application.cache.BusinessTierCache" %>
<%@ page import="gov.nih.nci.caintegrator.service.findings.*" %>
<%@ page import="gov.nih.nci.caintegrator.enumeration.*" %>
<%@ page import="org.apache.commons.lang.StringEscapeUtils" %>

<script language="javascript">
	if(location.href.indexOf("viewResults") == -1)	{
		location.replace("viewResults.do");
	}
</script>
<!--
<script type="text/javascript" src="soundMgr/soundmanager.js"></script>
<script type="text/javascript">soundManagerInit();</script>
-->
<%
String helpLink = "<a href=\"javascript: spawn('help.jsp";
String helpLinkClose = "', 350, 500);\">"+
				"<img align=\"right\" src=\"images/help.png\" border=\"0\" "+
				"onmouseover=\"return overlib('Click here for additional information about the view results page.', CAPTION, 'Help', OFFSETX, -50);\" onmouseout=\"return nd();\">"+
				"</a><br clear=\"all\">";
%> 


  <script type="text/javascript">Help.insertHelp("View_results_overview", " align='right'", "padding:2px;");</script>
    <br />
    <form action="#">     
      <fieldset>
        <legend>
          Query Results
        </legend>
        <br/>
        <logic:notEmpty name="viewResultsForm" property="compoundQueries">
        
        <table align="center" border="0" width="95%" cellpadding="2" cellspacing="1" id="rosso">
		  <tr>
            <td class="message">Compound Query</td>
            <td class="message">Institutions</td> 
            <td class="message">View</td>
          </tr>
          <nested:iterate name="viewResultsForm" 
				property="compoundQueries" 
				id="compoundQuery"  
				indexId="index">
              <tr style="background-color:#f2f2f2;font-size:.8em">
              <td><table><tr>
                 <td><bean:write name="compoundQuery" property="queryNameWithLink" 	filter="false"/></td>
                 </tr>
                 </table>
                </td>
                
                
                <td>
                	<nested:notEmpty name="compoundQuery" property="institutionCriteria">
          			<nested:iterate name="compoundQuery" 
						property="institutionCriteria.institutions" 
						id="institute"  
						indexId="index">
						<nested:write name ="institute" property="instituteName"/><br><br>
                	</nested:iterate>
                	</nested:notEmpty>
	            </td>
	            
	            <td>
	            	<nested:notEmpty name="compoundQuery" property="validViewStrings" >
          			<nested:iterate name="compoundQuery"
          				property="validViewStrings" 
						id="validView"  
						indexId="index">
						<logic:notEqual name="validView" property="value" value="2">
						<a href="#" onclick="javascript:spawnx('runCompoundQuery.do?method=runCompoundQueryReport&queryName=<nested:write name="compoundQuery" property="queryName"/>&typeOfView=<nested:write name="validView" property="value"/>',770,550,'_report');return false;" 
							style="font-size:.9em" target="_blank">
						<nested:write name="validView" property="label"/></a><br><br>
						</logic:notEqual>
                	</nested:iterate>
                	</nested:notEmpty>
	            </td>	  
              </tr>
          </nested:iterate>
         </table>
         
     
     </logic:notEmpty>
     
     <logic:empty name="viewResultsForm" property="compoundQueries">
     <strong>There are no compound queries to execute at this time.</strong>
     <br /><br />
     </logic:empty>
        
     </fieldset>
     <br /><br />
     <!--  <form> -->
     
     <fieldset>
     	<legend>High Order Analysis</legend>
       	<div id="loadingMsg" style="color:red;font-weight:bold;">&nbsp;</div>
     <%
     	//get the finding related HOA's
     	//will use scriptlet, as we arent accessing a Struts related form
     	//PresentationTierCache ptc = ApplicationFactory.getPresentationTierCache();
		BusinessTierCache btc = ApplicationFactory.getBusinessTierCache();
		Collection sessionFindings = btc.getAllSessionFindings(session.getId());
		if(sessionFindings!=null && !sessionFindings.isEmpty())	{
		
			//looks like we have some findings, generate the JS to check the status of them
			%>
			<script language="javascript" src="js/a_functions.js"></script>
			<script language="javascript">	
				//testMap("testingtesting");
				var customError = function(message)	{};
				DWREngine.setWarningHandler(customError);
				DWREngine.setErrorHandler(customError);
				
				setTimeout("A_checkAllFindingStatus('<%=session.getId()%>')", 0200);
				var vr_checker = setInterval("A_checkAllFindingStatus('<%=session.getId()%>')", 5000);

			</script>
			<%
			
			//why arent these in the backingbean?  - we need to check these directly from cache
			// because they are dynamic - we can not look at a copy placed in the backing bean
			//for(Object o : sessionFindings)	{ //no 1.5 stuff allowed
			
			boolean displayableFindings = false;
			
			for(Iterator i = sessionFindings.iterator();i.hasNext();)	{
	
				// Finding f = (Finding) o;
				Finding f = (Finding) i.next();
				String qname = "N/A";
				//q&d
				try	{
					qname =  f.getQueryDTO().getQueryName();
				}
				catch(Exception e)	{
					qname = f.getTaskId();
				}
				
				String comments = "";
				String _htm = "";
				
				String currentStatus = "running";
				if(f.getStatus() == FindingStatus.Completed)
					currentStatus = "<b id=\"" +f.getTaskId() + "_status\">completed</b>  <img src='images/check.png' alt='complete' id=\"" + f.getTaskId() + "_image\"/>";
				else if(f.getStatus() == FindingStatus.Running)
					currentStatus = "<b id=\"" + f.getTaskId() + "_status\" >running</b> <img src='images/circle.gif' alt='running' id=\"" + f.getTaskId() + "_image\" />";
				else if(f.getStatus() == FindingStatus.Error)	{
					comments = f.getStatus().getComment() != null ? f.getStatus().getComment() : "Unspecified Error";
					//currentStatus = "<b id=\"" + f.getTaskId() + "_status\" ><a href=\"#\" onmouseover=\"return overlibWrapper('"+comments+"');return false;\" onmouseout=\"return nd();\" ><strong>error</strong></a></b> <img src='images/error.png' alt='error' id=\"" + f.getTaskId() + "_image\" />";
					comments = StringEscapeUtils.escapeJavaScript(comments);
					currentStatus = "<b id=\"" + f.getTaskId() + "_status\" ><script language=\"javascript\">document.write(showErrorHelp('"+comments+"','error'));</script></b> <img src='images/error.png' alt='error' id=\"" + f.getTaskId() + "_image\" />";
				}
				
				_htm += "<span style='color:red; float:right'>" + currentStatus + "</span> ";
				
				String onclick="";	
				if(f.getStatus()!= FindingStatus.Completed)	{
					onclick = "javascript:alert('Analysis Not yet complete');return false;";
				}
				
				//check the type of finding and create the appropriate link
				if(f instanceof ClassComparisonFinding){
					displayableFindings = true;
					_htm += "<li><a id=\"" + f.getTaskId() + "_link\" href=\"javascript:spawnx('testReport.do?key=' + encodeURIComponent('" + URLEncoder.encode(f.getTaskId()) + "') + '&newReport=true', 750, 500,'hoa_report');\" onclick=\"" + onclick + "\">" + qname + "</a> <i>(CC)</i> ";
				}
				else if(f instanceof HCAFinding){
					displayableFindings = true;
					_htm += "<li><a id=\"" + f.getTaskId() + "_link\" href=\"javascript:spawnx('hcReport.do?key=' + encodeURIComponent('" + URLEncoder.encode(f.getTaskId()) + "') + ', 750, 500,'hoa_report');\" onclick=\"" + onclick + "\">" + qname + " </a> <i>(HC)</i> ";
				}
				else if(f instanceof PrincipalComponentAnalysisFinding){
					displayableFindings = true;
					_htm += "<li><a id=\"" + f.getTaskId() + "_link\" href=\"javascript:spawnx('pcaReport.do?key=' + encodeURIComponent('" + URLEncoder.encode(f.getTaskId()) + "') + ', 900, 600,'hoa_report');\" onclick=\"" + onclick + "\">" + qname + "</a> <i>(PCA)</i> ";
				}
				else if(f instanceof FTestFinding){
					displayableFindings = true;
					_htm += "<li><a id=\"" + f.getTaskId() + "_link\" href=\"javascript:spawnx('testReport.do?key=' + encodeURIComponent('" + URLEncoder.encode(f.getTaskId()) + "') + ', 750, 500,'hoa_report');\" onclick=\"" + onclick + "\">" + qname + "</a> <i>(CC)</i> ";
				}
				else	{
					//skip this one, as its not a valid finding to show
					continue;
				}
				
				out.println(_htm);
				_htm = "";
				out.println("<span style=\"font-size:10px\">(elapsed time: <span id=\"" + f.getTaskId() + "_time\" >" + f.getElapsedTime() + "</span>ms) </span>");
				out.println("</li>");
				out.println("<br clear=\"all\" />");
				out.println("<br clear=\"all\" />");
			}
			if(displayableFindings == false)	{
				out.println("<strong>No HOA Results at this time.</strong><br/><br/>");
			}
		}
		else	{
			//no findings yet
			out.println("<strong>No HOA Results at this time.</strong><br/><br/>");
		}
     
     
     %>
     <br/><br/>
     <div style="font-size:9px;text-align:center;">
     (CC) Class Comparison | 
     (HC) Hierarchical Clustering | 
     (PCA) Principal Component Analysis
     </div>
     
</fieldset>
<br /><br />

	<!--  BEGIN BULK DOWNLOAD SECTION -->
	<script type="text/javascript">
		function getDL(sel)	{
			var dl = sel.value;
			var lnk = "fileDownload.do?method=brbFileDownload&fileId=";
			window.location.href=lnk+dl;
		}
	</script>

      <fieldset style="background:transparent;background-color:#fff;">
        <legend>
          Bulk Downloads
        </legend>
        <br/>
        <logic:notEmpty name="downloadFileList">
        	<select>
        		<option>BRB Format</option>
        	</select>
        	<select id="idfile" style="width:300px">
		        <logic:iterate name="downloadFileList" id="downloadFile">
			       <option value="<bean:write name="downloadFile" property="fileId"/>"><bean:write name="downloadFile" property="fileName"/></option>
		        </logic:iterate>
	        </select>
	        <input type="button" onclick="getDL($('idfile'))" value="download" style="width:70px"/>
     	</logic:notEmpty>
	    <logic:empty name="downloadFileList">
		    <strong>There are no files to download at this time.</strong>
		    <br /><br />
	    </logic:empty>
     	<br/><br/>
	    &nbsp;&nbsp;&nbsp;
	    <a href="http://linus.nci.nih.gov/BRB-ArrayTools.html" target="_blank"><span style="font-size:.8em;text-align:left;"> BRB-Array Tools </span></a>
	  	<br /><br />   
	  </fieldset>
	  <br/><br/>