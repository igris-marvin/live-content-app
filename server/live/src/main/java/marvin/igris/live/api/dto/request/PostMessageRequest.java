package marvin.igris.live.api.dto.request;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMessageRequest implements Serializable {
    private String text;
    private String ipAddress;
    private Integer year;
    private String month;
}
