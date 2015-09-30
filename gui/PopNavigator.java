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

    private final DemApplet1 demApplet;
    private final JTree tree;

    public PopNavigator(DemApplet1 demAp) {
        this.demApplet = demAp;

        //create the root node
        DefaultMutableTreeNode root = demAp.getCcm().getAllCCPops();
        tree = new JTree(root);
        tree.setRootVisible(false);
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
