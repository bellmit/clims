$(function () {
	'use strict';

	var page = $("#page").val();
	var pageCount = $("#pageCount").val();
	var allRecordCount = $("#allRecordCount").val();
	var actionName = $("#actionName").val();

	kkpager.generPageHtml({
		pno: page,
		//总页码
		total: pageCount,
		//总数据条数
		totalRecords: allRecordCount,
		//链接前部
		hrefFormer: actionName,
		//链接尾部
		hrefLatter: '',
		getLink: function (page) {
			return this.hrefFormer + this.hrefLatter + "?" + "page=" + page + "&" + $("#consignationForm").serialize();
		}
		, lang: {
			firstPageText: '首页',
			firstPageTipText: '首页',
			lastPageText: '尾页',
			lastPageTipText: '尾页',
			prePageText: '上一页',
			prePageTipText: '上一页',
			nextPageText: '下一页',
			nextPageTipText: '下一页',
			totalPageBeforeText: '共',
			totalPageAfterText: '页',
			currPageBeforeText: '当前第',
			currPageAfterText: '页',
			totalInfoSplitStr: '/',
			totalRecordsBeforeText: '共',
			totalRecordsAfterText: '条数据',
			gopageBeforeText: '&nbsp;转到',
			gopageButtonOkText: '确 定',
			gopageAfterText: '页',
			buttonTipBeforeText: '第',
			buttonTipAfterText: '页'
		}
	});

	$("#addInformant").on("click", function () {
		$("#page").val(1);
		$('#consignationForm').submit();
	});

});
