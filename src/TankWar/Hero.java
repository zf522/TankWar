/**
 * FileName: Hero
 * Author:   16681
 * Date:     2019/3/29 14:24
 * Description: �ҵ�̹��
 */
package TankWar;

import java.util.Vector;

//�ҵ�tank
class Hero extends Tank {
    //�����ӵ�

    Vector<Shot> shots = new Vector<>();
    Shot s = null;
    //һ����̳У�����Ҫд���๹�췽��
    //�̳и��࣬������Ĺ��췽������super���ø���Ĺ��췽��

    public Hero(int x, int y) {

        super(x, y);
    }

    public void ShotEnemy() {

        switch (this.direct) {
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