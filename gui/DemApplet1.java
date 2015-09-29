package gui;

import UtahDemEconCoCompMod_v001.UtahDemEconCoCompMod_v001;
import java.awt.*;
import javax.swing.JApplet;
import structures.Population;
import UtahDemEconCoCompMod_v001.CohortComponentModel;

public class DemApplet1 extends JApplet {

    private UtahDemEconCoCompMod_v001 udem;
    private Population[] stockPops, deathPops, migrantPops, birthPops;
    private PopPyramid popPyramid;
    private CohortComponentModel ccm;
    private PopNavigator popNavigator;
    private MetricSlide metSlide;
    private Population treeOfStockPops;

    public DemApplet1(UtahDemEconCoCompMod_v001 demMod) {
        this.udem = demMod;
        this.ccm = udem.getWorkbench().getModel();
        this.stockPops = ccm.getStockPops();
        treeOfStockPops = ccm.getTreeOfStockPops();
        this.setLayout(new BorderLayout());
        this.popPyramid = new PopPyramid(this);
        this.popNavigator = new PopNavigator(this);
        this.add(popNavigator, BorderLayout.WEST);
        this.add(popPyramid, BorderLayout.CENTER);
        this.metSlide = new MetricSlide(this);
        this.add(metSlide, BorderLayout.SOUTH);
        repaint();
    }

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

   
    public PopPyramid getPopPyramid() {
        return popPyramid;
    }

    public PopNavigator getPopNavigator() {
        return popNavigator;
    }

    public CohortComponentModel getCcm() {
        return ccm;
    }

    public MetricSlide getMetSlide() {
        return metSlide;
    }

    public Population getTreeOfStockPops() {
        return treeOfStockPops;
    }

    
    
}
