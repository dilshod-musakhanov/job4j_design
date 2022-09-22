package ru.job4j.isp;

public class IspViolation {
    /**
     * Нарушение принципа ISP
     * Слишком много методов в одном интерфейсе
     * Не каждоя техника SelfCooker сможет реализовать все методы.
     * Правильно было бы разделить их на несколько интерфейсов.
     */

    interface SelfCooker {
        void bake(String item);

        void steam(String item);

        void barbeque(String item);

        void fry(String item);

        void boil(String item);

        void warmUp(String item);
    }


    /**
     * Нарушение принципа ISP
     * Не каждое траспортное средство перевозит и груз и людей и животных одновременно.
     * Некоторые реализации не смогут реализовать какой-то из методов.
     * Правильно было бы разделить их на три интерфейса.
     */

    interface Transport {
        void transportCargo(double weight);

        void transportPeople(int amount);

        void transportAnimals(int amount);
    }


    /**
     * Нарушение принципа ISP
     * Некоторые реализации Ваучэров могут и не реализовать какой-то из методов.
     * Правильно было бы разделить их на три интерфейса (ShoppingVoucher, DiningVoucher, EntertainmentVoucher)
     */

    interface Voucher {
        void getDiscountOnGrocery(int amount);

        void getDiscountOnCinema(int amount);

        void getDiscountOnPizzaHut(int amount);
    }

}
