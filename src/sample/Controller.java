package sample;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import sample.Classes.Windows.WindowError;

import java.net.URL;
import java.util.ResourceBundle;


public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private AnchorPane mainStage;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private TextField fieldTime;

    @FXML
    private TextField timeBornGuppyFish;

    @FXML
    private TextField timeBornGoldFish;

    @FXML
    private Pane mainPane;

    @FXML
    private Button pauseButton;

    @FXML
    private Slider sliderVariationBornGoldFish;

    @FXML
    private Slider sliderVariationBornGuppyFish;

    @FXML
    private CheckBox checkBoxShowDialog;

    @FXML
    private CheckBox checkBoxShowTime;

    @FXML
    private Label labelTextTIMER;

    @FXML
    private TextField timeLifeGuppyFish;

    @FXML
    private TextField timeLifeGoldFish;

    @FXML
    private Button showInformationButton;

    private Boolean showLog = true;

    @FXML
    void initialize(AppManager appManager) {
        try {
            initSliders();
            initCheckBoxes();
            initListeners(appManager);
            showLog = checkBoxShowTime.isSelected();
        }
        catch (Exception e ){
            WindowError windowError = new WindowError(e.toString());
        }
    }

    /**
     *  метод обработки кнопок
     * @param appManager экзземпляр приложения
     */
    private void initListeners(AppManager appManager){
        startButton.setOnAction(event ->
        {
            if (checkTextBooxsInputValue())
            {
                try {
                    fieldTime.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        stopButton.setOnAction(event ->
        {
            try {
                appManager.appStop();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        pauseButton.setOnAction(event ->
        {
            try {
                appManager.appPause();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        showInformationButton.setOnAction(event -> appManager.showWindowCollectionsInformatos());

        mainStage.setOnKeyPressed(event -> {
            try {
                writeKeyCode(event.getCode(),appManager);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        checkBoxShowTime.selectedProperty().addListener((ov, old_val, new_val) -> showTimer());

    }

    private void initCheckBoxes(){
        checkBoxShowTime.setSelected(true);
        checkBoxShowDialog.setSelected(true);
    }

    private void initSliders(){
        sliderVariationBornGoldFish.setMin(0);
        sliderVariationBornGoldFish.setMax(100);
        sliderVariationBornGoldFish.setBlockIncrement(10);
        sliderVariationBornGoldFish.setMajorTickUnit(10);
        sliderVariationBornGoldFish.setShowTickLabels(true);
        sliderVariationBornGoldFish.setShowTickMarks(true);
        sliderVariationBornGoldFish.setValue(50);

        sliderVariationBornGuppyFish.setMin(0);
        sliderVariationBornGuppyFish.setMax(100);
        sliderVariationBornGuppyFish.setBlockIncrement(10);
        sliderVariationBornGuppyFish.setMajorTickUnit(10);
        sliderVariationBornGuppyFish.setShowTickLabels(true);
        sliderVariationBornGuppyFish.setShowTickMarks(true);
        sliderVariationBornGuppyFish.setValue(50);
    }

    private boolean isIntegerTextField(TextField textField){
        try{
            Integer.parseInt(textField.getText());
            return true;
        }
        catch (NumberFormatException e){
            showDialogError(textField);
            return false;
        }
    }

    /**
     *  генерация окна ошибки
     * @param textField - ТЕКСТОВОЕ ПОЛЕ
     */
    private void showDialogError(TextField textField) {
        String erroeMessage;
        if(textField.getId().equals(timeBornGuppyFish.getId())){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения GuppyFish\" \n"
                            + "должно быть не пустим и целочисленным";
            WindowError windowError = new WindowError(erroeMessage);
            textField.requestFocus();
        }
        if(textField.getId().equals(timeBornGoldFish.getId())){
            erroeMessage =
                    "Значание в поле: \n"
                            + "\"Время рождения GoldFish\" \n"
                            + "должно быть не пустим и целочисленным";
            textField.requestFocus();
            WindowError windowError = new WindowError(erroeMessage);
        }
    }

    /**
     *  делаем
     * @param key- клавиша
     * @param appManager экземпляр приложения
     */
    private void writeKeyCode(KeyCode key, AppManager appManager) {

        if(key == KeyCode.T) {
            showTimer();
        }
        if (key == KeyCode.E){
            if (checkTextBooxsInputValue())
            {
                try {
                    fieldTime.setText("");
                    appManager.appStart();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (key == KeyCode.B){
            appManager.appStop();
        }
    }

    /**
     * метод таймера
     */
    private void showTimer(){
        if(!showLog)
        {
            showLog = true;
            fieldTime.setVisible(true);
            labelTextTIMER.setVisible(true);
        }
        else
        {
            showLog = false;
            fieldTime.setVisible(false);
            labelTextTIMER.setVisible(false);
        }
    }

    private Boolean checkTextBooxsInputValue(){
        return isIntegerTextField(timeBornGuppyFish) &&
                isIntegerTextField(timeBornGoldFish) &&
                isIntegerTextField(timeLifeGoldFish) &&
                isIntegerTextField(timeLifeGuppyFish);
    }

    TextField getFieldTime() {
        return fieldTime;
    }
    Button getStartButton(){return startButton;}
    Button getStopButton(){return stopButton;}
    Button getPauseButton(){return pauseButton;}
    Pane getMainPane() {
        return mainPane;
    }

    int getValueSliderVariationBornRabbitAlbinos() {
        return (int) sliderVariationBornGoldFish.getValue();
    }

    int getValueSliderVariationBornRabbitOdinary() {
        return (int) sliderVariationBornGuppyFish.getValue();
    }

    int getValueTimeBornRabbitOdinaty() {
        return Integer.parseInt(timeBornGuppyFish.getText());
    }

    int getValueTimeBornRabbitAlbinos() {
        return Integer.parseInt(timeBornGoldFish.getText());
    }

    int getValuetTimeLifeRabbitOdinaty() {
        return Integer.parseInt(timeLifeGuppyFish.getText());
    }

    int getValueTimeLifeRabbitAlbinos() {
        return Integer.parseInt(timeLifeGoldFish.getText());
    }

    Boolean getValueCheckBoxShowDialog() {
        return checkBoxShowDialog.isSelected();
    }

    public Boolean getValueCheckBoxShowTime() {
        return checkBoxShowTime.isSelected();
    }

    AnchorPane getMainStage() {
        return mainStage;
    }
}