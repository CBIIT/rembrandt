<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html:form action="refineQuery.do">
<tr class="report"><td>
<div class="steps">
<b>Step 1: Please refine your result set by grouping it</b>
	&nbsp;&nbsp;&nbsp;
	<table border="0" width="95%" cellpadding="2" cellspacing="1" id="rosso">
		<tr>
			<td colspan="4" class="message">
				Group Your Queries<BR>
				<html:errors property="org.apache.struts.action.GLOBAL_ERROR"/>
			</td>
		</tr>
		<tr>
			<td class="message" width="10%">(</td>
			<td class="message" width="30%">Query Name</td>
			<td class="message" width="10%">)</td>
			<td class="message" width="10%">and/or</td>
		</tr>
		<tr class="setQuery" id="test">
			<!--
			<Td>
				<html:select property="queryName1" onchange="javascript:showName(this, document.getElementById('query1'));">
				    <html:optionsCollection property="queryNameColl" />
				</html:select><html:errors property="queryName1"/>
				
			</td>
			-->
			<td>
				<html:select property="leftParen1">
					<html:option value="">&nbsp;</html:option>
					<html:option value="(">&nbsp;(&nbsp;</html:option>
				</html:select>
			</td>
			<!--
			<td id="query1">
				&nbsp;
			</td>
			-->
			<Td>
				<html:select property="queryName1" onchange="javascript:showName(this, document.getElementById('query1'));">
				    <html:optionsCollection property="queryNameColl" />
				</html:select><html:errors property="queryName1"/>
				
			</td>
			<td>
				<html:select property="rightParen1">
					<html:option value="">&nbsp;</html:option>
					<html:option value=")">&nbsp;)&nbsp;</html:option>
				</html:select>
			</td>
			<Td>
				<html:select property="operatorType1" onchange="resetVal(this)">
					<html:option value="">&nbsp;</html:option>
					<html:option value="AND">and</html:option>
					<html:option value="OR">or</html:option>
					<!--<html:option value="NOT">not</html:option>
					<html:option value="PRB">project from results</html:option>-->
				</html:select><html:errors property="operatorType1"/>
			</td>
		</tr>
		<tr class="setQuery">
			<!--
			<Td>
				<html:select property="queryName2" onchange="javascript:showName(this, document.getElementById('query2'));">
				    <html:optionsCollection property="queryNameColl" />
				</html:select><html:errors property="queryName2"/>
			</td>
			-->
			<td>
				<html:select property="leftParen2">
					<html:option value="">&nbsp;</html:option>
					<html:option value="(">&nbsp;(&nbsp;</html:option>
				</html:select>
			</td>
			<!--
			<tD id="query2">
				&nbsp;
			</td>
			-->
			<Td>
				<html:select property="queryName2" onchange="javascript:showName(this, document.getElementById('query2'));">
				    <html:optionsCollection property="queryNameColl" />
				</html:select><html:errors property="queryName2"/>
			</td>
			<td>
				<html:select property="rightParen2">
					<html:option value="">&nbsp;</html:option>
					<html:option value=")">&nbsp;)&nbsp;</html:option>
				</html:select>
			</td>
			<Td>
				<html:select property="operatorType2" onchange="resetVal(this)">
					<html:option value="">&nbsp;</html:option>
					<html:option value="AND">and</html:option>
					<html:option value="OR">or</html:option>
					<!--<html:option value="NOT">not</html:option>
					<html:option value="PRB">project from results</html:option>-->
				</html:select><html:errors property="operatorType2"/>
			</td>
		</tr>
		<tr class="setQuery">
			<!--
			<Td>
				<html:select property="queryName3" onchange="javascript:showName(this, document.getElementById('query3'));">
				    <html:optionsCollection property="queryNameColl" />
				</html:select>
			</td>
			-->
			<td>
				<html:select property="leftParen3">
					<html:option value="">&nbsp;</html:option>
					<html:option value="(">&nbsp;(&nbsp;</html:option>
				</html:select>
			</td>
			<!--
			<tD id="query3">
				&nbsp;
			</td>
			-->
			<Td>
				<html:select property="queryName3" onchange="javascript:showName(this, document.getElementById('query3'));">
				    <html:optionsCollection property="queryNameColl" />
				</html:select>
			</td>
			<td>
				<html:select property="rightParen3">
					<html:option value="">&nbsp;</html:option>
					<html:option value=")">&nbsp;)&nbsp;</html:option>
				</html:select>
			</td>
			<Td>&nbsp;
			</td>
		</tr>
	</table>
</div>
<!--Display buttons here to add later-->			
<!--<div class="midButtons">
	<b class="message">[add more rows]</b><br />
		<br><input type="reset" value="reset query" class="sbutton">
</div>	-->
<br/><br/>

<div class="steps">	
<b>Step 2: Validate your query</b><br>
	<table border="0" cellpadding="2" cellspacing="2">
		<tr>
			<td align="center">&nbsp;&nbsp;&nbsp;
			<!--JavaScript added here to reset the response target back to the submitting window-->
			<html:submit styleClass="xbutton"  property="method" onclick="javascript:document.forms[0].target = '_self';">
				<bean:message key="RefineQueryAction.validateButton"/>
			</html:submit>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
			<td align="right">
				<html:textarea property="queryText"  style="width:300px; height:40px;"></html:textarea>
			</td>
			
		</tr>
		
	</table>
		
</div>	
	
	
<div class="steps">	
	<table width="100%" border="0">
		<tr><td>
				<br>
				<b>Step 3: Please select a View</b>&nbsp;&nbsp;
				<html:select property="compoundView" onchange="">
				    <html:optionsCollection property="compoundViewColl" />
				</html:select><html:errors property="compoundView"/>
			</td>
		</tr>
	</table>
	
</div>

<div class="steps">
	 <table border="0">
		<tr>
			<td>
				<br>
				<b>Step 4: Please name your resultset</b>&nbsp;&nbsp;
				<html:text property="resultsetName" onchange="" disabled="true">
				</html:text>&nbsp;&nbsp;&nbsp;
				<input type="button" class="sbutton" value="Save Query As..." disabled="true">
			</td>
		</tr>
	</table>
</div>	
	<br>
<div class="steps">	
	<table border="0" width="100%">
		<tr>
			<td>
			<b>Step 5: Run report or return to previous screen</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			</td>
		</tr>
		<tr>
			<td align="center"><br>
				<html:button property="backbutton" styleClass="xbutton" value="<< Back" 
					onclick="javascript:history.back();"/>&nbsp;&nbsp
				<!--check to see if query has been validated and the runFlag has been set on the form-->
				<logic:equal name="refineQueryForm" property="runFlag" value="yes" >
					<!-- JavaScript here is to create a popup for the ReportResults -->
					<html:submit styleClass="xbutton"  property="method" onclick="javascript:return formNewTargetSimple('_report', 770, 550);">
						<bean:message key="RefineQueryAction.runReportButton"/>
					</html:submit>
				</logic:equal> 
			</td>
		</tr>
	</table>
</div>
</html:form>
</td></tr>		
		