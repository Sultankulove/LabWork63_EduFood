package kg.attractor.java25.labwork63_edufood.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "Users")
public class User {
    @Id
    private Long id;


    private String name;

    private String password;

    private boolean enabled;
}
