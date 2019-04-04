/**
 * FileName: EnemyTank
 * Author:   16681
 * Date:     2019/3/29 14:57
 * Description: 敌人坦克
 */
package TankWar;

import sun.text.resources.cldr.ia.FormatData_ia;

import java.util.Vector;

class EnemyTank extends Tank implements Runnable {

    //定义一个敌坦子弹集合，存放子弹
    Vector<Shot> shots = new Vector<>();
    int time = 0;
    //定义一个集合，可以访问到MyPanel上的所有的敌坦集合
    Vector<EnemyTank> enemyTanks = new Vector<>();

    //得到MyPanel中敌坦集合
    public void setEts(Vector<EnemyTank> vv) {

        this.enemyTanks = vv;
    }

    public EnemyTank(int x, int y) {

        super(x, y);
    }

    //判断该敌坦是否碰到其他敌坦
    public boolean isTouchOtherEnemy() {

        boolean b = false;
        switch (this.direct) {
            case 0:
                //该坦克向上
                //取出所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //如果不是自己
                    if (enemyTank != this) {
                        //如果其他敌坦向上或者向下
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            //该坦克的上一点
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                            //该坦克的下一点
                            if (this.x + 20 >= enemyTank.x && this.x + 20 <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //如果其他敌坦向左或者向右
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
                //该坦克向下
                //取出所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //如果不是自己
                    if (enemyTank != this) {
                        //如果其他敌坦向上或者向下
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y + 30 >= enemyTank.y && this.y + 30 <= enemyTank.y + 30) {
                                return true;
                            }
                            if (this.x + 20 >= enemyTank.x && this.x + 20 <= enemyTank.x + 20 && this.y + 30 >= enemyTank.y && this.y + 30 <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //如果其他敌坦向左或者向右
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
                //该坦克向左
                //取出所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //如果不是自己
                    if (enemyTank != this) {
                        //如果其他敌坦向上或者向下
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                            if (this.x >= enemyTank.x && this.x <= enemyTank.x + 20 && this.y + 20 >= enemyTank.y && this.y + 20 <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //如果其他敌坦向左或者向右
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
                //该坦克向右
                //取出所有的坦克
                for (int i = 0; i < enemyTanks.size(); i++) {
                    EnemyTank enemyTank = enemyTanks.get(i);
                    //如果不是自己
                    if (enemyTank != this) {
                        //如果其他敌坦向上或者向下
                        if (enemyTank.direct == 0 || enemyTank.direct == 1) {
                            if (this.x + 30 >= enemyTank.x && this.x + 30 <= enemyTank.x + 20 && this.y >= enemyTank.y && this.y <= enemyTank.y + 30) {
                                return true;
                            }
                            if (this.x + 30 >= enemyTank.x && this.x + 30 <= enemyTank.x + 20 && this.y + 20 >= enemyTank.y && this.y + 20 <= enemyTank.y + 30) {
                                return true;
                            }
                        }
                        //如果其他敌坦向左或者向右
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
                //坦克向上走30步
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
            //判断是否需要给坦克添加新的子弹
            if (time % 2 == 0) {
                if (isLive) {
                    if (shots.size() < 5) {
                        //添加子弹
                        Shot s = null;
                        switch (direct) {
                            case 0:
                                //创建一颗子弹
                                s = new Shot(x + 10, y - 5, 0);
                                //把子弹加入到集合中
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
                        //启动子弹线程
                        Thread thread = new Thread(s);
                        thread.start();
                    }
                }
            }
            //让坦克随机产生一个方向
            this.direct = (int) (Math.random() * 4);
            //判断敌坦是否死亡
            if (this.isLive == false) {
                //让坦克死亡，退出线程
                break;
            }
        }
    }
}
