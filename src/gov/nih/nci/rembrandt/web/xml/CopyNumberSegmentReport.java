package gov.nih.nci.rembrandt.web.xml;

import gov.nih.nci.rembrandt.queryservice.resultset.DimensionalViewContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.Resultant;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultsContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.copynumber.CopyNumberSegmentViewResultsContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.copynumber.SampleCopyNumberValuesResultset;
import gov.nih.nci.rembrandt.queryservice.resultset.sample.SampleResultset;
import gov.nih.nci.rembrandt.queryservice.resultset.sample.SampleViewResultsContainer;
import gov.nih.nci.rembrandt.util.ContextSensitiveHelpTag;
import gov.nih.nci.rembrandt.util.DEUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

/**
 * @author LandyR
 * Feb 8, 2005
 * 
 */


/**
* caIntegrator License
* 
* Copyright 2001-2005 Science Applications International Corporation ("SAIC"). 
* The software subject to this notice and license includes both human readable source code form and machine readable, 
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with 
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC. 
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105. 
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an 
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control" 
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) 
* beneficial ownership of such entity. 
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive, 
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights 
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly 
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed 
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof; 
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such 
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You. 
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce 
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any. 
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This 
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user 
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments 
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and 
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any 
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License. 
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and 
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary 
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to 
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including 
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties 
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail 
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to 
*    the extent prohibited by law, resulting from Your failure to obtain such permissions. 
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and 
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses 
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, 
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, 
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. 
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, 
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
* 
*/

public class CopyNumberSegmentReport implements ReportGenerator {

	/**
	 * 
	 */
	public CopyNumberSegmentReport () {
		super();
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.nautilus.ui.report.ReportGenerator#getTemplate(gov.nih.nci.nautilus.resultset.Resultant, java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Document getReportXML(Resultant resultant, Map filterMapParams) {

		//String theColors[] = { "B6C5F2","F2E3B5","DAE1F9","C4F2B5","819BE9", "E9CF81" };
		DecimalFormat resultFormat = new DecimalFormat("0.0000");
		String defaultV = "-";
		
			Document document = DocumentHelper.createDocument();

			try	{
			Element report = document.addElement( "Report" );
			Element cell = null;
			Element data = null;
			Element dataRow = null;
			//add the atts
	        report.addAttribute("reportType", "Copy Number");
	        report.addAttribute("helpLink", ContextSensitiveHelpTag.getHelpLink("Copy Number"));
	        //fudge these for now
	        report.addAttribute("groupBy", "none");
	        String queryName = resultant.getAssociatedQuery().getQueryName();
	        
	        //set the queryName to be unique for session/cache access
	        report.addAttribute("queryName", queryName);
	        report.addAttribute("sessionId", "the session id");
	        report.addAttribute("creationTime", "right now");
	        //hold a message to display on the report
	        report.addAttribute("msg", (resultant.isOverLimit() ? "over limit" : ""));
	        
			StringBuffer sb = new StringBuffer();
			
			ResultsContainer  resultsContainer = resultant.getResultsContainer();
			CopyNumberSegmentViewResultsContainer segmentViewResultsContainer = null;
			if(resultsContainer instanceof DimensionalViewContainer)	{
				
				DimensionalViewContainer dimensionalViewContainer = (DimensionalViewContainer) resultsContainer;
						

				segmentViewResultsContainer = dimensionalViewContainer.getCopyNumberSegmentViewResultsContainer();
				
			}
			else if (resultsContainer instanceof CopyNumberSegmentViewResultsContainer)	{
				segmentViewResultsContainer = (CopyNumberSegmentViewResultsContainer) resultsContainer;
			}
			
			Collection segments = segmentViewResultsContainer.getSampleCopyNumberValuesResultsets();

			//	set up the headers for this table 
			Element headerRow = report.addElement("Row").addAttribute("name", "headerRow");
			
			for(String h : CopyNumberSegmentReport.getCopyNumberSegmentHeaderValues()){
				cell = headerRow.addElement("Cell").addAttribute("type", "header").addAttribute("class", "header").addAttribute("group", "header");
			        data = cell.addElement("Data").addAttribute("type", "header").addText(h);
			        data = null;
		        cell = null;			
			}

			
   			for (Iterator sampleIterator = segments.iterator(); sampleIterator.hasNext();) {

   				SampleCopyNumberValuesResultset sampleResultset =  (SampleCopyNumberValuesResultset)sampleIterator.next();   			
				
				dataRow = report.addElement("Row").addAttribute("name", "dataRow");
				
				List rows = new ArrayList();
				rows = CopyNumberSegmentReport.getClinicalRowValues(sampleResultset);
				
				cell = dataRow.addElement("Cell").addAttribute("type", "data").addAttribute("class", "data").addAttribute("group", "sample");
			        data = cell.addElement("Data").addAttribute("type", "data").addText(DEUtils.checkNull(rows.get(0)));
	        		data = null;
	        	 cell = null;
	        	 
	        	 String tooltip = "Specimen = " + rows.get(0);
        	 
				for(int i=1; i<rows.size(); i++)	{
					switch (i) {
					case 1: tooltip = "Specimen = " + rows.get(0) + " (Chr No.)"; break;
					case 2: tooltip = "Specimen = " + rows.get(0) + " (Start Position)"; break;
					case 3: tooltip = "Specimen = " + rows.get(0) + " (End Position)"; break;
					case 4: tooltip = "Specimen = " + rows.get(0) + " (Number of Marks)"; break;
					case 5: tooltip = "Specimen = " + rows.get(0) + " (Segment Mean)"; break;
					default: tooltip = "Specimen = " + rows.get(0); break;
					}

					 cell = dataRow.addElement("Cell").addAttribute("type", "data").addAttribute("class", "data").addAttribute("group", "data");
				        data = cell.addElement("Data").addAttribute("type", "data").addAttribute("datainfo", tooltip).addText(DEUtils.checkNull(rows.get(i)));
		        		data = null;
		        	 cell = null;
				}
				
    		}
			}
			catch(Exception e)	{
				System.out.println(e);
			}
		    return document;		     
	}
	
	@SuppressWarnings("unchecked")
	public static List getClinicalRowValues(SampleCopyNumberValuesResultset sampleResultset){
		String defaultV = "-";
		List rows = new ArrayList();


		rows.add(sampleResultset.getBiospecimen().getSpecimenName());
		rows.add(sampleResultset.getChr());
		rows.add(sampleResultset.getLocStart());
		rows.add(sampleResultset.getLocEnd());
		rows.add(sampleResultset.getNumberOFMarks());
		rows.add(sampleResultset.getSegmentMean());
//		rows.add(sampleResultset.getSampleIDDE());
//		rows.add(sampleResultset.getDisease());

		
		return rows;
	}
	
	public static List<String> getCopyNumberSegmentHeaderValues()	{
		String headers = "Specimen,Chr No.,Start Position, End Position,Number of Marks,Segment Mean";//Number of Marks,,Sample,Disease";
		List<String> heads = new ArrayList<String>();
		heads = Arrays.asList(StringUtils.split(headers, ","));
		return heads;
	}
}
