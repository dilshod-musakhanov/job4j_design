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
    public void whenBelow25PctSendToWarehouse() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food cheeseCake = new CheeseCake(
                "cheese cake",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(31),
                150.00,
                0
        );

        controlQuality.checkAndDistribute(cheeseCake);
        Food expected = warehouse.findByNameAndReturnFood("cheese cake");
        assertThat(expected, is(cheeseCake));
    }

    @Test
    public void whenBetween25PctAnd75PctSendToShop() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food cheeseCakeSpecial = new CheeseCake(
                "cheese cake special",
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(9),
                180.00,
                0
        );
        controlQuality.checkAndDistribute(cheeseCakeSpecial);
        Food expected = shop.findByNameAndReturnFood("cheese cake special");
        assertThat(expected, is(cheeseCakeSpecial));
    }

    @Test
    public void whenAbove75PctSendToShopOnDiscount() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food homeBread = new HomeBread(
                "home bread",
                LocalDate.now().minusDays(7),
                LocalDate.now().plusDays(2),
                100.00,
                0
        );
        controlQuality.checkAndDistribute(homeBread);
        Food expected = shop.findByNameAndReturnFood("home bread");
        assertThat(expected.getPrice(), is(80.0));
    }

    @Test
    public void whenExpiration100PctAndAboveSendToTrash() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food homeBreadHoney = new HomeBread(
                "home bread with honey",
                LocalDate.now().minusDays(5),
                LocalDate.now(),
                120.00,
                0
        );
        controlQuality.checkAndDistribute(homeBreadHoney);
        Food expected = trash.findByNameAndReturnFood("home bread with honey");
        assertThat(expected, is(homeBreadHoney));
    }

    @Test
    public void whenFindAllInShop() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food homeBread1 = new HomeBread(
                "home butter bread",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                110.00,
                0
        );
        Food homeBread2 = new HomeBread(
                "home cheese bread",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                110.00,
                0
        );
        Food homeBread3 = new HomeBread(
                "home honey bread",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(10),
                110.00,
                0
        );
        Food homeBread4 = new HomeBread(
                "home garlic bread",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(50),
                110.00,
                0
        );
        controlQuality.checkAndDistribute(homeBread1);
        controlQuality.checkAndDistribute(homeBread2);
        controlQuality.checkAndDistribute(homeBread3);
        controlQuality.checkAndDistribute(homeBread4);
        List<Food> expected = shop.findAll();
        assertThat(expected, is(List.of(
                homeBread1, homeBread2, homeBread3
        )));
    }

    @Test
    public void whenListOfFoodDistributed() {
        ControlQuality controlQuality = new ControlQuality(stores);
        Food cheeseCake = new CheeseCake(
                "cheese cake",
                LocalDate.now().minusDays(10),
                LocalDate.now().plusDays(31),
                150.00,
                0
        );
        Food cheeseCakeSpecial = new CheeseCake(
                "cheese cake special",
                LocalDate.now().minusDays(3),
                LocalDate.now().plusDays(9),
                180.00,
                0
        );
        Food homeBreadHoney = new HomeBread(
                "home bread with honey",
                LocalDate.now().minusDays(5),
                LocalDate.now(),
                120.00,
                0
        );
        controlQuality.checkAndDistributeListOfFood(
                List.of(cheeseCake, cheeseCakeSpecial, homeBreadHoney)
        );
        Food expected1 = warehouse.findByNameAndReturnFood("cheese cake");
        Food expected2 = shop.findByNameAndReturnFood("cheese cake special");
        Food expected3 = trash.findByNameAndReturnFood("home bread with honey");
        assertThat(expected1, is(cheeseCake));
        assertThat(expected2, is(cheeseCakeSpecial));
        assertThat(expected3, is(homeBreadHoney));
    }

}