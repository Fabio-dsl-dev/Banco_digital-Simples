import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BancoGUI extends JFrame {
    private JTextField nomeClienteField;
    private JTextArea extratoArea;
    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;

    public BancoGUI() {
        setTitle("Banco Digital - Simples");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel painelTopo = new JPanel();
        painelTopo.add(new JLabel("Nome do Cliente:"));
        nomeClienteField = new JTextField(15);
        painelTopo.add(nomeClienteField);
        JButton criarContasBtn = new JButton("Criar Contas");
        painelTopo.add(criarContasBtn);
        add(painelTopo, BorderLayout.NORTH);

        extratoArea = new JTextArea();
        extratoArea.setEditable(false);
        add(new JScrollPane(extratoArea), BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel();
        JButton depositarBtn = new JButton("Depositar R$100");
        JButton transferirBtn = new JButton("Transferir R$50");
        JButton extratoBtn = new JButton("Ver Extratos");
        painelBotoes.add(depositarBtn);
        painelBotoes.add(transferirBtn);
        painelBotoes.add(extratoBtn);
        add(painelBotoes, BorderLayout.SOUTH);

        criarContasBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = nomeClienteField.getText();
                if (!nome.isEmpty()) {
                    Cliente cliente = new Cliente(nome);
                    contaCorrente = new ContaCorrente(cliente);
                    contaPoupanca = new ContaPoupanca(cliente);
                    extratoArea.setText("Contas criadas para " + nome + "!\n");
                } else {
                    extratoArea.setText("Digite o nome do cliente.\n");
                }
            }
        });

        depositarBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contaCorrente != null) {
                    contaCorrente.depositar(100);
                    extratoArea.append("Depositado R$100 na Conta Corrente.\n");
                } else {
                    extratoArea.setText("Crie as contas primeiro.\n");
                }
            }
        });

        transferirBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contaCorrente != null && contaPoupanca != null) {
                    contaCorrente.transferir(50, contaPoupanca);
                    extratoArea.append("Transferido R$50 da Corrente para Poupança.\n");
                } else {
                    extratoArea.setText("Crie as contas primeiro.\n");
                }
            }
        });

        extratoBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (contaCorrente != null && contaPoupanca != null) {
                    extratoArea.append("\n--- Extrato Conta Corrente ---\n");
                    extratoArea.append("Saldo: R$" + String.format("%.2f", contaCorrente.getSaldo()) + "\n");
                    extratoArea.append("--- Extrato Conta Poupança ---\n");
                    extratoArea.append("Saldo: R$" + String.format("%.2f", contaPoupanca.getSaldo()) + "\n");
                } else {
                    extratoArea.setText("Crie as contas primeiro.\n");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new BancoGUI().setVisible(true);
        });
    }
}
