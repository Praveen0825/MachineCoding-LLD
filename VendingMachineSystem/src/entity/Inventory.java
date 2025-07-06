package entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
    private Map<Integer,Item> itemMap;

    public Inventory() {
        this.itemMap = new HashMap<>();
    }

    public Item getItem(int id){
        return itemMap.get(id);
    }

    public void updateStock(int id, int delta){
        Item item=itemMap.get(id);
        item.setQuantity(item.getQuantity()+delta);
    }

    public void addItem(Item item){
        itemMap.put(item.getId(), item);
    }

    public List<Item> getAllItems(){
        return new ArrayList<>( itemMap.values());
    }
}
