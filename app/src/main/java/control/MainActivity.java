package control;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.content.Intent;

import com.example.user.android5779_0283_9642_00.R;

public class MainActivity extends AppCompatActivity {

    ImageButton taxiLogo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taxiLogo = (ImageButton) findViewById(R.id.TaxiLogo);
        taxiLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BookRideActivity.class));
            }
        });
    }

}
