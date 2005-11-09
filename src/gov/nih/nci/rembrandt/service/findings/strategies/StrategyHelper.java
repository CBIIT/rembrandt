/**
 * 
 */
package gov.nih.nci.rembrandt.service.findings.strategies;

import gov.nih.nci.rembrandt.queryservice.resultset.DimensionalViewContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultsContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.gene.GeneExprResultsContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.gene.GeneExprSingleViewResultsContainer;
import gov.nih.nci.rembrandt.queryservice.resultset.sample.SampleResultset;
import gov.nih.nci.rembrandt.queryservice.resultset.sample.SampleViewResultsContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

/**
 * @author sahnih
 *
 */
public class StrategyHelper {
	/**
	 * 
	 * @param container --this is the container that you want the sample ids from
	 * @return --A collection of all the ResultsContainer sampleId Domain Elements
	 */
	@SuppressWarnings("unchecked")
	public static Collection<String> extractSampleIds(ResultsContainer container)throws OperationNotSupportedException{
		Collection<String> sampleIds = new ArrayList();
		SampleViewResultsContainer svrContainer = null;
		/*
		 * These are currently the only two results containers that we have to
		 * worry about at this time, I believe.
		 */
		if(container instanceof DimensionalViewContainer) {
			//Get the SampleViewResultsContainer from the DimensionalViewContainer
			DimensionalViewContainer dvContainer = (DimensionalViewContainer)container;
			svrContainer = dvContainer.getSampleViewResultsContainer();
		}else if(container instanceof SampleViewResultsContainer) {
			svrContainer = (SampleViewResultsContainer)container;
		}
		//Handle the SampleViewResultsContainers if that is what we got
		if(svrContainer!=null) {
			Collection bioSpecimen = svrContainer.getBioSpecimenResultsets();
			for(Iterator i = bioSpecimen.iterator();i.hasNext();) {
				SampleResultset sampleResultset =  (SampleResultset)i.next();
				sampleIds.add(sampleResultset.getBiospecimen().getValue().toString());
			}
		}else {
			throw new OperationNotSupportedException("We are not able to able to extract SampleIds from: "+container.getClass());
		}
		return sampleIds;
	}
	public static Collection<String> extractReporters(ResultsContainer container)throws OperationNotSupportedException{
		Collection<String> reporters = new ArrayList<String>();
		DimensionalViewContainer dvContainer = null;
		/*
		 * These are currently the only two results containers that we have to
		 * worry about at this time, I believe.
		 */
		if(container instanceof DimensionalViewContainer) {
			//Get the DimensionalViewContainer
			dvContainer = (DimensionalViewContainer)container;
	
			if(dvContainer!=null) {
				GeneExprSingleViewResultsContainer gesvContainer = (GeneExprSingleViewResultsContainer) dvContainer.getGeneExprSingleViewContainer();
				List reporterList =gesvContainer.getAllReporterNames();
				for(Iterator i = reporterList.iterator();i.hasNext();) {
					reporters.add((String)i.next());
				}
			}else {
				throw new OperationNotSupportedException("We are not able to able to extract Reporters from: "+container.getClass());
			}
		}
		return reporters;
	}
}
