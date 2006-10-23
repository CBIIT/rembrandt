<%@ page language="java" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/rembrandt.tld"  prefix="app" %>
<script language="javascript" src="js/lib/scriptaculous/scriptaculous.js"></script>
<script type="text/javascript">Help.insertHelp("Logging_out", " align='right'", "padding:2px;");</script>
 <html:form action="logout.do">
	<fieldset class="gray">
		<legend class="red">
		Thank you for visiting the REMBRANDT application
		</legend>
		
		You cannot save the current session if you are logged in a guest user (RBTuser).
	<br /><br />
		<html:radio styleClass="radio" property="procedure" value="logoutSave" />
		Save my current session and logout. 
		<app:help help="This will save all queries and preferences from your current browser session in addition to queries from your previous session(s)." />
		<br />
		<html:radio styleClass="radio" property="procedure" value="logoutNoSave" />
		Do not save my current session and logout. 
		<app:help help="This will not save any queries or preferences from your current browser session." />
		<br/>
		<html:radio styleClass="radio" property="procedure" value="dontLogout" />
		Continue working in the application and do not logout. 
		
		<br /><br />
		<div id="surveyHeader" style="text-align:center; background-color:silver">
			<br/><a href="#" onclick="Effect.toggle('survey');return false;">Click Here to take our quick feedback survey...</a><br/><br/>
		</div>
		<div id="survey" style="display:none;border:2px dotted silver;border-top:1px solid silver;">
			<div style="margin:10px;">
			The feature I used/liked the most this session: 
			<select name="usedMost">
				<option>N/A</option>
				<option>Gene Exp. Simple Search</option>
			</select>
			<br/><br/>
			The feature I used/liked the least this session: 
			<select name="usedLeast">
				<option>N/A</option>
				<option>Gene Exp. Simple Search</option>
			</select>
			<br/><br/>
			General Feedback:<br/>
			<textarea style="width:90%" name="generalFeedback"></textarea>
			<br/>
			We appreciate your input.
			</div>
		</div>
		<br/>
		<div align="center">
		<html:submit styleClass="xbutton" />
		</div>
   </fieldset>
</html:form>




