package model.backend;
import model.datasource.Action;

import entities.Trip;

public interface Backend {
    public void addTrip(final Trip trip, final Action<String> action)throws Exception;
}
