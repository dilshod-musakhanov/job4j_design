package ru.job4j.lspviolation;

/**
 * Класс нарушеат принцип LSP:
 * Постусловия (Postconditions) не могут быть ослаблены в подклассе TrainTicketCashier
 */

public class LspViolationTwo {

    public class FlightTicketCashier {
        int soldTickets;
        int wrongTickets;

        public FlightTicketCashier(int soldTickets, int wrongTickets) {
            this.soldTickets = soldTickets;
            this.wrongTickets = wrongTickets;
        }

        public void getBonus() {
            if (5 > wrongTickets && 100 < soldTickets) {
                System.out.println("15% bonus added the coming salary");
            }
        }
    }

    public class TrainTicketCashier extends FlightTicketCashier {

        public TrainTicketCashier(int soldTickets, int wrongTickets) {
            super(soldTickets, wrongTickets);
        }

        @Override
        public void getBonus() {
            if (10 > wrongTickets && 50 < soldTickets) {
                System.out.println("15% bonus added the coming salary");
            }

        }
    }

}
