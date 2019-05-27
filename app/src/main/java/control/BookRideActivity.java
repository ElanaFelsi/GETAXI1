package control;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


import model.datasource.Action;
import com.example.user.android5779_0283_9642_00.R;

import entities.Trip;
import model.datasource.FireBaseDataBase;

public class BookRideActivity extends AppCompatActivity {

    Button findRide;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_ride);


        findRide = (Button) findViewById(R.id.findmearide);
        findRide.setOnClickListener(new View.OnClickListener() {
            String Pickup;
            String Destination;
            String PickupTime;
            String Name;
            String PhoneNumber;
            String Email;

            @Override
            public void onClick(View v) {
                //find view by ID
                EditText pickup = (EditText) findViewById(R.id.PickUP);
                EditText destination = (EditText) findViewById(R.id.Destination);
                EditText pickuptime = (EditText) findViewById(R.id.PickupTime);
                EditText phonenumber = (EditText) findViewById(R.id.PhoneNumer);
                EditText name = (EditText) findViewById(R.id.Name);
                EditText email = (EditText) findViewById(R.id.Email);

                Trip trip = new Trip();
                boolean flag = true;

                Pickup = pickup.getText().toString();
                if (Pickup.isEmpty()) {
                    pickup.setError("Please enter address");
                    flag = false;
                }
                Destination = destination.getText().toString();
                if (Destination.isEmpty()) {
                    destination.setError("Please enter destination");
                    flag = false;
                }

                PickupTime= pickuptime.getText().toString();
                if (PickupTime.isEmpty()) {
                    pickuptime.setError("Please enter pickup time");
                    //throw (ex);
                    flag = false;
                }
                Name = name.getText().toString();
                if (Name.isEmpty()) {
                    name.setError("Please enter name");
                    flag = false;
                }
                PhoneNumber = phonenumber.getText().toString();
                if (PhoneNumber.isEmpty()) {
                    phonenumber.setError("Please enter phonenumber");
                    flag = false;
                }
                if (!(PhoneNumber.length() == 10)) {
                    phonenumber.setError("Phone number incorrect");
                    flag = false;
                }


                Email = email.getText().toString();
                if (Email.isEmpty()) {
                    email.setError("Please enter email");
                    flag = false;
                }
                if (!Email.contains("@")) {
                    email.setError("Email address incorect");
                    flag = false;
                }

                if (flag) {
                    if (flag) {
                        new AsyncTask<Context, Void, Void>() {
                            @Override

                            protected Void doInBackground(Context... contexts) {
                                //Trip trip = new Trip(Pickup, Destination, PickupTime, DropOffTime, Name, PhoneNumber, Email);
                                Trip trip = getTrip();
                                FireBaseDataBase tmp = new FireBaseDataBase();
                                try {
                                    tmp.addTrip(trip, new Action<String>() {
                                        @Override
                                        public void onSuccess(String obj) {
                                        }

                                        @Override
                                        public void onFailure(Exception exception) {
                                        }

                                        @Override
                                        public void onProgress(String status, double percent) {
                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                return null;
                            }
                        }.execute();
                        Toast.makeText(BookRideActivity.this, "Your order is being processed", Toast.LENGTH_LONG).show();
                    }
                }

            }

            private Trip getTrip() {
                Trip trip = new Trip();
                trip.setCurrentLocation(Pickup);
                trip.setPassengerName(Name);
                trip.setPassengerNumber(PhoneNumber);
                trip.setPassengerEmail(Email);
                trip.setDestination(Destination);
                trip.setStatus(Trip.TripStatus.AVAILABLE);
                trip.setDriverID("000000000");
                trip.setPickUpTime(PickupTime);
                trip.setDropOffTime(PickupTime);
                return trip;
            }

        });
    }
}
