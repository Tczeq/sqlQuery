package pracaDomowa.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Car {
    private int id;
    private String brand;
    private String model;
    private double mileage;
}
