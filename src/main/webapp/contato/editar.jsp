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
			<form action="${pageContext.request.contextPath}/controller.do"
				method="post" class="form-horizontal">
				<input type="hidden" name="op" value="cadctt">
				<input type="hidden" name="usuario" value="${sessionScope.usuario.id}">
				 <input
					type="hidden" name="id" value="${contato.id}"> <input
					id="nome" value="${contato.nome}" name="nome" type="text"
					class="form-control" placeholder="Nome" /> <input id="fone"
					value="${contato.fone}" name="fone" type="text"
					class="form-control" placeholder="Fone" />
				<fmt:formatDate var="dataAniv" value="${contato.dataAniversario}"
					pattern="dd/MM/yyyy" />
				<input id="dataaniv" value="${dataAniv}" name="dataaniv"
					class="form-control" type="date"
					placeholder="Data de criação (dd/mm/aaaa)" /> <select
					class="form-control" id="operadora" name="operadora">
					<option value="${null}" label="Selecione a operadora">Selecione
						a operadora</option>
					<c:forEach var="operadora" items="${utilBean.operadoras}">
						<c:if test="${operadora.id eq contato.operadora.id}">
							<option value="${operadora.id}" label="${operadora.nome}"
								selected>${operadora.nome}</option>
						</c:if>
						<c:if test="${operadora.id ne contato.operadora.id}">
							<option value="${operadora.id}" label="${operadora.nome}">
								${operadora.nome}</option>
						</c:if>
					</c:forEach>
				</select> <button type="submit" class="form-control btn btn-primary" style= "width:350px;">Salvar <i class="glyphicon glyphicon-floppy-disk"></i></button>
			</form>
			<a class="form-control btn btn-success" href="${pageContext.request.contextPath}">Voltar <i class="glyphicon glyphicon-triangle-left"></i></a>
		</div>
	</div>
	<c:set var="endofconversation" value="true" scope="request" />
	</jsp:body>
</tt:template>