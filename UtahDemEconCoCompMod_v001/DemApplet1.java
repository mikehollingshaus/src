package UtahDemEconCoCompMod_v001;

import java.awt.*;
import javax.swing.JApplet;
import structures.AgeDistribution;
import structures.PopValue;
import structures.Population;
import tools.CohortComponentModel;

public class DemApplet1 extends JApplet {

    private UtahDemEconCoCompMod_v001 udem;
    private Population[] stockPops, deathPops, migrantPops, birthPops;
    private PopPyramid popPyramid;
    private CohortComponentModel ccm;
    private PopNavigator popNavigator;
    private Population uberPop;
   
    
    public DemApplet1(UtahDemEconCoCompMod_v001 demMod) {
        this.udem = demMod;
        this.ccm = udem.getWorkbench().getModel();
        this.stockPops = ccm.getStockPops();
        uberPop = ccm.getTreeOfStockPops();
        this.setLayout(new BorderLayout());
        this.popPyramid = new PopPyramid(this);
        this.popNavigator = new PopNavigator(this);
        this.add(popNavigator, BorderLayout.WEST);
        this.add(popPyramid, BorderLayout.CENTER);
        repaint();
        
    }

    
    
    /*@Override
     public void paint(Graphics g) {
     paintChildren(g);
     //        if (!udem.isModelBuilt()) {
     //        return;    
     //        }

     }
     */
    public UtahDemEconCoCompMod_v001 getUdem() {
        return udem;
    }

    public Population[] getStockPops() {
        return stockPops;
    }

    public Population[] getDeathPops() {
        return deathPops;
    }

    public Population[] getMigrantPops() {
        return migrantPops;
    }

    public Population[] getBirthPops() {
        return birthPops;
    }

    public Population getUberPop() {
        return uberPop;
    }

    public PopPyramid getPopPyramid() {
        return popPyramid;
    }

    public PopNavigator getPopNavigator() {
        return popNavigator;
    }
    
    

    
}
