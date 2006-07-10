package gov.nih.nci.rembrandt.web.ajax;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import gov.nih.nci.caintegrator.application.lists.ListManager;
import gov.nih.nci.caintegrator.application.lists.ListSubType;
import gov.nih.nci.caintegrator.application.lists.ListType;
import gov.nih.nci.caintegrator.application.lists.UserList;
import gov.nih.nci.caintegrator.application.lists.UserListBean;
import gov.nih.nci.caintegrator.application.lists.UserListBeanHelper;
import gov.nih.nci.caintegrator.application.lists.ajax.CommonListFunctions;
//import gov.nih.nci.ispy.util.ispyConstants;
import gov.nih.nci.rembrandt.web.helper.RembrandtListValidator;


import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import uk.ltd.getahead.dwr.ExecutionContext;

public class DynamicListHelper {
/**
 * Basically a wrapper for app-commons/application/lists/ajax/CommonListFunctions
 * except this specifically sets the ListValidator for this context and passes it off
 * to the CommonListFunctions
 *
 */
	
	public DynamicListHelper() {}
	/*
    public static String getPatientListAsList()	{
		return CommonListFunctions.getListAsList(ListType.PatientDID);
	}
	
	public static String getGeneListAsList()	{
		return CommonListFunctions.getListAsList(ListType.GeneSymbol);
	}
	*/
	public static String getGenericListAsList(String listType)	{
		return CommonListFunctions.getListAsList(ListType.valueOf(listType));
	}
	
	public static String createGenericList(String listType, String[] list, String name)	{
		try	{
			ListType lt = ListType.valueOf(listType);
			return CommonListFunctions.createGenericList(lt, list, name, new RembrandtListValidator());
		}
		catch(Exception e)	{
			//try as a patient list as default, will fail validation if its not accepted
			return CommonListFunctions.createGenericList(ListType.PatientDID, list, name, new RembrandtListValidator());
		}
	}

	public static String createPatientList(String[] list, String name){
		return CommonListFunctions.createGenericList(ListType.PatientDID, list, name, new RembrandtListValidator());
	}

	
	public static String createGeneList(String[] list, String name){
		return CommonListFunctions.createGenericList(ListType.GeneSymbol, list, name, new RembrandtListValidator());
	}

	public static String exportListasTxt(String name, HttpSession session){
		return CommonListFunctions.exportListasTxt(name, session);
	}
	
	public static String getAllLists()	{
		//create a list of allowable types
		ArrayList listTypesList = new ArrayList();
		for(ListType l  : ListType.values())	{
			listTypesList.add(l.toString());
		}
		//call CommonListFunctions.getAllLists(listTypesList);
		return CommonListFunctions.getAllLists(listTypesList);
	}
	public static String getGenericList(String listType)	{
		//just want one type
		ArrayList<String> listTypesList = new ArrayList();
		listTypesList.add(listType);
		return CommonListFunctions.getAllLists(listTypesList);
	}
	
	/*
	public static String getAllPatientLists()	{
		return CommonListFunctions.getAllLists(ListType.PatientDID.toString());
	}
	*/

	/*
	public static String getAllGeneLists()	{
		return CommonListFunctions.getAllLists(ListType.GeneSymbol.toString());
	}
	*/
	
	public static String uniteLists(String[] sLists, String groupName, String groupType, String action)	{	
		return CommonListFunctions.uniteLists(sLists, groupName, groupType, action);
	}
}