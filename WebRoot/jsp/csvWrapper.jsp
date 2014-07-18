<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ page language="java" %><%@ page buffer="none" %><%@ page import="
gov.nih.nci.rembrandt.web.helper.ReportGeneratorHelper,
gov.nih.nci.rembrandt.web.bean.SessionQueryBag,
gov.nih.nci.rembrandt.util.RembrandtConstants,
org.dom4j.Document,org.dom4j.io.XMLWriter,org.dom4j.io.OutputFormat,
gov.nih.nci.caintegrator.service.findings.*, gov.nih.nci.rembrandt.web.helper.*,
gov.nih.nci.rembrandt.web.factory.*, gov.nih.nci.rembrandt.web.bean.*, 
org.dom4j.Document, gov.nih.nci.rembrandt.util.*,
gov.nih.nci.rembrandt.web.factory.ApplicationFactory,
gov.nih.nci.rembrandt.cache.RembrandtPresentationTierCache,gov.nih.nci.rembrandt.web.xml.*,
java.util.HashMap, java.util.ArrayList" %>

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

<%

String key = request.getParameter("key")!=null ? (String) request.getParameter("key") : null;
	if(key != null)	{
		RembrandtPresentationTierCache ptc = ApplicationFactory.getPresentationTierCache();
		FindingReportBean frb = (FindingReportBean) ptc.getNonPersistableObjectFromSessionCache(session.getId(), key);
		if(frb!=null && frb.getFinding()!=null)	{
			Document xmlDocument = null;
			if(frb.getXmlDocCSV()!=null)
				xmlDocument = (Document)frb.getXmlDocCSV();
			else	{
				//generate the XML for CSV (w/annotations) and cache
				if(frb.getFinding() instanceof ClassComparisonFinding)	{
					ArrayList reporterIds = (ArrayList)request.getSession().getAttribute("tmpReporterList");
					xmlDocument = ClassComparisonReport.getReportXML(frb.getFinding(), new HashMap(), true, reporterIds);
				}
				else if(frb.getFinding() instanceof FTestFinding)	{
					xmlDocument = FTestReport.getReportXML(frb.getFinding(), new HashMap(), true);
				}
				//put frb back in cache
//				we don't want to save it in cache because from now on each time
//				the user could export a different set of list.
//				so, saving the csv in the cache is useless.				
//				frb.setXmlDocCSV(xmlDocument);
//				ptc.addNonPersistableToSessionCache(frb.getFinding().getSessionId(),frb.getFinding().getTaskId(), frb);
			}
			if(xmlDocument!=null)	{
				//generate the CSV
				response.setContentType("application/csv");
				response.setHeader("Content-Disposition", "attachment; filename=report.csv");
				try{	
					ReportGeneratorHelper.renderReport(request, xmlDocument,"csv.xsl",out);
				}
				catch(Exception e)	{
					out.println("Error Generating the report");
				}
			}
			else	{
				out.println("No Records Available for this query");
			}
		}
		else	{
			out.println("No Records Available for this query");
		}
	}
	else	{
		out.println("Error Generating the CSV.");
	}
%>
</html>