package marvin.igris.live.firebase.mapping;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FireMessage implements Serializable {
    private String messageId;
    private String text;
    private String ipAddress;
    private Integer year;
    private String month;

    // datetime
    private String createdAt;
    private String updatedAt;
}
