package ru.yandex.practicum.delivery.parcels;

import java.util.ArrayList;

public class ParcelBox<T extends Parcel> {
    private final int maxWeight;
    private ArrayList<T> parcelsInBox;
    private int currentWeight;

    public ParcelBox(int maxWeight) {
        this.maxWeight = maxWeight;
        this.parcelsInBox = new ArrayList<>();;
        this.currentWeight = 0;
    }

    public void addParcel(T parcel) {
        if (currentWeight + parcel.getWeight() <= maxWeight) {
            parcelsInBox.add(parcel);
            currentWeight += parcel.getWeight();
            System.out.println("Посылка <<" + parcel.getDescription() + ">> добавлена в коробку. " +
                    "Текущий вес коробки: " + currentWeight + " кг.");
        } else {
            System.out.println("Ошибка: превышен максимальный вес коробки! " +
                    "Нельзя добавить посылку <<" + parcel.getDescription() + ">> весом " + parcel.getWeight() +
                    ". Текущий вес: " + currentWeight + " кг. Максимальный вес: " + maxWeight + " кг.");
        }
    }

    public void getAllParcels() {
        if (parcelsInBox.isEmpty()) {
            System.out.println("Коробка пуста.");
            return;
        }
        System.out.println("Содержимое коробки:");
        for (T parcel : parcelsInBox) {
            System.out.println("Посылка <<" + parcel.getDescription() + ">> находится в коробке.");
        }
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public ArrayList<T> getParcelsInBox() {
        return parcelsInBox;
    }


}
