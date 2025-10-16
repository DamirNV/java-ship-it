package ru.yandex.practicum;

import ru.yandex.practicum.delivery.parcels.FragileParcel;
import ru.yandex.practicum.delivery.parcels.PerishableParcel;
import ru.yandex.practicum.delivery.parcels.StandardParcel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class CalculateDeliveryCostTest {

    @Test
    public void calculateDeliveryCost_whenStandardParcelWithWeight15_returns30() {
        StandardParcel standardParcel = new StandardParcel("Гиря", 15, "г. Москва, " +
                "ул. Кремлевская, д. 1, кв. 100", 4);
        int ExpectedDeliveryCost = 30;
        assertEquals(ExpectedDeliveryCost, standardParcel.calculateDeliveryCost(), "Стоимость доставки для " +
                "стандартных посылок рассчитывается неверно.");
    }

    @Test
    public void calculateDeliveryCost_whenFragileParcelWithWeight2_returns8() {
        FragileParcel fragileParcel = new FragileParcel("Тарелка", 2, "г. Казань, " +
                "ул. Меридианная, д. 3, кв. 85", 7);
        int ExpectedDeliveryCost = 8;
        assertEquals(ExpectedDeliveryCost, fragileParcel.calculateDeliveryCost(), "Стоимость доставки для " +
                "хрупких посылок рассчитывается неверно.");
    }

    @Test
    public void calculateDeliveryCost_whenPerishableParcelWithWeight5_returns15() {
        PerishableParcel perishableParcel  = new PerishableParcel("Яблочный пирог", 5,
                "г. Пенза, " + "ул. Ленина, д. 83, кв. 7", 15, 5);
        int ExpectedDeliveryCost = 15;
        assertEquals(ExpectedDeliveryCost, perishableParcel.calculateDeliveryCost(), "Стоимость доставки для " +
                "скоропортящихся посылок рассчитывается неверно.");
    }

    @Test
    public void calculateDeliveryCost_whenStandardParcelWithWeight0_returns0() {
        StandardParcel standardParcel = new StandardParcel("Вата", 0, "г. Нижнекамск, " +
                "ул. Мира, д. 444, кв. 104", 4);
        int ExpectedDeliveryCost = 0;
        assertEquals(ExpectedDeliveryCost, standardParcel.calculateDeliveryCost(), "Стоимость доставки для " +
                "стандартных посылок с весом 0 рассчитывается неверно.");
    }

    @Test
    public void calculateDeliveryCost_whenFragileParcelWithWeight0_returns0() {
        FragileParcel fragileParcel = new FragileParcel("Хрустальная иголка", 0,
                "г. Снежинск, ул. Снежная, д. 65, кв. 77", 8);
        int ExpectedDeliveryCost = 0;
        assertEquals(ExpectedDeliveryCost, fragileParcel.calculateDeliveryCost(), "Стоимость доставки для " +
                "хрупких посылок с весом 0 рассчитывается неверно.");
    }

    @Test
    public void calculateDeliveryCost_whenPerishableParcelWithWeight0_returns0() {
        PerishableParcel perishableParcel  = new PerishableParcel("Конфета", 0,
                "г. Самара, " + "ул. Химиков, д. 45, кв. 73", 5, 10);
        int ExpectedDeliveryCost = 0;
        assertEquals(ExpectedDeliveryCost, perishableParcel.calculateDeliveryCost(), "Стоимость доставки для " +
                "скоропортящихся посылок рассчитывается неверно.");
    }

}
