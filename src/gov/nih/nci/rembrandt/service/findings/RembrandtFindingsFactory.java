/**
 * 
 */
package gov.nih.nci.rembrandt.service.findings;

import gov.nih.nci.caintegrator.dto.query.ClassComparisonQueryDTO;
import gov.nih.nci.caintegrator.dto.query.HierarchicalClusteringQueryDTO;
import gov.nih.nci.caintegrator.dto.query.PrincipalComponentAnalysisQueryDTO;
import gov.nih.nci.caintegrator.dto.query.QueryDTO;
import gov.nih.nci.caintegrator.enumeration.FindingStatus;
import gov.nih.nci.caintegrator.exceptions.FindingsAnalysisException;
import gov.nih.nci.caintegrator.exceptions.FindingsQueryException;
import gov.nih.nci.caintegrator.exceptions.FrameworkException;
import gov.nih.nci.caintegrator.exceptions.ValidationException;
import gov.nih.nci.caintegrator.service.findings.ClassComparisonFinding;
import gov.nih.nci.caintegrator.service.findings.ClinicalFinding;
import gov.nih.nci.caintegrator.service.findings.CopyNumberFinding;
import gov.nih.nci.caintegrator.service.findings.Finding;
import gov.nih.nci.caintegrator.service.findings.FindingsFactory;
import gov.nih.nci.caintegrator.service.findings.GEIntensityFinding;
import gov.nih.nci.caintegrator.service.findings.HCAFinding;
import gov.nih.nci.caintegrator.service.findings.KMFinding;
import gov.nih.nci.caintegrator.service.findings.PrincipalComponentAnalysisFinding;
import gov.nih.nci.rembrandt.cache.BusinessTierCache;
import gov.nih.nci.rembrandt.service.findings.strategies.ClassComparisonFindingStrategy;
import gov.nih.nci.rembrandt.service.findings.strategies.HierarchicalClusteringFindingStrategy;
import gov.nih.nci.rembrandt.service.findings.strategies.PrincipalComponentAnalysisFindingStrategy;
import gov.nih.nci.rembrandt.web.factory.ApplicationFactory;

import org.apache.log4j.Logger;

/**
 * @author sahnih
 *
 */
public class RembrandtFindingsFactory implements FindingsFactory {
	private static Logger logger = Logger.getLogger(RembrandtFindingsFactory.class);
	private BusinessTierCache cacheManager = ApplicationFactory.getBusinessTierCache();


	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.service.findings.FindingsFactory#createClassComparisonFinding(gov.nih.nci.caintegrator.dto.query.QueryDTOold)
	 */
	public ClassComparisonFinding createClassComparisonFinding(ClassComparisonQueryDTO queryDTO, String sessionID, String taskID) throws FrameworkException  {
		ClassComparisonFinding finding = null;
		try {
			ClassComparisonFindingStrategy strategy = new  ClassComparisonFindingStrategy(sessionID,queryDTO.getQueryName(),queryDTO );
			strategy.createQuery();
			strategy.executeQuery();
			strategy.analyzeResultSet();
			finding = (ClassComparisonFinding)strategy.getFinding();

		} catch (ValidationException e) {
			logger.error(e);
			changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
			throw(e);
		} catch (FindingsQueryException e) {
			logger.error(e);
			changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
			throw(e);
		} catch (FindingsAnalysisException e) {
			logger.error(e);
			changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
			throw(e);
		}
		return finding;
	}

	/* (non-Javadoc)
	 * @see gov.nih.nci.caintegrator.service.findings.FindingsFactory#createPCAFinding(gov.nih.nci.caintegrator.dto.query.QueryDTOold)
	 */
	public PrincipalComponentAnalysisFinding createPCAFinding(PrincipalComponentAnalysisQueryDTO queryDTO, String sessionID, String taskID) throws FrameworkException {
	PrincipalComponentAnalysisFinding finding = null;
		try {
			PrincipalComponentAnalysisFindingStrategy strategy = new  PrincipalComponentAnalysisFindingStrategy(sessionID,queryDTO.getQueryName(),queryDTO );
			strategy.createQuery();
			strategy.executeQuery();
			strategy.analyzeResultSet();
			finding = (PrincipalComponentAnalysisFinding)strategy.getFinding();

		} catch (ValidationException e) {
			logger.error(e);
			changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
			throw(e);
		} catch (FindingsQueryException e) {
			logger.error(e);
			changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
			throw(e);
		} catch (FindingsAnalysisException e) {
			logger.error(e);
			changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
			throw(e);
		}
		return finding;
	}


	public KMFinding createKMFinding(QueryDTO query) {
		// TODO Auto-generated method stub
		return null;
	}

	public CopyNumberFinding createCopyNumberFinding(QueryDTO query) {
		// TODO Auto-generated method stub
		return null;
	}

	public ClinicalFinding createClinicalFinding(QueryDTO query) {
		// TODO Auto-generated method stub
		return null;
	}

	public HCAFinding createHCAFinding(HierarchicalClusteringQueryDTO queryDTO,String sessionID, String taskID) throws FrameworkException {
        HCAFinding finding = null;
        try {
            HierarchicalClusteringFindingStrategy strategy = new  HierarchicalClusteringFindingStrategy(sessionID,queryDTO.getQueryName(),queryDTO );
            strategy.createQuery();
            strategy.executeQuery();
            strategy.analyzeResultSet();
            finding = (HCAFinding)strategy.getFinding();

        } catch (ValidationException e) {
            logger.error(e);
            changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
            throw(e);
        } catch (FindingsQueryException e) {
            logger.error(e);
            changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
            throw(e);
        } catch (FindingsAnalysisException e) {
            logger.error(e);
            changeStatusToError(sessionID,queryDTO.getQueryName(),e.getMessage());
            throw(e);
        }
        return finding;
	}

	public GEIntensityFinding createGEIntensityFinding(QueryDTO query) {
		// TODO Auto-generated method stub
		return null;
	}

	public Object createCustomFinding(QueryDTO query) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * Used internally to handle execeptions by changing the status of the Findings object  
	 * 
	 * 
	 * @param sessionID
	 * @param taskID
	 * @param comment
	 * 
	 */
	private void changeStatusToError(String sessionID, String taskID, String comment){
		Finding finding = cacheManager.getSessionFinding(sessionID, taskID);
		if(finding != null){
			FindingStatus newStatus = FindingStatus.Error;
			newStatus.setComment(comment);
			finding.setStatus(newStatus) ;
			
		}
		cacheManager.addToSessionCache(sessionID, taskID, finding);

	}
}
