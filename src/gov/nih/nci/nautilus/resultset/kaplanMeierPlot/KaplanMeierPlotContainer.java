/*
 * @author: SahniH
 * Created on Nov 11, 2004
 * @version $ Revision: 1.0 $
 * 
 *	The caBIO Software License, Version 1.0
 *
 *	Copyright 2004 SAIC. This software was developed in conjunction with the National Cancer 
 *	Institute, and so to the extent government employees are co-authors, any rights in such works 
 *	shall be subject to Title 17 of the United States Code, section 105.
 * 
 *	Redistribution and use in source and binary forms, with or without modification, are permitted 
 *	provided that the following conditions are met:
 *	 
 *	1. Redistributions of source code must retain the above copyright notice, this list of conditions 
 *	and the disclaimer of Article 3, below.  Redistributions in binary form must reproduce the above 
 *	copyright notice, this list of conditions and the following disclaimer in the documentation and/or 
 *	other materials provided with the distribution.
 * 
 *	2.  The end-user documentation included with the redistribution, if any, must include the 
 *	following acknowledgment:
 *	
 *	"This product includes software developed by the SAIC and the National Cancer 
 *	Institute."
 *	
 *	If no such end-user documentation is to be included, this acknowledgment shall appear in the 
 *	software itself, wherever such third-party acknowledgments normally appear.
 *	 
 *	3. The names "The National Cancer Institute", "NCI" and "SAIC" must not be used to endorse or 
 *	promote products derived from this software.
 *	 
 *	4. This license does not authorize the incorporation of this software into any proprietary 
 *	programs.  This license does not authorize the recipient to use any trademarks owned by either 
 *	NCI or SAIC-Frederick.
 *	 
 *	
 *	5. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED 
 *	WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF 
 *	MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE) ARE 
 *	DISCLAIMED.  IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR 
 *	THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, 
 *	EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, 
 *	PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
 *	PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY 
 *	OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING 
 *	NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS 
 *	SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *	
 */
package gov.nih.nci.nautilus.resultset.kaplanMeierPlot;

import gov.nih.nci.nautilus.de.GeneIdentifierDE;
import gov.nih.nci.nautilus.resultset.geneExpressionPlot.ReporterFoldChangeValuesResultset;
import gov.nih.nci.nautilus.resultset.sample.SampleViewResultsContainer;
import gov.nih.nci.nautilus.ui.graph.kaplanMeier.KMSampleInfo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * @author Himanso
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class KaplanMeierPlotContainer extends SampleViewResultsContainer {
	private static Logger logger = Logger.getLogger(KaplanMeierPlotContainer.class);

    private GeneIdentifierDE.GeneSymbol geneSymbol;


	/**
	 * @return Returns the geneSymbol.
	 */
	public GeneIdentifierDE.GeneSymbol getGeneSymbol() {
		return this.geneSymbol;
	}

	/**
	 * @param geneSymbol
	 *            The geneSymbol to set.
	 */
	public void setGeneSymbol(GeneIdentifierDE.GeneSymbol geneSymbol) {
		this.geneSymbol = geneSymbol;
	}
	public KMSampleInfo[] getSummaryKMPlotSamples() {
	    List kmSampleInfoArray = new ArrayList(); 
	    Collection samples = getBioSpecimenResultsets();
        //Clear the Previous collection
	    for (Iterator sampleIterator = samples.iterator(); sampleIterator.hasNext();) {
			SampleKaplanMeierPlotResultset sample = (SampleKaplanMeierPlotResultset) sampleIterator.next();
			Long time = (Long) (sample.getSurvivalLength().getValue());
			Integer censor = new Integer((sample.getCensor().getValue().toString()));
			Double value = (Double) sample.getSummaryReporterFoldChange().getValue();
			KMSampleInfo kmSampleInfo = new KMSampleInfo(time.intValue(), censor.intValue(), value.doubleValue());
			kmSampleInfoArray.add(kmSampleInfo);
		}
		return (KMSampleInfo[]) kmSampleInfoArray.toArray(new KMSampleInfo[kmSampleInfoArray.size()]);
	}
	public KMSampleInfo[] getKMPlotSamplesForReporter(String reporterName){
	    List kmSampleInfoArray = new ArrayList(); 
	    Collection samples = getBioSpecimenResultsets();
	    for (Iterator sampleIterator = samples.iterator(); sampleIterator.hasNext();) {
			SampleKaplanMeierPlotResultset sample = (SampleKaplanMeierPlotResultset) sampleIterator.next();
			ReporterFoldChangeValuesResultset reporterFCValueResultset = sample.getReporterFoldChangeValuesResultset(reporterName);
			if (reporterFCValueResultset != null && reporterFCValueResultset.getFoldChangeRatioValue() != null){
				Long time = (Long) (sample.getSurvivalLength().getValue());
				Integer censor = new Integer((sample.getCensor().getValue().toString()));
				Double value = (Double) reporterFCValueResultset.getFoldChangeRatioValue().getValue();
				KMSampleInfo kmSampleInfo = new KMSampleInfo(time.intValue(), censor.intValue(), value.doubleValue());
				kmSampleInfoArray.add(kmSampleInfo);
			}
		}
		return (KMSampleInfo[]) kmSampleInfoArray.toArray(new KMSampleInfo[kmSampleInfoArray.size()]);
	}
	public List getAssociatedReporters(){
		List reporterNames = null;
	    Collection samples = getBioSpecimenResultsets();
	    
	    if(!samples.isEmpty()){
	    	Iterator sampleIterator = samples.iterator();	    	
	    	SampleKaplanMeierPlotResultset sample = (SampleKaplanMeierPlotResultset) sampleIterator.next();
            reporterNames = sample.getReporterNames();
	    }
	    return reporterNames;
	}
}