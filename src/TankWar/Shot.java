/**
 * FileName: Shot
 * Author:   16681
 * Date:     2019/3/29 16:38
 * Description: �ӵ���
 */
package TankWar;

class Shot implements Runnable {
    int x;
    int y;
    int direct;
    int speed = 4;
    //�ж��ӵ�������
    boolean isLive = true;

    public Shot(int x, int y, int direct) {

        this.x = x;
        this.y = y;
        this.direct = direct;
    }

    @Override
    public void run() {
        //�����̣߳��ӵ�ֻ��һ��������
        while (true) {
            try {
                Thread.sleep(50);
            } catch (Exception e) {
                e.getStackTrace();
            }
            switch (direct) {
                case 0:
                    y -= speed;
                    break;
                case 1:
                    y += speed;
                    break;
                case 2:
                    x -= speed;
                    break;
                case 3:
                    x += speed;
                    break;
            }
            //�ж��ӵ��Ƿ������߽�����
            if (x < 0 || x > 600 || y < 0 || y > 400) {
                this.isLive = false;
                break;
            }
        }
    }
}
