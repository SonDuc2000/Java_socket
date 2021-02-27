package Test;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		FileInputStream file;
		try {
			
			file = new FileInputStream("data.text");
			DataInputStream dat = new DataInputStream(file);
			try {
				String s;
				while((s = dat.readUTF()) != null) {
				System.out.println(s.toString());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Lỗi");
			} finally {
				if(dat != null)
					try {
						dat.close();
						if(file != null)
							file.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

}
