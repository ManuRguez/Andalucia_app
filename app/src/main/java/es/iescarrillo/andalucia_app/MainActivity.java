package es.iescarrillo.andalucia_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity {

    private ImageButton btnMusic;

    private AssetManager assetManager;

    private MediaPlayer mediaPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Inicicializamos el boton
        btnMusic = findViewById(R.id.btnMusic);


        //Cargamos la lista de canciones
        assetManager = getAssets();


        // Establecemos un listener para el clic del botón btnMusic
        btnMusic.setOnClickListener(v -> {
            try {
                if (mediaPlayer == null) {  // Si el MediaPlayer no ha sido inicializado
                    mediaPlayer = new MediaPlayer();  // Inicializa un nuevo MediaPlayer
                    AssetFileDescriptor descriptor = assetManager.openFd("Andalucia.mp3");  // Abre el archivo de audio
                    mediaPlayer.setDataSource(descriptor.getFileDescriptor(), descriptor.getStartOffset(), descriptor.getLength());  // Establece los datos fuente del MediaPlayer
                    mediaPlayer.prepare();  // Prepara el MediaPlayer para la reproducción
                }

                if (mediaPlayer.isPlaying()) {  // Si el MediaPlayer está reproduciendo actualmente
                    mediaPlayer.pause();  // Pausa la reproducción
                } else {
                    mediaPlayer.start();  // Si no está reproduciendo, inicia la reproducción
                }
            } catch (Exception e) {
                Log.e("Error", e.getMessage());  //Recogemos cualquier excepcion que pueda surgir en el momento

            }
        });



    }
}