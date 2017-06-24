<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cadastro de contato</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>
				Memori<i class="glyphicon glyphicon-phone"></i>m
			</h2>

			<h3>Dados da Operadora</h3>
			<c:if test="${not empty msgs}">
				<div align="left">
					<div style="color: red">
						<ul style="padding-left: 0px;">
							<c:forEach var="msg" items="${msgs}">
								<li style="list-style-type: none;">${msg}</li>
							</c:forEach>
						</ul>
					</div>
				</div>
			</c:if>

			<form action="${pageContext.request.contextPath}/controller.do"
				method="post" class="form-horizontal">
				<input type="hidden" name="op" value="cadope"> 
				<input type="hidden" name="id" value="${operadora.id}">
				<input id="nome" value="${operadora.nome}" name="nome" type="text" class="form-control"  /> 
				<input id="prefixo" value="${operadora.prefixo}" name="prefixo" type="text" class="form-control"  />				
				 <button type="submit" class="form-control btn btn-primary" style= "width:350px;">Salvar <i class="glyphicon glyphicon-floppy-disk"></i></button>
			</form>
			<a class="form-control btn btn-success" href="${pageContext.request.contextPath}">Voltar <i class="glyphicon glyphicon-triangle-left"></i></a>
		</div>
	</div>
	<c:set var="endofconversation" value="true" scope="request" />
	<script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
	<script	src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js" ></script>
</body>
</html>