package gov.nih.nci.rembrandt.web.struts2.form;




import gov.nih.nci.caintegrator.enumeration.*;
import gov.nih.nci.rembrandt.util.MoreStringUtils;
import gov.nih.nci.rembrandt.web.bean.UserPreferencesBean;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.util.LabelValueBean;






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

public class PrincipalComponentForm implements RootForm {
    
 // -------------INSTANCE VARIABLES-----------------------------//
    private static Logger logger = Logger.getLogger(BaseForm.class);
	
    private String groupsOption = "allSamples";
    
    private String [] existingGroups;
    
    private List existingGroupsList;
    
    private String [] selectedGroups;
    
    private String selectedGroupName = "";
    
    private String analysisResultName = "";    
        
    private int variancePercentile = 70;
    
    private String filterType = "default";
    
    private String geneSetName;
    
    private String reporterSetName;
    
    private String arrayPlatform = "";
    
    public PrincipalComponentForm(){
		
    }

    /**
     * @return Returns the existingGroupsList.
     */
    public List getExistingGroupsList() {
        return this.existingGroupsList;
    }

    /**
     * @param existingGroupsList The existingGroupsList to set.
     */
    public void setExistingGroupsList(List existingGroupsList) {
        this.existingGroupsList = existingGroupsList;
    }

    /**
     * @return Returns the analysisResultName.
     */
    public String getAnalysisResultName() {
        return analysisResultName;
    }

    /**
     * @param analysisResultName The analysisResultName to set.
     */
    public void setAnalysisResultName(String analysisResultName) {
		if (analysisResultName != null )
			analysisResultName = MoreStringUtils.cleanJavascript(analysisResultName);

        this.analysisResultName = analysisResultName;
    }

    /**
     * @return Returns the arrayPlatform.
     */
    public String getArrayPlatform() {
        return arrayPlatform;
    }

    /**
     * @param arrayPlatform The arrayPlatform to set.
     */
    public void setArrayPlatform(String arrayPlatform) {
        this.arrayPlatform = arrayPlatform;
    }

    
       /**
     * @return Returns the variancePercentile.
     */
    public int getVariancePercentile() {
        return variancePercentile;
    }

    /**
     * @param variancePercentile The variancePercentile to set.
     */
    public void setVariancePercentile(int variancePercentile) {
        this.variancePercentile = variancePercentile;
    }
    
    /**
     * @param variancePercentile The variancePercentile to set.
     */
    public void setVariancePercentile(String variancePercentile) {
    	try {
    		this.variancePercentile = Integer.valueOf(variancePercentile);
    	}
    	catch (NumberFormatException e) {}
    }    


    /**
     * @return Returns the filterType.
     */
    public String getFilterType() {
        return filterType;
    }


    /**
     * @param filterType The filterType to set.
     */
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }


    /**
     * @return Returns the geneSetName.
     */
    public String getGeneSetName() {
        return geneSetName;
    }


    /**
     * @param geneSetName The geneSetName to set.
     */
    public void setGeneSetName(String geneSetName) {
        this.geneSetName = geneSetName;
    }


    /**
     * @return Returns the reporterSetName.
     */
    public String getReporterSetName() {
        return reporterSetName;
    }


    /**
     * @param reporterSetName The reporterSetName to set.
     */
    public void setReporterSetName(String reporterSetName) {
        this.reporterSetName = reporterSetName;
    }


    /**
     * @return Returns the selectedGroupName.
     */
    public String getSelectedGroupName() {
        return selectedGroupName;
    }


    /**
     * @param selectedGroupName The selectedGroupName to set.
     */
    public void setSelectedGroupName(String selectedGroupName) {
        this.selectedGroupName = selectedGroupName;
    }
    /**
     * @return Returns the existingGroups.
     */
    public String[] getExistingGroups() {
        return existingGroups;
    }

    /**
     * @param existingGroups The existingGroups to set.
     */
    public void setExistingGroups(String[] existingGroups) {
        this.existingGroups = existingGroups;
    }

    /**
     * @return Returns the selectedGroups.
     */
    public String[] getSelectedGroups() {
        return selectedGroups;
    }

    /**
     * @param selectedGroups The selectedGroups to set.
     */
    public void setSelectedGroups(String[] selectedGroups) {
        this.selectedGroups = selectedGroups;
    }
    

    /**
     * @return Returns the groupsOption.
     */
    public String getGroupsOption() {
        return groupsOption;
    }

    /**
     * @param groupsOption The groupsOption to set.
     */
    public void setGroupsOption(String groupsOption) {
        this.groupsOption = groupsOption;
    }

    /**
     * Method validate
     * 
     * @param ActionMapping
     *            mapping
     * @param HttpServletRequest
     *            request
     * @return ActionErrors
     */
/*    public ActionErrors validate(ActionMapping mapping,
            HttpServletRequest request) {

        ActionErrors errors = new ActionErrors();
        
       // Analysis name cannot be blank
        errors = UIFormValidator.validateQueryName(analysisResultName, errors);
       
       // Validate group field
        errors = UIFormValidator.validateSelectedGroup(groupsOption, selectedGroups, errors);
       
        return errors;
    }
    */
    
  
    /**
     * Method reset
     * 
     * @param ActionMapping
     *            mapping
     * @param HttpServletRequest
     *            request
     */
    public void reset(ActionMapping mapping, HttpServletRequest request) {
        analysisResultName = "";           
        arrayPlatform = ""; 
        filterType = "default";
      
    }

    


    
}
