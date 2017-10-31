<#macro modalIframe title="标题" src="about:blank" height=300 button1="关闭" button2="确定">
<div class="modal fade" id="modalIframe" tabindex="-1" role="dialog" aria-labelledby="modalIframeTitle" aria-hidden="false">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="modalIframeTitle">
					${title}
				</h4>
			</div>
			<div class="modal-body" style="padding: 0px;">
				<iframe width="100%" height="${height}" frameborder="0" scrolling="auto" src="${src}"></iframe>
			</div>
			<div class="modal-footer" style="margin-top: 0px;">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary">
					提交
				</button>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
</#macro>

<#macro adPromotionRechargeModal adPromotionId=0 balance=0.00>
<div class="modal fade" id="adPromotionRechargeModal" tabindex="-1" role="dialog" aria-labelledby="modelTitle" aria-hidden="true">
	<form action="adPromotionRecharge.htm" method="post" data-parsley-validate>
		<input type="hidden" value=${adPromotionId} name="adPromotionId">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="modelTitle">
						充值（当前余额￥ ${balance} 元）
					</h4>
				</div>
				<div class="modal-body" style="padding: 10px 30px 0px 30px;">
					<div class="input-group m-b">
	                  <span class="input-group-addon">￥</span>
	                  <input name="amount" type="text" class="form-control parsley-validated" data-parsley-type="integer" data-parsley-min="1" data-parsley-max="100000" required data-parsley-errors-container="#divRechargeError">
	                  <span class="input-group-addon">.00</span>
	                </div>
	                <div id="divRechargeError"></div>
				</div>
				<div class="modal-footer" style="margin-top: 0px;">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary">
						提交
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</form>
</div>
</#macro>

<#macro appSettlementModal appId=0 settlableDate="">
<div class="modal fade" id="appSettlementModal" tabindex="-1" role="dialog" aria-labelledby="modelTitle" aria-hidden="false">
	<form action="appSettle.htm" method="post" data-parsley-validate>
		<input type="hidden" value=${appId} name="appId">
		<input type="hidden" value="${settlableDate}" name="beginDate">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
						&times;
					</button>
					<h4 class="modal-title" id="modelTitle">
						结算 可结算日：${settlableDate!} 至昨天
					</h4>
				</div>
				<div class="modal-body" style="padding: 10px 30px 0px 30px;">
					<div class="input-group m-b">
	                  <span class="input-group-addon">结算至：</span>
	                  <input name="endDate" id="txtEndDate" type="text" class="form-control parsley-validated" required data-parsley-type="date" data-parsley-errors-container="#divDateError">
	                  
	                </div>
	                <div id="divDateError"></div>
				</div>
				<div class="modal-footer" style="margin-top: 0px;">
					<button type="button" class="btn btn-default" data-dismiss="modal">取消
					</button>
					<button type="submit" class="btn btn-primary">
						提交
					</button>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</form>
</div>
</#macro>