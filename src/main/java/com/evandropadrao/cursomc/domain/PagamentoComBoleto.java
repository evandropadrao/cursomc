package com.evandropadrao.cursomc.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.evandropadrao.cursomc.enums.EstadoPagamento;
import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class PagamentoComBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataVencimemnto;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private Date dataPagamento;
	
	public PagamentoComBoleto() {
	}

	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido,
			Date dataVencimemnto, Date dataPagamento) {
		
		super(id, estado, pedido);
		
		this.dataVencimemnto = dataVencimemnto;
		this.dataPagamento = dataPagamento;
	}

	public Date getDataVencimemnto() {
		return dataVencimemnto;
	}

	public void setDataVencimemnto(Date dataVencimemnto) {
		this.dataVencimemnto = dataVencimemnto;
	}

	public Date getDataPagamento() {
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento) {
		this.dataPagamento = dataPagamento;
	}


}
