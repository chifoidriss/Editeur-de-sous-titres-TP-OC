<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Acceuil</title>
<link type="text/css" rel="stylesheet" href="./asset/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./asset/css/default.css">

<script type="text/javascript" src="./asset/js/jquery.min.js"></script>
<script type="text/javascript" src="./asset/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid home">
		<%@include file="include/header.jsp" %>
		
		<h2 class="page-header text-center">Mes Fichiers de Sous-Titres Uploadés</h2>

		<table class="table table-responsive table-striped table-hover table-condensed">
			<thead>
				<tr>
					<th>#</th>
					<th>Version Originale (VO)</th>
					<th>Version Traduite (VT)</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${subtitles }" var="item" varStatus="i">
					<tr>
						<td>
							<b>${i.index+1}</b>
						</td>
						<td>
							<a href="${item.filePathVO}" class="text-success">
								<c:out value="${item.fileName}"></c:out>
							</a>
						</td>
						<td>
							<c:choose>
								<c:when test="${!empty item.filePathVT}">
									<a href="${item.filePathVT}" class="text-primary">
										<c:out value="${item.fileName}"></c:out>
									</a>
								</c:when>
								<c:otherwise>
									<b class="text-danger"><i>Pas encore traduit!!!</i></b>
								</c:otherwise>
							</c:choose>
						</td>
						<td>
							<a href="edit?filePath=${item.filePathVO}">
								<c:choose>
									<c:when test="${empty item.filePathVT}">
										<button class="btn btn-primary">Traduire</button>
									</c:when>
									<c:otherwise>
										<button class="btn btn-warning">Modifier</button>
									</c:otherwise>
								</c:choose>
							</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>