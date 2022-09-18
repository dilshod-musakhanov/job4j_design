package ru.job4j.lsp;

import java.time.LocalDate;
import java.util.List;

public class ControlQuality {
    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void checkAndDestribute(Food food) {
        for (Store store : stores) {
            store.add(food);
        }
    }

    public static void main(String[] args) {
        ControlQuality controlQuality = new ControlQuality(
                List.of(new Warehouse(), new Shop(), new Trash())
        );
        Food cheeseCake = new CheeseCake(
                "cheese cake", LocalDate.now().minusDays(10), LocalDate.now().minusDays(1), 100.00
        );
        Food cheeseCake2 = new CheeseCake(
                "cheese cake", LocalDate.now().minusDays(3), LocalDate.now().plusDays(9), 100.00
        );
        Food meetBalls = new MeetBalls(
                "meat balls", LocalDate.now().minusDays(10), LocalDate.now().plusDays(10), 150.00
        );
        Food homeBread = new HomeBread(
                "home bread", LocalDate.now().minusDays(2), LocalDate.now().plusDays(3), 50.00
        );
        controlQuality.checkAndDestribute(cheeseCake);
        controlQuality.checkAndDestribute(cheeseCake2);
        controlQuality.checkAndDestribute(meetBalls);
        controlQuality.checkAndDestribute(homeBread);
    }
}
