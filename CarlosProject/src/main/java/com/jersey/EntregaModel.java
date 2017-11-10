package com.jersey;

import java.sql.Date;

public class EntregaModel {


	private int id;
	private int numPedido;
	private int idCliente;
	private String nomeRecebedor;
	private int cpfRecebedor;
	private Date dataEntrega;
	private String horaEntrega;
	
	public EntregaModel(){}
	
	public EntregaModel(int id, int numPedido, int idCliente, String nomeRecebedor, int cpfRecebedor, Date dataEntrega, String horaEntrega) {
		this.id = id;
		this.numPedido = numPedido;
		this.idCliente = idCliente;
		this.nomeRecebedor = nomeRecebedor;
		this.cpfRecebedor = cpfRecebedor;
		this.dataEntrega = dataEntrega;
		this.horaEntrega = horaEntrega;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumPedido() {
		return numPedido;
	}

	public void setNumPedido(int numPedido) {
		this.numPedido = numPedido;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	public String getNomeRecebedor() {
		return nomeRecebedor;
	}

	public void setNomeRecebedor(String nomeRecebedor) {
		this.nomeRecebedor = nomeRecebedor;
	}

	public int getCpfRecebedor() {
		return cpfRecebedor;
	}

	public void setCpfRecebedor(int cpfRecebedor) {
		this.cpfRecebedor = cpfRecebedor;
	}

	public Date getDataEntrega() {
		return dataEntrega;
	}

	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}

	public String getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(String horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

}
