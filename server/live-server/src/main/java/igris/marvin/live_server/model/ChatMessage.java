package igris.marvin.live_server.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChatMessage {
    private String nickname;
    private String content;
    private Date timestamp;
}
