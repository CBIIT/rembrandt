// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package gov.nih.nci.nautilus.ui.struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import org.apache.struts.action.ActionError;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import java.util.*;
import java.lang.reflect.*;
import java.io.*;

import gov.nih.nci.nautilus.constants.NautilusConstants;
import gov.nih.nci.nautilus.criteria.*;
import gov.nih.nci.nautilus.de.*;


/** 
 * ComparitivegenomicForm.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 09-12-2004
 * 
 * XDoclet definition:
 * @struts:form name="comparitivegenomicForm"
 */
public class ComparativeGenomicForm extends BaseForm {
    private static Logger logger = Logger.getLogger(ComparativeGenomicForm.class);
	// --------------------------------------------------------- Instance Variables

	/** geneList property */
	private String geneList;

	/** tumorGrade property */
	private String tumorGrade;

	/** assayPlatform property */
	private String assayPlatform;

	/** region property */
	private String region;

	/** cytobandRegion property */
	private String cytobandRegion;

	/** snpList property */
	private String snpList;

	/** cloneId property */
	private String cloneId;

	/** cnAmplified property */
	private String cnAmplified;

	/** tumorType property */
	private String tumorType;

	/** cloneListFile property */
	private String cloneListFile;

	/** snpListFile property */
	private FormFile snpListFile;

	/** cloneListSpecify property */
	private String cloneListSpecify;

	/** snpListSpecify property */
	private String snpListSpecify;

	/** cnADAmplified property */
	private String cnADAmplified;

	/** genomicTrack property */
	private String genomicTrack;

	/** basePairEnd property */
	private String basePairEnd;

	/** chrosomeNumber property */
	private String chrosomeNumber;

	/** cnADDeleted property */
	private String cnADDeleted;

	/** cnUnchangeTo property */
	private String cnUnchangeTo;

	/** alleleFrequency property */
	private String alleleFrequency;

	/** geneType property */
	private String geneType;

	/** validatedSNP property */
	private String validatedSNP;

	/** resultView property */
	private String resultView;

	/** geneFile property */
	private FormFile geneFile;

	/** snpId property */
	private String snpId;

	/** cnDeleted property */
	private String cnDeleted;

	/** geneGroup property */
	private String geneGroup;

	/** cnUnchangeFrom property */
	private String cnUnchangeFrom;

	/** cloneList property */
	private String cloneList;

	/** queryName property */
	private String queryName;

	/** copyNumber property */
	private String copyNumber;

	/** basePairStart property */
	private String basePairStart;

	
	// Collections used for Lookup values.
	
/*	
 ** moved to the upper class: BaseForm.java
  private ArrayList diseaseTypes;
  private ArrayList geneTypeColl;
  */
  private ArrayList cloneTypeColl;
  private ArrayList snpTypes;
  private ArrayList alleleTypes;
  private ArrayList assayTypes;  
  
  
    //HashMap to store Domain Elements  
  private HashMap diseaseDomainMap;
  private HashMap geneDomainMap;
  private HashMap copyNoAmpDomainMap;
  private HashMap copyNoDelDomainMap;
  private HashMap regionDomainMap;
  private HashMap cloneDomainMap;
  private HashMap snpDomainMap;
  private HashMap alleleDomainMap;// this may be implemented this release.
  private HashMap assayDomainMap;
  
  private DiseaseOrGradeCriteria diseaseOrGradeCriteria;	
  private GeneIDCriteria geneCriteria;
  private CopyNumberCriteria copyNumberCriteria;
  private RegionCriteria regionCriteria;
  private CloneOrProbeIDCriteria cloneOrProbeIDCriteria;
  private SNPCriteria snpCriteria;
  private AlleleFrequencyCriteria alleleFrequencyCriteria;
  private AssayPlatformCriteria assayPlatformCriteria;
  
  private  HttpServletRequest thisRequest;
  
  //----------------------------constuctor()
  
   public ComparativeGenomicForm(){
     super();
     startComparativeGemomicLookup();
	}
  
	// --------------------------------------------------------- Methods
	
	private void startComparativeGemomicLookup(){
	  /*  
	   ** moved to the upper class: BaseForm.java  
      diseaseTypes = new ArrayList();
	  geneTypeColl = new ArrayList();
	  */
	  cloneTypeColl = new ArrayList();
	  snpTypes = new ArrayList();
	  alleleTypes = new ArrayList();
	  assayTypes = new ArrayList();
	  
	 // These are hardcoded but will come from DB
	  
	  cloneTypeColl.add(new LabelValueBean("IMAGE Id","imageId"));
	  cloneTypeColl.add(new LabelValueBean("BAC Id","BACId"));
	  
	  
	  //snpTypes.add(new LabelValueBean("TSC Id","TSCId"));
	  snpTypes.add(new LabelValueBean("dBSNP Id","dBSNPId"));
	  snpTypes.add(new LabelValueBean("SNP Probe Set Id","probeSetId"));
	  
	  alleleTypes.add(new LabelValueBean("ALL","ALL"));
	  alleleTypes.add(new LabelValueBean("CENTRAL ASIA","CENTRAL ASIA"));
	  alleleTypes.add(new LabelValueBean("CENTRAL/SOUTH AFRICA","CENTRAL/SOUTH AFRICA"));
	  alleleTypes.add(new LabelValueBean("CENTRAL/SOUTH AMERICA","CENTRAL/SOUTH AMERICA"));
	  alleleTypes.add(new LabelValueBean("EAST ASIA","EAST ASIA"));
	  alleleTypes.add(new LabelValueBean("EUROPE","EUROPE"));
	  alleleTypes.add(new LabelValueBean("MULTI-NATIONAL","MULTI-NATIONAL"));
	  alleleTypes.add(new LabelValueBean("NORTH AMERICA","NORTH AMERICA"));
	  alleleTypes.add(new LabelValueBean("NORTH/EAST AFRICA  MIDDLE EASTL","NORTH/EAST AFRICA  MIDDLE EAST"));
	  alleleTypes.add(new LabelValueBean("NOT SPECIFIED","NOT SPECIFIED"));
	  alleleTypes.add(new LabelValueBean("PACIFIC","PACIFIC"));
	  alleleTypes.add(new LabelValueBean("UNKNOWN","UNKNOWN"));
	  alleleTypes.add(new LabelValueBean("WEST AFRICA","WEST AFRICA"));
	  
	  assayTypes.add(new LabelValueBean("All","All"));
	  assayTypes.add(new LabelValueBean("100K SNP Array","100K SNP Array"));
	  assayTypes.add(new LabelValueBean("Array CGH","Array CGH"));	  
	  
  }
  
  

	/** 
	 * Method validate
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 * @return ActionErrors
	 */
	public ActionErrors validate(
		ActionMapping mapping,
		HttpServletRequest request) {

			ActionErrors errors = new ActionErrors();
			logger.debug("I am in the cgh validata method()");
		
			// Query Name cannot be blank
			if ((queryName == null || queryName.length() < 1))
				errors.add("queryName", new ActionError("gov.nih.nci.nautilus.ui.struts.form.queryname.no.error"));

			if (this.getChrosomeNumber().trim().length() > 0){
				if (this.getRegion().trim().length() < 1) 
					errors.add("chrosomeNumber", new ActionError("gov.nih.nci.nautilus.ui.struts.form.region.no.error"));
				else {
					if (this.getRegion().trim().equalsIgnoreCase("cytoband")){
						if (this.getCytobandRegion().trim().length() < 1) 
							errors.add("cytobandRegion", new ActionError("gov.nih.nci.nautilus.ui.struts.form.cytobandregion.no.error"));
					}
					if (this.getRegion().trim().equalsIgnoreCase("basePairPosition")){
						if ((this.getBasePairStart().trim().length() < 1) || (this.getBasePairEnd().trim().length() < 1)){
							errors.add("basePairEnd", new ActionError("gov.nih.nci.nautilus.ui.struts.form.basePair.no.error"));
						}else{
							if (!isBasePairValid(this.getBasePairStart(), this.getBasePairEnd()))
								errors.add("basePairEnd", new ActionError("gov.nih.nci.nautilus.ui.struts.form.basePair.incorrect.error"));
							}
					}
					
				}
				
			}
			if (this.getSnpId() != null && this.getSnpId().trim().length() >= 1){
				if (this.getSnpList().trim().length() < 1 && this.getSnpListFile() == null){
					errors
					.add(
							"snpId",
							new ActionError(
									"gov.nih.nci.nautilus.ui.struts.form.snpid.no.error"));
				}
			
			}
			if (this.getGeneGroup() != null && this.getGeneGroup().trim().length() >= 1){
				if (this.getGeneList().trim().length() < 1 && this.getGeneFile() == null){
					errors
					.add(
							"geneGroup",
							new ActionError(
									"gov.nih.nci.nautilus.ui.struts.form.geneGroup.no.error"));
				}
			
			}
			//Make sure the uploaded File is of type txt and MIME type is text/plain
			if(this.getGeneFile() != null  &&
			  (!(this.getGeneFile().getFileName().endsWith(".txt"))) &&
			  (!(this.getGeneFile().getContentType().equals("text/plain")))){
				errors
				.add(
						"geneGroup",
						new ActionError(
								"gov.nih.nci.nautilus.ui.struts.form.uploadFile.no.error"));
			}
			
			//Make sure the uploaded File is of type txt and MIME type is text/plain
			if(this.getSnpListFile() != null  &&
			  (!(this.getSnpListFile().getFileName().endsWith(".txt"))) &&
			  (!(this.getSnpListFile().getContentType().equals("text/plain")))){
				errors
				.add(
						"snpId",
						new ActionError(
								"gov.nih.nci.nautilus.ui.struts.form.uploadFile.no.error"));
			}	
				
			// Validate minimum criteria's for GE Query 
			if (this.getQueryName() != null && this.getQueryName().length() >= 1) {
			   if ((this.getGeneGroup() == null || this.getGeneGroup().trim().length() < 1) &&
					(this.getChrosomeNumber() == null || this.getChrosomeNumber().trim().length() < 1)) {
					if ((this.getSnpId() == null || this.getSnpId().trim().length() < 1) ||
						(this.getSnpListSpecify().length() < 1 && this.getSnpListFile()==null) ||
						(this.getSnpListSpecify().length() >= 1 && (!this.getSnpList().equalsIgnoreCase("dBSNPId") && 
						!this.getSnpList().equalsIgnoreCase("probeSetId")))){
				
					errors
					.add(
							ActionErrors.GLOBAL_ERROR,
							new ActionError(
									"gov.nih.nci.nautilus.ui.struts.form.cgh.minimum.error"));
					}
				}
			}


		  if (errors.isEmpty()) {// if there are no errors, then proceed.
		    createDiseaseCriteriaObject();
			createGeneCriteriaObject();
			createCopyNumberCriteriaObject();
			createRegionCriteriaObject();
			createCloneOrProbeCriteriaObject();
			createSNPCriteriaObject();
			createAlleleFrequencyCriteriaObject();
			createAssayPlatformCriteriaObject();		
		}
			return errors;

	}
	
	
  /* createDiseaseCriteriaObject() mtethod is to look through the diseaseDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */
private void createDiseaseCriteriaObject(){
   if(diseaseDomainMap != null){
     Set keySet = diseaseDomainMap.keySet();
	 Iterator iter = keySet.iterator();
	 while(iter.hasNext()){
	    try{
		    String key = (String)iter.next();
			String className = (String)diseaseDomainMap.get(key);
			Constructor[] diseaseConstructors = Class.forName(className).getConstructors();
			String[] initargs = {key};
			DiseaseNameDE diseaseDE = (DiseaseNameDE)diseaseConstructors[0].newInstance(initargs);		
			diseaseOrGradeCriteria.setDisease(diseaseDE);	   
		     } // end of try
		catch (Exception ex) {
		    logger.error("Error in createDiseaseCriteriaObject() method:  "+ex.getMessage());
				logger.error(ex);
			} 
		catch (LinkageError le) {
		    	logger.error("Linkage Error in createDiseaseCriteriaObject() method: "+ le.getMessage());
				logger.error(le);
			}	
			
		 }// end of while		 
		   
      }// end of if
   }
   
/* createGeneCriteriaObject() mtethod is to look through the geneDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createGeneCriteriaObject(){
   if(geneDomainMap.size()>0){
     Set keys = geneDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
	   try{
		   String key = (String)iter.next();
		   String className = (String)geneDomainMap.get(key);
		   Constructor[] geneConstructors = Class.forName(className).getConstructors();
		   String [] initargs = {key};
		   GeneIdentifierDE geneIdentifierDE = (GeneIdentifierDE)geneConstructors[0].newInstance(initargs);
		   geneCriteria.setGeneIdentifier(geneIdentifierDE);
		   }//end of try
	   catch (Exception ex) {
	       	logger.error("Error in createGeneCriteriaObject() method:  "+ex.getMessage());
			logger.error(ex);
	   } 
	   catch (LinkageError le) {
	       		logger.error("Linkage Error in createGeneCriteriaObject() method: "+ le.getMessage());
				logger.error(le);
	   }	
						   
		} //end of while   
    } // end of if 
 }
 
  /* createCopyNumberCriteriaObject() mtethod is to look through the copyNoDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createCopyNumberCriteriaObject(){
   if(copyNoAmpDomainMap.size()>0){
     Set keys = copyNoAmpDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
	   try{
		   String key = (String)iter.next();
		   String className = (String)copyNoAmpDomainMap.get(key);
		   Constructor[] copyNoConstructors = Class.forName(className).getConstructors();
		   
		   Object [] initargs = {Float.valueOf((String) key)};		  
		   CopyNumberDE copyNumberDE = (CopyNumberDE)copyNoConstructors[0].newInstance(initargs);
		   copyNumberCriteria.setCopyNumber(copyNumberDE);
		    }// end of try
	   catch (Exception ex) {
	       logger.error("Error in createCopyNumberCriteriaObject() method:  "+ex.getMessage());
	       logger.error(ex);
			} 
	   catch (LinkageError le) {
				logger.error("Linkage Error in createCopyNumberCriteriaObject() method: "+ le.getMessage());
				logger.error(le);
			}	
						   
		} //end of while   	
     }   // end of if
	if(copyNoDelDomainMap.size()>0){
	  Set keys = copyNoDelDomainMap.keySet();
	  Iterator iter = keys.iterator();
	  while(iter.hasNext()){
		try{
			String key = (String)iter.next();
			String className = (String)copyNoDelDomainMap.get(key);
			Constructor[] copyNoConstructors = Class.forName(className).getConstructors();
		   
			Object [] initargs = {Float.valueOf((String) key)};		  
			CopyNumberDE copyNumberDE = (CopyNumberDE)copyNoConstructors[0].newInstance(initargs);
			copyNumberCriteria.setCopyNumber(copyNumberDE);
			 }// end of try
		catch (Exception ex) {
		    logger.error("Error in createCopyNumberCriteriaObject() method:  "+ex.getMessage());
		    logger.error(ex);
			 } 
		catch (LinkageError le) {
		    logger.error("Linkage Error in createCopyNumberCriteriaObject() method: "+ le.getMessage());
		    	logger.error(le);
			 }	
						   
		 } //end of while   	
	  }   // end of if
 }

 /* createRegionCriteriaObject() mtethod is to look through the regionDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createRegionCriteriaObject(){
   if(regionDomainMap.size()>0){
     Set keys = regionDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
	   try{
		   String key = (String)iter.next();
		   String className = (String)regionDomainMap.get(key);
		   Constructor[] regionConstructors = Class.forName(className).getConstructors();
		   	   
		   if(className.endsWith("CytobandDE")){
		     String [] initargs = {key};
		     CytobandDE cytobandDE = (CytobandDE)regionConstructors[0].newInstance(initargs);	
			 regionCriteria.setCytoband(cytobandDE);    
		     }
		  else if(className.endsWith("ChromosomeNumberDE")){
		     String [] initargs = {key};
			 ChromosomeNumberDE chromosomeNumberDE = (ChromosomeNumberDE)regionConstructors[0].newInstance(initargs);	
			 regionCriteria.setChromNumber(chromosomeNumberDE);
		    
		  }
		   else if(className.endsWith("StartPosition")){
		     Integer [] initargs = {new Integer(Integer.parseInt(key))};
		     BasePairPositionDE.StartPosition  startPosition = (BasePairPositionDE.StartPosition)regionConstructors[0].newInstance(initargs);	
			 regionCriteria.setStart(startPosition);    
		     }
		    
		   else if(className.endsWith("EndPosition")){
		     Integer [] initargs = {new Integer(Integer.parseInt(key))};
		     BasePairPositionDE.EndPosition  endPosition = (BasePairPositionDE.EndPosition)regionConstructors[0].newInstance(initargs);	
			 regionCriteria.setEnd(endPosition);    
		      }
		   }//end of try	
		   
		catch (Exception ex) {
		    logger.error("Error in createRegionCriteriaObject() method: "+ex.getMessage());
		    logger.error(ex);;
			} 
	     catch (LinkageError le) {
	         logger.error("Linkage Error in createRegionCriteriaObject() method: "+ le.getMessage());
	         logger.error(le);;
			}	   
		     
	    }// end of while
     }   // end of if
 }
 
   /* createCloneOrProbeCriteriaObject() mtethod is to look through the cloneDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createCloneOrProbeCriteriaObject(){
   if(cloneDomainMap.size()>0){
     Set keys = cloneDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
	   try{
		   String key = (String)iter.next();
		   String className = (String)cloneDomainMap.get(key);
		   Constructor[] cloneConstructors = Class.forName(className).getConstructors();
		   String [] initargs = {key};
		   CloneIdentifierDE cloneIdentifierDE = (CloneIdentifierDE)cloneConstructors[0].newInstance(initargs);
		   cloneOrProbeIDCriteria.setCloneIdentifier(cloneIdentifierDE);
		   }// end of try
		catch (Exception ex) {
		    logger.error("Error in createCloneOrProbeCriteriaObject() method:  "+ex.getMessage());
		    logger.error(ex);
			} 
	    catch (LinkageError le) {
	        logger.error("Linkage Error in createCloneOrProbeCriteriaObject()method: "+ le.getMessage());
	        logger.error(le);
			}	   
		} // end of while
    } // end of if 
 }
 /* createSNPCriteriaObject() mtethod is to look through the snpDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createSNPCriteriaObject(){
   if(snpDomainMap.size()>0){
       logger.debug("snpDomainMap.size():"+snpDomainMap.size());
     Set keys = snpDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
	   try{
		   String key = (String)iter.next();
		   String className = (String)snpDomainMap.get(key);
		   Constructor[] snpConstructors = Class.forName(className).getConstructors();
		   String [] initargs = {key};
		   SNPIdentifierDE snpIdentifierDE = (SNPIdentifierDE)snpConstructors[0].newInstance(initargs);
		   snpCriteria.setSNPIdentifier(snpIdentifierDE);
		   }// end of try
	   catch (Exception ex) {
	       logger.error("Error in createSNPCriteriaObject() method : "+ex.getMessage());
	       logger.error(ex);
			} 
	   catch (LinkageError le) {
	       logger.error("Linkage Error in createSNPCriteriaObject() method:  "+ le.getMessage());
	       logger.error(le);
			}   
		   
		}// end of while   
    }  // end of if
 }
 
 /* createAlleleFrequencyCriteriaObject() mtethod is to look through the alleleDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createAlleleFrequencyCriteriaObject(){
   if(alleleDomainMap.size()>0){
     Set keys = alleleDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
	   try{
		   String key = (String)iter.next();
		   String className = (String)alleleDomainMap.get(key);
		   Constructor[] geneConstructors = Class.forName(className).getConstructors();
		   String [] initargs = {key};
		   AlleleFrequencyDE alleleFrequencyDE = (AlleleFrequencyDE)geneConstructors[0].newInstance(initargs);
		   alleleFrequencyCriteria.setAlleleFrequencyDE(alleleFrequencyDE);
		   }// end of try
	   catch (Exception ex) {
	       logger.error("Error in createAlleleFrequencyCriteriaObject() method : "+ex.getMessage());
	       logger.error(ex);
			} 
	   catch (LinkageError le) {
	       logger.error("Linkage Error in createAlleleFrequencyCriteriaObject() method:  "+ le.getMessage());
	       logger.error(le);
			}     
		} //end of while   
    }// end of if  
 }
  /* createAssayPlatformCriteriaObject() mtethod is to look through the assayDomainMap
  ** and extract out the domain elements and create respective Criteria Objects
  */  
private void createAssayPlatformCriteriaObject(){
   if(assayDomainMap.size()>0){
     Set keys = assayDomainMap.keySet();
	 Iterator iter = keys.iterator();
	 while(iter.hasNext()){
		try{
		   String key = (String)iter.next();
		   String className = (String)assayDomainMap.get(key);
		   Constructor[] geneConstructors = Class.forName(className).getConstructors();
		   String [] initargs = {key};
		   AssayPlatformDE assayPlatformDE = (AssayPlatformDE)geneConstructors[0].newInstance(initargs);
		   assayPlatformCriteria.setAssayPlatformDE(assayPlatformDE);
		 }// end of try
	   catch (Exception ex) {
	       logger.error("Error in createAssayPlatformCriteriaObject() method : "+ex.getMessage());
	       logger.error(ex);
			} 
	    catch (LinkageError le) {
	        logger.error("Linkage Error in createAssayPlatformCriteriaObject() method:  "+ le.getMessage());
			logger.error(le);
			}    
	   }// end of while
    }// end of if
 }
 
 	/** 
	 * Method reset
	 * @param ActionMapping mapping
	 * @param HttpServletRequest request
	 */
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		geneList = "";
		tumorGrade = "";
		assayPlatform = "";
		region = "";
		cytobandRegion = "";
		snpList = "";
		cloneId = "";
		cnAmplified = "";
		tumorType = "";
		cloneListFile = "";
		snpListFile = null;
		cloneListSpecify = "";
		snpListSpecify = "";
		cnADAmplified = "";
		genomicTrack = "";
		basePairEnd = "";
		chrosomeNumber = "";
		cnADDeleted = "";
		cnUnchangeTo = "";
		alleleFrequency = "";
		geneType = "";
		validatedSNP = "";
		resultView = "";
		geneFile = null;
		snpId = "";
		cnDeleted = "";
		geneGroup = "";
		cnUnchangeFrom = "";
		cloneList = "";
		queryName = "";
		copyNumber = "";
		basePairStart = "";
		
		
		
		 diseaseDomainMap = new HashMap();
	     geneDomainMap = new HashMap();
	     copyNoAmpDomainMap = new HashMap();
		 copyNoDelDomainMap = new HashMap();
	     regionDomainMap = new HashMap();
	     cloneDomainMap = new HashMap();
	     snpDomainMap = new HashMap();
	     alleleDomainMap = new HashMap();// this may be implemented this release.
	     assayDomainMap = new HashMap();
		 
		 diseaseOrGradeCriteria = new DiseaseOrGradeCriteria();	
	     geneCriteria = new GeneIDCriteria();
	     copyNumberCriteria = new CopyNumberCriteria();
	     regionCriteria = new RegionCriteria();
	     cloneOrProbeIDCriteria = new CloneOrProbeIDCriteria();
	     snpCriteria = new SNPCriteria();
	     alleleFrequencyCriteria = new AlleleFrequencyCriteria();
	     assayPlatformCriteria = new AssayPlatformCriteria();
	 
		 // reset the request object
		 thisRequest = request;    

	}

	/** 
	 * Returns the geneList.
	 * @return String
	 */
	public String getGeneList() {
		return geneList;
	}

	/** 
	 * Set the geneList.
	 * @param geneList The geneList to set
	 */
	public void setGeneList(String geneList) {
		this.geneList = geneList;
		
		 // this is the ratio button indicating gene choice has been seleted.
       String thisGeneGroup = (String)this.thisRequest.getParameter("geneGroup");
  
        // this indicates the type of gene choices: either it is gene name/symbo, 
        // locus link id or genBank accNo
        String thisGeneType =(String) this.thisRequest.getParameter("geneType");
 
        if(thisGeneGroup != null && thisGeneGroup.equalsIgnoreCase("Specify") && thisGeneType != null ){   
     
	       if(!thisGeneType.equals("allgenes") && this.geneList != null && !this.geneList.equals("")){
			 String[] geneStr = this.geneList.split("\\x2C");
			    for(int i = 0; i < geneStr.length; i++){
				   if(thisGeneType.equalsIgnoreCase("genesymbol")){	     
				      geneDomainMap.put(geneStr[i].trim(), GeneIdentifierDE.GeneSymbol.class.getName());
				      }
				   else if (thisGeneType.equalsIgnoreCase("locusLinkId")){	
				      geneDomainMap.put(geneStr[i].trim(), GeneIdentifierDE.LocusLink.class.getName());
				     }
				   else if(thisGeneType.equalsIgnoreCase("genBankAccNo")){
				     geneDomainMap.put(geneStr[i].trim(), GeneIdentifierDE.GenBankAccessionNumber.class.getName());
				     }	   
			      } // end of for loop 
		    }// end of if(this.geneList != null && !this.geneList.equals(""))
		   else if(thisGeneType.equals("allgenes")&&  this.geneList == null){
		         geneDomainMap.put("allgenes", GeneIdentifierDE.GeneSymbol.class.getName());
			   }
		   }
       }
	   
	   
	
	public DiseaseOrGradeCriteria getDiseaseOrGradeCriteria() {
		return this.diseaseOrGradeCriteria;
	}
	   
    public GeneIDCriteria getGeneIDCriteria() {
		return this.geneCriteria;
	}
	
	public CopyNumberCriteria getCopyNumberCriteria() {
		return this.copyNumberCriteria;
	}
	public RegionCriteria getRegionCriteria() {
		return this.regionCriteria;
	}

	
	public CloneOrProbeIDCriteria getCloneOrProbeIDCriteria() {
		return this.cloneOrProbeIDCriteria;
	}
	public SNPCriteria getSNPCriteria() {
		return this.snpCriteria;
	}

	public AlleleFrequencyCriteria getAlleleFrequencyCriteria() {
		return this.alleleFrequencyCriteria;
	}
	
	public AssayPlatformCriteria getAssayPlatformCriteria(){
	   return assayPlatformCriteria;	
	}
	/** 
	 * Returns the tumorGrade.
	 * @return String
	 */
	public String getTumorGrade() {
		return tumorGrade;
	}

	/** 
	 * Set the tumorGrade.
	 * @param tumorGrade The tumorGrade to set
	 */
	public void setTumorGrade(String tumorGrade) {
		this.tumorGrade = tumorGrade;
	}

	/** 
	 * Returns the assayPlatform.
	 * @return String
	 */
	public String getAssayPlatform() {
		return assayPlatform;
	}

	/** 
	 * Set the assayPlatform.
	 * @param assayPlatform The assayPlatform to set
	 */
	public void setAssayPlatform(String assayPlatform) {
		this.assayPlatform = assayPlatform;
		assayDomainMap.put(this.assayPlatform, AssayPlatformDE.class.getName());	
	}

	/** 
	 * Returns the region.
	 * @return String
	 */
	public String getRegion() {
		return region;
	}

	/** 
	 * Set the region.
	 * @param region The region to set
	 */
	public void setRegion(String region) {
		this.region = region;
	}

	/** 
	 * Returns the cytobandRegion.
	 * @return String
	 */
	public String getCytobandRegion() {
		return cytobandRegion;
	}

	/** 
	 * Set the cytobandRegion.
	 * @param cytobandRegion The cytobandRegion to set
	 */
	public void setCytobandRegion(String cytobandRegion) {
		this.cytobandRegion = cytobandRegion;
		String thisRegion = this.thisRequest.getParameter("region"); 		
		String thisChrNumber = this.thisRequest.getParameter("chrosomeNumber");
		
		if (thisChrNumber != null && thisChrNumber.trim().length()>0){
			
			if (thisRegion != null && thisRegion.equalsIgnoreCase("cytoband") && this.cytobandRegion.trim().length() > 0){
				regionDomainMap.put(this.cytobandRegion, CytobandDE.class.getName());
				}
		}

	}

	/** 
	 * Returns the snpList.
	 * @return String
	 */
	public String getSnpList() {
		return snpList;
	}

	/** 
	 * Set the snpList.
	 * @param snpList The snpList to set
	 */
	public void setSnpList(String snpList) {
		this.snpList = snpList;
	}

	/** 
	 * Returns the cloneId.
	 * @return String
	 */
	public String getCloneId() {
		return cloneId;
	}

	/** 
	 * Set the cloneId.
	 * @param cloneId The cloneId to set
	 */
	public void setCloneId(String cloneId) {
		this.cloneId = cloneId;
	}

	/** 
	 * Returns the cnAmplified.
	 * @return String
	 */
	public String getCnAmplified() {
		return cnAmplified;
	}

	/** 
	 * Set the cnAmplified.
	 * @param cnAmplified The cnAmplified to set
	 */
	public void setCnAmplified(String cnAmplified) {
		this.cnAmplified = cnAmplified;
		// need to make sure the parameters such as copyNumberAmplified and regulationStatus
		// match the ones declared on the copyNumber_tile.jsp
		String thisCopyNumber = this.thisRequest.getParameter("copyNumber"); 		
		if (thisCopyNumber != null && thisCopyNumber.equalsIgnoreCase("amplified") && (this.cnAmplified.length() > 0)){
			copyNoAmpDomainMap.put(this.cnAmplified, CopyNumberDE.Amplification.class.getName());	
			}
	}

	/** 
	 * Returns the tumorType.
	 * @return String
	 */
	public String getTumorType() {
		return tumorType;
	}

	/** 
	 * Set the tumorType.
	 * @param tumorType The tumorType to set
	 */
	public void setTumorType(String tumorType) {
		this.tumorType = tumorType;

		if (this.tumorType.equalsIgnoreCase("ALL")) {
			ArrayList allDiseases = this.getDiseaseType();
			for (Iterator diseaseIter = allDiseases.iterator(); diseaseIter.hasNext();) {
				LabelValueBean thisLabelBean = (LabelValueBean) diseaseIter.next();
				String thisDiseaseType = thisLabelBean.getValue();
				// stuff this in our DomainMap for later use !!
				if (!thisDiseaseType.equalsIgnoreCase("ALL")){
					diseaseDomainMap.put(thisDiseaseType, DiseaseNameDE.class.getName());
				}
			}		 
		}else{ 
			diseaseDomainMap.put(this.tumorType, DiseaseNameDE.class.getName());
		}
			
	}

	/** 
	 * Returns the cloneListFile.
	 * @return String
	 */
	public String getCloneListFile() {
		return cloneListFile;
	}

	/** 
	 * Set the cloneListFile.
	 * @param cloneListFile The cloneListFile to set
	 */
	public void setCloneListFile(String cloneListFile) {
		this.cloneListFile = cloneListFile;
			// this is to check if the radio button is selected for the clone category
		String thisCloneId = (String)thisRequest.getParameter("cloneId");
	   // this is to check the type of the clone
	   String thisCloneList = (String)thisRequest.getParameter("cloneList");	
	   if ((thisCloneId != null) && thisCloneId.equalsIgnoreCase("Upload") && (thisCloneList.length() >0) && (this.cloneListFile.length() > 0)){
		     
             File cloneFile = new File(this.cloneListFile);
			 String line = null;
			 try{
			   FileReader editfr = new FileReader (cloneFile);
		       BufferedReader inFile = new BufferedReader (editfr);           
		       line = inFile.readLine();
			   int i=0;
			 
			   while (line != null && line.length()>0) {				  		     
			      StringTokenizer st = new StringTokenizer(line);
			      while(st.hasMoreTokens()){			   
				      String token = st.nextToken();
					  if (thisCloneList.equalsIgnoreCase("imageId")){
					    cloneDomainMap.put(token,CloneIdentifierDE.IMAGEClone.class.getName());						
					    } 
					  else if (thisCloneList.equalsIgnoreCase("BACId")){
					    cloneDomainMap.put(token,CloneIdentifierDE.BACClone.class.getName());						
					   }				              
			    
					}
				  line = inFile.readLine();  				  
			    }// end of while
				
			 inFile.close();
			  }
			 catch(IOException ex){
			     logger.error("Errors when uploading gene file:" + ex.getMessage());
			  }
	   }

	}

	

	/** 
	 * Returns the cloneListSpecify.
	 * @return String
	 */
	public String getCloneListSpecify() {
		return cloneListSpecify;
	}

	/** 
	 * Set the cloneListSpecify.
	 * @param cloneListSpecify The cloneListSpecify to set
	 */
	public void setCloneListSpecify(String cloneListSpecify) {
		this.cloneListSpecify = cloneListSpecify;
		
		 // this is to check if the radio button is selected for the clone category
        String thisCloneId = (String)thisRequest.getParameter("cloneId");   
        // this is to check the type of the clone
        String thisCloneList = (String)thisRequest.getParameter("cloneList");   
        if(thisCloneId != null && thisCloneList != null && !thisCloneList.equals("")){
	      if(this.cloneListSpecify != null && !this.cloneListSpecify.equals("")){
		    String [] cloneStr = this.cloneListSpecify.split("\\x2C");
			for(int i=0; i<cloneStr.length; i++){
			  if(thisCloneList.equalsIgnoreCase("imageId")){
			     cloneDomainMap.put(cloneStr[i].trim(),CloneIdentifierDE.IMAGEClone.class.getName());
				}
			  else if(thisCloneList.equalsIgnoreCase("BACId")){	
			     cloneDomainMap.put(cloneStr[i].trim(),CloneIdentifierDE.BACClone.class.getName());
				}
			
			  }	 // end of for loop 
		    }// end of  if(this.cloneListSpecify != null && !cloneListSpecify.equals("")){
	     
         }
	}

	/** 
	 * Returns the snpListSpecify.
	 * @return String
	 */
	public String getSnpListSpecify() {
		return snpListSpecify;
	}

	/** 
	 * Set the snpListSpecify.
	 * @param snpListSpecify The snpListSpecify to set
	 */
	public void setSnpListSpecify(String snpListSpecify) {
		this.snpListSpecify = snpListSpecify;
		
	   // this is to check if the radio button is selected for the SNP category
	   String thisSNPId = (String)thisRequest.getParameter("snpId");	
	  
	   // this is to check the type of the SNP
	   String thisSNPList = (String)thisRequest.getParameter("snpList");
	  
	   if(thisSNPId != null && thisSNPId.equalsIgnoreCase("specify") && thisSNPList != null && !thisSNPList.equals("")){
	      if(this.snpListSpecify != null && !this.snpListSpecify.equals("")){
		    String [] snpStr = this.snpListSpecify.split("\\x2C");
			for(int i=0; i<snpStr.length; i++){
			  if(thisSNPList.equalsIgnoreCase("TSCId")){
			     snpDomainMap.put(snpStr[i].trim(),SNPIdentifierDE.TSC.class.getName());
				}
			  else if(thisSNPList.equalsIgnoreCase("dBSNPId")){	
			     snpDomainMap.put(snpStr[i].trim(),SNPIdentifierDE.DBSNP.class.getName());
				}
			  else if(thisSNPList.equalsIgnoreCase("probeSetId")){	
			     snpDomainMap.put(snpStr[i].trim(),SNPIdentifierDE.SNPProbeSet.class.getName());
				}
			  }	 // end of for loop 
		   }// end of  if(thisSNPId != null && thisSNPList != null && !thisSNPList.equals("")){
	     
	    }
	}

	/** 
	 * Returns the snpListFile.
	 * @return String
	 */
	public FormFile getSnpListFile() {
		return snpListFile;
	}

	/** 
	 * Set the snpListFile.
	 * @param snpListFile The snpListFile to set
	 */
	public void setSnpListFile(FormFile snpListFile) {
		this.snpListFile = snpListFile;
	  // this is to check if the radio button is selected for the SNP category
	   String thisSNPId = (String)thisRequest.getParameter("snpId");	
	   // this is to check the type of the SNP
	   String thisSNPList = (String)thisRequest.getParameter("snpList");

//		retrieve the file name & size
 		String fileName= snpListFile.getFileName();
 		int fileSize = snpListFile.getFileSize();

		if ((thisSNPId != null) && thisSNPId.equalsIgnoreCase("Upload")
				&& (thisSNPList.length() > 0)
				&& (this.snpListFile != null)
				&& (this.snpListFile.getFileName().endsWith(".txt"))
				&& (this.snpListFile.getContentType().equals("text/plain"))) {

				try {
					InputStream stream = snpListFile.getInputStream();				
					String inputLine = null;
					BufferedReader inFile = new BufferedReader( new InputStreamReader(stream));
					int count = 0;
					while ((inputLine = inFile.readLine()) != null && count < NautilusConstants.MAX_FILEFORM_COUNT)  {
						if(isAscii(inputLine)){ //make sure all data is ASCII
							count ++; //increment
							if(thisSNPList.equalsIgnoreCase("TSCId")){
							    snpDomainMap.put(inputLine,SNPIdentifierDE.TSC.class.getName());
							   } 
							   else if(thisSNPList.equalsIgnoreCase("dBSNPId")){	
							    snpDomainMap.put(inputLine,SNPIdentifierDE.DBSNP.class.getName());					   
							   }				              
					           else if(thisSNPList.equalsIgnoreCase("probeSetId")){	
							    snpDomainMap.put(inputLine,SNPIdentifierDE.SNPProbeSet.class.getName());					  
							   }	
						}
					}// end of while

				inFile.close();
			} catch (IOException ex) {
			    logger.error("Errors when uploading snp file:"
						+ ex.getMessage());
			}
		}
	}
	/** 
	 * Returns the cnADAmplified.
	 * @return String
	 */
	public String getCnADAmplified() {
		return cnADAmplified;
	}

	/** 
	 * Set the cnADAmplified.
	 * @param cnADAmplified The cnADAmplified to set
	 */
	public void setCnADAmplified(String cnADAmplified) {
		this.cnADAmplified = cnADAmplified;
		// need to make sure the parameters such as copyNumberADAmplified and regulationStatus
		// match the ones declared on the copyNumber_tile.jsp
		String thisCopyNumber = this.thisRequest.getParameter("copyNumber"); 
		
		if (thisCopyNumber != null && thisCopyNumber.equalsIgnoreCase("ampdel") && (this.cnADAmplified.length() > 0)){
			copyNoAmpDomainMap.put(this.cnADAmplified, CopyNumberDE.Amplification.class.getName());
			}
	
	}

	/** 
	 * Returns the genomicTrack.
	 * @return String
	 */
	public String getGenomicTrack() {
		return genomicTrack;
	}

	/** 
	 * Set the genomicTrack.
	 * @param genomicTrack The genomicTrack to set
	 */
	public void setGenomicTrack(String genomicTrack) {
		this.genomicTrack = genomicTrack;
	}

	/** 
	 * Returns the basePairEnd.
	 * @return String
	 */
	public String getBasePairEnd() {
		return basePairEnd;
	}

	/** 
	 * Set the basePairEnd.
	 * @param basePairEnd The basePairEnd to set
	 */
	public void setBasePairEnd(String basePairEnd) {
		this.basePairEnd = basePairEnd;
		String thisRegion = this.thisRequest.getParameter("region"); 		
		String thisChrNumber = this.thisRequest.getParameter("chrosomeNumber");
		String thisBasePairStart = this.thisRequest.getParameter("basePairStart");

		if (thisChrNumber != null && thisChrNumber.trim().length()>0){
			if (thisRegion != null && thisBasePairStart != null && this.basePairEnd != null) {
				if ((thisRegion.equalsIgnoreCase("basePairPosition")) && (thisBasePairStart.trim().length() > 0) 
					&& (this.basePairEnd.trim().length()>0)){

						regionDomainMap.put(this.basePairEnd, BasePairPositionDE.EndPosition.class.getName());
					}
			}
		}
	}

	/** 
	 * Returns the chrosomeNumber.
	 * @return String
	 */
	public String getChrosomeNumber() {
		return chrosomeNumber;
	}

	/** 
	 * Set the chrosomeNumber.
	 * @param chrosomeNumber The chrosomeNumber to set
	 */
	public void setChrosomeNumber(String chrosomeNumber) {
		this.chrosomeNumber = chrosomeNumber;

		if (chrosomeNumber != null && chrosomeNumber.length() > 0) {
				regionDomainMap.put(this.chrosomeNumber, ChromosomeNumberDE.class.getName());
			}

	}

	/** 
	 * Returns the cnADDeleted.
	 * @return String
	 */
	public String getCnADDeleted() {
		return cnADDeleted;
	}

	/** 
	 * Set the cnADDeleted.
	 * @param cnADDeleted The cnADDeleted to set
	 */
	public void setCnADDeleted(String cnADDeleted) {
		this.cnADDeleted = cnADDeleted;
		// need to make sure the parameters such as copyNumberADDeleted and regulationStatus
		// match the ones declared on the copyNumber_tile.jsp
		String thisCopyNumber = this.thisRequest.getParameter("copyNumber"); 
		
		if (thisCopyNumber != null && thisCopyNumber.equalsIgnoreCase("ampdel") && (this.cnADDeleted.length() > 0)){
			copyNoDelDomainMap.put(this.cnADDeleted, CopyNumberDE.Deletion.class.getName());
			}	
	 }

	/** 
	 * Returns the cnUnchangeTo.
	 * @return String
	 */
	public String getCnUnchangeTo() {
		return cnUnchangeTo;
	}

	/** 
	 * Set the cnUnchangeTo.
	 * @param cnUnchangeTo The cnUnchangeTo to set
	 */
	public void setCnUnchangeTo(String cnUnchangeTo) {
		this.cnUnchangeTo = cnUnchangeTo;
		String thisCopyNumber = this.thisRequest.getParameter("copyNumber"); 		
		if (thisCopyNumber != null && thisCopyNumber.equalsIgnoreCase("unchange") && (this.cnUnchangeTo.length() > 0)) {
			copyNoDelDomainMap.put(this.cnUnchangeTo, CopyNumberDE.UnChangedCopyNumberUpperLimit.class.getName());
		}

	}

	/** 
	 * Returns the alleleFrequency.
	 * @return String
	 */
	public String getAlleleFrequency() {
		return alleleFrequency;
	}

	/** 
	 * Set the alleleFrequency.
	 * @param alleleFrequency The alleleFrequency to set
	 */
	public void setAlleleFrequency(String alleleFrequency) {
		this.alleleFrequency = alleleFrequency;
		alleleDomainMap.put(this.alleleFrequency, AlleleFrequencyDE.class.getName());	
	}

	/** 
	 * Returns the geneType.
	 * @return String
	 */
	public String getGeneType() {
		return geneType;
	}

	/** 
	 * Set the geneType.
	 * @param geneType The geneType to set
	 */
	public void setGeneType(String geneType) {
		this.geneType = geneType;
	}

	/** 
	 * Returns the validatedSNP.
	 * @return String
	 */
	public String getValidatedSNP() {
		return validatedSNP;
	}

	/** 
	 * Set the validatedSNP.
	 * @param validatedSNP The validatedSNP to set
	 */
	public void setValidatedSNP(String validatedSNP) {
		this.validatedSNP = validatedSNP;
	}

	/** 
	 * Returns the resultView.
	 * @return String
	 */
	public String getResultView() {
		return resultView;
	}

	/** 
	 * Set the resultView.
	 * @param resultView The resultView to set
	 */
	public void setResultView(String resultView) {
		this.resultView = resultView;
	}

	/** 
	 * Returns the geneFile.
	 * @return FormFile
	 */
	public FormFile getGeneFile() {
		return geneFile;
	}

	/** 
	 * Set the geneFile.
	 * @param geneFile The geneFile to set
	 */
	public void setGeneFile(FormFile geneFile) {
		this.geneFile = geneFile;

		String thisGeneType = this.thisRequest.getParameter("geneType");
		String thisGeneGroup = this.thisRequest.getParameter("geneGroup");
//		retrieve the file name & size
 		String fileName= geneFile.getFileName();
 		int fileSize = geneFile.getFileSize();

 		if ((thisGeneGroup != null) && thisGeneGroup.equalsIgnoreCase("Upload")
				&& (thisGeneType.length() > 0)
				&& (this.geneFile != null)
				&& (this.geneFile.getFileName().endsWith(".txt"))
				&& (this.geneFile.getContentType().equals("text/plain"))) {
			try {
				InputStream stream = geneFile.getInputStream();				
				String inputLine = null;
				BufferedReader inFile = new BufferedReader( new InputStreamReader(stream));
				
				int count = 0;
				while ((inputLine = inFile.readLine()) != null && count < NautilusConstants.MAX_FILEFORM_COUNT)  {
					if(isAscii(inputLine)){ //make sure all data is ASCII
							count++;
							if (thisGeneType.equalsIgnoreCase("genesymbol")) {
								geneDomainMap.put(inputLine,
												GeneIdentifierDE.GeneSymbol.class
														.getName());
							} else if (thisGeneType.equalsIgnoreCase("genelocus")) {
								geneDomainMap.put(inputLine,
										GeneIdentifierDE.LocusLink.class.getName());
							} else if (thisGeneType.equalsIgnoreCase("genbankno")) {
								geneDomainMap.put(
										inputLine,
												GeneIdentifierDE.GenBankAccessionNumber.class
														.getName());
							} else if (thisGeneType.equalsIgnoreCase("allgenes")) {
								geneDomainMap.put(inputLine,
												GeneIdentifierDE.GeneSymbol.class
														.getName());
							}
					}
				}// end of while

				inFile.close();
			} catch (IOException ex) {
			    logger.error("Errors when uploading gene file:"
						+ ex.getMessage());
			}

		}
	}

	/** 
	 * Returns the snpId.
	 * @return String
	 */
	public String getSnpId() {
		return snpId;
	}

	/** 
	 * Set the snpId.
	 * @param snpId The snpId to set
	 */
	public void setSnpId(String snpId) {
		this.snpId = snpId;
	}

	/** 
	 * Returns the cnDeleted.
	 * @return String
	 */
	public String getCnDeleted() {
		return cnDeleted;
	}

	/** 
	 * Set the cnDeleted.
	 * @param cnDeleted The cnDeleted to set
	 */
	public void setCnDeleted(String cnDeleted) {
		this.cnDeleted = cnDeleted;
			// need to make sure the parameters such as copyNumberDeleted and regulationStatus
		// match the ones declared on the copyNumber_tile.jsp
		String thisCopyNumber = this.thisRequest.getParameter("copyNumber"); 
		if (thisCopyNumber != null && thisCopyNumber.equalsIgnoreCase("deleted") && (this.cnDeleted.length() > 0)){
			copyNoDelDomainMap.put(this.cnDeleted, CopyNumberDE.Deletion.class.getName());
			}	
	 }

	/** 
	 * Returns the geneGroup.
	 * @return String
	 */
	public String getGeneGroup() {
		return geneGroup;
	}

	/** 
	 * Set the geneGroup.
	 * @param geneGroup The geneGroup to set
	 */
	public void setGeneGroup(String geneGroup) {
		this.geneGroup = geneGroup;
	}

	/** 
	 * Returns the cnUnchangeFrom.
	 * @return String
	 */
	public String getCnUnchangeFrom() {
		return cnUnchangeFrom;
	}

	/** 
	 * Set the cnUnchangeFrom.
	 * @param cnUnchangeFrom The cnUnchangeFrom to set
	 */
	public void setCnUnchangeFrom(String cnUnchangeFrom) {
		this.cnUnchangeFrom = cnUnchangeFrom;
		String thisCopyNumber = this.thisRequest.getParameter("copyNumber"); 		
		if (thisCopyNumber != null && thisCopyNumber.equalsIgnoreCase("unchange") && (this.cnUnchangeFrom.length() > 0)){
			copyNoAmpDomainMap.put(this.cnUnchangeFrom, CopyNumberDE.UnChangedCopyNumberDownLimit.class.getName());
			}	
	}

	/** 
	 * Returns the cloneList.
	 * @return String
	 */
	public String getCloneList() {
		return cloneList;
	}

	/** 
	 * Set the cloneList.
	 * @param cloneList The cloneList to set
	 */
	public void setCloneList(String cloneList) {
		this.cloneList = cloneList;
	}

	/** 
	 * Returns the queryName.
	 * @return String
	 */
	public String getQueryName() {
		return queryName;
	}

	/** 
	 * Set the queryName.
	 * @param queryName The queryName to set
	 */
	public void setQueryName(String queryName) {
		this.queryName = queryName;
	}

	/** 
	 * Returns the copyNumber.
	 * @return String
	 */
	public String getCopyNumber() {
		return copyNumber;
	}

	/** 
	 * Set the copyNumber.
	 * @param copyNumber The copyNumber to set
	 */
	public void setCopyNumber(String copyNumber) {
		this.copyNumber = copyNumber;
	}

	/** 
	 * Returns the basePairStart.
	 * @return String
	 */
	public String getBasePairStart() {
		return basePairStart;
	}

	/** 
	 * Set the basePairStart.
	 * @param basePairStart The basePairStart to set
	 */
	public void setBasePairStart(String basePairStart) {
		this.basePairStart = basePairStart;
		String thisRegion = this.thisRequest.getParameter("region"); 		
		String thisChrNumber = this.thisRequest.getParameter("chrosomeNumber");
		String thisBasePairEnd = this.thisRequest.getParameter("basePairEnd");

		if (thisChrNumber != null && thisChrNumber.trim().length()>0){
			if (thisRegion != null && this.basePairStart != null && thisBasePairEnd != null) {
				if ((thisRegion.equalsIgnoreCase("basePairPosition")) && (thisBasePairEnd.trim().length() > 0) 
					&& (this.basePairStart.trim().length()>0)){

						regionDomainMap.put(this.basePairStart, BasePairPositionDE.StartPosition.class.getName());
					}
			}
		}
	}

	public ArrayList getCloneTypeColl(){
	   return cloneTypeColl; 	   
	   }

   public ArrayList getSnpTypes(){
	   return snpTypes; 	   
	   }
   public ArrayList getAlleleTypes(){
	   return alleleTypes; 	   
	   }
}
