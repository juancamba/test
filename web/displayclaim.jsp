<?xml version="1.0"?>
<!-- File: displayclaim.jsp -->

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"	
 xmlns:c="http://java.sun.com/jsp/jstl/core"
 xmlns:html="http://struts.apache.org/tags-html"
 xmlns:bean="http://struts.apache.org/tags-bean" 
 version="2.0">
<jsp:directive.page contentType="text/html"/>  
<jsp:output omit-xml-declaration="false"
 doctype-root-element="html"
 doctype-public="-//W3C//DTD XHTML 1.1//EN" 
 doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />
 <jsp:useBean id="claim" class="com.webhomecover.model.Claim" scope="request" />
 <jsp:useBean id="policy" class="com.webhomecover.model.Policy" scope="request" />
 <html:html xhtml="true">
  <head>
      <link href="struts.css" rel="stylesheet" type="text/css" />
    <title>Insurance Claim</title>
  </head>
  <body>
   <h2>Thank you for making your insurance claim</h2>
   <p>Please take a note of the reference number below</p>
   Policy Number: <strong><bean:write name="policy" property="policyNumber"/></strong><br />
   Reference:<strong><jsp:getProperty name="claim" property="reference"/></strong><br />
   Date of claim: <strong><jsp:getProperty name="claim" property="claimDate"/></strong><br />
   Policy start date: <strong><jsp:getProperty name="policy" property="startDate"/></strong><br />
   Is policy paid up?: <strong><jsp:getProperty name="policy" property="paidUp"/></strong><br />
   Annual premium: <strong><jsp:getProperty name="policy" property="annualPremium"/></strong><br />
   Amount of claim: <strong><jsp:getProperty name="claim" property="amount"/></strong><br />
   Type of policy: <strong><jsp:getProperty name="claim" property="type"/></strong><br />
   Description: <strong><jsp:getProperty name="claim" property="description"/></strong>
   <hr />
   <html:link action="home">Home page</html:link>
  </body>
</html:html>
</jsp:root>


