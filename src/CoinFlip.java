package ehsan;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CoinFlip extends JFrame {
    public StaticJLabels() {
        super("Flipping Coins");
        ExecutorService taskList = Executors.newFixedThreadPool(400);
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(5, 1));
        for(int i=0; i<5; i++) { // I am using 5 concurrent threads
            JLabel label = new JLabel("Label " + i);
            label.setFont(new Font("SansSerif", Font.PLAIN, 60));
            contentPane.add(label);
            taskList.execute(new CoinFlipThread(label));
        }
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void main(String[] args) {
        new StaticJLabels();
    }
    public class CoinFlipThread implements Runnable {

        private final JLabel mylabel;

        public CoinFlipThread(JLabel colabel) {
            this.mylabel = colabel;
        }

        @Override
        public void run() {
            String msg;
            Integer heads_count = 0;
            for(int i = 0;i < 1000;i++) {
                double hold = Math.random();
                if(hold>0.5) {
                    ++heads_count;
                    msg = String.format("The number of Heads occured : %s",heads_count );
                    mylabel.setText(msg);
                    try {
                        Thread.sleep(12);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
