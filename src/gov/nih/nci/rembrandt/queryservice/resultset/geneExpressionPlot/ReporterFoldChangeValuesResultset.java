/*
 *  @author: SahniH
 *  Created on Nov 9, 2004
 *  @version $ Revision: 1.0 $
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
package gov.nih.nci.rembrandt.queryservice.resultset.geneExpressionPlot;

import gov.nih.nci.caintegrator.dto.de.DatumDE;
import gov.nih.nci.rembrandt.queryservice.resultset.gene.FoldChange;

/**
 * @author SahniH
 * Date: Nov 9, 2004
 * 
 */
public class ReporterFoldChangeValuesResultset implements FoldChange{
	private DatumDE foldChangeRatioValue = null;
	private DatumDE foldChangeIntensity = null;
	private DatumDE foldChangeNonTumorIntensity = null;
	private DatumDE ratioPval = null;
	private DatumDE reporter = null;

	/**
	 * @param reporter
	 */
	public ReporterFoldChangeValuesResultset(DatumDE reporter) {		
		setReporter(reporter);
	}
	/**
	 * @return Returns the foldChangeIntensity.
	 */
	public DatumDE getFoldChangeIntensity() {
		return this.foldChangeIntensity;
	}
	/**
	 * @param foldChangeIntensity The foldChangeIntensity to set.
	 */
	public void setFoldChangeIntensity(DatumDE foldChangeSampleIntensity) {
		this.foldChangeIntensity = foldChangeSampleIntensity;
	}
	/**
	 * @return Returns the ratioPval.
	 */
	public DatumDE getRatioPval() {
		return this.ratioPval;
	}
	/**
	 * @param ratioPval The ratioPval to set.
	 */
	public void setRatioPval(DatumDE ratioPval) {
		this.ratioPval = ratioPval;
	}
	/**
	 * @return Returns the reporterName.
	 */
	public DatumDE getReporter() {
		return this.reporter;
	}
	/**
	 * @param reporterName The reporterName to set.
	 */
	public void setReporter(DatumDE reporterName) {
		this.reporter = reporterName;
	}
	/**
	 * @return Returns the foldChangeNonTumorIntensity.
	 */
	public DatumDE getFoldChangeNonTumorIntensity() {
		return foldChangeNonTumorIntensity;
	}
	/**
	 * @param foldChangeNonTumorIntensity The foldChangeNonTumorIntensity to set.
	 */
	public void setFoldChangeNonTumorIntensity(
			DatumDE foldChangeNonTumorIntensity) {
		this.foldChangeNonTumorIntensity = foldChangeNonTumorIntensity;
	}
	/**
	 * @return Returns the foldChangeRatioValue.
	 */
	public DatumDE getFoldChangeRatioValue() {
		return foldChangeRatioValue;
	}
	/**
	 * @param foldChangeRatioValue The foldChangeRatioValue to set.
	 */
	public void setFoldChangeRatioValue(DatumDE foldChangeRatioValue) {
		this.foldChangeRatioValue = foldChangeRatioValue;
	}
}