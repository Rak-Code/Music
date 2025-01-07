package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;





import java.io.File;

public class MusicPlayer extends Application {

    private MediaPlayer mediaPlayer;
    private String[] songs = {
    	"C:\\Users\\Rakesh\\eclipse-workspace\\Music\\src\\musiclist\\song1.mp3",
        "C:\\Users\\Rakesh\\eclipse-workspace\\Music\\src\\musiclist",
        "C:\\Users\\Rakesh\\eclipse-workspace\\Music\\src\\musiclist\\song3.mp3"
    };
    private int currentSongIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        loadSong(currentSongIndex);

        Button playButton = new Button("Play");
        playButton.setOnAction(e -> mediaPlayer.play());

        Button pauseButton = new Button("Pause");
        pauseButton.setOnAction(e -> mediaPlayer.pause());

        Button stopButton = new Button("Stop");
        stopButton.setOnAction(e -> mediaPlayer.stop());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> {
            currentSongIndex = (currentSongIndex + 1) % songs.length;
            loadSong(currentSongIndex);
            mediaPlayer.play();
        });

        Button previousButton = new Button("Previous");
        previousButton.setOnAction(e -> {
            currentSongIndex = (currentSongIndex - 1 + songs.length) % songs.length;
            loadSong(currentSongIndex);
            mediaPlayer.play();
        });

        HBox controls = new HBox(10, playButton, pauseButton, stopButton, previousButton, nextButton);
        Scene scene = new Scene(controls, 400, 100);

        primaryStage.setTitle("Music Player");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadSong(int index) {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        String musicFile = songs[index];
        Media media = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(media);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
