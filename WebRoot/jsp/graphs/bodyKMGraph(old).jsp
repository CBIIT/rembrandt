<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri='/WEB-INF/caintegrator-graphing.tld' prefix='graphing' %>

<%
 String helpLink = "<a href=\"javascript: spawn('help.jsp";
 String helpLinkClose = "', 350, 500);\">"+
				"<img align=\"right\" src=\"images/help.png\" border=\"0\" "+
				"onmouseover=\"return overlib('Click here for additional information about this report.', CAPTION, 'Help', OFFSETX, -50);\" onmouseout=\"return nd();\">"+
				"</a><br clear=\"all\">";
%>
<div>
<html:errors/>

<%
   String km = "kmplotGE";
   if(  ((request.getParameter("plot") != null) && 
         (request.getParameter("plot").equalsIgnoreCase("kapMaiPlotGE")))
           || 
        ((request.getParameter("plot") == null) && 
         (request.getParameter("plotType").equalsIgnoreCase("GE_KM_PLOT"))) ){
         km = "kmplotGE";
   }
   if(  ((request.getParameter("plot") != null) && 
         (request.getParameter("plot").equalsIgnoreCase("kapMaiPlotCN")))
           || 
        ((request.getParameter("plot") == null) && 
         (request.getParameter("plotType").equalsIgnoreCase("COPY_NUM_KM_PLOT"))) ){
        km = "kmplotCN";
   }
	
%>


<%=helpLink%>?sect=<%=km%><%=helpLinkClose%>


        
</div>

<html:form action="/kmGraph.do?method=redrawKMPlot">
  <html:hidden property="geneOrCytoband"/>
  <html:hidden property="plotType"/>
  <div>
    <b>
      <bean:write name="kmDataSetForm" property="upOrAmplified"/> <!-- Upregulated/Amplified  -->
      &nbsp;&ge;&nbsp;
      <html:select property="upFold">
        <html:options property="folds"/>
      </html:select>
      &nbsp;
      <bean:write name="kmDataSetForm" property="changeType"/> <!--Fold/Copies -->
      &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
      <bean:write name="kmDataSetForm" property="downOrDeleted"/> <!-- Downregulated/Deleted -->
      &nbsp;&ge;&nbsp;
      <html:select property="downFold">
        <html:options property="folds"/>
      </html:select>
      &nbsp;<bean:write name="kmDataSetForm" property="changeType"/> <!--Fold/Copies -->
      &nbsp;&nbsp;Reporters
      <html:select property="selectedReporter">
        <html:options property="reporters"/>
      </html:select>
      &nbsp;
      &nbsp;&nbsp;
    </b>
    <html:submit value="Redraw Graph"/>
  </div>
  <div>
  <!-- TAG CREATION WOULD NEED TO HAVE THE FOLLOWING-->
  <logic:equal name="kmDataSetForm" property="plotVisible" value="true">
    <hr>
    <b>
    <font size="+1">
      <bean:write name="kmDataSetForm" property="chartTitle"/>
    </font>
    <p>
    <!-- INSERT CHART HERE -->
    <graphing:KaplanMeierPlot/>
    <p>
    <br>
    <!-- Statistical Data  STARTS HERE -->
    <fieldset class="gray" style="text-align:left">
    <legend class="red">Statistical Report: </legend>
<table class="graphTable" border="0" cellpadding="2" cellspacing="0">
    <logic:present name="kmDataSetForm" property="geneOrCytoband">
    <tr><td colspan="2" id="reportBold"><bean:write name="kmDataSetForm" property="geneOrCytoband" /></td>
    </tr>
    </logic:present>
    <logic:present name="kmDataSetForm" property="selectedReporter">
	<tr><td colspan="2" id="reportBold">Reporter: <bean:write name="kmDataSetForm" property="selectedReporter" /></td>
	</tr>
	</logic:present>
    <tr><td colspan="2">&nbsp;</td></tr>
	<tr>
	    <td colspan="2" id="reportBold">Number of samples in group:</td>
	</tr>
	
	<logic:greaterThan name="kmDataSetForm" property="upSampleCount" value="0">
	<tr>
	    <td><bean:write name="kmDataSetForm" property="upOrAmplified"/></td>
	    <td><bean:write name="kmDataSetForm" property="upSampleCount" /></td>
	</tr>
	</logic:greaterThan>
	<logic:greaterThan name="kmDataSetForm" property="downSampleCount" value="0">
	<tr>
	    <td><bean:write name="kmDataSetForm" property="downOrDeleted"/></td>
	    <td><bean:write name="kmDataSetForm" property="downSampleCount" /></td>
	</tr>
	</logic:greaterThan>
	<logic:greaterThan name="kmDataSetForm" property="intSampleCount" value="0">
	<tr>
	    <td>Intermediate:</td>
	    <td><bean:write name="kmDataSetForm" property="intSampleCount"/></td>
	</tr>
	</logic:greaterThan>
	<tr><td colspan="2"><hr width="100%" size="1" color="black" /></td>
	</tr>
	
	<tr>
	   <td id="reportBold" colspan="3">Log-rank p-value(for significance of difference of survival between group of samples)</td>
	</tr>
	<logic:greaterThan name="kmDataSetForm" property="upVsIntPvalue" value="-100">
	    <td><bean:write name="kmDataSetForm" property="upOrAmplified" /> vs. Intermediate: </td>
        <td><bean:write name="kmDataSetForm" property="upVsIntPvalue" /></td>
    </tr>
    </logic:greaterThan>
    
    <logic:greaterThan name="kmDataSetForm" property="upVsDownPvalue" value="-100">
    <tr>
	   <td><bean:write name="kmDataSetForm" property="upOrAmplified" />
	   vs.<bean:write name="kmDataSetForm" property="downOrDeleted" /></td>
       <td><bean:write name="kmDataSetForm" property="upVsDownPvalue" /></td>
    </tr>
    </logic:greaterThan>
    
    <logic:greaterThan name="kmDataSetForm" property="downVsIntPvalue" value="-100">
    <tr>
	   <td><bean:write name="kmDataSetForm" property="downOrDeleted" />
	    vs. Intermediate: </td>
       <td><bean:write name="kmDataSetForm" property="downVsIntPvalue"/></td>
    </tr>
    </logic:greaterThan>
    <logic:greaterThan name="kmDataSetForm" property="numberOfPlots" value="2">
	    <tr><td colspan="2"><hr width="100%" size="1" color="black" /></td>
		</tr>
		<logic:greaterThan name="kmDataSetForm" property="upVsRestPvalue" value="-100">
	    <tr>
		   <td><bean:write name="kmDataSetForm" property="upOrAmplified" />
		   vs. all other samples: </td>
	       <td><bean:write name="kmDataSetForm" property="upVsRestPvalue" /></td>
	    </tr>
	    </logic:greaterThan>
	    <logic:greaterThan name="kmDataSetForm" property="downVsRestPvalue" value="-100">
	    <tr>
		   <td><bean:write name="kmDataSetForm" property="downOrDeleted" />
		   vs. all other samples: </td>
	       <td><bean:write name="kmDataSetForm" property="downVsRestPvalue" /></td>
	    </tr>
	    </logic:greaterThan>
	    <logic:greaterThan name="kmDataSetForm" property="intVsRestPvalue" value="-100">
	    <tr>
		   <td>Intermediate vs. all other samples: </td>
	       <td><bean:write name="kmDataSetForm" property="intVsRestPvalue" /></td>
	    </tr>
	    </logic:greaterThan>
    </logic:greaterThan>
    <logic:lessEqual name="kmDataSetForm" property="numberOfPlots" value="1">
    	<td>N/A ( Not Applicable )</td>
    </logic:lessEqual>
</table>
</fieldset>
</logic:equal>
<!-- TAG CREATION WOULD NEED TO CONTAIN THE ABOVE -->
<logic:equal name="kmDataSetForm" property="plotVisible" value="false">
<p>
To display graph, Please select a Reporter for the Gene: <bean:write name="kmDataSetForm" property="geneOrCytoband" /> and select "Redraw Graph"
</p>
</logic:equal>

  </div>
</html:form>