package it.colledge.download;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.FileInputStream;
import java.io.IOException;

public class MP3Player extends Thread{

    @Override
    public void run() {

        while (!FilesDownloaded.getMusicDownloaded()) {
            try {
                //noinspection BusyWait
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try (FileInputStream inputStream = new FileInputStream(
                "downloads\\music0.mp3")
        ) {
            try {
                System.out.println("Воспроизведение музыки началось");
                Player player = new Player(inputStream);
                player.play();
                System.out.println("Воспроизведение музыки завершилось");
            } catch (JavaLayerException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
