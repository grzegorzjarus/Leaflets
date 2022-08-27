package pl.coderslab.leaflets.ajax;



import java.util.Arrays;

public class MyData {

    //List<Point> coordinates;
    private double[][] coordinates;

//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }



    // private  String name;


//    private User user;
//
//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }

    public double[][] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "coordinates=" + Arrays.toString(coordinates) +
                '}';
    }
}

