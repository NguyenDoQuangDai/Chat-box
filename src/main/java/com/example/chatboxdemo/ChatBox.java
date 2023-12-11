package com.example.chatboxdemo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ChatBox extends Application {
    private VBox chatArea;
    private TextArea messageArea;
    private ScrollPane chatScrollPane;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Chat Box");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(new Image("file:src/main/resources/Icon.png"));

        Image friendAvatar = new Image("file:src/main/resources/Avatar1.jpg");
        ImageView friendAvatarView = new ImageView(friendAvatar);
        friendAvatarView.setFitWidth(50);
        friendAvatarView.setFitHeight(50);
        Label friendNameLabel = new Label("Thành Nam");
        friendNameLabel.setStyle("-fx-font-size: 14; -fx-font-weight: bold;");

        HBox friendInfoBox = new HBox(10);
        friendInfoBox.getChildren().addAll(friendAvatarView, friendNameLabel);
        friendInfoBox.setAlignment(Pos.CENTER_LEFT);

        chatArea = new VBox(10);
        chatArea.setPadding(new Insets(10, 10, 10, 10));
        chatScrollPane = new ScrollPane(chatArea);
        chatScrollPane.setFitToWidth(true);
        chatScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        chatScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        chatScrollPane.setVvalue(1.0);

        HBox messageInputBox = new HBox(10);
        messageInputBox.setPadding(new Insets(10, 10, 10, 10));

        messageArea = new TextArea();
        messageArea.setPromptText("Type your message...");
        messageArea.setPrefHeight(60);
        messageArea.setPrefWidth(285);
        messageArea.setWrapText(true);
        HBox.setHgrow(messageArea, Priority.ALWAYS);

        Button sendButton = new Button("Send");
        sendButton.setOnAction(e -> sendMessage());

        messageInputBox.getChildren().addAll(messageArea, sendButton);
        messageInputBox.setAlignment(Pos.BOTTOM_LEFT);

        Separator separator = new Separator();
        separator.setMaxWidth(Double.MAX_VALUE);

        BorderPane chatLayout = new BorderPane();
        chatLayout.setTop(friendInfoBox);
        chatLayout.setCenter(chatScrollPane);
        chatLayout.setBottom(messageInputBox);
        chatLayout.setMargin(messageInputBox, new Insets(10, 10, 10, 10));
        chatLayout.setPadding(new Insets(10));

        VBox mainLayout = new VBox(10);
        mainLayout.getChildren().addAll(chatLayout, separator);

        Scene scene = new Scene(mainLayout, 400, 600);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());

        primaryStage.setScene(scene);


        displayMessage(true, "");
        displayMessage(true, "Hi.");
        displayMessage(true, "Can I talk to you?");

        primaryStage.show();
    }

    private void sendMessage() {
        String message = messageArea.getText().trim();
        if (!message.isEmpty()) {
            displayMessage(false, message);
            messageArea.clear();
        }
    }

    private void displayMessage(boolean isFriend, String message) {
        if (!message.isEmpty()) {
            HBox messageBox = new HBox(10);
            messageBox.setPadding(new Insets(5));

            ImageView avatarView = isFriend ?
                    new ImageView(new Image("file:src/main/resources/Avatar1.jpg")) :
                    new ImageView(new Image("file:src/main/resources/Avatar2.jpg"));

            avatarView.setFitWidth(40);
            avatarView.setFitHeight(40);

            Label messageLabel = new Label(message);
            messageLabel.setWrapText(true);
            messageLabel.setFont(Font.font("System", 14));

            if (isFriend) {
                messageBox.getChildren().addAll(avatarView, messageLabel);
                messageBox.setStyle(
                        "-fx-border-color: #999999; " +
                                "-fx-border-width: 1; " +
                                "-fx-background-radius: 3 10 10 10; " +
                                "-fx-border-radius: 3 10 10 10;"
                );
            } else {
                messageBox.getChildren().addAll(messageLabel, avatarView);
                messageBox.setAlignment(Pos.CENTER_RIGHT);
                messageBox.setStyle(
                        "-fx-border-color: #999999; " +
                                "-fx-border-width: 1; " +
                                "-fx-background-radius: 10 3 10 10; " +
                                "-fx-border-radius: 10 3 10 10;"
                );
            }

            chatArea.getChildren().add(messageBox);
            chatScrollPane.layout();
            chatScrollPane.setVvalue(chatScrollPane.getVmax());
        } else {
            HBox spacerBox = new HBox(10);
            spacerBox.setPadding(new Insets(5));

            ImageView avatarView = new ImageView(new Image("file:src/main/resources/Avatar1.jpg"));
            avatarView.setFitWidth(80);
            avatarView.setFitHeight(80);

            Label nameLabel = new Label("Thành Nam\n\n\n\n");
            Label spacerLabel = new Label("\n\n");
            nameLabel.setStyle("-fx-font-size: 25; -fx-font-weight: bold;");
            nameLabel.setAlignment(Pos.TOP_CENTER);

            VBox avatarNameBox = new VBox(5);
            avatarNameBox.getChildren().addAll(spacerLabel, avatarView, nameLabel);
            avatarNameBox.setAlignment(Pos.BOTTOM_CENTER);

            spacerBox.setAlignment(Pos.CENTER);

            spacerBox.getChildren().add(avatarNameBox);
            chatArea.getChildren().add(spacerBox);
        }
    }
}


