package ru.yandex.practicum.delivery;

public class PerishableParcel extends Parcel {
    private static final int DELIVERY_COST = 3;
    private int timeToLive;

    public PerishableParcel(String description, int weight, String deliveryAddress, int sendDay, int timeToLive) {
        super(description, weight, deliveryAddress, sendDay);
        this.timeToLive = timeToLive;
    }

    @Override
    public void packageItem() {
        System.out.println("Посылка " + description + " упакована.");
    }

    @Override
    public int calculateDeliveryCost() {
        return getWeight() * DELIVERY_COST;
    }

    public boolean isExpired(int currentDay) {
        return (getSendDay() + timeToLive) <= currentDay;
    }

    public int getTimeToLive() {
        return timeToLive;
    }
}
