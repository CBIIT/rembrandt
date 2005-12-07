
// Generated by OJB SchemeGenerator

package gov.nih.nci.rembrandt.queryservice.queryprocessing.cgh;

import gov.nih.nci.rembrandt.queryservice.resultset.ClinicalResultSet;
import gov.nih.nci.rembrandt.queryservice.resultset.ResultSet;

import java.sql.Date;
import java.util.HashSet;

public class CopyNumber implements ResultSet, ClinicalResultSet
{
    public Long getAgaID() {
        return agaID;
    }

    public void setAgaID(Long agaID) {
        this.agaID = agaID;
    }
  private Long 	 survivalLength;
  private String 	 censor;
  private Long agaID;
  private String ageGroup;
  private Long biospecimenId;
  private Double channelRatio;
  private Double copyNumber;
  private Double copynoPval;
  private String sampleId;
  private String cytoband;
  private String diseaseType;
  private String genderCode;
  private String race;
  private Double loh;
  private String lossGain;
  private Long snpProbesetId;
  private String snpProbesetName ;
  private String survivalLengthRange;
  private Long timecourseId;
  private Long physicalPosition;
  private SNPAnnotation annotations;
  private String chromosome;
  private String timePoint;  
  private String timePoints;  

  private Date followupDate;  
  private String followupDates;   
  

  private Long followupMonth;
  private String followupMonths;

  private Date neuroEvaluationDate;   
  private String neuroEvaluationDates;
  
  private Long karnofskyScore;
  private String karnofskyScores;
  
  private Long lanskyScore;
  private String lanskyScores;
  
  private Long neuroExam;  
  private String neuroExams;  
  
  private Long mriCtScore;
  private String mriCtScores;
  
  private String steroidDoseStatus;
  private String steroidDoseStatuses;
  
  private String antiConvulsantStatus;
  private String antiConvulsantStatuses;
  
  private String priorRadiationTimePoints;
  private String priorRadiationRadiationSites;
  private String priorRadiationDoseStartDates;
  private String priorRadiationDoseStopDates;
  private String priorRadiationFractionDoses;
  private String priorRadiationFractionNumbers;
  private String priorRadiationRadiationTypes;
  
  
  private String priorChemoTimePoints;
  private String priorChemoagentIds ;
  private String priorChemoAgentNames ;							
  private String priorChemoCourseCounts ;
  private String priorChemoDoseStartDates;	
  private String priorChemoDoseStopDates ;
  private String priorChemoStudySources ;
  private String priorChemoProtocolNumbers ;
  
  private String priorSurgeryTimePoints ;
  private String priorSurgeryProcedureTitles ;
  private String priorSurgeryTumorHistologys ;							
  private String priorSurgerySurgeryDates ;
  private String priorSurgerySurgeryOutcomes ;	
  
  
  
   
    /**
 * @return Returns the antiConvulsantStatuses.
 */
public String getAntiConvulsantStatuses() {
	return antiConvulsantStatuses;
}

/**
 * @param antiConvulsantStatuses The antiConvulsantStatuses to set.
 */
public void setAntiConvulsantStatuses(String antiConvulsantStatuses) {
	this.antiConvulsantStatuses = antiConvulsantStatuses;
}

/**
 * @return Returns the followupMonths.
 */
public String getFollowupMonths() {
	return followupMonths;
}

/**
 * @param followupMonths The followupMonths to set.
 */
public void setFollowupMonths(String followupMonths) {
	this.followupMonths = followupMonths;
}

/**
 * @return Returns the karnofskyScores.
 */
public String getKarnofskyScores() {
	return karnofskyScores;
}

/**
 * @param karnofskyScores The karnofskyScores to set.
 */
public void setKarnofskyScores(String karnofskyScores) {
	this.karnofskyScores = karnofskyScores;
}

/**
 * @return Returns the lanskyScores.
 */
public String getLanskyScores() {
	return lanskyScores;
}

/**
 * @param lanskyScores The lanskyScores to set.
 */
public void setLanskyScores(String lanskyScores) {
	this.lanskyScores = lanskyScores;
}

/**
 * @return Returns the mriCtScores.
 */
public String getMriCtScores() {
	return mriCtScores;
}

/**
 * @param mriCtScores The mriCtScores to set.
 */
public void setMriCtScores(String mriCtScores) {
	this.mriCtScores = mriCtScores;
}

/**
 * @return Returns the neuroEvaluationDates.
 */
public String getNeuroEvaluationDates() {
	return neuroEvaluationDates;
}

/**
 * @param neuroEvaluationDates The neuroEvaluationDates to set.
 */
public void setNeuroEvaluationDates(String neuroEvaluationDates) {
	this.neuroEvaluationDates = neuroEvaluationDates;
}

/**
 * @return Returns the neuroExams.
 */
public String getNeuroExams() {
	return neuroExams;
}

/**
 * @param neuroExams The neuroExams to set.
 */
public void setNeuroExams(String neuroExams) {
	this.neuroExams = neuroExams;
}

/**
 * @return Returns the steroidDoseStatuses.
 */
public String getSteroidDoseStatuses() {
	return steroidDoseStatuses;
}

/**
 * @param steroidDoseStatuses The steroidDoseStatuses to set.
 */
public void setSteroidDoseStatuses(String steroidDoseStatuses) {
	this.steroidDoseStatuses = steroidDoseStatuses;
}

/**
 * @return Returns the timePoints.
 */
public String getTimePoints() {
	return timePoints;
}

/**
 * @param timePoints The timePoints to set.
 */
public void setTimePoints(String timePoints) {
	this.timePoints = timePoints;
}

	/**
 * @return Returns the followupDates.
 */
public String getFollowupDates() {
	return followupDates;
}

/**
 * @param followupDates The followupDates to set.
 */
public void setFollowupDates(String followupDates) {
	this.followupDates = followupDates;
}

	/**
 * @return Returns the antiConvulsantStatus.
 */
public String getAntiConvulsantStatus() {
	return antiConvulsantStatus;
}

/**
 * @param antiConvulsantStatus The antiConvulsantStatus to set.
 */
public void setAntiConvulsantStatus(String antiConvulsantStatus) {
	this.antiConvulsantStatus = antiConvulsantStatus;
}

/**
 * @return Returns the followupMonth.
 */
public Long getFollowupMonth() {
	return followupMonth;
}

/**
 * @param followupMonth The followupMonth to set.
 */
public void setFollowupMonth(Long followupMonth) {
	this.followupMonth = followupMonth;
}

/**
 * @return Returns the neuroEvaluationDate.
 */
public Date getNeuroEvaluationDate() {
	return neuroEvaluationDate;
}

/**
 * @param neuroEvaluationDate The neuroEvaluationDate to set.
 */
public void setNeuroEvaluationDate(Date neuroEvaluationDate) {
	this.neuroEvaluationDate = neuroEvaluationDate;
}

/**
 * @return Returns the steroidDoseStatus.
 */
public String getSteroidDoseStatus() {
	return steroidDoseStatus;
}

/**
 * @param steroidDoseStatus The steroidDoseStatus to set.
 */
public void setSteroidDoseStatus(String steroidDoseStatus) {
	this.steroidDoseStatus = steroidDoseStatus;
}

	/**
 * @return Returns the followupDate.
 */
public Date getFollowupDate() {
	return followupDate;
}

/**
 * @param followupDate The followupDate to set.
 */
public void setFollowupDate(Date followupDate) {
	this.followupDate = followupDate;
}

	/**
 * @return Returns the timePoint.
 */
public String getTimePoint() {
	return timePoint;
}

/**
 * @param timePoint The timePoint to set.
 */
public void setTimePoint(String timePoint) {
	this.timePoint = timePoint;
}

	/**
 * @return Returns the mriCtScore.
 */
public Long getMriCtScore() {
	return mriCtScore;
}

/**
 * @param mriCtScore The mriCtScore to set.
 */
public void setMriCtScore(Long mriCtScore) {
	this.mriCtScore = mriCtScore;
}

	/**
 * @return Returns the neuroExam.
 */
public Long getNeuroExam() {
	return neuroExam;
}

/**
 * @param neuroExam The neuroExam to set.
 */
public void setNeuroExam(Long neuroExam) {
	this.neuroExam = neuroExam;
}

	/**
 * @return Returns the karnofskyScore.
 */
public Long getKarnofskyScore() {
	return karnofskyScore;
}

/**
 * @param karnofskyScore The karnofskyScore to set.
 */
public void setKarnofskyScore(Long karnofskyScore) {
	this.karnofskyScore = karnofskyScore;
}

/**
 * @return Returns the lanskyScore.
 */
public Long getLanskyScore() {
	return lanskyScore;
}

/**
 * @param lanskyScore The lanskyScore to set.
 */
public void setLanskyScore(Long lanskyScore) {
	this.lanskyScore = lanskyScore;
}


/**
 * @return Returns the priorChemoagentIds.
 */
public String getPriorChemoagentIds() {
	return priorChemoagentIds;
}

/**
 * @param priorChemoagentIds The priorChemoagentIds to set.
 */
public void setPriorChemoagentIds(String priorChemoagentIds) {
	this.priorChemoagentIds = priorChemoagentIds;
}

/**
 * @return Returns the priorChemoAgentNames.
 */
public String getPriorChemoAgentNames() {
	return priorChemoAgentNames;
}

/**
 * @param priorChemoAgentNames The priorChemoAgentNames to set.
 */
public void setPriorChemoAgentNames(String priorChemoAgentNames) {
	this.priorChemoAgentNames = priorChemoAgentNames;
}

/**
 * @return Returns the priorChemoCourseCounts.
 */
public String getPriorChemoCourseCounts() {
	return priorChemoCourseCounts;
}

/**
 * @param priorChemoCourseCounts The priorChemoCourseCounts to set.
 */
public void setPriorChemoCourseCounts(String priorChemoCourseCounts) {
	this.priorChemoCourseCounts = priorChemoCourseCounts;
}

/**
 * @return Returns the priorChemoDoseStartDates.
 */
public String getPriorChemoDoseStartDates() {
	return priorChemoDoseStartDates;
}

/**
 * @param priorChemoDoseStartDates The priorChemoDoseStartDates to set.
 */
public void setPriorChemoDoseStartDates(String priorChemoDoseStartDates) {
	this.priorChemoDoseStartDates = priorChemoDoseStartDates;
}

/**
 * @return Returns the priorChemoDoseStopDates.
 */
public String getPriorChemoDoseStopDates() {
	return priorChemoDoseStopDates;
}

/**
 * @param priorChemoDoseStopDates The priorChemoDoseStopDates to set.
 */
public void setPriorChemoDoseStopDates(String priorChemoDoseStopDates) {
	this.priorChemoDoseStopDates = priorChemoDoseStopDates;
}

/**
 * @return Returns the priorChemoProtocolNumbers.
 */
public String getPriorChemoProtocolNumbers() {
	return priorChemoProtocolNumbers;
}

/**
 * @param priorChemoProtocolNumbers The priorChemoProtocolNumbers to set.
 */
public void setPriorChemoProtocolNumbers(String priorChemoProtocolNumbers) {
	this.priorChemoProtocolNumbers = priorChemoProtocolNumbers;
}

/**
 * @return Returns the priorChemoStudySources.
 */
public String getPriorChemoStudySources() {
	return priorChemoStudySources;
}

/**
 * @param priorChemoStudySources The priorChemoStudySources to set.
 */
public void setPriorChemoStudySources(String priorChemoStudySources) {
	this.priorChemoStudySources = priorChemoStudySources;
}

/**
 * @return Returns the priorChemoTimePoints.
 */
public String getPriorChemoTimePoints() {
	return priorChemoTimePoints;
}

/**
 * @param priorChemoTimePoints The priorChemoTimePoints to set.
 */
public void setPriorChemoTimePoints(String priorChemoTimePoints) {
	this.priorChemoTimePoints = priorChemoTimePoints;
}

/**
 * @return Returns the priorRadiationDoseStartDates.
 */
public String getPriorRadiationDoseStartDates() {
	return priorRadiationDoseStartDates;
}

/**
 * @param priorRadiationDoseStartDates The priorRadiationDoseStartDates to set.
 */
public void setPriorRadiationDoseStartDates(String priorRadiationDoseStartDates) {
	this.priorRadiationDoseStartDates = priorRadiationDoseStartDates;
}

/**
 * @return Returns the priorRadiationDoseStopDates.
 */
public String getPriorRadiationDoseStopDates() {
	return priorRadiationDoseStopDates;
}

/**
 * @param priorRadiationDoseStopDates The priorRadiationDoseStopDates to set.
 */
public void setPriorRadiationDoseStopDates(String priorRadiationDoseStopDates) {
	this.priorRadiationDoseStopDates = priorRadiationDoseStopDates;
}

/**
 * @return Returns the priorRadiationFractionDoses.
 */
public String getPriorRadiationFractionDoses() {
	return priorRadiationFractionDoses;
}

/**
 * @param priorRadiationFractionDoses The priorRadiationFractionDoses to set.
 */
public void setPriorRadiationFractionDoses(String priorRadiationFractionDoses) {
	this.priorRadiationFractionDoses = priorRadiationFractionDoses;
}

/**
 * @return Returns the priorRadiationFractionNumbers.
 */
public String getPriorRadiationFractionNumbers() {
	return priorRadiationFractionNumbers;
}

/**
 * @param priorRadiationFractionNumbers The priorRadiationFractionNumbers to set.
 */
public void setPriorRadiationFractionNumbers(
		String priorRadiationFractionNumbers) {
	this.priorRadiationFractionNumbers = priorRadiationFractionNumbers;
}

/**
 * @return Returns the priorRadiationRadiationSites.
 */
public String getPriorRadiationRadiationSites() {
	return priorRadiationRadiationSites;
}

/**
 * @param priorRadiationRadiationSites The priorRadiationRadiationSites to set.
 */
public void setPriorRadiationRadiationSites(String priorRadiationRadiationSites) {
	this.priorRadiationRadiationSites = priorRadiationRadiationSites;
}

/**
 * @return Returns the priorRadiationRadiationTypes.
 */
public String getPriorRadiationRadiationTypes() {
	return priorRadiationRadiationTypes;
}

/**
 * @param priorRadiationRadiationTypes The priorRadiationRadiationTypes to set.
 */
public void setPriorRadiationRadiationTypes(String priorRadiationRadiationTypes) {
	this.priorRadiationRadiationTypes = priorRadiationRadiationTypes;
}

/**
 * @return Returns the priorRadiationTimePoints.
 */
public String getPriorRadiationTimePoints() {
	return priorRadiationTimePoints;
}

/**
 * @param priorRadiationTimePoints The priorRadiationTimePoints to set.
 */
public void setPriorRadiationTimePoints(String priorRadiationTimePoints) {
	this.priorRadiationTimePoints = priorRadiationTimePoints;
}

/**
 * @return Returns the priorSurgeryProcedureTitles.
 */
public String getPriorSurgeryProcedureTitles() {
	return priorSurgeryProcedureTitles;
}

/**
 * @param priorSurgeryProcedureTitles The priorSurgeryProcedureTitles to set.
 */
public void setPriorSurgeryProcedureTitles(String priorSurgeryProcedureTitles) {
	this.priorSurgeryProcedureTitles = priorSurgeryProcedureTitles;
}

/**
 * @return Returns the priorSurgerySurgeryDates.
 */
public String getPriorSurgerySurgeryDates() {
	return priorSurgerySurgeryDates;
}

/**
 * @param priorSurgerySurgeryDates The priorSurgerySurgeryDates to set.
 */
public void setPriorSurgerySurgeryDates(String priorSurgerySurgeryDates) {
	this.priorSurgerySurgeryDates = priorSurgerySurgeryDates;
}

/**
 * @return Returns the priorSurgerySurgeryOutcomes.
 */
public String getPriorSurgerySurgeryOutcomes() {
	return priorSurgerySurgeryOutcomes;
}

/**
 * @param priorSurgerySurgeryOutcomes The priorSurgerySurgeryOutcomes to set.
 */
public void setPriorSurgerySurgeryOutcomes(String priorSurgerySurgeryOutcomes) {
	this.priorSurgerySurgeryOutcomes = priorSurgerySurgeryOutcomes;
}

/**
 * @return Returns the priorSurgeryTimePoints.
 */
public String getPriorSurgeryTimePoints() {
	return priorSurgeryTimePoints;
}

/**
 * @param priorSurgeryTimePoints The priorSurgeryTimePoints to set.
 */
public void setPriorSurgeryTimePoints(String priorSurgeryTimePoints) {
	this.priorSurgeryTimePoints = priorSurgeryTimePoints;
}

/**
 * @return Returns the priorSurgeryTumorHistologys.
 */
public String getPriorSurgeryTumorHistologys() {
	return priorSurgeryTumorHistologys;
}

/**
 * @param priorSurgeryTumorHistologys The priorSurgeryTumorHistologys to set.
 */
public void setPriorSurgeryTumorHistologys(String priorSurgeryTumorHistologys) {
	this.priorSurgeryTumorHistologys = priorSurgeryTumorHistologys;
}

	/**
 * @return Returns the race.
 */
public String getRace() {
	return race;
}

/**
 * @param race The race to set.
 */
public void setRace(String race) {
	this.race = race;
}

	public String getChromosome() {
        return chromosome;
    }

    public void setChromosome(String chromosome) {
        this.chromosome = chromosome;
    }

    public static class SNPAnnotation {
         public Long snpProbesetID;
         public HashSet geneSymbols;
         public HashSet locusLinkIDs;
         public HashSet accessionNumbers;

         public SNPAnnotation(Long snpProbesetID, HashSet geneSymbols, HashSet locusLinkIDs, HashSet accessionNumbers) {
             this.snpProbesetID = snpProbesetID;
             this.geneSymbols = geneSymbols;
             this.locusLinkIDs = locusLinkIDs;
             this.accessionNumbers = accessionNumbers;
         }
         public Long getSnpProbesetID() {
             return snpProbesetID;
         }
         public void setSnpProbesetID(Long snpProbesetID) {
             this.snpProbesetID = snpProbesetID;
         }
         public HashSet getGeneSymbols() {
             return geneSymbols;
         }
         public HashSet getLocusLinkIDs() {
             return locusLinkIDs;
         }

         public HashSet getAccessionNumbers() {
             return accessionNumbers;
         }
     }

    public SNPAnnotation   getAnnotations() {
        return annotations;
    }

    public void setAnnotations(SNPAnnotation  annotations) {
        this.annotations = annotations;
    }
    public String getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(String ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Long getBiospecimenId() {
        return biospecimenId;
    }

    public void setBiospecimenId(Long biospecimenId) {
        this.biospecimenId = biospecimenId;
    }

    public Double getChannelRatio() {
        return channelRatio;
    }

    public void setChannelRatio(Double channelRatio) {
        this.channelRatio = channelRatio;
    }

    public Double getCopyNumber() {
        return copyNumber;
    }

    public void setCopyNumber(Double copyNumber) {
        this.copyNumber = copyNumber;
    }

    public Double getCopynoPval() {
        return copynoPval;
    }

    public void setCopynoPval(Double copynoPval) {
        this.copynoPval = copynoPval;
    }

    public String getSampleId() {
        return sampleId;
    }

    public void setSampleId(String sampleId) {
        this.sampleId = sampleId;
    }

    public String getCytoband() {
        return cytoband;
    }

    public void setCytoband(String cytoband) {
        this.cytoband = cytoband;
    }

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    public String getGenderCode() {
        return genderCode;
    }

    public void setGenderCode(String genderCode) {
        this.genderCode = genderCode;
    }

    public Double getLoh() {
        return loh;
    }

    public void setLoh(Double loh) {
        this.loh = loh;
    }

    public String getLossGain() {
        return lossGain;
    }

    public void setLossGain(String lossGain) {
        this.lossGain = lossGain;
    }

    public Long getSnpProbesetId() {
        return snpProbesetId;
    }

    public void setSnpProbesetId(Long snpProbesetId) {
        this.snpProbesetId = snpProbesetId;
    }

    public String getSnpProbesetName() {
        return snpProbesetName;
    }

    public void setSnpProbesetName(String snpProbesetName) {
        this.snpProbesetName = snpProbesetName;
    }

    public String getSurvivalLengthRange() {
        return survivalLengthRange;
    }

    public void setSurvivalLengthRange(String survivalLengthRange) {
        this.survivalLengthRange = survivalLengthRange;
    }

    public Long getTimecourseId() {
        return timecourseId;
    }

    public void setTimecourseId(Long timecourseId) {
        this.timecourseId = timecourseId;
    }

    public Long getPhysicalPosition() {
        return physicalPosition;
    }

    public void setPhysicalPosition(Long physicalPosition) {
        this.physicalPosition = physicalPosition;
    }
	/**
	 * @return Returns the survivalLength.
	 */
	public Long getSurvivalLength() {
		return survivalLength;
	}

	/**
	 * @param survivalLength The survivalLength to set.
	 */
	public void setSurvivalLength(Long survivalLength) {
		this.survivalLength = survivalLength;
	}

	public String getCensoringStatus() {
		return censor;
	}

	public void setCensoringStatus(String censor) {
		this.censor = censor;
		
	}
}

