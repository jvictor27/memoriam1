<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="tt" tagdir="/WEB-INF/tags/templating"%>
<%@ taglib prefix="mm" tagdir="/WEB-INF/tags/messages"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<tt:template title="Editar contato">
	<jsp:attribute name="tscript">
<link href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css" rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/memoriam.css" rel="stylesheet">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
</jsp:attribute>
	<jsp:body>
	<div class="container">
		<div class="jumbotron">
			<h2>
				Memori<i class="glyphicon glyphicon-phone"></i>m
			</h2>
			<mm:messages value="${msgs}" erroStyle="color:red" infoStyle="color:blue"/>

			<h3>Dados da Operadora</h3>

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
	</jsp:body>
</tt:template>