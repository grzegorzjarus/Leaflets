package pl.coderslab.leaflets.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor

public class Region {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = {CascadeType.ALL})
    private List<Point> points;


    public Region(String name, List<Point> points){
        this.name = name;
        this.points=points;
    }


}
