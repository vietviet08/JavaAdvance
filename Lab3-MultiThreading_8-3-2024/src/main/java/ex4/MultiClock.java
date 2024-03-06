package ex4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

public class MultiClock extends JFrame implements ActionListener, Runnable {


    private JButton btnCreatClock;
    private Label clock ;

    public MultiClock() {

        setTitle("Multi Clock");
        setSize(350, 350);
        setLocationRelativeTo(null);

        Container container = this.getContentPane();
        container.setLayout(new BorderLayout());

        btnCreatClock = new JButton("Create clock");
        container.add(btnCreatClock, BorderLayout.NORTH);
        btnCreatClock.addActionListener(this);

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        clock = new Label(simpleDateFormat.format(calendar.getTime()));
        clock.setFont(new Font("Courier" , Font.BOLD, 30));
        clock.setForeground(randomColor());
        container.add(clock, BorderLayout.CENTER);

        Thread thread = new Thread(this);
        thread.start();

        setVisible(true);

    }

    private Color randomColor(){
        Random random = new Random() ;
        return new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            Thread thread = new Thread(new MultiClock());
            thread.start();
    }

    @Override
    public void run() {
        while (true){
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
            clock.setText(simpleDateFormat.format(calendar.getTime()));
        }
    }


    public static void main(String[] args) {
        new MultiClock();
    }
}
