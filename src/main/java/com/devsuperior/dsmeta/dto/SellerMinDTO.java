package com.devsuperior.dsmeta.dto;

import com.devsuperior.dsmeta.entities.Sale;
import com.devsuperior.dsmeta.entities.Seller;

public class SellerMinDTO {

    private String sellerName;
    private Double total;

    public SellerMinDTO(String sellerName, Double total) {
        this.sellerName = sellerName;
        this.total = total;
    }

    public SellerMinDTO(Sale entity) {
        this.sellerName = entity.getSeller().getName();
        this.total = entity.getAmount();
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
