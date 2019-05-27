package model.datasource;

import android.support.annotation.NonNull;

import model.backend.Backend;
import entities.Trip;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class FireBaseDataBase implements Backend {


    /**
     * Try to insert Ride with the data from the user to firebase
     *
     * @param //trip   - the ride initialized with user's data.
     * @param //action - implementation of Action interface, which defines what will happen in the insertion of the data
     * @throws Exception
     */
    public interface NotifyDataChange<T> {
        void OnDataChanged(T obj);

        void onFailure(Exception exception);
    }

    static DatabaseReference tripRef;
    static List<Trip> trips;
    public static ChildEventListener tripRefChildEventListener;

    static {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        tripRef = database.getReference("trips");
        trips = new ArrayList<>();

    }

    @Override
    //public static void addRide(final Ride ride, final Action<Long> action) לא עובד עם static
    public void addTrip(final Trip trip, final Action<String> action) {
        {
            DatabaseReference pushedPostRef = tripRef.push();
            String key =pushedPostRef.getKey();
         //   String key = trip.getTripID();
            tripRef.child(key).setValue(trip).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    action.onSuccess(trip.getTripID());
                    action.onProgress("upload ride data", 100);
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    action.onFailure(e);
                    action.onProgress("error upload ride data", 100);
                }
            });

        }
    }
}