package com.ccostao.projectstoreapi.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ccostao.projectstoreapi.domain.Order;
import com.ccostao.projectstoreapi.domain.OrderItem;
import com.ccostao.projectstoreapi.domain.PaymentBoleto;
import com.ccostao.projectstoreapi.domain.enums.PaymentStatus;
import com.ccostao.projectstoreapi.repository.OrderItemRepository;
import com.ccostao.projectstoreapi.repository.OrderRepository;
import com.ccostao.projectstoreapi.repository.PaymentRepository;
import com.ccostao.projectstoreapi.service.exception.ObjectNotFoundException;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repo;
	
	@Autowired
	private BoletoService boletoService;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private ProductService productService;
	
	public List<Order> findAll() {
		List<Order> order = repo.findAll();
		return order;
	}
	
	public Order find(Integer id) {
		Optional<Order> order = repo.findById(id);
		return order.orElseThrow(() -> new ObjectNotFoundException("Object not found! ID: " + id + ", Type: " + Order.class.getName()));
	}

	public Order insert(Order obj) {
		obj.setId(null);
		obj.setInstante(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDING);
		obj.getPayment().setOrder(obj);
		if (obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto pagto = (PaymentBoleto) obj.getPayment();
			boletoService.preencherPagamentoComBoleto(pagto, obj.getInstante());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for (OrderItem ip : obj.getItens()) {
			ip.setDiscount(0.0);
			ip.setPrice(productService.find(ip.getProduct().getId()).getPrice());
			ip.setOrder(obj);
		}
		orderItemRepository.saveAll(obj.getItens());
		return obj;
	}
	
}
