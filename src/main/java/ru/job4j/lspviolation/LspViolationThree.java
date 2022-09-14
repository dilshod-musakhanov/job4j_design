package ru.job4j.lspviolation;

/**
 * Класс нарушеат принцип LSP:
 * в классе TownLibrary не выполнено условие базового класса.
 */

public class LspViolationThree {

    public class StateLibrary {

        private String membershipID;

        private String status;

        public StateLibrary(String membershipID, String status) {
            this.membershipID = membershipID;
            this.status = status;
        }

        public void validateMember(String membershipID, String status) {
            if (membershipID.startsWith("#123") && status.contains("active")) {
                System.out.println("Member validated");
            }
        }
        public void access(String membershipID, String status) {
            validateMember(membershipID, status);
            System.out.println("Library access granted");
        }
    }

    public class TownLibrary extends StateLibrary {

        public TownLibrary(String membershipID, String status) {
            super(membershipID, status);
        }

        @Override
        public void access(String membershipID, String status) {
            System.out.println("Library access granted");
        }
    }
}
