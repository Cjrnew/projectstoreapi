package com.ccostao.projectstoreapi.service;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.ccostao.projectstoreapi.domain.PaymentBoleto;

@Service
public class BoletoService {
	
	public void preencherPagamentoComBoleto(PaymentBoleto pagto, Date instantOfOrder) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(instantOfOrder);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagto.setDueDateo(cal.getTime());
	}
	
}
