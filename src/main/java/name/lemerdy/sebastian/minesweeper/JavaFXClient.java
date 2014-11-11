package name.lemerdy.sebastian.minesweeper;

import javafx.application.Application;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class JavaFXClient extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        int width = 5;
        int height = 5;
        int dimension = 30;

        BorderPane root = new BorderPane();
        GridPane tst = new GridPane();

        Grid grid = new Grid(width, height).mine(0, 0).mine(1, 0).mine(2, 2);

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                final int xCopy = x;
                final int yCopy = y;
                ToggleButton button = new ToggleButton();
                button.setPrefWidth(dimension);
                button.setPrefHeight(dimension);
                final StringProperty btnText = button.textProperty();
                button.setOnAction(actionEvent -> {
                    ToggleButton source = (ToggleButton) actionEvent.getSource();
                    if (source.isSelected()) {
                        grid.discover(xCopy, yCopy);
                    }
                });
                grid.setDiscoveredListener(x, y, cell -> {
                    btnText.set(cell.toString());
                    button.setDisable(true);
                });
                tst.add(button, x, y);
            }
        }
        root.setCenter(tst);

        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setWidth(dimension * width);
        primaryStage.setHeight(dimension * height);
        primaryStage.show();
    }
}
