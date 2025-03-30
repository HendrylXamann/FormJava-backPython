import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

public class Form extends JFrame {
    private JFrame frame;
    private JTextField textFieldNome;
    private JTextField textFieldValor;
    private JTextField textFieldData;

    public Form() {
        initialize();
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Form window = new Form();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void initialize() {
        frame = new JFrame("Formulário");
        JPanel panel1 = new JPanel();
        JLabel lblNome = new JLabel("Nome");
        JLabel lblValor = new JLabel("Valor");
        JLabel lblData = new JLabel("Data:");
        JButton btnEnviar = new JButton("Enviar");
        textFieldNome = new JTextField();
        textFieldValor = new JTextField();
        textFieldData = new JTextField();
        frame.setBounds(50, 50, 347, 290);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(panel1, BorderLayout.CENTER);
        panel1.setLayout(null);
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(44, 31, 48, 14);
        panel1.add(lblNome);
        textFieldNome.setBounds(102, 28, 188, 20);
        panel1.add(textFieldNome);
        textFieldNome.setColumns(10);
        textFieldValor.setBounds(102, 71, 188, 20);
        panel1.add(textFieldValor);
        textFieldValor.setColumns(10);
        lblValor.setHorizontalAlignment(SwingConstants.RIGHT);
        lblValor.setBounds(53, 74, 39, 14);
        panel1.add(lblValor);
        textFieldData.setBounds(102, 114, 188, 20);
        panel1.add(textFieldData);
        textFieldData.setColumns(10);
        lblData.setHorizontalAlignment(SwingConstants.RIGHT);
        lblData.setBounds(10, 117, 82, 14);
        panel1.add(lblData);
        btnEnviar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String nome = textFieldNome.getText();
                String valor = textFieldValor.getText();
                String data = textFieldData.getText();
                try {
                    ProcessBuilder processBuilder = new ProcessBuilder(
                            "python",
                            "caminhoParaOscriptColocarRelativoOuCompelto/ScriptConexão.py",
                            nome,
                            valor,
                            data);

                    Process process = processBuilder.start();
                    int exitCode = process.waitFor();

                    if (exitCode == 0) {
                        System.out.println("Script Python executado com sucesso.");
                    } else {
                        System.out.println("Erro ao executar o script Python.");
                    }
                } catch (IOException | InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        });
        btnEnviar.setBounds(138, 140, 89, 23);
        panel1.add(btnEnviar);
    }

}
