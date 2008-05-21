
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-nested.tld" prefix="nested" %>

<%@ page import="gov.nih.nci.caintegrator.application.cache.CacheFactory" %>
<%@ page import="gov.nih.nci.caintegrator.application.cache.PresentationTierCache" %>
<%@ page import="gov.nih.nci.caintegrator.service.task.GPTask" %>
<%@ page import="gov.nih.nci.caintegrator.enumeration.FindingStatus" %>
<%@ page import="gov.nih.nci.caintegrator.application.analysis.gp.GenePatternIntegrationHelper" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>


<script type="text/javascript">
function checkJobId(jobList) {
    if (jobList.options.length == 0) {
        alert("No Gene Pattern Job is available yet");
        return false;
    } else {
        return true;
    }
}
</script>

<% 
	String jobId = (String)request.getAttribute("jobId"); 
	String jobIdSelect = (String)request.getAttribute("jobIdSelect");
	String processSelect = (String)request.getAttribute("processSelect");
	String submitButton = (String)request.getAttribute("submitButton");
	String gpurl = (String)request.getAttribute("genePatternURL");
%>
<br/>
<script type="text/javascript">Help.insertHelp("genepattern_job_list", " align='right'", "padding:2px;");</script>
     <fieldset>
     	<legend>Gene Pattern Modules</legend>
     	<br/>
     	<html:form method="post" target="_blank" action="/gpProcess.do?method=startApplet" styleId="qsForm" onsubmit="return checkJobId(document.forms[0].jobId);">
       		<table border="0" cellpadding="3" cellspacing="3">
       			<tr> 
       				<td width="20%">
       					GP job Number
       				</td>
       				<td colspan="2" >
       					GP Process
       				</td>
       			</tr>
       			<logic:present name="jobId" >
       			<tr>
       				<td>  
						<html:select property="jobId" style="width:100px"
							disabled="true" styleId="<%= jobIdSelect %>">
								<html:options property="jobList" />
						</html:select>
					</td>
					<td>
						<html:select property="processName" style="width:200px"
							disabled="true" styleId="<%= processSelect %>">
								<html:options property="processList" />
						</html:select>
					</td>
					<td>
						<html:submit styleClass="subButton" disabled="true" property="method" value="go" styleId="<%= submitButton %>"> 
						</html:submit>
       				</td>
     			</tr>
     			</logic:present>
     			<logic:notPresent name="jobId" >
       			<tr>
       				<td>  
						<html:select property="jobId" styleId="geneList" style="width:100px" styleId="<%= jobIdSelect %>">
								<html:options property="jobList" />
						</html:select>
					</td>
					<td>
						<html:select property="processName" styleId="geneList" style="width:200px" styleId="<%= processSelect %>">
								<html:options property="processList" />
						</html:select>
					</td>
					<td>
						<html:submit styleId="submittalButton"  styleClass="subButton" property="method" value="go" styleId="<%= submitButton %>"> 
						</html:submit>
       				</td>
     			</tr>
     			</logic:notPresent>
     		</table>
     		</html:form>
		</fieldset>
<br /><br />			
<script type="text/javascript">Help.insertHelp("genepattern_job_result", " align='right'", "padding:2px;");</script>
<br/>       
     <fieldset>
     	<legend>Gene Pattern Job Results</legend>
     	<br/>
       	<div id="loadingMsg" style="color:red;font-weight:bold;">&nbsp;</div>
       		<table border="0" cellpadding="3" cellspacing="3">
       			<logic:present name="jobId" >
       			<tr>
       				<td>
       					Your request has been sent to GenePattern for processing, and  
       					your job id is :  <span style="color:red;font-weight:bold"><bean:write name="jobId"/></span>.
       					When your task is complete, your data will be ready 
       					for analysis in GenePattern.  Your available tasks will appear in the right 
       					sidebar of the GenePattern when they are ready.  The approximate 
       					processing time is 2-3 minutes.<br><br>
       				
						<!-- to check if the gene pattern job is completed -->
					</td>
				</tr>
				<tr>
					<td>
					<ul><li>
						
					<% 
						String resultName = (String)request.getAttribute("resultName");
						String currentStatus = (String)request.getAttribute("gpStatus");
					%>	
					<script language="javascript" src="js/a_genePattern.js"></script>
					<script type='text/javascript' src='dwr/interface/GenePatternHelper.js'></script>
					<script language="javascript">	
						//testMap("testingtesting");
						var customError = function(message)	{};
						DWREngine.setWarningHandler(customError);
						DWREngine.setErrorHandler(customError);
					
						setTimeout("A_checkGenePatternStatus('<%= jobId %>')", 0200);
						var vr_checker = setInterval("A_checkGenePatternStatus('<%= jobId %>')", 5000);
	
					</script>
				
					<%
						//Check completion status
						if(currentStatus.equalsIgnoreCase("completed"))
							currentStatus = "<b id=\"" + jobId + "_status\">completed</b>  <img src='images/check.png' alt='complete' id=\"" + jobId + "_image\"/>";
						else if(currentStatus.equalsIgnoreCase("running"))
							currentStatus = "<b id=\"" + jobId + "_status\" >running</b> <img src='images/circle.gif' alt='running' id=\"" + jobId + "_image\" />";
						else if(currentStatus.equalsIgnoreCase("error"))	{
					
							String comments = "An error occured during sending your job request to GenePattern or during GenePattern processing";
							currentStatus = "<b id=\"" + jobId + "_status\" ><script language=\"javascript\">document.write(showErrorHelp('"+comments+"','error'));</script></b> <img src='images/error.png' alt='error' id=\"" + jobId + "_image\" />";
						}
					
						out.println("<span style='color:red; float:right'>" + currentStatus + "</span> ");
					
						String onclick="";	
						if(!currentStatus.equals("completed"))	{
							onclick = "javascript:alert('Gene Pattern Processing Not yet complete');return false;";
						}
						out.println("<a id=\"" + jobId + "_link\" href=\"" + gpurl + "\" onclick=\"" + onclick + "\" target=\"new\">GenePattern Job " + jobId + " (" +  resultName + ") </a>");
					
						out.println("<br clear=\"all\" />");
						out.println("<br clear=\"all\" />");
					%>
					</li></ul>
					</td>
				</tr>
				</logic:present>
				<tr>
					<td>
						All available Gene Pattern jobs
					<ul>
					<% 
						PresentationTierCache ptc = CacheFactory.getPresentationTierCache();
						Collection tempGpTaskList = ptc.getAllSessionGPTasks(request.getSession().getId());

						if (gpurl == null) {
							gpurl = GenePatternIntegrationHelper.gpHomeURL(request);
						}
						//String jobId = (String)request.getAttribute("jobId");
						if (tempGpTaskList != null && !tempGpTaskList.isEmpty()){
							for (Iterator i = tempGpTaskList.iterator();i.hasNext();)	{
			
								GPTask task = (GPTask) i.next();
							
								if (jobId != null && jobId.equals(task.getJobId()))
									continue;
								String nowStatus = "";
								if(task.getStatus() == FindingStatus.Completed)
									nowStatus = "<b id=\"" + task.getJobId() + "_status\">completed</b>  <img src='images/check.png' alt='complete' id=\"" + task.getJobId() + "_image\"/>";
								else if(task.getStatus() == FindingStatus.Error) {
									String comments = "An error occured during sending your job request to GenePattern or during GenePattern processing";
									nowStatus = "<b id=\"" + task.getJobId() + "_status\" ><script language=\"javascript\">document.write(showErrorHelp('"+comments+"','error'));</script></b> <img src='images/error.png' alt='error' id=\"" + task.getJobId() + "_image\" />";
								}
								out.println("<span style='color:red; float:right'>" + nowStatus + "</span> ");
								
								out.println("<li><span id=\"" + task.getJobId() + "_link\" ></span><a id=\"" + task.getJobId() + "_link\" href=\"" + gpurl + "\" target=\"new\">GenePattern Job " + task.getJobId() + " (" + task.getResultName() + ")</a>");
								out.println("</li>");
								out.println("<br clear=\"all\" />");
								//out.println("<br clear=\"all\" />");
								//out.println("<a id=\"" + task.getJobId() + "_link\" href=\"" + gpurl + "\" target=\"new\">GenePattern Job " + task.getJobId() + " (" + task.getResultName() + ")</a>");
								
							}
						}
						else {
							out.println("<span style='color:red; float:left'>No Gene Pattern jobs available yet.</span> ");
						}
					%>
					</ul>
					</td>
				</tr>
				<tr>
					<td>
       					Please click the above link to launch GenePattern.  If your task does not appear in 
       					the sidebar, please wait a minute and refresh the GenePattern page to try again.
       				</td>
     			</tr>
     		</table>
		</fieldset>
<br /><br />



