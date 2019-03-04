/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DofusCraftHelper;

import DofusCraftHelper.Accueil;
import DofusCraftHelper.Dialogue;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author antoi
 */
public class BDD {
 
    public static Connection conn = null;
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_CONNECTION = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "";
    private static final String DB_PASSWORD = "";
    
    ////////////////////////////////////////////////////////////////////////////
    private static Connection getDBConnection() {
        Connection conn = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            conn = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    
    //Récupération de toutes les idoles
    public ResultSet getIdIdole(Accueil fen, String selecteur, String orderBy) throws SQLException {
        Connection connection = getDBConnection();
         Statement stm = null;
        ResultSet rs = null;
        
        try{
        connection.setAutoCommit(false);
        stm = connection.createStatement();
        rs = stm.executeQuery("SELECT * FROM idole" + selecteur + orderBy);
        
        String id = "";
        String nom = "";
        String lvl = "";
        fen.model.getDataVector().clear();
            while (rs.next()){ 
                id = rs.getString("ID_idle");
                nom = rs.getString("Nom_idle");
                lvl = rs.getString("Lvl_idle");
                                
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
        return rs;
    }
    
    //Récupérer la recette
    public ResultSet getRecette(Dialogue dial){
      
        Connection connection = getDBConnection();
        Statement stm = null;
        ResultSet Recette = null;
        ResultSet Ressource = null;
        
        try{
        connection.setAutoCommit(false);
        stm = connection.createStatement(); 
        
        Recette = stm.executeQuery("SELECT * FROM ressource_idole WHERE ID_idle ="+dial.IdIdole);
        System.out.println(Recette);
        //dial.model.getDataVector().clear();
        
        String ID = "";
        String Nbre = "";
        int x = 0;
        
            try{
                             while (Recette.next()){ 
                Recette.absolute(x+1);
                ID = Recette.getString("ID_Ress");
                Nbre = Recette.getString("nbre"); 
                
                //Retrouver le nom et le type
                Ressource = stm.executeQuery("SELECT * FROM ressource WHERE ID_ress =" + ID);
                System.out.println(Ressource);
               
                //Ecrire les données
                while (Ressource.next()){ 

                    dial.strIngr[x] = Ressource.getString("Nom_ress");
                    dial.strNbre[x] = Nbre;
                    dial.strType[x] = Ressource.getString("Type_ress");
                }
                Recette = stm.executeQuery("SELECT * FROM ressource_idole WHERE ID_idle ="+dial.IdIdole);
            x = x+1;
            }
            }catch (SQLException ex){
                
            }
 
        }
        catch (SQLException ex) { 
            System.out.println ("Erreur ta mère");            
            ex.printStackTrace();        
        }
        return Recette;
    }
    
    //////////////////////////////////////////////////////////////////////////////
    
        public BDD(){
        Connection connection = getDBConnection();
        Statement stmt = null;
        try {
            connection.setAutoCommit(false);
            stmt = connection.createStatement();
            
            //Table idole
            String content1 = read("src/Ressources/Table1_idole.txt");
            stmt.execute(content1);
            
            //Table ressource
            String content2 = read("src/Ressources/Table2_ressource.txt");
            stmt.execute(content2);

            //Table liaison idole - ressource
            String content3 = read("src/Ressources/Table3_recette.txt");
            stmt.execute(content3);
            
            connection.commit();
            
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //connection.close();
        }
    }
     
    private String read(String f) throws FileNotFoundException {
        
        String str = new Scanner(new File(f), "UTF-8").useDelimiter("\\A").next();

          //System.out.println(entireFileText);
          return str;
        }

}

