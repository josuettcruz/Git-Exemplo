import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane; // https://brunoagt.wordpress.com/2011/03/28/javax-swing-joptionpane-conhecendo-e-utilizando-a-classe-joptionpane/

/**
 *
 * Josué Trindade
 * 660011453A
 * 
 */

public class conectaDAO {
    
    public Connection connectDB(){
        
        Connection conn;
        
        try {
        
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/uc11?user=aula","aula","660009929");
            
        } catch (SQLException erro){
            
            conn = null;
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO\nCodigo: [" + erro.getErrorCode() + "]\n" +  erro.getSQLState());
            
        } catch (Exception erro){
            
            conn = null;
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO\nCodigo: [" + erro.hashCode() + "]\n" +  erro.getMessage());
            
        }
        
        return conn;
        
    }
    
}