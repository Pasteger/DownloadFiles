package it.colledge.download;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import java.io.FileInputStream;
import java.io.IOException;

/**Данный класс реализует запуск mp3-файла**/

public class MP3Player extends Thread{

    @Override
    public void run() {

        //После запуска поток ждёт пока скачается mp3-файл, который он должен запустить
        //Поток узнаёт о готовности файла через значение поля musicDownloaded класса FilesDownloaded,
        //которое устанавливает поток скачивания
        while (!FilesDownloaded.isMusicDownloaded()) {
            try {
                //Следующий комментарий меня заставила оставить среда разработки:
                //noinspection BusyWait
                Thread.sleep(1);
                //Если поток скачивания потерпел неудачу, этому потоку придёт сообщение и он закончит своё выполнение
                if(FilesDownloaded.isDownloadMusicError()){return;}
            } catch (InterruptedException e) {
                //В случае ошибки в консоль выведется сообщение для пользователя
                FilesDownloaded.errorMessage();
                e.printStackTrace();
            }
        }

        //Здесь происходит воспроизведение mp3-файла
        try (FileInputStream inputStream = new FileInputStream("downloads\\music0.mp3")) {
            try {
                System.out.println("Воспроизведение музыки началось");
                Player player = new Player(inputStream);
                player.play();
                System.out.println("Воспроизведение музыки завершилось");
            } catch (JavaLayerException e) {
                FilesDownloaded.errorMessage();
                e.printStackTrace();
            }
        } catch (IOException e) {
            FilesDownloaded.errorMessage();
            e.printStackTrace();
        }
    }
}
