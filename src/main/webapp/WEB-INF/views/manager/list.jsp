<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<html>

<head></head>
<body>
<div class="container">
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">계정 관리 페이지</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">
				<button id='regBtn' type="button" class="btn btn-xs pull-right">계정 생성</button>
			</div>

			<!-- /.panel-heading -->
			<div class="panel-body">
				<table class="table table-striped table-bordered table-hover">
					<thead>
						<tr>
							<th>사번</th>
							<th>부서명</th>
							<th>이름</th>
							<th>직급</th>
							<th>입사일</th>
						</tr>
					</thead>

					<c:forEach items="${list}" var="employee">
						<tr>
							<td><a class='move' href='<c:out value="${employee.emp_id}"/>'>
							<c:out value="${employee.emp_id}" /></a></td>
						<td><c:out value="${employee.dept_name}"/></td>
							<td><c:out value="${employee.name}" /></td>
							<td><c:out value="${employee.position}" /></td>
							<td><c:out value="${employee.entry_date}" /></td>
				
						</tr>
					</c:forEach>
				</table>

				 <div class='row'>
					<div class="col-lg-12">

						<form id='searchForm' action="/manager/list" method='get'>
							<select name='type'>
								<option value=""
									<c:out value="${pageMaker.cri.type == null?'selected':''}"/>>--</option>
								<option value="E"
									<c:out value="${pageMaker.cri.type eq 'E'?'selected':''}"/>>사번</option>
								<option value="D"
									<c:out value="${pageMaker.cri.type eq 'D'?'selected':''}"/>>부서명</option>
								<option value="N"
									<c:out value="${pageMaker.cri.type eq 'N'?'selected':''}"/>>이름</option>
								<option value="ED"
									<c:out value="${pageMaker.cri.type eq 'ED'?'selected':''}"/>>사번
									or 부서명</option>
								<option value="EN"
									<c:out value="${pageMaker.cri.type eq 'EN'?'selected':''}"/>>사번
									or 이름</option>
								<option value="EDN"
									<c:out value="${pageMaker.cri.type eq 'EDN'?'selected':''}"/>>사번
									or 부서명 or 이름</option>
							</select> <input type='text' name='keyword'
								value='<c:out value="${pageMaker.cri.keyword}"/>' /> <input
								type='hidden' name='pageNum'
								value='<c:out value="${pageMaker.cri.pageNum}"/>' /> <input
								type='hidden' name='amount'
								value='<c:out value="${pageMaker.cri.amount}"/>' />
							<button class='btn btn-default'>검색</button>
						</form>
					</div>
				</div>


	 			<div class="pull-right">
					<ul class="pagination">
						<c:if test="${pageMaker.prev}">
							<li class="paginate_button previous"><a href="${pageMaker.startPage -1}">Previous</a></li>
						</c:if>

						<c:forEach var="num" begin="${pageMaker.startPage}" end="${pageMaker.endPage}">
							<li class="paginate_button  ${pageMaker.cri.pageNum == num ? "active":""} ">
								<a href="${num}">&ensp;${num}&ensp;</a>
							</li>
						</c:forEach>

						<c:if test="${pageMaker.next}">
							<li class="paginate_button next"><a href="${pageMaker.endPage +1 }">Next</a></li>
						</c:if>
					</ul>
				</div>
				<!--  end Pagination -->
			</div>

			<form id='actionForm' action="/manager/list" method='get'>
				<input type='hidden' name='pageNum' value='${pageMaker.cri.pageNum}'>
				<input type='hidden' name='amount' value='${pageMaker.cri.amount}'>
				<input type='hidden' name='type'
					value='<c:out value="${ pageMaker.cri.type }"/>'> 
					<input type='hidden' name='keyword'
					value='<c:out value="${ pageMaker.cri.keyword }"/>'>


			</form>


			<!-- Modal  추가 -->
			<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
				aria-labelledby="myModalLabel" aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-hidden="true">&times;</button>
							<h4 class="modal-name" id="myModalLabel">success!</h4>
						</div>
						<div class="modal-body">처리가 완료되었습니다.</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-default"
								data-dismiss="modal" id="rgmdBtn">Close</button>
							<button type="button" class="btn btn-primary"  id="rgmdBtn">Save Changes</button>
						</div>
					</div>
					<!-- /.modal-content -->
				</div>
				<!-- /.modal-dialog -->
			</div>
			<!-- /.modal -->


		</div>
		<!--  end panel-body -->
	</div>
	<!-- end panel -->
</div>
<!-- </div> -->
<!-- /.row -->






<script type="text/javascript">
	$(document).ready(
					function() {

						var result = '<c:out value="${result}"/>';
						var modifyResult = '<c:out value="${modifyResult}"/>'

						checkModal(result);
						checkModal(modifyResult);

						history.replaceState({}, null, null);

						function checkModal(result) {

							if (result === '' || history.state) {
								return;
							}

							if (result !=='null') {
								$(".modal-body").html("완료되었습니다.");
							}

							$("#myModal").modal("show");
							$("#rgmdBtn").click(function() {
				                $("#myModal").modal("hide");
				            });
							  
						}

						$("#regBtn").on("click", function() {

							self.location = "/manager/register";

						});
						

						var actionForm = $("#actionForm");

						$(".paginate_button a").on(
								"click",
								function(e) {

									e.preventDefault();

									console.log('click');

									actionForm.find("input[name='pageNum']")
											.val($(this).attr("href"));
									actionForm.submit();
								});

						$(".move").on("click", function(e) {

											e.preventDefault();
											
											actionForm
													.append("<input type='hidden' name='emp_id' value='"
															+ $(this).attr(
																	"href")
															+ "'>");
											actionForm.attr("action",
													"/manager/get");
											actionForm.submit();

										});

					var searchForm = $("#searchForm");

						$("#searchForm button").on(
								"click",
								function(e) {

									if (!searchForm.find("option:selected").val()) {
										alert("검색종류를 선택하세요");
										return false;
									}

									if (!searchForm.find(
											"input[name='keyword']").val()) {
										alert("키워드를 입력하세요");
										return false;
									}

									searchForm.find("input[name='pageNum']").val("1");
									e.preventDefault();

									searchForm.submit();

								}); 

					});
</script>

</div>
</body>
</html>


<%@include file="/WEB-INF/views/includes/footer.jsp"%>

