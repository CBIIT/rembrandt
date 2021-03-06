/*L
 * Copyright (c) 2006 SAIC, SAIC-F.
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/rembrandt/LICENSE.txt for details.
 */

package gov.nih.nci.rembrandt.web.struts2.action;

import gov.nih.nci.caintegrator.application.lists.ListItem;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.rembrandt.cache.RembrandtPresentationTierCache;
import gov.nih.nci.rembrandt.dto.query.Query;
import gov.nih.nci.rembrandt.util.RembrandtConstants;
import gov.nih.nci.rembrandt.web.ajax.WorkspaceHelper;
import gov.nih.nci.rembrandt.web.bean.SessionQueryBag;
import gov.nih.nci.rembrandt.web.factory.ApplicationFactory;
import gov.nih.nci.rembrandt.workspace.WorkspaceQuery;
import gov.nih.nci.caintegrator.application.workspace.WorkspaceList;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.mapping.MappingException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.xml.sax.InputSource;

import com.opensymphony.xwork2.ActionSupport;




/**
 * @author PrakashT
 *
 */


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

public class ExportWorkspaceAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware  {

	private static Logger logger = Logger.getLogger(ExportWorkspaceAction.class);
	private static RembrandtPresentationTierCache _cacheManager = ApplicationFactory.getPresentationTierCache();
	
	Map<String, Object> sessionMap;
	HttpServletRequest servletRequest;
	HttpServletResponse servletReponse;
	
	String node_name;
	String node_type;
	
	/**
	 * execute is called when this action is posted to
	 * <P>
	 * @param mapping The ActionMapping for this action as configured in struts
	 * @param form The ActionForm that posted to this action if any
	 * @param request The HttpServletRequest for the current post
	 * @param response The HttpServletResponse for the current post
	 */
	public String execute()
		throws Exception
	{
		HttpSession sess = this.servletRequest.getSession();
		WorkspaceList exportListFolder = null;
		WorkspaceQuery exportQueryFolder = null;
		
		String nodeName = this.node_name; // this.servletRequest.getParameter( "node_name" );
		String nodeType = this.node_type; // this.servletRequest.getParameter( "node_type" );
		
		if ( nodeType.equals( "Lists")) {
			UserListBeanHelper ulbh = new UserListBeanHelper(sess.getId());
			List<UserList> uls = ulbh.getAllCustomLists(); 
			
			// if the user selects just one file, we wrap it manually with an Export Folder and make it the root.
			for(UserList ul : uls){
				if ( ul.getName().equals(nodeName)){
					removeId( ul );
					exportListFolder = new WorkspaceList( "_Export _Folder" );
					exportListFolder.addLeaf(ul);
					break;
				}
			}
		
			// means the user clicked on a folder NOT an individual file. The folder becomes the root.
			if ( exportListFolder == null )
			{
				String tree = (String) sessionMap.get(RembrandtConstants.OLIST_STRUCT);
				JSONObject node = WorkspaceHelper.findNode(tree, nodeName);
				
				if ( node != null )
					exportListFolder = updateExportList(exportListFolder, uls, node);
			}
		}
		else {
			SessionQueryBag queryBag = _cacheManager.getSessionQueryBag(sess.getId());

			Query query = queryBag.getQuery(nodeName);
			if ( query != null )
			{
				exportQueryFolder = new WorkspaceQuery( "_Export _Folder" );
				exportQueryFolder.addLeaf(query);
			}

			// means the user clicked on a folder NOT an individual file. The folder becomes the root.
			if ( exportQueryFolder == null )	
			{
				String tree = (String) this.sessionMap.get(RembrandtConstants.OQUERY_STRUCT);
				JSONObject node = WorkspaceHelper.findNode(tree, nodeName);
				
				if ( node != null )
					exportQueryFolder = updateExportQuery(exportQueryFolder, queryBag.getQueries(), node);
			}
		}
		
		this.servletReponse.setContentType("application/x-download");
		this.servletReponse.setHeader("Content-Disposition", "attachment; filename=\"" + nodeName + ".xml" + "\"");
		try
		{
			PrintWriter out = this.servletReponse.getWriter();
			StringWriter writer = new StringWriter();
			
			if ( nodeType.equals( "Lists") )
			{
				Marshaller.marshal(exportListFolder, writer);
			}
			else
			{
//				URL castor_location = new URL("file:///c:/local/content/rembrandt/config/castor_query.xml");
				URL castor_location = new URL("file:///local/content/rembrandt/config/castor_query.xml");
				InputSource is = new InputSource(castor_location.getPath());
//				InputSource is = new InputSource(getClass().getClassLoader().getResource("castor_query.xml").getPath());
				Mapping castorMapping = new Mapping();
				castorMapping.loadMapping(is);

				Marshaller marshaller = new Marshaller(writer);
				marshaller.setMapping(castorMapping);
				marshaller.marshal(exportQueryFolder);
			}
			
			out.print( writer );
			writer.close();
		}
		catch( IOException e )
		{
			logger.error( e.getMessage());
			throw new Exception(e.getMessage());
		}
		catch( MappingException e )
		{
			logger.error( e.getMessage());
			throw new Exception(e.getMessage());
		}
		
		return null;
	}

	private WorkspaceList updateExportList(WorkspaceList exportFolder, List<UserList> uls, JSONObject jsaObj) {
		WorkspaceList workspaceFolder;
		Object obj;
		JSONObject customList;
		JSONArray customItems;

		customList = jsaObj;
		customItems = (JSONArray) customList.get("items");
		
		if ( exportFolder == null )
		{	
			exportFolder = new WorkspaceList();
			exportFolder.setName( (String)jsaObj.get("txt"));
		}
		Iterator custom_iterator = customItems.iterator();
		while(custom_iterator.hasNext()){
			obj = custom_iterator.next();
			JSONObject customObj = (JSONObject)obj;
			boolean found = false;
			for(UserList ul : uls){
				if ( ul.getName().equals(customObj.get("txt"))){
					removeId( ul );
					exportFolder.addLeaf( ul );
					found = true;
					break;
				}
			}
			if ( ! found )  // then it is a folder. search recursively
			{
				if( ( (String)customObj.get("txt") ).equals( "Trash") )	// ignore the trash folder
				{
					continue;
				}
					
				workspaceFolder = new WorkspaceList( (String)customObj.get("txt") );
				exportFolder.addLeaf( updateExportList( workspaceFolder, uls, customObj ) ) ;	// recursive call to catch the underlying folders/files
			}
		}

		return exportFolder;
	}
	
	private WorkspaceQuery updateExportQuery(WorkspaceQuery exportFolder, Collection<Query> queries, JSONObject jsaObj) {
		WorkspaceQuery workspaceFolder;
		Object obj;
		JSONObject customList;
		JSONArray customItems;

		customList = jsaObj;
		customItems = (JSONArray) customList.get("items");
		
		if ( exportFolder == null )
		{	
			exportFolder = new WorkspaceQuery();
			exportFolder.setQueryName((String)jsaObj.get("txt"));
		}
		Iterator custom_iterator = customItems.iterator();
		while(custom_iterator.hasNext()){
			obj = custom_iterator.next();
			JSONObject customObj = (JSONObject)obj;
			boolean found = false;
			for(Query query :  queries ){
				if( query.getQueryName().equals( customObj.get("txt") ) ){
					exportFolder.addLeaf(query);
					found = true;
					break;
				}
			}
			if ( ! found )  // then it is a folder. search recursively
			{
				if( ( (String)customObj.get("txt") ).equals( "Trash") )	// ignore the trash folder
				{
					continue;
				}
					
				workspaceFolder = new WorkspaceQuery( (String)customObj.get("txt") );
				exportFolder.addLeaf( updateExportQuery( workspaceFolder, queries, customObj ) ) ;	// recursive call to catch the underlying folders/files
			}
		}

		return exportFolder;
	}

	private void removeId( UserList ul )
	{
		ul.setId(null);	// need to be set to null so that DB will create a new List record.
		for ( ListItem li : ul.getListItems() )
		{
			li.setId( null );  // need to be set to null so that DB will create a new List Item record.
			li.setListId( null );
		}
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.servletRequest = arg0;
	}

	@Override
	public void setSession(Map<String, Object> arg0) {
		this.sessionMap = arg0;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.servletReponse = arg0;
	}

	public String getNode_name() {
		return node_name;
	}

	public void setNode_name(String node_name) {
		this.node_name = node_name;
	}

	public String getNode_type() {
		return node_type;
	}

	public void setNode_type(String node_type) {
		this.node_type = node_type;
	}
	
	
}
