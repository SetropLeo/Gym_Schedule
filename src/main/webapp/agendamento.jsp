<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Agendamento de horário</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
	integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
	crossorigin="anonymous">
<style>
.navbar-nav {
	gap: 40px;
	flex-direction: row !important;
}
</style>
</head>

<body>
	<header>
		<nav class="navbar navbar-dark bg-dark">
			<div>
				<a href="" class="navbar-brand">Agendamento</a>
			</div>
			<ul class="navbar-nav">
				<a href="<%=request.getContextPath()%>/listagem" class="nav-link">Listagem
					de reservas</a>
				</li>
				<li><a href="<%=request.getContextPath()%>" class="nav-link">Menu
						Inicial</a></li>
				<li>
			</ul>
		</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				<c:if test="${agendamento != null}">
					<form action="atualizar" method="post">
				</c:if>
				<c:if test="${agendamento == null}">
					<form action="inserir" method="post">
				</c:if>
				<caption>
					<h2>
						<c:if test="${agendamento != null}">
									Editar Alterações
									<hr>
						</c:if>
						<c:if test="${agendamento == null}">
									Adicionar Usuário
									<hr>
						</c:if>
					</h2>
				</caption>
				<c:if test="${agendamento != null}">
					<input type="hidden" name="id"
						value="<c:out value='${agendamento.id}' />" />
				</c:if>
				<fieldset class="form-group">
					<label>Nome</label> <input type="text"
						placeholder="Ex: Leonardo Portes"
						value="<c:out value='${agendamento.nome}' />" class="form-control"
						name="nome" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Apartamento</label> <input type="text" placeholder="Ex: 201"
						value="<c:out value='${agendamento.apartamento}' />"
						class="form-control" name="apartamento" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Data</label> <input type="text" placeholder="Ex: 23/05/2002"
						value="<c:out value='${agendamento.data}' />" class="form-control"
						name="data" required="required">
				</fieldset>
				<fieldset class="form-group">
					<label>Hora</label> <input type="text" placeholder="Ex: 10:30"
						value="<c:out value='${agendamento.horario}' />"
						class="form-control" name="horario" required="required">
				</fieldset>
				<button type="submit" class="btn btn-success btn-sm">Salvar</button>
				<a class="btn btn-danger btn-sm"
					href="<%=request.getContextPath()%>/listagem">Cancelar</a>
				</form>
			</div>
		</div>
	</div>
</body>

</html>