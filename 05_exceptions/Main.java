package savepackage;

import java.io.IOException;
import javax.management.RuntimeOperationsException;
import exception.Save;

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		Save s = new Savewrite();
		String str = "dsfdf";
		
		try{
		s.save("C:\tes4t.txt", str);
		}catch (RuntimeException e){
		//}catch (RuntimeOperationsException e){
			
			System.out.println(e.getCause());
		
		}
	}
}
