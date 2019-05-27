package model.datasource;

import java.util.ArrayList;
import entities.Trip;
import model.backend.Backend;


public class DatabaseList implements Backend {

    public ArrayList<Trip> trips= new ArrayList<Trip>();


    @Override
    public void addTrip(Trip trip, final Action<String> action) throws Exception {
        for (Trip n : trips)
            if(n.equals(trip))
                throw new Exception("This trip already exists.");
        trips.add(trip);
    }

}
