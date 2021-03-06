<%--L
  Copyright (c) 2006 SAIC, SAIC-F.

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/rembrandt/LICENSE.txt for details.
L--%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="gov.nih.nci.rembrandt.util.RembrandtConstants,
					gov.nih.nci.caintegrator.security.UserCredentials" %>
					
<!--header NCI logo-->
<table width="765" align="center" border="0" cellspacing="0" cellpadding="0" bgcolor="#A90101" summary="This table is used to format page content">
<tr>
<th></th><th></th><th></th>
</tr>
<tr bgcolor="#A90101">
<td width="283" height="37" align="left"><a href="http://www.cancer.gov"><img alt="National Cancer Institute" src="images/logotype.gif" width="283" height="37" border="0"></a></td>
<td>&nbsp;</td>
<td width="295" height="37" align="right"><a href="http://www.cancer.gov"><img alt="National Institute of Health" src="images/tagline.gif" width="295" height="37" border="0"></a></td>
</tr>
</table>

<div align="center" style="padding:0px;">
<!--<p style="text-align:right; padding:0px">
Welcome&nbsp;
<B><% out.println(session.getAttribute("name")); %></b>&nbsp;|&nbsp;
<a href="logout.jsp">Logout</a>
</p>-->

<!--header REMBRANDT image map-->

<div style="width:765px; border-bottom: 1px solid #000000; margin:0px;">
<map name="headerMap">

<%
UserCredentials credent = (UserCredentials)session.getAttribute(RembrandtConstants.USER_CREDENTIALS);
if (credent == null || (credent.getUserName() != null && credent.getUserName().equalsIgnoreCase("RBTuser"))) {	
%>
	<s:url action="guestHome" namespace="/" id="aURL"></s:url>
	<area alt="REMBRANDT application logo" coords="7,8,272,50" href="guestHome.action">
	
<%} else {%>
	<s:url action="menu" namespace="/" id="aURL"></s:url>
	<area alt="REMBRANDT application logo" coords="7,8,272,50" href="menu.action">
<%} %>

</map>
<img src="images/header.jpg" width="765" height="65" alt="REMBRANDT application logo" border="0" usemap="#headerMap">
</div>
<!--end all headers-->

