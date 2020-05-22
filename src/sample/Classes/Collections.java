package sample.Classes;

import javafx.scene.layout.Pane;
import sample.Classes.Fish.Fish;

import java.util.*;

class Collections {
    private ArrayList<Fish> arrayList;            //Коллекция для хранения объектов
    private TreeSet<Integer> treeSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private HashMap<Integer,Integer> hashMap;       //Коллекция для хранения времени рождения объектов

    Collections(){
        arrayList = new ArrayList<Fish>();
        treeSet = new TreeSet<Integer>();
        hashMap = new HashMap<Integer,Integer>();
    }

    void add(Fish fish){
        arrayList.add(fish);
        treeSet.add(fish.getIdentifier());
        hashMap.put(fish.getIdentifier(), fish.getTimeBorn());
    }

    private void delete(Fish fish){
        arrayList.remove(fish);
        treeSet.remove(fish.getIdentifier());
        hashMap.remove(fish.getIdentifier(), fish.getTimeBorn());
    }

    void  updateCollectionsPerTime(Pane pane){
        for (Fish fishUpdate : arrayList) {
            fishUpdate.updaTimeLiveFish();
        }

        /*
         *       У меня так и не получилось обойти ошибку ConcurrentModificationException
         *       Возникает из-за того, что в потоке нельзя УДАЛЯТЬ элемент из КОЛЛЕКЦИИ
         *       Не смог найти альтернативу, кроме как разбить эту операцию на 2 разных итератора
         *       Каждый итератор завернут в функцию
         * */

        while(checkIsAmyRabbitDead()){
            Fish deleFish = findDeadRabbit();
            delete(deleFish);
            pane.getChildren().remove(deleFish.getImageView());
        }

    }

    private boolean checkIsAmyRabbitDead(){
        Iterator<Fish> iteratorDelete = arrayList.listIterator();
        while (iteratorDelete.hasNext()) {
            Fish element = iteratorDelete.next();
            if (element.isDead())
            {
                return true;
            }
        }
        return  false;
    }

    private Fish findDeadRabbit(){
        for (Fish element : arrayList) {
            if (element.isDead()) {
                return element;
            }
        }
        return  null; // NEVER RETURN NULL!!
    }

    void clear(){
        arrayList.clear();
        treeSet.clear();
        hashMap.clear();
    }

    String getAliveFish(){
        StringBuilder resultString = new StringBuilder();
        Iterator<Fish> iteratorDelete = arrayList.listIterator();
        int count = 0;
        while (iteratorDelete.hasNext()) {
            Fish element = iteratorDelete.next();
            count++;
            if (!element.isDead())
            {
                resultString.append(count).append(". ").append("Type: ").append(element.getTypeFish()).append("; TimeBorn: ").append(element.getTimeBorn()).append("; Id: ").append(element.getIdentifier()).append("\n");
            }
        }

        return resultString.toString();
    }
}
