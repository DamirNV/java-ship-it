package ru.yandex.practicum;

import ru.yandex.practicum.delivery.parcels.FragileParcel;
import ru.yandex.practicum.delivery.parcels.ParcelBox;
import ru.yandex.practicum.delivery.parcels.PerishableParcel;
import ru.yandex.practicum.delivery.parcels.StandardParcel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ParcelBoxAddParcelTest {

    @Test
    public void addParcel_whenWeightWithinLimit_addsSuccessfully() {
        StandardParcel standardParcel = new StandardParcel("Книги", 3,
                "г. Москва, ул. Ленина, д. 45, кв. 12", 1);
        ParcelBox parcelBoxStandard = new ParcelBox(100);
        parcelBoxStandard.addParcel(standardParcel);
        int expectedCurrentBoxWeight = 3;
        int expectedParcelsCount = 1;
        assertEquals(expectedCurrentBoxWeight, parcelBoxStandard.getCurrentWeight(), "Неверный вес коробки " +
                "после добавления посылки. Метод добавления посылок в коробку работает некорректно.");
        assertEquals(expectedParcelsCount, parcelBoxStandard.getParcelsInBox().size(), "Неверное количество " +
                "посылок в коробке. Метод добавления посылок в коробку работает некорректно.");
    }

    @Test
    public void addParcel_whenWeightExceedsLimit_doesNotAdd() {
        FragileParcel fragileParcel = new FragileParcel("Хрустальный лебедь", 80,
                "г. Красноярск, ул. Мира, д. 102, кв. 35", 8);
        ParcelBox parcelBoxFragile = new ParcelBox(75);
        parcelBoxFragile.addParcel(fragileParcel);
        int expectedCurrentBoxWeight = 0;
        int expectedParcelsCount = 0;
        assertEquals(expectedCurrentBoxWeight, parcelBoxFragile.getCurrentWeight(), "Неверный вес коробки. " +
                "Метод добавления посылок в коробку работает некорректно.");
        assertEquals(expectedParcelsCount, parcelBoxFragile.getParcelsInBox().size(), "Неверное количество " +
                "посылок в коробке. Метод добавления посылок в коробку работает некорректно.");
    }

    @Test
    public void addParcel_whenWeightEqualsMaxLimit_addsSuccessfully() {
        PerishableParcel perishableparcel = new PerishableParcel("Мешок с сырками", 50,
                "г. Ростов-на-Дону, ул. Большая Садовая, д. 88, кв. 67", 7, 5);
        ParcelBox parcelBoxPerishable = new ParcelBox(50);
        parcelBoxPerishable.addParcel(perishableparcel);
        int expectedCurrentBoxWeight = 50;
        int expectedParcelsCount = 1;
        assertEquals(expectedCurrentBoxWeight, parcelBoxPerishable.getCurrentWeight(), "Неверный вес коробки " +
                "после добавления посылки. Метод добавления посылок в коробку работает некорректно.");
        assertEquals(expectedParcelsCount, parcelBoxPerishable.getParcelsInBox().size(), "Неверное количество " +
                "посылок в коробке. Метод добавления посылок в коробку работает некорректно.");

    }

}

