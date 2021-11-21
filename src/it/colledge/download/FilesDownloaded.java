package it.colledge.download;

public class FilesDownloaded {

    private static boolean musicDownloaded = false;
    private static boolean pictureDownload = false;
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

    public static boolean getMusicDownloaded() {
        return musicDownloaded;
    }

    public static boolean getPictureDownload() {
        return pictureDownload;
    }

    public static void errorMessage(){
        System.out.println(ERROR_MESSAGE);
    }
}
