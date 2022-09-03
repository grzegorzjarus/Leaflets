package pl.coderslab.leaflets.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data

public class User {

    private enum UserType{
        Client,
        Distributor
    }

    //private Set<UserType>  type1;
    private Set<String>  type;

   // private Long id;
    private String email;
    private String password;
    public User(){
        this.type= new HashSet<>();
        this.type= new HashSet<>();
        this.type.add("Client");
        this.type.add("Distributor");
//        this.type.add(UserType.Client);
//        this.type.add(UserType.Distributor);
    }

}
