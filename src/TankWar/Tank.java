/**
 * FileName: Tank
 * Author:   16681
 * Date:     2019/3/28 19:27
 * Description: ̹�˵ĸ���
 */
package TankWar;

class Tank {
    int x = 0;
    int y = 0;
    //0�����ϣ� 1�����£� 2������ 3������
    int direct = 0;
    //�ƶ��ٶ�
    int speed = 4;
//    int color;
    boolean isLive =true;

    public Tank(int x, int y) {

        this.x = x;
        this.y = y;

    }

//    public int getColor() {
//
//        return color;
//    }
//
//    public void setColor(int color) {
//
//        this.color = color;
//    }

    public int getSpeed() {

        return speed;
    }

    public void setSpeed(int speed) {

        this.speed = speed;
    }

    public int getDirect() {

        return direct;
    }

    public void setDirect(int direct) {

        this.direct = direct;
    }

    public int getX() {

        return x;
    }

    public void setX(int x) {

        this.x = x;
    }

    public int getY() {

        return y;
    }

    public void setY(int y) {

        this.y = y;
    }
}
