/*L
 * Copyright (c) 2006 SAIC, SAIC-F.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/rembrandt/LICENSE.txt for details.
 */

package gov.nih.nci.rembrandt.web.struts2.action;

import gov.nih.nci.caintegrator.application.cache.CacheConstants;
import gov.nih.nci.caintegrator.application.configuration.SpringContext;
import gov.nih.nci.caintegrator.application.lists.ListOrigin;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.UserListBean;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.rembrandt.util.ApplicationContext;
import gov.nih.nci.caintegrator.dto.de.InstitutionDE;
import gov.nih.nci.caintegrator.exceptions.FindingsQueryException;
import gov.nih.nci.caintegrator.security.SecurityManager;
import gov.nih.nci.caintegrator.security.UserCredentials;
import gov.nih.nci.rembrandt.cache.RembrandtPresentationTierCache;
import gov.nih.nci.rembrandt.service.findings.RembrandtAsynchronousFindingManagerImpl;
import gov.nih.nci.rembrandt.util.RembrandtConstants;
import gov.nih.nci.rembrandt.util.RembrandtListLoader;
import gov.nih.nci.rembrandt.util.StatisticsInfoPlugIn;

import gov.nih.nci.rembrandt.web.ajax.WorkspaceHelper;
import gov.nih.nci.rembrandt.web.bean.SessionQueryBag;
import gov.nih.nci.rembrandt.web.bean.UserPreferencesBean;
import gov.nih.nci.rembrandt.web.factory.ApplicationFactory;
import gov.nih.nci.rembrandt.web.helper.InsitutionAccessHelper;

import gov.nih.nci.rembrandt.web.struts2.form.LoginForm;

import gov.nih.nci.caintegrator.security.SecurityManager;
import gov.nih.nci.caintegrator.security.UserCredentials;

import java.text.ParseException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.security.sasl.AuthenticationException;
import javax.naming.OperationNotSupportedException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import org.quartz.SchedulerException;

import com.opensymphony.xwork2.ActionSupport;


/**
* caIntegrator License
* 
* Copyright 2001-2005 Science Applications International Corporation ("SAIC"). 
* The software subject to this notice and license includes both human readable source code form and machine readable, 
* binary, object code form ("the caIntegrator Software"). The caIntegrator Software was developed in conjunction with 
* the National Cancer Institute ("NCI") by NCI employees and employees of SAIC. 
* To the extent government employees are authors, any rights in such works shall be subject to Title 17 of the United States
* Code, section 105. 
* This caIntegrator Software License (the "License") is between NCI and You. "You (or "Your") shall mean a person or an 
* entity, and all other entities that control, are controlled by, or are under common control with the entity. "Control" 
* for purposes of this definition means (i) the direct or indirect power to cause the direction or management of such entity,
*  whether by contract or otherwise, or (ii) ownership of fifty percent (50%) or more of the outstanding shares, or (iii) 
* beneficial ownership of such entity. 
* This License is granted provided that You agree to the conditions described below. NCI grants You a non-exclusive, 
* worldwide, perpetual, fully-paid-up, no-charge, irrevocable, transferable and royalty-free right and license in its rights 
* in the caIntegrator Software to (i) use, install, access, operate, execute, copy, modify, translate, market, publicly 
* display, publicly perform, and prepare derivative works of the caIntegrator Software; (ii) distribute and have distributed 
* to and by third parties the caIntegrator Software and any modifications and derivative works thereof; 
* and (iii) sublicense the foregoing rights set out in (i) and (ii) to third parties, including the right to license such 
* rights to further third parties. For sake of clarity, and not by way of limitation, NCI shall have no right of accounting
* or right of payment from You or Your sublicensees for the rights granted under this License. This License is granted at no
* charge to You. 
* 1. Your redistributions of the source code for the Software must retain the above copyright notice, this list of conditions
*    and the disclaimer and limitation of liability of Article 6, below. Your redistributions in object code form must reproduce 
*    the above copyright notice, this list of conditions and the disclaimer of Article 6 in the documentation and/or other materials
*    provided with the distribution, if any. 
* 2. Your end-user documentation included with the redistribution, if any, must include the following acknowledgment: "This 
*    product includes software developed by SAIC and the National Cancer Institute." If You do not include such end-user 
*    documentation, You shall include this acknowledgment in the Software itself, wherever such third-party acknowledgments 
*    normally appear.
* 3. You may not use the names "The National Cancer Institute", "NCI" "Science Applications International Corporation" and 
*    "SAIC" to endorse or promote products derived from this Software. This License does not authorize You to use any 
*    trademarks, service marks, trade names, logos or product names of either NCI or SAIC, except as required to comply with
*    the terms of this License. 
* 4. For sake of clarity, and not by way of limitation, You may incorporate this Software into Your proprietary programs and 
*    into any third party proprietary programs. However, if You incorporate the Software into third party proprietary 
*    programs, You agree that You are solely responsible for obtaining any permission from such third parties required to 
*    incorporate the Software into such third party proprietary programs and for informing Your sublicensees, including 
*    without limitation Your end-users, of their obligation to secure any required permissions from such third parties 
*    before incorporating the Software into such third party proprietary software programs. In the event that You fail 
*    to obtain such permissions, You agree to indemnify NCI for any claims against NCI by such third parties, except to 
*    the extent prohibited by law, resulting from Your failure to obtain such permissions. 
* 5. For sake of clarity, and not by way of limitation, You may add Your own copyright statement to Your modifications and 
*    to the derivative works, and You may provide additional or different license terms and conditions in Your sublicenses 
*    of modifications of the Software, or any derivative works of the Software as a whole, provided Your use, reproduction, 
*    and distribution of the Work otherwise complies with the conditions stated in this License.
* 6. THIS SOFTWARE IS PROVIDED "AS IS," AND ANY EXPRESSED OR IMPLIED WARRANTIES, (INCLUDING, BUT NOT LIMITED TO, 
*    THE IMPLIED WARRANTIES OF MERCHANTABILITY, NON-INFRINGEMENT AND FITNESS FOR A PARTICULAR PURPOSE) ARE DISCLAIMED. 
*    IN NO EVENT SHALL THE NATIONAL CANCER INSTITUTE, SAIC, OR THEIR AFFILIATES BE LIABLE FOR ANY DIRECT, INDIRECT, 
*    INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE 
*    GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF 
*    LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT 
*    OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
* 
*/


public final class LoginAction extends ActionSupport implements ServletRequestAware
{
    private static Logger logger = Logger.getLogger(LoginAction.class);
    
    private static RembrandtPresentationTierCache _cacheManager = ApplicationFactory.getPresentationTierCache();
    private RembrandtListLoader listLoader;
    
    private static SecurityManager securityManager = SecurityManager
			.getInstance("rembrandt");
    private UserCredentials credentials;
    
    private HttpServletRequest servletRequest;
    
    LoginForm loginForm;
    String token;
    
    public String execute()
    {
    	/**
    	 * This needs to be modified to take advantage of the new UserCredentials
    	 * object.
    	 * 
    	 * It also needs to have the logic added for reinstating a previously
    	 * saved session.
    	 */
    	
    	String sID = this.servletRequest.getHeader("Referer");
    	
    	// prevents Referer Header injection
    	if ( sID != null && sID != "" && !sID.contains("rembrandt")) {
    		return "failure";
    	}
    	
        HttpSession session = this.servletRequest.getSession();
        ServletContext context = session.getServletContext();
        RembrandtListLoader myListLoader = (RembrandtListLoader) SpringContext.getBean("listLoader");
        SessionQueryBag theBag = null;
        
        //check to see if jobscheduler is started.
        String schedulerType = System.getProperty("rembrandt.scheduler.type");
        try{
        	if(System.getProperty("rembrandt.scheduler.started") == null){
        		StatisticsInfoPlugIn.scheduleWork(Integer.parseInt(schedulerType), context);
        		System.setProperty("rembrandt.scheduler.started","TRUE");
        	}
        }catch (SchedulerException se){
        	logger.error("Failed to schedule the job: " + se.getMessage());
        }
       
        LoginForm f = loginForm;
        
        String error = validateLogin(f.getUserName(), f.getPassword());
        
        if(!f.getUserLoggedIn()) {
        	addFieldError("invalidLogin", error);
        	return "failure";  
        }
        
        session = this.servletRequest.getSession();
        session.setAttribute("autoLogged", "no");
        
        //Shan: seems this is never set to "no"
        session.setAttribute("logged", "yes");
        session.setAttribute("name", f.getUserName());
        UserPreferencesBean userPreferencesBean = new UserPreferencesBean();
        session.setAttribute(RembrandtConstants.USER_PREFERENCES,userPreferencesBean);
        //UserCredentials credentials = (UserCredentials)session.getAttribute(RembrandtConstants.USER_CREDENTIALS);
        if(credentials.getUserId() != null  && !credentials.getUserName().equals("RBTuser")){
        	session.setAttribute("email", credentials.getEmailAddress());
        	theBag = WorkspaceHelper.loadSessionQueryBagFromDB(session,credentials.getUserId());
        	WorkspaceHelper.loadListTreeStructures(session);
        	WorkspaceHelper.loadQueryTreeStructures(session);
        }

        if(theBag != null){
        	_cacheManager.putSessionQueryBag(session.getId(), theBag);
        }else{ //user has no previously saved session so create one
        	_cacheManager.putSessionQueryBag(session.getId(), new SessionQueryBag());
        } 

        UserListBean userListBean = new UserListBean();
        try {
        	userListBean = myListLoader.loadDiseaseGroups(userListBean, session);
        } catch (OperationNotSupportedException e) {
        	// TODO Auto-generated catch block
        	e.printStackTrace();
        }


        UserListBeanHelper userListBeanHelper = new UserListBeanHelper(session.getId());

        //load institution based lists
        Collection<InstitutionDE> institutions = InsitutionAccessHelper.getInsititutionCollection(session);
        if(institutions != null){
        	for(InstitutionDE institution:institutions){
        		List<UserList> userLists = (List<UserList>) myListLoader.loadUserListsByInstitution(institution.getInstituteName());
        		if(userLists != null && userLists.size() > 0){
        			for(UserList ul: userLists){
        				userListBean.addList(ul);
        			}
        		}
        	}
        }
        //load custom lists
        List<UserList> userLists = WorkspaceHelper.loadCustomListsFromDB(credentials.getUserName());
        if(userLists != null && userLists.size() > 0){
        	WorkspaceHelper.fetchListWorkspaceFromDB(session, credentials.getUserId());
        	for(UserList ul: userLists){
        		userListBean.addList(ul);
        	}
        }

        userListBeanHelper.addBean(session.getId(),CacheConstants.USER_LISTS,userListBean);
        //load persisted Q

        String reportName = (String) this.servletRequest.getSession().getAttribute("emailFileName");
        if(reportName != null){       	
        	RembrandtAsynchronousFindingManagerImpl asynchronousFindingManagerImpl = new RembrandtAsynchronousFindingManagerImpl();
        	try {						
        		asynchronousFindingManagerImpl.retrieveResultsFromFile(this.servletRequest.getSession().getId(), 
        				reportName, credentials.getUserName(), this.servletRequest.getSession());
        	} catch (FindingsQueryException e) {
        		logger.error(e.getMessage());				
        	}
        }
       
        return "success";
    }
    
    private UserList getUserList(UserList theList){
    	UserList userList = new UserList();
        try {
        	userList.setDateCreated(theList.getDateCreated());
        	userList.setInvalidList(theList.getInvalidList());
        	userList.setItemCount(theList.getItemCount());
        	userList.setList(theList.getList());
        	userList.setListSubType(theList.getListSubType());
        	userList.setListType(theList.getListType());
            userList.setListOrigin(ListOrigin.Custom);
        	userList.setName(theList.getName());
        	userList.setNotes(theList.getNotes());
        }
        catch (ParseException pe){
        	logger.debug("Persisted UserList has a malformed Date object.");
        }
        return userList;
    }
    /**
     * @return Returns the listLoader.
     */
    public RembrandtListLoader getListLoader() {
        return listLoader;
    }
    /**
     * @param listLoader The listLoader to set.
     */
    public void setListLoader(RembrandtListLoader listLoader) {
        this.listLoader = listLoader;
    }
   	
   	@Override
    public void validate(){
   		String userName = this.loginForm.getUserName();
   		String password = this.loginForm.getPassword();
        
   		if(userName == null || userName.length() == 0){
            addFieldError("invalidLogin", "Invalid User Name");
        }
   		
        if(password == null || password.length() == 0){
        	addFieldError("invalidLogin", "Invalid Password");
        }
    }

	public LoginForm getLoginForm() {
		return loginForm;
	}

	public void setLoginForm(LoginForm loginForm) {
		this.loginForm = loginForm;
	}
   	

	public String validateLogin(String userName, String password) {
		
		try {
			boolean authenticate = false;
			if("RBTuser".equals(userName)&&"RBTpass".equals(password)){
				authenticate = true; //bypass security manager
			} else if(securityManager != null  &&
			  securityManager.authenticate(userName, password) ) {
				authenticate = true;
			}
			
			if(authenticate){				
				credentials = securityManager.authorization(userName);
			}
		} catch (AuthenticationException e) {
			logger.error(e);
			return e.getMessage();
		}
		
		// Checking for Injected Sessions (Appscan)
		/*
		 * Shan TODO: use the struts 2 way to handle this
		String saved = (String) request.getSession().getAttribute("org.apache.struts.action.TOKEN");
		String token = request.getParameter("org.apache.struts.taglib.html.TOKEN");
		
		
		if( ( saved!= null && token != null && !saved.equals(token) ) || saved == null || token == null ) {
			request.getSession().invalidate();
			//errors.add("invalidLogin", new ActionError(
			//		"gov.nih.nci.nautilus.ui.struts.form.invalidLogin.error"));
			
			//Why it doesn't return immediately?
			error = "Invalid login";
		}
		
		*/
		String error = "";
		if (credentials != null && credentials.authenticated()) {
			this.servletRequest.getSession().invalidate();
			this.loginForm.setUserLoggedIn(true);
			this.servletRequest.getSession(true).setAttribute(RembrandtConstants.USER_CREDENTIALS,credentials);
		} else {
			error = ApplicationContext.getLabelProperties().getProperty(
	    			"gov.nih.nci.nautilus.ui.struts.form.invalidLogin.error");
		}

		return error;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
 
}
