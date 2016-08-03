<%@ page contentType="text/html; charset=UTF-8"%>
<style>
div a img{
	display:none;
}
</style>
<div class="container">
	<div class="row" style="margin-top:100px;">
		<div class="col-md-3"><a href="">帮助中心</a> | <a href="#">隐私条款</a> | <a href="#">关于我们</a></div>
	</div>
<%-- 	<div class="row" style="margin-top:100px;">
		<div class="col-md-3"><a href="">帮助中心</a>|<a href="#">隐私条款</a>|<a href="#">关于我们</a></div>
		<div class="col-md-3"></div>
		<div class="col-md-2"></div>
		<div class="col-md-4"><img src="<%=rootPath%>/images/logo2.jpg"  width="114" height="42" align="left"/>&copy;2009.INBAI Technology Co.Ltd<br />
	    粤ICP备09104593</div>
	</div> --%>

	<input type="hidden" id="userId" name="userId" value="${sessionScope.userInfo.id}"/>
	
	<!-- Baidu 分享 BEGIN --><!-- 'http://192.168.1.102:8888/brilliance/page/forwardToRegister?userId=${session.userInfo.id}', http:/<%=request.getContextPath()%>:8888/page/forwardToRegister?userId=${session.userInfo.id}-->
	<div id="bdshare" class="bdshare_t bds_tools_32 get-codes-bdshare"
		data="{
		'bdDes':'您的自定义分享摘要',	
		'text':'我发现了一个很不错的在线快递网站，大家都来看看吧！',
		'title':'您的自定义pop窗口标题',
		'pic':'您的自定义分享出去的图片',
		'bdComment':'您的自定义分享评论',
		'url':'http://192.168.1.102:8888/brilliance/page/forwardToRegister?userId=${sessionScope.userInfo.id}',
		'wbuid':'您的自定义微博 ID'
	}">
		<a class="bds_qzone"></a> 
		<a class="bds_tsina"></a> 
		<a class="bds_tqq"></a>
		<a class="bds_renren"></a> 
	</div>
	<script type="text/javascript" id="bdshare_js" data="type=tools&mini=1"></script>
	<script type="text/javascript" id="bdshell_js"></script>
	<script type="text/javascript">
		/**
		 * 在这里定义bds_config
		 */
		var bds_config = {};

		document.getElementById('bdshell_js').src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion="
				+ new Date().getHours();
	</script>
	<!-- Baidu 分享 END -->
	<!-- 判断点击了哪个分享 -->
	<script type="text/javascript">
		$(document).ready(function(){
			$("#bdshare a").on("click",function(){
				var userId = $("#userId").val();
				var newData = "";
				if($(this).attr("class")=="bds_qzone"){
					newData = "{'text':'我发现了一个很不错的在线快递网站，大家都来看看吧！','url':'http://192.168.1.102:8888/brilliance/page/forwardToRegister?source=2&userId="+userId+"'}";
				}else if($(this).attr("class")=="bds_tsina"){
					newData = "{'text':'我发现了一个很不错的在线快递网站，大家都来看看吧！','url':'http://192.168.1.102:8888/brilliance/page/forwardToRegister?source=1&userId="+userId+"'}";
				}else{
					newData = "{'text':'我发现了一个很不错的在线快递网站，大家都来看看吧！','url':'http://192.168.1.102:8888/brilliance/page/forwardToRegister?source=3&userId="+userId+"'}";
				}
				$("#bdshare").attr("data",newData);
				$.ajax({
					type: "POST",
					url: "../page/url/shareAdd",
					dataType: "json",
					data:{userId:userId,url:"http://192.168.1.102:8888/brilliance/page/forwardToRegister",type:1},
					success: function(jsonData){
						//alert("分享成功！");
					}
				});
			});
		});
	</script>


	<!-- 百度统计 -->
	<script type="text/javascript">
		var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://"
				: " http://");
		document
				.write(unescape("%3Cscript src='"
						+ _bdhmProtocol
						+ "hm.baidu.com/h.js%3F6d9bdfbfaeda343ea3d1759a94aef181' type='text/javascript'%3E%3C/script%3E"));
	</script>
	
</div>

