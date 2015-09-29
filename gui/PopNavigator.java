/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import structures.Population;

/**
 *
 * @author u0214256
 */
public final class PopNavigator extends JPanel {

    private DemApplet1 demApplet;

//    private JScrollPane scroller;
    private JTree tree;
    private Population uberPop;
    private Population[] allSubPops;

    public PopNavigator(DemApplet1 demAp) {
        this.demApplet = demAp;
//        this.uberPop = demAp.getTreeOfStockPops();
//        this.uberPop = demAp.getCcm().getAllCCPops().getSubPopulations()[3];
            this.uberPop = demAp.getCcm().getAllCCPops();
        //create the root node
//        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        DefaultMutableTreeNode root = uberPop;

        this.allSubPops = uberPop.getSubPopulations();
   
          tree = new JTree(root);
//        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        add(new JScrollPane(tree));

        addAllListeners();
    }

    public final void addAllListeners() {
        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                Population selectedPop = (Population) selectedNode;
                PopPyramid pp = demApplet.getPopPyramid();
                demApplet.getPopPyramid().setCurrentPop(selectedPop);
            }
        });
    }
}
