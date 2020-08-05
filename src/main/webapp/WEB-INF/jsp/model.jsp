<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade modal-primary msg-model" id="msg-model" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><span class="glyphicon glyphicon-exclamation-sign"
                                                    aria-hidden="true"></span></h4>
            </div>
            <div class="modal-body">
                <h4 id="modelContent"></h4>
            </div>
            <div class="msg-model-footer">
                <button class="btn btn-info" data-dismiss="modal">确 认</button>
            </div>
        </div>
    </div>
</div>
<div class="modal fade modal-primary msg-model" id="confirm-model" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title"><span class="glyphicon glyphicon-exclamation-sign"
                                                    aria-hidden="true"></span></h4>
            </div>
            <div class="modal-body">
                <h4 id="confirmContent"></h4>
            </div>
            <div class="modal-footer">
                <div>
                    <button class="btn btn-info btn-sm"  data-dismiss="modal" type="button" id="confirmBtn">确 定</button>
                    <button class="btn btn-info btn-sm" data-dismiss="modal">取 消</button>
                    <input type="hidden" id="identification">
                    <input type="hidden" id="param1">
                    <input type="hidden" id="param2">
                </div>
            </div>
        </div>
    </div>
</div>