package com.mehrdadmoradli.springboot_ecommerce.dto;

import java.math.BigDecimal;

public class OrderItemResponseDto {

    private Long orderId;
    private Long productId;
    private String productName;
    private BigDecimal priceAtPurchase;
    private Integer quantity;
    private BigDecimal price;
    

    public OrderItemResponseDto() {
    }


    public OrderItemResponseDto(Long orderId, Long productId, String productName,
                                BigDecimal priceAtPurchase, Integer quantity, BigDecimal price) {
        this.orderId = orderId;
        this.productId = productId;
        this.productName = productName;
        this.priceAtPurchase = priceAtPurchase;
        this.quantity = quantity;
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPriceAtPurchase() {
        return priceAtPurchase;
    }

    public void setPriceAtPurchase(BigDecimal priceAtPurchase) {
        this.priceAtPurchase = priceAtPurchase;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}