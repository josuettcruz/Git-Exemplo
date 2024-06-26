/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author josue
 */
public class Err {
    
    private String type;
    private int code;
    private String message;
    private boolean user;
    
    public Err(){
        
        this.type = "";
        this.code = 0;
        this.message = "";
        this.user = false;
        
    }
    
    public void Clean(){
        
        this.type = "";
        this.code = 0;
        this.message = "";
        this.user = false;
        
    }
    
    public void setCmd(String type){
        
        this.type = type;
        this.user = true;
        
    }
    
    public void setCode(int code){
        
        this.code = code;
        this.user = true;
        
    }
    
    public void setMsg(String message){
        
        this.message = message;
        this.user = true;
        
    }
    
    public boolean Ok(){
        
        return this.user;
        
    }
    
    public String getCmd(){
        
        return this.type;
        
    }
    
    public int getCode(){
        
        return this.code;
        
    }
    
    public String getMsg(){
        
        return this.message;
        
    }
    
}