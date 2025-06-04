package service;

import entity.Restaurant;

import java.util.*;

public class RestaurantService {
    Map<String, Restaurant> restaurants = new HashMap<>();

    public void onboardRestaurant(String name, int capacity, double rating) {
        restaurants.put(name, new Restaurant(name, capacity, rating));
    }

    public void updateMenu(String name, String item, int price) {
        Restaurant r = restaurants.get(name);
        if (r != null) r.addOrUpdateItem(item, price);
    }

    public void updateCapacity(String name, int newCap) {
        Restaurant r = restaurants.get(name);
        if (r != null) r.maxCapacity = newCap;
    }

    public Collection<Restaurant> getAll() {
        return restaurants.values();
    }

    public Restaurant get(String name) {
        return restaurants.get(name);
    }
}
