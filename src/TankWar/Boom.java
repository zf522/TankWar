/**
 * FileName: Boom
 * Author:   16681
 * Date:     2019/3/31 8:30
 * Description: ը����
 */
package TankWar;

public class Boom {
    int x, y;
    boolean isLive = true;
    //ը������ֵΪ9����ըʱ����������ʱ�䲻ͬ��ʾ��ͬ��ͼƬ
    int Life = 9;

    public Boom(int x, int y) {

        this.x = x;
        this.y = y;
    }

    //��������ֵ
    public void LifeDown() {

        if (Life > 0) {
            Life--;
        } else {
            this.isLive = false;
        }
    }
}
