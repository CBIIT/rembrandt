// Created by Xslt generator for Eclipse.
// XSL :  not found (java.io.FileNotFoundException:  (Bad file descriptor))
// Default XSL used : easystruts.jar$org.easystruts.xslgen.JavaClass.xsl

package gov.nih.nci.nautilus.struts.action;

import gov.nih.nci.nautilus.struts.form.ComparativeGenomicForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import gov.nih.nci.nautilus.criteria.*;
import gov.nih.nci.nautilus.query.*;
import gov.nih.nci.nautilus.view.*;
import gov.nih.nci.nautilus.constants.Constants;

/** 
 * ComparitivegenomicAction.java created by EasyStruts - XsltGen.
 * http://easystruts.sf.net
 * created on 09-12-2004
 * 
 * XDoclet definition:
 * @struts:action path="/comparitivegenomic" name="comparitivegenomicForm" input="/jsp/comparitivegenomic.jsp" validate="true"
 * @struts:action-forward name="nautilus.menu" path="nautilus.menu"
 * @struts:action-forward name="nautilus.comparitiveGenomic" path="nautilus.comparitiveGenomic"
 */
public class ComparativeGenomicAction extends Action {

	// --------------------------------------------------------- Instance Variables

	// --------------------------------------------------------- Methods

	/** 
	 * Method execute
	 * @param ActionMapping mapping
	 * @param ActionForm form
	 * @param HttpServletRequest request
	 * @param HttpServletResponse response
	 * @return ActionForward
	 * @throws Exception
	 */
	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
			ComparativeGenomicForm comparativeGenomicForm =
				(ComparativeGenomicForm) form;
				
			String thisView = comparativeGenomicForm.getResultView();
			// Create Query Objects
			ComparativeGenomicQuery cghQuery = (ComparativeGenomicQuery) QueryManager.createQuery(QueryType.CGH_QUERY_TYPE);
			cghQuery.setQueryName(comparativeGenomicForm.getQueryName());
			
			// Change this code later to get view type directly from Form !!
			if (thisView.equalsIgnoreCase("sample")) 
				cghQuery.setAssociatedView(ViewFactory.newView(ViewType.SAMPLE_VIEW_TYPE));
			else if (thisView.equalsIgnoreCase("gene"))
				cghQuery.setAssociatedView(ViewFactory.newView(ViewType.Gene_VIEW_TYPE)); 
	 
			
			
			
		throw new UnsupportedOperationException("Generated method 'execute(...)' not implemented.");
		
		
		
		
	}

}
