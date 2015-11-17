package com.nixsolutions.exceptions;

import java.io.File;



public class Main {
	/*
	 * Реализовать интерфейс Save (смотри приатаченый файл
	 * learn-1.0-SNAPSHOT.jar) в котором есть только один метод save
	 * предназначенный для сохранения строки в файл, который принимает два
	 * параметра: первый параметр - это стринга/текст что будет сохранятся,
	 * второй параметр - это абсолютный путь к файлу в который будет сохранятся.
	 * Если файл с таким именем не существует то его нужно будет создать. Если
	 * файл с таким именем существует то нужно будет перезаписать его
	 * содержимое. При необходимости создать свой класс исключений.
	 */

	public static void main(String[] args) {
		String path = new File(".").getAbsolutePath();
		SaveFile saveFile = new SaveFile();
		saveFile.save("blah-blah-oh-la-la", path);
	}

}
