package models;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class RoundImpl implements Round {

    @Getter
    private Integer moves;
    @Getter
    private LocalDateTime timestamp;
}
