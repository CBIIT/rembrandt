<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>

<s:form action="refineQueryRunReport" theme="simple">

<app:cshelp topic="Refine_query" /><br clear="all"/>

<s:actionerror />
<fieldset class="grayRefine">
<legend class="red">Step 1: Refine your result set</legend>
	
    <input type="radio" id="isAllGenesQuery1" name="refineQueryForm.isAllGenesQuery" class="radio" value="false" checked="true" 
    		onclick="javascript:onRadio(this, 0);"/>
    <label for="isAllGenesQuery1">Please refine your results by grouping the queries</label><br />
    
    <div id="qrows">
	<table align="center" border="0" width="95%" cellpadding="2" cellspacing="1" id="rosso" summary="This table is used to format page content">
		<tr><th></th></tr>
		<tr>
			<td colspan="4" class="message">
				Group Your Queries<BR>
				
			</td>
		</tr>
		<tr>
			<td class="message" width="10%"><label for="leftParen">(</label></td>
			<td class="message" width="30%"><label for="queryName">Query Name</label></td>
			<td class="message" width="10%"><label for="rightParen">)</label></td>
			<td class="message" width="10%"><label for="operand">and/or</label></td>
		</tr>
		<!-- BEGIN  Selected Queries -->
			
		
		<s:iterator value="refineQueryForm.selectedQueries" status="stat">
		
		<tr>
			<td>
				<s:select id="leftParen" name="refineQueryForm.selectedQueries[%{#stat.index}].leftParen" 
					list="refineQueryForm.leftParenOptions" headerValue="refineQueryForm.selectedQueries[%{#stat.index}].leftParen"/>
			</td>
			<td>
			<s:if test="refineQueryForm.selectedQueries[%{#stat.index}].queryName.length() > 0">
				<s:select id="queryName"name="refineQueryForm.selectedQueries[%{#stat.index}].queryName" 
					list="refineQueryForm.nonAllGenesQueries" listKey="queryName" listValue="queryName" 
					headerValue="refineQueryForm.selectedQueries[%{#stat.index}].queryName"/>
					</s:if>
					<s:else>
					<s:select id="queryName"name="refineQueryForm.selectedQueries[%{#stat.index}].queryName" 
						list="refineQueryForm.nonAllGenesQueries" listKey="queryName" listValue="queryName" 
						headerKey="-1" headerValue=" "/>
					</s:else>
			</td>
			<td>
				<s:select id="rightParen" name="refineQueryForm.selectedQueries[%{#stat.index}].rightParen" 
					list="refineQueryForm.rightParenOptions" headerValue="refineQueryForm.selectedQueries[%{#stat.index}].rightParen"/>
			</td>
			<Td>
				<s:select id="operand" name="refineQueryForm.selectedQueries[%{#stat.index}].operand" 
					list="refineQueryForm.operands" headerValue="refineQueryForm.selectedQueries[%{#stat.index}].operand"
					onchange="operandChange()" />
			</td>
		</tr>
		<s:fielderror fieldName="operand"/>
		</s:iterator>
				
		<!-- End  Selected Queries -->
	</table><br />
	</div>

</fieldset>
<br clear="all"/>
<!--Step 2-->
<fieldset class="grayRefine">
<legend class="red">Step 2: Select Result set (mandatory for "All Genes" queries)</legend>
<label for="srs">&nbsp;&nbsp;&nbsp;Select Result set to apply the above Query:</label>

<script type='text/javascript' src='dwr/interface/UserListHelper.js'></script>
<script language="javascript">
	function updateG(){
    	UserListHelper.getGenericListNamesFromString("PatientDID",createSampleList);
	}
	function createSampleList(data){   	
    	DWRUtil.removeAllOptions("srs");

   		DWRUtil.addOptions("srs", eval("[ { name:'', id:'none' } ]") , "name", "id");

    	DWRUtil.addOptions("srs", data);
	}
	

</script>
    	<select name="refineQueryForm.selectedResultSet" onfocus="updateG();" id="srs" style="width:150px;"><option value=""></option></select>
    	
  	
</fieldset>
<br clear="all"/>
<!--Step 3-->
<fieldset class="grayRefine">
<legend class="red"><label for="queryText">Step 3: Validate your query</label></legend>
	<table border="0" cellpadding="2" cellspacing="2" summary="This table is used to format page content">
		<tr><th></th><th></th></tr>
		<tr>
			<td align="center">&nbsp;&nbsp;&nbsp;
			<!--JavaScript added here to reset the response target back to the submitting window-->
			<s:submit id="validateButton" value="Validate Query" class="xbutton" onclick="validateQuery()">
			</s:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td align="right">
				<s:textarea id="queryText" name="refineQueryForm.queryText"  style="width:300px; height:40px;"></s:textarea>
			</td>
			
		</tr>
		
	</table>		
</fieldset>
<br clear="all"/>
<!--Step 4-->
<fieldset class="grayRefine">
<legend class="red"><label for="compoundView">Step 4: Please select a View</label></legend>	
	<table width="100%" border="0" summary="This table is used to format page content">
		<tr><th></th></tr>
		<tr><td>
				<!--  select name="refineQueryForm.compoundView" onchange="" id="compoundView" style="width:300px"><option value=" "> </option></select>
				-->
				<s:select name="refineQueryForm.compoundView" onchange="" id="compoundView" style="width:300px"
					list="refineQueryForm.compoundViewColl" listKey="value" listValue="label">
				</s:select>
				<s:fielderror fieldName="compoundView"/>
			</td>
		</tr>
	</table>
	<br>
	
</fieldset>
<br clear="all"/>
<!--Step 5-->
<fieldset class="grayRefine">
<legend class="red"><label for="instituteView">Step 5: Please select data source(s) to filter the query by ( Optional )</label></legend>	
	<table width="100%" border="0" summary="This table is used to format page content">
		<tr><th></th></tr>
		<tr><td>
			
				<s:select name="refineQueryForm.instituteView" onchange="" id="instituteView" style="width:300px" 
					list="refineQueryForm.institueViewColl" listKey="displayName" listValue="displayName" 
					headerKey="-1" headerValue=" "/>
					 
				<s:fielderror fieldName="instituteView"/>
			</td>
		</tr>
	</table>
	<br>
	
</fieldset>
<br clear="all"/>
<!--Step 6-->
<fieldset class="grayRefine">
<legend class="red">Step 6: Run report or return to previous screen</legend>
			<br>
			    <input type="button" id="clearButton" class="xbutton" value="Clear" onclick="javascript:location.href='refinecheck'"/>
				
				<input type="button" name="backbutton" class="xbutton" value="<< Back" 
					onclick="javascript:history.back();"/>

					&nbsp;&nbsp
				<!--check to see if query has been validated and the runFlag has been set on the form-->
				<s:if test="refineQueryForm.runFlag.equals('yes')">
					<!-- JavaScript here is to create a popup for the ReportResults -->
					<input type="button" id="runReportButton" name="backbutton" class="xbutton" value="Run Report >>" 
						onclick="runReport();"/>
				</s:if>
				
</fieldset>
</s:form>
	

<script language="JavaScript">
function operandChange(){
  //document.forms[0].validateButton.value="ChangedOperand";
  //document.forms[0].validateButton.click();
  document.forms[0].action="refineQueryOperandChange.action";
  document.forms[0].submit();
}

function validateQuery(){
	  //document.forms[0].validateButton.value="ChangedOperand";
	  //document.forms[0].validateButton.click();
	  document.forms[0].action="refineQueryValidateQery.action";
	  document.forms[0].submit();
	}
	
function runReport(){
	  //document.forms[0].validateButton.value="ChangedOperand";
	  //document.forms[0].validateButton.click();
	  document.forms[0].action="refineQueryRunReport.action";
	  document.forms[0].submit();
	}
</script>	
		