import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GetPinCode extends JFrame implements  ActionListener {

    JButton b1, b2, b3, b4, b5, b6, b7, b8, b9, b0, del, ok;
    JTextField t1, t2, t3, t4, t5, t6;
    JFrame frame;
    PinCode pinCode;

    GetPinCode(){
        PinCode pc = new PinCode();
        pinCode = pc.deserialize(pc);
        pinCode.setTextFieldsStyle();
        frame = this;
        pinCode.setFrame(frame);
        setLayout(null);
        frame.setSize(305,438);
        frame.getContentPane().setBackground(new Color(204, 212, 226));
        SetPinCode.centerFrame(frame);

        b1 = new JButton();
        b2 = new JButton();
        b3 = new JButton();
        b4 = new JButton();
        b5 = new JButton();
        b6 = new JButton();
        b7 = new JButton();
        b8 = new JButton();
        b9 = new JButton();
        b0 = new JButton();
        del = new JButton();
        ok = new JButton();

        t1 = pinCode.getT1();
        t2 = pinCode.getT2();
        t3 = pinCode.getT3();
        t4 = pinCode.getT4();
        t5 = pinCode.getT5();
        t6 = pinCode.getT6();
        t6.setVisible(false);


        b1.setText("1");
        b2.setText("2");
        b3.setText("3");
        b4.setText("4");
        b5.setText("5");
        b6.setText("6");
        b7.setText("7");
        b8.setText("8");
        b9.setText("9");
        b0.setText("0");
        del.setText("X");
        ok.setText("OK");


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        b0.addActionListener(this);
        del.addActionListener(this);
        ok.addActionListener(this);


        buttonSetSize(b1);
        buttonSetSize(b2);
        buttonSetSize(b3);
        buttonSetSize(b4);
        buttonSetSize(b5);
        buttonSetSize(b6);
        buttonSetSize(b7);
        buttonSetSize(b8);
        buttonSetSize(b9);
        buttonSetSize(b0);
        buttonSetSize(del);
        buttonSetSize(ok);


        buttonSetStyle(b1);
        buttonSetStyle(b2);
        buttonSetStyle(b3);
        buttonSetStyle(b4);
        buttonSetStyle(b5);
        buttonSetStyle(b6);
        buttonSetStyle(b7);
        buttonSetStyle(b8);
        buttonSetStyle(b9);
        buttonSetStyle(b0);
        buttonSetStyle(ok);
        buttonSetStyle(del);

        ok.setBackground(Color.GREEN);
        del.setBackground(Color.RED);

        add(b1);
        add(b2);
        add(b3);
        add(b4);
        add(b5);
        add(b6);
        add(b7);
        add(b8);
        add(b9);
        add(b0);
        add(ok);
        add(del);
        add(t1);
        add(t2);
        add(t3);
        add(t4);
        add(t5);
        add(t6);

        int p = 0;
        if (pinCode.getNumberOfPins() == 4){
            frame.setSize(250, 438);
            t5.setVisible(false);
            p = -27;
        }
        if (pinCode.getNumberOfPins() == 6){
            frame.setSize(360, 438);
            t6.setVisible(true);
            p = 27;
        }

        int w = 50;
        int y = 140;
        b1.setBounds(68+p,y,w,w);
        b2.setBounds(123+p,y,w,w);
        b3.setBounds(178+p,y,w,w);
        b4.setBounds(68+p,y+55,w,w);
        b5.setBounds(123+p,y+55,w,w);
        b6.setBounds(178+p,y+55,w,w);
        b7.setBounds(68+p,y+110,w,w);
        b8.setBounds(123+p,y+110,w,w);
        b9.setBounds(178+p,y+110,w,w);
        del.setBounds(68+p,y+165,w,w);
        b0.setBounds(123+p,y+165,w,w);
        ok.setBounds(178+p,y+165,w,w);
        t1.setBounds(13,45,w,w);
        t2.setBounds(68,45,w,w);
        t3.setBounds(123,45,w,w);
        t4.setBounds(178,45,w,w);
        t5.setBounds(233,45,w,w);
        t6.setBounds(288,45,w,w);

        frame.setResizable(false);
        frame.setVisible(true);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                pinCode.serialize();
                frame.dispose();
                new SetPinCode();
            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == b1){
            pinCode.buttonAction('1');
        }
        if (source == b2){
            pinCode.buttonAction('2');
        }
        if (source == b3){
            pinCode.buttonAction('3');
        }
        if (source == b4){
            pinCode.buttonAction('4');
        }
        if (source == b5){
            pinCode.buttonAction('5');
        }
        if (source == b6){
            pinCode.buttonAction('6');
        }
        if (source == b7){
            pinCode.buttonAction('7');
        }
        if (source == b8){
            pinCode.buttonAction('8');
        }
        if (source == b9){
            pinCode.buttonAction('9');
        }
        if (source == b0){
            pinCode.buttonAction('0');
        }
        if (source == del){
            pinCode.delete();
        }
        if (source == ok){
            pinCode.ok();
        }
    }


    public void buttonSetSize(JButton b) { b.setSize(50,50); }
    public void buttonSetStyle(JButton b) {
        b.setBackground(new Color(10, 45, 73));
        b.setForeground(Color.WHITE);
    }
}
