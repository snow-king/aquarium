package sample.Classes.Fish;


/*
* Создать интерфейс IBehaviour, задающий поведение объекта
 Реализовать иерархию классов, определяющих объекты по варианту и реализующие интерфейс IBehaviour.
*
* */
public interface IBehaviour {
    void move(int x, int y);
    void setX(int x);
    void setY(int y);
    double getX();
    double getY();

    void updaTimeLiveFish();
}
