package sample.Classes;

import javafx.scene.image.Image;
import javafx.scene.image.*;
import javafx.scene.layout.Pane;
import sample.Classes.Fish.GoldFish;
import sample.Classes.Fish.GuppyFish;
import sample.Classes.Fish.Fish;


/*
* Создать класс Habitat (среда), определяющий размер рабочей области и хранящий массив объектов,
* с параметрами, заданными вариантом.
* Предусмотреть в классе метод Update, вызывающийся по таймеру и получающий на вход время, прошедшее от начала симуляции.
* В данном методе должны генерироваться новые объекты и помещаться в поле визуализации в случайном месте.
* Визуализация объекта - использовать готовые небольшие картинки;
* */

public class Habitat {

    // размер и картинка заднего фона
    private static final int WIDTH = 400;
    private static final int HEIGH = 400;
    private static final Image imageBackground = new Image("sample/Images/fon.png");

    // картинки для рыбок
    private static final Image imageGoldFish = new Image("sample/Images/GoldFish.png");
    private static final Image imageGuppyFish = new Image("sample/Images/Guppi.png");

    private int N1; // Время рождения Гуппи(каждые N1 секунды)
    private int P1; // Вероятность % Гуппи
    private int N2; // Время рождения Золотой каждые N2 секунды)
    private int K2; // проценнт от общего числа рыбок
    private int timeLifeGoldFish;
    private int timeLifeGuppyFish;

    // массив рыбок
    private Collections collectionsFish;

    public Habitat(){
        collectionsFish = new Collections();
    }

    public void update(int time, Pane pane) {
        if (canBornGuppyFish(N1, P1, time))
        {
            GuppyFish guppyFish = makeGuppyFish(time);
            collectionsFish.add(guppyFish);
            pane.getChildren().addAll(guppyFish.getImageView());
        }

        if (canBornGoldFish(N2, K2, time))
        {
            GoldFish goldFish = makeGoldFish(time);
            collectionsFish.add(goldFish);
            pane.getChildren().addAll(goldFish.getImageView());
        }

        collectionsFish.updateCollectionsPerTime(pane);
    }

    //   Гуппи рождаются каждые N1 секунд с вероятностью P1.
    private boolean canBornGuppyFish(int N1, int P1, int time){
        int randomVariation = (int)Math.floor(Math.random()*100);
        return time % N1 == 0 && randomVariation <= P1;
    }

    private GuppyFish makeGuppyFish(int time){
        ImageView imageView = new ImageView(imageGuppyFish);
        int x = (int)Math.floor(Math.random()*(WIDTH- Fish.WIDTH));
        int y = (int)Math.floor(Math.random()*(HEIGH- Fish.HEIGHT));
        return new GuppyFish(imageView,x,y,time, timeLifeGuppyFish);
    }

    //    Золотые рыбкирождаются каждые N2 секунд, при условии, что их количество менее K % от общего числа кроликов,
    //    в противном случае – не рождаются вовсе.
    private boolean canBornGoldFish(int N2, int K2, int time){
        return time % N2 == 0 && Fish.countsAllFish <= Fish.countsAllFish * K2;
    }

    private GoldFish makeGoldFish(int time){
        ImageView imageView = new ImageView(imageGoldFish);
        int x = (int)Math.floor(Math.random()*(WIDTH- Fish.WIDTH));
        int y = (int)Math.floor(Math.random()*(HEIGH- Fish.HEIGHT));
        return new GoldFish(imageView,x,y,time, timeLifeGoldFish);
    }

    public void clear(){
        Fish.countsAllFish = 0;
        GoldFish.countGoldFish = 0;
        GuppyFish.countGuppyFish = 0;
        collectionsFish.clear();
    }

    public void setConditionsBornFish(int N1, int P1, int N2, int K2){
        this.N1 = N1;
        this.P1 = P1;
        this.N2 = N2;
        this.K2 = K2;
    };
    public void setConditionsTimeLifeFish(int timeLifeGoldFish, int timeLifeGuppyFish){
        this.timeLifeGoldFish = timeLifeGoldFish;
        this.timeLifeGuppyFish = timeLifeGuppyFish;
    };

    public ImageView getImageViewBackground() {
        ImageView imageViewBackground = new ImageView(imageBackground);
        imageViewBackground.setFitWidth(WIDTH);
        imageViewBackground.setFitHeight(HEIGH);
        return imageViewBackground;
    }

    public String getInfoAliveFish(){
        return collectionsFish.getAliveFish();
    }
}
