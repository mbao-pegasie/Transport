package com.transport.ly.util;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

public class Helper {

	private static final String FILE_SOURCE = "src/main/resources/orders_raw_data.json";
	private static final String FINAL_DESTINATION = "src/main/resources/Coding-assigment-orders.json";

	//Helper function to convert Json file to Json array file:
	public static void rawDataConverter() throws IOException, ParseException {

		JSONParser jsonParser = new JSONParser();
		JSONArray orderList = new JSONArray();
		try(FileReader reader = new FileReader(FILE_SOURCE)) {
			Object obj = jsonParser.parse(reader);
			JSONObject order = (JSONObject) obj;
			for(Iterator iterator = order.keySet().iterator(); iterator.hasNext();) {
				String key = (String) iterator.next();
				String orderNumber = key;
				int priority = Integer.valueOf(key.substring(key.indexOf('-') + 1));
				JSONObject destinationObject = (JSONObject)order.get(key);
				String destinationAirport = (String) destinationObject.get("destination");
				JSONObject newJsonObject = new JSONObject();
				newJsonObject.put("orderNumber", key);
				newJsonObject.put("priority", priority);
				newJsonObject.put("destinationAirport", destinationAirport);
				orderList.add(newJsonObject);
			}
		}

		try (FileWriter file = new FileWriter(FINAL_DESTINATION)) {
			//We can write any JSONArray or JSONObject instance to the file
			file.write(orderList.toJSONString());
			file.flush();
		}
	}
}
