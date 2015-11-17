package com.nixsolutions.exceptions;

import java.io.File;

public class SaveFile implements exception.Save {

	@Override
	public void save(String strTextForSaving, String strPath) {
		// TODO Auto-generated method stub
		if ((new File(strPath)).exists()) {

		}else{
			File file = new File(strPath);
			
		}
	}

}
