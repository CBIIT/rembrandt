package gov.nih.nci.nautilus.query;

import gov.nih.nci.nautilus.queryprocessing.CompoundQueryProcessor;
import gov.nih.nci.nautilus.queryprocessing.QueryProcessor;
import gov.nih.nci.nautilus.resultset.CompoundResultSet;
import gov.nih.nci.nautilus.resultset.ResultSet;

/**
 * @author BhattarR
 */
public class QueryManager {

    public static Query createQuery(QueryType typeOfQuery) {
        return QueryFactory.newQuery(typeOfQuery);
    }
    public static ResultSet[] executeQuery(Queriable queryToExecute) throws Exception {
    	ResultSet[] resultset = null;
    	if(queryToExecute instanceof Query){
    		resultset = QueryProcessor.execute((Query) queryToExecute);
    	}
    	return resultset;
    }
    public static CompoundResultSet executeCompoundQuery(Queriable queryToExecute) throws Exception {
    	CompoundResultSet compoundResultset = null;
    	if (queryToExecute instanceof CompoundQuery){
    		compoundResultset = CompoundQueryProcessor.execute((CompoundQuery) queryToExecute);
    	}
    	
        return compoundResultset;
    }

}
