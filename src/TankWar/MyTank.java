/**
 * FileName: MyTank
 * Author:   16681
 * Date:     2019/3/28 19:38
 * Description: 我的坦克
 * 功能：
 * 1.画出坦克
 * 2.我的坦克可以上下移动
 * 3.可以发射子弹，子弹连发（最多五颗）
 * 4.当我的坦克击中敌坦时，敌坦消失（爆炸效果）
 * 5.我被击中后，显示爆炸效果
 * 6.防止敌坦重叠运动:决定吧判断是否碰撞的函数写到EnemyTank类
 * 7.可以分关：做一个开始的Panel,它是一个空的;闪烁效果
 * 8.可以在玩游戏的时候暂停和继续：当用户按下空格键暂停时，子弹的速度和坦克的速度都设为0，控制坦克方向不变
 * 9.可以记录玩家的成绩：1.写一个记录类，记录成绩。2.存盘退出，可以记录当前敌人坦克的坐标并可以恢复。3.退出保存玩家的成绩
 * 10.java如何操作声音文件
 */
package TankWar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import static java.awt.Font.BOLD;

class startPanel extends JPanel implements Runnable {
    int time = 0;

    public void paint(Graphics g) {

        super.paint(g);
        g.fillRect(0, 0, 600, 400);
        if (time % 2 == 0) {
            g.setColor(Color.YELLOW);
            g.setFont(new Font("华文新魏", BOLD, 60));
            g.drawString("第一关", 200, 200);
        }
    }

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(400);
            } catch (Exception e) {
                e.getStackTrace();
            }
            this.repaint();
            time++;
        }
    }
}

class MyPanel extends JPanel implements KeyListener, Runnable {
    //定义我的坦克
    Hero hero = null;
    //定义敌人坦克组
    //用Vector线程安全
    Vector<EnemyTank> ets = new Vector<>();
    Vector<Node> nodes = new Vector<>();
    EnemyTank enemyTank = null;
    int ensize = 3;
    //定义一个炸弹类
    Vector<Boom> booms = new Vector<>();
    //定义三张图片到面板中:三张图片组成一个炸弹
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String flag) {

        Remember.readscore();
        hero = new Hero(400, 300);
        if (flag.equals("new")) {
            //初始化敌人坦克
            for (int i = 0; i < ensize; i++) {
                //创建一辆敌人坦克对象
                EnemyTank et = new EnemyTank((i + 1) * 50, 10);
                et.setDirect(1);
                //将MyPanel上的敌坦集合交给该敌坦
                et.setEts(ets);
                //启动敌坦
                Thread thread = new Thread(et);
                thread.start();
                //增加一颗子弹
                Shot shot = new Shot(et.x + 10, et.y + 35, 1);
                //加给敌坦
                et.shots.add(shot);
                //开启敌坦子弹线程
                Thread thread1 = new Thread(shot);
                thread1.start();
                //加入到敌人坦克集合中
                ets.add(et);
            }
        } else {
//            System.out.println("*****************************");
            nodes = new Remember().getNodesandEnums();
            for (int i = 0; i < nodes.size(); i++) {
//                System.out.println("*****************************");
                Node node = nodes.get(i);
                //获取每一辆敌人坦克对象的坐标
                EnemyTank et = new EnemyTank(node.x, node.y);
                et.setDirect(node.direct);
                //将MyPanel上的敌坦集合交给该敌坦
                et.setEts(ets);
                //启动敌坦
                Thread thread2 = new Thread(et);
                thread2.start();
                //增加一颗子弹
                Shot shot = new Shot(et.x + 10, et.y + 35, 1);
                //加给敌坦
                et.shots.add(shot);
                //开启敌坦子弹线程
                Thread thread3 = new Thread(shot);
                thread3.start();
                //加入到敌人坦克集合中
                ets.add(et);
            }
        }
        try {
            //初始化爆炸的图片
            image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/boom1.png"));
            image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/boom2.png"));
            image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/boom3.png"));
        }catch (Exception e){
            e.getStackTrace();
        }
        //播放音乐
        Music music = new Music("src\\MusicFile\\2.wav");
        music.start();
    }

    public void paint(Graphics g) {

        super.paint(g);
        g.fillRect(0, 0, 600, 400);
        //画出子弹:从集合中获取每一个子弹
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot myshot = hero.shots.get(i);
            if (myshot != null && myshot.isLive == true) {
                g.setColor(Color.CYAN);
                g.fill3DRect(myshot.x, myshot.y, 2, 2, false);
            }
            if (myshot.isLive == false) {
                //从shots中删除该子弹，可以重新发射一颗子弹
                hero.shots.remove(myshot);
            }
        }
        //提示坦克剩余信息
        this.show_info(g);
        //画出炸弹
        for (int i = 0; i < booms.size(); i++) {
            Boom boom = booms.get(i);
            if (boom.Life > 6) {
                g.drawImage(image1, boom.x, boom.y, 30, 30, this);
            }
            if (boom.Life > 3) {
                g.drawImage(image2, boom.x, boom.y, 30, 30, this);
            } else {
                g.drawImage(image3, boom.x, boom.y, 30, 30, this);
            }
            //让炸弹的生命值减小
            boom.LifeDown();
            if (boom.Life == 0) {
                booms.remove(boom);
            }
        }
        //画出自己的坦克
        if (hero.isLive) {
            this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
        }
        //画出敌人的坦克
        for (int i = 0; i < ets.size(); i++) {
            //判断坦克是否被子弹击中死亡
            EnemyTank enemyTank = ets.get(i);
            if (enemyTank.isLive) {
                this.drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //画出敌人坦克的子弹
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //取出子弹
                    Shot enemyshot = enemyTank.shots.get(j);
                    //判断子弹是否还活着,画出子弹
                    if (enemyshot.isLive) {
                        g.fill3DRect(enemyshot.x, enemyshot.y, 2, 2, false);
                    } else {
                        enemyTank.shots.remove(enemyshot);
                    }
                }
            }
        }
    }

    public void drawTank(int x, int y, Graphics g, int direct, int type) {
        //判断坦克的类型
        switch (type) {
            //我方坦克
            case 0:
                g.setColor(Color.CYAN);
                break;
            //敌人坦克
            case 1:
                g.setColor(Color.YELLOW);
                break;
        }
        //判断坦克的方向
        switch (direct) {
            //向上
            case 0:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y - 5);
                break;
            //向下
            case 1:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 35, x + 10, y + 15);
                break;
            //向左
            case 2:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x - 5, y + 10, x + 10, y + 10);
                break;
            //向右
            case 3:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 10, y + 10, x + 35, y + 10);
                break;
        }
    }

    //显示记录
    public void show_info(Graphics g) {
        //提示双方坦克数量
        this.drawTank(150, 430, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Remember.getNumber() + "", 180, 440);
        this.drawTank(220, 430, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Remember.getMyLife() + "", 250, 440);
        //画出玩家的总成绩（消灭敌人坦克数量）
        g.setColor(Color.BLACK);
        g.setFont(new Font("宋体", BOLD, 20));
        g.drawString("您的总成绩：" + "", 610, 30);
        g.drawString(Remember.getScore() + "", 650, 70);
        this.drawTank(610, 50, g, 0, 1);
    }

    // 判断子弹是否击中敌人坦克
    public void hitEnemyTank() {
        //判断子弹是否击中敌人坦克
        //遍历每一个子弹，将每一个 子弹 与 敌人坦克 匹配
        for (int i = 0; i < hero.shots.size(); i++) {
            //取出子弹
            Shot shot = hero.shots.get(i);
            //判断子弹是否有效
            if (shot.isLive) {
                //取出每个坦克，与它判断
                for (int j = 0; j < ets.size(); j++) {
                    //取出每一个坦克
                    EnemyTank enemyTank = ets.get(j);
                    //判断坦克是否还活着
                    if (enemyTank.isLive) {
                        if (this.hitTank(shot, enemyTank)) {
                            //减少敌人坦克的数量
                            Remember.ReduceNum();
                            //增加玩家的成绩
                            Remember.addScore();
                            System.out.println("111");
                        }
                    }
                }
            }
        }
    }

    //敌坦的子弹是否击中我的坦克
    public void hitMe() {
//取出每一个敌坦
        for (int i = 0; i < ets.size(); i++) {
            EnemyTank enemyTank = ets.get(i);
            //取出每一个敌坦的子弹
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //取出子弹
                Shot shot = enemyTank.shots.get(j);
                //判断shot是否击中我的坦克
                if (hero.isLive) {
                    if (this.hitTank(shot, hero)) {
                    }
                }
            }
        }
    }

    //函数：判断子弹是否击中坦克
    public boolean hitTank(Shot s, Tank et) {

        boolean b = false;
        //判断该坦克的方向
        switch (et.direct) {
            //敌坦向上和向下：
            case 0:
            case 1:
                //子弹是否击中
                if (s.x > et.x && s.x < et.x + 20 && s.y > et.y && s.y < et.y + 30) {
                    //子弹死亡
                    s.isLive = false;
                    //敌人坦克死亡
                    et.isLive = false;
                    //创建一个炸弹
                    Boom boom = new Boom(et.x, et.y);
                    //放入Vector
                    booms.add(boom);
                    b = true;
                }
                break;
            //向左向右
            case 2:
            case 3:
                if (s.x > et.x && s.x < et.x + 30 && s.y > et.y && s.y < et.y + 20) {
                    //子弹死亡
                    s.isLive = false;
                    //敌人坦克死亡
                    et.isLive = false;
                    //创建一个炸弹
                    Boom boom = new Boom(et.x, et.y);
                    //放入Vector
                    booms.add(boom);
                    b = true;
                }
                break;
        }
        return b;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    //w s a d代表上下左右
    @Override
    public void keyPressed(KeyEvent e) {
        //向上
        if (e.getKeyCode() == KeyEvent.VK_W) {
            this.hero.direct = 0;
            this.hero.moveup();
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            this.hero.direct = 1;
            this.hero.movedown();
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            this.hero.direct = 2;
            this.hero.moveleft();
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            this.hero.direct = 3;
            this.hero.moveright();
        }
        //按下J键，发射子弹
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //最多只能连发5颗子弹
            if (this.hero.shots.size() < 5) {
                this.hero.ShotEnemy();
            }
        }
        if (e.getKeyCode() == KeyEvent.VK_I) {
            for (int i = 0; i < ets.size(); i++) {
                enemyTank = ets.get(i);
                enemyTank.speed = 0;
                enemyTank.direct = enemyTank.getDirect();
            }
            this.hero.speed = 0;
            //方向不变
            this.hero.direct = this.hero.getDirect();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //重画子弹
    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.getStackTrace();
            }
            this.hitEnemyTank();
            //判断敌人坦克是否击中我
            this.hitMe();
            this.repaint();
        }
    }
}

public class MyTank extends JFrame implements ActionListener {
    MyPanel myPanel = null;
    //定义一个开始面板
    startPanel startpanel = null;
    //创建菜单和菜单项
    JMenuBar jMenuBar = null;
    JMenu jMenu = null;
    JMenuItem saveout = null;
    JMenuItem jMenuItem = null;
    JMenuItem saveall = null;
    JMenuItem congame = null;

    public MyTank() {
//
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("游戏(G)");
        //设置快捷键
        jMenu.setMnemonic('G');
        jMenuItem = new JMenuItem("开始新游戏(N)");
        //响应，注册监听
        jMenuItem.addActionListener(this);
        jMenuItem.setActionCommand("newgame");
        saveout = new JMenuItem("保存退出（E）");
        saveout.setActionCommand("saveout");
        saveout.addActionListener(this);
        saveall = new JMenuItem("保存游戏并退出（S）");
        saveall.setActionCommand("saveall");
        saveall.addActionListener(this);
        congame = new JMenuItem("继续上局游戏（C）");
        congame.setActionCommand("congame");
        congame.addActionListener(this);
        jMenu.add(jMenuItem);
        jMenu.add(saveout);
        jMenu.add(saveall);
        jMenu.add(congame);
        jMenuBar.add(jMenu);
        startpanel = new startPanel();
        this.setJMenuBar(jMenuBar);
        Thread thread = new Thread(startpanel);
        thread.start();
        this.add(startpanel);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //对用户不同的点击做出不同的处理
        //开始游戏
        if (e.getActionCommand().equals("newgame")) {
            //创建战场面板
            myPanel = new MyPanel("new");
            //启动myPanel的线程
            Thread t = new Thread(myPanel);
            t.start();
            //先删除开始面板
            this.remove(startpanel);
            this.add(myPanel);
            this.addKeyListener(myPanel);
            this.setVisible(true);
        }
        //保存退出
        else if (e.getActionCommand().equals("saveout")) {
            //保存玩家成绩
            Remember.keepscore();
            System.exit(0);
        }
        //保存游戏：保存坐标、坦克方向和剩余数量
        else if (e.getActionCommand().equals("saveall")) {
            //保存游戏状态
            try {
                //工作
                //要让Remember知道外界的EnemyTanks
                Remember remember = new Remember();
                remember.setEnemyTanks(myPanel.ets);
                //保存坐标
                remember.savexandy();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
            System.exit(0);
        } else if (e.getActionCommand().equals("congame")) {
            //继续上局游戏
            //创建战场面板
//            System.out.println("进入");
            myPanel = new MyPanel("con");
//            myPanel.nodes = new Remember().getNodesandEnums();
            //启动myPanel的线程
            Thread t = new Thread(myPanel);
            t.start();
            //先删除开始面板
            this.remove(startpanel);
            this.add(myPanel);
            this.addKeyListener(myPanel);
            this.setVisible(true);
        }
    }

    public static void main(String[] args) {

        new MyTank();
    }
}
