package it.colledge.download;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**Класс реализует скачивание mp3-файла по ссылке из интернета**/

public class DownloadMusic extends Thread{

    //InFIle - файл откуда берутся ссылка на файл и путь для сохранения файла
    private static final String IN_FILE_TXT = "inFile.txt";
    private static final String OUT_FILE_TXT = "downloads\\outFileMusic.txt";
    private static String PathToMusic;

    @Override
    public void run() {
        //Сообщение в консоль для пользователя
        System.out.println("Скачивание музыки началось");
        //Чтение файла
        readingFile();
        //Скачивание
        download();
        //Сообщение для остальной программы о завершении скачивания
        FilesDownloaded.setMusicDownloaded(true);
    }

    private void readingFile(){
        //Согласно ТЗ, InFile содержит две строки следующего формата:
        //[ССЫЛКА_НА_ФАЙЛ][ПРОБЕЛ][ПУТЬ_ДЛЯ_СОХРАНЕНИЯ]
        //в первой строке ссылка на изображение, во второй строке ссылка на mp3-файл.
        //По этому, DownloadMusic помещает считанные из файла строки в ArrayList<String>
        //И забирает из него условленную строку (вторую)

        ArrayList<String> textInFile = new ArrayList<>();

        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT));
             BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {

            String line = " ";
            while (line != null) {
                line = inFile.readLine();
                textInFile.add(line);
            }

            //[ССЫЛКА_НА_ФАЙЛ][ПРОБЕЛ][ПУТЬ_ДЛЯ_СОХРАНЕНИЯ]
            //Здесь происходит разделение строки на ссылку и путь (строка вторая (1))
            URL url = new URL(textInFile.get(1).substring(0, textInFile.get(1).indexOf(" ")));
            PathToMusic = textInFile.get(1).substring(textInFile.get(1).indexOf(" ") + 1);
            textInFile.clear();

            String result;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                result = bufferedReader.lines().collect(Collectors.joining("\n"));
            }
            Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*/*(?=\")");
            Matcher matcher = email_pattern.matcher(result);
            int i = 0;
            //Здесь происходит запись в OutFile
            while (matcher.find() && i < 1) {
                outFile.write(matcher.group() + "\r\n");
                i++;
            }
        } catch (IOException e) {
            //В случае исключительной ситуации пользователь получает сообщение в консоль
            //И отправляется сообщение для MP3Player, о том что mp3-файл не удалось скачать
            FilesDownloaded.errorMessage();
            FilesDownloaded.setDownloadMusicError(true);
            e.printStackTrace();
        }
    }

    private void download(){
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    DownloadUsingNIO.downloadUsingNIO(music, PathToMusic + count + ".mp3");
                    count++;
                }
            } catch (IOException e) {
                FilesDownloaded.errorMessage();
                FilesDownloaded.setDownloadMusicError(true);
                e.printStackTrace();
            }
        } catch (IOException e) {
            FilesDownloaded.errorMessage();
            FilesDownloaded.setDownloadMusicError(true);
            e.printStackTrace();
        }
    }
}
