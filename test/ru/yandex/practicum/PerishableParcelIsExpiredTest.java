package ru.yandex.practicum;

import ru.yandex.practicum.delivery.parcels.PerishableParcel;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class PerishableParcelIsExpiredTest {

    @Test
    public void isExpired_whenCurrentDayAfterExpiry_returnsTrue() {
        PerishableParcel perishableparcel = new PerishableParcel("Молоко", 1,
                "г. Екатеринбург, ул. Ленина, д. 45, кв. 12", 1, 3);
        assertTrue(perishableparcel.isExpired(5));
    }

    @Test
    public void isExpired_whenCurrentDayBeforeExpiry_returnsFalse() {
        PerishableParcel perishableparcel = new PerishableParcel("Сырок", 1,
                "г. Новосибирск, ул. Сибирская, д. 123, кв. 56", 7, 5);
        assertFalse(perishableparcel.isExpired(11));
    }

    @Test
    public void isExpired_whenCurrentDayOnExpiry_returnsFalse() {
        PerishableParcel perishableparcel = new PerishableParcel("Пицца", 1,
                "г. Санкт-Петербург, ул. Невский проспект, д. 25, кв. 17", 15, 2);
        assertFalse(perishableparcel.isExpired(17));
    }

}
