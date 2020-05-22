package sample.Classes.Fish;

import javafx.scene.image.ImageView;

/*
*  Альбиносы рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа кроликов,
* в противном случае – не рождаются вовсе.
* */
public class GoldFish extends Fish {

    public static int countGoldFish = 0;

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
