package com.baggio.pedidovendas.dspedidovendas;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.baggio.pedidovendas.dspedidovendas.domain.Categoria;
import com.baggio.pedidovendas.dspedidovendas.domain.Cidade;
import com.baggio.pedidovendas.dspedidovendas.domain.Cliente;
import com.baggio.pedidovendas.dspedidovendas.domain.Endereco;
import com.baggio.pedidovendas.dspedidovendas.domain.Estado;
import com.baggio.pedidovendas.dspedidovendas.domain.Produto;
import com.baggio.pedidovendas.dspedidovendas.domain.enums.TipoCliente;
import com.baggio.pedidovendas.dspedidovendas.repository.CategoriaRepository;
import com.baggio.pedidovendas.dspedidovendas.repository.CidadeRepository;
import com.baggio.pedidovendas.dspedidovendas.repository.ClienteRepository;
import com.baggio.pedidovendas.dspedidovendas.repository.EnderecoRepository;
import com.baggio.pedidovendas.dspedidovendas.repository.EstadoRepository;
import com.baggio.pedidovendas.dspedidovendas.repository.ProdutoRepository;

@SpringBootApplication
public class DspedidovendasApplication implements CommandLineRunner{
	
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
	

	public static void main(String[] args) {
		SpringApplication.run(DspedidovendasApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ========= Categorias e Produtos ========
		
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 80.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAllAndFlush(Arrays.asList(p1, p2, p3));
				
		// ========================================= 
		// ========= Estados e Cidades =============
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		Estado est3 = new Estado(null, "Paraná");
		
		Cidade c1 = new Cidade(null, "Uberlândia", est1);
		Cidade c2 = new Cidade(null, "São Paulo", est2);
		Cidade c3 = new Cidade(null, "Campinas", est2);
		Cidade c4 = new Cidade(null, "Dois Vizinhos", est3);
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2, c3));
		est3.getCidades().addAll(Arrays.asList(c4));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2, est3));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3, c4));
		
		// ========================================= 
		// ===== Cliente, Endereco e Telefone ======
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);

		cli1.getTelefones().addAll(Arrays.asList("4627363323", "4627364456"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
	
		// ========================================= 
	}
	

}
