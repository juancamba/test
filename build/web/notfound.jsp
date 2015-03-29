<?xml version="1.0"?>
<!-- File: notfound.jsp -->

<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
  xmlns:html="http://struts.apache.org/tags-html"
  version="2.0">
<jsp:directive.page contentType="text/html"/>   
<jsp:output omit-xml-declaration="false"
 doctype-root-element="html"
 doctype-public="-//W3C//DTD XHTML 1.1//EN" 
 doctype-system="http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd" />
<jsp:useBean id="claimBean" class="com.webhomecover.beans.ClaimBean" scope="request" />
<html:html xhtml="true">
  <head>
      <link href="struts.css" rel="stylesheet" type="text/css" />
    <title>Policy not found</title>
  </head>
  <body>
    Policy number 
    <jsp:getProperty name="claimBean" property="policyNumber" />
    not found
  <hr />
  <html:link action="home">Home page</html:link>
  </body>
</html:html>
</jsp:root>
