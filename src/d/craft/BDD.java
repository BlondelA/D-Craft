/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d.craft;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
            
            stmt.execute("CREATE TABLE IF NOT EXISTS `idole` (\n" +
                        "  `ID_idle` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `Nom_idle` varchar(60) NOT NULL,\n" +
                        "  `Lvl_idle` int(11) NOT NULL,\n" +
                        "  PRIMARY KEY (`ID_idle`)\n" +
                        ")");
            
            stmt.execute("INSERT INTO `idole` (`ID_idle`, `Nom_idle`, `Lvl_idle`) VALUES\n" +
                        "(1, 'Aroumb', 80),\n" +
                        "(2, 'Bihilète', 44),\n" +
                        "(3, 'Bihilète Magistrale', 114),\n" +
                        "(4, 'Bihilète Majeure', 74),\n" +
                        "(5, 'Bihilète Mineure', 4),\n" +
                        "(6, 'Binar ', 170),\n" +
                        "(7, 'Boble', 88),\n" +
                        "(8, 'Boble Magistrale', 168),\n" +
                        "(9, 'Boble Majeure', 128),\n" +
                        "(10, 'Boble Mineure', 48),\n" +
                        "(11, 'Buto', 65),\n" +
                        "(12, 'Butor Magistrale', 145),\n" +
                        "(13, 'Butor Majeure', 105),\n" +
                        "(14, 'Butor Mineure', 25),\n" +
                        "(15, 'Cafra', 108),\n" +
                        "(16, 'Cafra Magistrale', 188),\n" +
                        "(17, 'Cafra Majeure', 148),\n" +
                        "(18, 'Cafra Mineure', 68),\n" +
                        "(19, 'Corrode', 127),\n" +
                        "(20, 'Critus', 140),\n" +
                        "(21, 'Dagob', 85),\n" +
                        "(22, 'Dagob Magistrale', 165),\n" +
                        "(23, 'Dagob Majeure', 125),\n" +
                        "(24, 'Dagob Mineure', 45),\n" +
                        "(25, 'Djim', 161),\n" +
                        "(26, 'Domo', 57),\n" +
                        "(27, 'Domo Magistrale', 137),\n" +
                        "(28, 'Domo Majeure', 97),\n" +
                        "(29, 'Domo Mineure', 17),\n" +
                        "(30, 'Dynamo', 41),\n" +
                        "(31, 'Dynamo Magistrale', 121),\n" +
                        "(32, 'Dynamo Majeure', 81),\n" +
                        "(33, 'Dynamo Mineure', 1),\n" +
                        "(34, 'Horize', 75),\n" +
                        "(35, 'Horize Magistrale', 155),\n" +
                        "(36, 'Horize Majeure', 115),\n" +
                        "(37, 'Horize Mineure', 35),\n" +
                        "(38, 'Hoskar ', 120),\n" +
                        "(39, 'Hulhu', 72),\n" +
                        "(40, 'Hulhu Magistrale', 152),\n" +
                        "(41, 'Hulhu Majeure', 112),\n" +
                        "(42, 'Hulhu Mineure', 32),\n" +
                        "(43, 'Korria', 163),\n" +
                        "(44, 'Kyoub', 62),\n" +
                        "(45, 'Kyoub Magistrale', 142),\n" +
                        "(46, 'Kyoub Majeure', 102),\n" +
                        "(47, 'Kyoub Mineure', 22),\n" +
                        "(48, 'Leukide', 90),\n" +
                        "(49, 'Muta', 60),\n" +
                        "(50, 'Nahuatl', 180),\n" +
                        "(51, 'Nékinéko', 110),\n" +
                        "(52, 'Nyan', 100),\n" +
                        "(53, 'Nyoro', 46),\n" +
                        "(54, 'Nyoro Magistrale', 126),\n" +
                        "(55, 'Nyoro Majeure', 86),\n" +
                        "(56, 'Nyoro Mineure', 6),\n" +
                        "(57, 'Ougah', 50),\n" +
                        "(58, 'Pého', 63),\n" +
                        "(59, 'Pého Magistrale', 143),\n" +
                        "(60, 'Pého Majeure', 103),\n" +
                        "(61, 'Pého Mineure', 23),\n" +
                        "(62, 'Pénitent', 123),\n" +
                        "(63, 'Pétunia', 53),\n" +
                        "(64, 'Pétunia Magistrale', 133),\n" +
                        "(65, 'Pétunia Majeure', 93),\n" +
                        "(66, 'Pétunia Mineure', 13),\n" +
                        "(67, 'Pikmi', 77),\n" +
                        "(68, 'Pikmi Magistrale', 157),\n" +
                        "(69, 'Pikmi Majeure', 117),\n" +
                        "(70, 'Pikmi Mineure', 37),\n" +
                        "(71, 'Proxima', 30),\n" +
                        "(72, 'Sak', 96),\n" +
                        "(73, 'Sak Magistrale', 176),\n" +
                        "(74, 'Sak Majeure', 136),\n" +
                        "(75, 'Sak Mineure', 56),\n" +
                        "(76, 'Teleb', 91),\n" +
                        "(77, 'Teleb Magitrale', 171),\n" +
                        "(78, 'Teleb Majeure', 131),\n" +
                        "(79, 'Teleb Mineure', 51),\n" +
                        "(80, 'Ultram', 130),\n" +
                        "(81, 'Vaude', 70),\n" +
                        "(82, 'Yoche', 55),\n" +
                        "(83, 'Yoche Magistrale', 135),\n" +
                        "(84, 'Yoche Majeure', 95),\n" +
                        "(85, 'Yoche Mineure', 15),\n" +
                        "(86, 'Zaihn', 101),\n" +
                        "(87, 'Zaihn Magistrale', 181),\n" +
                        "(88, 'Zaihn Majeure', 141),\n" +
                        "(89, 'Zaihn Mineure', 61)");
            
            stmt.execute("CREATE TABLE IF NOT EXISTS `ressource` (\n" +
                        "  `ID_ress` int(11) NOT NULL AUTO_INCREMENT,\n" +
                        "  `Nom_ress` varchar(60) NOT NULL,\n" +
                        "  `Type_ress` varchar(20) NOT NULL,\n" +
                        "  PRIMARY KEY (`ID_ress`)\n" +
                        ")");
                    
            stmt.execute("INSERT INTO `ressource` (`ID_ress`, `Nom_ress`, `Type_ress`) VALUES\n" +
                        "(12, 'Essence de ben le ripate', 'Alchimie'),\n" +
                        "(11, 'Essence de batofu', 'Alchimie'),\n" +
                        "(10, 'Essence d abraknyde ancestral', 'Alchimie'),\n" +
                        "(9, 'Essence de boostache', 'Alchimie'),\n" +
                        "(349, 'Essence de Kardorim', 'Ressource'),\n" +
                        "(13, 'Essence de blop multicolore royal', 'Alchimie'),\n" +
                        "(14, 'Essence de blop royal', 'Alchimie'),\n" +
                        "(15, 'Essence de bouftou royal', 'Alchimie'),\n" +
                        "(16, 'Essence de bulbig brozeur', 'Alchimie'),\n" +
                        "(17, 'Essence de bworkette', 'Alchimie'),\n" +
                        "(18, 'Essence de capitaine ekarlatte', 'Alchimie'),\n" +
                        "(19, 'Essence de chafer ronin', 'Alchimie'),\n" +
                        "(20, 'Essence de corailleur magistral', 'Alchimie'),\n" +
                        "(21, 'Essence de craqueuleur légendaire', 'Alchimie'),\n" +
                        "(22, 'Essence de crocabulia', 'Alchimie'),\n" +
                        "(23, 'Essence de daigoro', 'Alchimie'),\n" +
                        "(24, 'Essence de dramak', 'Alchimie'),\n" +
                        "(25, 'Essence de forgerons', 'Alchimie'),\n" +
                        "(26, 'Essence de fraktale', 'Alchimie'),\n" +
                        "(27, 'Essence de gelée royale', 'Alchimie'),\n" +
                        "(28, 'Essence de gourlo le terrible', 'Alchimie'),\n" +
                        "(29, 'Essence de haute truche', 'Alchimie'),\n" +
                        "(30, 'Essence de kanigroula', 'Alchimie'),\n" +
                        "(31, 'Essence de kankreblath', 'Alchimie'),\n" +
                        "(32, 'Essence de kanniboule ebil', 'Alchimie'),\n" +
                        "(33, 'Essence de kimbo', 'Alchimie'),\n" +
                        "(34, 'Essence de korriandre', 'Alchimie'),\n" +
                        "(35, 'Essence de koulosse', 'Alchimie'),\n" +
                        "(36, 'Essence de kralamoure géant', 'Alchimie'),\n" +
                        "(37, 'Essence de kwakwa', 'Alchimie'),\n" +
                        "(38, 'Essence de la reine nyée', 'Alchimie'),\n" +
                        "(39, 'Essence de maître corbac', 'Alchimie'),\n" +
                        "(40, 'Essence de maître pandore', 'Alchimie'),\n" +
                        "(41, 'Essence de maléfisk', 'Alchimie'),\n" +
                        "(42, 'Essence de mansot royal', 'Alchimie'),\n" +
                        "(43, 'Essence de meulou', 'Alchimie'),\n" +
                        "(44, 'Essence de minotoror', 'Alchimie'),\n" +
                        "(45, 'Essence de minotot', 'Alchimie'),\n" +
                        "(46, 'Essence de Mob l éponge', 'Alchimie'),\n" +
                        "(47, 'Essence de moon', 'Alchimie'),\n" +
                        "(48, 'Essence de nelween', 'Alchimie'),\n" +
                        "(49, 'Essence de péki péki', 'Alchimie'),\n" +
                        "(50, 'Essence de phossile', 'Alchimie'),\n" +
                        "(51, 'Essence de rasboul majeur', 'Alchimie'),\n" +
                        "(52, 'Essence de rat blanc', 'Alchimie'),\n" +
                        "(53, 'Essence de rat noir', 'Alchimie'),\n" +
                        "(54, 'Essence de royalmouth', 'Alchimie'),\n" +
                        "(55, 'Essence de scarabosse dorée', 'Alchimie'),\n" +
                        "(56, 'Essence de shin larve', 'Alchimie'),\n" +
                        "(57, 'Essence de skeunk', 'Alchimie'),\n" +
                        "(58, 'Essence de sphincter cell', 'Alchimie'),\n" +
                        "(59, 'Essence de tanukouï san', 'Alchimie'),\n" +
                        "(60, 'Essence de tengu givrefoux', 'Alchimie'),\n" +
                        "(61, 'Essence de tournesol affamé', 'Alchimie'),\n" +
                        "(62, 'Essence de toxoliath', 'Alchimie'),\n" +
                        "(63, 'Essence de Tynril', 'Alchimie'),\n" +
                        "(64, 'Essence de wa wabbit', 'Alchimie'),\n" +
                        "(65, 'Essence de wa wobot', 'Alchimie'),\n" +
                        "(66, 'Essence de xlii', 'Alchimie'),\n" +
                        "(67, 'Essence d obsidiantre', 'Alchimie'),\n" +
                        "(68, 'Essence d ougah', 'Alchimie'),\n" +
                        "(69, 'Essence du chêne mou', 'Alchimie'),\n" +
                        "(70, 'Essence du dragon cochon', 'Alchimie'),\n" +
                        "(71, 'Essence du tofu royal', 'Alchimie'),\n" +
                        "(72, 'Feuille de tournesol sauvage', 'Alchimie'),\n" +
                        "(73, 'Feuille de tronknyde', 'Alchimie'),\n" +
                        "(74, 'Fleur de blopignon', 'Alchimie'),\n" +
                        "(75, 'Fleur de bulbiflore', 'Alchimie'),\n" +
                        "(76, 'Fleur de gloutoblop', 'Alchimie'),\n" +
                        "(77, 'Ortie', 'Alchimie'),\n" +
                        "(78, 'Bois de bambouto sacré', 'Bûcheron'),\n" +
                        "(79, 'Bois de frêne', 'Bûcheron'),\n" +
                        "(80, 'Bourgeon de fourbasse', 'Bûcheron'),\n" +
                        "(81, 'Bourgeon de l abraknyde sombre irascible', 'Bûcheron'),\n" +
                        "(82, 'Ecorce d abrazif', 'Bûcheron'),\n" +
                        "(83, 'Ecorce de brouture', 'Bûcheron'),\n" +
                        "(84, 'Ecorce de champaknyde', 'Bûcheron'),\n" +
                        "(85, 'Ecorce de chiendent', 'Bûcheron'),\n" +
                        "(86, 'Ecorce de floribonde', 'Bûcheron'),\n" +
                        "(87, 'Ecorce de fourbasse', 'Bûcheron'),\n" +
                        "(88, 'Ecorce de Liroye Merline', 'Bûcheron'),\n" +
                        "(89, 'Ecorce magique de bulbiflore', 'Bûcheron'),\n" +
                        "(90, 'Racine d abraknyde sombre', 'Bûcheron'),\n" +
                        "(91, 'Racine d abraknyde vénérable', 'Bûcheron'),\n" +
                        "(92, 'Racine de bulbambou', 'Bûcheron'),\n" +
                        "(93, 'Racine de bulbig', 'Bûcheron'),\n" +
                        "(94, 'Racine de bulbuisson', 'Bûcheron'),\n" +
                        "(95, 'Racine de fourbasse', 'Bûcheron'),\n" +
                        "(96, 'Racine de tronkoblop', 'Bûcheron'),\n" +
                        "(97, 'Racine magique de bambouto', 'Bûcheron'),\n" +
                        "(98, 'Racine magique de bambouto sacré', 'Bûcheron'),\n" +
                        "(99, 'Souche de l abrakleur clair', 'Bûcheron'),\n" +
                        "(100, 'Substat de forêt vierge', 'Bûcheron'),\n" +
                        "(101, 'Substrat de bocage', 'Bûcheron'),\n" +
                        "(102, 'Substrat de bosquet', 'Bûcheron'),\n" +
                        "(103, 'Substrat de buisson', 'Bûcheron'),\n" +
                        "(104, 'Substrat de fascine', 'Bûcheron'),\n" +
                        "(105, 'Substrat de forêt', 'Bûcheron'),\n" +
                        "(106, 'Substrat de forêt primaire', 'Bûcheron'),\n" +
                        "(107, 'Substrat de fourré', 'Bûcheron'),\n" +
                        "(108, 'Substrat de futale', 'Bûcheron'),\n" +
                        "(109, 'Tronc de kokoko', 'Bûcheron'),\n" +
                        "(110, 'Galet brasillant', 'Façonneur'),\n" +
                        "(111, 'Blé', 'Paysan'),\n" +
                        "(112, 'Goujon', 'Pêcheur'),\n" +
                        "(113, 'Carniflore', 'Plante'),\n" +
                        "(114, 'Aluminite', 'Mineur'),\n" +
                        "(115, 'Bakélélite', 'Mineur'),\n" +
                        "(116, 'Ebonite', 'Mineur'),\n" +
                        "(117, 'Fer', 'Mineur'),\n" +
                        "(118, 'Ferrite', 'Mineur'),\n" +
                        "(119, 'Kouartz', 'Mineur'),\n" +
                        "(120, 'Kriptonite', 'Mineur'),\n" +
                        "(121, 'Magnésite', 'Mineur'),\n" +
                        "(122, 'Pépite', 'Mineur'),\n" +
                        "(123, 'Pyrute', 'Mineur'),\n" +
                        "(124, 'Rutile', 'Mineur'),\n" +
                        "(125, 'Tourmaline', 'Mineur'),\n" +
                        "(126, 'Aile atrophiée de tofu dodu', 'Ressource'),\n" +
                        "(127, 'Aile de bitouf des plaines', 'Ressource'),\n" +
                        "(128, 'Aile de dragodinde', 'Ressource'),\n" +
                        "(129, 'Aile de dragodinde dorée', 'Ressource'),\n" +
                        "(130, 'Aile de dragoeuf volant', 'Ressource'),\n" +
                        "(131, 'Aile de gruche', 'Ressource'),\n" +
                        "(132, 'Aile de mansobèse', 'Ressource'),\n" +
                        "(133, 'Amygdales du bitouf sombre', 'Ressource'),\n" +
                        "(134, 'Antenne de trésantène', 'Ressource'),\n" +
                        "(135, 'Arakne majeure morte', 'Ressource'),\n" +
                        "(136, 'Arête géante du shamansot', 'Ressource'),\n" +
                        "(137, 'Bandeau de black tiwabbit', 'Ressource'),\n" +
                        "(138, 'Bandelette de tromblion', 'Ressource'),\n" +
                        "(139, 'Bave du kaskargo', 'Ressource'),\n" +
                        "(140, 'Bec de truchon', 'Ressource'),\n" +
                        "(141, 'Bec du kido', 'Ressource'),\n" +
                        "(142, 'Bec de tofu', 'Ressource'),\n" +
                        "(143, 'Botte trouée de bwork', 'Ressource'),\n" +
                        "(144, 'Bouclier zoth', 'Ressource'),\n" +
                        "(145, 'Bouée de fantomalamère', 'Ressource'),\n" +
                        "(146, 'Bougie du mineur sombre', 'Ressource'),\n" +
                        "(147, 'Boulon de cybwork', 'Ressource'),\n" +
                        "(148, 'Boulon de wabbit', 'Ressource'),\n" +
                        "(149, 'Bractée de chiendent', 'Ressource'),\n" +
                        "(150, 'Braguette du maître zoth', 'Ressource'),\n" +
                        "(151, 'Broderie de malléfisk', 'Ressource'),\n" +
                        "(152, 'Caleçon bleu', 'Ressource'),\n" +
                        "(153, 'Caleçon de cybwork', 'Ressource'),\n" +
                        "(154, 'Calice de fécorce', 'Ressource'),\n" +
                        "(155, 'Calumet du zoth', 'Ressource'),\n" +
                        "(156, 'Canine de félygiène', 'Ressource'),\n" +
                        "(157, 'Canine du mulou', 'Ressource'),\n" +
                        "(158, 'Carapace de scaratos', 'Ressource'),\n" +
                        "(159, 'Carpelle de brouture', 'Ressource'),\n" +
                        "(160, 'Casque de wobot', 'Ressource'),\n" +
                        "(161, 'Casque du chafer primitif', 'Ressource'),\n" +
                        "(162, 'Cawotte transgénique', 'Ressource'),\n" +
                        "(163, 'Chaîne de l''ouginak', 'Ressource'),\n" +
                        "(164, 'Chaîne de panthégros', 'Ressource'),\n" +
                        "(165, 'Chaînes brisées', 'Ressource'),\n" +
                        "(166, 'Cheveux d''alhyène', 'Ressource'),\n" +
                        "(167, 'Chicot du flib', 'Ressource'),\n" +
                        "(168, 'Coccyx du corailleur', 'Ressource'),\n" +
                        "(169, 'Coco du bitouf aérien', 'Ressource'),\n" +
                        "(170, 'Coco du bitouf des plaines', 'Ressource'),\n" +
                        "(171, 'Coco du bitouf sombre', 'Ressource'),\n" +
                        "(172, 'Coeur d''ouginak', 'Ressource'),\n" +
                        "(173, 'Collier cassé d''ouginak', 'Ressource'),\n" +
                        "(174, 'Colonne vertébrale', 'Ressource'),\n" +
                        "(175, 'Coquille de dragoss blanc', 'Ressource'),\n" +
                        "(176, 'Coquille de dragoss doré', 'Ressource'),\n" +
                        "(177, 'Coquille de dragoss noir', 'Ressource'),\n" +
                        "(178, 'Coquille de dragoss saphir', 'Ressource'),\n" +
                        "(179, 'Coquille de harpirate', 'Ressource'),\n" +
                        "(180, 'Coquille de kaskargo', 'Ressource'),\n" +
                        "(181, 'Coquille du fantimonier', 'Ressource'),\n" +
                        "(182, 'Corde de boursoin', 'Ressource'),\n" +
                        "(183, 'Corde du fancrôme', 'Ressource'),\n" +
                        "(184, 'Corne de berserkoffre', 'Ressource'),\n" +
                        "(185, 'Corne de dragoss blanc', 'Ressource'),\n" +
                        "(186, 'Corne de rhinoféroce', 'Ressource'),\n" +
                        "(187, 'Corne du boufcoul', 'Ressource'),\n" +
                        "(188, 'Crâne de wabbit squelette', 'Ressource'),\n" +
                        "(189, 'Crinière d''orfélin', 'Ressource'),\n" +
                        "(190, 'Croupion du truchmuche', 'Ressource'),\n" +
                        "(191, 'Dent de bourbassingue', 'Ressource'),\n" +
                        "(192, 'Dent de crocodaille', 'Ressource'),\n" +
                        "(193, 'Dent de dragodinde', 'Ressource'),\n" +
                        "(194, 'Dent de fantômat', 'Ressource'),\n" +
                        "(195, 'Dent de gargantûl', 'Ressource'),\n" +
                        "(196, 'Dent de larve émeraude', 'Ressource'),\n" +
                        "(197, 'Dent de larve saphir', 'Ressource'),\n" +
                        "(198, 'Dent de molette', 'Ressource'),\n" +
                        "(199, 'Dent magique de cooleuvre', 'Ressource'),\n" +
                        "(200, 'Duvet de truchon', 'Ressource'),\n" +
                        "(201, 'Duvet du mamansot', 'Ressource'),\n" +
                        "(202, 'Ecaille de harpirate', 'Ressource'),\n" +
                        "(203, 'Ecaille poisseuse', 'Ressource'),\n" +
                        "(204, 'Echasse de molette', 'Ressource'),\n" +
                        "(205, 'Epine de champ champ', 'Ressource'),\n" +
                        "(206, 'Estomac de Black wo wabbit', 'Ressource'),\n" +
                        "(207, 'Etamine de floribonde', 'Ressource'),\n" +
                        "(208, 'Etoffe de dok alako', 'Ressource'),\n" +
                        "(209, 'Etoffe de kaniglou', 'Ressource'),\n" +
                        "(210, 'Etoffe de ouassingue', 'Ressource'),\n" +
                        "(211, 'Etoffe de rat bougri', 'Ressource'),\n" +
                        "(212, 'Etoffe de vigie pirate', 'Ressource'),\n" +
                        "(213, 'Etoffe de zoth', 'Ressource'),\n" +
                        "(214, 'Etoffe du wabbit', 'Ressource'),\n" +
                        "(215, 'Flamme spectrale', 'Ressource'),\n" +
                        "(216, 'Foulard du milimaître', 'Ressource'),\n" +
                        "(217, 'Foulard du sparo', 'Ressource'),\n" +
                        "(218, 'Fragment d''os', 'Ressource'),\n" +
                        "(219, 'Furoncle de la mama bwork', 'Ressource'),\n" +
                        "(220, 'Glandes de truchtine', 'Ressource'),\n" +
                        "(221, 'Graine de la discorde', 'Ressource'),\n" +
                        "(222, 'Graine de l''abrakleur sombre', 'Ressource'),\n" +
                        "(223, 'Graine de pavot', 'Ressource'),\n" +
                        "(224, 'Graine de tournesol sauvage', 'Ressource'),\n" +
                        "(225, 'Grelot', 'Ressource'),\n" +
                        "(226, 'Griffe de félygiène', 'Ressource'),\n" +
                        "(227, 'Houpette de koalak sanguin', 'Ressource'),\n" +
                        "(228, 'Houpette de vilain petit tofu', 'Ressource'),\n" +
                        "(229, 'Ivoire', 'Ressource'),\n" +
                        "(230, 'Kokolait', 'Ressource'),\n" +
                        "(231, 'Laine de dardalaine', 'Ressource'),\n" +
                        "(232, 'Laine du boufcoul', 'Ressource'),\n" +
                        "(233, 'Laine du trool furieux', 'Ressource'),\n" +
                        "(234, 'Lamelle de mérulette', 'Ressource'),\n" +
                        "(235, 'Langue de champodonte', 'Ressource'),\n" +
                        "(236, 'Langue de mimikado', 'Ressource'),\n" +
                        "(237, 'Langue de truchmuche', 'Ressource'),\n" +
                        "(238, 'Mâchoire de rib', 'Ressource'),\n" +
                        "(239, 'Mâchoire du chafer draugr', 'Ressource'),\n" +
                        "(240, 'Maillot de corps de barbroussa', 'Ressource'),\n" +
                        "(241, 'Manubrium de wolvero', 'Ressource'),\n" +
                        "(242, 'Masque brisé d''araknotron', 'Ressource'),\n" +
                        "(243, 'Morpion de truchideur', 'Ressource'),\n" +
                        "(244, 'Moustache de mufafah', 'Ressource'),\n" +
                        "(245, 'Moustache de tiwabbit', 'Ressource'),\n" +
                        "(246, 'Noeud de l''abrakleur clair', 'Ressource'),\n" +
                        "(247, 'Oeil de branche invocatrice', 'Ressource'),\n" +
                        "(248, 'Oeil de branche soignante', 'Ressource'),\n" +
                        "(249, 'Oeil de rat masseur', 'Ressource'),\n" +
                        "(250, 'Oeil de saltik', 'Ressource'),\n" +
                        "(251, 'Oeil de sapeur', 'Ressource'),\n" +
                        "(252, 'Oeil de torve de fricochère', 'Ressource'),\n" +
                        "(253, 'Oeil de truchtine', 'Ressource'),\n" +
                        "(254, 'Oeil de vigie pirate', 'Ressource'),\n" +
                        "(255, 'Oeil d''ouginak', 'Ressource'),\n" +
                        "(256, 'Oeil du mulou', 'Ressource'),\n" +
                        "(257, 'oeuf de tofu', 'Ressource'),\n" +
                        "(258, 'Oreille d''apériglours', 'Ressource'),\n" +
                        "(259, 'Oreille de bouledogre', 'Ressource'),\n" +
                        "(260, 'Oreille de cochon de lait', 'Ressource'),\n" +
                        "(261, 'Oreille de grand pa wabbit', 'Ressource'),\n" +
                        "(262, 'Oreille de kaniglou', 'Ressource'),\n" +
                        "(263, 'Oreille de kanigrou', 'Ressource'),\n" +
                        "(264, 'Oreille de soryo givrefoux', 'Ressource'),\n" +
                        "(265, 'Oreille de tiwabbit', 'Ressource'),\n" +
                        "(266, 'Oreille percée du fricochère', 'Ressource'),\n" +
                        "(267, 'Oreilles de croc gland', 'Ressource'),\n" +
                        "(268, 'Os de fantôme de léopardo', 'Ressource'),\n" +
                        "(269, 'Os de fantôme de soryo firefoux', 'Ressource'),\n" +
                        "(270, 'Os de mama koalak', 'Ressource'),\n" +
                        "(271, 'Os de wabbit squelette', 'Ressource'),\n" +
                        "(272, 'Os invisible du chafer invisible', 'Ressource'),\n" +
                        "(273, 'Osselet de black wabbit squelette', 'Ressource'),\n" +
                        "(274, 'Patte d''araknawa', 'Ressource'),\n" +
                        "(275, 'Patte de black wabbit', 'Ressource'),\n" +
                        "(276, 'Patte de gruche', 'Ressource'),\n" +
                        "(277, 'Patte de soryo givrefoux', 'Ressource'),\n" +
                        "(278, 'Patte de wabbit', 'Ressource'),\n" +
                        "(279, 'Paupière d''étoile', 'Ressource'),\n" +
                        "(280, 'Peau de chamane d''égoutant', 'Ressource'),\n" +
                        "(281, 'Peau de cochon de farle', 'Ressource'),\n" +
                        "(282, 'Peau de cooleuvre', 'Ressource'),\n" +
                        "(283, 'Peau de don dorgan', 'Ressource'),\n" +
                        "(284, 'Peau de don duss ang', 'Ressource'),\n" +
                        "(285, 'Peau de drakoalak', 'Ressource'),\n" +
                        "(286, 'Peau de maho firefoux', 'Ressource'),\n" +
                        "(287, 'Peau de mandrine ', 'Ressource'),\n" +
                        "(288, 'Peau de métaphorreur', 'Ressource'),\n" +
                        "(289, 'Peau de pandikaze', 'Ressource'),\n" +
                        "(290, 'Peau de serpentin', 'Ressource'),\n" +
                        "(291, 'Peau de soryo firefoux', 'Ressource'),\n" +
                        "(292, 'Péroné du marôdeur', 'Ressource'),\n" +
                        "(293, 'Pic de dragodinde dorée', 'Ressource'),\n" +
                        "(294, 'Pince de crabe', 'Ressource'),\n" +
                        "(295, 'Pince du fancrôme', 'Ressource'),\n" +
                        "(296, 'Plume de Gobvious', 'Ressource'),\n" +
                        "(297, 'Plume de serpiplume', 'Ressource'),\n" +
                        "(298, 'Plume de truchideur', 'Ressource'),\n" +
                        "(299, 'Plume du timansot', 'Ressource'),\n" +
                        "(300, 'Poil de gamino', 'Ressource'),\n" +
                        "(301, 'Poil de rat d''égoutant', 'Ressource'),\n" +
                        "(302, 'Poil de smilomouth', 'Ressource'),\n" +
                        "(303, 'Poil de soryo firefoux', 'Ressource'),\n" +
                        "(304, 'Poils de barbe de bwork mage', 'Ressource'),\n" +
                        "(305, 'Poils de black tiwabbit', 'Ressource'),\n" +
                        "(306, 'Poils de guerrier koalak', 'Ressource'),\n" +
                        "(307, 'Poils de koalak forestier', 'Ressource'),\n" +
                        "(308, 'Poils de léopardo', 'Ressource'),\n" +
                        "(309, 'Poils de maho firefoux', 'Ressource'),\n" +
                        "(310, 'Poils de pandikaze', 'Ressource'),\n" +
                        "(311, 'Poils de pandule', 'Ressource'),\n" +
                        "(312, 'Poils de wo wabbit', 'Ressource'),\n" +
                        "(313, 'Poupée vaudou archer', 'Ressource'),\n" +
                        "(314, 'Poupée vaudou jav', 'Ressource'),\n" +
                        "(315, 'Poupée vaudou sarbak', 'Ressource'),\n" +
                        "(316, 'Protection de la dragueuse', 'Ressource'),\n" +
                        "(317, 'Puces sauteuses', 'Ressource'),\n" +
                        "(318, 'Queue de wolvero', 'Ressource'),\n" +
                        "(319, 'Queue de yomi givrefoux', 'Ressource'),\n" +
                        "(320, 'Queue du boufmouth légendaire', 'Ressource'),\n" +
                        "(321, 'Queue du fantomalamère', 'Ressource'),\n" +
                        "(322, 'Queue du fu mansot', 'Ressource'),\n" +
                        "(323, 'Queue du mulou', 'Ressource'),\n" +
                        "(324, 'Radius d''ouilleur', 'Ressource'),\n" +
                        "(325, 'Rembourrage de meupette', 'Ressource'),\n" +
                        "(326, 'Rhum Fourbe', 'Ressource'),\n" +
                        "(327, 'Rotule du disciple zoth', 'Ressource'),\n" +
                        "(328, 'Sablier miniature', 'Ressource'),\n" +
                        "(329, 'Sacoche de kartouche', 'Ressource'),\n" +
                        "(330, 'Sang d''oni ', 'Ressource'),\n" +
                        "(331, 'Selle en cuir', 'Ressource'),\n" +
                        "(332, 'Sépale de nerbe', 'Ressource'),\n" +
                        "(333, 'Serviette de plage', 'Ressource'),\n" +
                        "(334, 'Slip d''elsoummo', 'Ressource'),\n" +
                        "(335, 'Slip du bwork archer', 'Ressource'),\n" +
                        "(336, 'Soie baveuse', 'Ressource'),\n" +
                        "(337, 'Sourcil de tronknyde', 'Ressource'),\n" +
                        "(338, 'Souris verte', 'Ressource'),\n" +
                        "(339, 'Sporme de champ champ', 'Ressource'),\n" +
                        "(340, 'Sternum de chachachovage', 'Ressource'),\n" +
                        "(341, 'Sueur froide de fantôme', 'Ressource'),\n" +
                        "(342, 'Tignasse de kanihilan', 'Ressource'),\n" +
                        "(343, 'Tissu invisible', 'Ressource'),\n" +
                        "(344, 'Tissu sombre', 'Ressource'),\n" +
                        "(345, 'Touffe de cheveux blancs', 'Ressource'),\n" +
                        "(346, 'Tresse de poolay', 'Ressource'),\n" +
                        "(347, 'Trompe de tromperelle', 'Ressource'),\n" +
                        "(348, 'Ulna de solfatoré', 'Ressource'),\n" +
                        "(350, 'Essence de Chêne Mou', 'Bûcheron'),\n" +
                        "(351, 'Essence des Forgerons', 'Ressource'),\n" +
                        "(352, 'Essence du Rat Noir', 'Ressource'),\n" +
                        "(353, 'Essence de Craqueleur Légendaire', 'Ressource'),\n" +
                        "(354, 'Essence de Kanniboul Ebil', 'Ressource'),\n" +
                        "(355, 'Essence du Capitaine Ekarlatte', 'Ressource'),\n" +
                        "(356, 'Essence de Tofu Royal', 'Ressource'),\n" +
                        "(357, 'Essence du Mulou', 'Alchimie'),\n" +
                        "(358, 'Essence de Wo Wabbit', 'Alchimie'),\n" +
                        "(359, 'Kobalite', 'Ressource'),\n" +
                        "(360, 'Substrat de Forêt Vierge', 'Ressource'),\n" +
                        "(361, 'Oreille de Foufayteur', 'Ressource'),\n" +
                        "(362, 'Camiflore', 'Ressource'),\n" +
                        "(363, 'Tatouage de Mauvais garçon', 'Ressource'),\n" +
                        "(364, 'Poils de Rat d''égoutant', 'Ressource'),\n" +
                        "(365, 'Poupée Vaudou Ark', 'Ressource'),\n" +
                        "(366, 'Pétale Magique de Tournesol Affamé', 'Ressource'),\n" +
                        "(367, 'Oreille de Wabbit', 'Ressource'),\n" +
                        "(368, 'Botte Trouée de Mégabwork', 'Ressource'),\n" +
                        "(369, 'Coeur de Buveur', 'Ressource'),\n" +
                        "(370, 'Oreille de Medibwork', 'Ressource'),\n" +
                        "(371, 'Corne de Boufcool', 'Ressource'),\n" +
                        "(372, 'Noeul Mort', 'Ressource');");

            stmt.execute("CREATE TABLE IF NOT EXISTS `ressource_idole` (\n" +
                        "  `ID_idle` int(11) NOT NULL,\n" +
                        "  `ID_ress` int(11) DEFAULT NULL,\n" +
                        "  `nbre` int(11) DEFAULT NULL\n" +
                        ")");
            
            stmt.execute("INSERT INTO `ressource_idole` (`ID_idle`, `ID_ress`, `nbre`) VALUES\n" +
                        "(1, 358, 2),\n" +
                        "(2, 9, 2),\n" +
                        "(3, 39, 2),\n" +
                        "(4, 48, 2),\n" +
                        "(5, 349, 2),\n" +
                        "(6, 66, 2),\n" +
                        "(7, 65, 2),\n" +
                        "(8, 33, 2),\n" +
                        "(9, 44, 2),\n" +
                        "(10, 11, 2),\n" +
                        "(11, 354, 2),\n" +
                        "(12, 42, 2),\n" +
                        "(13, 24, 2),\n" +
                        "(14, 61, 2),\n" +
                        "(15, 35, 2),\n" +
                        "(16, 62, 2),\n" +
                        "(17, 350, 2),\n" +
                        "(18, 14, 2),\n" +
                        "(19, 22, 2),\n" +
                        "(20, 42, 2),\n" +
                        "(21, 65, 2),\n" +
                        "(22, 30, 2),\n" +
                        "(23, 40, 2),\n" +
                        "(24, 19, 2),\n" +
                        "(25, 45, 2),\n" +
                        "(26, 351, 2),\n" +
                        "(27, 59, 2),\n" +
                        "(28, 70, 2),\n" +
                        "(29, 61, 2),\n" +
                        "(30, 31, 2),\n" +
                        "(31, 57, 2),\n" +
                        "(32, 23, 2),\n" +
                        "(33, 111, 1),\n" +
                        "(34, 28, 2),\n" +
                        "(35, 58, 2),\n" +
                        "(36, 352, 2),\n" +
                        "(37, 15, 2),\n" +
                        "(38, 13, 2),\n" +
                        "(39, 353, 2),\n" +
                        "(40, 50, 2),\n" +
                        "(41, 51, 2),\n" +
                        "(42, 15, 2),\n" +
                        "(43, 33, 2),\n" +
                        "(44, 14, 2),\n" +
                        "(45, 49, 2),\n" +
                        "(46, 47, 2),\n" +
                        "(47, 61, 2),\n" +
                        "(48, 38, 2),\n" +
                        "(49, 354, 2),\n" +
                        "(50, 34, 2),\n" +
                        "(51, 355, 2),\n" +
                        "(52, 41, 2),\n" +
                        "(53, 55, 2),\n" +
                        "(54, 356, 2),\n" +
                        "(55, 65, 2),\n" +
                        "(56, 46, 2),\n" +
                        "(57, 37, 2),\n" +
                        "(58, 27, 2),\n" +
                        "(59, 350, 2),\n" +
                        "(60, 24, 2),\n" +
                        "(61, 61, 2),\n" +
                        "(62, 54, 2),\n" +
                        "(63, 20, 2),\n" +
                        "(64, 59, 2),\n" +
                        "(65, 38, 2),\n" +
                        "(66, 46, 2),\n" +
                        "(67, 353, 2),\n" +
                        "(68, 12, 2),\n" +
                        "(69, 52, 2),\n" +
                        "(70, 15, 2),\n" +
                        "(71, 15, 2),\n" +
                        "(72, 10, 2),\n" +
                        "(73, 68, 2),\n" +
                        "(74, 29, 2),\n" +
                        "(75, 17, 2),\n" +
                        "(76, 10, 2),\n" +
                        "(77, 60, 2),\n" +
                        "(78, 29, 2),\n" +
                        "(79, 56, 2),\n" +
                        "(80, 26, 2),\n" +
                        "(81, 28, 2),\n" +
                        "(82, 16, 2),\n" +
                        "(83, 26, 2),\n" +
                        "(84, 38, 2),\n" +
                        "(85, 46, 2),\n" +
                        "(86, 357, 2),\n" +
                        "(87, 36, 2),\n" +
                        "(88, 63, 2),\n" +
                        "(89, 64, 2),\n" +
                        "(1, 104, 2),\n" +
                        "(2, 116, 4),\n" +
                        "(3, 102, 2),\n" +
                        "(4, 121, 4),\n" +
                        "(5, 118, 4),\n" +
                        "(6, 106, 2),\n" +
                        "(7, 115, 4),\n" +
                        "(8, 106, 2),\n" +
                        "(9, 120, 4),\n" +
                        "(10, 101, 2),\n" +
                        "(11, 108, 2),\n" +
                        "(12, 105, 2),\n" +
                        "(13, 119, 4),\n" +
                        "(14, 114, 4),\n" +
                        "(15, 119, 4),\n" +
                        "(16, 123, 4),\n" +
                        "(17, 105, 2),\n" +
                        "(18, 108, 2),\n" +
                        "(19, 107, 2),\n" +
                        "(20, 105, 2),\n" +
                        "(21, 104, 2),\n" +
                        "(22, 124, 4),\n" +
                        "(23, 107, 2),\n" +
                        "(24, 101, 2),\n" +
                        "(25, 124, 4),\n" +
                        "(26, 101, 2),\n" +
                        "(27, 120, 4),\n" +
                        "(28, 104, 2),\n" +
                        "(29, 103, 2),\n" +
                        "(30, 101, 2),\n" +
                        "(31, 107, 2),\n" +
                        "(32, 115, 4),\n" +
                        "(33, 77, 1),\n" +
                        "(34, 108, 2),\n" +
                        "(35, 105, 2),\n" +
                        "(36, 119, 4),\n" +
                        "(37, 103, 2),\n" +
                        "(38, 120, 4),\n" +
                        "(39, 108, 2),\n" +
                        "(40, 359, 4),\n" +
                        "(41, 119, 4),\n" +
                        "(42, 114, 4),\n" +
                        "(43, 106, 2),\n" +
                        "(44, 121, 4),\n" +
                        "(45, 105, 2),\n" +
                        "(46, 119, 4),\n" +
                        "(47, 114, 4),\n" +
                        "(48, 104, 2),\n" +
                        "(49, 121, 4),\n" +
                        "(50, 360, 2),\n" +
                        "(51, 102, 2),\n" +
                        "(52, 119, 4),\n" +
                        "(53, 116, 4),\n" +
                        "(54, 120, 4),\n" +
                        "(55, 115, 4),\n" +
                        "(56, 118, 4),\n" +
                        "(57, 116, 4),\n" +
                        "(58, 108, 2),\n" +
                        "(59, 359, 4),\n" +
                        "(60, 102, 2),\n" +
                        "(61, 103, 2),\n" +
                        "(62, 120, 4),\n" +
                        "(63, 116, 4),\n" +
                        "(64, 107, 2),\n" +
                        "(65, 104, 2),\n" +
                        "(66, 118, 4),\n" +
                        "(67, 121, 4),\n" +
                        "(68, 359, 4),\n" +
                        "(69, 102, 2),\n" +
                        "(70, 114, 4),\n" +
                        "(71, 103, 2),\n" +
                        "(72, 115, 4),\n" +
                        "(73, 123, 4),\n" +
                        "(74, 107, 2),\n" +
                        "(75, 116, 4),\n" +
                        "(76, 115, 4),\n" +
                        "(77, 124, 4),\n" +
                        "(78, 120, 4),\n" +
                        "(79, 101, 2),\n" +
                        "(80, 107, 2),\n" +
                        "(81, 121, 4),\n" +
                        "(82, 101, 2),\n" +
                        "(83, 120, 4),\n" +
                        "(84, 115, 4),\n" +
                        "(85, 103, 2),\n" +
                        "(86, 102, 2),\n" +
                        "(87, 123, 4),\n" +
                        "(88, 359, 4),\n" +
                        "(89, 108, 2),\n" +
                        "(1, 148, 10),\n" +
                        "(2, 221, 10),\n" +
                        "(3, 110, 1),\n" +
                        "(4, 361, 10),\n" +
                        "(5, 294, 10),\n" +
                        "(6, 110, 3),\n" +
                        "(7, 110, 1),\n" +
                        "(8, 110, 3),\n" +
                        "(9, 110, 2),\n" +
                        "(10, 92, 10),\n" +
                        "(11, 314, 10),\n" +
                        "(12, 110, 2),\n" +
                        "(13, 110, 1),\n" +
                        "(14, 343, 10),\n" +
                        "(15, 110, 1),\n" +
                        "(16, 110, 3),\n" +
                        "(17, 110, 2),\n" +
                        "(18, 240, 10),\n" +
                        "(19, 110, 2),\n" +
                        "(20, 110, 2),\n" +
                        "(21, 110, 1),\n" +
                        "(22, 110, 3),\n" +
                        "(23, 110, 2),\n" +
                        "(24, 97, 1),\n" +
                        "(25, 110, 3),\n" +
                        "(26, 278, 10),\n" +
                        "(27, 110, 2),\n" +
                        "(28, 110, 1),\n" +
                        "(29, 333, 10),\n" +
                        "(30, 225, 1),\n" +
                        "(31, 110, 2),\n" +
                        "(32, 110, 1),\n" +
                        "(33, 79, 1),\n" +
                        "(34, 173, 1),\n" +
                        "(35, 110, 2),\n" +
                        "(36, 110, 1),\n" +
                        "(37, 229, 10),\n" +
                        "(38, 110, 2),\n" +
                        "(39, 362, 13),\n" +
                        "(40, 110, 2),\n" +
                        "(41, 110, 1),\n" +
                        "(42, 146, 1),\n" +
                        "(43, 110, 3),\n" +
                        "(44, 282, 10),\n" +
                        "(45, 110, 2),\n" +
                        "(46, 110, 1),\n" +
                        "(47, 363, 10),\n" +
                        "(48, 110, 1),\n" +
                        "(49, 364, 10),\n" +
                        "(50, 110, 3),\n" +
                        "(51, 110, 1),\n" +
                        "(52, 110, 1),\n" +
                        "(53, 161, 10),\n" +
                        "(54, 110, 2),\n" +
                        "(55, 110, 1),\n" +
                        "(56, 257, 10),\n" +
                        "(57, 89, 1),\n" +
                        "(58, 365, 10),\n" +
                        "(59, 110, 2),\n" +
                        "(60, 110, 1),\n" +
                        "(61, 366, 1),\n" +
                        "(62, 110, 2),\n" +
                        "(63, 210, 10),\n" +
                        "(64, 110, 2),\n" +
                        "(65, 110, 1),\n" +
                        "(66, 205, 10),\n" +
                        "(67, 271, 10),\n" +
                        "(68, 110, 2),\n" +
                        "(69, 110, 1),\n" +
                        "(70, 238, 10),\n" +
                        "(71, 345, 1),\n" +
                        "(72, 110, 1),\n" +
                        "(73, 110, 3),\n" +
                        "(74, 110, 2),\n" +
                        "(75, 192, 10),\n" +
                        "(76, 110, 1),\n" +
                        "(77, 110, 3),\n" +
                        "(78, 110, 2),\n" +
                        "(79, 367, 10),\n" +
                        "(80, 110, 2),\n" +
                        "(81, 167, 10),\n" +
                        "(82, 310, 10),\n" +
                        "(83, 110, 2),\n" +
                        "(84, 110, 1),\n" +
                        "(85, 279, 10),\n" +
                        "(86, 110, 1),\n" +
                        "(87, 110, 3),\n" +
                        "(88, 110, 2),\n" +
                        "(89, 193, 10),\n" +
                        "(1, 307, 10),\n" +
                        "(2, 290, 10),\n" +
                        "(3, 151, 10),\n" +
                        "(4, 273, 1),\n" +
                        "(5, 142, 10),\n" +
                        "(6, 122, 3000),\n" +
                        "(7, 197, 10),\n" +
                        "(8, 122, 3000),\n" +
                        "(9, 122, 2000),\n" +
                        "(10, 337, 1),\n" +
                        "(11, 341, 10),\n" +
                        "(12, 122, 2000),\n" +
                        "(13, 269, 10),\n" +
                        "(14, 272, 10),\n" +
                        "(15, 141, 1),\n" +
                        "(16, 122, 3000),\n" +
                        "(17, 122, 2000),\n" +
                        "(18, 74, 10),\n" +
                        "(19, 122, 2000),\n" +
                        "(20, 122, 2000),\n" +
                        "(21, 250, 1),\n" +
                        "(22, 122, 3000),\n" +
                        "(23, 122, 2000),\n" +
                        "(24, 369, 1),\n" +
                        "(25, 122, 3000),\n" +
                        "(26, 368, 10),\n" +
                        "(27, 122, 2000),\n" +
                        "(28, 204, 1),\n" +
                        "(29, 203, 10),\n" +
                        "(30, 239, 10),\n" +
                        "(31, 122, 2000),\n" +
                        "(32, 88, 1),\n" +
                        "(33, 117, 1),\n" +
                        "(34, 96, 10),\n" +
                        "(35, 122, 2000),\n" +
                        "(36, 227, 10),\n" +
                        "(37, 344, 10),\n" +
                        "(38, 122, 2000),\n" +
                        "(39, 208, 10),\n" +
                        "(40, 122, 2000),\n" +
                        "(41, 182, 10),\n" +
                        "(42, 370, 10),\n" +
                        "(43, 122, 3000),\n" +
                        "(44, 215, 10),\n" +
                        "(45, 122, 2000),\n" +
                        "(46, 177, 1),\n" +
                        "(47, 224, 10),\n" +
                        "(48, 293, 10),\n" +
                        "(49, 76, 10),\n" +
                        "(50, 122, 3000),\n" +
                        "(51, 134, 10),\n" +
                        "(52, 371, 10),\n" +
                        "(53, 274, 10),\n" +
                        "(54, 122, 2000),\n" +
                        "(55, 94, 10),\n" +
                        "(56, 339, 10),\n" +
                        "(57, 109, 10),\n" +
                        "(58, 261, 10),\n" +
                        "(59, 122, 2000),\n" +
                        "(60, 178, 1),\n" +
                        "(61, 72, 10),\n" +
                        "(62, 122, 2000),\n" +
                        "(63, 335, 10),\n" +
                        "(64, 122, 2000),\n" +
                        "(65, 336, 10),\n" +
                        "(66, 223, 10),\n" +
                        "(67, 214, 1),\n" +
                        "(68, 122, 2000),\n" +
                        "(69, 316, 1),\n" +
                        "(70, 174, 10),\n" +
                        "(71, 146, 1),\n" +
                        "(72, 198, 10),\n" +
                        "(73, 122, 3000),\n" +
                        "(74, 122, 2000),\n" +
                        "(75, 218, 1),\n" +
                        "(76, 98, 1),\n" +
                        "(77, 122, 3000),\n" +
                        "(78, 122, 2000),\n" +
                        "(79, 304, 10),\n" +
                        "(80, 122, 2000),\n" +
                        "(81, 311, 10),\n" +
                        "(82, 137, 10),\n" +
                        "(83, 122, 2000),\n" +
                        "(84, 270, 1),\n" +
                        "(85, 372, 1),\n" +
                        "(86, 176, 1),\n" +
                        "(87, 122, 3000),\n" +
                        "(88, 122, 2000),\n" +
                        "(89, 199, 1);");
            
            connection.commit();
        } catch (SQLException e) {
            System.out.println(e.getLocalizedMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //connection.close();
        }
    }
    
}

