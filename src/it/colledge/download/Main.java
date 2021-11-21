package it.colledge.download;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        ParallelDownload.parallelDownload();
        MP3Player mp3Player = new MP3Player();
        mp3Player.start();
    }
}
