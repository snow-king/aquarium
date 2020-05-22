package sample.Classes.Fish;

import javafx.scene.layout.Pane;
import javafx.scene.image.ImageView;

/*
*
* Объект – рыбки. Бывают 2 видов: гуппи и золотая.
*
* Гуппи рождвются каждые N1 секунд с вероятностью P1.
*
* Золотые рождаются каждые N2 секунд, при условии, что их количество менее K% от общего числа рыбок,
* в противном случае – не рождаются вовсе.
*/


// Класс наследуется от Pane, и имеет интерфейс поведения IBehavior
public abstract class Fish extends Pane implements IBehaviour{
    String typeFish;

    private ImageView imageView;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    static public int countsAllFish = 0;
    private int identifier = 0;
    private int timeBorn = 0;
    int timeLife = 0;
    boolean isDead = false;

    Fish(ImageView imageView, int timeBorn, int timeLife){
        this.imageView = imageView;
        this.timeBorn = timeBorn;
        this.timeLife = timeLife;
        imageView.setFitWidth(WIDTH);
        imageView.setFitHeight(HEIGHT);
        generateIdentifer();
        countsAllFish++;
    }

    private void generateIdentifer(){
        identifier = (int)Math.floor(Math.random()*10000);
    }

    @Override
    public void move(int x, int y) {

    }

    @Override
    public void setX(int x) {
        imageView.setTranslateX(x);
    }

    @Override
    public void setY(int y) {
        imageView.setTranslateY(y);
    }

    @Override
    public double getX() {
        return imageView.getX();
    }

    @Override
    public double getY() {
        return imageView.getY();
    }

    void setPosition(int x, int y){
        this.setX(x);
        this.setY(y);
    }

    @Override
    public void updaTimeLiveFish(){
    }

    public ImageView getImageView(){
        return imageView;
    }

    public int getIdentifier() {
        return identifier;
    }

    public int getTimeBorn() {
        return timeBorn;
    }

    public String getTypeFish() {
        return typeFish;
    }

    public void setTImeLife(int timeLife){
        this.timeLife = timeLife;
    }
    public int getTimeLife() {
        return timeLife;
    }

    public boolean isDead() {
        return isDead;
    }

    void dicreementcountsAllFish(){
        countsAllFish--;
    }
}
