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
    							<a href="#">Home</a>
    						</li>
    						<li class="active">Dashboard</li>
    					</ul><!-- /.breadcrumb -->


    				</div>

    				<div class="page-content">


    					<div class="page-header">
    						<h1 style="font-family: 'Times New Roman', Times, serif;">
    							Dashboard
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
    									<label class="col-xs-3">Tên tòa nhà</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "name"/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Quận</label>
    									<div class="col-xs-3">
                                                 <form:select class="form-control" path = "district" >
 													    <form:option value="">----Chọn Quận----</form:option>
 													    <form:options items = "${districts}"/>
 												</form:select>
    									</div>
    								</div>

    								<div class="form-group">
    									<label class="col-xs-3">Phường</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "ward" name = "ward" value = ""/>
    									</div>
    								</div>

    								<div class="form-group">
    									<label class="col-xs-3">Đường</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "street" name = "street" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Kết cấu</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "structure" name = "structure" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Số tầng hầm</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "numberOfBasement" name = "numberOfBasement"/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Diện tích sàn</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "floorArea" name = "floorArea" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Hướng</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "direction" name = "direction" value = ""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Hạng</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "level" name = "level" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Diện tích thuê</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "rentArea" name = "rentArea" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Gía thuê</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "rentPrice" name = "rentPrice" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Mô tả giá</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "rentpricedescription" name = "rentPriceDescription" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Phí dịch vụ</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "serviceFee" name = "serviceFee" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Phí ô tô</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "carFee" name = "carFee" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Phí mô tô</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "motobikeFee" name = "motobikeFee" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Phí ngoài giờ</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "overtimeFee" name = "overtimeFee" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Tiền điện</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "electricityFee" name = "electricityFee" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Đặt cọc</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "deposit" name = "deposit" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Thanh toán</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "payment" name = "payment" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Thời hạn thuê</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "rentTime" name = "rentTime" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Thời gian trang trí</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "decorationTime" name = "decorationTime" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Tên quản lý</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "managerName" name = "managerName" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">SĐT quản lý</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "managerPhone" name = "managerPhone" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Phí môi giới </label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "brokerageFee" name = "brokerageFee" value =""/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Loại tòa nhà</label>
    									<div class="col-xs-9" >
    										<form:checkboxes items= "${typeCodes}" path = "typeCode"/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3">Ghi chú</label>
    									<div class="col-xs-9" >
    										<form:input class = "form-control" path = "note" name = "note"/>
    									</div>
    								</div>
    								<div class="form-group">
    									<label class="col-xs-3"></label>
    									<div class="col-xs-9" >
    									<c:if test = "${not empty buildingEdit.id}">
                                                <button class="btn btn-primary" type="button" id="btnAddOrUpdateBuilding">
    												Cập nhật tòa nhà
    											</button>
    											<button class="btn btn-primary" type="button" id = "btnCancel">
    												Hủy thao tác
    											</button>
    									</c:if>
                                        <c:if test = "${empty buildingEdit.id}">
                                                <button class="btn btn-primary" type="button" id="btnAddOrUpdateBuilding">
    												Thêm tòa nhà
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
		$('#btnAddOrUpdateBuilding').click(function(){

			var data = {};
			var typeCode = [];
			var formData = $('#listForm').serializeArray();
			$.each(formData,function(i,v){
				if (v.name!= 'typeCode'){
					data["" + v.name + ""] = v.value;
				}
				else {
					typeCode.push(v.value);
				}
			})
			data['typeCode'] = typeCode;
			console.log("ok");


			//call api
            if (typeCode != ''){
                addOrUpdateBuilding(data);
            }
            else {
                window.location.href = "/admin/building-edit?typeCode=required";
            }
		});


		function addOrUpdateBuilding(data) {
             $.ajax({
					type: "POST",
                    url: "${buildingAPI}",
					data: JSON.stringify(data),
					contentType:"application/json",
					dataType: "JSON",
                    success: function (respond) {
                        console.log("success");
                        alert("sửa thành công")
                        window.location.href= '<c:url value="/admin/building-list?message=success"/> ';
                    },
					error: function(respond){
						console.log("fail");
						console.log(respond);
					}
             });
		}
		$('#btnCancel').click(function(){
		    window.location.href = "/admin/building-list";
		});

	</script>
</body>
</html>