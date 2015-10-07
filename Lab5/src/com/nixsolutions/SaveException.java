package com.nixsolutions;

import java.io.*;
import exception.Save;

public class SaveException implements Save {

	@Override
	public void save(String textToSave, String path) throws WriteException {
		File file = new File(path);
		FileWriter fw = null;
		try {
			if (!file.exists()) {
				String folderPath = path.substring(0, path.lastIndexOf('\\'));
				new File(folderPath).mkdirs();
				file.createNewFile();
			}
			fw = new FileWriter(file, false);//to get exception set second parameter to true
			fw.write(textToSave);
			fw.flush();
			if (!read(path).equals(textToSave)){
				throw new WriteException(); 
			}
		} catch (IOException e) {
			throw new WriteException(); 
		} finally {
			if (fw != null) {
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String read(String path) {
		BufferedReader br = null;
		String toReturn = "";
		try{
			br = new BufferedReader(new FileReader(path));
			String line;
			while((line = br.readLine())!=null){
				toReturn += line; 
			}
		}catch(IOException ex){
			ex.printStackTrace();
		}finally {
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}		
		return toReturn;
	}
}
