<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var = "buildingAPI" value = "/api/building"/>
<html>
<head>
    <title>Sửa tòa nhà</title>
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
    						<li class="active">Thêm khách hàng</li>
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
    					    <form:form modelAttribute = "buildingEdit" id = "listForm" method = "GET">
                                <div class="col-xs-12">
    							<form class="form-horizontal" role="form" id = "form-edit">
    								<div class="form-group">
    									<label class="col-xs-3">Tên khách hàng</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "name"/>
    									</div>
    								</div>


    								<div class="form-group">
    									<label class="col-xs-3">Số điện thoại</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "phone" name = "phone" value = ""/>
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
    										<form:input class = "form-control" path = "companyname" name = "companyname" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Nhu cầu</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "demand" name = "demand"/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Diện tích sàn</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "floorArea" name = "floorArea" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Tình trạng</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "status" name = "status" value = ""/>
    									</div>
    								</div>

    								<div class="form-group">
    									<label class="col-xs-3"></label>
    									<div class="col-xs-9" >
    									<c:if test = "${not empty buildingEdit.id}">
                                                <button class="btn btn-primary" type="button" id="btnAddOrUpdateBuilding">
    												Cập nhật khách hàng
    											</button>
    											<button class="btn btn-primary" type="button" id = "btnCancel">
    												Hủy thao tác
    											</button>
    									</c:if>
                                        <c:if test = "${empty buildingEdit.id}">
                                                <button class="btn btn-primary" type="button" id="btnAddOrUpdateBuilding">
    												Thêm khách hàng
    											</button>
    											<button class="btn btn-primary" type="button" id = "btnCancel">
    												Hủy thao tác
    											</button>
    									</c:if>
    									</div>
    								</div>
    								<form:hidden path = "id" id = "buildingId"/>
    							</form>

    						</div>
    					    </form:form>
    					</div>
    				</div><!-- /.page-content -->
    			</div>
</div><!-- /.main-container -->
    <script>
        var imageBase64 = '';
        var imageName = '';
		$('#btnAddOrUpdateCustomer').click(function(){

			var data = {};
			var typeCode = [];
			var formData = $('#listForm').serializeArray();
			$.each(formData,function(i,v){
				if (v.name!= 'typeCode'){
					data["" + v.name + ""] = v.value;
				}
			})




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
                        alert("thêm/sửa thành công")
                        window.location.href= '<c:url value="/admin/customer-list?message=success"/> ';
                    },
					error: function(respond){
						console.log("fail");
						console.log(respond);
					}
             });
		}
		$('#btnCancel').click(function(){
		    window.location.href = "/admin/customer-list";
		});


	</script>
</body>
</html>