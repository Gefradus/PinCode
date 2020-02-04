import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.io.*;

public class PinCode implements Serializable {
    private static JTextField t1, t2, t3, t4, t5, t6;
    private static char c1, c2, c3, c4, c5, c6, PinFromTF1, PinFromTF2, PinFromTF3, PinFromTF4, PinFromTF5, PinFromTF6;
    private char Pin1, Pin2, Pin3, Pin4, Pin5, Pin6;
    private static JFrame frame;
    private String successMessage, invalidPinMessage, blockadeMessage;
    private int NumberOfPins, i, chance, thickness;
    private Color color;

    public PinCode() {
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();
        setTextFieldsStyle();
        delete();
    }

    public void setTextFieldStyle(JTextField tf) {
        tf.setSize(50, 50);
        tf.setText(" ");
        tf.setEditable(false);
        tf.setBorder(new LineBorder(color,thickness));
        tf.setRequestFocusEnabled(false);
        tf.setHorizontalAlignment(JTextField.CENTER);
    }


    public void ok(){
        getPins();
        boolean isPinCorrect = false;
        if (NumberOfPins == 6) {
            isPinCorrect = PinFromTF1 == Pin1 & PinFromTF2 == Pin2 & PinFromTF3 == Pin3 & PinFromTF4 == Pin4 & PinFromTF5 == Pin5 & PinFromTF6 == Pin6;
        }
        if (NumberOfPins == 5) {
            isPinCorrect = PinFromTF1 == Pin1 & PinFromTF2 == Pin2 & PinFromTF3 == Pin3 & PinFromTF4 == Pin4 & PinFromTF5 == Pin5;
        }
        if (NumberOfPins == 4){
            isPinCorrect = PinFromTF1 == Pin1 & PinFromTF2 == Pin2 & PinFromTF3 == Pin3 & PinFromTF4 == Pin4;
        }

        if (isPinCorrect) {
            if (i > 0) {
                i = chance;
                JOptionPane.showMessageDialog(null, successMessage, "", JOptionPane.PLAIN_MESSAGE);
                delete();
                serialize();
                frame.dispose();
            }
            else{
                block();
            }

        } else {
            if (i > 1) {
                i--;
                JOptionPane.showMessageDialog(null, invalidPinMessage + i, "", JOptionPane.ERROR_MESSAGE);
                delete();
            } else {
                block();
            }
        }
    }


    public void block(){
        i=0;
        JOptionPane.showMessageDialog(null, blockadeMessage, "",
                JOptionPane.ERROR_MESSAGE);
        delete();
        serialize();
        frame.dispose();
    }



    public void buttonAction(char u) {
        getPins();

        if (c1 != '*') {
            t1.setText("*");
            PinFromTF1 = u;
        } else {
            if (c2 != '*') {
                t2.setText("*");
                PinFromTF2 = u;
            } else {
                if (c3 != '*') {
                    t3.setText("*");
                    PinFromTF3 = u;
                } else {
                    if (c4 != '*') {
                        t4.setText("*");
                        PinFromTF4 = u;
                    } else {
                        if (c5 != '*') {
                            t5.setText("*");
                            PinFromTF5 = u;
                        } else {
                            if (c6 != '*') {
                                t6.setText("*");
                                PinFromTF6 = u;
                            }}}}}}
    }


    public void serialize(){
        try (FileOutputStream fout = new FileOutputStream(System.getProperty("user.dir") + "\\save\\" + "serializacja.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fout)) {
            oos.writeObject(this);
        }catch(Exception e){
            System.err.println();
        }
    }


    public PinCode deserialize(PinCode pc){
        try (FileInputStream fin = new FileInputStream(System.getProperty("user.dir") + "\\save\\" + "serializacja.txt");
             ObjectInputStream ois = new ObjectInputStream(fin)) {
            return (PinCode) ois.readObject();
        }
        catch (Exception e){
            return pc;
        }
    }



    public void delete() {
        PinFromTF1 = ' ';
        PinFromTF2 = ' ';
        PinFromTF3 = ' ';
        PinFromTF4 = ' ';
        PinFromTF5 = ' ';
        PinFromTF6 = ' ';

        t1.setText(" ");
        t2.setText(" ");
        t3.setText(" ");
        t4.setText(" ");
        t5.setText(" ");
        t6.setText(" ");
    }

    public void setTextFieldsStyle(){
        setTextFieldStyle(t1);
        setTextFieldStyle(t2);
        setTextFieldStyle(t3);
        setTextFieldStyle(t4);
        setTextFieldStyle(t5);
        setTextFieldStyle(t6);
    }


    public void getPins() {
        c1 = t1.getText().charAt(0);
        c2 = t2.getText().charAt(0);
        c3 = t3.getText().charAt(0);
        c4 = t4.getText().charAt(0);
        c5 = t5.getText().charAt(0);
        c6 = t6.getText().charAt(0);
    }


    public JTextField getT1() { return t1; }
    public JTextField getT2() { return t2; }
    public JTextField getT3() { return t3; }
    public JTextField getT4() { return t4; }
    public JTextField getT5() { return t5; }
    public JTextField getT6() { return t6; }
    public int getNumberOfPins(){ return NumberOfPins; }
    public int getChance() { return chance; }
    public String getSuccessMessage() {return successMessage; }
    public String getInvalidPinMessage(){return invalidPinMessage; }
    public String getBlockadeMessage(){return blockadeMessage; }
    public int getThickness() { return thickness; }
    public Color getColor() { return color; }
    public char getPin1() { return Pin1; }
    public char getPin2() { return Pin2; }
    public char getPin3() { return Pin3; }
    public char getPin4() { return Pin4; }
    public char getPin5() { return Pin5; }
    public char getPin6() { return Pin6; }
    public int getI() { return i; }

    public void setThickness(int thickness) { this.thickness = thickness; }
    public void setColor(Color color) { this.color = color; }
    public void setPin1(char pin1) { Pin1 = pin1; }
    public void setPin2(char pin2) { Pin2 = pin2; }
    public void setPin3(char pin3) { Pin3 = pin3; }
    public void setPin4(char pin4) { Pin4 = pin4; }
    public void setPin5(char pin5) { Pin5 = pin5; }
    public void setPin6(char pin6){ Pin6 = pin6; }
    public void setFrame(JFrame Frame) { frame = Frame; }
    public void setNumberOfPins(int numberOfPins){ NumberOfPins = numberOfPins; }
    public void setI(int i){ this.i = i; }
    public void setChance(int Chance){chance = Chance; }
    public void setSuccessMessage(String successMessage) { this.successMessage = successMessage; }
    public void setInvalidPinMessage(String invalidPinMessage) {this.invalidPinMessage = invalidPinMessage;}
    public void setBlockadeMessage(String blockadeMessage) {this.blockadeMessage = blockadeMessage;}
}
