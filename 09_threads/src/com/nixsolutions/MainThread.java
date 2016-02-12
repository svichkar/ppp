package com.nixsolutions;
/*Разработать приложение, в котором главный поток каждые 100ms инкрементирует счетчик от 1 до 1000, 
а 3 потока стартуют при достижении счеткика 100, 300 и 500 соответственно -
каждую секунду выводят в консоль сообщение о своей работе
*/

public class MainThread {

	public static void main(String[] args) throws InterruptedException {
		Thread firstThread = new Thread(new OverallThread("NUMBER_1", Thread.currentThread()));
		Thread secondThread = new Thread(new OverallThread("NUMBER_2", Thread.currentThread()));
		Thread thirdThread = new Thread(new OverallThread("NUMBER_3", Thread.currentThread()));
		int key=0;

		for(int i=1; i<=1000;i++)
		{
			Thread.sleep(100);
			key = i;
			switch (key) {
			case 100:
				firstThread.start();
				break;
			case 300:
				secondThread.start();
				break;
			case 500:
				thirdThread.start();
				break;
			}
		}
	}

}
