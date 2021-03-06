package com.ccostao.projectstoreapi.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.ccostao.projectstoreapi.domain.Address;
import com.ccostao.projectstoreapi.domain.Category;
import com.ccostao.projectstoreapi.domain.City;
import com.ccostao.projectstoreapi.domain.Client;
import com.ccostao.projectstoreapi.domain.Order;
import com.ccostao.projectstoreapi.domain.OrderItem;
import com.ccostao.projectstoreapi.domain.Payment;
import com.ccostao.projectstoreapi.domain.PaymentBoleto;
import com.ccostao.projectstoreapi.domain.PaymentCreditCard;
import com.ccostao.projectstoreapi.domain.Product;
import com.ccostao.projectstoreapi.domain.State;
import com.ccostao.projectstoreapi.domain.enums.ClientType;
import com.ccostao.projectstoreapi.domain.enums.PaymentStatus;
import com.ccostao.projectstoreapi.repository.AddressRepository;
import com.ccostao.projectstoreapi.repository.CategoryRepository;
import com.ccostao.projectstoreapi.repository.CityRepository;
import com.ccostao.projectstoreapi.repository.ClientRepository;
import com.ccostao.projectstoreapi.repository.OrderItemRepository;
import com.ccostao.projectstoreapi.repository.OrderRepository;
import com.ccostao.projectstoreapi.repository.PaymentRepository;
import com.ccostao.projectstoreapi.repository.ProductRepository;
import com.ccostao.projectstoreapi.repository.StateRepository;

@Configuration
@Profile("test")
public class TestConfigDB implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category category1 = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");
		Category category3 = new Category(null, "Cama mesa e banho");
		Category category4 = new Category(null, "Eletrônicos");
		Category category5 = new Category(null, "Jardinagem");
		Category category6 = new Category(null, "Decoração");
		Category category7 = new Category(null, "Perfumaria");
		
		categoryRepository.saveAll(Arrays.asList(
				category1, category2, category3, category4, category5, category6, category7));
		
		Product product1 = new Product(null, "Notebook", 2000.0);
		Product product2 = new Product(null, "Impressora", 800.0);
		Product product3 = new Product(null, "Mouse", 80.0);
		Product product4 = new Product(null, "Mesa de escritório", 300.00);
		Product product5 = new Product(null, "Toalha", 50.00);
		Product product6 = new Product(null, "Colcha", 200.00);
		Product product7 = new Product(null, "TV true color", 1200.00);
		Product product8 = new Product(null, "Roçadeira", 800.00);
		Product product9 = new Product(null, "Abajour", 100.00);
		Product product10 = new Product(null, "Pendente", 180.00);
		Product product11 = new Product(null, "Shampoo", 90.00);
		
		category1.getProducts().addAll(Arrays.asList(product1, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2, product4));
		category3.getProducts().addAll(Arrays.asList(product5, product6));
		category4.getProducts().addAll(Arrays.asList(product1, product2, product3, product7));
		category5.getProducts().addAll(Arrays.asList(product8));
		category6.getProducts().addAll(Arrays.asList(product9, product10));
		category7.getProducts().addAll(Arrays.asList(product11));
		
		product1.getCategories().addAll(Arrays.asList(category1, category4));
		product2.getCategories().addAll(Arrays.asList(category1, category2, category4));
		product3.getCategories().addAll(Arrays.asList(category1, category4));
		product4.getCategories().addAll(Arrays.asList(category2));
		product5.getCategories().addAll(Arrays.asList(category3));
		product6.getCategories().addAll(Arrays.asList(category3));
		product7.getCategories().addAll(Arrays.asList(category4));
		product8.getCategories().addAll(Arrays.asList(category5));
		product9.getCategories().addAll(Arrays.asList(category6));
		product10.getCategories().addAll(Arrays.asList(category6));
		product11.getCategories().addAll(Arrays.asList(category7));
		
		productRepository.saveAll(Arrays.asList(product1, product2, product3
				, product4, product5, product6
				, product7, product8, product9
				, product10, product11));
		
		State state1 = new State(null, "Paraíba");
		State state2 = new State(null, "Pernambuco");
		
		City city1 = new City(null, "João Pessoa", state1);
		City city2 = new City(null, "Campina Grande", state1);
		City city3 = new City(null, "Recife", state2);
		
		state1.getCities().addAll(Arrays.asList(city1,city2));
		state2.getCities().addAll(Arrays.asList(city3));
		
		stateRepository.saveAll(Arrays.asList(state1,state2));
		cityRepository.saveAll(Arrays.asList(city1,city2,city3));
		
		Client cli = new Client(null, "Maria Silva", "maria@gmail.com", "32165498777", ClientType.NATURALPERSON);
		cli.getPhones().addAll(Arrays.asList("321654987", "987654321"));
		
		Address e1 = new Address(null, "Rua Estudante", "300", "Apt 303", "Bancários", "589646", cli, city1);
		Address e2 = new Address(null, "Rua das Colinas", "277", "Casa 107", "Hill", "365549788", cli, city2);
		
		cli.getAdresses().addAll(Arrays.asList(e1,e2));
		
		clientRepository.saveAll(Arrays.asList(cli));
		addressRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Order ped1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli, e1);
		Order ped2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli, e2);

		Payment pagto1 = new PaymentCreditCard(null, PaymentStatus.PAID, ped1, 6);
		ped1.setPayment(pagto1);

		Payment pagto2 = new PaymentBoleto(null, PaymentStatus.PENDING, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPayment(pagto2);

		cli.getOrders().addAll(Arrays.asList(ped1, ped2));

		orderRepository.saveAll(Arrays.asList(ped1, ped2));
		paymentRepository.saveAll(Arrays.asList(pagto1, pagto2));
		
		OrderItem ip1 = new OrderItem(ped1, product1, 0.00, 1, 2000.00);
		OrderItem ip2 = new OrderItem(ped1, product3, 0.00, 2, 80.00);
		OrderItem ip3 = new OrderItem(ped2, product2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		product1.getItens().addAll(Arrays.asList(ip1));
		product2.getItens().addAll(Arrays.asList(ip3));
		product3.getItens().addAll(Arrays.asList(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	
}
