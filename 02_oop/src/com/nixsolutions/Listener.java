import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Lexx on 21.11.2015.
 * Class for listen keyboard actions.
 */
public class Listener {
    /**
     * Listen keyboard actions.
     * can react on press keys 'WASD'
     * actions:
     * press w - all (or selected) a figure(s) move up;
     * press a - all (or selected) a figure(s) move left;
     * press s - all (or selected) a figure(s) move down;
     * press d - all (or selected) a figure(s) move right;
     * Also can select a figure by number of his name. (example: square_5 - number is 5)
     * Or press 0 if you want to select all
     * Note: known issue: a figure with number 0 is not select
     *
     * @param obj    - current figure
     * @param frame  - current frame
     * @param isStop - listen or not
     */
    public void listen(final Figures obj, JFrame frame, boolean isStop) {
        if (isStop) {
            return;
        }
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                for (int i = 0; i < 10; i++) {
                    try {
                        if (Integer.parseInt(String.valueOf(e.getKeyChar())) == i) Main.turnOff(i);
                        if (Integer.parseInt(String.valueOf(e.getKeyChar())) == 0) Main.turnOnAll();
                    } catch (NumberFormatException ex) {
                    }
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                long start = System.currentTimeMillis();
                switch (e.getKeyChar()) {
                    case 'w':
                        obj.shift("up", 10, start);
                        break;
                    case 's':
                        obj.shift("down", 10, start);
                        break;
                    case 'a':
                        obj.shift("left", 10, start);
                        break;
                    case 'd':
                        obj.shift("right", 10, start);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

    }

}
