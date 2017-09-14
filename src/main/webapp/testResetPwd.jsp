<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

	  <script type="application/javascript">
		  var XMLHttpReq;
		  function createXMLHttpRequest() {
			  try {
				  XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");//IE高版本创建XMLHTTP
			  }
			  catch(E) {
				  try {
					  XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");//IE低版本创建XMLHTTP
				  }
				  catch(E) {
					  XMLHttpReq = new XMLHttpRequest();//兼容非IE浏览器，直接创建XMLHTTP对象
				  }
			  }

		  }
		  function sendAjaxRequest() {
			  var url="http://localhost:8084/dbsyn/TestResetPwd";
			  createXMLHttpRequest();                                //创建XMLHttpRequest对象
			  XMLHttpReq.open("post", url, true);
			  XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			  //参数的传法
			  XMLHttpReq.onreadystatechange = processResponse; //指定响应函数
			  XMLHttpReq.send(null);
		  }
		  //回调函数
		  function processResponse() {

			  if (XMLHttpReq.readyState == 4) {
				  if (XMLHttpReq.status == 200) {
					  var text = XMLHttpReq.responseText;
					  //json字符串转json对象
					  var json1 =  JSON.parse(text);
					  // var json2 = text.parseJSON();???
					  //var obj = eval('(' + strJson字符串 + ')');
					  // alert(json1.ORDERNO);
					  for(var key in json1){
						  //alert(obj);
						  // alert(json1[obj]);
						  document.getElementById(key).value=json1[key];
					  }
					  /**
					   *实现回调
					   */
					  text = window.decodeURI(text);
					  var cp = document.getElementById("cp");
					  cp.innerHTML = "";
					  var values = text.split("|");
					  for (var i = 0; i < values.length; i++) {
						  var temp = document.createElement("option");
						  temp.text = values[i];
						  temp.value = values[i];
						  cp.options.add(temp);
					  }


				  }
			  }

		  }


	  </script>
  </head>
  
  <body onload="sendAjaxRequest();">

  <form  action="https://test.credit2go.cn/fes/p2p/pinset" method="post">

	  <li class="line"><label>账户:</label> <span><input type="text" style="width: 200px; height: 15px;" maxlength="19" autocomplete="off" value="${CARDNBR }" id="CARDNBR" name="CARDNBR" /></span></li>
	  <li class="line"><label>证件号码:</label> <span><input  name="IDNO" id="IDNO" type="text" maxlength="18" value="${IDNO }" /> </span></li>
	  <li class="line"><label>证件类型:</label> <span><input  name="IDTYPE" id="IDTYPE" type="text" maxlength="2" value="${IDTYPE }" /> </span></li>
	  <li class="line"><label>手机号码:</label> <span><input  name="PHONE" id="PHONE" type="text" maxlength="11" value="${PHONE }" /> </span></li>
	  <li class="line"><label>姓名:</label> <span><input  name="NAME" id="NAME" value="${NAME }" type="text" maxlength="60" /> </span></li>
	  <li class="line"><label>银行代码:</label> <span><input  name="BANKCODE" id="BANKCODE" value="${BANKCODE }" type="text" maxlength="12" /> </span></li>
	  <li class="line"><label>合作单位编号:</label> <span><input  name="COINSTCODE" id="COINSTCODE" value="${COINSTCODE }" type="text" /> </span></li>
	  <li class="line"><label>合作单位渠道:</label> <span><input  name="COINSTCHANNEL" id="COINSTCHANNEL" value="${COINSTCHANNEL }" type="text" /> </span></li>
	  <li class="line"><label>密码设置成功跳转链接:</label> <span><input  name="RESETPWD_SURL" id="RESETPWD_SURL" value="${RESETPWD_SURL }" type="text" /> </span></li>
	  <li class="line"><label>密码设置失败跳转链接:</label> <span><input  name="RESETPWD_FURL" id="RESETPWD_FURL" value="${RESETPWD_FURL }" type="text" /> </span></li>
	  <li class="line"><label>后台响应链接:</label> <span><input  name="BACKGROUND_URL" id="BACKGROUND_URL" value="${BACKGROUND_URL }" type="text" /> </span></li>
	  <li class="line"><label>请求方保留:</label> <span><input  name="ACQRES" id="ACQRES" value="${ACQRES }" type="text" /> </span></li>
	  <li class="line"><label>签名:</label> <span><input  name="SIGN" id="SIGN" value="${SIGN }" type="text" /> </span></li>
		<input type="submit" value="come on">
  </form>
  </body>
</html>
