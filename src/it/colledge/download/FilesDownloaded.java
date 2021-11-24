package it.colledge.download;

/**Данный класс способствует передачи сообщений от одного объекта программы к другому (подробнее см. в других классах)**/

public class FilesDownloaded {

    private static boolean musicDownloaded = false;
    private static boolean pictureDownload = false;
    private static boolean downloadMusicError = false;
    private static final String ERROR_MESSAGE = """
            При выполнении программы возникла ошибка. :(
            Подробности выведены красным текстом.
            Сообщите разработчику об ошибке.""";

    public static void setPictureDownloaded(boolean pictureDownload){
        FilesDownloaded.pictureDownload = pictureDownload;
        System.out.println("Скачивание картинки завершилось");
    }

    public static void setMusicDownloaded(boolean musicDownloaded) {
        FilesDownloaded.musicDownloaded = musicDownloaded;
        System.out.println("Скачивание музыки завершилось");
    }

    public static boolean isMusicDownloaded() {return musicDownloaded;}

    public static boolean isPictureDownload() {return pictureDownload;}

    public static void errorMessage(){System.out.println(ERROR_MESSAGE);}

    public static void setDownloadMusicError(boolean downloadMusicError) {
        FilesDownloaded.downloadMusicError = downloadMusicError;}

    public static boolean isDownloadMusicError() {return downloadMusicError;}
}
