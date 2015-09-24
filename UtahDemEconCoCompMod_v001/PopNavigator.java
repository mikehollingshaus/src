/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import java.awt.*;
import java.util.HashMap;
import javax.swing.JLabel;
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
        this.uberPop = demAp.getUberPop();

        //create the root node
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

        this.allSubPops = uberPop.getSubPopulations();

        for (Population p : allSubPops) {
            // If it has subpops
            // for (Population ps: p.getSubPopulations()
            DefaultMutableTreeNode popNode = p;

            root.add(popNode);
            DefaultMutableTreeNode utahCountyNode = new DefaultMutableTreeNode("Utah County");
            DefaultMutableTreeNode slCountyNode = new DefaultMutableTreeNode("Salt Lake County");
            DefaultMutableTreeNode garfieldCountyNode = new DefaultMutableTreeNode("Garfield County");
            popNode.add(utahCountyNode);
            popNode.add(slCountyNode);
            popNode.add(garfieldCountyNode);
            DefaultMutableTreeNode slPrison = new DefaultMutableTreeNode("Salt Lake City Prison");
            slCountyNode.add(slPrison);

        }
        //create the child nodes
//        DefaultMutableTreeNode vegetableNode = new DefaultMutableTreeNode(subPops[0].getName?);
//        DefaultMutableTreeNode fruitNode = new DefaultMutableTreeNode(subPops[1].getName());

        //add the child nodes to the root node
//        root.add(vegetableNode);
//        root.add(fruitNode);```
//        vegetableNode.add(new DefaultMutableTreeNode("Carrot"));
        //create the tree by passing in the root node
        tree = new JTree(root);
        tree.setRootVisible(false);
        tree.setShowsRootHandles(true);
//        scroller = new JScrollPane();
//        scroller.add(tree);
        add(new JScrollPane(tree));

//
//        DefaultMutableTreeNode testNode = allSubPops[0].getTreeNode();
//        Population testPop = map.get(testNode);
        addAllListeners();
//        this.selectedLabel = new JLabel();
//        demApplet.add(selectedLabel, BorderLayout.SOUTH);
//        this.setVisible(true);
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
