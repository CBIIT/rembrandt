/*
 * Created on Sep 14, 2004
 *
 */
package gov.nih.nci.nautilus.resultset.gene;
import gov.nih.nci.nautilus.resultset.sample.BioSpecimenResultset;

import java.util.Collection;
import java.util.SortedSet;
import java.util.TreeSet;


/**
 * @author SahniH
 * GeneExprSingleViewResultsContainer contains a collection for GeneResultset object
 * 
 *
 * 
 */
public class GeneExprSingleViewResultsContainer extends GeneExprResultsContainer{
	
	/**
	 * @return Returns the biospecimenLabels.
	 */
	public Collection getBiospecimenLabels(String groupLabel) {
		return (SortedSet) groupsLabels.get(groupLabel);
	}
	/**
	 * @param groupsLabels The groupsLabels to set.
	 */
	public void addBiospecimensToGroups(String groupLabel, String BiospecimenId) {
		SortedSet biospecimenLabels = null;
		if(groupsLabels.containsKey(groupLabel)){
			biospecimenLabels = (SortedSet) groupsLabels.get(groupLabel);
		}
		else { ///key does not exsist
			biospecimenLabels = new TreeSet();			
		}
		biospecimenLabels.add(BiospecimenId);
		groupsLabels.put(groupLabel,biospecimenLabels);
	}
    /**
     * @param geneSymbol,reporterName,groupType
	 * @return groupResultset Returns groupResultset for this groupType, reporterName , geneSymbol.
	 */
    public Collection getBioSpecimentResultsets(String geneSymbol,String reporterName, String groupType){
    	if(geneSymbol== null){
    		geneSymbol = GeneExprResultsContainer.NO_GENE_SYMBOL;
    	}
    	if(reporterName != null){
    		GeneResultset geneResultset = (GeneResultset) genes.get(geneSymbol);
            if(geneResultset != null){
        		ReporterResultset reporterResultset = geneResultset.getRepoterResultset(reporterName);
    			if(reporterResultset != null){
                    ViewByGroupResultset groupResultset = (ViewByGroupResultset) reporterResultset.getGroupByResultset(groupType);
                    if(groupResultset != null){
                        return groupResultset.getBioSpecimenResultsets();
                    }
            }
            }
		}
    		return null;
    }
    /**
     * @param geneSymbol,reporterName,groupType,bioSpecimenID
	 * @return bioSpecimenResultset Returns BioSpecimenResultset for this bioSpecimenID,groupType, reporterName , geneSymbol.
	 */
    public BioSpecimenResultset getBioSpecimentResultset(String geneSymbol,String reporterName, String groupType, String bioSpecimenID){
    	if(geneSymbol== null){
    		geneSymbol = GeneExprResultsContainer.NO_GENE_SYMBOL;
    	}
    	if(reporterName != null){
    		GeneResultset geneResultset = (GeneResultset) genes.get(geneSymbol);
            if(geneResultset != null){
        		ReporterResultset reporterResultset = geneResultset.getRepoterResultset(reporterName);
                if(reporterResultset != null){
        			ViewByGroupResultset groupResultset = (ViewByGroupResultset) reporterResultset.getGroupByResultset(groupType);
                    if(groupResultset!= null){
                        return groupResultset.getBioSpecimenResultset(bioSpecimenID);
                    }
                }
            }
		}
    		return null;
    }

}
