package controle;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.AgendamentoDAO;
import modelo.Agendamento;

@WebServlet("/")
public class AgendamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AgendamentoDAO agendamentoDAO;

	public void init() {
		agendamentoDAO = new AgendamentoDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String acao = request.getServletPath();

		try {
			switch (acao) {
				case ("/novo"):
					exibirNovoAgendamento(request, response);
					break;
				case ("/inserir"):
					inserirAgendamento(request, response);
					break;
				case ("/deletar"):
					deletarAgendamento(request, response);
					break;
				case ("/editar"):
					exibirEditarAgendamento(request, response);
					break;
				case ("/atualizar"):
					atualizarAgendamento(request, response);
					break;
				default:
					listarAgendamentos(request, response);
					break;
			}
		} catch (SQLException e) {
			throw new ServletException(e);
		}
	}

	private void listarAgendamentos(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Agendamento> listarAgendamentos = agendamentoDAO.listarAgendamentos();
		request.setAttribute("listarAgendamentos", listarAgendamentos);
		RequestDispatcher render = request.getRequestDispatcher("listagem.jsp");
		render.forward(request, response);
	}

	private void exibirNovoAgendamento(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher render = request.getRequestDispatcher("agendamento.jsp");
		render.forward(request, response);
	}

	private void exibirEditarAgendamento(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Agendamento agendamento = agendamentoDAO.selecionarAgendamento(id);
		RequestDispatcher render = request.getRequestDispatcher("agendamento.jsp");
		request.setAttribute("agendamento", agendamento);
		render.forward(request, response);
	}

	private void inserirAgendamento(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		String nome = request.getParameter("nome");
		String apartamento = request.getParameter("apartamento");
		String data = request.getParameter("data");
		String horario = request.getParameter("horario");
		Agendamento novoAgendamento = new Agendamento(nome, apartamento, data, horario);
		agendamentoDAO.inserirAgendamento(novoAgendamento);
		response.sendRedirect("listagem");
	}

	private void atualizarAgendamento(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		String nome = request.getParameter("nome");
		String apartamento = request.getParameter("apartamento");
		String data = request.getParameter("data");
		String horario = request.getParameter("horario");
		Agendamento agendamento = new Agendamento(id, nome, apartamento, data, horario);
		agendamentoDAO.atualizarAgendamento(agendamento);
		response.sendRedirect("listagem");
	}

	private void deletarAgendamento(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		agendamentoDAO.deletarAgendamento(id);
		response.sendRedirect("listagem");
	}
}