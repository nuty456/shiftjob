package cz.zauzoo.shiftjob;

import javax.websocket.server.PathParam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.openshift.client.DefaultOpenShiftClient;
import io.fabric8.openshift.client.OpenShiftClient;

@SpringBootApplication
@Controller
public class ShiftjobApplication {

	@GetMapping("/{namespace}")
	public String pods(final Model model, @PathParam("namespace") String namespace) {
		model.addAttribute("title", "Docker + Spring Boot");
		model.addAttribute("msg", "Welcome to the docker container!");

		// OpenShiftConfig config
		Config config = new ConfigBuilder()
				.withMasterUrl("https://192.168.99.101:8443")
				.withUsername("developer")
				.withPassword("developer")
				.build();

		try (OpenShiftClient client = new DefaultOpenShiftClient(config)) {
			System.out.println("Client opened is: " + client);
			client.pods().inNamespace(namespace).list().getItems();
			// jobs = client.batch().jobs().list();
		}

		return "index";
	}

	@GetMapping("/")
	public String index(final Model model) {
		model.addAttribute("title", "Docker + Spring Boot");
		model.addAttribute("msg", "Welcome to the docker container!");

		// OpenShiftConfig config
		Config config = new ConfigBuilder()
				.withMasterUrl("https://192.168.99.101:8443")
				.withUsername("developer")
				.withPassword("developer")
				.build();

		try (OpenShiftClient client = new DefaultOpenShiftClient(config)) {
			System.out.println("Client opened is: " + client);
			client.pods().list().getItems().forEach(p -> System.out.println("pod: " + p));
			// jobs = client.batch().jobs().list();
		}

		return "index";
	}

	public static void main(String[] args) {
		SpringApplication.run(ShiftjobApplication.class, args);
	}

}
