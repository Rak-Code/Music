package src;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;

public class MusicPlayer extends Application {

    private MediaPlayer mediaPlayer;
    private String[] songs = {
        "C:\\Users\\Rakesh\\eclipse-workspace\\Music\\src\\musiclist\\song1.mp3",
        "C:\\Users\\Rakesh\\eclipse-workspace\\Music\\src\\musiclist\\song2.mp3",
        "C:\\Users\\Rakesh\\eclipse-workspace\\Music\\src\\musiclist\\song3.mp3"
    };
    private int currentSongIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        showSongSelectionMenu(primaryStage);
    }

    private void showSongSelectionMenu(Stage primaryStage) {
        // Create a ListView to display the songs
        ListView<String> songListView = new ListView<>();
        for (String song : songs) {
            songListView.getItems().add(new File(song).getName());
        }

        // Button to proceed to the player
        Button selectButton = new Button("Select and Play");
        selectButton.setOnAction(e -> {
            int selectedSongIndex = songListView.getSelectionModel().getSelectedIndex();
            if (selectedSongIndex != -1) {
                currentSongIndex = selectedSongIndex;
                showPlayerPanel(primaryStage);
            } else {
                System.out.println("Please select a song first.");
            }
        });

        // Layout for the menu
        BorderPane menuLayout = new BorderPane();
        menuLayout.setCenter(songListView);
        menuLayout.setBottom(selectButton);

        Scene menuScene = new Scene(menuLayout, 400, 300);
        primaryStage.setTitle("Select a Song");
        primaryStage.setScene(menuScene);
        primaryStage.show();
    }

    private void showPlayerPanel(Stage primaryStage) {
        loadSong(currentSongIndex);

        // Create player controls
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

        // Add a Back to Menu button
        Button backButton = new Button("Back to Menu");
        backButton.setOnAction(e -> {
            if (mediaPlayer != null) {
                mediaPlayer.stop();
            }
            showSongSelectionMenu(primaryStage);
        });

        HBox controls = new HBox(15, playButton, pauseButton, stopButton, previousButton, nextButton, backButton);
        Scene playerScene = new Scene(controls, 400, 100);

        primaryStage.setTitle("Music Player");
        primaryStage.setScene(playerScene);
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
