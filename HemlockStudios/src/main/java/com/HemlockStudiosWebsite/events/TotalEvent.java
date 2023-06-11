package com.HemlockStudiosWebsite.events;


public class TotalEvent {
    

    private final Double total;

    public TotalEvent(Double total) {
        this.total = total;
    }

    public Double getTotal() {
        return total;
    }
}
