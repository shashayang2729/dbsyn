<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	  <script type="text/javascript">

	  </script>
  </head>

  <BODY>
  <div style="position:absolute;left:500;top:100;width:500;height:120;border:1px solid blue;">
	  请输入/选择:
	  <input type=text id=re_name name=re_name style="width:150px;height:21px;font-size:10pt;"><span style="width:28px;border:0px solid red;">
<select id="r00" name="r00" style="margin-left:-150px;width:168px; background-colorFFEEEE;" onChange="document.all.re_name.value=this.value;">
	<option value="1">111</option>
	<option value="2">222</option>
	<option value="3">3</option>
</select>
</span>
  </div>
  </BODY>
</html>
