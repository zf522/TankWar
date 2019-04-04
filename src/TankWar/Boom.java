/**
 * FileName: Boom
 * Author:   16681
 * Date:     2019/3/31 8:30
 * Description: 炸弹类
 */
package TankWar;

public class Boom {
    int x, y;
    boolean isLive = true;
    //炸弹生命值为9：爆炸时，随着生命时间不同显示不同的图片
    int Life = 9;

    public Boom(int x, int y) {

        this.x = x;
        this.y = y;
    }

    //减少生命值
    public void LifeDown() {

        if (Life > 0) {
            Life--;
        } else {
            this.isLive = false;
        }
    }
}
