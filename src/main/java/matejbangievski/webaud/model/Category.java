package matejbangievski.webaud.model;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data // Valjda @Getters i @Setter zaedno
@AllArgsConstructor
public class Category {
    private String name;
    private String description;
}
