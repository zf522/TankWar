/**
 * FileName: MyTank
 * Author:   16681
 * Date:     2019/3/28 19:38
 * Description: �ҵ�̹��
 * ���ܣ�
 * 1.����̹��
 * 2.�ҵ�̹�˿��������ƶ�
 * 3.���Է����ӵ����ӵ������������ţ�
 * 4.���ҵ�̹�˻��е�̹ʱ����̹��ʧ����ըЧ����
 * 5.�ұ����к���ʾ��ըЧ��
 * 6.��ֹ��̹�ص��˶�:�������ж��Ƿ���ײ�ĺ���д��EnemyTank��
 * 7.���Էֹأ���һ����ʼ��Panel,����һ���յ�;��˸Ч��
 * 8.����������Ϸ��ʱ����ͣ�ͼ��������û����¿ո����ͣʱ���ӵ����ٶȺ�̹�˵��ٶȶ���Ϊ0������̹�˷��򲻱�
 * 9.���Լ�¼��ҵĳɼ���1.дһ����¼�࣬��¼�ɼ���2.�����˳������Լ�¼��ǰ����̹�˵����겢���Իָ���3.�˳�������ҵĳɼ�
 * 10.java��β��������ļ�
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
            g.setFont(new Font("������κ", BOLD, 60));
            g.drawString("��һ��", 200, 200);
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
    //�����ҵ�̹��
    Hero hero = null;
    //�������̹����
    //��Vector�̰߳�ȫ
    Vector<EnemyTank> ets = new Vector<>();
    Vector<Node> nodes = new Vector<>();
    EnemyTank enemyTank = null;
    int ensize = 3;
    //����һ��ը����
    Vector<Boom> booms = new Vector<>();
    //��������ͼƬ�������:����ͼƬ���һ��ը��
    Image image1 = null;
    Image image2 = null;
    Image image3 = null;

    public MyPanel(String flag) {

        Remember.readscore();
        hero = new Hero(400, 300);
        if (flag.equals("new")) {
            //��ʼ������̹��
            for (int i = 0; i < ensize; i++) {
                //����һ������̹�˶���
                EnemyTank et = new EnemyTank((i + 1) * 50, 10);
                et.setDirect(1);
                //��MyPanel�ϵĵ�̹���Ͻ����õ�̹
                et.setEts(ets);
                //������̹
                Thread thread = new Thread(et);
                thread.start();
                //����һ���ӵ�
                Shot shot = new Shot(et.x + 10, et.y + 35, 1);
                //�Ӹ���̹
                et.shots.add(shot);
                //������̹�ӵ��߳�
                Thread thread1 = new Thread(shot);
                thread1.start();
                //���뵽����̹�˼�����
                ets.add(et);
            }
        } else {
//            System.out.println("*****************************");
            nodes = new Remember().getNodesandEnums();
            for (int i = 0; i < nodes.size(); i++) {
//                System.out.println("*****************************");
                Node node = nodes.get(i);
                //��ȡÿһ������̹�˶��������
                EnemyTank et = new EnemyTank(node.x, node.y);
                et.setDirect(node.direct);
                //��MyPanel�ϵĵ�̹���Ͻ����õ�̹
                et.setEts(ets);
                //������̹
                Thread thread2 = new Thread(et);
                thread2.start();
                //����һ���ӵ�
                Shot shot = new Shot(et.x + 10, et.y + 35, 1);
                //�Ӹ���̹
                et.shots.add(shot);
                //������̹�ӵ��߳�
                Thread thread3 = new Thread(shot);
                thread3.start();
                //���뵽����̹�˼�����
                ets.add(et);
            }
        }
        try {
            //��ʼ����ը��ͼƬ
            image1 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/boom1.png"));
            image2 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/boom2.png"));
            image3 = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/images/boom3.png"));
        }catch (Exception e){
            e.getStackTrace();
        }
        //��������
        Music music = new Music("src\\MusicFile\\2.wav");
        music.start();
    }

    public void paint(Graphics g) {

        super.paint(g);
        g.fillRect(0, 0, 600, 400);
        //�����ӵ�:�Ӽ����л�ȡÿһ���ӵ�
        for (int i = 0; i < hero.shots.size(); i++) {
            Shot myshot = hero.shots.get(i);
            if (myshot != null && myshot.isLive == true) {
                g.setColor(Color.CYAN);
                g.fill3DRect(myshot.x, myshot.y, 2, 2, false);
            }
            if (myshot.isLive == false) {
                //��shots��ɾ�����ӵ����������·���һ���ӵ�
                hero.shots.remove(myshot);
            }
        }
        //��ʾ̹��ʣ����Ϣ
        this.show_info(g);
        //����ը��
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
            //��ը��������ֵ��С
            boom.LifeDown();
            if (boom.Life == 0) {
                booms.remove(boom);
            }
        }
        //�����Լ���̹��
        if (hero.isLive) {
            this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, 0);
        }
        //�������˵�̹��
        for (int i = 0; i < ets.size(); i++) {
            //�ж�̹���Ƿ��ӵ���������
            EnemyTank enemyTank = ets.get(i);
            if (enemyTank.isLive) {
                this.drawTank(enemyTank.getX(), enemyTank.getY(), g, enemyTank.getDirect(), 1);
                //��������̹�˵��ӵ�
                for (int j = 0; j < enemyTank.shots.size(); j++) {
                    //ȡ���ӵ�
                    Shot enemyshot = enemyTank.shots.get(j);
                    //�ж��ӵ��Ƿ񻹻���,�����ӵ�
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
        //�ж�̹�˵�����
        switch (type) {
            //�ҷ�̹��
            case 0:
                g.setColor(Color.CYAN);
                break;
            //����̹��
            case 1:
                g.setColor(Color.YELLOW);
                break;
        }
        //�ж�̹�˵ķ���
        switch (direct) {
            //����
            case 0:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 15, x + 10, y - 5);
                break;
            //����
            case 1:
                g.fill3DRect(x, y, 5, 30, false);
                g.fill3DRect(x + 15, y, 5, 30, false);
                g.fill3DRect(x + 5, y + 5, 10, 20, false);
                g.fillOval(x + 5, y + 10, 10, 10);
                g.drawLine(x + 10, y + 35, x + 10, y + 15);
                break;
            //����
            case 2:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x - 5, y + 10, x + 10, y + 10);
                break;
            //����
            case 3:
                g.fill3DRect(x, y, 30, 5, false);
                g.fill3DRect(x, y + 15, 30, 5, false);
                g.fill3DRect(x + 5, y + 5, 20, 10, false);
                g.fillOval(x + 10, y + 5, 10, 10);
                g.drawLine(x + 10, y + 10, x + 35, y + 10);
                break;
        }
    }

    //��ʾ��¼
    public void show_info(Graphics g) {
        //��ʾ˫��̹������
        this.drawTank(150, 430, g, 0, 1);
        g.setColor(Color.BLACK);
        g.drawString(Remember.getNumber() + "", 180, 440);
        this.drawTank(220, 430, g, 0, 0);
        g.setColor(Color.BLACK);
        g.drawString(Remember.getMyLife() + "", 250, 440);
        //������ҵ��ܳɼ����������̹��������
        g.setColor(Color.BLACK);
        g.setFont(new Font("����", BOLD, 20));
        g.drawString("�����ܳɼ���" + "", 610, 30);
        g.drawString(Remember.getScore() + "", 650, 70);
        this.drawTank(610, 50, g, 0, 1);
    }

    // �ж��ӵ��Ƿ���е���̹��
    public void hitEnemyTank() {
        //�ж��ӵ��Ƿ���е���̹��
        //����ÿһ���ӵ�����ÿһ�� �ӵ� �� ����̹�� ƥ��
        for (int i = 0; i < hero.shots.size(); i++) {
            //ȡ���ӵ�
            Shot shot = hero.shots.get(i);
            //�ж��ӵ��Ƿ���Ч
            if (shot.isLive) {
                //ȡ��ÿ��̹�ˣ������ж�
                for (int j = 0; j < ets.size(); j++) {
                    //ȡ��ÿһ��̹��
                    EnemyTank enemyTank = ets.get(j);
                    //�ж�̹���Ƿ񻹻���
                    if (enemyTank.isLive) {
                        if (this.hitTank(shot, enemyTank)) {
                            //���ٵ���̹�˵�����
                            Remember.ReduceNum();
                            //������ҵĳɼ�
                            Remember.addScore();
                            System.out.println("111");
                        }
                    }
                }
            }
        }
    }

    //��̹���ӵ��Ƿ�����ҵ�̹��
    public void hitMe() {
//ȡ��ÿһ����̹
        for (int i = 0; i < ets.size(); i++) {
            EnemyTank enemyTank = ets.get(i);
            //ȡ��ÿһ����̹���ӵ�
            for (int j = 0; j < enemyTank.shots.size(); j++) {
                //ȡ���ӵ�
                Shot shot = enemyTank.shots.get(j);
                //�ж�shot�Ƿ�����ҵ�̹��
                if (hero.isLive) {
                    if (this.hitTank(shot, hero)) {
                    }
                }
            }
        }
    }

    //�������ж��ӵ��Ƿ����̹��
    public boolean hitTank(Shot s, Tank et) {

        boolean b = false;
        //�жϸ�̹�˵ķ���
        switch (et.direct) {
            //��̹���Ϻ����£�
            case 0:
            case 1:
                //�ӵ��Ƿ����
                if (s.x > et.x && s.x < et.x + 20 && s.y > et.y && s.y < et.y + 30) {
                    //�ӵ�����
                    s.isLive = false;
                    //����̹������
                    et.isLive = false;
                    //����һ��ը��
                    Boom boom = new Boom(et.x, et.y);
                    //����Vector
                    booms.add(boom);
                    b = true;
                }
                break;
            //��������
            case 2:
            case 3:
                if (s.x > et.x && s.x < et.x + 30 && s.y > et.y && s.y < et.y + 20) {
                    //�ӵ�����
                    s.isLive = false;
                    //����̹������
                    et.isLive = false;
                    //����һ��ը��
                    Boom boom = new Boom(et.x, et.y);
                    //����Vector
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

    //w s a d������������
    @Override
    public void keyPressed(KeyEvent e) {
        //����
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
        //����J���������ӵ�
        if (e.getKeyCode() == KeyEvent.VK_J) {
            //���ֻ������5���ӵ�
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
            //���򲻱�
            this.hero.direct = this.hero.getDirect();
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    //�ػ��ӵ�
    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                e.getStackTrace();
            }
            this.hitEnemyTank();
            //�жϵ���̹���Ƿ������
            this.hitMe();
            this.repaint();
        }
    }
}

public class MyTank extends JFrame implements ActionListener {
    MyPanel myPanel = null;
    //����һ����ʼ���
    startPanel startpanel = null;
    //�����˵��Ͳ˵���
    JMenuBar jMenuBar = null;
    JMenu jMenu = null;
    JMenuItem saveout = null;
    JMenuItem jMenuItem = null;
    JMenuItem saveall = null;
    JMenuItem congame = null;

    public MyTank() {
//
        jMenuBar = new JMenuBar();
        jMenu = new JMenu("��Ϸ(G)");
        //���ÿ�ݼ�
        jMenu.setMnemonic('G');
        jMenuItem = new JMenuItem("��ʼ����Ϸ(N)");
        //��Ӧ��ע�����
        jMenuItem.addActionListener(this);
        jMenuItem.setActionCommand("newgame");
        saveout = new JMenuItem("�����˳���E��");
        saveout.setActionCommand("saveout");
        saveout.addActionListener(this);
        saveall = new JMenuItem("������Ϸ���˳���S��");
        saveall.setActionCommand("saveall");
        saveall.addActionListener(this);
        congame = new JMenuItem("�����Ͼ���Ϸ��C��");
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
        //���û���ͬ�ĵ��������ͬ�Ĵ���
        //��ʼ��Ϸ
        if (e.getActionCommand().equals("newgame")) {
            //����ս�����
            myPanel = new MyPanel("new");
            //����myPanel���߳�
            Thread t = new Thread(myPanel);
            t.start();
            //��ɾ����ʼ���
            this.remove(startpanel);
            this.add(myPanel);
            this.addKeyListener(myPanel);
            this.setVisible(true);
        }
        //�����˳�
        else if (e.getActionCommand().equals("saveout")) {
            //������ҳɼ�
            Remember.keepscore();
            System.exit(0);
        }
        //������Ϸ���������ꡢ̹�˷����ʣ������
        else if (e.getActionCommand().equals("saveall")) {
            //������Ϸ״̬
            try {
                //����
                //Ҫ��Remember֪������EnemyTanks
                Remember remember = new Remember();
                remember.setEnemyTanks(myPanel.ets);
                //��������
                remember.savexandy();
            } catch (Exception e1) {
                e1.getStackTrace();
            }
            System.exit(0);
        } else if (e.getActionCommand().equals("congame")) {
            //�����Ͼ���Ϸ
            //����ս�����
//            System.out.println("����");
            myPanel = new MyPanel("con");
//            myPanel.nodes = new Remember().getNodesandEnums();
            //����myPanel���߳�
            Thread t = new Thread(myPanel);
            t.start();
            //��ɾ����ʼ���
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
