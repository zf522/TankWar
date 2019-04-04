/**
 * FileName: Music
 * Author:   16681
 * Date:     2019/4/2 20:22
 * Description: 播放音乐
 */
package TankWar;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Music extends Thread {
    private String filename;

    public Music(String musicfile) {

        filename = musicfile;
    }

    public void run() {

        File soundfile = new File(filename);
        //获取音频输入流
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundfile);
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }
        //获取音频编码对象
        AudioFormat format = audioInputStream.getFormat();
        //设置数据输入
        SourceDataLine sourceDataLine = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        try {
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(info);
            sourceDataLine.open(format);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
            return;
        }
        sourceDataLine.start();
        //从输入流中读取数据发送到混音器
        int nBytesRead = 0;
        //这是缓冲
        byte[] abData = new byte[1024];
        try {
            while (nBytesRead != -1) {
                nBytesRead = audioInputStream.read(abData, 0, abData.length);
                if (nBytesRead >= 0) {
                    sourceDataLine.write(abData, 0, nBytesRead);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        } finally {
            //清空数据缓冲，并关闭输入
            sourceDataLine.drain();
            sourceDataLine.close();
        }
    }
}
