$(function () {
	'use strict';

	$.fn.getKkpager = function() {
		kkpagerTrigger();
	}

	kkpagerTrigger();

	function kkpagerTrigger() {
		var page = $("#page").val();
		var pageCount = $("#pageCount").val();
		var allRecordCount = $("#allRecordCount").val();
		var actionName = $("#actionName").val();

		kkpager.pno = page;
		kkpager.total = pageCount;
		kkpager.totalRecords = allRecordCount;
		kkpager.generPageHtml({
			pno: page,
			//总页码
			total: pageCount,
			//总数据条数
			totalRecords: allRecordCount,
			mode : 'click',//默认值是link，可选link或者click
			click : function(n){
				// do something
				//手动选中按钮
				$("#page").val(n);
				this.selectPage(n);
				$().getList();
				return false;
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
	}

});
