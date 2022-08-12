package com.neoris.transactionservice.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.neoris.transactionservice.dto.request.TransactionRequest;
import com.neoris.transactionservice.service.ITransactionService;

@ActiveProfiles(profiles = "test")
@WebMvcTest(controllers = TransactionController.class)
@ExtendWith(SpringExtension.class)
public class TransactionControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ITransactionService transactionService;

	private ObjectMapper objectMapper;

	private TransactionRequest transactionRequest;

	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());

		transactionRequest = new TransactionRequest();
		transactionRequest.setMovimiento(new BigDecimal(10.0));
		transactionRequest.setNumCuenta("111111");

	}

	@Test
	void getTransactionByAccountNumber() throws Exception {

		mvc.perform(get("/movimientos/1").contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	void saveTransaction() throws Exception {

		mvc.perform(post("/movimientos").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(transactionRequest))).andDo(print())
				.andExpect(status().isOk());
	}
}
