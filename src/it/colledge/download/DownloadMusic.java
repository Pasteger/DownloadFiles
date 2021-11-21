package it.colledge.download;

import java.io.*;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class DownloadMusic extends Thread{

    private static final String IN_FILE_TXT = "inFile.txt";
    private static final String OUT_FILE_TXT = "downloads\\outFileMusic.txt";
    private static String PathToMusic;

    @Override
    public void run() {
        System.out.println("Скачивание музыки началось");
        readingFile();
        download();
        FilesDownloaded.setMusicDownloaded(true);
    }

    private void readingFile(){
        ArrayList<String> textInFile = new ArrayList<>();

        try (BufferedWriter outFile = new BufferedWriter(new FileWriter(OUT_FILE_TXT));
             BufferedReader inFile = new BufferedReader(new FileReader(IN_FILE_TXT))) {

            String line = " ";
            while (line != null) {
                line = inFile.readLine();
                textInFile.add(line);
            }

            URL url = new URL(textInFile.get(1).substring(0, textInFile.get(1).indexOf(" ")));
            PathToMusic = textInFile.get(1).substring(textInFile.get(1).indexOf(" ") + 1);

            String result;
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(url.openStream()))) {
                result = bufferedReader.lines().collect(Collectors.joining("\n"));
            }
            Pattern email_pattern = Pattern.compile("\\s*(?<=data-url\\s?=\\s?\")[^>]*/*(?=\")");
            Matcher matcher = email_pattern.matcher(result);
            int i = 0;
            while (matcher.find() && i < 1) {
                outFile.write(matcher.group() + "\r\n");
                i++;
            }
        } catch (IOException e) {
            FilesDownloaded.errorMessage();
            e.printStackTrace();
        }
    }

    private void download(){
        try (BufferedReader musicFile = new BufferedReader(new FileReader(OUT_FILE_TXT))) {
            String music;
            int count = 0;
            try {
                while ((music = musicFile.readLine()) != null) {
                    downloadUsingNIO(music, PathToMusic + count + ".mp3");
                    count++;
                }
            } catch (IOException e) {
                FilesDownloaded.errorMessage();
                e.printStackTrace();
            }
        } catch (IOException e) {
            FilesDownloaded.errorMessage();
            e.printStackTrace();
        }
    }

    private void downloadUsingNIO(String strUrl, String file) throws IOException {
        URL url = new URL(strUrl);
        ReadableByteChannel byteChannel = Channels.newChannel(url.openStream());
        FileOutputStream stream = new FileOutputStream(file);
        stream.getChannel().transferFrom(byteChannel, 0, Long.MAX_VALUE);
        stream.close();
        byteChannel.close();
    }
}
