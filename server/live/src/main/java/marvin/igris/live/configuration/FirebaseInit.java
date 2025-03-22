package marvin.igris.live.configuration;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.FirebaseDatabase;

@Configuration
public class FirebaseInit {
    
    @Bean
    public FirebaseApp initialzr() throws IOException {

        if(FirebaseApp.getApps().isEmpty()) {

            String firebaseConfigJson = "{\"type\":\"service_account\",\"project_id\":\"spring-reactive-test\",\"private_key_id\":\"9da514a03174771715f40bd4c9d8f6f3a24241c7\",\"private_key\":\"-----BEGIN PRIVATE KEY-----\\n" + //
                        "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQDX5Ftgy5jUXYeT\\n" + //
                        "Ts3yg8Ue4c1+tf3/rZIBh+1oF49GVa1AvBaSWKimE5emJY8GrbWyPzpjnzj9eyD5\\n" + //
                        "taFUJrOn2MZxuH4ukKaYEB0t5KSfR4/bt7EovMbf9gyluzEoD9giar9+Wx8ln5NY\\n" + //
                        "uCsdweIAlcVctsO3R35wgi5lcukInG9ClgXxmdlZkIsHfVDCJZZS1VCp+WYkVpZ4\\n" + //
                        "6gZ84W2Fe8+0syeQq+S/nr9+cbafl8LwcRXh85zfiorC5lwf3VBz+WSO2IzMKxp6\\n" + //
                        "FtaMuMm4DQNlQx9tBJT2cqZ4MWoJCnwfpD4s7saEaaQo/P86oWU6p3D+PfCnX5ax\\n" + //
                        "0FsI6V3VAgMBAAECggEAEYXyFSF5Z7/+st902mrFIMb8VmerCeoanYAHrkj+mAYj\\n" + //
                        "avYeppAb15JkCaLnS3K6j9D6KjS13BHhgOXem3ViZCzgG0IPEIms/U842xfW9Sxf\\n" + //
                        "uhpcaWgnLphVTvdqcOwWTjbYEQdljWN42CeSAlc8GEblJIEbfNXIpr+o8z2Nj3qi\\n" + //
                        "2Cpsekh7xi4gTgIjr1ilmx4zKFTeHB74GiZHds99RxHsgv3eWWUXyT8svQKcqk8v\\n" + //
                        "V3746gA0cQLfeE4ioWIdKV3bKqZzkwpKnuCa2HoJ/w9zZpGo7xFthMV2tsTcKf04\\n" + //
                        "ZhoDArnOJtDzaHbClfh3Ug5USNzT6SZEt+Hb0/SlcQKBgQD4/XwgA1XueFoVUUE8\\n" + //
                        "QWWwHDBqjYNLWJ5fhN49Sepagnazhg/yxxrshpId6JFmWCzC+CMiCyw+YEjzuiyJ\\n" + //
                        "ISzBUBTLZcMmDxRYftIHvwQaN2I88fs+tjSpFZaoPvfkOcpocrEQu7iBhS0AZ2w/\\n" + //
                        "ZZu5QbrE6ODM/LcEOiNAQ8Dh0QKBgQDd+FP1cwR/TJXwC2tuLHESZmj8PUowW0lS\\n" + //
                        "lW3IwO0A9jug4GEKi2QNPcuspkIWmrDBMH79H98umgwKBYZDxrpZvOATrxNYXZL4\\n" + //
                        "WvSk2aNzKxxB1t6mrPw1c+ly6bCKXFYD9o/x97c+czoDmSn3fYLlhHgtBc9R9N4W\\n" + //
                        "DC8bD1AYxQKBgQDjqzyVNRec7vVH6r1gsFef+ZaXx3/XJFfj9WUU24JrckHBk4L/\\n" + //
                        "tek7fy0aJe7ViyiaujO9zEOPYZEzMvfIB8qZlHT/KrSPF1i+NnAr3xCMPDoaihT2\\n" + //
                        "2bo7JV9VxgX67JDUgpK/PzbNT42/gdHsAieq9Jwnstzi8u0R82sKNymmEQKBgQC4\\n" + //
                        "3pVWrRfGyktqpKgyF3OJJqAzzPwYMD9uN6Q6+muqTGo3QAmylYub96bFwabzWpzy\\n" + //
                        "Gej5+uyPzJ9QTW+YzU/xY3yhCEStaSn6DQ8KqXUXe4VKVKIV4achAcwh9/4gDm1Z\\n" + //
                        "WaZjmbQVoDRmQM+SIAfADsdkgB0+nhnF9RnaiLNmHQKBgQDWQmaIudSNJ+H4WfRQ\\n" + //
                        "hCoJwFLNfBjmcXgt2W7YMUYqgtOU6R2tu6nGhYkmv6ArbBy8Xtk0nOaz/anlsQsU\\n" + //
                        "79gdxBdvlO8DPw3Vx91biQ9KYwjoxdWzZ1Z/h9/6OoHf7T12pTikkwS+hFA2WaaA\\n" + //
                        "+KjhdJUs5LQZtQES/lDSs8lMVg==\\n" + //
                        "-----END PRIVATE KEY-----\\n" + //
                        "\",\"client_email\":\"firebase-adminsdk-fwv05@spring-reactive-test.iam.gserviceaccount.com\",\"client_id\":\"118387327646699791896\",\"auth_uri\":\"https://accounts.google.com/o/oauth2/auth\",\"token_uri\":\"https://oauth2.googleapis.com/token\",\"auth_provider_x509_cert_url\":\"https://www.googleapis.com/oauth2/v1/certs\",\"client_x509_cert_url\":\"https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-fwv05%40spring-reactive-test.iam.gserviceaccount.com\",\"universe_domain\":\"googleapis.com\"}";

            // Convert the JSON string into an InputStream
            InputStream serviceAccount = new ByteArrayInputStream(firebaseConfigJson.getBytes());

            FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://spring-reactive-test-default-rtdb.firebaseio.com")
                .build();

            FirebaseApp app = FirebaseApp.initializeApp(options);

            System.out.println("\n# [ firebase Connection Established ]\n");

            return app;

        } else {
            System.out.println("\n# [ Firebase Connection is already active, nice :) ]\n");
            return FirebaseApp.getInstance();
        }
    }

    @Bean
    public FirebaseDatabase getFirebaseDatabase(
        FirebaseApp app
    ) {
        return FirebaseDatabase.getInstance(app);
    }
}
