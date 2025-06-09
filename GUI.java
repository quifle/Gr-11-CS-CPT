//Name: Timothy Yang, Neil Wan
//Date: January 10, 2025
//Title: Snake CPT
//Verified By: Neil Wan, Timothy Yang
//Importing resources
import java.awt.event.*;
import javax.swing.*;
import java.lang.Math;
import java.util.ArrayList;

//creating the class for the gui
public class GUI extends JFrame implements KeyListener {
    //creating the variables
    public JLabel label;
    public JLabel playAgainer;
    public boolean up = false;
    public boolean left = false;
    public boolean right = false;
    public boolean down = false;
    public boolean clear = false;
    public int lastDirection = 0;
    public boolean replay = false;
    public ArrayList<JLabel> body = new ArrayList<>();
    public int appleCnt = 0;
    boolean run = true;
    int moveSpeed = 10;
    //creating the timer
    Timer moveTimer;
    //setting the images to image icon variables
    public ImageIcon play = new ImageIcon("src/playAgain.png");
    public ImageIcon snakeright = new ImageIcon("src/snakehead.png");
    public ImageIcon snakeup = new ImageIcon("src/snakeheadup.png");
    public ImageIcon snakedown = new ImageIcon("src/snakeheaddown.png");
    public ImageIcon snakeleft = new ImageIcon("src/snakeheadright.png");
    private ImageIcon Left = new ImageIcon("src/bodyHor.png");
    private ImageIcon Right = new ImageIcon("src/bodyHor.png");
    private ImageIcon Up = new ImageIcon("src/bodyVert.png");
    private ImageIcon Down = new ImageIcon("src/bodyVert.png");
    JLabel apple; // creating a label then setting our icon to it, setting where our apple is then adding it
    public boolean a = true;


    GUI() {
        //setting up the actual GUI
            //when you press x it closes
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            //sets the size of application
            this.setSize(862, 887);
            //sets the title
            this.setTitle("Score: 0");
            //doesn't let you change the size
            this.setResizable(false);
            //sets the location of the screen
            this.setLocation(465, 100);
            //sets the layout to null of the JFrame
            this.setLayout(null);
            this.addKeyListener(this);
            JLabel background = new JLabel(new ImageIcon("src/bigGrid.png"));
            this.setContentPane(background);
            //creating a new JLabel, which includes the play again message. Setting the size, as well as Icon into it, as well as setting the visibility to false, unless certain conditions are met.
            playAgainer = new JLabel();
            playAgainer.setBounds(140, 350, 600, 200);
            playAgainer.setIcon(play);
            playAgainer.setVisible(false);
            //creating the snakehead as a label named label because neil is bad at naming variables
            label = new JLabel();
            //setting the size, as well as the starting icon
            label.setBounds(250, 400, 50, 50);
            label.setIcon(snakeright);
            //adding both
            add(label);
            add(playAgainer);
            //creating a timer for the movement of the snake, so that we can see it update, simulating movement
            moveTimer = new Timer(30, new ActionListener() {
                @Override
                //after time above if action is performed, movesnake method is called
                public void actionPerformed(ActionEvent e) {
                    moveSnake();
                }
            });
            //creating the apple variable, setting an image icon for it, setting the size and bounds, adding it, setting the visibility to true.
            apple = new JLabel(new ImageIcon("src/apple.png"));
            apple.setBounds(547, 400, 50, 50);
            this.add(apple);
            setVisible(true);
            this.add(new JLabel());

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
    }
//key pressed method
    public void keyPressed(KeyEvent e) {
        //if run variable is true, do the following: turn off the visibility of the restart screen, start the move timer, set the directional movement all to false as a base. Switch statement is created
        //Each case is based off of the number assigned to the keys, if lastdirection, determined by what key is pressed, is equal to the key pressed, change the snake skin to face the direction, and set the direction true.
        if (run) {
            playAgainer.setVisible(false);
            moveTimer.start();
            up = false;
            down = false;
            left = false;
            right = false;
            switch (e.getKeyCode()) {
                case 37:
                    if (lastDirection == 3) {
                        right = true;
                        label.setIcon(snakeright);
                        break;
                    }
                    left = true;
                    break;

                case 38:
                    if (lastDirection == 4) {
                        down = true;
                        label.setIcon(snakedown);
                        break;
                    }
                    up = true;
                    break;

                case 39:
                    if (lastDirection == 1) {
                        left = true;
                        label.setIcon(snakeleft);
                        break;
                    }
                    right = true;
                    break;
                case 40:
                    if (lastDirection == 2) {
                        up = true;
                        label.setIcon(snakeup);
                        break;
                    }
                    down = true;
                    break;
                    //default movement code for the snake, assuming last direction is equal to the value required. This is in case another key is pressed.
                default:
                    if (lastDirection == 3) {
                        right = true;
                        label.setIcon(snakeright);
                        break;
                    }
                    if (lastDirection == 2) {
                        up = true;
                        label.setIcon(snakeup);
                        break;
                    }
                    if (lastDirection == 1) {
                        left = true;
                        label.setIcon(snakeleft);
                        break;
                    }
                    if (lastDirection == 4) {
                        down = true;
                        label.setIcon(snakedown);
                        break;
                    }
            }
        }
        //if replay is true, and key code 32, or space is pressed, reset the game to the beginning by calling the reset method.
        if (replay) {
            if (e.getKeyCode() == 32) {
                reset();
            }
        }
    }
//creating the movesnake method to move the snake
    private void moveSnake() {
        //calling the boundary method so as to establish the boundaries so you die
        boundary();
        //tracking the body to the head. if apple cnt is greater than 0, (there are body parts) run the for loop based on the size of the body. Take the body coordinates, and set the icon depending on it set the location
        if (appleCnt > 0) {
            for (int i = 1; i < body.size(); i++) {
                int px = body.get(i).getX();
                int py = body.get(i).getY();
                int cx = body.get(i).getX();
                int cy = body.get(i).getY();
                if (body.get(i - 1).getIcon() == Left) {
                    if (body.get(i).getIcon() == Up) {
                        if (cy == py) body.get(i).setIcon(Left);
                        else cy -= moveSpeed;
                    }
                    if (body.get(i).getIcon() == Down) {
                        if (cy == py) body.get(i).setIcon(Left);
                        else cy += moveSpeed;
                    }
                    if (body.get(i).getIcon() == Left) cx -= moveSpeed;
                }
                if (body.get(i - 1).getIcon() == Right) {
                    if (body.get(i).getIcon() == Up) {
                        if (cy == py) body.get(i).setIcon(Right);
                        else cy -= moveSpeed;
                    }
                    if (body.get(i).getIcon() == Down) {
                        if (cy == py) body.get(i).setIcon(Right);
                        else cy += moveSpeed;
                    }
                    if (body.get(i).getIcon() == Right) cx += moveSpeed;
                }
                if (body.get(i - 1).getIcon() == Up) {
                    if (body.get(i).getIcon() == Left) {
                        if (cx == px) body.get(i).setIcon(Up);
                        else cx -= moveSpeed;
                    }
                    if (body.get(i).getIcon() == Right) {
                        if (cx == px) body.get(i).setIcon(Up);
                        else cx += moveSpeed;
                    }
                    if (body.get(i).getIcon() == Up) cy -= moveSpeed;
                }
                if (body.get(i - 1).getIcon() == Down) {
                    if (body.get(i).getIcon() == Left) {
                        if (cx == px) body.get(i).setIcon(Down);
                        else cx -= moveSpeed;
                    }
                    if (body.get(i).getIcon() == Right) {
                        if (cx == px) body.get(i).setIcon(Down);
                        else cx += moveSpeed;
                    }
                    if (body.get(i).getIcon() == Up) cy += moveSpeed;
                }
                System.out.println(cx + " " + cy);
                body.get(i).setLocation(cx,cy);
            }
            //if the body size is greater than 0, ser the location to the x and y of the head. This is specifically for the body after the head. If the label is left or right set the body to horizontal, but if its up or down itll be vertical.
            if (body.size() > 0) {
                body.get(0).setLocation(label.getX(), label.getY());
                if (label.getIcon() == snakeleft) {
                    body.get(0).setIcon(Left);
                    body.get(0).setLocation(label.getX() + 40, label.getY());
                }
                if (label.getIcon() == snakedown) {
                    body.get(0).setIcon(Down);
                    body.get(0).setLocation(label.getX(), label.getY() - 40);
                }
                if (label.getIcon() == snakeright) {
                    body.get(0).setIcon(Right);
                    body.get(0).setLocation(label.getX() - 40, label.getY());
                }
                if (label.getIcon() == snakeup) {
                    body.get(0).setIcon(Up);
                    body.get(0).setLocation(label.getX(), label.getY() + 40);
                }
            }
        }
        //useless stuff now. made so that it checked if it was possible to turn, when the movement was smooth. This meant you couldnt move between the lines. We changed movement speed to 50 now so it doesnt matter, because it makes the body spawn properly
        int x = label.getX();
        int y = label.getY();
        clear = false;
        if (left || right) {
            if (y % 50 != 0) {
                if (lastDirection == 2) y -= moveSpeed;
                if (lastDirection == 4) y += moveSpeed;
            } else clear = true;
        }
        if (up || down) {
            if (x % 50 != 0) {
                if (lastDirection == 1) x -= moveSpeed;
                if (lastDirection == 3) x += moveSpeed;
            } else clear = true;
        }
        if (clear) {
            if (left) {
                x -= moveSpeed;
                lastDirection = 1;
                label.setIcon(snakeleft);
            }
            if (right) {
                x += moveSpeed;
                lastDirection = 3;
                label.setIcon(snakeright);
            }
            if (up) {
                y -= moveSpeed;
                lastDirection = 2;
                label.setIcon(snakeup);
            }
            if (down) {
                y += moveSpeed;
                lastDirection = 4;
                label.setIcon(snakedown);
            }
        }
        //setting the location and calling the eat method
        label.setLocation(x, y);
        eat();
    }
// add body method
    public void addBody() {
        // if the last direction is left or right, add a horizontal body. if its is up or down, add a vertical body. set the size of it as well as the bounds.
        if (lastDirection == 1) body.add(new JLabel(Left));
        if (lastDirection == 2) body.add(new JLabel(Up));
        if (lastDirection == 3) body.add(new JLabel(Right));
        if (lastDirection == 4) body.add(new JLabel(Down));
        //adding it
        if (appleCnt == 1) {
            body.get(appleCnt - 1).setBounds(-50, -50, 50, 50);
        } else {
            if (lastDirection == 1) body.get(appleCnt - 1).setBounds(body.get(appleCnt-2).getX()+50, body.get(appleCnt-2).getY(), 50, 50);
            if (lastDirection == 2) body.get(appleCnt - 1).setBounds(body.get(appleCnt-2).getX(), body.get(appleCnt-2).getY()+50, 50, 50);
            if (lastDirection == 3) body.get(appleCnt - 1).setBounds(body.get(appleCnt-2).getX()-50, body.get(appleCnt-2).getY(), 50, 50);
            if (lastDirection == 4) body.get(appleCnt - 1).setBounds(body.get(appleCnt-2).getX(), body.get(appleCnt-2).getX()-50, 50, 50);
        }
        this.add(body.get(appleCnt - 1));
    }
    //setting up the boundaries of the JFrame, so that we don't just enter the void that is outside. Trigger the death method, when you reach a certain x or y value, ending the code.
    private void boundary() {
        int x = label.getX();
        int y = label.getY();
        if (label.getX() >= 809) {
            death();
            x -= 50;
        }
        if (label.getX() <= -11) {
            death();
            x += 50;
        }
        if (label.getY() >= 830) {
            death();
            y -= 50;
        }
        if (label.getY() <= -11) {
            death();
            y += 50;
        }
        //bounce off the wall a tiny bit so that you're not entirely going into it.
        label.setLocation(x, y);
        //checking for if it hits itself
        for (int i = 0; i < body.size(); i++) {
            int bx = body.get(i).getX();
            int by = body.get(i).getY();
            if (x == bx && y == by ) {
                death();
            }
        }
    }
//Death method. Turns off the timer, sets the death screen on, turns run to false, and sets replay to true.
    public void death() {
        moveTimer.stop();
        playAgainer.setVisible(true);
        run = false;
        replay = true;
    }
//the reset method. removes and sets the visibility of body parts off, so that it goes back to how it was with just the head piece there.
    public void reset() {
        for (int i = body.size() - 1; i >= 0; i--) {
            body.get(i).setVisible(false);
            body.remove(i);
        }
        //reset the score, which is visible at the title, set the location of the apple and head to their starting position, reset up down left and right, as well as run, the head direction, the last direction, and the visibility of the death screen when it is called.
        appleCnt = 0;
        label.setLocation(250, 400);
        apple.setLocation(545, 400);
        lastDirection = 0;
        up = down = left = right = replay = false;
        run = true;
        label.setIcon(snakeright);
        playAgainer.setVisible(false);
        this.setTitle("Score: 0");
    }
//create two variables, which will later be used a lot. If there is a body, get the x of the label and y of the label as well as the x of the apple and y of the apple. If the match to a degree, run the following"
    //Set the aplx and aply to random numbers, which will then be inputted as the x and y value fo the apple.
    public void eat() {
        int aplx = 0;
        int aply = 0;
        if (body.size() > 0) {
                if (label.getX() - 10 < apple.getX() && label.getX() + 60 > apple.getX() + 50 && label.getY() - 10 < apple.getY() && label.getY() + 60 > apple.getY() + 50) {
                    do {
                        aplx = (int) (Math.random() * 17);
                        aplx = aplx * 50;
                        aply = (int) (Math.random() * 17);
                        aply = aply * 50;
                    } while (appleValid(aplx, aply));
                    appleSetup(aplx, aply);
                }

                // when the body is just the head, do the same thing as above
        } else {
            do {
                aplx = (int) (Math.random() * 17);
                aplx = aplx * 50;
                aply = (int) (Math.random() * 17);
                aply = aply * 50;
            } while (appleValid(aplx, aply));
            if (label.getX() + 10 > apple.getX() && label.getX() + 40 < apple.getX() + 50 && label.getY() + 10 > apple.getY() && label.getY() + 40 < apple.getY() + 50) {
                appleSetup(aplx, aply);
            }
        }
    }
    //check if the new apple coordinates are in the old apple coordinates or in the snake
    public boolean appleValid(int aplx, int aply) {
        for (int i = body.size() - 1; i >= 0; i--) {
            if (body.get(i).getX() == aplx && body.get(i).getY() == aply) {
                return true;
            }
        }
        if (aplx == apple.getX() && aply == apple.getY()) return true;
        return false;
    }
    //reset the apple location and stuff like the bounds and visibility
    public void appleSetup(int aplx, int aply) {
        apple.setVisible(false);
        apple.setBounds(aplx - 3, aply, 50, 50);
        apple.setVisible(true);
        appleCnt++;
        addBody();
        String ab = Integer.toString(appleCnt);
        this.setTitle("Score: " + ab);
    }
}

