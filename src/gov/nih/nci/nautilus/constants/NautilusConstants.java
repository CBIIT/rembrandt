package gov.nih.nci.nautilus.constants;

public final class NautilusConstants {

    //Specifies the location in the webapp classes directory
    //to find ApplicationResources.properties
    public static final String  APPLICATION_RESOURCES = "ApplicationResources";
    //Specifies where in the webapps classes directory to find
 	//log4j.properties
 	public static final String LOGGING_PROPERTIES = "log4j.properties";

    public static final String CACHE_PROPERTIES = "rembrandtCache";

	public static final String SESSION_QUERY_BAG_KEY = "nautilus.queryMap";

	public static final String RESULTSET_KEY = "geneViewResultSet";

	public static final String VALID_QUERY_TYPES_KEY = "nautilus.request.validQuerytypes";

	public static final String NORMAL = "NON_TUMOR";

	public static final String ASTRO = "ASTROCYTOMA";

	public static final String LOGGER = "gov.nih.nci.nautilus";

	public static final String JSP_LOGGER = "gov.nih.nci.nautilus.jsp";

	public static final int MAX_FILEFORM_COUNT = 40000;
	
	public static final String REPORT_BEAN = "reportBean";
	
	public static final String REPORT_XML = "reportXML";
	
	public static final String TEMP_RESULTS = "Rembrandt_results ";
	
	public static final String DEFAULT_XSLT_FILENAME ="report.xsl";
	
	public static final String XSLT_FILE_NAME ="xsltFileName";
	
	public static final String FILTER_PARAM_MAP ="filterParamMap";
	
	public static final String PREVIEW_RESULTS = "previewResults";
	
	public static final String REPORT_COUNTER = "reportCounter";
	
    public static final String GRAPH_DEFAULT = "Default";
    
    public static final String GENE_EXP_KMPLOT ="GE_KM_PLOT";
    
    public static final String COPY_NUMBER_KMPLOT = "COPY_NUM_KM_PLOT";
    
    public static final String GENE_SYMBOL = "Gene Symbol";
    
    public static final String CYTOBAND = "Cytoband";
    
    public static final String SNP_PROBESET_ID = "SNP Probe set ID";
}
