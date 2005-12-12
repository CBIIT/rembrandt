package gov.nih.nci.rembrandt.web.struts.action;

import gov.nih.nci.caintegrator.dto.critieria.AllGenesCriteria;
import gov.nih.nci.caintegrator.dto.critieria.AlleleFrequencyCriteria;
import gov.nih.nci.caintegrator.dto.critieria.AssayPlatformCriteria;
import gov.nih.nci.caintegrator.dto.critieria.CloneOrProbeIDCriteria;
import gov.nih.nci.caintegrator.dto.critieria.Constants;
import gov.nih.nci.caintegrator.dto.critieria.CopyNumberCriteria;
import gov.nih.nci.caintegrator.dto.critieria.DiseaseOrGradeCriteria;
import gov.nih.nci.caintegrator.dto.critieria.GeneIDCriteria;
import gov.nih.nci.caintegrator.dto.critieria.RegionCriteria;
import gov.nih.nci.caintegrator.dto.critieria.SNPCriteria;
import gov.nih.nci.caintegrator.dto.critieria.SampleCriteria;
import gov.nih.nci.caintegrator.dto.de.AssayPlatformDE;
import gov.nih.nci.caintegrator.dto.query.QueryType;
import gov.nih.nci.caintegrator.dto.view.ViewFactory;
import gov.nih.nci.caintegrator.dto.view.ViewType;
import gov.nih.nci.rembrandt.cache.PresentationTierCache;
import gov.nih.nci.rembrandt.dto.query.ComparativeGenomicQuery;
import gov.nih.nci.rembrandt.dto.query.CompoundQuery;
import gov.nih.nci.rembrandt.queryservice.QueryManager;
import gov.nih.nci.rembrandt.util.RembrandtConstants;
import gov.nih.nci.rembrandt.web.bean.ChromosomeBean;
import gov.nih.nci.rembrandt.web.bean.SessionQueryBag;
import gov.nih.nci.rembrandt.web.factory.ApplicationFactory;
import gov.nih.nci.rembrandt.web.helper.ChromosomeHelper;
import gov.nih.nci.rembrandt.web.helper.InsitutionAccessHelper;
import gov.nih.nci.rembrandt.web.helper.ReportGeneratorHelper;
import gov.nih.nci.rembrandt.web.struts.form.ComparativeGenomicForm;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;

public class ComparativeGenomicAction extends LookupDispatchAction {
    private static Logger logger = Logger.getLogger(ComparativeGenomicAction.class);
    private PresentationTierCache presentationTierCache = ApplicationFactory.getPresentationTierCache();
   //if multiUse button clicked (with styles de-activated) forward back to page
    public ActionForward multiUse(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		
		return mapping.findForward("backToCGH");
    }
    
    /**
     * Method setup
     * 
     * @param ActionMapping
     *            mapping
     * @param ActionForm
     *            form
     * @param HttpServletRequest
     *            request
     * @param HttpServletResponse
     *            response
     * @return ActionForward
     * @throws Exception
     */
    
    //Setup the comparativeGenomicForm from menu page
    public ActionForward setup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		ComparativeGenomicForm comparativeGenomicForm = (ComparativeGenomicForm) form;
		//Since Chromosomes is a static variable there is no need to set it twice.
		//It is only a lookup option collection
		if(comparativeGenomicForm.getChromosomes()==null||comparativeGenomicForm.getChromosomes().isEmpty()) {
			//set the chromsomes list in the form 
			logger.debug("Setup the chromosome values for the form");
			comparativeGenomicForm.setChromosomes(ChromosomeHelper.getInstance().getChromosomes());
		}
		return mapping.findForward("backToCGH");
    }
    
    public ActionForward getCytobands(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			ComparativeGenomicForm cgForm = (ComparativeGenomicForm)form;
			//This is the static list of chromosomes that is fetched the first time it is needed
			List chromosomes = cgForm.getChromosomes();
			//IMPORTANT! geForm.chromosomeNumber is NOT the chromosome number.  It is the index
			//into the static chromosomes list where the chromosome can be found.
			if(!"".equals(cgForm.getChromosomeNumber())) {
				ChromosomeBean bean = (ChromosomeBean)chromosomes.get(Integer.parseInt(cgForm.getChromosomeNumber()));
				cgForm.setCytobands(bean.getCytobands());
			}
			
			return mapping.findForward("backToCGH");
	}
    
    
    /**
     * Method submitAllGenes
     * 
     * @param ActionMapping
     *            mapping
     * @param ActionForm
     *            form
     * @param HttpServletRequest
     *            request
     * @param HttpServletResponse
     *            response
     * @return ActionForward
     * @throws Exception
     */
    
    //If this is an All Genes submit
    public ActionForward submitAllGenes(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
        
        request.getSession().setAttribute("currentPage", "0");
		request.getSession().removeAttribute("currentPage2");
		
		ComparativeGenomicForm comparativeGenomicForm = (ComparativeGenomicForm) form;
        //set all Genes query and give copyNumber default value
		comparativeGenomicForm.setGeneGroup("");
		comparativeGenomicForm.setCopyNumber("amplified");
		comparativeGenomicForm.setCnAmplified(RembrandtConstants.ALL_GENES_COPY_NUMBER_REGULATION);
		comparativeGenomicForm.setCnADAmplified(RembrandtConstants.ALL_GENES_COPY_NUMBER_REGULATION);
		comparativeGenomicForm.setCnADDeleted("1");
		comparativeGenomicForm.setCnDeleted("1");
         
		logger.debug("This is an All Genes cgh Submital");
		return mapping.findForward("showAllGenes");
    }
    
    /**
     * Method submitStandard
     * 
     * @param ActionMapping
     *            mapping
     * @param ActionForm
     *            form
     * @param HttpServletRequest
     *            request
     * @param HttpServletResponse
     *            response
     * @return ActionForward
     * @throws Exception
     */
    
    //If this is a standard submit
    public ActionForward submitStandard(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
        
        request.getSession().setAttribute("currentPage", "0");
		request.getSession().removeAttribute("currentPage2");
		
		ComparativeGenomicForm comparativeGenomicForm = (ComparativeGenomicForm) form;
		////set standard query and clear copyNumber default value
		comparativeGenomicForm.setCopyNumber("");
		comparativeGenomicForm.setCnAmplified("");
		comparativeGenomicForm.setCnADAmplified("");
		comparativeGenomicForm.setCnADDeleted("");
		comparativeGenomicForm.setCnDeleted("");
		
		logger.debug("This is an Standard cgh Submital");
		return mapping.findForward("backToCGH");
    }
    
    
    /**
     * Method submittal
     * 
     * @param ActionMapping
     *            mapping
     * @param ActionForm
     *            form
     * @param HttpServletRequest
     *            request
     * @param HttpServletResponse
     *            response
     * @return ActionForward
     * @throws Exception
     */
    
     //If this is a Submittal do the following
    public ActionForward submittal(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().setAttribute("currentPage", "0");
        request.getSession().removeAttribute("currentPage2");
        String sessionId = request.getSession().getId();
        ComparativeGenomicForm comparativeGenomicForm = (ComparativeGenomicForm) form;
        
        /*The following 15 lines of code/logic will eventually need to be moved/re-organized. All Genes queries should have their own actions, forms, etc. For
   	  * now, in order to properly validate an all genes query and STILL be able to forward back to
   	  * the appropriate query page (tile order), this logic has been placed in the action itself, so
   	  * that there can be proper forwarding when errors are generated from an all genes submission.
   	  * This logic checks if the query is an all genes query and then if the copy number value is
   	  * less than 10 for amplified and greater than 1 for deletion. If it is less than 10 for amp and greater than 1 for deleted,
   	  * an error is created and sent with the request to the forward.
   	  * BEGINS HERE!!
   	  */
   		   if (comparativeGenomicForm.getIsAllGenes()){
   		       try{
   		        int intCnAmplified = Integer.parseInt(comparativeGenomicForm.getCnAmplified());
   		        float floatCnDeleted = Float.parseFloat(comparativeGenomicForm.getCnDeleted());
   		        int intCnADAmplified = Integer.parseInt(comparativeGenomicForm.getCnADAmplified());
   		        float floatCnADDeleted = Float.parseFloat(comparativeGenomicForm.getCnADDeleted());
   			        if((intCnAmplified < 10 && comparativeGenomicForm.getCopyNumber().equalsIgnoreCase("amplified")) || 
   			             (intCnADAmplified < 10 && comparativeGenomicForm.getCopyNumber().equalsIgnoreCase("ampdel"))){
   			            ActionErrors errors = new ActionErrors();
			            errors.add("copyNumberAllGenesAmp", new ActionError(
						"gov.nih.nci.nautilus.ui.struts.form.copyNumberAmp.allGenes.error")); 
			            this.saveErrors(request, errors);
					    return mapping.findForward("showAllGenes"); 
   			        }
   			        if((floatCnDeleted > 1 && comparativeGenomicForm.getCopyNumber().equalsIgnoreCase("deleted")) ||
   			             (floatCnADDeleted > 1 && comparativeGenomicForm.getCopyNumber().equalsIgnoreCase("ampdel"))){
   			            ActionErrors errors = new ActionErrors();
   			            errors.add("copyNumberAllGenesDel", new ActionError(
   						"gov.nih.nci.nautilus.ui.struts.form.copyNumberDel.allGenes.error")); 
   			            this.saveErrors(request, errors);
   					    return mapping.findForward("showAllGenes"); 
   			        }
   		     } catch (NumberFormatException ex) {
   		           ActionErrors errors = new ActionErrors();
		            errors.add("copyNumberAllGenesDel", new ActionError(
					"gov.nih.nci.nautilus.ui.struts.form.copyNumberDel.allGenes.error")); 
		            this.saveErrors(request, errors);
				    return mapping.findForward("showAllGenes");    
  		    }
        }
   		  //All Genes validation ENDS HERE  
   		
        
        logger.debug("This is a Comparative Genomic Submittal");
        //Create Query Objects
        ComparativeGenomicQuery cghQuery = createCGHQuery(comparativeGenomicForm);
        
        //Check user credentials and constrain query by Institutions
        if(cghQuery != null){
        	cghQuery.setInstitutionCriteria(InsitutionAccessHelper.getInsititutionCriteria(request.getSession()));
            }
        
        //This is required as struts resets the form.  It is later added back to the request
        request.setAttribute("previewForm", comparativeGenomicForm.cloneMe());
       
        
            try {
                //Store the query in the SessionQueryBag
                if (!cghQuery.isEmpty()) {
                    SessionQueryBag queryBag = presentationTierCache.getSessionQueryBag(sessionId);
                    queryBag.putQuery(cghQuery, comparativeGenomicForm);
                    presentationTierCache.putSessionQueryBag(sessionId, queryBag);
                } else {
                    ActionErrors errors = new ActionErrors();
                    ActionError error =  new ActionError(
                    		"gov.nih.nci.nautilus.ui.struts.form.query.cgh.error");
                    errors.add(ActionErrors.GLOBAL_ERROR,error);
                    this.saveErrors(request, errors);
                    return mapping.findForward("backToCGH");
                }
            }
            catch (Exception e) {
               logger.error(e);
            }
            return mapping.findForward("advanceSearchMenu");
        }
  
    
    /**
     * Method preview
     * 
     * @param ActionMapping
     *            mapping
     * @param ActionForm
     *            form
     * @param HttpServletRequest
     *            request
     * @param HttpServletResponse
     *            response
     * @return ActionForward
     * @throws Exception
     */
    public ActionForward preview(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        request.getSession().setAttribute("currentPage", "0");
        request.getSession().removeAttribute("currentPage2");
        ComparativeGenomicForm comparativeGenomicForm = (ComparativeGenomicForm) form;
        logger.debug("This is a Comparative Genomic Preview");
        //Create Query Objects
        ComparativeGenomicQuery cghQuery = createCGHQuery(comparativeGenomicForm);
        if(cghQuery != null){
        	cghQuery.setInstitutionCriteria(InsitutionAccessHelper.getInsititutionCriteria(request.getSession()));
            }
        //This is required as struts resets the form.  It is later added back to the request
        request.setAttribute("previewForm", comparativeGenomicForm.cloneMe());
        CompoundQuery compoundQuery = new CompoundQuery(cghQuery);
        compoundQuery.setQueryName(RembrandtConstants.PREVIEW_RESULTS);
        logger.debug("Setting query name to:"+compoundQuery.getQueryName());
        compoundQuery.setAssociatedView(ViewFactory
                .newView(ViewType.COPYNUMBER_GROUP_SAMPLE_VIEW));
        logger.debug("Associated View for the Preview:"+compoundQuery.getAssociatedView().getClass());
	    //Save the sessionId that this preview query is associated with
        compoundQuery.setSessionId(request.getSession().getId());
        //Generate the reportXML for the preview.  It will be stored in the session
	    //cache for later retrieval
        ReportGeneratorHelper reportHelper = new ReportGeneratorHelper(compoundQuery, new HashMap());
        return mapping.findForward("previewReport");
    }
            
    
    private ComparativeGenomicQuery createCGHQuery(ComparativeGenomicForm comparativeGenomicForm){
        //Create Query Objects
        ComparativeGenomicQuery cghQuery = (ComparativeGenomicQuery) QueryManager
                .createQuery(QueryType.CGH_QUERY_TYPE);
        cghQuery.setQueryName(comparativeGenomicForm.getQueryName());
        String thisView = comparativeGenomicForm.getResultView();
        // Change this code later to get view type directly from Form !!
        if (thisView.equalsIgnoreCase("sample")) {
            cghQuery.setAssociatedView(ViewFactory
                    .newView(ViewType.CLINICAL_VIEW));
        } else if (thisView.equalsIgnoreCase("gene")) {
            cghQuery.setAssociatedView(ViewFactory
                    .newView(ViewType.GENE_SINGLE_SAMPLE_VIEW));
        }

        // set Disease criteria
        DiseaseOrGradeCriteria diseaseOrGradeCriteria = comparativeGenomicForm
                .getDiseaseOrGradeCriteria();
        if (!diseaseOrGradeCriteria.isEmpty()) {
            cghQuery.setDiseaseOrGradeCrit(diseaseOrGradeCriteria);
        }
       
        // Set gene criteria
        GeneIDCriteria geneIDCrit = comparativeGenomicForm.getGeneIDCriteria();
        if (!geneIDCrit.isEmpty()) {
            cghQuery.setGeneIDCrit(geneIDCrit);
        }
        
        // Set all genes criteria
        AllGenesCriteria allGenesCrit = comparativeGenomicForm.getAllGenesCriteria();
		if (!allGenesCrit.isEmpty())
		    cghQuery.setAllGenesCrit(allGenesCrit);
        
        SampleCriteria sampleIDCrit = comparativeGenomicForm.getSampleCriteria();
		if (!sampleIDCrit.isEmpty())
		    cghQuery.setSampleIDCrit(sampleIDCrit);

        // set copy number criteria
        CopyNumberCriteria CopyNumberCrit = comparativeGenomicForm
                .getCopyNumberCriteria();
        if (!CopyNumberCrit.isEmpty()) {
            cghQuery.setCopyNumberCrit(CopyNumberCrit);
        }

        // set region criteria
        RegionCriteria regionCrit = comparativeGenomicForm.getRegionCriteria();
        if (!regionCrit.isEmpty()) {
            cghQuery.setRegionCrit(regionCrit);
        }

        // set clone/probe criteria
        CloneOrProbeIDCriteria cloneOrProbeIDCriteria = comparativeGenomicForm
                .getCloneOrProbeIDCriteria();
        if (!cloneOrProbeIDCriteria.isEmpty()) {
            cghQuery.setCloneOrProbeIDCrit(cloneOrProbeIDCriteria);
        }

        // set snp criteria
        SNPCriteria snpCrit = comparativeGenomicForm.getSNPCriteria();
        if (!snpCrit.isEmpty()) {
            cghQuery.setSNPCrit(snpCrit);
        }

        // set allele criteria
        AlleleFrequencyCriteria alleleFrequencyCriteria = comparativeGenomicForm
                .getAlleleFrequencyCriteria();
        if (!alleleFrequencyCriteria.isEmpty()) {
            cghQuery.setAlleleFrequencyCrit(alleleFrequencyCriteria);
        }

        AssayPlatformCriteria assayPlatformCriteria = comparativeGenomicForm
                .getAssayPlatformCriteria();
        if (!assayPlatformCriteria.isEmpty()) {
            cghQuery.setAssayPlatformCrit(assayPlatformCriteria);
        }
	    else {
			/*
			 * This logic is required for an all genes query.  There
			 * must be an AssayPlatformDE specified for the all gene's
			 * query, and there was not one being created.  This is 
			 * probably a hack as we may later allow the user to select
			 * from the a list of platforms, and all could be the default.
			 * --Dave
			 */
	    	assayPlatformCriteria = new AssayPlatformCriteria();
	    	assayPlatformCriteria.setAssayPlatformDE(new AssayPlatformDE(Constants.AFFY_100K_SNP_ARRAY));
	    	cghQuery.setAssayPlatformCrit(assayPlatformCriteria);
		}
        
        
        
        return cghQuery;
    }
    
    protected Map getKeyMethodMap() {
		 
      HashMap<String,String> map = new HashMap<String,String>();
      //Comparative Genomic Query Button using comparative genomic setup method
      map.put("ComparativeGenomicAction.setupButton", "setup");
      
      //Submit Query Button using comparative genomic submittal method
      map.put("buttons_tile.submittalButton", "submittal");
      
      //Preview Query Button using comparative genomic preview method
      map.put("buttons_tile.previewButton", "preview");
      
      //Submit All Genes Button using cgh submitAllGenes method
      map.put("buttons_tile.submitAllGenes", "submitAllGenes");
      
      //Submit Standard Button using cgh expression submitStandard method
      map.put("buttons_tile.submitStandard", "submitStandard");
      
      //Submit to get the cytobands of the selected chromosome
      map.put("ComparativeGenomicAction.getCytobands", "getCytobands");
      
      //Submit nothing if multiuse button entered if css turned off
      map.put("buttons_tile.multiUseButton", "multiUse");
      
      return map;
      
      }
    
    
}