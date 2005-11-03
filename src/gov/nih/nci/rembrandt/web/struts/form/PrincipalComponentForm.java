package gov.nih.nci.rembrandt.web.struts.form;




import gov.nih.nci.caintegrator.enumeration.*;
import gov.nih.nci.rembrandt.web.helper.DefaultsHelper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;




public class PrincipalComponentForm extends ActionForm {
    
 // -------------INSTANCE VARIABLES-----------------------------//
    private static Logger logger = Logger.getLogger(BaseForm.class);
	
    private static List existingGroupsList;
    
    private String selectedGroupName = "";
    
    private String analysisResultName = "";    
        
    private int variancePercentile = 70;
    
    private String filterType = "default";
    
    private String geneSetName = "";
    
    private String reporterSetName = "";
    
    private String arrayPlatform = "";
    
    private String diffExpGenes = "";
    
    private String diffExpReporters = "";
    
    private String constraintVariance = "";

	public PrincipalComponentForm(){
		
    }

    /**
     * @return Returns the existingGroupsList.
     */
    public List getExistingGroupsList() {
        return PrincipalComponentForm.existingGroupsList;
    }

    /**
     * @param existingGroupsList The existingGroupsList to set.
     */
    public void setExistingGroupsList(List existingGroupsList) {
        PrincipalComponentForm.existingGroupsList = existingGroupsList;
    }

    /**
     * @return Returns the analysisResultName.
     */
    public String getAnalysisResultName() {
        return analysisResultName;
    }

    /**
     * @param analysisResultName The analysisResultName to set.
     */
    public void setAnalysisResultName(String analysisResultName) {
        this.analysisResultName = analysisResultName;
    }

    /**
     * @return Returns the arrayPlatform.
     */
    public String getArrayPlatform() {
        return arrayPlatform;
    }

    /**
     * @param arrayPlatform The arrayPlatform to set.
     */
    public void setArrayPlatform(String arrayPlatform) {
        this.arrayPlatform = arrayPlatform;
    }

    
       /**
     * @return Returns the variancePercentile.
     */
    public int getVariancePercentile() {
        return DefaultsHelper.getVariancePercentile();
    }

    /**
     * @param variancePercentile The variancePercentile to set.
     */
    public void setVariancePercentile(int variancePercentile) {
        this.variancePercentile = variancePercentile;
    }


    /**
     * @return Returns the constraintVariance.
     */
    public String getConstraintVariance() {
        return constraintVariance;
    }


    /**
     * @param constraintVariance The constraintVariance to set.
     */
    public void setConstraintVariance(String constraintVariance) {
        this.constraintVariance = constraintVariance;
    }


    /**
     * @return Returns the diffExpGenes.
     */
    public String getDiffExpGenes() {
        return diffExpGenes;
    }


    /**
     * @param diffExpGenes The diffExpGenes to set.
     */
    public void setDiffExpGenes(String diffExpGenes) {
        this.diffExpGenes = diffExpGenes;
    }


    /**
     * @return Returns the diffExpReporters.
     */
    public String getDiffExpReporters() {
        return diffExpReporters;
    }


    /**
     * @param diffExpReporters The diffExpReporters to set.
     */
    public void setDiffExpReporters(String diffExpReporters) {
        this.diffExpReporters = diffExpReporters;
    }


    /**
     * @return Returns the filterType.
     */
    public String getFilterType() {
        return filterType;
    }


    /**
     * @param filterType The filterType to set.
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }


    /**
     * @return Returns the geneSetName.
     */
    public String getGeneSetName() {
        return geneSetName;
    }


    /**
     * @param geneSetName The geneSetName to set.
     */
    public void setGeneSetName(String geneSetName) {
        this.geneSetName = geneSetName;
    }


    /**
     * @return Returns the reporterSetName.
     */
    public String getReporterSetName() {
        return reporterSetName;
    }


    /**
     * @param reporterSetName The reporterSetName to set.
     */
    public void setReporterSetName(String reporterSetName) {
        this.reporterSetName = reporterSetName;
    }


    /**
     * @return Returns the selectedGroupName.
     */
    public String getSelectedGroupName() {
        return selectedGroupName;
    }


    /**
     * @param selectedGroupName The selectedGroupName to set.
     */
    public void setSelectedGroupName(String selectedGroupName) {
        this.selectedGroupName = selectedGroupName;
    }

    /**
     * Method validate
     * 
     * @param ActionMapping
     *            mapping
     * @param HttpServletRequest
     *            request
     * @return ActionErrors
     */
    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();
        
        
        
       
       
        return errors;
    }
    
  
    /**
     * Method reset
     * 
     * @param ActionMapping
     *            mapping
     * @param HttpServletRequest
     *            request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        analysisResultName = "";           
        arrayPlatform = "";             
      
    }
    


    
}