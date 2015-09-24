/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import java.awt.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import structures.Population;

/**
 *
 * @author u0214256
 */
public class PopNavigator extends JPanel {

    private DemApplet1 demApplet;

    private JTree tree;
    private Population uberPop;
    
//    private JLabel selectedLabel;

    public PopNavigator(DemApplet1 demAp) {
        this.uberPop = demAp.getUberPop();
        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");
        Population[] subPops = uberPop.getSubPopulations();
        //create the child nodes
        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(subPops[0].getName());
        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode(subPops[1].getName());

        //add the child nodes to the root node
        root.add(vegetableNode);
        root.add(fruitNode);

        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));

        //create the tree by passing in the root node
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
        add(tree);

//        this.selectedLabel = new JLabel();

//        demApplet.add(selectedLabel, BorderLayout.SOUTH);

        tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
//             demApplet.getPopPyramid().setCurrentPop();
            }
        });
        this.setVisible(true);
        
        
    }

}
