<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="row" style="padding: 0px">
	<div class="col-md-12">
		<input type="hidden" name="page" id="page" value="${pageInfo.page}"/>
		<input type="hidden" name="pageCount" id="pageCount" value="${pageInfo.pageCount}"/>
		<input type="hidden" name="allRecordCount" id="allRecordCount" value="${pageInfo.allRecordCount}"/>
		<div id="kkpager"></div>
	</div>
</div>
