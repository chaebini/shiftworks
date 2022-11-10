<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/WEB-INF/views/includes/header.jsp"%>
<%@ page session="false"%>
<html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<head>

<meta name="viewport"
	content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<!-- fullcalendar CDN -->
<link
	href='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.css'
	rel='stylesheet' />
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/main.min.js'></script>
<!-- fullcalendar 언어 CDN -->
<script
	src='https://cdn.jsdelivr.net/npm/fullcalendar@5.8.0/locales-all.min.js'></script>
<!--javaScript 스크립트-->
<script type="text/javascript" src="/resources/js/booking.js"></script>
<script type="text/javascript">
	document.addEventListener('DOMContentLoaded', function() {
		$(function() {
			var request = $.ajax({
				url : "/booking/cal2.json",
				method : "GET"
			});

			request.done(function(data) {
				console.log(data); //가져온 data 확인

				var calendarEl = document.getElementById('calendar');

				var calendar = new FullCalendar.Calendar(calendarEl, {
					nowIndicator : true,
					initialView : 'listWeek',
					/* headerToolbar: {
					    center: 'title',
					}, */
					eventBackgroundColor : "#1C3359",
					events : data,
				});

				calendar.render();
			});

			request.fail(function(jqXHR, textStatus) {
				alert("Request failed: " + textStatus);
			});
		});

	});

	$(document).ready(function() {

		console.log("Ready for home page");

		$(function() {
			var request = $.ajax({
				url : "/booking/list",
				method : "GET"
			});

			request.done(function(data) {

			});

		});

	});
</script>

<script type="text/javascript">
$(document).ready(function(){
	// 로딩 시 보이기
	var emp_id = '<sec:authentication property="principal.username"/>';
	var today = new Date();
    var year = today.getFullYear();
    var month = ('0' + (today.getMonth() + 1)).slice(-2);
    var day = ('0' + today.getDate()).slice(-2);
    today = year + '-' + month  + '-' + day;
	
	// 알림 내역 가져와서 뿌리기
    $.ajax({
    	type: 'get',
        url: '/alarm/' + emp_id + "/" + today,
        success: function(result) {
            if (result != null) {
            	console.log(result);
            	
				var html = '';
				// 알림을 li로 만들기
           		result.forEach((alarm) => {
           			html += '<li>' + alarm.content + '</li>';
           		});
				$('.alarmResult').html(html);
			}
        },
		error : function(xhr, status, er) {
			if (error) {
				error(er);
			}
		}
    });
	
	
});

</script>

<title>Home</title>
</head>
<body>
	<div class="container">
		<!-- <h1>
			Hello world!  
		</h1> -->

		<h2>
			<sec:authentication property="principal.username"></sec:authentication>
			님 환영합니다
		</h2>
		<%-- <c:out value="${get.rsc_id }"></c:out> --%>

		<div class="row">

			<!-- 		<div class="col">
		</div> -->


			<!-- 알림창 -->
			<div class="offcanvas offcanvas-start show" tabindex="-1"
				id="offcanvas" aria-labelledby="offcanvasLabel">
				<div class="offcanvas-header">
					<h5 class="offcanvas-title" id="offcanvasLabel">나의 알림</h5>
					<button type="button" class="btn-close" data-bs-dismiss="offcanvas"
						aria-label="Close"></button>
				</div>
				<div class="offcanvas-body">
					<ul class="alarmResult">
						
					</ul>
				</div>
			</div>


			<div class="col-5 card" style="background: white; height: 450px;">
				<!-- 1row 1번째 -->
				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#calendar">주간 회의일정</a></li>
					<li class="nav-item"><a class="nav-link" href="/booking/cal">월간
							회의일정</a></li>
					<li class="nav-item"><a class="nav-link" href="/booking/new">예약
							바로가기</a></li>
					<!-- <li class="nav-item"><a class="nav-link disabled" href="#"
						tabindex="-1" aria-disabled="true">Disabled</a></li> -->
				</ul>
				<div id='calendar' style="height: 200px; font-size: 12px;"></div>
			</div>
			<!-- end 1row 1st -->

			<div class="col"></div>
			<!-- end col -->

			<div class="col-6" style="background: white; margin-top: 30px;">
				<!-- 1row 2번째 -->

				<div class="col">
					<ul class="list-group">
						<a href="/approval/main" style="text-decoration: none;"><li
							class="list-group-item d-flex justify-content-between align-items-center">
								결재가 필요한 문서 <span class="badge bg-primary rounded-pill">14</span>
						</li></a>
						<a href="/approval/receivedList" style="text-decoration: none;"><li
							class="list-group-item d-flex justify-content-between align-items-center">
								결재 중인 문서 <span class="badge bg-primary rounded-pill">2</span>
						</li></a>
						<a href="/approval/list" style="text-decoration: none;"><li
							class="list-group-item d-flex justify-content-between align-items-center">
								반려된 문서 <span class="badge bg-primary rounded-pill">1</span>
						</li></a>
					</ul>
				</div>

				<br> <br>


				<ul class="nav nav-tabs">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page" href="#tabOne">Tab1</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Tab2</a></li>
					<li class="nav-item"><a class="nav-link" href="#">Tab3</a></li>
					<li class="nav-item"><a class="nav-link disabled" href="#"
						tabindex="-1" aria-disabled="true">Disabled</a></li>
				</ul>

				<!-- 게시판 적용 -->
				<table class="table table-sm" id="tabOne">
					<thead>
						<tr>
							<th>글번호</th>
							<th>제목</th>
							<th>작성자</th>
							<th>일자</th>
							<!-- <th>예약시간</th> -->
						</tr>
					</thead>

					<c:forEach items="${ event }" var="reserv" begin="0" end="4">
						<tr>
							<td><c:out value="${reserv.book_id }"></c:out></td>
							<td><a href="/booking/${reserv.book_id}"><c:out
										value="${reserv.book_title }"></c:out></a></td>
							<td><c:out value="${reserv.emp_id }"></c:out></td>
							<td><c:set var="book_date" value="${reserv.book_date }"></c:set>
								<c:out value="${fn:substring(book_date,0,10) }"></c:out></td>
							<%-- <td><c:out value="${reserv.book_begin }시~${reserv.book_begin+2 }시"></c:out> </td> --%>
						</tr>
					</c:forEach>
				</table>



				<!-- 게시판 최신글 -->
				<%--  			<table class="table table-sm" id="tabOne">
 				<thead>
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<!-- <th>작성자</th> -->
						<!-- <th>일자</th> -->
						<!-- <th>예약시간</th> -->
					</tr>
				</thead>
				
				<c:forEach items="${ board }" var="reserv">
					<tr>
						<td><c:out value="${reserv.post_id }"></c:out></td>
						<td><a href="#"><c:out value="${reserv.post_name }"></c:out></a></td>
						<td><c:out value="${reserv.emp_id }"></c:out></td>
						<td><c:set var="book_date" value="${reserv.book_date }"></c:set>
						<c:out value="${fn:substring(book_date,0,10) }"></c:out></td>
						<td><c:out value="${reserv.book_begin }시~${reserv.book_begin+2 }시"></c:out> </td>
					</tr>
				</c:forEach>
			</table> --%>



				<!-- <br><br>
			<button type="button" class="btn btn-primary">Primary</button>
			<button type="button" class="btn btn-secondary">Secondary</button>
			<button type="button" class="btn btn-success">Success</button> -->


			</div>
		</div>
		<!-- end 1st row -->











	</div>
	<!-- end container -->
</body>
<%@include file="/WEB-INF/views/includes/footer.jsp"%>
</html>
