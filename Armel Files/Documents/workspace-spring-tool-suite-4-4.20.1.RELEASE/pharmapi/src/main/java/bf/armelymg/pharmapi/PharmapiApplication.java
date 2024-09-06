package bf.armelymg.pharmapi;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Logger;

import org.apache.juli.logging.Log;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Pharm API", 
				version = "1.0", 
				description = "DOCUMENTATION Pharmacie de Garde v1.0")
)
@ComponentScan(basePackages = {"bf.armelymg.pharmapi"})
public class PharmapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(PharmapiApplication.class, args);
	}

}
