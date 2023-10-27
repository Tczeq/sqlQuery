package pracaDomowaTransakcje.model;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Train {
    private String name;
    private double length;
    private boolean isWars;
    private int seatNumber;
}
