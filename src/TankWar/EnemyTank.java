/**
 * FileName: EnemyTank
 * Author:   16681
 * Date:     2019/3/29 14:57
 * Description: ����̹��
 */
package TankWar;

import sun.text.resources.cldr.ia.FormatData_ia;

import java.util.Vector;

class EnemyTank extends Tank implements Runnable {

    //����һ����̹�ӵ����ϣ�����ӵ�
    Vector<Shot> shots = new Vector<>();
    int time = 0;
    //����һ�����ϣ����Է��ʵ�MyPanel�ϵ����еĵ�̹����
    Vector<EnemyTank> enemyTanks = new Vector<>();

    //�õ�MyPanel�е�̹����
    public void setEts(Vector<EnemyTank> vv) {

        this.enemyTanks = vv;
    }

    public EnemyTank(int x, int y) {

        super(x, y);
    }

    //�жϸõ�̹�Ƿ�����������̹
    public boolean isTouchOtherEnemy() {

        boolean b = false;
        switch (this.direct) {
            case 0:
                //��̹������
                //ȡ�����е�̹��
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //��������Լ�
                    if (enemyTank != this) {
                        //���������̹���ϻ�������
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            //��̹�˵���һ��
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                            //��̹�˵���һ��
                            if (this.x + 20 >= enemyTank.x && this.x + 20 <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //���������̹�����������
                        if (enemyTank.direct == 2 || enemyTank.direct == 3) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 30 && this.y >= enemyTank.y && this.y <= enemyTank.y + 20) {
                                return true;
                            }
                            if (this.x + 20 >= enemyTank.x && this.x + 30 <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 20) {
                                return true;
                            }
                        }
                    }
                }
            case 1:
                //��̹������
                //ȡ�����е�̹��
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //��������Լ�
                    if (enemyTank != this) {
                        //���������̹���ϻ�������
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y + 30 >= enemyTank.y && this.y + 30 <= enemyTank.y + 30) {
                                return true;
                            }
                            if (this.x + 20 >= enemyTank.x && this.x + 20 <= enemyTank.x + 20 && this.y + 30 >= enemyTank.y && this.y + 30 <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //���������̹�����������
                        if (enemyTank.direct == 2 || enemyTank.direct == 3) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 30 && this.y + 30 >= enemyTank.y && this.y + 30 <= enemyTank.y + 20) {
                                return true;
                            }
                            if (this.x + 20 >= enemyTank.x && this.x + 20 <= enemyTank.x + 30 && this.y + 30 >= enemyTank.y && this.y + 30 <= enemyTank.y + 20) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 2:
                //��̹������
                //ȡ�����е�̹��
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //��������Լ�
                    if (enemyTank != this) {
                        //���������̹���ϻ�������
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y + 20 >= enemyTank.y && this.y + 20 <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //���������̹�����������
                        if (enemyTank.direct == 2 || enemyTank.direct == 3) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 30 && this.y >= enemyTank.y && this.y <= enemyTank.y + 20) {
                                return true;
                            }
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 30 && this.y + 20 >= enemyTank.y && this.y + 20 <= enemyTank.y + 20) {
                                return true;
                            }
                        }
                    }
                }
                break;
            case 3:
                //��̹������
                //ȡ�����е�̹��
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //��������Լ�
                    if (enemyTank != this) {
                        //���������̹���ϻ�������
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            if (this.x + 30 >= enemyTank.x && this.x + 30 <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                            if (this.x + 30 >= enemyTank.x && this.x + 30 <= enemyTank.x + 20 && this.y + 20 >= enemyTank.y && this.y + 20 <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //���������̹�����������
                        if (enemyTank.direct == 2 || enemyTank.direct == 3) {
                            if (this.x + 30 >= enemyTank.x && this.x + 30 <= enemyTank.x + 30 && this.y >= enemyTank.y && this.y <= enemyTank.y + 20) {
                                return true;
                            }
                            if (this.x + 30 >= enemyTank.x && this.x + 30 <= enemyTank.x + 30 && this.y + 20 >= enemyTank.y && this.y + 20 <= enemyTank.y + 20) {
                                return true;
                            }
                        }
                    }
                }
                break;
        }
        return b;
    }

    @Override
    public void run() {

        while (true) {
            switch (this.direct) {
                //̹��������30��
                case 0:
                    for (int i = 0; i < 30; i++) {
                        if (y > 0 && !isTouchOtherEnemy()) {
                            y -= speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    break;
                case 1:
                    for (int i = 0; i < 30; i++) {
                        if (y <=370 && !isTouchOtherEnemy()) {
                            y += speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    break;
                case 2:
                    for (int i = 0; i < 30; i++) {
                        if (x > 0 && !isTouchOtherEnemy()) {
                            x -= speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    break;
                case 3:
                    for (int i = 0; i < 30; i++) {
                        if (x <= 570 && !isTouchOtherEnemy()) {
                            x += speed;
                        }
                        try {
                            Thread.sleep(50);
                        } catch (Exception e) {
                            e.getStackTrace();
                        }
                    }
                    break;
            }
            this.time++;
            //�ж��Ƿ���Ҫ��̹������µ��ӵ�
            if (time % 2 == 0) {
                if (isLive) {
                    if (shots.size() < 5) {
                        //����ӵ�
                        Shot s = null;
                        switch (direct) {
                            case 0:
                                //����һ���ӵ�
                                s = new Shot(x + 10, y - 5, 0);
                                //���ӵ����뵽������
                                shots.add(s);
                                break;
                            case 1:
                                s = new Shot(x + 10, y + 20, 1);
                                shots.add(s);
                                break;
                            case 2:
                                s = new Shot(x - 5, y + 10, 2);
                                shots.add(s);
                                break;
                            case 3:
                                s = new Shot(x + 35, y + 10, 3);
                                shots.add(s);
                                break;
                        }
                        //�����ӵ��߳�
                        Thread thread = new Thread(s);
                        thread.start();
                    }
                }
            }
            //��̹���������һ������
            this.direct = (int) (Math.random() * 4);
            //�жϵ�̹�Ƿ�����
            if (this.isLive == false) {
                //��̹���������˳��߳�
                break;
            }
        }
    }
}
