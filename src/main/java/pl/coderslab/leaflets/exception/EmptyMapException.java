package pl.coderslab.leaflets.exception;

import pl.coderslab.leaflets.controller.OfferController;

public class EmptyMapException extends NullPointerException{
    public EmptyMapException(){
        System.out.println("Pusta mapa");

    }
}
