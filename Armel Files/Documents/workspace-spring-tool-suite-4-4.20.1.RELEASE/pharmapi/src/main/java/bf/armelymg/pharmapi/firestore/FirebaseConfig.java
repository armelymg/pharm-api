package bf.armelymg.pharmapi.firestore;

import java.io.FileInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import bf.armelymg.pharmapi.PharmapiApplication;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FirebaseConfig {
	
    @PostConstruct
	public void initializeFirebaseApp() {
        /*try {
            FileInputStream serviceAccount = new FileInputStream("./serviceAccountKey.json");

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        
         try {
            /*ClassLoader classLoader = PharmapiApplication.class.getClassLoader();

		    File file = new  File(Objects.requireNonNull(classLoader.getResource("serviceAccountKey.json")).getFile());
		
		    FileInputStream serviceAccount = new FileInputStream(file.getAbsolutePath());*/
			//FileInputStream serviceAccount = new FileInputStream(new File("src/main/resources/serviceAccountKey.json"));
			
			// Charger le fichier JSON depuis le répertoire resources
            InputStream serviceAccount = new ClassPathResource("serviceAccountKey.json").getInputStream();

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            //if(FirebaseApp.getApps().isEmpty()) { //<------- Here
                FirebaseApp.initializeApp(options);
                log.info("----------------------------- FirebaseApp Initialize -----------------------------");
            //}
        } catch (IOException e) {
            e.printStackTrace();
        }
         
    }

}
