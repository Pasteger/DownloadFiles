package it.colledge.download;

public class ParallelDownload{

    public static void parallelDownload() {
        DownloadMusic downloadMusic = new DownloadMusic();
        DownloadPicture downloadPicture = new DownloadPicture();
        downloadMusic.start();
        downloadPicture.start();
    }
}
