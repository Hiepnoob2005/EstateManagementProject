<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var = "customerListUrl" value = "/admin/customer-list"/>
<c:url var = "customerAPI" value = "/api/customer"/>
<html>
<head>
<title> Chỉnh sửa thông tin </title>
</head>
<body>


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
    						<h1>
    							Danh sách khách hàng
    							<small>
    								<i class="ace-icon fa fa-angle-double-right"></i>
    								overview &amp; stats
    							</small>
    						</h1>
    					</div><!-- /.page-header -->

                    <div class=row>
						<div class="col-xs-12 col-sm-12 widget-container-col ui-sortable">
							<div class="widget-box">
								<div class="widget-header">
									<h5 class="widget-title">Tìm kiếm</h5>

									<div class="widget-toolbar">
										<a href="#" data-action="collapse">
											<i class="ace-icon fa fa-chevron-up"></i>
										</a>
									</div>
								</div>

								<div class="widget-body" style="font-family: 'Times New Roman', Times, serif;">
									<div class="widget-main">
									    <form:form id = "listForm" modelAttribute = "modelSearch" method = "GET" action = "${customerListUrl}">
                                        <div class="row">


 												<div class="form-group">
 													<div class="col-xs-12">
 													<div class="col-xs-4">
 														<label class="name">Tên khách hàng</label>
 														<form:input name = "name" class = "form-control" path = "fullname"/>
 													</div>
 													<div class="col-xs-4">
 														<label class="name">Số điện thoại</label>
 														<form:input class = "form-control" path = "phone"/>
 													</div>
 													<div class="col-xs-4">
 														<label class="name">Email</label>
 														<form:input class = "form-control" path = "email"/>
 													</div>
 													</div>
 												</div>
 												</div>
 											<div class="form-group">
 												<div class="col-xs-12">
 													<div class="col-xs-4">
                                                        <security:authorize access = "hasRole('MANAGER')">
                                                            <div>
                                                                <form:select class="form-control" path = "staffId" >
  															    <form:option value="">----Chọn Nhân viên----</form:option>
  															    <form:options items = "${listStaffs}"/>
  														        </form:select>
                                                            </div>
                                                        </security:authorize>
 													</div>
 												</div>
 											</div>
 											<div class="form-group">
 												<div class="col-xs-12">
 													<div class="col-xs-6">
 														<button class="btn btn-danger" id="btnSearchBuilding">
 															<i class="ace-icon glyphicon glyphicon-search"></i>
 															Tìm kiếm
 														</button>
 													</div>
 												</div>
 											</div>
 										</div>
									    </form:form>
									</div>
								</div>



							</div>
							<div class="pull-right">
							    <security:authorize access = "hasRole('MANAGER')">
							    <a href= "/admin/customer-edit">
                                    <button class="btn btn-info" title="Thêm khách hàng">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
										class="bi bi-building-add" viewBox="0 0 16 16">
										<path
											d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7m.5-5v1h1a.5.5 0 0 1 0 1h-1v1a.5.5 0 0 1-1 0v-1h-1a.5.5 0 0 1 0-1h1v-1a.5.5 0 0 1 1 0" />
										<path
											d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
										<path
											d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
									</svg>
								    </button>
							    </a>
                                </security:authorize>
                                <security:authorize access = "hasRole('MANAGER')">
								<button class="btn btn-danger" title="Xóa khách hàng" id = "btnDeleteCustomer">
									<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
										class="bi bi-building-dash" viewBox="0 0 16 16">
										<path
											d="M12.5 16a3.5 3.5 0 1 0 0-7 3.5 3.5 0 0 0 0 7M11 12h3a.5.5 0 0 1 0 1h-3a.5.5 0 0 1 0-1" />
										<path
											d="M2 1a1 1 0 0 1 1-1h10a1 1 0 0 1 1 1v6.5a.5.5 0 0 1-1 0V1H3v14h3v-2.5a.5.5 0 0 1 .5-.5H8v4H3a1 1 0 0 1-1-1z" />
										<path
											d="M4.5 2a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm-6 3a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5zm3 0a.5.5 0 0 0-.5.5v1a.5.5 0 0 0 .5.5h1a.5.5 0 0 0 .5-.5v-1a.5.5 0 0 0-.5-.5z" />
									</svg>
								</button>
								</security:authorize>
							</div>
						</div>
					</div>

    					<!-- Bảng danh sách -->
    				<div class = "row">
                        <div class="col-xs-12">
                            <div class = "table-responsive">
                                 <display:table name="customerList.listResult" cellspacing="0" cellpadding="0"
                                           requestURI="${customerListURL}" partialList="true" sort="external"
                                           size="${customerList.totalItems}" defaultsort="2" defaultorder="ascending"
                                           id="tableList" pagesize="${customerList.maxPageItems}"
                                           export="false"
                                           class="table table-fcv-ace table-striped table-bordered table-hover dataTable no-footer"
                                           style="margin: 3em 0 1.5em;">
                                    <display:column title = "<fieldset class='form-group'>
                                                            <input type='checkbox' id='checkAll' class='check-box-element'>
                                                            </fieldset>" class="center select-cell" headerClass="center select-cell">
                                        <fieldset>
                                            <input type = "checkbox" name = "checkList" value = "${tableList.id}"
                                                id = "checkbox_${tableList.id}" class = "check-box-element"/>
                                        </fieldset>
                                    </display:column>
                                    <display:column headerClass = "text-left" property = "fullName" title = "Tên khách hàng"/>
                                    <display:column headerClass = "text-left" property = "phone" title = "Di động"/>
                                    <display:column headerClass = "text-left" property = "email" title = "Email"/>
                                    <display:column headerClass = "text-left" property = "demand" title = "Nhu cầu"/>
                                    <display:column headerClass = "text-left" property = "createdBy" title = "Người thêm"/>
                                    <display:column headerClass = "text-left" property = "createdDate" title = "Ngày thêm"/>
                                    <display:column headerClass = "text-left" property = "status" title = "Tình trạng"/>
                                    <display:column headerClass="col-actions" title="Thao tác">
                                    <security:authorize access = "hasRole('MANAGER')">
                                    <button class="btn btn-xs btn-success" title="Giao khách hàng" onclick="assignmentCustomer(${tableList.id})">
                                        <i class="ace-icon glyphicon glyphicon-list"></i>
                                    </button>
                                    </security:authorize>
                                    <a class="btn btn-xs btn-info" title="Sửa thông tin" href="/admin/customer-edit-${tableList.id}" >
                                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                                    </a>
                                    <security:authorize access = "hasRole('MANAGER')">
                                        <button class="btn btn-xs btn-danger" title="Xóa khách hàng" onclick="deleteCustomer(${tableList.id})">
                                            <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                        </button>
                                    </security:authorize>
                                    </display:column>
                                 </display:table>
                            </div>
    					</div>
    				</div>

    			</div><!-- /.page-content -->
    			</div>
    		</div><!-- /.main-content -->



    		<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
    			<i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
    		</a>
    	</div><!-- /.main-container -->
    	<div class="modal fade" id="assignmentCustomerModal" role="dialog" style="font-family: 'Times New Roman', Times, serif;">
            <div class="modal-dialog">

              <!-- Modal content-->
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal">&times;</button>
                  <h4 class="modal-title">Danh sách nhân viên</h4>
                </div>
                <div class="modal-body" >
                  <table  class="table table-striped table-bordered table-hover" id = "staffList">
        							<thead>
        								<tr>
        									<th>
        										Chọn
        									</th>
        									<th>Tên Nhân Viên</th>

        								</tr>
        							</thead>

        							<tbody>

        							</tbody>
        						</table>
        						<input type="hidden" id="customerId" name="Customer" value="">
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" id="btnassignmentCustomer">Giao khách hàng</button>
        		    <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
                </div>
              </div>
            </div>
          </div>

          <script>
          		function assignmentCustomer(customerId) {
          			$('#assignmentCustomerModal').modal();
          			loadStaff(customerId);
          			$('#customerId').val(customerId);

          		}
          		function loadStaff(customerId){
                  $.ajax({
					type: "GET",
                    url: "${customerAPI}/" + customerId  + '/staffs',
					dataType: "JSON",
                    success: function (response) {
                        var row = "";
                        $.each(response.data, function(index, item){
                            row += '<tr>';
                            row += '<td class = "text-center"><input type = "checkbox" value = ' + item.staffId +' id = "checkbox_" ' + item.staffId + '" class ="check-box-element " '+ item.checked + '/></td>';
                            row += '<td class = "text-center">' + item.fullName + '</td>';
                            row += '</tr>';
                        });
                        $('#staffList tbody').html(row);
                        console.info("Success");
                    },
					error: function(response){
						console.log("fail");
						 window.location.href= '<c:url value="/admin/building-list?message=error"/> ';
						console.log(response);
					}
                 });
          		}
          		$('#btnassignmentCustomer').click(function(e){
          			e.preventDefault();
          			var data = {};
          			data['customerId'] = $('#customerId').val();
          			var staffs = $('#staffList').find('tbody input[type = checkbox]:checked').map(function(){
          				return $(this).val();
          			}).get();
          			data['staffs']  = staffs;
          			if (data['staffs'] != ''){
          			    assignment(data);
          			}
          			console.log("ok");
          		})

          		function assignment(data){
                  $.ajax({

                    url: "${customerAPI}/" + 'assignment',
                    type: "POST",
                    data: JSON.stringify(data),
                    contentType:"application/json",
					dataType: "JSON",
                    success: function (response) {
                        console.info("Success");
                        window.location.href= '<c:url value="/admin/building-list?message=success"/> ';
                    },
					error: function(response){
					    console.info("Giao ko thành công")
						 window.location.href= '<c:url value="/admin/building-list?message=error"/> ';
						console.log(response);
					}
                  });
          		}

          		$('#btnSearchCustomer').click(function(e){
          		    e.preventDefault();
          		    $('#listForm').submit();
          		});
          		function deleteCustomer(data){
          		    var customerId = [data];
          		    deleteCustomers(customerId)
          		}
                $('#btnDeleteCustomer').click(function(e){
                    e.preventDefault();
                    var data = {};
                    var customerIds = $('#tableList').find('tbody input[type = checkbox]:checked').map(function(){
                        return $(this).val();
                    }).get();
                    console.log("ok")
                    deleteBuildings(customerIds)
          		});
            function deleteCustomers(data){
                 $.ajax({
					type: "Delete",
                    url: "${customerAPI}/" + data,
					data: JSON.stringify(data),
					contentType:"application/json",
					dataType: "JSON",
                    success: function (respond) {
                        console.log("success");
                        alert("xóa thành công")
                        window.location.href= '<c:url value="/admin/customer-list?message=success"/> ';
                    },
					error: function(respond){
						console.log("fail");
						console.log(respond);
					}
                 });
          	}

          	</script>
</body>
</html>