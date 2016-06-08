$("#detail").click(function(e) {
	var id = $(this).attr("value");
	$("#popover-modal-body").text("数据正在加载中...");
	$('#popover-modal').modal('show');
	$.get("/product/get.do?id=" + id, function(data) {
		ajaxResult(data, function(content) {
			$.popoverModal({
				title : "商品详细信息",
				content : content,
				model : [ [ {
					label : "ID",
					key : "id"
				}, {
					label : "商品名称",
					key : "name",
				} ], [ {
					label : "关键词",
					key : "tags"
				}, {
					label : "推荐指数",
					key : "rating"
				} ], [ {
					label : "品牌",
					key : "brandId"
				}, {
					label : "业务类型",
					key : "businessType"
				} ], [ {
					label : "行邮税税率",
					key : "postTaxRate"
				}, {
					label : "结算规则",
					key : "paymentRule"
				} ], [ {
					label : "简介",
					key : "shortDescription"
				}, {
					label : "描述",
					key : "description"
				} ], [ {
					label : "上架",
					key : "active"
				}, {
					label : "精选",
					key : "selected"
				} ], [ {
					label : "是否置顶",
					key : "istop"
				}, {
					label : "三级分类",
					key : "categoryId"
				} ], [ {
					label : "参考价",
					key : "referencePrice"
				}, {
					label : "创建人",
					key : "operatorId"
				} ]

				, [ {
					label : "发布时间",
					key : "publishTime"
				}, {
					label : "精选时间",
					key : "choiceTime",

				} ]

				, [ {
					label : "每日限购",
					key : "limitPurchase"
				}, {
					label : "推荐标题",
					key : "recommendTitle",
				} ], [ {
					label : "商品备注",
					key : "commentOptionId",
				}, {
					label : "商品属性",
					key : "property"
				} ]

				, [ {
					label : "特卖商品标签1",
					key : "specialLabel",
				}, {
					label : "特卖商品标签2",
					key : "specialLabel2",
				} ], [ {
					label : "特卖商品标签3",
					key : "specialLabel3",
				}, {
					label : "顶数",
					key : "support"
				} ], [ {
					label : "踩数",
					key : "oppose"
				}, {
					label : "发货渠道",
					key : "expressChannel"
				} ], [ {
					label : "pc推荐",
					key : "pcRecommend"
				}, {
					label : "推荐语",
					key : "recommendText"
				} ], [ {
					label : "税费提示",
					key : "taxTip"
				}, {
					label : "创建时间",
					key : "createTime"
				} ]

				, [ {
					label : "采购人",
					key : "purchaserId"
				}, {
					label : "发货城市",
					key : "expressCity"
				} ]

				, [ {
					label : "修改时间",
					key : "modified"
				} ], ]
			});
		});
	})
});
