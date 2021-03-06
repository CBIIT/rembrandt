<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>

<%@ page import="gov.nih.nci.caintegrator.application.cache.CacheFactory" %>
<%@ page import="gov.nih.nci.caintegrator.application.cache.PresentationTierCache" %>
<%@ page import="gov.nih.nci.caintegrator.service.task.GPTask" %>
<%@ page import="gov.nih.nci.caintegrator.enumeration.FindingStatus" %>
<%@ page import="gov.nih.nci.caintegrator.application.analysis.gp.GenePatternIntegrationHelper" %>
<%@ page import="java.util.Collection" %>
<%@ page import="java.util.Iterator" %>

<style type="text/css">
 div.divHide {display:none;}
div.divShow	{
	display:block;
	position:relative;
	border: 0;
	}
</style>

<script type="text/javascript">
function checkJobId(jobList) {
//refresh the iframe first to keep the session alive
	$('pingFrame').src = $('pingFrame').src;
    if (jobList.options.length == 0) {
        alert("No GenePattern Job is available yet");
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
	String gpurl = (String)request.getSession().getAttribute("ticketString");
	String jobTitle = (String)request.getAttribute("taskModule");
	String gpTaskType = (String)request.getAttribute("gpTaskType");
	String goApplet = (String)request.getAttribute("goApplet");
	String indicator = "1";
	String actionLink1 = null;
	if (jobTitle == null) {
		if (gpTaskType != null && gpTaskType.equals("IGV")){ 
			indicator = "2";
			actionLink1 = "igvViewer.action?jobId=" + jobId;
			jobTitle = "IGV";
		}
		else {
			jobTitle = "GenePattern";
		}
	}
	else if (jobTitle != null && jobTitle.equalsIgnoreCase("HC.pipeline")){
		indicator = "2";
		actionLink1 = "hcApplet.action?jobId=" + jobId;
		jobTitle = "Hierarchical Clustering";
	} else if (jobTitle != null && jobTitle.equalsIgnoreCase("KNN.pipeline")){
		indicator = "2";
		actionLink1 = "knnApplet.action?jobId=" + jobId;
		jobTitle = "K-Nearest Neighbors";
	} else if (jobTitle != null && jobTitle.equalsIgnoreCase("CMS.pipeline")){
		indicator = "2";
		actionLink1 = "cmsApplet.action?jobId=" + jobId;
		jobTitle = "Comparative Marker Selection";
	}
%>
<script type="text/javascript">
	Event.observe(window, "load", function(){
	setTimeout ( "turnOffLoadingMessage()", 3000 );
});

function turnOffLoadingMessage(){
	var div = document.getElementById("advOptions");
	div.className = 'divHide';
}
</script>
<br/>
  
<app:cshelp topic="view_genepattern_job_help" />
     <fieldset>
     	<legend>GenePattern Modules <app:cshelp topic="genepattern_Modules_tooltip" text="[?]"/></legend>
     	<br/>
     	<s:form action="gpProcessStartApplet.action" id="qsForm" onsubmit="return checkJobId(document.forms[0].jobId);">
       		<table border="0" cellpadding="3" cellspacing="3">
       			<!-- s:if test="goApplet != null" -->
       			<% if (goApplet != null) { %>
       		    <tr><td colspan="3">
       		    	<div id="advOptions" class="divShow">
					<b>Please be patient, the viewer is loading...</b><br/> <img src="images/indicator.gif"/>
					<b>The viewer requires JVM 1.5 or above. </b><br/><br/>
					</div>
				</td></tr>
				<% } %>
				<!-- /s:if -->
       			<tr> 
       				<td width="20%">
       					<label for="<%= jobIdSelect %>">GP job Number</label>
       				</td>
       				<td colspan="2" >
       					<label for="<%= processSelect %>">GP Process</label>
       				</td>
       			</tr>
       			<!-- s:if test="jobId != null" -->
       			<% if (jobId != null) { %>
       			<tr>
       				<td>  
						<s:select id="jobIdSelect" disabled="true" style="width:100px" name="form.jobId" list="form.jobList" theme="simple">
 						</s:select>
					</td>
					<td>
						<s:select id="processSelect" disabled="true" style="width:200px" name="form.processName" list="form.processList" listKey="value" listValue="label" theme="simple">
 						</s:select>
					</td>
					<td>
						<s:submit cssClass="subButton" disabled="true" name="method" value="go" id="submitButton" theme="simple"> 
						</s:submit>
       				</td>
       			<% } %>
     			<!-- /s:if -->
     			<!-- s:if test="jobId == null" -->
     			<% if (jobId == null) { %>
     			<tr>
       				<td>  
						<s:select id="jobIdSelect" style="width:100px" name="form.jobId" list="form.jobList" theme="simple">
 						</s:select>
					</td>
					<td>
						<s:select id="processSelect" style="width:200px" name="form.processName" list="form.processList" listKey="value" listValue="label" theme="simple">
 						</s:select>
					</td>
					<td>
						<s:submit cssClass="subButton" name="method" value="go" id="submitButton" theme="simple"> 
						</s:submit>
       				</td>
       			<% } %>
     			<!-- /s:if -->
     		</s:form>
		</fieldset>
<br /><br />			
<br/>       
     <fieldset>
     	<legend>GenePattern Job Results <app:cshelp topic="genepattern_Jobresults_tooltip" text="[?]"/></legend>
     	<br/>
       	<div id="loadingMsg" style="color:red;font-weight:bold;">&nbsp;</div>
       		<table border="0" cellpadding="3" cellspacing="3">
       			<!-- s:if test="jobId != null" -->
       			<% if (jobId != null) { %>
       			<tr>
       				<td>
       				<% if (indicator.equals("2")) { %>
       					Your request has been sent to GenePattern for processing, and  
       					your job id is :  <span style="color:red;font-weight:bold"><%= jobId %></span>.
       					When your task is complete, your data will be ready 
       					for visualizer.  Just click <img src='images/visualizer.gif' border='0' alt='visualizer' id=\"" + jobId + "_image\" /> next to the link below to 
       					launch the visualizer you have selected.   
       					The approximate processing time is 2-8 minutes 
       					depending on the size of the dataset.<br><br>
       				<% } else { %> 
       					Your request has been sent to GenePattern for processing, and  
       					your job id is :  <span style="color:red;font-weight:bold"><%= jobId %></span>.
       					When your task is complete, your data will be ready 
       					for analysis in GenePattern.  Your available tasks will appear in the right 
       					sidebar of the GenePattern when they are ready.  The approximate 
       					processing time is 2-3 minutes.<br><br>
       				<% } %> 
						<!-- to check if the genepattern job is completed -->
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
					
						setTimeout("A_checkGenePatternStatus('<%= jobId %>', '<%= indicator %>')", 0200);
						var vr_checker = setInterval("A_checkGenePatternStatus('<%= jobId %>', '<%= indicator %>')", 5000);
	
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
						if(!currentStatus.equalsIgnoreCase("completed"))	{
							onclick = "javascript:alert('GenePattern Processing Not yet complete');return false;";
						}
						if (indicator.equals("2")){
							out.println("<a id=\"" + jobId + "_link\" href=\"" + gpurl + "\" onclick=\"" + onclick + "\" target=\"new\">" + jobTitle  + " Job# " + jobId + " (" +  resultName + ") </a>");
							out.println("<a id=\"" + jobId + "_v_link\" href=\"" + actionLink1 + "\" onclick=\"" + onclick + "\"" + ">" + "<img src='images/visualizer.gif' border='0' alt='visualizer' id=\"" + jobId + "_image\" /></a>");
						}else {
							out.println("<a id=\"" + jobId + "_link\" href=\"" + gpurl + "\" onclick=\"" + onclick + "\" target=\"new\">" + jobTitle  + " Job# " + jobId + " (" +  resultName + ") </a>");
						}
						out.println("<br clear=\"all\" />");
						out.println("<br clear=\"all\" />");
					%>
					</li></ul>
					</td>
				</tr>
				<% } %>
				<!-- /s:if -->
				<tr>
					<td>
						<!--  All available GenePattern jobs -->
					<ul>
					<% 
						PresentationTierCache ptc = CacheFactory.getPresentationTierCache();
						Collection tempGpTaskList = ptc.getAllSessionGPTasks(request.getSession().getId());
						String actionLink2 = null;
						if (gpurl == null) {
							gpurl = GenePatternIntegrationHelper.gpHomeURL(request);
						}
						//ping GP server here to open session
						%>
						<iframe id="pingFrame" title="pingFrame" height="1" size="1" style="display:none" src="<%=gpurl%>"></iframe>
						<% 
						//String jobId = (String)request.getAttribute("jobId");
						if (tempGpTaskList != null && !tempGpTaskList.isEmpty()){
							out.println("All available GenePattern jobs<br/><br/>");
							for (Iterator i = tempGpTaskList.iterator();i.hasNext();)	{
			
								GPTask task = (GPTask) i.next();
								if (task.getTaskModule() == null){
									if( task.getType().equals(GPTask.TaskType.IGV_GENE_EXP) || task.getType().equals(GPTask.TaskType.IGV_COPY_NUMBER)) {
										jobTitle = "IGV";
										actionLink2 = "igvViewer.action?jobId=" + task.getJobId();
										indicator = "2";
									}
									else {
										jobTitle = "GenePattern";
										indicator = "1";
									}
								}
								else if (task.getTaskModule().equalsIgnoreCase("HC.pipeline")){
									jobTitle = task.getTaskModuleDisplayName();
									actionLink2 = "hcApplet.action?jobId=" + task.getJobId();
									indicator = "2";
								}
								else if (task.getTaskModule().equalsIgnoreCase("KNN.pipeline")){
									jobTitle = task.getTaskModuleDisplayName();
									actionLink2 = "knnApplet.action?jobId=" + task.getJobId();
									indicator = "2";
								}
								else if (task.getTaskModule().equalsIgnoreCase("CMS.pipeline")){
									jobTitle = task.getTaskModuleDisplayName();
									actionLink2 = "cmsApplet.action?jobId=" + task.getJobId();
									indicator = "2";
								}
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
								if (indicator.equals("2")){
									out.println("<li><span id=\"" + task.getJobId() + "_link\" ></span><a id=\"" + task.getJobId() + "_link\" href=\"" + gpurl + "\" target=\"new\">" + jobTitle + " Job# " + task.getJobId() + " (" + task.getResultName() + ")</a>");
									out.println("<a id=\"" + task.getJobId() + "_v_link\" href=\"" + actionLink2 + "\"" + ">" + "<img src='images/visualizer.gif' border='0' alt=\"visualizer\" id=\"" + jobId + "_image\" /></a>");
								}
								else {
									out.println("<li><span id=\"" + task.getJobId() + "_link\" ></span><a id=\"" + task.getJobId() + "_link\" href=\"" + gpurl + "\" target=\"new\">" + jobTitle + " Job# " + task.getJobId() + " (" + task.getResultName() + ")</a>");
								}
								out.println("</li>");
								out.println("<br clear=\"all\" />");
								//out.println("<br clear=\"all\" />");
								//out.println("<a id=\"" + task.getJobId() + "_link\" href=\"" + gpurl + "\" target=\"new\">GenePattern Job " + task.getJobId() + " (" + task.getResultName() + ")</a>");
								
							}
						}
						else {
							out.println("<span style='color:red; float:left'>No GenePattern jobs available yet.</span> ");
						}
					%>
					</ul>
					</td>
				</tr>
				<tr>
					<td>
       					Please click the above link to launch GenePattern or <img src='images/visualizer.gif' border='0' alt='visualizer' id=\"" + jobId + "_image\" /> to launch Visualizer.  
       					If your task does not appear in 
       					the sidebar, please wait a minute and refresh the GenePattern page to try again. If some of your jobs are not listed in the
       					sidebar, they have been moved to the Job results menu.
       				</td>
     			</tr>
     			<tr>
					<td>
						<br/>
       					To Visualize the gene expression data using IGV Viewer, please follow the steps below: <br/>
       					1. Launch GenePattern application by clicking a GenePattern job above and download the GCT data locally. <br />
       					2. Click 'High Order Analysis' TAB and click the 'Launch IGV Viewer' button. <br />
       					3. This opens the IGV Viewer. Select 'File/Load From File' to select the local GCT file and visualize the data.
       				</td>
     			</tr>
     		</table>
		</fieldset>
<br /><br />




