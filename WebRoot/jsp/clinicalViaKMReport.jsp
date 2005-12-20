<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>
<%@ page buffer="none" %>
<%@ taglib uri='/WEB-INF/caintegrator-graphing.tld' prefix='graphing' %>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri='/WEB-INF/caintegrator-graphing.tld' prefix='graphing' %>
<%@ page import="gov.nih.nci.caintegrator.service.findings.*, gov.nih.nci.rembrandt.web.helper.*,gov.nih.nci.rembrandt.queryservice.QueryManager,gov.nih.nci.caintegrator.dto.query.QueryType,
gov.nih.nci.rembrandt.web.factory.*, gov.nih.nci.rembrandt.web.bean.*, org.dom4j.Document, gov.nih.nci.rembrandt.util.*,java.util.Collection,gov.nih.nci.rembrandt.dto.query.*" %>
<%@ page import="gov.nih.nci.rembrandt.web.factory.ApplicationFactory, gov.nih.nci.caintegrator.ui.graphing.data.kaplanmeier.KaplanMeierStoredData,org.apache.commons.beanutils.BeanUtils" %>
<%@ page import="gov.nih.nci.rembrandt.web.bean.*,gov.nih.nci.rembrandt.cache.*,java.util.*,java.lang.*,gov.nih.nci.caintegrator.dto.view.ViewFactory,gov.nih.nci.caintegrator.dto.view.ViewType,gov.nih.nci.rembrandt.cache.PresentationTierCache,gov.nih.nci.caintegrator.ui.graphing.data.kaplanmeier.KaplanMeierSampleInfo" %>


	<%
	PresentationTierCache presentationTierCache = ApplicationFactory.getPresentationTierCache();
	String sampleGroup = request.getParameter("sampleGroup");
	String dataName = request.getParameter("dataName");
	String sessionId = request.getSession().getId();
	Map params = new HashMap(); 
	params.put("showSampleSelect","false");
	
	//Retrieve the cache data about the plots
	KaplanMeierStoredData cacheData = (KaplanMeierStoredData)presentationTierCache.getSessionGraphingData(sessionId,dataName);
		
		//if the data is there grab the param from the request to see which sample Group they want to view: Up, Down or intermediate
		if(cacheData !=null){
			Collection samples = new ArrayList();
			if(sampleGroup.equals("up")){
			  samples = cacheData.getUpSamples();
			 }
			 else if (sampleGroup.equals("down")){
			  samples = cacheData.getDownSamples();
			 }
			 else if (sampleGroup.equals("inter")){
			  samples = cacheData.getIntSamples();
			 }
			Collection samplesList=new ArrayList();
			// if there are samples, iterate over the collection and cast them to info objects so
			// that we can get the sampleIDs, and add them into a new arraylist
			if(!samples.isEmpty()){
					for (Iterator it=samples.iterator(); it.hasNext(); ) {
       					 Object element = it.next();
   					     KaplanMeierSampleInfo kaplanMeierSampleInfo = (KaplanMeierSampleInfo)element;
   					     samplesList.add(kaplanMeierSampleInfo.getSampleID());
   					     
					}
					//create a compund query using an empty clinical query
					ClinicalDataQuery clinicalDataQuery = (ClinicalDataQuery) QueryManager.createQuery(QueryType.CLINICAL_DATA_QUERY_TYPE);
		    		clinicalDataQuery.setQueryName("clinical");
		    		CompoundQuery cquery = new CompoundQuery(clinicalDataQuery);		
					cquery.setAssociatedView(ViewFactory.newView(ViewType.CLINICAL_VIEW));
					cquery.setQueryName("clinicalViaKM");
					cquery.setSessionId(sessionId);		    
		    		String[] samplesArray = (String[]) samplesList.toArray(new String[0]);
					//generate the reportBean with the reportXML using the compQury and samples Array
					ReportGeneratorHelper reportGeneratorHelper = new ReportGeneratorHelper(cquery, samplesArray);
					ReportBean clincalReportBean = presentationTierCache.getReportBean(sessionId,cquery.getQueryName());
						if(clincalReportBean!=null){
							Document reportXML = clincalReportBean.getReportXML();
							String report = reportGeneratorHelper.renderReport(params,reportXML,"report.xsl");
							//return the string buffer and print
							out.write(report);
						}
						else{
						out.println("No Records Available for this query");
						}
			}
			else{
			out.println("No Samples Available for this query");
            }
			
		}
		
	%>		
	
	
	
	

