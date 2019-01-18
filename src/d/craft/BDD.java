/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d.craft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author antoi
 */
public class BDD {
    
    ResultSet Idole = null;
    ResultSet Id_Idole = null;
    ResultSet Nom_Idole = null;
    ResultSet LVL_Idole = null;
    
    ResultSet Recette = null;
    ResultSet Ressource = null;
    
    private Accueil fen;
    
    Connection conn = null;
    
    public Connection getConnection(){
        String url = new String("jdbc:mysql://localhost/dofuscraft");
        try {            
            Class.forName("com.mysql.jdbc.Driver"); 
            String usr = "root";
            String psw = "";
            conn= DriverManager.getConnection(url, usr, psw);
              System.out.println ("Connexion OK");
        } 
        catch (SQLException ex) { 
            System.out.println ("Erreur de connexion à la Base");            
            ex.printStackTrace();        
        } 
        catch (ClassNotFoundException ex) { 
            System.out.println ("Erreur initialisation du Driver");             
            ex.printStackTrace();        
        }    
        return conn;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    
    //Récupération de toutes les idoles
    public ResultSet getIdIdole(Accueil fen, String selecteur, String orderBy){ 
        
        try{
        Statement stm = conn.createStatement(); 
        Idole = stm.executeQuery("SELECT * FROM idole"+ selecteur + orderBy);
        System.out.println(Idole);
        String id = "";
        String nom = "";
        String lvl = "";
        fen.model.getDataVector().clear();
            while (Idole.next()){ 
                id = Idole.getString("ID_idle");
                nom = Idole.getString("Nom_idle");
                lvl = Idole.getString("Lvl_idle");
                                
                fen.rowData[0] = id;
                fen.rowData[1] = nom;
                fen.rowData[2] = lvl;
                fen.model.addRow(fen.rowData);
            }
            System.out.println(fen.tableauDynamiqueID) ;
        }
        catch (SQLException ex) { 
            System.out.println ("Erreur ta mère");            
            ex.printStackTrace();        
        }
        return Idole;
        
    }
    
    //Récupérer la recette
    public ResultSet getRecette(Dialogue dial){
      
        dial = dial;
        
        try{
        Statement stm = conn.createStatement(); 
        Recette = stm.executeQuery("SELECT * FROM ressource_idole WHERE ID_idle ="+dial.IdIdole);
        System.out.println(Recette);
        //dial.model.getDataVector().clear();
        
        String ID = "";
        String Nbre = "";
        int x = 0;
        
            while (Recette.next()){ 
//                BDD bdd = new BDD();
//                bdd.getConnection();
                ID = Recette.getString("ID_Ress");
                Nbre = Recette.getString("nbre"); 
//                bdd.getRessource(ID, x);
                
                //Retrouver le nom et le type
                stm = conn.createStatement();
                Ressource = stm.executeQuery("SELECT * FROM ressource WHERE ID_ress =" + ID);
                System.out.println(Ressource);
               
                //Ecrire les données
                while (Ressource.next()){ 
                    dial.strIngr[x] = Ressource.getString("Nom_ress");
                    dial.strNbre[x] = Nbre;
                    dial.strType[x] = Ressource.getString("Type_ress");
                }
                
            x = x+1;
            }
        }
        catch (SQLException ex) { 
            System.out.println ("Erreur ta mère");            
            ex.printStackTrace();        
        }
        return Idole;
    }
}
