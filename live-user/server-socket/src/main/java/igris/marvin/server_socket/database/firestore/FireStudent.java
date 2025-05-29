package igris.marvin.server_socket.database.firestore;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FireStudent implements Serializable {
    private String studentId;
    private String name;
    private Integer age;
    private String timestamp;
}
