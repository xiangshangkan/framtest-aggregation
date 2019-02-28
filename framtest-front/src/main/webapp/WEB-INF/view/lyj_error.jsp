<%@ page contentType="text/html; charset=UTF-8" isErrorPage="true" %>
<%@ page import="java.io.*" %>
<%@ page import="com.jjshome.bigdata.log.CommonUtil" %>
<%@ page import="com.jjshome.erp.entity.extras.Operator" %>
<html>
<head>
	<title>系统异常</title>

	<style>
		html,body{
			font-family: "Microsoft YaHei";
		}
		.container{
			width:1190px;
			margin:0 auto;
		}
		.cont_500{
			width:818px;
			height:409px;
			margin:100px auto;
			background:url(https://shenzhen.leyoujia.com/images/500.png) no-repeat;
		}
		.cont_500 p{
			text-align:center;
		}
		.error_box{
			float:left;
			width:100%;
			margin-top: 154px;
			margin-left: -27px;
			text-align:center;
		}
		.cont_500 .error{
			font-size:30px;
			color:#c0c0c0;
		}
		.cont_500 .xiufu{
			font-size:18px;
			color:#7d7d7d;
		}
		.cont_500 .kefu{
			font-size:18px;
			color:#2c2c2c;
		}
	</style>
</head>
<body>
<%
	//设置IM推送消息的用户ID
	Operator op = (Operator) request.getSession().getAttribute("operator");
	String wid = request.getParameter("wid");
	String url="";
	String msg="";
	String loc="";
	String md5="";
	String name="";
	String dept="";
	if(exception != null) {
		Throwable e = exception;
		for(int i=0;i<5;i++){
			e = e.getCause();
			url = CommonUtil.getUrl(e);
			if(url!=null&&(url.indexOf("jjshome")>-1||url.indexOf("freemarker")>-1)){
				msg = e.toString();
				System.out.println(msg);
				msg = msg.replaceAll("(\\r\\n|\\r|\\n|\\n\\r)","<\br>");
				System.out.println(msg);
				break;
			}
		}
		md5 = CommonUtil.getMD5(e.toString()+"-"+msg).toUpperCase();
	}

	String params = "";

	if(op!=null){
		name = op.getWorker();
		dept = op.getDeptName();

		params = "?worker-id="+op.getWorkerId();
	}

%>
<BR>
<div class="container">
	<div class="cont_500">
		<div class="error_box">
			<p class="error">服务器出错了</p>
			<p class="xiufu">非常抱歉！程序猿正在查询问题~~~</p>
			<p class="kefu">错误代码:<%=md5%></p>
			<input id="err_submit" style="height: 30px;" type="button" title="上传错误报告有利于IT人员快速定位问题" value="上传错误报告" onclick="submit();"/>

		</div>
	</div>
</div>
<script type="text/javascript" src="https://alicdn.leyoujia.com/js/common/jquery-min.js"></script>
<script type="text/javascript" src="https://static.jjshome.com/bigdata/js/t.min.js<%=params%>"></script>
<script>
    function formatDateTime() {
        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        m = m < 10 ? ('0' + m) : m;
        var d = date.getDate();
        d = d < 10 ? ('0' + d) : d;
        var h = date.getHours();
        h = h < 10 ? ('0' + h) : h;
        var minute = date.getMinutes();
        var second = date.getSeconds();
        minute = minute < 10 ? ('0' + minute) : minute;
        second = second < 10 ? ('0' + second) : second;
        return y + '-' + m + '-' + d+' '+h+':'+minute+':'+second;
    };
    var workernos='<%=wid%>';
    var url='<%=url%>';
    var msg='<%=msg%>';
    var md5='<%=md5%>1';
    var name='<%=name%>';
    var dept='<%=dept%>';
    var loc=window.location;
    var dat=formatDateTime();
    var content = "时间:"+dat+"<BR>";
    content += "网址:"+loc+"<BR>";
    content += "异常类型:"+msg+"<BR>";
    content += "异常代码:"+url+"<BR>";
    if(name!=''){
        content += "提交者:"+name+"("+dept+")<BR>";
    }
    content += "异常标识:"+md5+"<BR>";
    function submit(){
        $.ajax({
            type: "POST",
            url: "https://data.leyoujia.com/alarm/sendErrToIm",
            data:{"content":content,"workernos":workernos,"md5":md5},
            crossDomain: true,
            beforeSend: function(request) {
                request.setRequestHeader("POST-KEY", "7b5c9c67-1533-4824-8dd2-860d66a326d0");
            },
            success: function(result) {
                alert("谢谢您的反馈，我们会及时处理！");
                $("#err_submit").hide();
            }
        });
    }

    // 日志埋点
    window.onload = function(){
        _jjshome_t._set_event("P84660992","");
    }


</script>

</body>
</html>