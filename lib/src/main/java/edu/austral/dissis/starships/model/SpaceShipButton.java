package edu.austral.dissis.starships.model;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class SpaceShipButton extends Button{
    private final String FONT_PATH ="";

    public SpaceShipButton(String text, int x, int y, EventHandler<ActionEvent> eventHandler){
        setText(text);
        setButtonFont();
        setPrefWidth(190);
        setPrefHeight(49);
        setLayoutX(x);
        setLayoutY(y);
        initializeButtonListeners(eventHandler);
    }

    private void setButtonFont(){
        try{
            setFont(Font.loadFont(new FileInputStream(FONT_PATH),23));
        }catch (FileNotFoundException e){
            setFont(Font.font("Verdana",23));
        }
    }

    private void initializeButtonListeners(EventHandler<ActionEvent> eventHandler){

        setOnAction(eventHandler);

        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(new DropShadow(500, Color.BLUE));
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                setEffect(null);
            }
        });
    }



}
