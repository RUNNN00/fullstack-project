package com.ruanazevedo.fullstackprojectbackend.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.ruanazevedo.fullstackprojectbackend.domain.Adress;
import com.ruanazevedo.fullstackprojectbackend.domain.Category;
import com.ruanazevedo.fullstackprojectbackend.domain.City;
import com.ruanazevedo.fullstackprojectbackend.domain.Client;
import com.ruanazevedo.fullstackprojectbackend.domain.Order;
import com.ruanazevedo.fullstackprojectbackend.domain.OrderItem;
import com.ruanazevedo.fullstackprojectbackend.domain.Payment;
import com.ruanazevedo.fullstackprojectbackend.domain.PaymentCard;
import com.ruanazevedo.fullstackprojectbackend.domain.PaymentSlip;
import com.ruanazevedo.fullstackprojectbackend.domain.Product;
import com.ruanazevedo.fullstackprojectbackend.domain.State;
import com.ruanazevedo.fullstackprojectbackend.domain.enums.ClientType;
import com.ruanazevedo.fullstackprojectbackend.domain.enums.StatePayment;
import com.ruanazevedo.fullstackprojectbackend.repositories.AdressRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.CategoryRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.CityRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.ClientRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.OrderItemRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.OrderRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.PaymentRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.ProductRepository;
import com.ruanazevedo.fullstackprojectbackend.repositories.StateRepository;

@Configuration
public class Test implements CommandLineRunner {

	@Autowired
	private CategoryRepository categoryRepos;
	
	@Autowired
	private ProductRepository productRepos;
	
	@Autowired
	private StateRepository stateRepos;
	
	@Autowired
	private CityRepository cityRepos;
	
	@Autowired
	private ClientRepository clientRepos;
	
	@Autowired
	private AdressRepository adressRepos;
	
	@Autowired
	private OrderRepository orderRepos;
	
	@Autowired
	private PaymentRepository paymentRepos;
	
	@Autowired
	private OrderItemRepository orderItemRepos;
	
	@Override
	public void run(String... args) throws Exception {
		
		Category cat1 = new Category(null, "Informática");
		Category cat2 = new Category(null, "Escritório");
		
		Product prod1 = new Product(null, "Computador", 2000.00);
		Product prod2 = new Product(null, "Impressora", 800.00);
		Product prod3 = new Product(null, "Mouse", 80.00);
		
		cat1.getProducts().addAll(Arrays.asList(prod1,prod2, prod3));
		cat2.getProducts().add(prod2);
		
		prod1.getCategories().add(cat1);
		prod2.getCategories().addAll(Arrays.asList(cat1, cat2));
		prod3.getCategories().add(cat1);
		
		categoryRepos.saveAll(Arrays.asList(cat1, cat2));
		productRepos.saveAll(Arrays.asList(prod1, prod2, prod3));
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		stateRepos.saveAll(Arrays.asList(st1, st2));
		cityRepos.saveAll(Arrays.asList(c1, c2, c3));
		
		Client cli1 = new Client(null, "Maria Silva", "maria@gmail.com", "10866705513", ClientType.PESSOA_FISICA);
		
		String t1 = "27363323";
		String t2 = "93838393";
		cli1.getPhones().addAll(Arrays.asList(t1, t2));
		
		Adress adr1 = new Adress(null, "Rua Flores", "300", "Apto 203", "Jardim", "38220834", c1, cli1);
		Adress adr2 = new Adress(null, "Avenida Matos", "105", "Sala 800", "Centro", "38777012", c2, cli1);
		
		clientRepos.save(cli1);
		adressRepos.saveAll(Arrays.asList(adr1, adr2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Order o1 = new Order(null, sdf.parse("30/09/2017 10:32"), cli1, adr1);
		Order o2 = new Order(null, sdf.parse("10/10/2017 19:35"), cli1, adr2);
		
		Payment pagto1 = new PaymentCard(null, StatePayment.SETTLED, o1, 6);
		o1.setPayment(pagto1);
		
		Payment pagto2 = new PaymentSlip(null, StatePayment.PENDING, o2, sdf.parse("30/10/2017 00:00"), null);
		o2.setPayment(pagto2);
		
		cli1.getOrders().addAll(Arrays.asList(o2, o2));
		
		orderRepos.saveAll(Arrays.asList(o1, o2));
		paymentRepos.saveAll(Arrays.asList(pagto1, pagto2));
		
		OrderItem oi1 = new OrderItem(o1, prod1, 0.0, 1, 2000.0);
		OrderItem oi2 = new OrderItem(o1, prod3, 0.0, 2, 80.0);
		OrderItem oi3 = new OrderItem(o2, prod2, 100.0, 1, 800.0);
		
		o1.getItems().addAll(Arrays.asList(oi1, oi2));
		o2.getItems().add(oi3);
		
		prod1.getItems().add(oi1);
		prod2.getItems().add(oi3);
		prod3.getItems().add(oi2);
		
		orderItemRepos.saveAll(Arrays.asList(oi1, oi2, oi3));
	}
}