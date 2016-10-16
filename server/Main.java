import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {

    enum State {
        OFFLINE(-1),
        DISTRACTED(0),
        FOCUSED(1);

        private final int value;

        State(final int newValue) {
            value = newValue;
        }

        public int getValue() { return value; }
    }
    private static Map<Integer, State> states = new HashMap<>();
    private static final int IMG_LENGTH = 150;
    private static final int ROWS = 3;
    private static final int COLUMNS = 3;
    private static final int SIZE = ROWS * COLUMNS ;
    private static final String DISTRACTED_IMG = "redcomputer.png";
    private static final String FOCUSED_IMG = "computer.png";
    private static final String OFFLINE_IMG = "blackcomputer.png";

    public static void main(String[] args) throws AWTException, IOException, InterruptedException {
        states.put(-1, State.OFFLINE);
        states.put(0, State.DISTRACTED);
        states.put(1, State.FOCUSED);

        HttpResponseGetter httpResponseGetter = new HttpResponseGetter();
        ImageIcon [] images = new ImageIcon[SIZE];
        JPanel jPanel = new JPanel(new GridLayout(ROWS, COLUMNS));
        for(int i = 0; i < 9; i++) {
            JLabel label = new JLabel();
            ImageIcon imageIcon = new ImageIcon(new ImageIcon(FOCUSED_IMG).getImage().getScaledInstance(IMG_LENGTH, IMG_LENGTH, Image.SCALE_DEFAULT));
            label.setIcon(imageIcon);
            images[i] = imageIcon;

            jPanel.add(label);
        }

        jPanel.setVisible(true);

        JFrame frame = new JFrame();
        frame.setContentPane(jPanel);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);

        while(true) {
            Thread.sleep(1000);
            int state = httpResponseGetter.getResponse();
            monitor(frame, images, states.get(state));
        }
    }

    private static void monitor(JFrame frame, ImageIcon[] images, State state) {
        int i = 5; // hardcoded Anton's computer
        if(state == State.DISTRACTED) {
            images[i].setImage(new ImageIcon(DISTRACTED_IMG).getImage().getScaledInstance(IMG_LENGTH, IMG_LENGTH, Image.SCALE_DEFAULT));
        }
        else if( state == State.OFFLINE) {
            images[i].setImage(new ImageIcon(OFFLINE_IMG).getImage().getScaledInstance(IMG_LENGTH, IMG_LENGTH, Image.SCALE_DEFAULT));
        }
        else {
            images[i].setImage(new ImageIcon(FOCUSED_IMG).getImage().getScaledInstance(IMG_LENGTH, IMG_LENGTH, Image.SCALE_DEFAULT));
        }
        frame.getContentPane().getComponent(i).repaint();
    }
}