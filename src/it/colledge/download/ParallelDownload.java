package it.colledge.download;

/**Метод создаёт и запускает потоки скачивания музыки и картинки*/

public class ParallelDownload{
    public static void parallelDownload() {
        @ThisIsThread DownloadMusic downloadMusic = new DownloadMusic();
        @ThisIsThread DownloadPicture downloadPicture = new DownloadPicture();
        downloadMusic.start();
        downloadPicture.start();
    }
}
