package ajc.sopra.eshop.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

@Service
public class ConsoleService implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(ConsoleService.class);

	public ConsoleService() {
		LOGGER.trace("trace");
		LOGGER.debug("debug"); //niveau pour corriger les problemes
		LOGGER.info("info");
		LOGGER.warn("warning");
		LOGGER.error("error");
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello world");
	}

}
