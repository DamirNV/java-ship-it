package ru.yandex.practicum.delivery.parcels;

public abstract class Parcel {
    private String description;
    private int weight;
    private String deliveryAddress;
    private int sendDay;

    public Parcel(String description, int weight, String deliveryAddress, int sendDay) {
        this.description = description;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.sendDay = sendDay;
    }

    protected abstract int getBaseCost();

    public void packageItem(){
        System.out.println("Посылка <<" +  description + ">> упакована.");
    };

    public void deliver(){
        System.out.println("Посылка <<" + description + ">> доставлена по адресу " + deliveryAddress + ".");
    };

    public int calculateDeliveryCost(){
        return weight * getBaseCost();
    };

    public int getSendDay() {
        return sendDay;
    }

    public String getDescription() {
        return description;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public int getWeight() {
        return weight;
    }


}

