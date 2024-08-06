package com.springboot.ultimate;

import com.springboot.ultimate.colors.raw.ColourPrinter;
import com.springboot.ultimate.colors.rawimpl.ColourPrinterImpl;
import com.springboot.ultimate.configuration.PizzaConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootUltimateApplication implements CommandLineRunner {

	// this is a second way
	private final ColourPrinter colourPrinter;

	// this is for configuration properties
	private final PizzaConfig pizzaConfig;


	public SpringBootUltimateApplication(ColourPrinter colourPrinter,
										 PizzaConfig pizzaConfig){
		this.colourPrinter = colourPrinter;
		this.pizzaConfig = pizzaConfig;
	}


	public static void main(String[] args) {
		//System.setProperty("server.port","8090");
		SpringApplication.run(SpringBootUltimateApplication.class, args);

		System.out.println("Ultimate Application Running...!");
	}

	@Override
	public void run(String... args) throws Exception {
		// this is a first way
		//final ColourPrinter colourPrinter = new ColourPrinterImpl();
		System.out.println(colourPrinter.printer());
		//final PizzaConfig pizzaConfig = new PizzaConfig("tomato","mozorella","prabha");

		System.out.println(
				String.format("%s want a crust %s with %s sauce",
						pizzaConfig.getCrust(),
						pizzaConfig.getSauce(),
						pizzaConfig.getTopping()
				)
		);
	}
}
