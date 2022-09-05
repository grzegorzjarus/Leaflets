package pl.coderslab.leaflets.ajax;



import pl.coderslab.leaflets.model.Offer;

import java.util.Arrays;

public class MyData {

    //List<Point> coordinates;
    private double[][] coordinates;
    private AjaxUser user;

    private Offer offer;

    public MyData() {

    }

    public MyData(double[][] coordinates, AjaxUser user, Offer offer) {
        this.coordinates = coordinates;
        this.user = user;
        this.offer=offer;
    }



    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    public double[][] getCoordinates() {
        return this.coordinates;
    }

    public void setUser(AjaxUser user) {
        this.user=user;
    }

    public AjaxUser getUser() {
        return user;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public String toString() {
        return "MyData{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", user=" + user +
                '}';
    }


}

