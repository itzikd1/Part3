package View;

import Model.MyModel;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Aviadjo on 3/9/2017.
 */
public class MazeDisplay extends Canvas {

    private int[][] maze;
    private int characterPositionRow;
    private int characterPositionColumn;
    private Position endposition;
    private int[][] solved;

    public void setMaze(int[][] maze) {
        this.maze = maze;
    }

    public void endposition(algorithms.mazeGenerators.Position end){
        endposition = end;
    }

    public void Solved(int[][] answer){
        solved = answer;
    }


    public void setCharacterPosition(int row, int column) {
        characterPositionRow = row;
        characterPositionColumn = column;
    }

    public void redraw() {
        if (maze != null) {
            double canvasHeight = getHeight();
            double canvasWidth = getWidth();
            double cellHeight = canvasHeight / maze.length;
            double cellWidth = canvasWidth / maze[0].length;

            try {
                GraphicsContext graphicsContext2D = getGraphicsContext2D();
                graphicsContext2D.clearRect(0, 0, getWidth(), getHeight()); //Clears the canvas
                Image wallImage = new Image(new FileInputStream("resources/images/11.jpg"));

                //Draw Maze
                for (int i = 0; i < maze.length; i++) {
                    for (int j = 0; j < maze[i].length; j++) {
                        if (maze[i][j] == 1) {
                            //graphicsContext2D.fillRect(i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                            graphicsContext2D.drawImage(wallImage, i * cellHeight, j * cellWidth, cellHeight, cellWidth);
                        }
                    }
                }

                //draw end point
                Image endPos = new Image(new FileInputStream("resources/images/2.png"));
                graphicsContext2D.drawImage(endPos, endposition.getColumnIndex() * cellHeight, endposition.getRowIndex()* cellWidth, cellHeight, cellWidth);

                //Draw solution
                Image SolutionImage = new Image(new FileInputStream("resources/images/2.png"));
                for (int i = 0; i < solved.length; i++) {
                    int x= solved[0][i];
                    int y = solved[1][i];
                    graphicsContext2D.drawImage(SolutionImage, x * cellHeight, y * cellWidth, cellHeight, cellWidth);
                }




                //Draw Character
                //gc.setFill(Color.RED);
                //gc.fillOval(characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
                Image characterImage = new Image(new FileInputStream("resources/images/2.png"));
                graphicsContext2D.drawImage(characterImage, characterPositionColumn * cellHeight, characterPositionRow * cellWidth, cellHeight, cellWidth);
            } catch (FileNotFoundException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setContentText(String.format("Image doesn't exist: %s", e.getMessage()));
                alert.show();
            }
        }
    }

    //region Properties
    private StringProperty ImageFileNameWall = new SimpleStringProperty();
    private StringProperty ImageFileNameCharacter = new SimpleStringProperty();

    public String getImageFileNameWall() {
        return ImageFileNameWall.get();
    }

    public void setImageFileNameWall(String imageFileNameWall) {
        this.ImageFileNameWall.set(imageFileNameWall);
    }

    public String getImageFileNameCharacter() {
        return ImageFileNameCharacter.get();
    }

    public void setImageFileNameCharacter(String imageFileNameCharacter) {
        this.ImageFileNameCharacter.set(imageFileNameCharacter);
    }
    //endregion

}
