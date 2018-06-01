package q2;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polyline;
import javafx.stage.Stage;

/**
 * <p>This is where you put your description about what this class does. You
 * don't have to write an essay but you should describe exactly what it does.
 * Describing it will help you to understand the programming problem better.</p>
 *
 * @author Your Name goes here
 * @version 1.0
 */
public class DrawStar extends Application {

    /** The contents of the application scene. */
    private Group root;
    
    /** center point. */
    private Point2D center;
    /** circle to move to first mouse click location. */
    private Circle atCenterPoint = new Circle(0, 0, 3);
    
    
    private Circle[] atCenter = new Circle[5];
    
    private double distance;
    
   
    /**
     * Displays an initially empty scene, waiting for the user to draw lines
     * with the mouse.
     * 
     * @param primaryStage
     *            a Stage
     */
    public void start(Stage primaryStage) {
        
        for (int i = 0; i < atCenter.length; i++) {
            atCenter[i] = new Circle(0, 0, 3);
            atCenter[i].setFill(Color.CYAN);
        }
            
        root = new Group(atCenter);
        root.getChildren().add(atCenterPoint);

        atCenterPoint.setFill(Color.CYAN);
        
        final int appWidth = 1000;
        final int appHeight = 800;
        Scene scene = new Scene(root, appWidth, appHeight, Color.BLACK);
        scene.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                getAtCenterPoint().setCenterX(event.getX());
                getAtCenterPoint().setCenterY(event.getY());
                System.out.println("Mouse pressed");
                
            }
            
        });
        scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent event) {
                root.getChildren().clear();
                setCenter(new Point2D(event.getX(), event.getY()));
                double cx = getAtCenterPoint().getCenterX();
                double cy = getAtCenterPoint().getCenterY();
                double d = getCenter().distance(cx, cy);
                setDistance(d);
                double[] ptsX = new double[5];
                double[] ptsY = new double[5];
                Line[] lines = new Line[5];
                
                for (int i = 0; i < ptsX.length; i++) {
                    ptsX[i] = (d * Math.cos(Math.PI/180 * 72 * i)) + cx;
                    ptsY[i] = (d * Math.sin
                            (Math.PI/180 * 72 * i)) + cy;
                    atCenter[i].setCenterX(ptsX[i]);
                    atCenter[i].setCenterY(ptsY[i]);
                }
                
                for (int i = 0; i < lines.length; i++) {
                    if (i < 3) {
                        lines[i] = new Line();
                        lines[i].setStartX(ptsX[i]);
                        lines[i].setStartY(ptsY[i]);
                        lines[i].setEndX(ptsX[i + 2]);
                        lines[i].setEndY(ptsY[i + 2]);
                        lines[i].setStroke(Color.CYAN); 
                        root.getChildren().add(lines[i]);
                    } else {
                        lines[i] = new Line();
                        lines[i].setStartX(ptsX[i]);
                        lines[i].setStartY(ptsY[i]);
                        lines[i].setEndX(ptsX[i - 3]);
                        lines[i].setEndY(ptsY[i - 3]);
                        lines[i].setStroke(Color.CYAN); 
                        root.getChildren().add(lines[i]);
                    }
                    
                }
                
//              root.getChildren().clear();
//              double x = event.getX();
//              double y = event.getY();
//              double r     = Math.sqrt(x*x + y*y);
//              double theta = Math.atan2(y, x);
//              Circle[] dots = new Circle[5];
//                ptsX[0] = r;
//                ptsY[0] = theta;
//                System.out.println("Clear " + r + " " + theta);
//                for (int i = 1; i < ptsX.length; i++) {
//                  ptsX[i] = r;
//                  ptsY[i] = theta + ((i * 2/5) * Math.PI);
//                  System.out.println(ptsX[i] + " " + ptsY[i]);
//                }
//                for (int i = 0; i < ptsX.length; i++) {
//                    ptsX[i] = r * Math.cos(ptsY[i]);
//                    ptsY[i] = r * Math.sin(ptsY[i]);
////                    System.out.println(ptsX[i] + " " + ptsY[i]);
//                }
//                for (int i = 0; i < dots.length; i++) {
//                    dots[i] = new Circle(ptsX[i], ptsY[i], 3);
//                    dots[i].setFill(Color.CYAN);
//                    root.getChildren().add(dots[i]);
//                }
                
                
                
                
                
                event.consume();
            }
        }); 
        
        primaryStage.setTitle("Star");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    





    /**
     * ACCESSOR.
     * @return the distance
     */
    public double getDistance() {
        return distance;
    }

    /**
     * MUTATOR.
     * @param distance the distance to set
     */
    public void setDistance(double distance) {
        this.distance = distance;
    }

    /**
     * ACCESSOR.
     * @return the atCenter
     */
    public Circle getAtCenterPoint() {
        return atCenterPoint;
    }
    
    /**
     * MUTATOR.
     * @param center the center to set
     */
    public void setCenter(Point2D center) {
        this.center = center;
    }
    
    /**
     * ACCESSOR.
     * @return the center
     */
    public Point2D getCenter() {
        return center;
    }

    /**
     * Launches the JavaFX application.
     * 
     * @param args
     *            command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
    
}

