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
	  <script>
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
			  	 var url="http://localhost:8084/dbsyn/TestWithdraw";
			     createXMLHttpRequest();                                //创建XMLHttpRequest对象
			     XMLHttpReq.open("post", url, true);
			  	XMLHttpReq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
			  //参数的传法
			  var amount = document.getElementById("amount0");
			  var fee = document.getElementById("fee0");
			  var data = "amount="+amount.value+"&fee="+fee.value;
			  var xml="<user><userid>asdfasdfasdf<\/userid><\/user>";//
			  var json={id:0,username:"好人"};//json对象，不是字符串吧
			  XMLHttpReq.onreadystatechange = processResponse; //指定响应函数
			     XMLHttpReq.send(data);
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
  
  <body>

  提多少啊老板<input type="text" id="amount0"><br>
  手续费呢<input type="text" id="fee0">
  <input type="button" value="hehe" onclick="sendAjaxRequest()">
<br><br>
  <form id="myform" method="post" accept-charset="UTF-8" action="https://test.credit2go.cn/fes/p2p/withhold">
	  <!--订单编号-->
	 订单编号： <input  value="" id="ORDERNO" name="ORDERNO"/><br>
	  <!--账户-->
	  电子账户： <input   value="${CARDNBR}" id="CARDNBR" name="CARDNBR"/><br>
	  <!--银行名称:-->
	  银行名称: <input  name="BANKNAME" id="BANKNAME" value="" /><br>
	  <!--绑定卡号:-->
	  绑定卡号: <input value="${CARD_BIND}" id="CARD_BIND" name="CARD_BIND"/><br>
	  <!--姓名:-->
	  姓名:<input name="NAME" id="NAME" value="${NAME}"  /><br>
	  <!--证件号码:-->
	  证件号码: <input  name="IDNO" id="IDNO" value="${IDNO}"  /><br>
	  <!--手机号码:-->
	  手机号码: <input name="PHONE" id="PHONE" value="${PHONE}"  /><br>
	  <!--提现金额:-->
	  提现金额:<input  name="AMOUNT" id="AMOUNT" value="${AMOUNT}"  /><br>
	  <!--手续费:-->
	  手续费:<input  name="FEE" id="FEE" value="${FEE}"  /><br>
	  <!--日期:-->
	  日期:<input  name="TRXDATE" id="TRXDATE" value="${TRXDATE}" /><br>
	  <!--忘记密码跳转链接:-->
	  忘记密码跳转链接: <input name="FORGERPWD_URL" id="FORGERPWD_URL" value="${FORGERPWD_URL}" /><br>
	  <!--交易页面跳转链接:-->
	  交易页面跳转链接: <input  name="TRANSACTION_URL" id="TRANSACTION_URL" value="${TRANSACTION_URL}" /><br>
	  <!--后台响应链接：-->
	  后台响应链接：<input  name="BACKGROUND_URL" id="BACKGROUND_URL" value="${BACKGROUND_URL}" /><br>
	  <!--银行代码-->
	  银行代码：<input  name="BANKCODE" id="BANKCODE" value="${BANKCODE}" /><br>
	  <!--合作单位编号-->
	  合作单位编号：<input  name="COINSTCODE" id="COINSTCODE" value="${COINSTCODE}" /><br>
	  <!--合作单位渠道-->
	  合作单位渠道：<input  name="COINSTCHANNEL" id="COINSTCHANNEL" value="${COINSTCHANNEL}" /><br>
	  <!--是否指定通道:Y-指定资金通道 N-ESB选择资金通道-->
	  是否指定通道:<input  name="ROUT_FLAG" id="ROUT_FLAG" value="${ROUT_FLAG}" /><br>
	  <!--通道标识:B3-行内资金通道 G3-大额资金通道 CI-银联代收付资金通道-->
	  通道标识:<input name="ROUT_CODE" id="ROUT_CODE" value="${ROUT_CODE}" /><br>
	  <!--***************************************************************************-->
	  <!--开户银行联行号:-->
	  开户银行联行号: <input  name="BANK_CNAPS" id="BANK_CNAPS" value="" /><br>
	  <!--开户银行代码:-->
	  开户银行代码: <input name="BANK_CODE" id="BANK_CODE" value="" /><br>
	  <!--开户银行英文缩写:-->
	  开户银行英文缩写: <input  name="BANK_NAME_EN" id="BANK_NAME_EN" value="" /><br>
	  <!--开户银行中文名称:-->
	  开户银行中文名称:<input name="BANK_NAME_CN" id="BANK_NAME_CN" value="" /><br>
	  <!--开户行省份:-->
	  开户行省份:<input  name="ISS_BANK_PROVINCE" id="ISS_BANK_PROVINCE" value="" /><br>
	  <!--开户行城市:-->
	  开户行城市:<input  name="ISS_BANK_CITY" id="ISS_BANK_CITY" value="" /><br>
	  <!--请求方保留:-->
	  请求方保留:<input  name="ACQRES" id="ACQRES" value="${ACQRES}" /><br>
	  <!--签名:-->
	  签名:<input name="SIGN" id="SIGN" value="" />
	  <input type="submit" value="来啦"/>
  </form>
  </body>
</html>
