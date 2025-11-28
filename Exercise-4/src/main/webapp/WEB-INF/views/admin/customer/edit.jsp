<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var = "customerAPI" value = "/api/customer"/>
<c:url var = "buildingAPI" value = "/api/building"/>
<c:url var = "customerEditURL" value = "/admin/customer-edit"/>
<html>
<head>
    <title>Sửa thông tin khách hàng</title>
</head>
<body>
    <div class="main-content" id="main-container">
    		<div class="main-content">
    			<div class="main-content-inner">
    				<div class="breadcrumbs" id="breadcrumbs">
    					<script type="text/javascript">
    						try { ace.settings.check('breadcrumbs', 'fixed') } catch (e) { }
    					</script>

    					<ul class="breadcrumb">
    						<li>
    							<i class="ace-icon fa fa-home home-icon"></i>
    							<a href="#">Trang chủ</a>
    						</li>
    					<c:if test = "${empty customerEdit.id}">
    						<li class="active">Thêm khách hàng</li>
    					</c:if>
                        <c:if test = "${not empty customerEdit.id}">
    						<li class="active">Chỉnh sửa khách hàng</li>
    					</c:if>
    					</ul><!-- /.breadcrumb -->


    				</div>

    				<div class="page-content">


    					<div class="page-header">
    						<h1 style="font-family: 'Times New Roman', Times, serif;">
    							Thông tin khách hàng
    							<small>
    								<i class="ace-icon fa fa-angle-double-right"></i>
    								overview &amp; stats
    							</small>
    						</h1>
    					</div><!-- /.page-header -->

    					<div class=row>
    						<div class="col-xs-12">


    						</div>
    					</div>

    					<!-- Bảng danh sách -->
    					<div class= "row" style="font-family: 'Times New Roman', Times, serif;">
    					    <form:form modelAttribute = "customerEdit" id = "listForm" method = "GET">
                                <div class="col-xs-12">
    							<form class="form-horizontal" role="form" id = "form-edit">
    								<div class="form-group">
    									<label class="col-xs-3">Tên khách hàng</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "fullname"/>
    									</div>
    								</div>


    								<div class="form-group">
    									<label class="col-xs-3">Số điện thoại</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "customerPhone" name = "customerPhone" value = ""/>
    									</div>
    								</div>

    								<div class="form-group">
    									<label class="col-xs-3">Email</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "email" name = "email" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Tên công ty</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "companyName" name = "companyName" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Nhu cầu</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "demand" name = "demand"/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Tình trạng</label>
    									<div class="col-xs-9" >
                                            <form:select class="form-control" path = "status" name = "status" >
  											<form:option value="">----Chọn Tình Trạng----</form:option>
  											<form:options items = "${statuss}"/>
  								            </form:select>
  									    </div>
    								</div>

    								<div class="form-group">
    									<label class="col-xs-3"></label>
    									<div class="col-xs-9" >
    									<c:if test = "${not empty customerEdit.id}">
                                                <button class="btn btn-primary" type="button" id="btnAddOrUpdateCustomer">
    												Cập nhật thông tin
    											</button>
    											<button class="btn btn-primary" type="button" id = "btnCancel">
    												Hủy thao tác
    											</button>
    									</c:if>
                                        <c:if test = "${empty customerEdit.id}">
                                                <button class="btn btn-primary" type="button" id="btnAddOrUpdateCustomer">
    												Thêm khách hàng
    											</button>
    											<button class="btn btn-primary" type="button" id = "btnCancel">
    												Hủy thao tác
    											</button>
    									</c:if>
    									</div>
    								</div>
    								<form:hidden path = "id" id = "customerId"/>
    							</form>

    						</div>
    					    </form:form>
    					</div>
    				</div><!-- /.page-content -->
    <c:forEach var = "item" items = "${transactionType}">
        <div class = "col-xs-12">
            <div class "col-sm-12">
                <h3 class = "header smaller lighter blue">${item.value}</h3>
                <button class="btn btn-lg btn-primary" onclick = "transactionType('${item.key}',${customerEdit.id})">
                    <i class="orange ace-icon fa fa-location-arrow bigger-130"></i>Add
                </button>
            </div>
                    <c:if test="${item.key == 'CSKH'}">
                    <div class="col-xs-12" >
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${transactionListCSKH}" var="transaction">
                                    <tr>
                                        <td>${transaction.createdDate}</td>
                                        <td>${transaction.createdBy}</td>
                                        <td>${transaction.modifiedDate}</td>
                                        <td>${transaction.modifiedBy}</td>
                                        <td>${transaction.note}</td>
                                        <td>
                                            <button class="btn btn-xs btn-success" title="Sửa thông tin giao dịch" onclick="UpdateTransaction('${item.key}',${transaction.id})">
                                                <i class="ace-icon glyphicon glyphicon-list"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </c:if>
                    <c:if test="${item.key == 'DDX'}">
                    <div class="col-xs-12">
                        <div class="table-responsive">
                            <table class="table table-striped table-bordered table-hover">
                                <thead>
                                <tr>
                                    <th>Ngày tạo</th>
                                    <th>Người tạo</th>
                                    <th>Ngày sửa</th>
                                    <th>Người sửa</th>
                                    <th>Chi tiết giao dịch</th>
                                    <th>Thao tác</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${transactionListDDX}" var="transactionDDX">
                                    <tr>
                                        <td>${transactionDDX.createdDate}</td>
                                        <td>${transactionDDX.createdBy}</td>
                                        <td>${transactionDDX.modifiedDate}</td>
                                        <td>${transactionDDX.modifiedBy}</td>
                                        <td>${transactionDDX.note}</td>
                                        <td>
                                            <button class="btn btn-xs btn-success" title="Sửa thông tin giao dịch" onclick="UpdateTransaction('${item.key}',${transactionDDX.id})">
                                                <i class="ace-icon glyphicon glyphicon-list"></i>
                                            </button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                    </c:if>
        </div>

    </c:forEach>
  </div>
</div><!-- /.main-container -->

    	<div class="modal fade" id="transactionTypeModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
            <div class="modal-dialog">

              <!-- Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <h4 class="modal-title">Nhập giao dịch</h4>
                </div>
                <div class="modal-body" >
                  <div class = "form-group has-success">
                      <label for = "transactionDetail" class="col-xs-12 col-sm-3 control-label no no-padding-right"> Chi tiết giao dịch</label>
                      <div class = "col-xs-12 col-sm-9">
                      <span class="block input-icon-right">
                        <input type="text" id="transactionDetail" class="width-100" value="">
                      </span>
                      </div>
                  </div>
                  <input type = "hidden" id = "customerId" name = "customerId" value = "">
                  <input type = "hidden" id = "code" name = "code" value = "">
                  <input type = "hidden" id = "id" name = "id" value = "">
                  <input type="hidden" id="createdBy" name="createdBy" value=""/>
                  <input type="hidden" id="modifiedBy" name="modifiedBy" value=""/>
                  <input type="hidden" id="createdDate" name="createdDate" value=""/>
                  <input type="hidden" id="modifiedDate" name="modifiedDate" value=""/>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" id="btnAddOrUpdateTransaction">Thêm giao dịch</button>
        		    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
              </div>
            </div>
          </div>


    <script>
        function transactionType(code,customerId){
            $('#transactionTypeModal').modal();
            $('#customerId').val(customerId);
            $('#code').val(code);
        }

		$('#btnAddOrUpdateTransaction').click(function(e){
            e.preventDefault();
            var data = {};
            data['id'] = $('#id').val();
            data['customerId'] = $('#customerId').val();
            data['code'] = $('#code').val();
            data['note'] = $('#transactionDetail').val();

            addOrUpdateTransaction(data);
		});


		function addOrUpdateTransaction(data) {
		    var customerId = data['customerId'];
             $.ajax({
					type: "POST",
                    url: "${customerAPI}/transaction",
					data: JSON.stringify(data),
					contentType:"application/json",
					dataType: "JSON",
                    success: function (respond) {
                        console.log("success");
                        alert("thêm/sửa thành công")
                        window.location.href= '<c:url value="/admin/customer-edit-' + customerId + '?message=success"/>';
                    },
					error: function(respond){
						console.log("fail");
						console.log(respond);
						 window.location.href= '<c:url value="/admin/customer-edit-' + customerId + '?message=error"/>'
					}
             });
		}

		$('#btnAddOrUpdateCustomer').click(function() {
		    var data = {};
		    var formData = $('#listForm').serializeArray();
		    $.each(formData, function(i,v){
                data["" + v.name + ""] = v.value;
		    });
		    if (data['customerPhone'] != "" && data['fullName'] != ""){
		        addOrUpdateCustomer(data);
		    }
		    else {
               window.location.href= "<c:url value="/admin/customer-edit?nameorphone=require"/>";
		    }
		});
		function addOrUpdateCustomer(data) {
            $.ajax({
					type: "POST",
                    url: "${customerAPI}",
					data: JSON.stringify(data),
					contentType:"application/json",
					dataType: "JSON",
                    success: function (respond) {
                        console.log("success");
                        alert("sửa thành công")
                        window.location.href= '<c:url value="/admin/customer-list?message=success"/> ';
                    },
					error: function(respond){
						console.log("fail");
						console.log(respond);
					}
            });
		}
        function UpdateTransaction(code,id){
            var customerid = $('#customerId').val();
            $('#transactionTypeModal').modal();
            $('#code').val(code);
            $('#id').val(id);
            loadTransaction(id,customerid);
        }
		function loadTransaction (id,customerId){
		    $.ajax({
		        type: "GET",
		        url: "${customerAPI}/" + customerId + '/transaction/' + id,
		        dataType: "json",
		        success: function(response){
		            var note = response.note;
		            $('#transactionDetail').val(note);
		            console.info("Success");
		        },
                error: function(response){
                    console.log("Failed");
                    window.location.href= '<c:url value="/admin/customer-edit-' + customerId + '?message=failed"/>';
                    console.log(response);
                }
		    });
		}
		$('#btnCancel').click(function(){
		    window.location.href = "/admin/customer-list";
		});
	</script>
</body>
</html>