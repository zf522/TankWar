/**
 * FileName: Remember
 * Author:   16681
 * Date:     2019/4/1 8:48
 * Description: 记录类
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
    //记录敌坦数量
    private static int number = 20;
    //记录我方坦克的生命值
    private static int myLife = 3;
    //记录玩家的总成绩
    private static int score = 0;

    //保存坦克的坐标和敌坦的剩余数量
    private static Vector<EnemyTank> enemyTanks = null;
    //从保存的文件中读取上一局成绩
    private static FileReader fileReader = null;
    private static BufferedReader bufferedReader = null;
    //保存玩家成绩到文件中
    private static FileWriter fileWriter = null;
    private static BufferedWriter bufferedWriter = null;
    //从记录恢复记录点
    static Vector<Node> nodes = new Vector<>();

    //完成读取任务,恢复记录点
    public Vector<Node> getNodesandEnums() {
//        System.out.println("*****************************");
        try {
            fileReader = new FileReader("src\\ScoreFile\\myScore.txt");
            bufferedReader = new BufferedReader(fileReader);
            String string = "";
            //先读取第一行
            string = bufferedReader.readLine();
            score = Integer.parseInt(string);
            //读取下一行
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

    //保存成绩
    public static void keepscore() {

        try {
            //创建文件流，写文件
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

    //读取成绩
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

    //保存击毁的敌坦数量和坐标、方向
    public static void savexandy() {

        try {
            //创建文件流，写文件
            fileWriter = new FileWriter("src\\ScoreFile\\myScore.txt");
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(score + "\r\n");
            //保存当前活的坦克坐标和方向
            for (int i = 0; i < enemyTanks.size(); i++) {
                EnemyTank enemyTank = enemyTanks.get(i);
                if (enemyTank.isLive) {
                    String solution = enemyTank.x + " " + enemyTank.y + " " + enemyTank.direct;
                    //写入获得的坐标
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

    //敌人坦克数量
    public static void ReduceNum() {

        number--;
    }

    //我方坦克生命值
    public static void ReducemyLife() {

        myLife--;
    }

    //玩家的总成绩
    public static void addScore() {

        score++;
    }
}
