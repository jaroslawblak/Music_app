package blaczek.test001;

import android.content.res.Resources;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.io.IOException;


public class Radiotest extends AppCompatActivity {

    private ListView lvradio;
    private ArrayAdapter<String> adapter ;
    private String[] stacje;
    private String[] stream;
    private MediaPlayer player;
    private ImageButton stop;
    boolean isPlaying = false; // domyślnie false, bo muzyka nie gra

    Button buttonstopPlay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radiotest);

        lvradio = (ListView) findViewById(R.id.listView1);
        stop = (ImageButton) findViewById(R.id.buttonStop);
        initResources();
        initstacjeListView();
        player = new MediaPlayer();


        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player.stop();
                player.reset();

                isPlaying = false;
            }
        });


    }

    private void initstacjeListView() {
        lvradio.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, stacje));
        lvradio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if(isPlaying == false) {
                    Toast.makeText(getApplicationContext(), "Loading, Please wait a moment...", Toast.LENGTH_LONG).show();
                    try {
                        player.setDataSource(stream[position]);
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalStateException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    player.prepareAsync();

                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {

                        public void onPrepared(MediaPlayer mp) {
                            player.start();
                            isPlaying = true; //bo muzyka gra
                        }
                    });
                }
            else {
                    player.stop();
                    player.reset();

                     isPlaying = false;
            }
            }
        });
    }


    private void initResources() {
        Resources res = getResources();
        stacje = res.getStringArray(R.array.stacje);
        stream = res.getStringArray(R.array.stream);
    }

}
