package streaming.database;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import streaming.model.Colecao;

/**
 * Classe de conexão que serve apenas para conectar no Oracle do campus Canoas.
 * 
 * Para usar outro servidor altere ela de acordo com a necessidade. 
 * Lembre-se de alterar o seu usuario e sua senha.
 * 
 * @author Marcio Bigolin <marcio.bigolin@canoas.ifrs.edu.br>
 * @version 1.0.0
 */
public class Conector {

    private String usuario = "tads_bd09"; //"usuario";
    private String senha = "1234"; //"senha";
    private String servidor = "oracle.canoas.ifrs.edu.br";
    private int porta = 1521;

    private Connection conexao = null;

    public Conector() {
    }//inicia com os valores padrões

    public Conector(
            String usuario,
            String senha) {
        this.senha = senha;
        this.usuario = usuario;
    }

    public Connection getConexao() {
        if (conexao == null) {
            try {
                Class.forName("oracle.jdbc.driver.OracleDriver");
                conexao = DriverManager.getConnection(
                        "jdbc:oracle:thin:@" + this.servidor + ":" + this.porta + ":XE",
                        this.usuario,
                        this.senha);

            } catch (ClassNotFoundException e) {
                System.out.println("Senhor programador! Importe o pacote do DB antes de chingar o java");
            } catch(SQLException e){
                e.printStackTrace(); //Sei lá que diabos tu fez então olhe com calma as coisas.
            }

        }
        return conexao;
    }

    public void desconecta() {
        try {
            conexao.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}