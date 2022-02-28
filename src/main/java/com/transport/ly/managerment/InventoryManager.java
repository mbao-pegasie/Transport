package com.transport.ly.managerment;

import com.transport.ly.dataparser.OrderParser;
import com.transport.ly.domain.DestinationAirport;
import com.transport.ly.domain.Order;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryManager {

	private List<Order> orderList;

	public InventoryManager(){
		orderList = new ArrayList<Order>();
	}

	public List<Order> getCurrentInventory(){
		return orderList;
	}

	public void loadedInventory(String fileName) throws IOException, ParseException {
		addToInventory((new OrderParser()).getOrderList(fileName));
	}

	public void addToInventory(List<Order> orderList){
		this.orderList.addAll(orderList);
	}

	public List<Order> getOrderListByDestination(DestinationAirport value){
		return this.orderList.stream().filter(s -> (s.getDestinationAirport().name()).equals(value.name())).collect(Collectors.toList());
	}

	public List<DestinationAirport> getAllDestinationAirport(List<Order> orderList){
		return orderList.stream().map(o -> o.getDestinationAirport()).distinct().collect(Collectors.toList());
	}

	public List<Order> sortOrderListByDestinationAndPriority(DestinationAirport value){
		List<Order> orderList = getOrderListByDestination(value);
		orderList.sort(Comparator.comparing(Order::getPriority));
		return orderList;
	}
}
