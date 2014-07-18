<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>
<%@ page buffer="none" %>
<%@ page import="
gov.nih.nci.rembrandt.web.helper.ReportGeneratorHelper,
gov.nih.nci.rembrandt.web.bean.SessionQueryBag,
gov.nih.nci.rembrandt.util.RembrandtConstants,
org.dom4j.Document"
%>

<html>
  	<head>
		<title>My Report</title>
		
		<LINK href="/rembrandt/XSL/css.css" rel="stylesheet" type="text/css" />
		<script language="JavaScript" type="text/javascript" src="/rembrandt/js/overlib.js"></script>
		<script language="JavaScript" type="text/javascript" src="js/overlib_hideform.js"></script>
		<script language="JavaScript" type="text/javascript" src="js/caIntScript.js"></script> 
		<script language="JavaScript" type="text/javascript" src="XSL/js.js"></script> 
		<script language="JavaScript" type="text/javascript" src="XSL/a_saveSamples.js"></script>
		<script language="JavaScript" type="text/javascript" src="js/lib/prototype_1.5pre.js"></script>
		<script language="javascript" src="js/lib/Help.js"></script>
		<script language="javascript" src="js/lib/json.js"></script>
		<script type='text/javascript' src='/rembrandt/dwr/interface/DynamicReport.js'></script>
		<script type='text/javascript' src='/rembrandt/dwr/engine.js'></script>
		<script type='text/javascript' src='/rembrandt/dwr/util.js'></script>
		<script language="JavaScript" type="text/javascript" src="js/rembrandtScript.js"></script>
		<META HTTP-EQUIV="PRAGMA" CONTENT="NO-CACHE" />
		<META HTTP-EQUIV="Expires" CONTENT="-1" />
		
		
	</head>
	
<span id="spnLoading"  style="display:inline; width:500; text-align:center;" >
	<br><Br>
	<img src="images/statusBar2.gif">
	<br>Loading...please wait<br>
</span>
<%
	response.flushBuffer();	
	Document reportXML = (Document)request.getAttribute(RembrandtConstants.REPORT_XML);
	ReportGeneratorHelper.renderReport(request, reportXML,"report.xsl",out);
%>

</html>