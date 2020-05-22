package sample.Classes.Fish;

import javafx.scene.image.ImageView;

/**
 *  GuppyFish рождаются каждые N1 секунд с вероятностью P1.
 */

public class GuppyFish extends Fish {

    public static int countGuppyFish = 0;

    /**
     *
     * @param imageView бэкграудн
     * @param x позиция по x
     * @param y позиция по y
     * @param timeBorn время рождения
     * @param timeLife время смерти
     */
    public GuppyFish(ImageView imageView, int x, int y, int timeBorn, int timeLife){
        super(imageView,timeBorn,timeLife);
        setPosition(x,y);
        countGuppyFish++;
        typeFish = "Guppy Fish";
    }

    @Override
    public void updaTimeLiveFish(){
        this.timeLife--;
        if (timeLife<0) {
            isDead = true;
            countGuppyFish--;
            super.dicreementcountsAllFish();
        }
    }

}
