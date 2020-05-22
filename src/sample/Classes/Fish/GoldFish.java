package sample.Classes.Fish;

import javafx.scene.image.ImageView;

/**
 * GoldFish рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа рыбок,
 * в противном случае – не рождаются вовсе.
 */
public class GoldFish extends Fish {

    public static int countGoldFish = 0;
    /**
     *
     * @param imageView бэкграудн
     * @param x позиция по x
     * @param y позиция по y
     * @param timeBorn время рождения
     * @param timeLife время смерти
     */
    public GoldFish(ImageView imageView, int x, int y, int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        this.setPosition(x,y);
        countGoldFish++;
        typeFish = "Gold Fish";
    }

    @Override
    public void updaTimeLiveFish(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countGoldFish--;
            super.dicreementcountsAllFish();
        }
    }
}
