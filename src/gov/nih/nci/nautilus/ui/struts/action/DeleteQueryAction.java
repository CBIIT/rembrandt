package gov.nih.nci.nautilus.ui.struts.action;

import gov.nih.nci.nautilus.cache.CacheManagerDelegate;
import gov.nih.nci.nautilus.cache.ConvenientCache;
import gov.nih.nci.nautilus.ui.bean.SessionQueryBag;
import gov.nih.nci.nautilus.ui.struts.form.DeleteQueryForm;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;


public class DeleteQueryAction extends DispatchAction {
    private Logger logger = Logger.getLogger(DeleteQueryAction.class);
    private ConvenientCache cacheManager = CacheManagerDelegate.getInstance();

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
	public ActionForward deleteQuery(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
		   DeleteQueryForm deleteQueryForm = (DeleteQueryForm) form;	
		   String page = (String)request.getSession().getAttribute("currentPage");
		   logger.debug("the current page is :"+page);
		   String sessionId = request.getSession().getId();
		   SessionQueryBag queryBag = cacheManager.getSessionQueryBag(sessionId);
		   if(queryBag != null){			     
			  Collection queryColl = queryBag.getQueries();	
			  String queryKey = deleteQueryForm.getQueryKey();
			  logger.debug("queryKey is ************:"+queryKey);		  
			  queryColl.remove(queryBag.getQuery(queryKey));	 
			}  	 	
		   return mapping.findForward("menuPage");		
	     }
	
	public ActionForward deleteAll(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {
	 String sessionId = request.getSession().getId();	
	 DeleteQueryForm deleteQueryForm = (DeleteQueryForm) form;
	 String page = (String)request.getSession().getAttribute("currentPage");
	 SessionQueryBag queryBag = cacheManager.getSessionQueryBag(sessionId);
	 Collection queryColl = queryBag.getQueries();
	 /**
	  * @todo Need to make sure this actually clearing out the Collection
	  * 	--Dave
	  */
	 queryColl.clear();
	 return mapping.findForward("menuPage");			
	
	}
	

}
