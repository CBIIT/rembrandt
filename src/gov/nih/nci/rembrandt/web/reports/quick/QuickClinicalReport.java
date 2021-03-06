/*L
 * Copyright (c) 2006 SAIC, SAIC-F.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/rembrandt/LICENSE.txt for details.
 */

package gov.nih.nci.rembrandt.web.reports.quick;

import gov.nih.nci.rembrandt.queryservice.resultset.sample.SampleResultset;
import gov.nih.nci.rembrandt.queryservice.validation.ClinicalDataValidator;
import gov.nih.nci.rembrandt.util.DEUtils;
import gov.nih.nci.rembrandt.web.xml.ClinicalSampleReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class QuickClinicalReport {

	public static StringBuffer quickSampleReport(List<String> sampleIds){
		StringBuffer html = new StringBuffer();
		Document document = DocumentHelper.createDocument();
		String dv = "--";
		
		if(sampleIds != null)	{
			Map<String, SampleResultset> sampleResultsetMap;
			try {
				sampleResultsetMap = ClinicalDataValidator.getClinicalAnnotationsMapForSamples(sampleIds);
				if(sampleResultsetMap != null  && sampleIds != null){
					int count = 0;

					Element table = document.addElement("table").addAttribute("id", "reportTable").addAttribute("class", "report");
					Element tr = null;
					Element td = null;
					
					tr = table.addElement("tr").addAttribute("class", "header");
					List<String> heads = new ArrayList<String>();
					heads = ClinicalSampleReport.getClinicalHeaderValues();
					for(String h : heads)	{
						td = tr.addElement("td").addAttribute("class", "header").addText(h);
					}
					
					for(String sampleId:sampleIds){
						SampleResultset sampleResultset = sampleResultsetMap.get(sampleId);
						//lose this
						if(sampleResultset != null && sampleResultset.getSampleIDDE()!= null)	{
							System.out.println(++count+" SampleID :" +sampleResultset.getSampleIDDE().getValue().toString());
						}
						//end lose
						if(sampleResultset!=null)	{
							List dataRows = new ArrayList();
							dataRows = ClinicalSampleReport.getClinicalRowValues(sampleResultset);
							
							tr = table.addElement("tr").addAttribute("class", "data");
							
							for(int i=0; i<dataRows.size(); i++)	{
								td = tr.addElement("td").addText(DEUtils.checkNull(dataRows.get(i)));
							}
							/*
							String sid = sampleResultset.getSampleIDDE()!=null && sampleResultset.getSampleIDDE().getValue() != null ?sampleResultset.getSampleIDDE().getValue().toString() : dv;
							td = tr.addElement("td").addText(sid);
							
							String dis = sampleResultset.getDisease() != null && sampleResultset.getDisease().getValue() != null ?sampleResultset.getDisease().getValue().toString() : dv;
							td = tr.addElement("td").addText(dis);
							
							String gen = sampleResultset.getGenderCode() != null && sampleResultset.getGenderCode().getValue() != null ? sampleResultset.getGenderCode().getValue().toString() : dv;
							td = tr.addElement("td").addText(gen);
							
							String age = sampleResultset.getAgeGroup() != null && sampleResultset.getAgeGroup().getValue() != null ? sampleResultset.getAgeGroup().getValue().toString() : dv;
							td = tr.addElement("td").addText(age);
							
							String slength = sampleResultset.getSurvivalLength() != null ? String.valueOf(sampleResultset.getSurvivalLength()) : dv;
							td = tr.addElement("td").addText(slength);
							*/
						}
						
					}
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return html.append(document.asXML());
	}
}
