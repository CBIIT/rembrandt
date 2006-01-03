<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/rembrandt.tld" prefix="app" %>

<%
	String act = request.getParameter("act");
%>
<fieldset class="gray">
<legend class="red">Clone Id/Probe Set Id
<% if(act.equalsIgnoreCase("geneexpression"))	{ %>
<app:help help="Paste a comma delimited IMAGE Clone Id/Affymetrix probe set ID list, or upload a text file containing the IDs using the browse button. There must only be one entry per line and a return must be added at the end of the file. Image IDs must start with �IMAGE:�" />
<% } else { %>
<app:help help="Future implementation"/>
<% } %>
</legend>
<!-- <b class="message">(Paste comma separated Clone Id list, or upload file using Browse button)</b> -->
<br />

	
	
	
	
	<html:select property="cloneList" disabled="false">
	<html:optionsCollection property="cloneTypeColl" />
	</html:select>
				
&nbsp;
<html:radio property="cloneId" value="list" styleClass="radio" onfocus="javascript:onRadio(this,0);" />
	<html:text property="cloneListSpecify" disabled="false" onfocus="javascript:radioFold(this);"  onblur="javascript:cRadio(this, document.forms[0].cloneId[0]);" />
	&nbsp;-or-&nbsp;
<html:radio property="cloneId" value="Upload" styleClass="radio" onfocus="javascript:onRadio(this,1);" />
			<html:file property="cloneListFile" disabled="true"  onblur="javascript:cRadio(this, document.forms[0].cloneId[1]);" onfocus="javascript:document.forms[0].cloneId[1].checked = true;" />
			<!--<app:help help="Only files of type \"*.txt\" with each entry in a new line are accepted. Upper limit for this option is 500 entries in the txt file." /></br>-->
			<html:errors property="cloneId"/></br>
</fieldset>

