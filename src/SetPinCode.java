import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;


public class SetPinCode extends JFrame {

    private static JButton turnOn, setChance, setCommunique, setPin, setLookOfTextF;
    private static JComboBox<String> choiceBox;
    private static JFrame frame;
    private static PinCode pinCode;


    public static void main(String[] args) {
        new SetPinCode();
    }

    SetPinCode() {
        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); } catch (Exception ignored){}
        frame = this;
        frame.getContentPane().setBackground(new Color(227, 230, 222));
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setSize(340, 625);
        centerFrame(frame);
        pinCode = new PinCode();

        JTextField t1 = new JTextField();
        JTextField t2 = new JTextField();
        JTextField t3 = new JTextField();
        JTextField t4 = new JTextField();
        JTextField t5 = new JTextField();
        JTextField t6 = new JTextField();
        t6.setVisible(false);

        JLabel pinCount = new JLabel("Wybierz liczbę pinów: ");
        choiceBox = new JComboBox<>();
        choiceBox.addItem("4");
        choiceBox.addItem("5");
        choiceBox.addItem("6");
        choiceBox.setSelectedItem("5");

        customTextField(null, t1, t2);
        customTextField(t1, t2, t3);
        customTextField(t2, t3, t4);
        customTextField(t3, t4, t5);
        customTextField(t4, t5, t6);
        customTextField(t5, t6, null);

        turnOn = new JButton("Uruchom");
        turnOn.setBackground(new Color(87, 207, 53));

        setChance = new JButton("Ustaw liczbę prób");
        setCommunique = new JButton("Ustaw komunikaty");
        setPin = new JButton("Ustaw kod PIN");
        setLookOfTextF = new JButton("Ustaw obramowanie pól");
        setButtonsSize(170, 45);

        JPanel pinPanel = new JPanel();
        pinPanel.setBorder(new BevelBorder(BevelBorder.LOWERED));
        pinPanel.setLayout(null);
        pinPanel.add(t1);
        pinPanel.add(t2);
        pinPanel.add(t3);
        pinPanel.add(t4);
        pinPanel.add(t5);
        pinPanel.add(t6);
        pinPanel.add(pinCount);
        pinPanel.add(choiceBox);
        pinPanel.add(setPin);
        add(setLookOfTextF);
        add(pinPanel);
        add(setChance);
        add(setCommunique);
        add(turnOn);

        choiceBox.addActionListener(e -> {
            if (choiceBox.getSelectedItem().toString().equals("4")){
                t5.setVisible(false);
                t6.setVisible(false);
                setSize(280,625);
                setObjectsPos(50);
            }
            else if (choiceBox.getSelectedItem().toString().equals("5")){
                t6.setVisible(false);
                t5.setVisible(true);
                setSize(340, 625);
                setObjectsPos(80);
            }
            else {
                t5.setVisible(true);
                t6.setVisible(true);
                setSize(400,625);
                setObjectsPos(110);
            }
        });




        setPin.addActionListener(e -> {
            if (choiceBox.getSelectedItem().toString().equals("4")){
                try{
                    char c1 = t1.getText().charAt(0);
                    char c2 = t2.getText().charAt(0);
                    char c3 = t3.getText().charAt(0);
                    char c4 = t4.getText().charAt(0);

                    if (isNumber(c1) && isNumber(c2) && isNumber(c3) && isNumber(c4)) {
                        PinCode deserialized = pinCode.deserialize(pinCode);
                        deserialized.setPin1(c1);
                        deserialized.setPin2(c2);
                        deserialized.setPin3(c3);
                        deserialized.setPin4(c4);
                        deserialized.setNumberOfPins(4);
                        deserialized.serialize();
                        successAlert(4);
                    } else {
                        errorAlert();}}catch (Exception e2){
                    errorAlert();}
            }

            if (choiceBox.getSelectedItem().toString().equals("5")) {
                try{
                    char c1 = t1.getText().charAt(0);
                    char c2 = t2.getText().charAt(0);
                    char c3 = t3.getText().charAt(0);
                    char c4 = t4.getText().charAt(0);
                    char c5 = t5.getText().charAt(0);

                    if (isNumber(c1) && isNumber(c2) && isNumber(c3) && isNumber(c4) && isNumber(c5)) {
                        PinCode deserialized = pinCode.deserialize(pinCode);
                        deserialized.setPin1(c1);
                        deserialized.setPin2(c2);
                        deserialized.setPin3(c3);
                        deserialized.setPin4(c4);
                        deserialized.setPin5(c5);
                        deserialized.setNumberOfPins(5);
                        deserialized.serialize();
                        successAlert(5);
                    } else {
                        errorAlert();}}catch (Exception e2){
                    errorAlert();}
            }

            if (choiceBox.getSelectedItem().toString().equals("6")){
                try{
                    char c1 = t1.getText().charAt(0);
                    char c2 = t2.getText().charAt(0);
                    char c3 = t3.getText().charAt(0);
                    char c4 = t4.getText().charAt(0);
                    char c5 = t5.getText().charAt(0);
                    char c6 = t6.getText().charAt(0);

                    if (isNumber(c1) && isNumber(c2) && isNumber(c3) && isNumber(c4) && isNumber(c5) && isNumber(c6)) {
                        PinCode deserialized = pinCode.deserialize(pinCode);
                        deserialized.setPin1(c1);
                        deserialized.setPin2(c2);
                        deserialized.setPin3(c3);
                        deserialized.setPin4(c4);
                        deserialized.setPin5(c5);
                        deserialized.setPin6(c6);
                        deserialized.setNumberOfPins(6);
                        deserialized.serialize();
                        successAlert(6);
                    } else {
                        errorAlert();}}catch(Exception e2){
                    errorAlert();}
            }
        });


        turnOn.addActionListener(e -> {

            File file = new File(System.getProperty("user.dir") + "\\save\\" + "serializacja.txt");
            String pinNumber = choiceBox.getSelectedItem().toString();

            if (file.exists() && ifPinsExists()) {
                if(ifChanceExists()) {
                    PinCode deserialized = pinCode.deserialize(pinCode);
                    File communiqueFile = new File(System.getProperty("user.dir") + "\\save\\" + "komunikaty.bin");
                    File lookFile = new File(System.getProperty("user.dir") + "\\save\\" + "wygląd.bin");
                    if (!ifCommuniqueExists()) {
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(communiqueFile))) {
                            String successMessage = "TRANSAKCJA ZAAKCEPTOWANA";
                            String blockadeMessage = "PRZEKROCZONO LICZBĘ NIEPOPRAWNYCH PRÓB, OPERACJE ZABLOKOWANE.";
                            String invalidMessage = "NIEPOPRAWNY KOD. POZOSTAŁYCH PRÓB: ";
                            outputStream.writeObject(successMessage);
                            outputStream.writeObject(blockadeMessage);
                            outputStream.writeObject(invalidMessage);
                            deserialized.setSuccessMessage(successMessage);
                            deserialized.setBlockadeMessage(blockadeMessage);
                            deserialized.setInvalidPinMessage(invalidMessage);
                            deserialized.serialize();
                        } catch (Exception ignored) {}
                    }
                    if(!lookFile.exists()){
                        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(lookFile))) {
                          Color defaultColor = new Color(157, 157, 159);
                          outputStream.writeObject(defaultColor);
                          outputStream.writeObject(2);
                          deserialized.setColor(defaultColor);
                          deserialized.setThickness(2);
                          deserialized.serialize();
                        } catch (Exception ignored) {}
                    }

                    getPinCode(deserialized, frame);
                }
                else {
                    JOptionPane.showMessageDialog(null, "\"Błąd. Ustaw liczbę prób. ",
                            "", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(null, "\"Nie ustawiono PIN-CODE. " +
                                "Podaj PIN-CODE w postaci "+pinNumber+" cyfr",
                        "", JOptionPane.ERROR_MESSAGE);
            }
        });


        setChance.addActionListener(e -> {
            String[] options = {"OK"};
            JPanel panel = new JPanel();
            JLabel lbl = new JLabel("Podaj liczbę prób: ");
            JTextField txt = new JTextField(2);
            panel.add(lbl);
            panel.add(txt);
            int selectedOption = JOptionPane.showOptionDialog(null, panel, "Podaj liczbę prób",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options , options[0]);

            if(selectedOption == 0)
            {
                try{
                    int chance = Integer.parseInt(txt.getText());
                    PinCode deserialize = pinCode.deserialize(pinCode);
                    deserialize.setI(chance);
                    deserialize.setChance(chance);
                    deserialize.serialize();
                    JOptionPane.showMessageDialog(null, "Liczba prób została ustawiona na: "+chance,
                            "", JOptionPane.PLAIN_MESSAGE);

                }catch (Exception e2){
                    JOptionPane.showMessageDialog(null, "Podano błędnie liczbę prób",
                            "", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setCommunique.addActionListener(e -> {
            new SetCommunique();
            dispose();
        });

        setLookOfTextF.addActionListener(e -> {
            new SetLook();
            dispose();
        });



        pinPanel.setBounds(0, 0,400 , 230);
        t1.setBounds(20, 70, 50, 50);
        t2.setBounds(80, 70, 50, 50);
        t3.setBounds(140, 70, 50, 50);
        t4.setBounds(200, 70, 50, 50);
        t5.setBounds(260, 70, 50, 50);
        t6.setBounds(320,70,50,50);
        pinCount.setBounds(20,20,250,20);
        choiceBox.setBounds(140,15,50,30);
        setObjectsPos(80);
    }

    public boolean ifPinsExists(){
        PinCode deserialize = pinCode.deserialize(pinCode);
        return deserialize.getPin1() != '\u0000' && deserialize.getPin2() != '\u0000'
                && deserialize.getPin3() != '\u0000' && deserialize.getPin4() != '\u0000';
    }

    public boolean ifChanceExists(){
        return pinCode.deserialize(pinCode).getChance() != '\u0000';
    }

    public boolean ifCommuniqueExists() {
        PinCode deserialize = pinCode.deserialize(pinCode);
        return deserialize.getSuccessMessage() != null && deserialize.getInvalidPinMessage() != null
                && deserialize.getBlockadeMessage() != null;
    }

    public static void getPinCode(PinCode pc, JFrame frame){
        new GetPinCode();
        frame.dispose();
    }

    public static void setButtonsSize(int w, int h) {
        setChance.setSize(w, h);
        turnOn.setSize(w, h);
        setCommunique.setSize(w, h);
        setPin.setSize(w, h);
        setLookOfTextF.setSize(w,h);
    }

    public boolean isNumber(char charFromTexField) {
        return Character.isDigit(charFromTexField);     //zwraca true jeśli to cyfra
    }

    public static void customTextField(JTextField prevTF, JTextField tf, JTextField nextTF) {
        tf.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent e) {
                tf.getText();
                if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE && e.getKeyCode() != KeyEvent.VK_LEFT) {
                    if (tf.getText().length() > 0){
                        if (e.getKeyCode() >= 48 && e.getKeyCode()  <= 57 || e.getKeyCode()  >= 65 && e.getKeyCode()  <= 90){
                        nextTF.setText(e.getKeyChar()+"");
                        nextTF.requestFocus();
                        }
                    }
                }
                else{
                    if(tf.getText().length() == 0 && prevTF != null){
                        prevTF.requestFocus();
                    }
                }
            }
            public void keyTyped(KeyEvent e){
                if(tf.getText().length() > 0){
                    e.consume();
                }
            }
        });

        tf.setSize(50, 50);
        tf.setHorizontalAlignment(JTextField.CENTER);
    }


    public static void successAlert(int i){
        JOptionPane.showMessageDialog(null, i+"-cyfrowy PIN-CODE został ustawiony pomyślnie.",
                "", JOptionPane.PLAIN_MESSAGE);
    }


    public static void errorAlert(){
        JOptionPane.showMessageDialog(null, "Niepoprawnie podany PIN-CODE. " +
                        "Podaj PIN-CODE w postaci "+choiceBox.getSelectedItem()+" cyfr",
                "", JOptionPane.ERROR_MESSAGE);
    }

    public static void setObjectsPos(int x) {
        setPin.setBounds(x, 150, 170, 45);
        setChance.setBounds(x,265,170,45);
        setCommunique.setBounds(x,340,170,45);
        turnOn.setBounds(x,490,170,45);
        setLookOfTextF.setBounds(x,415,170,45);
    }

    public static void centerFrame(JFrame frame){
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - frame.getWidth()) / 2;
        int y = (screenSize.height - frame.getHeight()) * 7/20;
        frame.setLocation(x, y);
    }
}