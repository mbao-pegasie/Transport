package com.transport.ly.dataparser;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.transport.ly.domain.Order;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OrderParser {

	public List<Order> getOrderList(String fileName) throws IOException {
		ObjectMapper objectMapper = new ObjectMapper();
		List<Order> orderList = objectMapper.readValue(
				new File(fileName),
				new TypeReference<List<Order>>() {
				});
		return orderList;
	}

}
