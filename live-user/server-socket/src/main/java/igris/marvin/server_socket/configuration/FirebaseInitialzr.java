package igris.marvin.server_socket.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseInitialzr {

    @Bean
    public FirebaseApp firebaseConnection() throws IOException, Exception {

        if (FirebaseApp.getApps().isEmpty()) {

            // Retrieve the file path from the environment variable
            String serviceAccountPath = System.getenv("FIREBASE_TEST_CONFIG");

            if (serviceAccountPath == null || serviceAccountPath.isEmpty()) {

                System.out.println("\n# [ Path to file not found ] \n");
                System.out.println("\n# [ Switching to prof env ] \n");

                throw new Exception("\n# [ Path to firetest config file not found ] \n");

            } else {

                // Check if the file exists at the specified path
                File file = new File(serviceAccountPath);

                FileInputStream serviceAccount = new FileInputStream(file);

                @SuppressWarnings("deprecation")
                FirebaseOptions options = new FirebaseOptions.Builder()
                        .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                        .setDatabaseUrl("https://firetestdb-4fd44-default-rtdb.firebaseio.com")
                        .build();

                FirebaseApp app = FirebaseApp.initializeApp(options);

                System.out.println("\n# [ Successfully Connected to firebase database in DEV ]\n");

                return app;
            }

        } else {

            System.out.println("\n# [ FirestoreInitialzr -> Firebase connection is already active, nice ]\n");
            return FirebaseApp.getInstance();
        }
    }

    @Bean
    public FirebaseDatabase getFirebase(
        FirebaseApp app
    ) {
        return FirebaseDatabase.getInstance(app);
    }
}
