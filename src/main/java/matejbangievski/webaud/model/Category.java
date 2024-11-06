package matejbangievski.webaud.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data // Valjda @Getters i @Setter zaedno
@AllArgsConstructor
public class Category {
    private String name;
    private String description;
}
