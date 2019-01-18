/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package d.craft;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author antoi
 */
public class Accueil extends JFrame{
   
    public JLabel container = new JLabel();
    
    public JPanel JPtable = new JPanel(),
                  JPrecherche = new JPanel(),
                  JPquitter = new JPanel();
    
    public JButton quitter = new JButton("Quitter"),
                    chercher = new JButton("Chercher");
     
    public JTextField searchBar = new JTextField();
    
    public JFormattedTextField levelBar = new JFormattedTextField();
    
    public JTable table = new JTable();
    public DefaultTableModel model = new DefaultTableModel();
     
    public String insertID;
    public String insertNAME;
    public String insertADRESSE;
    public String IDidole = "";
    public String NOMidole = "";
    public String selecteur = "";
    public String orderBy = "";
    
    public boolean modif = false;  
     
    public String[][] tableauSimpleID = new String[][] {};
    public ArrayList tableauDynamiqueID = new ArrayList(Arrays.asList(tableauSimpleID));
    public Object[] rowData = new Object[3];
    
    public Accueil(){
        container.setLayout(new BorderLayout());                    //Création d'une petite marge en haut du JPtable
        this.setTitle("Dofus Craft");                               //Titre de la fenettre
        this.setSize(600, 700);                                     //Taille par défaut de la fenettre
        this.setResizable(false);                                   //Empeche l'utilisateur de redimensionner la fenetre
        this.setLocationRelativeTo(null);                           //Ouverture de la fenettre au millieu de l'écran (par défaut)
        container.setSize(600,700);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);        //Ferme le programme quand on ferme la fenettre
        
        //container.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Ressources/Wallpaper.png")));
        container.setOpaque(true);
        container.setBackground(new Color(201,191,156)); 
        

        //Barre de JPrecherche

        searchBar.setPreferredSize(new Dimension(300, 25));
        searchBar.setBorder(BorderFactory.createLineBorder(new Color(86,86,86)));
        JPrecherche.add(searchBar);
        
        levelBar.setPreferredSize(new Dimension(70, 25));
        levelBar.setBorder(BorderFactory.createLineBorder(new Color(86,86,86)));
        levelBar.setText("200");
        JPrecherche.add(levelBar);
        
        chercher.setPreferredSize(new Dimension(100, 25));
        chercher.setBackground(new Color(255,97,0));
        chercher.setForeground(Color.WHITE);
        chercher.setBorder(BorderFactory.createLineBorder(new Color(86,86,86)));
        JPrecherche.add(chercher);
        
        JPrecherche.setOpaque(false);
        container.add(JPrecherche, BorderLayout.NORTH);
        
        
        ////////////////////////////////////////////////////////////////////////
        
        //Tableau
        Object[] columnsName = new Object[3];
        columnsName[0] = "id";
        columnsName[1] = "Nom";
        columnsName[2] = "Niveau";
        model.setColumnIdentifiers(columnsName); 
        table.setModel(model);
        
        table.getColumnModel().getColumn(0).setMinWidth(0);                     //supprimer les ID
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.setBackground(new Color(201,191,156));
        ((DefaultTableCellRenderer)table.getDefaultRenderer(Object.class)).setBackground(new Color(201,191,156));
        table.setGridColor(new Color(81,73,60));
        table.setForeground(Color.BLACK);
        
        table.setDefaultRenderer(Object.class, new MyCellRenderer(table.getDefaultRenderer(Object.class)));

        
        JTableHeader header = table.getTableHeader();
        header.setBackground(new Color(81,73,60));
        header.setBorder(BorderFactory.createLineBorder(new Color(81,73,60)));
        header.setForeground(Color.WHITE);
        JPtable.setLayout(new BorderLayout());        
        
        JScrollPane jsp = new JScrollPane(table);
        jsp.getVerticalScrollBar().setUI(new MyScrollBarUI());
        jsp.setBackground(new Color(60,49,47,51));
        jsp.setOpaque(false);
        jsp.getViewport().setOpaque(false);
        jsp.setBorder(BorderFactory.createEmptyBorder());
        JPtable.add(jsp);
        JPtable.setOpaque(false);
        
        container.add(JPtable, BorderLayout.CENTER);
           
        ////////////////////////////////////////////////////////////////////////
                
        //Bouton Quitter
        quitter.setPreferredSize(new Dimension(100, 25));
        quitter.setBackground(new Color(255,97,0));
        quitter.setForeground(Color.WHITE);
        quitter.setBorder(BorderFactory.createLineBorder(new Color(86,86,86)));
        JPquitter.add(quitter);
        JPquitter.setOpaque(false);
        container.add(JPquitter, BorderLayout.SOUTH);
        
        this.getContentPane().add(container);
        
        BDD bdd = new BDD();
        bdd.getConnection();
        bdd.getIdIdole(this, selecteur, orderBy);
        
        table.selectAll();
        
        
        this.setVisible(true);
    }
    
    //Colorer 1 ligne sur 2 dans le tableau
    public class MyCellRenderer implements TableCellRenderer {
        private TableCellRenderer tcr;
        public MyCellRenderer (TableCellRenderer tcr) {
            this.tcr = tcr;
        }
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            Component comp = this.tcr.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            if ((row & 1) == 0) { // Ligne paire
                comp.setBackground(new Color(181,171,141));
            } else { // Ligne impaire
                comp.setBackground(new Color(201,191,156));
            }
            return comp;
        }
    }
    
    //Couleurs ScrollBar
    public class MyScrollBarUI extends BasicScrollBarUI {
        //boutons
        protected JButton createDecreaseButton(int orientation) {
            return new BasicArrowButton(orientation,
                                     (new Color(201,191,156)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)));
        }
        protected JButton createIncreaseButton(int orientation) {
    	    return new BasicArrowButton(orientation,
                                     (new Color(201,191,156)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)),
                                     (new Color(81,73,60)));
        }
        
        //Fond
        protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
            c.setBackground(new Color(201,191,156));
        }
        
    }
}
