
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>

<DIV class="title">	Prior Therapy</DIV>

<DIV class="title">	Survival Range</DIV>
		<%
	     String act = request.getParameter("act");
	     System.out.println(act);
        %>
	<!-- <html:form action="<%=act%>" method="get"> -->
<input type="checkbox" property="radiation" class="radio"
 onclick="javascript:document.forms[0].rad.disabled=(!(document.forms[0].rad.disabled));">Radiation&nbsp;&nbsp;
<!--- <select property="radiationType" name="rad" disabled="true">
	<option>any</option>
	<option>photon</option>
</select> --->

<html:select property="radiationType">
<html:optionsCollection property="radiationTypeColl" />
</html:select><html:errors property="radiationType"/>

<br>
<input property="chemo" type="checkbox" class="radio"
 onclick="javascript:document.forms[0].chemo.disabled=(!(document.forms[0].chemo.disabled));">Chemo&nbsp;&nbsp;
- agent:&nbsp;
<!--- <select property="chemoType" name="chemo" disabled="true">
	<option>any</option>
	<option></option>
</select> --->

<html:select property="chemoType">
<html:optionsCollection property="chemoAgentTypeColl" />
</html:select><html:errors property="chemoType"/>

<Br>
<input property="sugery" type="checkbox" class="radio"
 onclick="javascript:document.forms[0].surgery.disabled=(!(document.forms[0].surgery.disabled));">Surgery&nbsp;&nbsp;
<!--- <select property="surgeryType" name="surgery" disabled="true">
	<option>any</option>
	<option>Complete Resection (CR)</option>
	<option>Partial Resection (PR)</option>
	<option>Biopsy Only (BX)</option>
</select> --->

<html:select property="surgeryType">
<html:optionsCollection property="surgeryTypeColl" />
</html:select><html:errors property="surgeryType"/>

<!-- </html:form> -->
