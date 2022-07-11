<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
</head>

<body>
	<div id="wrap">

		<!-- 개인블로그 해더 -->
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>

		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">

					<!-- 사용자업로드 이미지 -->
					<img id="proImg"
						src="${pageContext.request.contextPath}/${blogMap.BlogVo.LOGOFILE}">

					<div id="nick">
						이름:${blogMap.BlogVo.USERNAME}<br>아이디: ${blogMap.BlogVo.ID}님
					</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<!-- for문 -->
						<c:forEach items="${blogMap.cateList}" var="vo">
							<li class="cateListClass" ><a href="${pageContext.request.contextPath}/${blogMap.BlogVo.ID}?cateNo=${vo.cateNo}">${vo.cateName}</a></li>
						</c:forEach>
					</ul>
					
				</div>
			</div>
			<!-- profilecate_area -->

			<div id="post_area">

				<div id="postBox" class="clearfix">
					<div id="postTitle" class="text-left">
						<strong>${blogMap.Postvo.POSTTITLE}</strong>
					</div>
					<div id="postDate" class="text-left">
						<strong>${blogMap.Postvo.REGDATE}</strong>
					</div>
					<div id="postNick">
						이름:${blogMap.BlogVo.USERNAME} <br>아이디:${blogMap.BlogVo.ID}님
					</div>
				</div>
				<!-- //postBox -->

				<div id="post">${blogMap.PostVo.POSTCONTENT}</div>
				<!-- //post -->

				<!-- 글이 없는 경우 -->

				


				<div id="list">
					<div id="listTitle" class="text-left">
						<strong>카테고리의 글</strong>
					</div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>

						<!-- for문 -->
						<c:forEach items="${blogMap.postList}" var="vo">
							<tr>
								<td class="text-left"><a href="">${vo.postTitle}</a></td>
								<td class="text-right">${vo.regDate}</td>
							</tr>
						</c:forEach>
						<!-- for문 -->

					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->



		</div>
		<!-- //content -->
		<div class=></div>
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>

	</div>
	<!-- //wrap -->
</body>

</html>