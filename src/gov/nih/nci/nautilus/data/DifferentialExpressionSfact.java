
// Generated by OJB SchemeGenerator

package gov.nih.nci.nautilus.data;

import gov.nih.nci.nautilus.util.HashCodeUtil;

public class DifferentialExpressionSfact
{
    public final static String DES_ID = "desId";
    public final static String CLONE_ID = "cloneId";
    public final static String PROBESET_ID= "probesetId";
    public final static String CLONE_NAME = "cloneName";
    public final static String PROBE_NAME = "probesetName";
    public final static String GENE_SYMBOL = "geneSymbol";
    public final static String BIOSPECIMEN_ID = "biospecimenId";
    public final static String EXPRESSION_RATIO = "expressionRatio";

    public String getCytoband() {
        return cytoband;
    }

    public void setCytoband(String cytoband) {
        this.cytoband = cytoband;
    }

    private String cytoband;




  private Long agentId;

  private String ageGroup;

  private Long biospecimenId;

  private Long cloneId;

  private Long datasetId;

  private Long desId;

  private Long diseaseHistoryId;

  private Long diseaseTypeId;

    public String getDiseaseType() {
        return diseaseType;
    }

    public void setDiseaseType(String diseaseType) {
        this.diseaseType = diseaseType;
    }

    private String diseaseType;

  private Double expressionRatio;

  private Long expPlatformId;
  private String geneSymbol;
  private String genderCode;
  private String probesetName;
  private String cloneName;

    public String getCloneName() {
        return cloneName;
    }



    public String getProbesetName() {
        return probesetName;
    }



    public String getGeneSymbol() {
        return geneSymbol;
    }

    public void setGeneSymbol(String geneSymbol) {
        this.geneSymbol = geneSymbol;
    }

  private Long institutionId;

  private Double normalIntensity;

  private Long probesetId;

  private Double sampleIntensity;

  private String survivalLengthRange;

  private Long timecourseId;

  private Long treatmentHistoryId;


  public Long getAgentId()
  {
     return this.agentId;
  }
  public void setAgentId(Long param)
  {
    this.agentId = param;
  }


  public String getAgeGroup()
  {
     return this.ageGroup;
  }
  public void setAgeGroup(String param)
  {
    this.ageGroup = param;
  }


  public Long getBiospecimenId()
  {
     return this.biospecimenId;
  }
  public void setBiospecimenId(Long param)
  {
    this.biospecimenId = param;
  }


  public Long getCloneId()
  {
     return this.cloneId;
  }
  public void setCloneId(Long param)
  {
    this.cloneId = param;
  }


  public Long getDatasetId()
  {
     return this.datasetId;
  }
  public void setDatasetId(Long param)
  {
    this.datasetId = param;
  }


  public Long getDesId()
  {
     return this.desId;
  }
  public void setDesId(Long param)
  {
    this.desId = param;
  }


  public Long getDiseaseHistoryId()
  {
     return this.diseaseHistoryId;
  }
  public void setDiseaseHistoryId(Long param)
  {
    this.diseaseHistoryId = param;
  }


  public Long getDiseaseTypeId()
  {
     return this.diseaseTypeId;
  }
  public void setDiseaseTypeId(Long param)
  {
    this.diseaseTypeId = param;
  }


  public Double getExpressionRatio()
  {
     return this.expressionRatio;
  }
  public void setExpressionRatio(Double param)
  {
    this.expressionRatio = param;
  }


  public Long getExpPlatformId()
  {
     return this.expPlatformId;
  }
  public void setExpPlatformId(Long param)
  {
    this.expPlatformId = param;
  }


  public String getGenderCode()
  {
     return this.genderCode;
  }
  public void setGenderCode(String param)
  {
    this.genderCode = param;
  }

  public Long getInstitutionId()
  {
     return this.institutionId;
  }
  public void setInstitutionId(Long param)
  {
    this.institutionId = param;
  }


  public Double getNormalIntensity()
  {
     return this.normalIntensity;
  }
  public void setNormalIntensity(Double param)
  {
    this.normalIntensity = param;
  }


  public Long getProbesetId()
  {
     return this.probesetId;
  }
  public void setProbesetId(Long param)
  {
    this.probesetId = param;
  }


  public Double getSampleIntensity()
  {
     return this.sampleIntensity;
  }
  public void setSampleIntensity(Double param)
  {
    this.sampleIntensity = param;
  }


  public String getSurvivalLengthRange()
  {
     return this.survivalLengthRange;
  }
  public void setSurvivalLengthRange(String param)
  {
    this.survivalLengthRange = param;
  }


  public Long getTimecourseId()
  {
     return this.timecourseId;
  }
  public void setTimecourseId(Long param)
  {
    this.timecourseId = param;
  }


  public Long getTreatmentHistoryId()
  {
     return this.treatmentHistoryId;
  }
  public void setTreatmentHistoryId(Long param)
  {
    this.treatmentHistoryId = param;
  }



  public int hashCode() {
        int result = HashCodeUtil.SEED;
        result = HashCodeUtil.hash( result, cloneId);
        result = HashCodeUtil.hash( result, probesetId);

        //TODO: complete this
        return result;
    }
}

