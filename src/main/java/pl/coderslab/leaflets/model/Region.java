package pl.coderslab.leaflets.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Point> points;
}
