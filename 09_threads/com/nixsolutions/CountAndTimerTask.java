package com.nixsolutions;

/**
 * Describe task: "Разработать приложение, в котором главный поток каждые 100ms инкрементирует
 * счетчик от 1 до 1000, а 3 потока стартуют при достижении счеткика 100, 300 и 500 соответственно
 * - каждую секунду выводят в консоль сообщение о своей работе"
 *
 * @author Dmitry Zinovyi
 */
public class CountAndTimerTask {

    private volatile static int count = 1;
    private static final int CAPACITY = 1000;

    public static void main(String[] argc) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count != CAPACITY) {
                    if (count == 100) {
                        new SomeWorker("Developer").start();
                    } else if (count == 300) {
                        new SomeWorker("QA").start();
                    } else if (count == 500) {
                        new SomeWorker("BA").start();
                    }
                    ++count;
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    static class SomeWorker extends Thread {

        private String name = "Unknown name";

        SomeWorker(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            while (count != CAPACITY) {
                System.out.println("I'm " + name + ". I'm still working...");
                try {
                    this.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
