package paint;

import java.applet.Applet;
import java.awt.Button;
import java.awt.Checkbox;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Label;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import paint.Models.Eraser;
import paint.Models.Line;
import paint.Models.Oval;
import paint.Models.Pencil;
import paint.Models.Rectangle;
import paint.Models.Shape;

public class Paint extends Applet {

    private int x1, x2, y1, y2;

    private ArrayList<Shape> shapes;
    private ArrayList<Shape> deletedshapes;
    private ArrayList<Shape> erasers;
    private ArrayList<Shape> pencils;

    private Shape currentShape;
    private String selectedShape;

    private Label funcLabel, paintLabel, colorLabel, BackgroundLabel;

    private Button clearButton, undoButton, redoButton, moveButton;
    private Button lineButton, ovalButton, rectangleButton, eraserButton, PencilButton;
    private Button redButton, greenButton, blueButton, blackButton, yellowButton, cyanButton, whiteButton;
    private Checkbox solidCheckbox, strokeCheckbox,dottedCheckbox, darkCheckbox;
    private Image eraserImage , pencilImage;
    private Cursor eraserCursor , pencilCursor;
    private Color color;

    private Shape selectedShapeToMove = null;
    private int moveX, moveY;

    @Override
    public void init() {
        setSize(Consts.WIDTH, Consts.HIGHT);
        color = Color.BLACK;
        selectedShape = "";

        Toolkit toolkit = Toolkit.getDefaultToolkit();
        eraserImage = toolkit.createImage(getClass().getResource("/paint/Images/eraser2.png"));
        eraserCursor = toolkit.createCustomCursor(eraserImage, new Point(0, 0), "eraser Cursor");
        
        pencilImage = toolkit.createImage(getClass().getResource("/paint/Images/pencil.png"));
        pencilCursor = toolkit.createCustomCursor(pencilImage, new Point(0, 0), "pencil Cursor");

        shapes = new ArrayList();
        deletedshapes = new ArrayList();
        erasers = new ArrayList<>();
        pencils = new ArrayList<>();

        drawAllViwes();
        ListenForEvents();
        
        MouseAdapter adapter;
        adapter = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedShapeToMove = null;
                x1 = e.getX();
                y1 = e.getY();
                switch (selectedShape) {
                    case Consts.LINE:
                        currentShape = new Line(x1, y1, x1, y1, color,dottedCheckbox.getState());
                        break;
                    case Consts.RECTANGLE:
                        currentShape = new Rectangle(x1, y1, x1, y1, color, solidCheckbox.getState(), strokeCheckbox.getState(),dottedCheckbox.getState());
                        break;
                    case Consts.OVAL:
                        currentShape = new Oval(x1, y1, x1, y1, color, solidCheckbox.getState(), strokeCheckbox.getState(),dottedCheckbox.getState());
                        break;
                    case Consts.MOVE:
                        for (Shape shape : shapes) {
                            if (shape.contains(x1, y1)) {
                                selectedShapeToMove = shape;
                                moveX = x1 - shape.getStartX();
                                moveY = y1 - shape.getStartY();
                                break;
                            }
                        }
                        break;
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (currentShape != null) {
                    deletedshapes.clear();
                    x2 = e.getX();
                    y2 = e.getY();
                    currentShape.setEndX(x2);
                    currentShape.setEndY(y2);
                    repaint();
                } else if (selectedShapeToMove != null) {
                    int newX = e.getX() - moveX;
                    int newY = e.getY() - moveY;
                    selectedShapeToMove.setPosition(newX, newY);
                    repaint();
                } else if (selectedShape.equals(Consts.ERASER)) {
                    x2 = e.getX();
                    y2 = e.getY();
                    Eraser eraser = new Eraser(x2, y2, Consts.ERASER_SIZE, getBackground());
                    erasers.add(eraser);
                    repaint();
                } else if (selectedShape.equals(Consts.PENCIL)) {
                    x2 = e.getX();
                    y2 = e.getY();
                    Pencil pencil = new Pencil(x2, y2, Consts.PENCIL_SIZE, color);
                    pencils.add(pencil);
                    repaint();
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                selectedShapeToMove = null;
                if (currentShape != null) {
                    shapes.add(currentShape);
                    currentShape = null;
                }
                repaint();
            }
        };

        addMouseListener(adapter);
        addMouseMotionListener(adapter);
        
    }
    @Override
    public void paint(Graphics graphics) {
        if (currentShape != null) {
            currentShape.draw(graphics); // Draw the shape being dragged
        }
        for (Shape shape : shapes) {
            shape.draw(graphics);
        } 
        for (Shape pencil : pencils) {
            pencil.draw(graphics);
        }
        for (Shape eraser : erasers) {
            eraser.draw(graphics);
        }
    }
    private void drawAllViwes(){
        funcLabel = new Label("Functions : ");
        paintLabel = new Label("Paint Mode : ");
        colorLabel = new Label("Colors : ");
        BackgroundLabel = new Label("Background Mood : ");
        
        funcLabel.setFont(Consts.LABEL_FONT);
        paintLabel.setFont(Consts.LABEL_FONT);
        colorLabel.setFont(Consts.LABEL_FONT);
        BackgroundLabel.setFont(Consts.LABEL_FONT);

        clearButton = new Button("Clear");
        undoButton = new Button("Undo");
        redoButton = new Button("Redo");
        moveButton = new Button("Move");
        
        clearButton.setFont(Consts.BUTTON_FONT);
        undoButton.setFont(Consts.BUTTON_FONT);
        redoButton.setFont(Consts.BUTTON_FONT);
        moveButton.setFont(Consts.BUTTON_FONT);

        lineButton = new Button(Consts.LINE);
        ovalButton = new Button(Consts.OVAL);
        rectangleButton = new Button(Consts.RECTANGLE);
        eraserButton = new Button(Consts.ERASER);
        PencilButton = new Button(Consts.PENCIL);
        solidCheckbox = new Checkbox("Solid");
        strokeCheckbox = new Checkbox("Stroke");
        dottedCheckbox = new Checkbox("Dotted");
        darkCheckbox = new Checkbox("Dark Mood");
        
        lineButton.setFont(Consts.BUTTON_FONT);
        ovalButton.setFont(Consts.BUTTON_FONT);
        rectangleButton.setFont(Consts.BUTTON_FONT);
        eraserButton.setFont(Consts.BUTTON_FONT);
        PencilButton.setFont(Consts.BUTTON_FONT);
        solidCheckbox.setFont(Consts.BUTTON_FONT);
        strokeCheckbox.setFont(Consts.BUTTON_FONT);
        dottedCheckbox.setFont(Consts.BUTTON_FONT);
        darkCheckbox.setFont(Consts.BUTTON_FONT);

        redButton = new Button(Consts.RED_COLOR);
        greenButton = new Button(Consts.GREEN_COLOR);
        blueButton = new Button(Consts.BLUE_COLOR);
        blackButton = new Button(Consts.BLACK_COLOR);
        yellowButton = new Button(Consts.YELLOW_COLOR);
        cyanButton = new Button(Consts.CYAN_COLOR);
        whiteButton = new Button(Consts.WHITE_COLOR);
        
        redButton.setFont(Consts.BUTTON_FONT);
        greenButton.setFont(Consts.BUTTON_FONT);
        blueButton.setFont(Consts.BUTTON_FONT);
        blackButton.setFont(Consts.BUTTON_FONT);
        yellowButton.setFont(Consts.BUTTON_FONT);
        cyanButton.setFont(Consts.BUTTON_FONT);
        whiteButton.setFont(Consts.BUTTON_FONT);

        redButton.setBackground(Color.RED);
        greenButton.setBackground(Color.GREEN);
        blueButton.setBackground(Color.BLUE);
        blackButton.setBackground(Color.BLACK);
        yellowButton.setBackground(Color.YELLOW);
        cyanButton.setBackground(Color.CYAN);
        whiteButton.setBackground(Color.WHITE);

        redButton.setForeground(Color.WHITE);
        blueButton.setForeground(Color.WHITE);
        blackButton.setForeground(Color.WHITE);
                
        add(funcLabel);
        add(clearButton);
        add(undoButton);
        add(redoButton);
        add(moveButton);
        
        add(paintLabel);
        add(lineButton);
        add(ovalButton);
        add(rectangleButton);
        add(PencilButton);
        add(eraserButton);
        add(solidCheckbox);
        add(dottedCheckbox);
        add(strokeCheckbox);

        add(colorLabel);
        add(blackButton);
        add(whiteButton);
        add(redButton);
        add(yellowButton);
        add(greenButton);
        add(blueButton);
        add(cyanButton);

        add(BackgroundLabel);
        add(darkCheckbox);
    }
    private void ListenForEvents(){
        darkCheckbox.addItemListener((ItemEvent e) -> {
            if (darkCheckbox.getState()) {
                switchToDarkMood();
                
            } else {
                switchToLightMood();
            }
        });
        clearButton.addActionListener((ActionEvent ae) -> {
            shapes.clear();
            deletedshapes.clear();
            erasers.clear();
            pencils.clear();
            repaint();
        });
        undoButton.addActionListener((ActionEvent ae) -> {
            undo();
        });
                
        redoButton.addActionListener((ActionEvent ae) -> {redo();});

        lineButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                selectedShape = Consts.LINE;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        ovalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                selectedShape = Consts.OVAL;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        });
        rectangleButton.addActionListener( new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                selectedShape = Consts.RECTANGLE;
                setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));
            }
        } );
        eraserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae
            ) {
                selectedShape = Consts.ERASER;
                setCursor(eraserCursor);
                //setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        PencilButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                selectedShape = Consts.PENCIL;
                setCursor(pencilCursor);
                //setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        moveButton.addActionListener((ActionEvent ae) -> {
            selectedShape = Consts.MOVE;
            setCursor(new Cursor(Cursor.MOVE_CURSOR));
        });

        redButton.addActionListener((ActionEvent ae) -> {
            color = Color.RED;
        });
        blueButton.addActionListener((ActionEvent ae) -> {
            color = Color.BLUE;
        });
        greenButton.addActionListener((ActionEvent ae) -> {
            color = Color.GREEN;
        });
        blackButton.addActionListener((ActionEvent ae) -> {
            color = Color.BLACK;
        });
        whiteButton.addActionListener((ActionEvent ae) -> {
            color = Color.WHITE;
        });
        yellowButton.addActionListener((ActionEvent ae) -> {
            color = Color.YELLOW;
        });
        cyanButton.addActionListener((ActionEvent ae) -> {
            color = Color.CYAN;
        });
    }
    private void undo(){
        switch (selectedShape) {
                case Consts.ERASER:
                {
                    int s = erasers.size();
                    if (s > 0) {
                        deletedshapes.add(erasers.remove(s - 1));
                        repaint();
                    }       break;
                }
                case Consts.PENCIL:
                {
                    int s = pencils.size();
                    if (s > 0) {
                        deletedshapes.add(pencils.remove(s - 1));
                        repaint();
                    }       break;
                }
                default:
                {
                    int s = shapes.size();
                    if (s > 0) {
                        deletedshapes.add(shapes.remove(s - 1));
                        repaint();
                    }       break;
                }
            }
    }
    private void redo(){
        switch (selectedShape) {
            case Consts.ERASER:
                {
                    int s = deletedshapes.size();
                    if (s > 0) {
                        erasers.add(deletedshapes.remove(s - 1));
                        repaint();
                    }   break;
                }
            case Consts.PENCIL:
                {
                    int s = deletedshapes.size();
                    if (s > 0) {
                        pencils.add(deletedshapes.remove(s - 1));
                        repaint();
                    }   break;
                }
            default:
                {
                    int s = deletedshapes.size();
                    if (s > 0) {
                        shapes.add(deletedshapes.remove(s - 1));
                        repaint();
                    }   break;
                }
        }
    }
    private void switchToDarkMood() {
        setBackground(Consts.DARK_COLOR);
        funcLabel.setBackground(Consts.DARK_COLOR);
        colorLabel.setBackground(Consts.DARK_COLOR);
        paintLabel.setBackground(Consts.DARK_COLOR);
        BackgroundLabel.setBackground(Consts.DARK_COLOR);
        solidCheckbox.setBackground(Consts.DARK_COLOR);
        strokeCheckbox.setBackground(Consts.DARK_COLOR);
        dottedCheckbox.setBackground(Consts.DARK_COLOR);
        darkCheckbox.setBackground(Consts.DARK_COLOR);

        funcLabel.setForeground(Consts.LIGHT_COLOR);
        colorLabel.setForeground(Consts.LIGHT_COLOR);
        paintLabel.setForeground(Consts.LIGHT_COLOR);
        BackgroundLabel.setForeground(Consts.LIGHT_COLOR);
        solidCheckbox.setForeground(Consts.LIGHT_COLOR);
        strokeCheckbox.setForeground(Consts.LIGHT_COLOR);
        dottedCheckbox.setForeground(Consts.LIGHT_COLOR);
        darkCheckbox.setForeground(Consts.LIGHT_COLOR);

        for (Shape eraser : erasers) {
            eraser.setColor(Consts.DARK_COLOR);
        }
    }
    private void switchToLightMood() {
        setBackground(Consts.LIGHT_COLOR);
        funcLabel.setBackground(Consts.LIGHT_COLOR);
        colorLabel.setBackground(Consts.LIGHT_COLOR);
        paintLabel.setBackground(Consts.LIGHT_COLOR);
        BackgroundLabel.setBackground(Consts.LIGHT_COLOR);
        solidCheckbox.setBackground(Consts.LIGHT_COLOR);
        strokeCheckbox.setBackground(Consts.LIGHT_COLOR);
        dottedCheckbox.setBackground(Consts.LIGHT_COLOR);
        darkCheckbox.setBackground(Consts.LIGHT_COLOR);

        funcLabel.setForeground(Color.BLACK);
        colorLabel.setForeground(Color.BLACK);
        paintLabel.setForeground(Color.BLACK);
        BackgroundLabel.setForeground(Color.BLACK);
        solidCheckbox.setForeground(Color.BLACK);
        strokeCheckbox.setForeground(Color.BLACK);
        dottedCheckbox.setForeground(Color.BLACK);
        darkCheckbox.setForeground(Color.BLACK);

        for (Shape eraser : erasers) {
            eraser.setColor(Consts.LIGHT_COLOR);
        }
    }
    
    
    
}
