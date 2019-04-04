/**
 * FileName: Remember
 * Author:   16681
 * Date:     2019/4/1 8:48
 * Description: ��¼��
 */
package TankWar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Vector;

class Node {
    int x;
    int y;
    int direct;

    public Node(int x, int y, int direct) {

        this.x = x;
        this.y = y;
        this.direct = direct;
    }
}

public class Remember {
    //��¼��̹����
    private static int number = 20;
    //��¼�ҷ�̹�˵�����ֵ
    private static int myLife = 3;
    //��¼��ҵ��ܳɼ�
    private static int score = 0;

    //����̹�˵�����͵�̹��ʣ������
    private static Vector<EnemyTank> enemyTanks = null;
    //�ӱ�����ļ��ж�ȡ��һ�ֳɼ�
    private static FileReader fileReader = null;
    private static BufferedReader bufferedReader = null;
    //������ҳɼ����ļ���
    private static FileWriter fileWriter = null;
    private static BufferedWriter bufferedWriter = null;
    //�Ӽ�¼�ָ���¼��
    static Vector<Node> nodes = new Vector<>();

    //��ɶ�ȡ����,�ָ���¼��
    public Vector<Node> getNodesandEnums() {
//        System.out.println("*****************************");
        try {
            fileReader = new FileReader("src\\ScoreFile\\myScore.txt");
            bufferedReader = new BufferedReader(fileReader);
            String string = "";
            //�ȶ�ȡ��һ��
            string = bufferedReader.readLine();
            score = Integer.parseInt(string);
            //��ȡ��һ��
            while ((string = bufferedReader.readLine()) != null) {
                String[] xyz = string.split(" ");
                Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
                nodes.add(node);
//                System.out.println("*****************************");
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
        return nodes;
    }

    //����ɼ�
    public static void keepscore() {

        try {
            //�����ļ�����д�ļ�
            fileWriter = new FileWriter("src\\ScoreFile\\myScore.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(score + "\n");
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    //��ȡ�ɼ�
    public static void readscore() {

        try {
            fileReader = new FileReader("src\\ScoreFile\\myScore.txt");
            bufferedReader = new BufferedReader(fileReader);
            String string = bufferedReader.readLine();
            score = Integer.parseInt(string);
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                bufferedReader.close();
                fileReader.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    //������ٵĵ�̹���������ꡢ����
    public static void savexandy() {

        try {
            //�����ļ�����д�ļ�
            fileWriter = new FileWriter("src\\ScoreFile\\myScore.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(score + "\r\n");
            //���浱ǰ���̹������ͷ���
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    String solution = enemyTank.x + " " + enemyTank.y + " " + enemyTank.direct;
                    //д���õ�����
                    bufferedWriter.write(solution + "\r\n");
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            try {
                bufferedWriter.close();
                fileWriter.close();
            } catch (Exception e) {
                e.getStackTrace();
            }
        }
    }

    public static int getNumber() {

        return number;
    }

    public static void setNumber(int number) {

        Remember.number = number;
    }

    public static int getMyLife() {

        return myLife;
    }

    public static void setMyLife(int myLife) {

        Remember.myLife = myLife;
    }

    public static int getScore() {

        return score;
    }

    public static void setScore(int score) {

        Remember.score = score;
    }

    public static Vector<EnemyTank> getEnemyTanks() {

        return enemyTanks;
    }

    public static void setEnemyTanks(Vector<EnemyTank> enemyTanks) {

        Remember.enemyTanks = enemyTanks;
    }

    //����̹������
    public static void ReduceNum() {

        number--;
    }

    //�ҷ�̹������ֵ
    public static void ReducemyLife() {

        myLife--;
    }

    //��ҵ��ܳɼ�
    public static void addScore() {

        score++;
    }
}
