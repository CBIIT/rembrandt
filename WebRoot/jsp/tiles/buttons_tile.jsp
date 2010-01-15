<%@page contentType="text/html"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
long randomness = System.currentTimeMillis(); //prevent image caching
%>
<script language="javascript">
document.forms[0].target = "_self";

var sURL = unescape(window.location.pathname);
var s = sURL.replace('/rembrandt/','');
function refresh()	{
    window.location.href = s;
}
</script>

<logic:notPresent name="deleteQueryForm">
<input type="button" id="clearButton" class="xbutton" value="clear" onclick="refresh();"/>
</logic:notPresent>

&nbsp;&nbsp;
<html:button styleClass="xbutton" property="method" value="cancel" onclick="javascript:alertUser('menu');" />
&nbsp;&nbsp;

<logic:present name="geneexpressionForm">
	<logic:equal name="geneexpressionForm" property="geneOption" scope="request" value="standard"> 
		<html:submit styleId="previewButton" styleClass="xbutton" property="method" onclick="return autoCheckAliases();">
			<bean:message key="buttons_tile.previewButton" />
		</html:submit>&nbsp;&nbsp;
 	</logic:equal>
	<logic:equal name="geneexpressionForm" property="geneOption" scope="request" value="geneList">
		<html:submit styleId="previewButton" styleClass="xbutton" property="method" onclick="return autoCheckAliases();">
			<bean:message key="buttons_tile.previewButton" />
		</html:submit>&nbsp;&nbsp;
 	</logic:equal>
</logic:present>
<logic:present name="comparitivegenomicForm">
	<logic:equal name="comparitivegenomicForm" property="geneOption" scope="request" value="standard">
		<html:submit styleId="previewButton" styleClass="xbutton" property="method">
			<bean:message key="buttons_tile.previewButton" />
		</html:submit>&nbsp;&nbsp;
	</logic:equal>
	<logic:equal name="comparitivegenomicForm" property="geneOption" scope="request" value="geneList">
		<html:submit styleId="previewButton" styleClass="xbutton" property="method" onclick="return autoCheckAliases();">
			<bean:message key="buttons_tile.previewButton" />
		</html:submit>&nbsp;&nbsp;
 	</logic:equal>
</logic:present>
<logic:present name="clinicaldataForm">
	<html:submit styleId="previewButton" styleClass="xbutton" property="method">
		<bean:message key="buttons_tile.previewButton" />
	</html:submit>&nbsp;&nbsp;
</logic:present>

<html:submit styleId="submittalButton" styleClass="subButton" property="method" onclick="return gecnSubmit();">
	<bean:message key="buttons_tile.submittalButton" />
</html:submit>

<html:submit styleId="multiUseButton" styleClass="subButtonInv" property="method"  onclick="return autoCheckAliases();">
	<bean:message key="buttons_tile.multiUseButton" />
</html:submit>
