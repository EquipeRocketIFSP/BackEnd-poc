package br.vet.sidekick.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages = "br.vet.sidekick.poc")
@EntityScan(basePackages = "br.vet.sidekick.poc.model")
public class PocApplication {
	public static void main(String[] args) { SpringApplication.run(PocApplication.class, args); }

}
