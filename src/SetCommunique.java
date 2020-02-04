import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SetCommunique extends JFrame implements ActionListener{

    JTextField tfSuccess, tfInvalid, tfBlockade;
    JLabel lblSuccess, lblInvalid, lblBlockade;
    JButton btnSuccess, btnInvalid, btnBlockade, btnSave;

    public SetCommunique(){
        setSize(770,350);
        setResizable(false);
        setVisible(true);
        setLayout(null);
        SetPinCode.centerFrame(this);

        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
                new SetPinCode();
            }
        });


        btnSave = new JButton("Zapisz komunikaty");
        btnSuccess = new JButton("Przywróć domyślny");
        btnInvalid = new JButton("Przywróć domyślny");
        btnBlockade = new JButton("Przywróć domyślny");
        btnSave.setSize(170,45);

        String successMessage = "Komunikat powodzenia";
        String invalidMessage = "Komunikat niepoprawnego PIN'u i pozostałej liczby prób)";
        String blockadeMessage = "Komunikatu o przekroczonej liczby prób i blokadzie";

        lblSuccess = new JLabel(successMessage + ":");
        lblInvalid = new JLabel(invalidMessage+ ":");
        lblBlockade = new JLabel(blockadeMessage+ ":");

        tfSuccess = new JTextField();
        tfInvalid = new JTextField();
        tfBlockade = new JTextField();
        deserialize();

        lblSuccess.setBounds(20, 20, 770, 20);
        tfSuccess.setBounds(20, 40, 550, 40);
        btnSuccess.setBounds(580, 40, 170, 45);
        lblInvalid.setBounds(20,80, 770, 20);
        tfInvalid.setBounds(20,100, 550, 40);
        btnInvalid.setBounds(580, 100, 170, 45);
        lblBlockade.setBounds(20,140,770,20);
        tfBlockade.setBounds(20,160, 550, 40);
        btnBlockade.setBounds(580, 160, 170, 45);
        btnSave.setBounds(300, 230, 170, 45);

        add(lblSuccess);
        add(tfSuccess);
        add(btnSuccess);
        add(lblInvalid);
        add(tfInvalid);
        add(btnInvalid);
        add(lblBlockade);
        add(tfBlockade);
        add(btnBlockade);
        add(btnSave);

        btnSuccess.addActionListener(this);
        btnBlockade.addActionListener(this);
        btnInvalid.addActionListener(this);
        btnSave.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnSuccess){
            tfSuccess.setText("TRANSAKCJA ZAAKCEPTOWANA");
        }
        if (source == btnBlockade){
            tfBlockade.setText("PRZEKROCZONO LICZBĘ NIEPOPRAWNYCH PRÓB, OPERACJE ZABLOKOWANE.");
        }
        if (source == btnInvalid){
            tfInvalid.setText("NIEPOPRAWNY KOD. POZOSTAŁYCH PRÓB: ");
        }
        if (source == btnSave){
            PinCode pinCode = new PinCode();
            PinCode deserialized = pinCode.deserialize(pinCode);
            deserialized.setSuccessMessage(tfSuccess.getText());
            deserialized.setBlockadeMessage(tfBlockade.getText());
            deserialized.setInvalidPinMessage(tfInvalid.getText());
            serialize(tfSuccess.getText(), tfBlockade.getText(), tfInvalid.getText());
            deserialized.serialize();
            JOptionPane.showMessageDialog(null, "Komunikaty zapisane pomyślnie.",
            "", JOptionPane.PLAIN_MESSAGE);
        }
    }

    public void serialize(String success, String blockade, String invalid){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(System.getProperty("user.dir") + "\\save\\" + "komunikaty.bin"))) {
            outputStream.writeObject(success);
            outputStream.writeObject(blockade);
            outputStream.writeObject(invalid);
        }
        catch (Exception e){
            System.err.println();
        }
    }

    public void deserialize(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(System.getProperty("user.dir") + "\\save\\" + "komunikaty.bin"))) {
            tfSuccess.setText((String) inputStream.readObject());
            tfBlockade.setText((String) inputStream.readObject());
            tfInvalid.setText((String) inputStream.readObject());
        }
        catch (Exception e){
            tfBlockade.setText("PRZEKROCZONO LICZBĘ NIEPOPRAWNYCH PRÓB, OPERACJE ZABLOKOWANE.");
            tfSuccess.setText("TRANSAKCJA ZAAKCEPTOWANA");
            tfInvalid.setText("NIEPOPRAWNY KOD. POZOSTAŁYCH PRÓB: ");
        }
    }
}