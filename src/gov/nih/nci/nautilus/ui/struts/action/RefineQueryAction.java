package gov.nih.nci.nautilus.ui.struts.action;


import gov.nih.nci.nautilus.constants.NautilusConstants;
import gov.nih.nci.nautilus.parser.Parser;
import gov.nih.nci.nautilus.query.CompoundQuery;
import gov.nih.nci.nautilus.query.Queriable;
import gov.nih.nci.nautilus.query.Query;
import gov.nih.nci.nautilus.query.QueryCollection;
import gov.nih.nci.nautilus.queryprocessing.ge.GeneExpr;
import gov.nih.nci.nautilus.resultset.ResultSet;
import gov.nih.nci.nautilus.ui.struts.form.RefineQueryForm;
import gov.nih.nci.nautilus.util.ApplicationContext;
import gov.nih.nci.nautilus.view.ViewFactory;
import gov.nih.nci.nautilus.view.ViewType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.LookupDispatchAction;
import org.apache.struts.util.LabelValueBean;


public class RefineQueryAction extends LookupDispatchAction {
    private static Logger logger = Logger.getLogger(RefineQueryAction.class);
	
    /** 
	 * Method execute
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward validateQuery(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		
		RefineQueryForm refineQueryForm = (RefineQueryForm) form;
		String leftParen = "";
		String queryName1 = "";
		String queryName2 = "";
		String queryName3 = "";
		Query query = null;
		String rightParen = "";
		String operatorType = "";
		Vector vectorOfTokens = new Vector();
		ActionErrors errors = new ActionErrors();
		

		QueryCollection queryCollect = (QueryCollection) request.getSession().getAttribute(NautilusConstants.QUERY_KEY);

		if (queryCollect != null){
			//Create a vector of search token values from FormBean
		
			// Query 1
			queryName1 = refineQueryForm.getQueryName1();
			queryName2 = refineQueryForm.getQueryName2();
			queryName3 = refineQueryForm.getQueryName3();
			
			// User input a single query 
			if ((queryName1.trim().length() >= 1) && (queryName2.trim().length() < 1) & (queryName3.trim().length() < 1)){
				CompoundQuery compoundQuery = new CompoundQuery(queryCollect.getQuery(queryName1));
				refineQueryForm.setQueryText(queryName1);
				
				refineQueryForm.setRunFlag("yes");
				logger.debug("set query text");
				
				//Stuff compoundquery in queryCollection 
				queryCollect.setCompoundQuery(compoundQuery);
				
				// Get collection of view types
				Collection viewCollection = setRefineQueryView(compoundQuery, request);
				// Set collection of view types
				refineQueryForm.setCompoundViewColl((ArrayList) viewCollection);
				// Set Resultset name for Single Query
				refineQueryForm.setResultsetName(queryName1);		
				return mapping.findForward("displayQuery");
			}

			if (queryName1 != null) {
				leftParen = refineQueryForm.getLeftParen1();
				query = queryCollect.getQuery(queryName1);
				rightParen = refineQueryForm.getRightParen1();
				operatorType = refineQueryForm.getOperatorType1();
				
				if (leftParen != null && leftParen.length() > 0) vectorOfTokens.add(leftParen);
				if (query != null) vectorOfTokens.add(query);
				if (rightParen != null && rightParen.length() > 0) vectorOfTokens.add(rightParen);
				if (operatorType != null && operatorType.length() > 0) vectorOfTokens.add(operatorType);
			
			}

//	Query 2
			if (queryName2 != null) {
				 leftParen = refineQueryForm.getLeftParen2();
				 query = queryCollect.getQuery(queryName2);
				 rightParen = refineQueryForm.getRightParen2();
				 operatorType = refineQueryForm.getOperatorType2();
				
				if (leftParen != null && leftParen.length() > 0) vectorOfTokens.add(leftParen);
				if (query != null) vectorOfTokens.add(query);
				if (rightParen != null && rightParen.length() > 0) vectorOfTokens.add(rightParen);
				if (operatorType != null && operatorType.length() > 0) vectorOfTokens.add(operatorType);
			
			}
//	Query 3
			if (queryName3 != null) {
				 leftParen = refineQueryForm.getLeftParen3();
				 query = queryCollect.getQuery(queryName3);
				 rightParen = refineQueryForm.getRightParen3();
				
				if (leftParen != null && leftParen.length() > 0) vectorOfTokens.add(leftParen);
				if (query != null) vectorOfTokens.add(query);
				if (rightParen != null && rightParen.length() > 0) vectorOfTokens.add(rightParen);
			
			}

			try {
				Parser queryParser = new Parser(vectorOfTokens);
				queryParser.expression();
				Queriable compoundQuery = queryParser.getCompoundQuery();
				//Display validated query on the screen 
				refineQueryForm.setQueryText(compoundQuery.toString());
				
				refineQueryForm.setRunFlag("yes");
				logger.debug("set query text");
				
				// Get collection of view types
				Collection viewCollection = setRefineQueryView((CompoundQuery) compoundQuery, request);
				// Set collection of view types in Form
				refineQueryForm.setCompoundViewColl((ArrayList) viewCollection);

				//Stuff compoundquery in queryCollection 
				queryCollect.setCompoundQuery((CompoundQuery) compoundQuery);
			
 
	
			}catch (Exception e){
				refineQueryForm.setQueryText("Error!! "+e.getMessage());
				logger.debug("Error Parsing Query and/or creating Compound Query " + e.getMessage());
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("gov.nih.nci.nautilus.ui.struts.action.refinequery.parse.error",e.getMessage()));

				this.saveErrors(request, errors);
				return (new ActionForward(mapping.getInput()));
			}

		}else{
			// Query Collection is null.  Go back and display error
			refineQueryForm.setQueryText("Error!! Query Collection is null");
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("gov.nih.nci.nautilus.ui.struts.action.refinequery.querycoll.empty.error"));
			this.saveErrors(request, errors);
			return (new ActionForward(mapping.getInput()));
			
		}
		return mapping.findForward("displayQuery");
		
     }
     

	private Collection setRefineQueryView(CompoundQuery cQuery, HttpServletRequest request) {

		// Get the Query Collection from the session
		ArrayList queryViewColl = new ArrayList();

		Properties props = new Properties();
		props = ApplicationContext.getLabelProperties();

		
		if (cQuery != null && props != null) {
			
			ViewType [] availableViewTypes = cQuery.getValidViews();
			//Set the View Types array in request to be used on return trip
			request.getSession().setAttribute(NautilusConstants.VALID_QUERY_TYPES_KEY, availableViewTypes);

			
			for (int viewIndex = 0; viewIndex < availableViewTypes.length; viewIndex++) {
				ViewType thisViewType = (ViewType) availableViewTypes[viewIndex];
				String viewText = (String) props.get(thisViewType.getClass().getName());

				queryViewColl.add( new LabelValueBean( viewText, Integer.toString(viewIndex)) );
			}
		
		}else {
		
			queryViewColl.add( new LabelValueBean( " ", " " ));
			logger.debug("Compound Query passed is null");
		}
		return queryViewColl;
	}

  
	public ActionForward displayResult(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		ActionErrors errors = new ActionErrors();
		RefineQueryForm refineQueryForm = (RefineQueryForm) form;


		QueryCollection queryCollect = (QueryCollection) request.getSession().getAttribute(NautilusConstants.QUERY_KEY);
		// Get the viewType array from session 
		ViewType [] availableViewTypes = (ViewType []) request.getSession().getAttribute(NautilusConstants.VALID_QUERY_TYPES_KEY);
// 		Set ViewType array in session to null, we dont need it anymore
//		request.getSession().setAttribute(Constants.VALID_QUERY_TYPES_KEY, null);
		
		if (queryCollect != null) {
			if (queryCollect.hasCompoundQuery()) {
				CompoundQuery cQuery = (CompoundQuery) queryCollect.getCompoundQuery();
				logger.debug(refineQueryForm.getCompoundView());
				ViewType selectView = availableViewTypes[Integer.parseInt(refineQueryForm.getCompoundView())];
				logger.debug(selectView);
				// Set View in compoundQuery
				cQuery.setAssociatedView(ViewFactory.newView(selectView));

				// Execute the query and place the query in session
//				ResultSet[] queryResultSetObjects = QueryManager.executeQuery(queryCollect.getCompoundQuery());
//				print(queryResultSetObjects);
//				request.getSession().setAttribute(Constants.RESULTSET_KEY,queryResultSetObjects);
				
			}else {
				logger.debug("QueryCollection has no Compound queries to execute.  Please select a query to execute");
				errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("gov.nih.nci.nautilus.ui.struts.action.executequery.querycoll.no.error"));
				this.saveErrors(request, errors);
				ActionForward thisForward = mapping.findForward("failure");
			}
		}else{	
			logger.debug("QueryCollection object missing in session!!");
			errors.add(ActionErrors.GLOBAL_ERROR, new ActionError("gov.nih.nci.nautilus.ui.struts.action.refinequery.querycoll.missing.error"));
			this.saveErrors(request, errors);
			ActionForward thisForward = mapping.findForward("failure");
		}
//Send to the appropriate view as per selection!!
		ActionForward thisForward = mapping.findForward("success");
		
		return thisForward;

	 }
  
	private void print(ResultSet[] geneExprObjects) {
		if(geneExprObjects != null){
			logger.debug("Number of Records:"+ geneExprObjects.length);
			for (int i =0; i < geneExprObjects.length; i++) {
				GeneExpr.GeneExprSingle expObj = (GeneExpr.GeneExprSingle) geneExprObjects[i];
				if(expObj != null){
				logger.debug( "uID: " + expObj.getDesId() + "|geneSymbol: " + expObj.getGeneSymbol() +"|clone: " + expObj.getCloneName()+"|probeSet: "+expObj.getProbesetName()+"|biospecimenID: " + expObj.getBiospecimenId() );
				}
			}
		}
	}


	/**
     * Creates and returns the key-value map for methods called based on which 
     * button is used to submit the form.
     * @return key-method pairs for the RefineQueryAction
	 */
	protected Map getKeyMethodMap() {
		
        HashMap map = new HashMap();
        //Validate Query Button using validate method
        map.put("RefineQueryAction.validateButton", "validateQuery");
        //Run Report Button using displayResult method
        map.put("RefineQueryAction.runReportButton", "displayResult");
       
       	return map;
	}		
     
}
