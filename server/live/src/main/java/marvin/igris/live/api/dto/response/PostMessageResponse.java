package marvin.igris.live.api.dto.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostMessageResponse implements Serializable {
    private Boolean success;
    private String message;
}
