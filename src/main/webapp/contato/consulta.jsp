<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Memoriam</title>
<link
	href="${pageContext.request.contextPath}/bootstrap/css/bootstrap.min.css"
	rel="stylesheet" />
<link href="${pageContext.request.contextPath}/css/memoriam.css"
	rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="jumbotron">
			<h2>
				Memori<i class="glyphicon glyphicon-phone"></i>m.
			</h2>
			<nav class="navbar navbar-default">
			    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			      <ul class="nav navbar-nav">
			        <li><a href="${pageContext.request.contextPath}">Contatos <i class="glyphicon glyphicon-earphone"></i></a></li>
			        <li><a href="${pageContext.request.contextPath}/controller.do?op=conope">Operadoras <i class="glyphicon glyphicon-usd"></i></a></li>
			      </ul>
			     </div>
			</nav>
			<table class="table table-striped">
				<tr align="left">
					<th></th>
					<th style="width: 30%">Nome</th>
					<th>Telefone</th>
					<th>Operadora</th>
				</tr>
				<form action="${pageContext.request.contextPath}/controller.do?op=deletectt" id="form_del" method="POST">
				<c:forEach var="contato" items="${contatos}">
					<tr align="left">
						<td><span class="fake_check glyphicon glyphicon-unchecked"><input hidden class="selections" name="del_selected" type="checkbox" value="${contato.id}"/></span></td>
						<td><a href="controller.do?op=edtctt&id=${contato.id}">${contato.nome}</a></td>
						<td>${contato.fone}</td>
						<td>${contato.operadora.nome}</td>
					</tr>
				</c:forEach>
				</form>
			</table>
			<a href="contato/cadastro.jsp" class="form-control btn btn-primary"  style= "width:350px;" >Novo Contato <i class="glyphicon glyphicon-floppy-disk"></i></a><br> 
			<!-- <a href="operadora/cadastro.jsp"class="form-control btn btn-primary">Nova Operadora</a> -->
			<!-- <a href=${pageContext.request.contextPath}/controller.do?op=conope class="form-control btn btn-primary">Consultar e Alterar Operadora</a> -->
			<a class="form-control btn btn-danger btn_delete" style="display: none; width:350px">Apagar selecionados <i class="glyphicon glyphicon-trash"></i></a>
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
