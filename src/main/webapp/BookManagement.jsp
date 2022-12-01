<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html lang="en">
<head>
<title>dashboard</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="laptop" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- Bootstrap Core CSS -->
<link href="css/bootstrap.min.css" rel='stylesheet' type='text/css' />
<!-- Custom CSS -->
<link href="css/style.css" rel='stylesheet' type='text/css' />
<link rel="stylesheet" href="css/morris.css" type="text/css"/>
<!-- Graph CSS -->
<link href="css/font-awesome.css" rel="stylesheet"> 
<!-- jQuery -->
<script src="js/jquery-2.1.4.min.js"></script>
<!-- //jQuery -->
<!-- <link href='//fonts.googleapis.com/css?family=Roboto:700,500,300,100italic,100,400' rel='stylesheet' type='text/css'/>
<link href='//fonts.googleapis.com/css?family=Montserrat:400,700' rel='stylesheet' type='text/css'> -->
<!-- lined-icons -->
<link rel="stylesheet" href="css/icon-font.min.css" type='text/css' />

<!-- tables -->
<link rel="stylesheet" type="text/css" href="css/table-style.css" />
<link rel="stylesheet" type="text/css" href="css/basictable.css" />
<script type="text/javascript" src="js/jquery.basictable.min.js"></script>

</head> 
<body>
   <div class="page-container">
   <!--/content-inner-->
<div class="left-content">
	   <div class="mother-grid-inner">
             <!--header start here-->
				<div class="header-main">
					
					
						 
						<div class="profile_details w3l">		
								<ul>
									<li class="dropdown profile_details_drop">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
											<div class="profile_img">
												<div class="user-name">
													<p>Thông tin</p>
													<span>Admin</span>
												</div>
												<i class="fa fa-angle-down"></i>
												<i class="fa fa-angle-up"></i>
												<div class="clearfix"></div>	
											</div>	
										</a>
										<ul class="dropdown-menu drp-mnu">
											<li> <a href="#"><i class="fa fa-cog"></i> Cài đặt</a> </li> 
											<li> <a href="#"><i class="fa fa-user"></i> Thông tin</a> </li> 
											<li> <a href="#"><i class="fa fa-sign-out"></i> Đăng xuất</a> </li>
										</ul>
									</li>
								</ul>
							</div>
							
				     <div class="clearfix"> </div>	
				</div>
<!--heder end here-->
		<ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="index1.html">Trang chủ</a><i class="fa fa-angle-right"></i>Sản phẩm</li>
            </ol>

<!-- grids -->
				<div class="grids">
				
					
				
					<div class="agile-calendar-grid">
						<div class="page">
							
							<div class="w3l-calendar-left">
								<div class="calendar-heading">
<!-- 									<p>Noi dung</p>
 -->								
 					<div class="agile-tables">
					<div class="w3l-table-info">
					  <h2>Thêm sản phẩm</h2>
					  <div class="row">
					  	<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
					  		
					  	</div>
					  	<div class="col-xs-10 col-sm-10 col-md-10 col-lg-10">
					  		<form action="">
							<div class="form-group">
							  <label for="tensach">Tên sách:</label>
							  <input type="text" class="form-control" id="tensach" name = "Book_title">
							</div>
							<div class="form-group">
						      <label for="comment">Mô tả:</label>
						      <textarea class="form-control" rows="5" id="comment" name = "Product_description"></textarea>
						    </div>
							
							<div class="form-group">
							  <label for="theloai">Thể Loại sách:</label>
							  <select name = "category_name" id="theloai" class="form-control">	
							  <c:forEach var="Category" items="${categorys}">
							  <option value="${Category.getCategory_name()}">${Category.getCategory_name()}</option>
							  </c:forEach>
							  </select>
							</div>
							<div class="form-group">
							  <label for="nhasb">Nhà xuất bản:</label>
							  <input type="text" class="form-control" id="nhasb" name="publisher">
							</div>
							<div class="form-group">
							  <label for="ngaysb">Ngày xuất bản:</label>
							  <input type="date" class="form-control" id="ngaysb" name="publish_date">
							</div>
							<div class="form-group">
							  <label for="author">Tác giả:</label>
							  <input type="textarea" class="form-control" id="author" name="authorname">
							</div>
							<div class="form-group">
							  <label for="gia">Giá bán:</label>
							  <input type="number" class="form-control" id="gia" name = "price">
							</div>
							<div class="form-group">
							  <label for="gia">Số lượng:</label>
							  <input type="number" class="form-control" id="quantity" name = "quantity">
							</div>
							<input type = "hidden" name = "action" value = "insert">
							<button type="submit" class="btn btn-default">Thêm sản phẩm</button>
							</form>
					  	</div>
					  	<div class="col-xs-1 col-sm-1 col-md-1 col-lg-1">
					  		
					  	</div>
					  </div>
					  <h2>Cập nhật sản phẩm</h2>
					    <table id="table">
						<thead >
						  <tr>
							<th>Mã sách</th>
							<th>Tên sách</th>
							<th style="text-align: center;">Mô tả</th>
							<th>Thể loại</th>
							<th>Nhà xuất bản</th>
							<th>Ngày xuất bản</th>
							<th>Tác giả</th>
							<th>Giá bán</th>
							<th>Số lượng</th>
							<th>Chỉnh sửa</th>
						  </tr>
						</thead>
						<tbody>
						<c:forEach var="ProductShow" items="${listProductShow}">
						   <tr class="info">
							<td>${ProductShow.getID_Product()}</td>
							<td>${ProductShow.getBook_title()}</td>
							<td>${ProductShow.getProduct_description()}</td>
							<td>${ProductShow.getCategory_name()}</td>
							<td>${ProductShow.getPublisher()}</td>
							<td>${ProductShow.getPublish_date()}</td>
							<td>${ProductShow.getAuthorname()}</td>
							<td>${ProductShow.getPrice()}</td>
							<td>${ProductShow.getQuantity()}</td>
							<td style="text-align: center;">
							<span >
								<a class="agile-icon" href="#" data-toggle="modal" data-target="#myModal"><i class="fa fa-pencil-square-o"></i></a>
							</span> 
							<span>
								<a class="agile-icon" href="#" data-toggle="modal" data-target="#myModal1"><i class="fa fa-times-circle"></i></a>
							</span>
							</td>
							</tr>
							</c:forEach>
						</tbody>
					  </table>
					</div>

  <!-- Modal -->
  <div class="modal fade" id="myModal" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Cập nhật thông tin sản phẩm</h4>
        </div>
        <div class="modal-body">
          <p>Mời bạn nhập thông tin sản phẩm: </p>
			<form action="">
				<div class="form-group">
			  <label for="masach">Mã sách:</label>
			  <input type="text" class="form-control" id="masach" name= "ID_Product2" readonly>
			</div>
			<div class="form-group">
			  <label for="tensach">Tên sách:</label>
			  <input type="text" class="form-control" id="tensach" name = "Book_title2">
			</div>
			<div class="form-group">
		      <label for="comment">Mô tả:</label>
		      <textarea class="form-control" rows="6" id="comment" name="Product_description2"></textarea>
		    </div>
			
			<div class="form-group">
				<label for="theloai">Thể Loại sách:</label>
					  <select name = "category_name2" id="theloai" class="form-control">	
						  <c:forEach var="Category" items="${categorys}">
							  <option value="${Category.getCategory_name()}">${Category.getCategory_name()}</option>
						  </c:forEach>
					  </select>
			</div>
			<div class="form-group">
			  <label for="nhasb">Nhà xuất bản:</label>
			  <input type="text" class="form-control" id="nhasb" name="publisher2">
			</div>
			<div class="form-group">
			  <label for="ngaysb">Ngày xuất bản:</label>
			  <input type="date" class="form-control" id="ngaysb" name="publish_date2">
			</div>
			<div class="form-group">
			  <label for="author">Tác giả:</label>
			  <input type="textarea" class="form-control" id="author" name="authorname2">
			</div>
			<div class="form-group">
			  <label for="gia">Giá bán:</label>
			  <input type="number" class="form-control" id="gia" name = "price2">
			</div>
			<div class="form-group">
			  <label for="gia">Số lượng:</label>
			  <input type="number" class="form-control" id="quantity" name ="quantity2">
			</div>
			<input type = "hidden" name = "action" value = "update"> 
			<button type="submit" class="btn btn-default">Cập nhật</button>
			</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- end mymodal -->
  <!-- sanpham -->
    <!-- Modal -->
  <div class="modal fade" id="myModal1" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Bạn có chắc chắn muốn xóa hay không?</h4>
        </div>
        <div class="modal-body">
          
			<form action="">
				<button type="submit" class="btn btn-default" style="margin-left: 43%;">Xóa
				</button>
			</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        </div>
      </div>
      
    </div>
  </div>
  <!-- end modal sanpham -->
								</div>
								</div>
							</div>
							
							<div class="clearfix"> </div>
						</div>
					</div>
				</div>
				<!-- //grids -->
<!--photoday-section-->	
			
                        
                    	<div class="col-sm-4 wthree-crd">
                            <div class="card">
                                <div class="card-body">
                                    
                                </div>
                            </div>
                        </div>
                        
						<div class="clearfix"></div>
                   
	<!--//photoday-section-->	
<!-- script-for sticky-nav -->
		<script>
		$(document).ready(function() {
			 var navoffeset=$(".header-main").offset().top;
			 $(window).scroll(function(){
				var scrollpos=$(window).scrollTop(); 
				if(scrollpos >=navoffeset){
					$(".header-main").addClass("fixed");
				}else{
					$(".header-main").removeClass("fixed");
				}
			 });
			 
		});
		</script>
		<!-- /script-for sticky-nav -->
<!--inner block start here-->
<div class="inner-block">

</div>
<!--inner block end here-->
<!--copy rights start here-->
<div class="copyrights">
	 <p>© 2018 N5QPT. All Rights Reserved  Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
</div>	
<!--COPY rights end here-->
</div>
</div>
  <!--//content-inner-->
			<!--/sidebar-menu-->
				<div class="sidebar-menu">
					<header class="logo1">
						<a href="#" class="sidebar-icon"> <span class="fa fa-bars"></span> </a> 
					</header>
						<div style="border-top:1px ridge rgba(255, 255, 255, 0.15)"></div>
                           <div class="menu">
									<ul id="menu" >
										<li><a href="index1.html"><i class="fa fa-tachometer"></i> <span>Trang chủ</span><div class="clearfix"></div></a></li>
										
										
										 <li id="menu-academico" ><a href="donhang.html"><i class="fa fa-envelope nav_icon"></i><span>Đơn hàng</span><div class="clearfix"></div></a></li>
									<!-- <li><a href="gallery.html"><i class="fa fa-picture-o" aria-hidden="true"></i><span>Gallery</span><div class="clearfix"></div></a></li> -->
									
									 <li id="menu-academico" ><a href="#"><i class="fa fa-list-ul" aria-hidden="true"></i><span>Sản phẩm</span> <span class="fa fa-angle-right" style="float: right"></span><div class="clearfix"></div></a>
										   <ul id="menu-academico-sub" >
										   <li id="menu-academico-avaliacoes" ><a href="sanpham.html">Quản lý sách</a></li>
											<li id="menu-academico-avaliacoes" ><a href="sanpham1.html">Loại sách</a></li>
										  </ul>
										</li>
								  </ul>
								</div>
							  </div>
							  <div class="clearfix"></div>		
							</div>
							<script>
							var toggle = true;
										
							$(".sidebar-icon").click(function() {                
							  if (toggle)
							  {
								$(".page-container").addClass("sidebar-collapsed").removeClass("sidebar-collapsed-back");
								$("#menu span").css({"position":"absolute"});
							  }
							  else
							  {
								$(".page-container").removeClass("sidebar-collapsed").addClass("sidebar-collapsed-back");
								setTimeout(function() {
								  $("#menu span").css({"position":"relative"});
								}, 400);
							  }
											
											toggle = !toggle;
										});
							</script>
<!--js -->
<script src="js/jquery.nicescroll.js"></script>
 <script src="js/scripts.js"></script>
<!-- Bootstrap Core JavaScript -->
   <script src="js/bootstrap.min.js"></script>
   <!-- /Bootstrap Core JavaScript -->	   
<!-- morris JavaScript -->	
<script src="js/raphael-min.js"></script>

</body>
</html>