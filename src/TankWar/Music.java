/**
 * FileName: Music
 * Author:   16681
 * Date:     2019/4/2 20:22
 * Description: ��������
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
        //��ȡ��Ƶ������
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(soundfile);
        } catch (Exception e) {
            e.getStackTrace();
            return;
        }
        //��ȡ��Ƶ�������
        AudioFormat format = audioInputStream.getFormat();
        //������������
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
        //���������ж�ȡ���ݷ��͵�������
        int nBytesRead = 0;
        //���ǻ���
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
            //������ݻ��壬���ر�����
            sourceDataLine.drain();
            sourceDataLine.close();
        }
    }
}
