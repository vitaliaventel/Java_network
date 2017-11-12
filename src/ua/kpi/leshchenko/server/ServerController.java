package ua.kpi.leshchenko.server;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import ua.kpi.leshchenko.model.Product;

public class ServerController {

	private Gson gson;
	private List<Product> productStorage;

	public ServerController() {
		gson = new Gson();
		productStorage = new ArrayList<>();
	}

	public Gson getGson() {
		return gson;
	}

	public List<Product> getProductStorage() {
		return productStorage;
	}

	public Product jsonToProduct(String json) {
		return gson.fromJson(json, Product.class);
	}

	public String productToJson(Product product) {
		return gson.toJson(product);
	}

	public void addToStorage(Product product) {
		productStorage.add(product);
	}

	public Product getProductById(long id) {
		for (Product product : productStorage) {
			if (product.getId() == id) {
				return product;
			}
		}
		return null;
	}

}
