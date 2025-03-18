import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main implements ActionListener{
    private JFrame frame;
    private BankAccount konto1, konto2, aktualneKonto;
    private JTextField kwotaField;
    private JLabel wlascicielLabel, nrkontaLabel, kwotaLabel, saldoLabel;
    private JButton wplataButton, wyplataButton, zmianaKontaButton;

    public Main(){
        frame = new JFrame("System bankowy");
        konto1 = new BankAccount("12345678", "Jan Kowalski", 10000);
        konto2 = new BankAccount("87654321", "Anna Nowak", 5000);
        aktualneKonto = konto1;

        frame.setSize(400, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(6, 2));

        wlascicielLabel = new JLabel();
        nrkontaLabel = new JLabel();
        kwotaLabel = new JLabel("Kwota:");
        kwotaField = new JTextField();
        wplataButton = new JButton("Wpłata");
        wyplataButton = new JButton("Wypłata");
        saldoLabel = new JLabel();
        zmianaKontaButton = new JButton("Zmień konto");

        wplataButton.addActionListener(this);
        wyplataButton.addActionListener(this);
        zmianaKontaButton.addActionListener(this);

        frame.add(nrkontaLabel);
        frame.add(wlascicielLabel);
        frame.add(kwotaLabel);
        frame.add(kwotaField);
        frame.add(wplataButton);
        frame.add(wyplataButton);
        frame.add(zmianaKontaButton);
        frame.add(saldoLabel);
        aktualizujWidok();
        frame.setVisible(true);
    }

    private void aktualizujWidok(){
        wlascicielLabel.setText("Właściciel konta: " + aktualneKonto.getWlasciciel());
        nrkontaLabel.setText("Numer konta: " + aktualneKonto.getNumerKonta());
        saldoLabel.setText("Saldo: " + aktualneKonto.getSaldo() + " PLN");
    }

    public static void main(String[] args){
        new Main();
    }

    @Override
    public void actionPerformed(ActionEvent e){
        Object src = e.getSource();

        if(src == wyplataButton){
            try{
                double kwota = Double.parseDouble(kwotaField.getText());
                aktualneKonto.wyplata(kwota);
                aktualizujWidok();
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Wprowadź poprawną liczbę!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(src == wplataButton){
            try{
                double kwota = Double.parseDouble(kwotaField.getText());
                aktualneKonto.wplata(kwota);
                aktualizujWidok();
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(frame, "Wprowadź poprawną liczbę!", "Błąd", JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex){
                JOptionPane.showMessageDialog(frame, ex.getMessage(), "Błąd", JOptionPane.ERROR_MESSAGE);
            }
        }
        if(src == zmianaKontaButton){
            if (aktualneKonto == konto1){
                aktualneKonto = konto2;
            } else{
                aktualneKonto = konto1;
            }
            aktualizujWidok();
        }

    }
}