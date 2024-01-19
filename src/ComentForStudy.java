import java.awt.EventQueue;
import javax.swing.JFrame; //  contêiner  p/GUI
import javax.swing.JPanel; //  contêiner  p/ajuste de componentes dentro de uma window
import java.awt.BorderLayout;
import javax.swing.JLabel; // p/ infos estáticas em GUI
import javax.swing.JTextField; // p/ input do usuário
import javax.swing.JButton;
import javax.swing.SwingConstants; // constantes p/ alinhamento de itens dentro do contêiner (controla layout do GUI)
import java.awt.event.ActionListener; //interface p/ responder as interações feitas
import java.awt.event.ActionEvent; // p/ dar respostas aos eventos (cliques no btn por exemplo)
import java.io.IOException;

public class Form extends JFrame { // Declaração da classe Form que herda da classe JFrame, ou seja, Form
                                     // aproveita as funcionalidades da JFrame p/ criar janela
    // Declaração das var privadas com seus respectivos tipos, onde a frame será p/
    // manipular método e propriedade do form e o TextField será os campos de
    // entrada
    private JFrame frame; // declara variavel privada frame do tipo JFRAME
    private JTextField textFieldNome;
    private JTextField textFieldValor;
    private JTextField textFieldData;
    public Form() {
        initialize(); 
    }

    // Aqui onde a execução começa
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() { // EventQueue é uma classe que gerencia execução de eventos na
                                                // interface, invokeLater é o método que agenda a execução de código na
                                                // thread de evento principal
            // new Runnable cria uma nova instância da classe Runnable e já inicia um bloco:
            public void run() { // define o método run(), que é um método padrão da interface Runnable que não
                                // retorna nada e é público
                try {
                    Form window = new Form(); // Cria uma nova instância da classe Form que representa o form
                                                  // (torna o ), no caso cria um novo objeto que representa a janela a
                                                  // ser exibida
                    window.frame.setVisible(true); // Dx a janela visível na tela, pois window é a instância do form e
                                                   // sendo assim está chamando ele, frame é a var que representa a
                                                   // classe que constroi uma GUI e setVisible(true) torna visivel na
                                                   // tela
                } catch (Exception e) { // Exception é o tipo de exceção e este 'e' é usada p/ ter infos sobre a
                                        // exceção, como a mensagem de erro e a causa
                    e.printStackTrace(); // mostra o erro no console (printStackTrace é um método da classe Throwable)
                }
            }
        });
    }

    private void initialize() { // método privado que não retorna nada
        /*
         * instanciando/criando um objeto: Tanto frame = new JFrame(); como JPanel
         * panel1 = new JPanel();
         * São formas de instanciar classes (criar objetos)
         */
        frame = new JFrame("Formulário");
        JPanel panel1 = new JPanel(); // (instanciando/criando um objeto -- Opção 2)
        JLabel lblNome = new JLabel("Nome");
        JLabel lblValor = new JLabel("Valor");
        JLabel lblData = new JLabel("Data:");
        JButton btnEnviar = new JButton("Enviar");
        textFieldNome = new JTextField();
        textFieldValor = new JTextField();
        textFieldData = new JTextField();
        /*
         * Abaixo são os objetos utilizando atributos e métodos da classe
         */
        frame.setBounds(50, 50, 347, 290); // atributo da classe para dimensões da janela
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // método p/definir o comportamento do frame qnd a janela
                                                              // fechar
        frame.getContentPane().add(panel1, BorderLayout.CENTER);
        panel1.setLayout(null);
        lblNome.setHorizontalAlignment(SwingConstants.RIGHT);
        lblNome.setBounds(44, 31, 48, 14);
        panel1.add(lblNome); // Adiciona um componente no painel
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
        /*
         * É adicionado um listener no botão, addActionListener é o método
         * E new ActionListener() {: Um objeto anônimo que implementa a interface
         * ActionListener
         * A ActionListener não pode ser instanciada diretamente, mas Podemos
         * implementar
         * ela criando uma classe própria que 'ouve' ação
         */
        btnEnviar.addActionListener(new ActionListener() {
            // Único método da ActionListener:
            public void actionPerformed(ActionEvent e) {
                // Esse 'e' é um objeto e ele da informações sobre o evento de uma ação
                String nome = textFieldNome.getText();
                /*
                 * Insere o texto do campo textFieldNome na variável nome pois getText é metodo
                 * da JTextField e
                 * retorna o texto que está atualmente no campo de texto. O mesmo pras demais
                 */
                String valor = textFieldValor.getText();
                String data = textFieldData.getText();
                try {
                    /*
                     * ProcessBuilder é uma classe do pacote java.lang(pacote pré-importado em todos
                     * os programas Java)
                     * e por isso posso só instanciar ela direto sem importar nada antes;
                     * Ela é p/criar e iniciar processos do sistema operacional;
                     * Executa programas externos, scripts, ou qualquer outro tipo de processo.
                     */
                    ProcessBuilder processBuilder = new ProcessBuilder(
                            "python",
                            "C:\\PROGRAMMING-PC\\WIP\\CAMINHO PARA O SCRIPT\\ScriptConexão.py",
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
