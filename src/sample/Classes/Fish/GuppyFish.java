package sample.Classes.Fish;

import javafx.scene.image.ImageView;


// Обыкновенные кролики рождаются каждые N1 секунд с вероятностью P1.

public class GuppyFish extends Fish {

    public static int countGuppyFish = 0;

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
