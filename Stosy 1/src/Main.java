import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;
import java.util.Map;

public class Main{
    private static JFrame frame;
    private static Map<Integer, Osoba> uczestnicy = new LinkedHashMap<>();
    private static int numerStartowy = 1;
    private static DefaultListModel<String> listaModel = new DefaultListModel<>();
    private static JList<String> listaUczestnikow = new JList<>(listaModel);

    Main(){
        frame = new JFrame("Zarządzanie uczestnikami");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPnl = new JPanel();
        JButton addBtn = new JButton("Dodaj");
        JButton delBtn = new JButton("Usuń");
        JButton checkBtn = new JButton("Sprawdź obecność");

        topPnl.add(addBtn);
        topPnl.add(delBtn);
        topPnl.add(checkBtn);

        frame.add(topPnl, BorderLayout.NORTH);
        frame.add(new JScrollPane(listaUczestnikow), BorderLayout.CENTER);

        addBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                dodajUczestnika();
            }
        });
        delBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                usunUczestnika();
            }
        });
        checkBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                sprawdzObecnosc();
            }
        });

        frame.setVisible(true);
    }

    private void dodajUczestnika(){
        JTextField imieField = new JTextField();
        JTextField nazwiskoField = new JTextField();
        JTextField wiekField = new JTextField();
        Object[] message ={
                "Imię:", imieField,
                "Nazwisko:", nazwiskoField,
                "Wiek:", wiekField
        };

        int option = JOptionPane.showConfirmDialog(frame, message, "Dodaj uczestnika", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION){
            try{
                String imie = imieField.getText();
                String nazwisko = nazwiskoField.getText();
                int wiek = Integer.parseInt(wiekField.getText());

                if (wiek <= 0){
                    JOptionPane.showMessageDialog(frame, "Wiek musi być większy od zera!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                Osoba osoba = new Osoba(imie, nazwisko, wiek);
                uczestnicy.put(numerStartowy++, osoba);
                listaModel.addElement("Numer " + (numerStartowy - 1) + ": " + osoba.toString());

            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Wiek musi być liczbą całkowitą!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void usunUczestnika(){
        String numer = JOptionPane.showInputDialog(frame, "Podaj numer startowy do usunięcia:");
        if (numer != null){
            try{
                int nr = Integer.parseInt(numer);
                if (uczestnicy.containsKey(nr)){
                    uczestnicy.remove(nr);
                    odswiezListe();
                    JOptionPane.showMessageDialog(frame, "Uczestnik usunięty.");
                } else{
                    JOptionPane.showMessageDialog(frame, "Nie znaleziono uczestnika o tym numerze.", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e){
                JOptionPane.showMessageDialog(frame, "Numer startowy musi być liczbą!", "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void sprawdzObecnosc(){
        String nazwisko = JOptionPane.showInputDialog(frame, "Podaj nazwisko do sprawdzenia:");
        if (nazwisko != null){
            boolean znaleziono = false;

            for (Osoba osoba : uczestnicy.values()){
                if (osoba.getNazwisko().equalsIgnoreCase(nazwisko)){
                    znaleziono = true;
                    break;
                }
            }
            String komunikat;
            if (znaleziono){
                komunikat = nazwisko + " jest na liście.";
            }
            else{
                komunikat = nazwisko + " NIE jest na liście.";
            }
            JOptionPane.showMessageDialog(frame, komunikat);
        }
    }

    private void odswiezListe(){
        listaModel.clear();
        for (Map.Entry<Integer, Osoba> entry : uczestnicy.entrySet()){
            listaModel.addElement("Numer " + entry.getKey() + ": " + entry.getValue().toString());
        }
    }

    public static void main(String[] args){
        new Main();
    }
}

