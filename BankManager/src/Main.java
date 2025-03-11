import bank.BankAccount;
import bank.BankManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
    static JFrame frame;
    static JComboBox<String> fromAccountCombo, toAccountCombo;
    static JTextField balanceField, transferAmountField;
    static JButton showBalanceBtn, transferBtn;
    static BankAccount account1 = new BankAccount("Jan Kowalski", 5000.0);
    static BankAccount account2 = new BankAccount("Anna Nowak", 7500.0);
    static BankManager manager = new BankManager();

    public Main(){
        frame = new JFrame("System Bankowy");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();
        topPanel.add(new JLabel("Wybierz konto:"));

        fromAccountCombo = new JComboBox<>(new String[]{"Jan Kowalski", "Anna Nowak"});
        topPanel.add(fromAccountCombo);

        showBalanceBtn = new JButton("Pokaż saldo");
        topPanel.add(showBalanceBtn);
        frame.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(3, 2));

        balanceField = new JTextField(15);
        balanceField.setEditable(false);
        centerPanel.add(new JLabel("Saldo:"));
        centerPanel.add(balanceField);

        transferAmountField = new JTextField(10);
        centerPanel.add(new JLabel("Kwota przelewu:"));
        centerPanel.add(transferAmountField);

        toAccountCombo = new JComboBox<>(new String[]{"Jan Kowalski", "Anna Nowak"});
        centerPanel.add(new JLabel("Przelej do:"));
        centerPanel.add(toAccountCombo);

        frame.add(centerPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        transferBtn = new JButton("Wykonaj przelew");
        bottomPanel.add(transferBtn);
        frame.add(bottomPanel, BorderLayout.SOUTH);

        showBalanceBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                BankAccount selectedAccount = getSelectedAccount(fromAccountCombo);
                balanceField.setText(selectedAccount.getBalance() + " PLN");
                manager.printBalance(selectedAccount);
            }
        });

        transferBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                BankAccount fromAccount = getSelectedAccount(fromAccountCombo);
                BankAccount toAccount = getSelectedAccount(toAccountCombo);

                if (fromAccount == toAccount){
                    JOptionPane.showMessageDialog(frame, "Nie można przelać na to samo konto!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String amountText = transferAmountField.getText();
                double amount = 0;

                if (!amountText.isEmpty()){
                    try {
                        amount = Double.parseDouble(amountText);
                    } catch (NumberFormatException ex){
                        JOptionPane.showMessageDialog(frame, "Niepoprawne dane!", "Błąd", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }

                if (amount <= 0){
                    JOptionPane.showMessageDialog(frame, "Kwota musi być większa niż 0!", "Błąd", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                if (manager.transfer(fromAccount, toAccount, amount)){
                    balanceField.setText(fromAccount.getBalance() + " PLN");
                    JOptionPane.showMessageDialog(frame, "Przelew udany!", "Sukces", JOptionPane.INFORMATION_MESSAGE);
                } else{
                    JOptionPane.showMessageDialog(frame, "Coś nie pykło!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        frame.setVisible(true);
    }

    private static BankAccount getSelectedAccount(JComboBox<String> comboBox){
        String selectedUser = (String) comboBox.getSelectedItem();
        if (selectedUser.equals("Jan Kowalski")){
            return account1;
        } else{
            return account2;
        }
    }

    public static void main(String[] args){
        new Main();
    }
}
