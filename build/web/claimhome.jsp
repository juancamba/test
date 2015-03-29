<?xml version="1.0"?>
<!-- File: claimhome.jsp -->

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
      <title><bean:message key="home.title"/></title>
  </head>
  <body>
    <h2><bean:message key="home.heading"/></h2> 
    <div>
      click <html:link action="claimform">here</html:link> to enter your claim
    </div>
  </body>
 </html:html>
</jsp:root>