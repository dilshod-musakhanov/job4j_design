package ru.job4j.lsp;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class ControlQualityTest {

    List<Store> stores;
    Store warehouse = new Warehouse();
    Store shop = new Shop();
    Store trash = new Trash();

    @Before
    public void initData() {
        stores = (List.of(warehouse, shop, trash));
    }

    @Test
    public void whenSentToWarehouse() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food cheeseCake = new CheeseCake(
                "cheese cake", LocalDate.now().minusDays(3), LocalDate.now().plusDays(10), 100.00
        );
        controlQuality.checkAndDestribute(cheeseCake);
        Food expected = warehouse.findByNameAndReturnFood("cheese cake");
        assertThat(expected, is(cheeseCake));
    }

    @Test
    public void whenSentToShop() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food meetBalls = new MeetBalls(
                "meet balls", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 150.00
        );
        controlQuality.checkAndDestribute(meetBalls);
        Food expected = shop.findByNameAndReturnFood("meet balls");
        assertThat(expected, is(meetBalls));
    }

    @Test
    public void whenSentToTrash() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food meetBalls = new MeetBalls(
                "spicy meet balls", LocalDate.now().minusDays(10), LocalDate.now().minusDays(1), 170.00
        );
        controlQuality.checkAndDestribute(meetBalls);
        Food expected = trash.findByNameAndReturnFood("spicy meet balls");
        assertThat(expected, is(meetBalls));
    }

    @Test
    public void whenSentToShopOnDiscount() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food homeBread = new HomeBread(
                "home bread", LocalDate.now().minusDays(10), LocalDate.now().plusDays(1), 80.00);
        controlQuality.checkAndDestribute(homeBread);
        Food expected = shop.findByNameAndReturnFood("home bread");
        assertThat(expected.getPrice(), is(64.0));
    }

    @Test
    public void whenFindAllInShop() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food homeBread1 = new HomeBread(
                "home butter bread", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 80.00
        );
        Food homeBread2 = new HomeBread(
                "home cheese bread", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 80.00
        );
        Food homeBread3 = new HomeBread(
                "home honey bread", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 80.00
        );
        controlQuality.checkAndDestribute(homeBread1);
        controlQuality.checkAndDestribute(homeBread2);
        controlQuality.checkAndDestribute(homeBread3);
        List<Food> expected = shop.findAll();
        assertThat(expected.size(), is(3));
    }

    @Test
    public void whenSameFoodInShop() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food homeBread1 = new HomeBread(
                "home butter bread", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 80.00
        );
        Food homeBread2 = new HomeBread(
                "home butter bread", LocalDate.now().minusDays(9), LocalDate.now().plusDays(9), 80.00
        );
        Food homeBread3 = new HomeBread(
                "home butter bread", LocalDate.now().minusDays(8), LocalDate.now().plusDays(8), 80.00
        );
        controlQuality.checkAndDestribute(homeBread1);
        controlQuality.checkAndDestribute(homeBread2);
        controlQuality.checkAndDestribute(homeBread3);
        List<Food> expected = shop.findByNameAndReturnFoodList("home butter bread");
        assertThat(expected.size(), is(3));
    }

}