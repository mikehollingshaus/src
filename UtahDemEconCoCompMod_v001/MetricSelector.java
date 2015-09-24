/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package UtahDemEconCoCompMod_v001;

import UtahDemEconCoCompMod_v001.PopPyramid.PyramidXAxisMetric;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author u0214256
 */
public class MetricSelector extends JPanel {

    private DemApplet1 demApplet;
    ButtonGroup bGroup;
    private MetricButton bMillion, bHundredK, bTenK, bThousand, bHundreds, bTens;

    public MetricSelector(DemApplet1 demAp) {
        this.demApplet = demAp;
        this.bGroup = new ButtonGroup();
        this.bMillion = new MetricButton(PyramidXAxisMetric.MILLIONS);
        this.bHundredK = new MetricButton(PyramidXAxisMetric.HUNDRED_KS);
        this.bTenK = new MetricButton(PyramidXAxisMetric.TEN_KS);
        this.bThousand = new MetricButton(PyramidXAxisMetric.THOUSANDS);
        this.bHundreds = new MetricButton(PyramidXAxisMetric.HUNDREDS);
        this.bTens = new MetricButton(PyramidXAxisMetric.TENS);

        bGroup.add(bMillion);
        bGroup.add(bHundredK);
        bGroup.add(bTenK);
        bGroup.add(bThousand);
        bGroup.add(bHundreds);
        bGroup.add(bTens);

        add(bMillion);
        add(bHundredK);
        add(bTenK);
        add(bThousand);
        add(bHundreds);
        add(bTens);

//        bMillion.addActionListener(this);
    }

    private class MetricButton extends JRadioButton {

        private PyramidXAxisMetric pyrMetric;
        private int number;

        public MetricButton(PyramidXAxisMetric pxam) {
            super(pxam.getName());
            this.pyrMetric = pxam;
            this.number = pxam.getNumber();
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked button");
    }
}
