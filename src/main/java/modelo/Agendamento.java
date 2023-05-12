package modelo;

public class Agendamento {
	protected int id;
	protected String nome;
	protected String apartamento;
	protected String data;
	protected String horario;

	public Agendamento() {

	}

	public Agendamento(String nome, String apartamento, String data, String horario) {
		super();
		this.nome = nome;
		this.apartamento = apartamento;
		this.data = data;
		this.horario = horario;
	}

	public Agendamento(int id, String nome, String apartamento, String data, String horario) {
		super();
		this.id = id;
		this.nome = nome;
		this.apartamento = apartamento;
		this.data = data;
		this.horario = horario;
	}

	public int getId() {
		return id;
	}

	public int setId(int id) {
		return this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApartamento() {
		return apartamento;
	}

	public void setApartamento(String apartamento) {
		this.apartamento = apartamento;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
}
