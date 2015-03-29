<?xml version="1.0"?>
<!-- File: claimdetails.jsp -->

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"	
   xmlns:html="http://struts.apache.org/tags-html" 		
   xmlns:bean="http://struts.apache.org/tags-bean" 		
   version="2.0">
<jsp:directive.page contentType="text/html"/>   
<jsp:output omit-xml-declaration="false"
 doctype-root-element="html"
 doctype-public="-//W3C//DTD XHTML 1.1//EN" 
 doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />
 <html:html xhtml="true">
  <head>
   <link href="struts.css" rel="stylesheet" type="text/css" />
   <title><bean:message key="claim.title" /></title>
  </head>
  <body>
   <h1><bean:message key="claim.heading" /></h1>
   <h2><bean:message key="claim.subheading" /></h2>
   <html:form action="/processclaim" > 
    <table>
     <tr>
      <td><bean:message key="label.policynumber" /></td>
      <td><html:text property="policyNumber" size="10" /></td><td><html:errors property="policyNumber" /></td>
     </tr>
     <tr>
      <td><bean:message key="label.amount" /></td>
      <td><html:text property="amount" size="10" /></td><td><html:errors property="amount" /></td>
     </tr>
     <tr>
      <td></td>
      <td>
       <html:radio property="type" value="buildings"/>
       <bean:message key="claim.buildingsradioprompt" />
      </td>
     </tr>
     <tr>
      <td></td>
      <td>
       <html:radio property="type" value="contents"/>
       <bean:message key="claim.contentsradioprompt" />
      </td>
     </tr>
     <tr>
      <td></td>
      <td><html:errors property="type" /></td>
     </tr>
     <tr>
      <td><bean:message key="label.description" /></td>
      <td><html:textarea property="description" rows="5" cols="30" /></td><td><html:errors property="description" /></td>
    </tr>
   <tr>
    <td></td>
    <td><html:submit><bean:message key="label.submit" /></html:submit></td>
   </tr>
  </table>
  </html:form>
  </body>
 </html:html>
</jsp:root>
