package blaczek.test001;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profil extends AppCompatActivity {

    private Button buttonLogout, buttonSongs, buttonSearch;
    private TextView textViewEmail;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        firebaseAuth = FirebaseAuth.getInstance();


        FirebaseUser user = firebaseAuth.getCurrentUser();




        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        buttonSongs = (Button) findViewById(R.id.buttonSongs);
        buttonSearch = (Button) findViewById(R.id.buttonSearch);
        textViewEmail = (TextView) findViewById(R.id.textViewUserEmail);

        textViewEmail.setText("Welcome "+user.getEmail() );


        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://www.youtube.com");
                Intent i = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(i);
            }
        });
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                firebaseAuth.signOut();
                finish();
                Intent back = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(back);

            }
        });


        buttonSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent i = new Intent(getApplicationContext(), SongsOD.class);
                startActivity(i);
            }
        });

    }
}
