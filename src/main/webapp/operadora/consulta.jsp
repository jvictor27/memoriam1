<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Consulta Operadoras</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>
			
				Memori<i class="glyphicon glyphicon-phone"></i>m
			</h2>
			
			<nav class="navbar navbar-default">
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li><a href="${pageContext.request.contextPath}">Contatos <i class="glyphicon glyphicon-earphone"></i></a></li>
			        <li><a href="${pageContext.request.contextPath}/controller.do?op=conope">Operadoras <i class="glyphicon glyphicon-usd"></i></a></li>
			      </ul>
			     </div>
			</nav>
			<table class="table table-striped table-bordered">
				<tr align="left">
					<th></th>
					<th>Nome</th>
					<th>Prefixo</th>
				</tr>
				<form action="${pageContext.request.contextPath}/controller.do?op=deleteope" id="form_del" method="POST">
				<c:forEach var="operadora" items="${operadoras}">
					<tr align="left">
						<td><span class="fake_check glyphicon glyphicon-unchecked"><input hidden class="selections" name="del_selected" type="checkbox" value="${operadora.id}"/></span></td>
						<td><a href="controller.do?op=edtope&id=${operadora.id}">${operadora.nome}</a></td>
						<td>${operadora.prefixo}</td>
					</tr>
				</c:forEach>
				</form>
			</table>
			<a href="operadora/cadastro.jsp"class="form-control btn btn-primary" style= "width:350px;">Nova Operadora <i class="glyphicon glyphicon-floppy-saved"></i></a><br>
			<a class="form-control btn btn-danger btn_delete" style="display: none;width:350px">Apagar selecionados <i class="glyphicon glyphicon-trash"></i></a>
			<%-- <a class="form-control btn btn-primary" href="${pageContext.request.contextPath}">Voltar</a> --%>
		</div>
	</div>
	<form id="form_oculto" action="" style="display: none;"></form>
</body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/bootstrap/dist/sweetalert.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/bootstrap/dist/sweetalert.css">
<script>
$(document).ready(
		function() {

			$(".fake_check").click(function() {					
				if ($(this).children('.selections').is(":checked")){
					$(this).children('.selections').prop('checked', false);
					$(this).attr('class', 'glyphicon glyphicon-check');
					$(this).attr('class', 'glyphicon glyphicon-unchecked');
				} else{
					$(this).children('.selections').prop('checked', true);
					$(this).attr('class', 'glyphicon glyphicon-unchecked');
					$(this).attr('class', 'glyphicon glyphicon-check');
				}
				
				if ($('.selections:checkbox:checked').length > 0) {
					$(".btn_delete").show();
					$(".btn_novo").hide();
				} else {
					$(".btn_delete").hide();
					$(".btn_novo").show();
				}

			});

			$(".btn_delete").click(
					function() {
						selecionados = $('.selections:checkbox:checked');
						swal({
							  title: "Você tem certeza?",
							  text: "Você não sera capaz de recuperar esses dados!",
							  type: "warning",
							  showCancelButton: true,
							  confirmButtonColor: "#DD6B55",
							  confirmButtonText: "Sim!",
							  cancelButtonText: "Não!",
							  closeOnConfirm: false,
							  closeOnCancel: false
							},
							function(isConfirm){
								  if (isConfirm) {
								    swal("Deletado!", "Hasta la vista, baby!", "success");
								    $("#form_del").submit();
								  } else {
								    swal("Cancelado", "Ufa essa passou perto :)", "info");
								  }
								});
					});
		});
</script>
</html>