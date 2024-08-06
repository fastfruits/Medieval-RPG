package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
	GamePanel gp;
	public Config(GamePanel gp) {
		this.gp = gp;
	}
	public void saveConfig() {
		try {			
			BufferedWriter bw = new BufferedWriter(new FileWriter("config"));
			
			if(gp.fullscreen == true) {
				bw.write("On");
			}
			if(gp.fullscreen == false) {
				bw.write("Off");
			}
			bw.newLine();
			bw.write(String.valueOf(gp.music.volumeScale));
			bw.newLine();
			bw.write(String.valueOf(gp.se.volumeScale));
			bw.newLine();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void loadConfig() {
		try  {
			
			BufferedReader br = new BufferedReader(new FileReader("config"));
			
			String s = br.readLine();
			
			if(s.equals("On")) {
				gp.fullscreen = true;
			}
			if(s.equals("Off")) {
				gp.fullscreen = false;
			}
			
			s = br.readLine();
			gp.music.volumeScale = Integer.parseInt(s);
			
			s = br.readLine();
			gp.se.volumeScale = Integer.parseInt(s);
			
			br.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
