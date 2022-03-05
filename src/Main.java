import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        int pagina = 0;
        int quantidadeItens = 0;
        int i = 0;
        Scanner scan = new Scanner(System.in);
        System.out.println("-----MENU-----");
        System.out.println("Selecione a quantidade de itens por pagina: ");
        quantidadeItens = scan.nextInt();
        System.out.println("Selecione a pagina: ");
        pagina = scan.nextInt();

        Connection con = new ConnectionFactory().getConnection();
        Statement stm = con.createStatement();

        int itemInicial = (quantidadeItens * pagina - quantidadeItens + 1);

        if(itemInicial <= 20){
            System.out.println("\n------------");
            System.out.println("Listando página: " + pagina);
            System.out.println("------------");
            for (i = itemInicial; i <= (quantidadeItens * pagina); i++) {
                stm.execute("SELECT * FROM filme WHERE id =" + i);
                ResultSet rst = stm.getResultSet();
                while (rst.next()) {
                    Integer id = rst.getInt(1);
                    String nome = rst.getString(2);
                    String descricao = rst.getString(3);
                    String ano = rst.getString(4);
                    System.out.println("\n------------");
                    System.out.println("ID: " + id);
                    System.out.println("------------");
                    System.out.println("FILME: " + nome);
                    System.out.println("------------");
                    System.out.println("DESCRIÇÃO: " + descricao);
                    System.out.println("------------");
                    System.out.println("ANO: " + ano);
                    System.out.println("------------");
                    if(i==20){
                        System.exit(0);
                    }
                }
            }
        }else{
            System.out.println("Pedido Invalido");
        }
    }
}