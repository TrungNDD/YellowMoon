/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trungndd.entities;

import java.util.HashMap;

/**
 *
 * @author Admin
 */
public class ShoppingCart extends HashMap<String, Cake> {

    double total;

    public ShoppingCart() {
        super();
    }

    public boolean add(Cake cake) {
        int quantity = 1;
        if (this.containsKey(cake.getIdCake())) {
            // get the cake from cart and set new quantity
            // do NOT use cake.getQuantity() this is wrong
            quantity = this.get(cake.getIdCake()).getQuantity() + 1;
        }

        // check quantity
        if (cake.getQuantity() < quantity) {
            return false;
        }
        cake.setQuantity(quantity);
        this.put(cake.getIdCake(), cake);
        return true;
    }

    public boolean update(Cake cake, int quantity) {
        if (this.containsKey(cake.getIdCake())) {
            // check quantity in stock and quantity to update
            if (cake.getQuantity() >= quantity) {
                cake.setQuantity(quantity);
                this.put(cake.getIdCake(), cake);
                return true;
            }
        }

        return false;
    }

    public double getTotal() {
        double total = 0;

        for (Cake cake : this.values()) {
            total += cake.getQuantity() * cake.getPrice();
        }

        return total;
    }
}
