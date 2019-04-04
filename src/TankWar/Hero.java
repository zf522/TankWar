/**
 * FileName: Hero
 * Author:   16681
 * Date:     2019/3/29 14:24
 * Description: 我的坦克
 */
package TankWar;

import java.util.Vector;

//我的tank
class Hero extends Tank {
    //定义子弹

    Vector<Shot> shots = new Vector<>();
    Shot s = null;
    //一个类继承，必须要写子类构造方法
    //继承父类，在子类的构造方法中用super调用父类的构造方法

    public Hero(int x, int y) {

        super(x, y);
    }

    public void ShotEnemy() {

        switch (this.direct) {
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

    public void moveup() {

        if (y > 0) {
            y -= speed;
        }
    }

    public void movedown() {

        if (y < 370) {
            y += speed;
        }
    }

    public void moveleft() {

        if (x > 0) {
            x -= speed;
        }
    }

    public void moveright() {

        if (x < 570) {
            x += speed;
        }
    }
}