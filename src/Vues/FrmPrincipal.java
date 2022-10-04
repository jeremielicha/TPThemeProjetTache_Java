package Vues;

import Entities.Tache;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

public class FrmPrincipal extends JFrame
{
    private JPanel pnlRoot;
    private JLabel lblTitre;
    private JLabel lblTheme;
    private JList lstThemes;
    private JLabel lblProjet;
    private JList lstProjets;
    private JLabel lblTache;
    private JTextField txtNomTache;
    private JLabel lblNomEmploye;
    private JComboBox cboEmployes;
    private JButton btnValider;
    private JTree trPlanning;

    private HashMap<String,HashMap<String, ArrayList<Tache>>> monPlanning;

    private DefaultMutableTreeNode racine;
    private DefaultMutableTreeNode dmtnTheme;
    private DefaultMutableTreeNode dmtnProjet;
    private DefaultMutableTreeNode dmtnTache;
    private DefaultMutableTreeNode dmtnNom;
    private DefaultMutableTreeNode dmtnDoing;
    private DefaultTreeModel model;
    public FrmPrincipal()
    {
        this.setTitle("Projet");
        this.setContentPane(pnlRoot);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);

        racine = new DefaultMutableTreeNode("Les tâches à faire");
        model = new DefaultTreeModel(racine);
        trPlanning.setModel(model);

        monPlanning = new HashMap<>();

        btnValider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                if (lstThemes.isSelectionEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Veuillez séléctionner un thème");
                }
                else if (lstProjets.isSelectionEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Veuillez séléctionner un projet");
                }
                else if (Objects.equals(txtNomTache.getText(), ""))
                {
                    JOptionPane.showMessageDialog(null,"Veuillez saisir une tache");
                }
                else
                {
                    String theme=lstThemes.getSelectedValue().toString();
                    String projet=lstProjets.getSelectedValue().toString();
                    String nomTache=txtNomTache.getText();
                    String nomDev=cboEmployes.getSelectedItem().toString();
                    if (!monPlanning.containsKey(theme))
                    {
                        ArrayList<Tache>lesTaches=new ArrayList<>();
                        Tache tache=new Tache(nomTache,nomDev,false);
                        HashMap<String,ArrayList<Tache>> lesProjets=new HashMap<>();
                        lesTaches.add(tache);
                        lesProjets.put(projet,lesTaches);
                        monPlanning.put(theme,lesProjets);
                    }
                    else
                    {
                        if (!monPlanning.get(theme).containsKey(projet))
                        {
                            ArrayList<Tache>lesTaches=new ArrayList<>();
                            Tache tache=new Tache(nomTache,nomDev,false);
                            lesTaches.add(tache);
                            monPlanning.get(theme).put(projet,lesTaches);
                        }
                        else
                        {
                            Tache tache=new Tache(nomTache,nomDev,false);
                            monPlanning.get(theme).get(projet).add(tache);
                        }
                    }
                }


                // Parcours du planning
                racine.removeAllChildren();
                for (String keyTheme:monPlanning.keySet())
                {
                    dmtnTheme=new DefaultMutableTreeNode(keyTheme);
                    for (String keyProjet:monPlanning.get(keyTheme).keySet())
                    {
                        dmtnProjet=new DefaultMutableTreeNode(keyProjet);
                        for (Tache lesTaches:monPlanning.get(keyTheme).get(keyProjet))
                        {
                            dmtnTache=new DefaultMutableTreeNode(lesTaches.getNomTache());
                            dmtnNom=new DefaultMutableTreeNode(lesTaches.getNomDeveloppeur());
                            dmtnDoing=new DefaultMutableTreeNode(lesTaches.isEstTerminee());
                            dmtnTache.add(dmtnNom);
                            dmtnTache.add(dmtnDoing);
                            dmtnProjet.add(dmtnTache);
                            dmtnTheme.add(dmtnProjet);
                            racine.add(dmtnTheme);
                        }
                    }
                }
                trPlanning.setModel(new DefaultTreeModel(racine));
            }
        });
        trPlanning.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

            }
        });
    }
}
