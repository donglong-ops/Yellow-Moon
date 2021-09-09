/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.cart;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;
import javax.naming.NamingException;
import longdh.cake.CakeDAO;
import longdh.cake.CakeDTO;



/**
 *
 * @author Dong Long
 */
public class CartObject implements Serializable {

    private CakeDTO cakeDto;
    public Map<Integer, Integer> items;
    public Map<Integer, CakeDTO> cakes;
    public Map<Integer, CakeDTO> listCake;
   

    public CakeDTO getCakeDto() {
        return cakeDto;
    }

    public void setCakeDto(CakeDTO cakeDto) {
        this.cakeDto = cakeDto;
    }

    public Map<Integer, CakeDTO> getCake() {
        return cakes;
    }

    public void setCake(Map<Integer, CakeDTO> cake) {
        this.cakes = cake;
    }

    public Map<Integer, Integer> getItems() {
        return items;
    }


    public void addItemToCart(int cakeId) throws SQLException, NamingException {
        if (cakeId == 0) {
            return;
        }
        if (this.items == null) {
            this.items = new HashMap<>();
        }
        int amount = 1;
        if (this.items.containsKey(cakeId)) {
            amount = this.items.get(cakeId) + 1;
        }

        this.items.put(cakeId, amount); 
        CakeDAO dao = new CakeDAO();
        CakeDTO dto = dao.getCakeByID(cakeId); 

        if (cakes == null) {
            cakes = new HashMap<>();
        }
        cakes.put(cakeId, dto);
    }

    public boolean updateItem(int cakeId, int amount) {
        if (amount <= 0) {
            return false;
        }

        if (items.containsKey(cakeId)) {
            items.put(cakeId, amount);
        }
        return true;
    }

    public void removeItemFromCart(int cakeId) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(cakeId)) {
            this.items.remove(cakeId);
        }

        if (this.cakes == null) {
            return;
        }
        if (this.cakes.containsKey(cakeId)) {
            this.cakes.remove(cakeId);
        }
    }

    public float getTotalPrice() {
        float total = 0;
        if (cakes != null && items != null) {
            for (Integer foodId : cakes.keySet()) {
                if (items != null) {
                    int amount = items.get(foodId);
                    float price = cakes.get(foodId).getCakePrice();
                    total += amount * price;   
                }
            }
        }
        return total;
    }
      
    public String getTotalPriceDisplay() {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(getTotalPrice());
    }

    public float getPriceOfItems(int cakeId) {
        float priceOfItems = 0;
        if (cakes != null && items != null) {
            int amount = items.get(cakeId);
            float price = cakes.get(cakeId).getCakePrice();
            priceOfItems = amount * price;

        }
        return priceOfItems;
    }
    public String getPriceOfEachItemDisplay(int cakeId) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(getPriceOfItems(cakeId));
    }
  
    public String getPriceOfItemsDisplay(int cakeId) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(getTotalPrice());
    }

    public String getPriceDisplay(int cakeId) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(cakes.get(cakeId).getCakePrice());
    }
}
