package ru.job4j.dip.anotherexample;

import ru.job4j.gc.prof.Data;

public class Dipviolation {

    abstract class Tool {
        private String name;

        public Tool(String name) {
            this.name = name;
        }
    }

    class Printer extends Tool {
        public Printer(String name) {
            super(name);
        }
    }

    class PaperTrimmer extends Tool {
        public PaperTrimmer(String name) {
            super(name);
        }
    }

    class FireBucket extends Tool {
        public FireBucket(String name) {
            super(name);
        }
    }

    interface Task {
        void doTask(Tool tool);
    }

    class Print implements Task {
        @Override
        public void doTask(Tool tool) {
            System.out.println("Printing fake document");
        }
    }

    class DestroyPaper implements Task {
        @Override
        public void doTask(Tool tool) {
            System.out.println("document with visible defects to be destroyed");
        }
    }


    /**
     * Первое нарушение: В этом классе поля представлены не абстракциями, а реализациями.
     * Второе нарушение: Аргументы конструктора представлены не абстракциями, а реализациями.
     */

    class FakeDocsFactory {
        private Printer printer;
        private PaperTrimmer paperTrimmer;
        private FireBucket fireBucket;
        private Print print;
        private DestroyPaper destroyPaper;

        public FakeDocsFactory(Printer printer, PaperTrimmer paperTrimmer, FireBucket fireBucket,
                               Print print, DestroyPaper destroyPaper) {
            this.printer = printer;
            this.paperTrimmer = paperTrimmer;
            this.fireBucket = fireBucket;
            this.print = print;
            this.destroyPaper = destroyPaper;
        }

        /**
         * Третьим нарушением является аргумент метода, представленный не абстракцией, а реализацией.
         * Поэтому мы не можем использовать для этого действия с другим Tool, например, fireBucket.
         */

        public void destroyDocs(DestroyPaper destroyPaper, PaperTrimmer paperTrimmer) {
            destroyPaper.doTask(paperTrimmer);
            System.out.println("docs were destroyed by " + paperTrimmer);
        }

    }


}
