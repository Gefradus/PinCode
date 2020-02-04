import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SetLook extends JFrame {
    private JLabel lblColor, lblThickness;
    private static JComboBox<String> choiceColor, choiceThickness;
    private JButton btnSave;
    private Color color;
    private int thickness = 2;

    public SetLook() {
    setSize(260,260);
    setResizable(false);
    setVisible(true);
    setLayout(null);
    SetPinCode.centerFrame(this);

    setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    addWindowListener(new WindowAdapter() {
        public void windowClosing (WindowEvent e){
            dispose();
            new SetPinCode();
        }
    });

    btnSave = new JButton("Zapisz zmiany");

    lblColor = new JLabel("Wybierz kolor obramowania:");
    lblThickness = new JLabel("Wybierz grubość obramowania:");

    choiceThickness = new JComboBox<>();
    choiceColor = new JComboBox<>();

    choiceThickness.addItem("Brak obramowania");
    choiceThickness.addItem("1 piksel");
    choiceThickness.addItem("2 piksele");
    choiceThickness.addItem("3 piksele");
    choiceThickness.setSelectedItem("2 piksele");

    choiceColor.addItem("Jasny szary");
    choiceColor.addItem("Ciemny szary");
    choiceColor.addItem("Granatowy");
    choiceColor.addItem("Czarny");
    choiceColor.setSelectedItem("Jasny szary");

    choiceColor.addActionListener(e -> {
        if (choiceColor.getSelectedItem() == "Jasny szary"){
            color = new Color(157, 157, 159);
        }
        if (choiceColor.getSelectedItem() == "Ciemny szary"){
            color = new Color(88, 88, 90);
        }
        if (choiceColor.getSelectedItem() == "Granatowy"){
            color = new Color(15, 51, 90);
        }
        if (choiceColor.getSelectedItem() == "Czarny"){
            color = Color.BLACK;
        }
    });

    choiceThickness.addActionListener(e -> {
        if (choiceThickness.getSelectedItem() == "Brak obramowania"){
            thickness = 0;
        }
        if (choiceThickness.getSelectedItem() == "1 piksel"){
            thickness = 1;
        }
        if (choiceThickness.getSelectedItem() == "2 piksele"){
            thickness = 2;
        }
        if (choiceThickness.getSelectedItem() == "3 piksele"){
            thickness = 3;
        }

    });


    add(lblColor);
    add(lblThickness);
    add(choiceThickness);
    add(choiceColor);
    add(btnSave);

    lblColor.setBounds(35,20,200,20);
    choiceColor.setBounds(35,40,180,30);
    lblThickness.setBounds(35,80,200,20);
    choiceThickness.setBounds(35,100,180,30);
    btnSave.setBounds(40,150,180,45);
    deserialize();

    btnSave.addActionListener(e -> {
        PinCode pinCode = new PinCode();
        PinCode deserialized = pinCode.deserialize(pinCode);
        deserialized.setColor(color);
        deserialized.setThickness(thickness);
        serialize();
        deserialized.serialize();
        JOptionPane.showMessageDialog(null, "Zmiany w wyglądzie zapisane pomyślnie.",
                "", JOptionPane.PLAIN_MESSAGE);
    });
    }

    public void serialize(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "\\save\\" + "wygląd.bin"))) {
            outputStream.writeObject(choiceColor.getSelectedItem());
            outputStream.writeObject(choiceThickness.getSelectedItem());
        }
        catch (Exception e){
            System.err.println();
        }
    }

    public void deserialize(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "\\save\\" + "wygląd.bin"))) {
            choiceColor.setSelectedItem(inputStream.readObject());
            choiceThickness.setSelectedItem(inputStream.readObject());
        }
        catch (Exception e){

        }
    }

}
