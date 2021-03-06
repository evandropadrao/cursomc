package com.evandropadrao.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.evandropadrao.cursomc.domain.Categoria;
import com.evandropadrao.cursomc.domain.Cidade;
import com.evandropadrao.cursomc.domain.Cliente;
import com.evandropadrao.cursomc.domain.Endereco;
import com.evandropadrao.cursomc.domain.Estado;
import com.evandropadrao.cursomc.domain.ItemPedido;
import com.evandropadrao.cursomc.domain.Pagamento;
import com.evandropadrao.cursomc.domain.PagamentoComBoleto;
import com.evandropadrao.cursomc.domain.PagamentoComCartao;
import com.evandropadrao.cursomc.domain.Pedido;
import com.evandropadrao.cursomc.domain.Produto;
import com.evandropadrao.cursomc.enums.EstadoPagamento;
import com.evandropadrao.cursomc.enums.TipoCliente;
import com.evandropadrao.cursomc.repositories.CategoriaRepository;
import com.evandropadrao.cursomc.repositories.CidadeRepository;
import com.evandropadrao.cursomc.repositories.ClienteRepository;
import com.evandropadrao.cursomc.repositories.EnderecoRepository;
import com.evandropadrao.cursomc.repositories.EstadoRepository;
import com.evandropadrao.cursomc.repositories.ItemPedidoRepository;
import com.evandropadrao.cursomc.repositories.PagamentoRepository;
import com.evandropadrao.cursomc.repositories.PedidoRepository;
import com.evandropadrao.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));

		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));

		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "Sao Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", est1);
		Cidade c2 = new Cidade(null, "Sao Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);

		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));


		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com",
				"36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("27363323", "93838393"));

		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto. 203", "Jardim", "28220384", cli1, c1); 

		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);

		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));

		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
		
		Pedido ped1 = new Pedido(null, sdf.parse("30/01/2020 10:33"), cli1, e1); 
		Pedido ped2 = new Pedido(null, sdf.parse("25/08/2019 09:14"), cli1, e2);

		Pagamento pgto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADDO, ped1, 6); 
		ped1.setPagamento(pgto1);

		Pagamento pgto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("28/02/2020 00:00"), null); 
		ped2.setPagamento(pgto2);
						
		cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));

		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pgto1, pgto2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.0, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.0, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 10.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1, ip2, ip3));

}

}
