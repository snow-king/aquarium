package sample.Classes;

import javafx.scene.layout.Pane;
import sample.Classes.Fish.Fish;

import java.util.*;

/**
 * класс коллекций
 */
class Collections {
    private ArrayList<Fish> arrayList;            //Коллекция для хранения объектов
    private TreeSet<Integer> treeSet;               //Коллекция для хранения и поиска уникальных идентификаторов
    private HashMap<Integer,Integer> hashMap;       //Коллекция для хранения времени рождения объектов

    Collections(){
        arrayList = new ArrayList<>();
        treeSet = new TreeSet<>();
        hashMap = new HashMap<>();
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

    /**
     *
     * @param pane - панель где выводяться рыбки
     */
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

        while(checkIsAnyFishDead()){
            Fish deleFish = findDeadFish();
            delete(deleFish);
            assert deleFish != null;
            pane.getChildren().remove(deleFish.getImageView());
        }

    }

    /**
     *
     * @return проверка есть ли мёртвая рыба
     */
    private boolean checkIsAnyFishDead(){
        for (Fish element : arrayList) {
            if (element.isDead()) {
                return true;
            }
        }
        return  false;
    }

    /**
     *
     * @return ищем мёртвую рыбу
     */
    private Fish findDeadFish(){
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

    /**
     *
     * @return Получаем живых рыб
     */
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
