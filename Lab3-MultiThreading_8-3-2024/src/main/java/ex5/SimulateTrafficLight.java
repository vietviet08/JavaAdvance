package ex5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulateTrafficLight extends JFrame implements ActionListener, Runnable {

    private  int timeStop = 4;

    private  int timeReadyStop = 1;

    private   int timeAcceptRun = 4;

    private boolean stop;
    private boolean run;


    private Label visibleTimeBottom;
    private Label visibleTimeRight;

    private Label visibleTimeLeft;
    private Label visibleTimeTop;


    public SimulateTrafficLight() {
        setTitle("Simulate Traffic Light");
        setSize(600, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());


        Container container = this.getContentPane();

        visibleTimeBottom = new Label(timeStop + "");
        visibleTimeBottom.setFont(new Font("Courier", Font.BOLD, 30));
        visibleTimeBottom.setAlignment(Label.CENTER);
        container.add(visibleTimeBottom, BorderLayout.SOUTH);

        visibleTimeTop = new Label(timeStop + "");
        visibleTimeTop.setFont(new Font("Courier", Font.BOLD, 30));
        visibleTimeTop.setAlignment(Label.CENTER);
        container.add(visibleTimeTop, BorderLayout.NORTH);


        visibleTimeRight = new Label(timeAcceptRun + "");
        visibleTimeRight.setFont(new Font("Courier", Font.BOLD, 30));
        container.add(visibleTimeRight, BorderLayout.EAST);


        visibleTimeLeft = new Label(timeAcceptRun + "");
        visibleTimeLeft.setFont(new Font("Courier", Font.BOLD, 30));
        container.add(visibleTimeLeft, BorderLayout.WEST);

        setVisible(true);
    }

    private Color setColorTime(boolean stop) {

        if (isStop())
            return Color.red;
        else return Color.green;

    }

    private Color setColorTime1(boolean run) {

        if (isRun())
            return Color.green;
        else return Color.red;

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void run() {

        while (timeStop > 0 && timeAcceptRun > 0) {
            visibleTimeBottom.setForeground(setColorTime(isStop()));
            visibleTimeRight.setForeground(setColorTime1(isRun()));

            visibleTimeTop.setForeground(setColorTime(isStop()));
            visibleTimeLeft.setForeground(setColorTime1(isRun()));


            try {
                Thread.sleep(1000);
                timeStop--;
                visibleTimeBottom.setText(timeStop + "");
                visibleTimeTop.setText(timeStop + "");
                timeAcceptRun--;
                visibleTimeRight.setText(timeAcceptRun + "");
                visibleTimeLeft.setText(timeAcceptRun + "");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }

        timeStop = 15;
        timeAcceptRun = 15;

        if (isStop()) {
            setStop(false);
            setRun(false);

        } else {
            setStop(true);
            setRun(true);
        }


        System.out.println(isRun());
        System.out.println(isStop());

        Thread thread = new Thread(this);
        thread.start();
    }


    public int getTimeStop() {
        return timeStop;
    }

    public void setTimeStop(int timeStop) {
        this.timeStop = timeStop;
    }

    public int getTimeReadyStop() {
        return timeReadyStop;
    }

    public void setTimeReadyStop(int timeReadyStop) {
        this.timeReadyStop = timeReadyStop;
    }

    public int getTimeAcceptRun() {
        return timeAcceptRun;
    }

    public void setTimeAcceptRun(int timeAcceptRun) {
        this.timeAcceptRun = timeAcceptRun;
    }

    public boolean isStop() {
        return stop;
    }

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public static void main(String[] args) {
        SimulateTrafficLight srl = new SimulateTrafficLight();
        srl.setRun(true);
        srl.setStop(true);
        Thread t1 = new Thread(srl);
        t1.start();

    }
}
