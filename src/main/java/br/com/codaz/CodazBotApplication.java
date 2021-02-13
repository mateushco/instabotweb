package br.com.codaz;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import org.jinstagram.exceptions.InstagramException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import br.com.codaz.services.Execute;

@SpringBootApplication
public class CodazBotApplication extends SpringBootServletInitializer{

	public static void main(String[] args) throws InstagramException, IOException {
		SpringApplication.run(CodazBotApplication.class, args);
		
		
		
		Timer timer = new Timer(); 
		timer.scheduleAtFixedRate(new TimerTask() {
		    @Override public void run() { 
		    	
		    	try {
					Execute.conectarSeguir("neovitalle", "neovitallesaude17", "conhecaunai", 50, 15);
					Execute.conectarSeguir("dra.solangebanoski", "sol260879", "conhecaunai", 50, 15);
				} catch (InstagramException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

		    }

		    }, 1000, 36000000);
		
		
	}

}
