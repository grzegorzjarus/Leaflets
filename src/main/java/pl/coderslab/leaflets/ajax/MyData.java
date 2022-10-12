package pl.coderslab.leaflets.ajax;



import pl.coderslab.leaflets.model.Offer;

import java.util.Arrays;

public class MyData {


    private double[][] coordinates;
    private Offer offer;

    public MyData() {

    }


    public void setCoordinates(double[][] coordinates) {
        this.coordinates = coordinates;
    }

    public double[][] getCoordinates() {
        return this.coordinates;
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
                ", offer=" + offer +
                '}';
    }
}

