package it.colledge.download;

/**Программа параллельно скачивает изображение и mp3-файл.
 * После скачивания mp3-файл воспроизводится программным способом.**/

public class Main {

    public static void main(String[] args) throws InterruptedException {
        //Запуск параллельного скачивания
        ParallelDownload.parallelDownload();
        @ThisIsThread MP3Player mp3Player = new MP3Player();
        mp3Player.start();
    }
}
