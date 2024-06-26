/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * Josué Trindade
 * 660011453A
 * 
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProdutosDAO {
    
    private Connection conn;
    private PreparedStatement prep;
    private Statement stmt = null;
    private ResultSet resultset;
    private boolean open;
    private int op;
    
    Err select;
    Err insert;
    Err modify;
    
    public ProdutosDAO(){
        
        this.select = new Err();
        this.insert = new Err();
        this.modify = new Err();
        this.op = 0;
        
        try{
            
            conn = new conectaDAO().connectDB();
            this.open = true;
            
        }catch(Exception e){
            
            this.open = false;
            
        }
        
    }//ProdutosDAO()
    
    public void cadastrarProduto(Produtos produto){
        
        this.op = 1;
        
        try{
            
            prep = conn.prepareStatement("insert into produtos (nome, valor, status) values (?, ?, ?)");
            prep.setString(1, produto.getNome());
            prep.setInt(2, produto.getValor());
            prep.setString(3, produto.getStatus());
            prep.executeUpdate();
            insert.Clean();
            
         }catch(SQLException e){
            
             insert.setCmd("SQLException");
             insert.setCode(e.getErrorCode());
             insert.setMsg(e.getSQLState());
            
        }catch(Exception e){
            
             insert.setCmd("Exception");
             insert.setCode(e.hashCode());
             insert.setMsg(e.getMessage());
            
        }
        
    }//cadastrarProduto(ProdutosDTO produto)
    
    public ArrayList<Produtos> listarProdutos(){
        
        this.op = 2;
        
        ArrayList<Produtos> listagem = new ArrayList<>();
        
        String sql = "select id, ifnull(nome, \"\") as nome, ";
        sql += "ifnull(valor, 0) as valor, ifnull(status, \"\") as status ";
        sql += "from produtos order by id asc;";
        
        try{
            
            prep = conn.prepareStatement(sql);
            resultset = prep.executeQuery();
            
            while(resultset.next()){
                
                Produtos pro = new Produtos();
                
                pro.setId(resultset.getInt("id"));
                pro.setNome(resultset.getString("nome"));
                pro.setValor(resultset.getInt("valor"));
                pro.setStatus(resultset.getString("status"));
                
                listagem.add(pro);
                
            }
            
            select.Clean();
            
        }catch(SQLException e){
            
            select.setCmd("SQLException");
            select.setCode(e.getErrorCode());
            select.setMsg(e.getMessage());
            
        }catch(Exception e){
            
            select.setCmd("Exception");
            select.setCode(e.hashCode());
            select.setMsg(e.getMessage());
            
        }
        
        return listagem;
        
    }//listarProdutos()
    
    public void venderProduto(int id){
        
        this.op = 3;
        
        try{
            
            prep = conn.prepareStatement("update produtos set status = \"Vendido\" where id = ?");
            prep.setInt(1, id);
            prep.executeUpdate();
            modify.Clean();
            
         }catch(SQLException e){
            
             modify.setCmd("SQLException");
             modify.setCode(e.getErrorCode());
             modify.setMsg(e.getMessage());
            
        }catch(Exception e){
            
             modify.setCmd("Exception");
             modify.setCode(e.hashCode());
             modify.setMsg(e.getMessage());
            
        }
        
    }
    
    public Err msgInsert(){
        return this.insert;
    }
    
    public Err msgSelect(){
        return this.select;
    }
    
    public Err msgModify(){
        return this.modify;
    }
    
    public void Alert(){
        
        switch(op){
            
            case 1 ->{
                
                if(insert.Ok()){

                    JOptionPane.showMessageDialog(null, insert.getMsg(), 
                            insert.getCmd() + 
                            ": " + 
                            insert.getCode(), 
                            JOptionPane.ERROR_MESSAGE);
                    
                    System.out.println(insert.getMsg());

                } else {

                    JOptionPane.showMessageDialog(null, "Dados inseridos com sucesso!", 
                            "Salvo",
                            JOptionPane.INFORMATION_MESSAGE);

                }
                
            }
            
            case 2 ->{
                
                if(select.Ok()){

                    JOptionPane.showMessageDialog(null, select.getMsg(), 
                            select.getCmd() + 
                            ": " + 
                            select.getCode(), 
                            JOptionPane.ERROR_MESSAGE);
                    
                    System.out.println(select.getMsg());

                }
                
            }
            
            case 3 ->{
                
                if(modify.Ok()){

                    JOptionPane.showMessageDialog(null, modify.getMsg(), 
                            modify.getCmd() + 
                            ": " + 
                            modify.getCode(), 
                            JOptionPane.ERROR_MESSAGE);
                    
                    System.out.println(modify.getMsg());

                }
                
            }
            
        }//switch(op)
        
    }//Alert()
    
    public String Close(){
        
        if(this.open){
            
            try{
                
                conn.close();
                
                return "Conexão encerrada com sucesso!";
                
            }catch (SQLException ex){
                
                return "SQLState: " + ex.getSQLState();
                
            }catch (Exception ex){
                
                return ex.getMessage();
                
            }
            
        } else {
            
            return "Não houve conexão!";
            
        }
        
    }//Close()
    
        
}