//package SimpleClock;

import javax.swing.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;


public class SimpleClock extends JFrame {

    Calendar calendar;
    SimpleDateFormat timeFormat;
    SimpleDateFormat dayFormat;
    SimpleDateFormat dateFormat;

    JLabel timeLabel;
    JLabel dayLabel;
    JLabel dateLabel;
    String time;
    String day;
    String date;

    private boolean isGMT = false;

    SimpleClock() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Digital Clock");
        this.setLayout(new FlowLayout());
        this.setSize(350, 220);
        this.setResizable(false);

        timeFormat = new SimpleDateFormat("hh:mm:ss a");
        dayFormat = new SimpleDateFormat("EEEE");
        dateFormat = new SimpleDateFormat("dd MMMMM, yyyy");
        timeLabel = new JLabel();
        timeLabel.setFont(new Font("SANS_SERIF", Font.PLAIN, 59));
        timeLabel.setBackground(Color.BLACK);
        timeLabel.setForeground(Color.WHITE);
        timeLabel.setOpaque(true);
        dayLabel = new JLabel();
        dayLabel.setFont(new Font("Ink Free", Font.BOLD, 34));

        dateLabel = new JLabel();
        dateLabel.setFont(new Font("Ink Free", Font.BOLD, 30));


        this.add(timeLabel);
        this.add(dayLabel);
        this.add(dateLabel);
        this.setVisible(true);

        JButton toggleButton = new JButton("Switch Time");
        toggleButton.setFont(new Font("Arial",Font.BOLD,20));
        toggleButton.addActionListener(e -> toggleTimeFormat());
        this.add(toggleButton);

        JButton timeZoneButton = new JButton("Time Zone");
        timeZoneButton.setFont(new Font("Arial",Font.BOLD,20));
        timeZoneButton.addActionListener(e -> toggleTimeZone());
        this.add(timeZoneButton);



        this.setVisible(true);

        setTimer();
    }

    private void toggleTimeFormat(){
        if (timeFormat.toPattern().equals("hh:mm:ss a")){

            timeFormat = new SimpleDateFormat("HH:mm:ss");
        } else {

            timeFormat = new SimpleDateFormat("hh:mm:ss a");
        }

        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }

    private void toggleTimeZone() {
        if (isGMT) {
            timeFormat.setTimeZone(TimeZone.getDefault());
        } else {
            timeFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        }
        isGMT = !isGMT;

        time = timeFormat.format(Calendar.getInstance().getTime());
        timeLabel.setText(time);
    }
    // Thread

    public void setTimer() {



           Thread timerThread = new Thread(() ->{
                while (true) {

                     time = timeFormat.format(Calendar.getInstance().getTime());
                     timeLabel.setText(time);

                     day = dayFormat.format(Calendar.getInstance().getTime());
                     dayLabel.setText(day);

                     date = dateFormat.format(Calendar.getInstance().getTime());
                    dateLabel.setText(date);

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();

                    }
                }


        });

    
       /* public void setTimer() {
            while (true) {
                time = timeFormat.format(Calendar.getInstance().getTime());
                timeLabel.setText(time);

                day = dayFormat.format(Calendar.getInstance().getTime());
                dayLabel.setText(day);

                date = dateFormat.format(Calendar.getInstance().getTime());
                dateLabel.setText(date);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }*/



        timerThread.setDaemon(true);
        timerThread.start();
    }

    public static void main(String[] args) {
        new SimpleClock();
    }
}