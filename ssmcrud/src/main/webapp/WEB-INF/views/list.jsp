<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%
		pageContext.setAttribute("AA_path",request.getContextPath());
	%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${AA_path}/static/bootstrap-3.3.7-dist/css/bootstrap.min.css" rel="stylesheet">
<script type="text/javascript" src="${AA_path}/static/js/jquery-1.12.4.min.js"></script>
<script src="${AA_path}/static/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<title>员工列表</title>
</head>
<body>
<div class="container">
	<div class="row">
		<div class="col-md-12"><h2>SSM-CRUD</h2></div>
	</div>
	<div class="row">
		<div class="col-md-4 col-md-offset-8">
			<button type="button" class="btn btn-primary">新增</button>
			<button type="button" class="btn btn-danger">删除</button>
		</div>
	</div>
	<div class="row">
		<div class="col-md-12">
			<table class="table">
				<tr>
					<th>#</th>
					<th>empName</th>
					<th>gender</th>
					<th>email</th>
					<th>deptName</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pageInfo.list}" var="emp">
					<tr>
						<th>${emp.empId}</th>
						<th>${emp.empName}</th>
						<th>${emp.gender}</th>
						<th>${emp.email}</th>
						<th>${emp.department.deptName}</th>
						<th>
							<button type="button" class="btn btn-primary">
								<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
								编辑
							</button>
							<button type="button" class="btn btn-danger">
								<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
								删除
							</button>
						</th>
					</tr>
				</c:forEach>	
			</table>
		</div>
	</div>
	<div class="row">
			<div class="col-md-6">
				当前${pageInfo.pageNum }页,总共${pageInfo.pages }页,总工${pageInfo.total }记录
			</div>
			<div class="col-md-6">
				<nav aria-label="Page navigation">
  					<ul class="pagination">
  						<li><a href="${AA_path }/emps?pn=1">首页</a></li>
  						<c:if test="${pageInfo.hasPreviousPage }">
  							<li>
      							<a href="${AA_path}/emps/?pn=${pageInfo.pageNum-1}" aria-label="Previous">
        							<span aria-hidden="true">&laquo;</span>
     					 		</a>
    						</li>
  						</c:if>
    					<c:forEach items="${pageInfo.navigatepageNums }" var="navigater">
    						<c:if test="${navigater==pageInfo.pageNum}">
    							<li class="active"><a href="#">${navigater}</a></li>
    						</c:if>
    						<c:if test="${navigater!=pageInfo.pageNum}">
    							<li><a href="${AA_path}/emps/?pn=${navigater}">${navigater}</a></li>
    						</c:if>
    					</c:forEach>
    					<c:if test="${pageInfo.hasNextPage }">
  							<li>
      							<a href="${AA_path}/emps/?pn=${pageInfo.pageNum+1}" aria-label="Next">
        							<span aria-hidden="true">&raquo;</span>
     					 		</a>
    						</li>
  						</c:if>
  						<li><a href="${AA_path }/emps?pn=${pageInfo.pages }">末页</a></li>
  					</ul>
				</nav>
			</div>
	</div>
</div>
</body>
</html>