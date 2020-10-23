<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Envoyer Fichier</title>
<link type="text/css" rel="stylesheet" href="./asset/css/bootstrap.min.css">
<link type="text/css" rel="stylesheet" href="./asset/css/default.css">

<script type="text/javascript" src="./asset/js/jquery.min.js"></script>
<script type="text/javascript" src="./asset/js/bootstrap.min.js"></script>
<script type="text/javascript">
	$(document).ready(function($) {
		$('.alert').click(function() {
			$(this).slideUp('slow');
		});
		
		$('input:file').change(function() {
			$(this).prev('label').text($(this).val()).fadeIn('slow');
		});
	});
</script>
</head>
<body>
	<div class="container-fluid home">
		<%@include file="include/header.jsp" %>
		
		<h2 class="page-header text-center">Envoyer un Fichier de Sous-Titres</h2>

		<c:choose>
			<c:when test="${!empty error}">
				<div class="alert alert-danger">${error}</div>
			</c:when>
			<c:when test="${!empty success}">
				<div class="alert alert-success">${success}</div>
			</c:when>
		</c:choose>
		<form method="post" enctype="multipart/form-data">
			<label for="file" class="input"> Choisir un fichier (.srt) </label>
			<input type="file" name="file" id="file" class="hidden">
			<button type="submit" class="button">Envoyer</button>
		</form>

	</div>
</body>
</html>