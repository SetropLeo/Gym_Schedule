package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Agendamento;

public class AgendamentoDAO {
  private String dbUrl = "jdbc:mysql://localhost/gymschedule";
  private String dbUser = "root";
  private String dbPassword = "1234";

  private static final String INSERT_AGENDAMENTO = "INSERT INTO agendamentos"
      + "(nome, apartamento, data, horario) VALUES "
      + " (?, ?, ?, ?);";
  private static final String SELECT_AGENDAMENTO = "SELECT id, nome, apartamento, data, horario FROM agendamentos WHERE id = ?;";
  private static final String SELECT_AGENDAMENTOS = "SELECT * FROM agendamentos;";
  private static final String DELETE_AGENDAMENTO = "DELETE FROM agendamentos WHERE id = ?;";
  private static final String UPDATE_AGENDAMENTO = "UPDATE agendamentos SET nome = ?, apartamento = ?, data = ?, horario = ? WHERE id = ?;";

  public AgendamentoDAO() {
  }

  protected Connection getConnection() {
    Connection connection = null;
    try {
      Class.forName("com.mysql.jdbc.Driver");
      connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    } catch (SQLException e) {
      e.printStackTrace();
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
    return connection;
  }

  public void inserirAgendamento(Agendamento agendamento) throws SQLException {
    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_AGENDAMENTO)) {
      preparedStatement.setString(1, agendamento.getNome());
      preparedStatement.setString(2, agendamento.getApartamento());
      preparedStatement.setString(3, agendamento.getData());
      preparedStatement.setString(4, agendamento.getHorario());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      printSQLException(e);
    }
  }

  public Agendamento selecionarAgendamento(int id) {
    Agendamento agendamento = null;
    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AGENDAMENTO)) {
      preparedStatement.setInt(1, id);
      ResultSet result = preparedStatement.executeQuery();
      while (result.next()) {
        String nome = result.getString("nome");
        String apartamento = result.getString("apartamento");
        String data = result.getString("data");
        String horario = result.getString("horario");
        agendamento = new Agendamento(id, nome, apartamento, data, horario);
      }
    } catch (SQLException e) {
      printSQLException(e);
    }
    return agendamento;
  }

  public List<Agendamento> listarAgendamentos() {
    List<Agendamento> agendamentos = new ArrayList<>();
    try (Connection connection = getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_AGENDAMENTOS)) {
      ResultSet result = preparedStatement.executeQuery();
      while (result.next()) {
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String apartamento = result.getString("apartamento");
        String data = result.getString("data");
        String horario = result.getString("horario");
        agendamentos.add(new Agendamento(id, nome, apartamento, data, horario));
      }
    } catch (SQLException e) {
      printSQLException(e);
    }
    return agendamentos;
  }

  public boolean deletarAgendamento(int id) throws SQLException {
    boolean agendamentoDeletado;
    try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_AGENDAMENTO)) {
      statement.setInt(1, id);
      agendamentoDeletado = statement.executeUpdate() > 0;
    }
    return agendamentoDeletado;
  }

  public boolean atualizarAgendamento(Agendamento agendamento) throws SQLException {
    boolean agendamentoAtualizado;
    try (Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_AGENDAMENTO)) {
      statement.setString(1, agendamento.getNome());
      statement.setString(2, agendamento.getApartamento());
      statement.setString(3, agendamento.getData());
      statement.setString(4, agendamento.getHorario());
      statement.setInt(5, agendamento.getId());
      agendamentoAtualizado = statement.executeUpdate() > 0;
    }
    return agendamentoAtualizado;
  }

  private void printSQLException(SQLException ex) {
    for (Throwable e : ex) {
      if (e instanceof SQLException) {
        e.printStackTrace(System.err);
        System.err.println("SQLState: " + ((SQLException) e).getSQLState());
        System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
        System.err.println("Message: " + e.getMessage());
        Throwable causaErro = ex.getCause();
        while (causaErro != null) {
          System.out.println("Cause: " + causaErro);
          causaErro = causaErro.getCause();
        }
      }
    }
  }
}
