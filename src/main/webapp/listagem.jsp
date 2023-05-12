<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="UTF-8" %> <%@ taglib
uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>Listagem de Agendamentos</title>
    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
      integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
      crossorigin="anonymous"
    />
    <style>
      nav {
        gap: 40px;
        justify-content: space-between;
      }
    </style>
  </head>

  <body>
    <header>
      <nav class="navbar navbar-dark bg-dark">
        <div>
          <a href="" class="navbar-brand">Listagem de Agendamentos</a>
        </div>
        <ul class="navbar-nav">
          <li>
            <a href="<%=request.getContextPath()%>" class="nav-link">Menu Inicial</a>
          </li>
        </ul>
      </nav>
    </header>
    <br />
    <div class="container">
      <h3>Agendamentos existentes</h3>
      <hr />
      <div class="container text-right">
        <a href="<%=request.getContextPath()%>/novo" class="btn btn-success btn-sm">Novo Agendamento</a>
      </div>
      <br />
      <table class="table table-hover table-sm">
        <thead>
          <tr>
            <th scope="col">Id</th>
            <th scope="col">Nome</th>
            <th scope="col">Apartamento</th>
            <th scope="col">Data</th>
            <th scope="col">Horario</th>
          </tr>
        </thead>
        <tbody class="align-middle">
          <c:forEach var="agendamento" items="${listarAgendamentos}">
            <tr>
              <td>
                <c:out value="${agendamento.id}" />
              </td>
              <td>
                <c:out value="${agendamento.nome}" />
              </td>
              <td>
                <c:out value="${agendamento.apartamento}" />
              </td>
              <td>
                <c:out value="${agendamento.data}" />
              </td>
              <td>
                <c:out value="${agendamento.horario}" />
              </td>
              <td>
                <a href="editar?id=<c:out value='${agendamento.id}' />" class="btn btn-primary btn-sm">EDITAR</a>
                <a href="deletar?id=<c:out value='${agendamento.id}' />" class="btn btn-danger btn-sm">DELETAR</a>
              </td>
            </tr>
          </c:forEach>
        </tbody>
      </table>
    </div>
  </body>
</html>
