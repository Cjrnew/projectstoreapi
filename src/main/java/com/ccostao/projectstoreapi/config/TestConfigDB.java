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
		
		Category category = new Category(null, "Informática");
		Category category2 = new Category(null, "Escritório");
		
		categoryRepository.saveAll(Arrays.asList(category, category2));
		
		Product product = new Product(null, "Notebook", 2000.0);
		Product product2 = new Product(null, "Impressora", 800.0);
		Product product3 = new Product(null, "Mouse", 80.0);
		
		category.getProducts().addAll(Arrays.asList(product, product2, product3));
		category2.getProducts().addAll(Arrays.asList(product2));
		
		product.getCategories().addAll(Arrays.asList(category));
		product2.getCategories().addAll(Arrays.asList(category,category2));
		product3.getCategories().addAll(Arrays.asList(category));
		
		productRepository.saveAll(Arrays.asList(product, product2, product3));
		
		State state = new State(null, "Paraíba");
		State state2 = new State(null, "Pernambuco");
		
		City city = new City(null, "João Pessoa", state);
		City city2 = new City(null, "Campina Grande", state);
		City city3 = new City(null, "Recife", state2);
		
		state.getCities().addAll(Arrays.asList(city,city2));
		state2.getCities().addAll(Arrays.asList(city3));
		
		stateRepository.saveAll(Arrays.asList(state,state2));
		cityRepository.saveAll(Arrays.asList(city,city2,city3));
		
		Client cli = new Client(null, "Maria Silva", "maria@gmail.com", "32165498777", ClientType.NATURALPERSON);
		cli.getPhones().addAll(Arrays.asList("321654987", "987654321"));
		
		Address e1 = new Address(null, "Rua Estudante", "300", "Apt 303", "Bancários", "589646", cli, city);
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
		
		OrderItem ip1 = new OrderItem(ped1, product, 0.00, 1, 2000.00);
		OrderItem ip2 = new OrderItem(ped1, product3, 0.00, 2, 80.00);
		OrderItem ip3 = new OrderItem(ped2, product2, 100.00, 1, 800.00);

		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));

		product.getItens().addAll(Arrays.asList(ip1));
		product2.getItens().addAll(Arrays.asList(ip3));
		product3.getItens().addAll(Arrays.asList(ip2));

		orderItemRepository.saveAll(Arrays.asList(ip1, ip2, ip3));
	}
	
}
