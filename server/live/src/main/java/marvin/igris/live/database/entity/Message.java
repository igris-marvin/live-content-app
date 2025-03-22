package marvin.igris.live.database.entity;

import java.io.Serializable;
import java.time.Month;
import java.time.Year;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message implements Serializable {
    private String messageId;
    private String text;
    private String ipAddress;
    private Year year;
    private Month month;

    // datetime
    private Date createdAt;
    private Date updatedAt;
}
