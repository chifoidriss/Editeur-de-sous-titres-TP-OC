<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Editeur de SousTitres</title>
<link type="text/css" rel="stylesheet" href="./asset/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./asset/css/default.css">
<script type="text/javascript" src="./asset/js/jquery.min.js"></script>
<script type="text/javascript" src="./asset/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function($) {
		$('.alert').click(function() {
			$(this).slideUp('slow');
		});
	});
</script>

</head>
<body>
	<div class="container-fluid home">
		<%@include file="include/header.jsp" %>
		
		<h2 class="page-header text-center">Mon Editeur de Sous-Titres Gratuit</h2>

		<c:choose>
			<c:when test="${!empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:when>
			<c:when test="${!empty success}">
				<div class="alert alert-success">${success}</div>
			</c:when>
		</c:choose>

		<form method="post">
			<table class="table table-responsive table-hover table-condensed">
				<thead>
					<tr>
						<th>Version Originale</th>
						<th>Traduire un sous-titre</th>
					</tr>
				</thead>

				<tbody>
					<c:set var="regexNumber" value="^([0-9]+)$"></c:set>
					<c:set var="regexTime" value="^([0-9:,-> ]+)$"></c:set>
					<c:set var="regexUrl" value="^((http?s)|(www).[a-zA-Z0-9?]*.[a-z]{2,})$"></c:set>

					<c:forEach items="${subtitles}" var="line" varStatus="i">
						<tr>
							<c:choose>
								<c:when test="${!line.isEmpty() && 
												!line.matches(regexNumber) && 
												!line.matches(regexTime) && 
												!line.matches(regexUrl)}">
									<td>
										<label for="line${i.index}">
											<c:out value="${line}"></c:out>
										</label>
									</td>
									<td>
										<input type="text" name="line${i.index}" id="line${i.index}"
											   class="input"value="${translateLine.get(i.index)}">
									</td>
								</c:when>
								<c:otherwise>
									<input type="hidden" name="line${i.index}" value="${line}">
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<button type="submit" class="btnForm">Save</button>
		</form>

	</div>
</body>
</html>