/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author u0214256
 */
public class MetricSlide extends JPanel implements ActionListener, ChangeListener
       
      {

    private DemApplet1 demApplet;
    private JSlider slider;

    public MetricSlide(DemApplet1 demAp) {
        this.demApplet = demAp;
        slider = new JSlider(JSlider.HORIZONTAL, 100, 200000, demAp.getPopPyramid().getMaxXValue());

//        setMajorTickSpacing(100);
        add(slider);
        slider.addChangeListener(this);

    }
    
    
    public void stateChanged(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
//        if (!source.getValueIsAdjusting()) {
            int newMax = (int)source.getValue();
            demApplet.getPopPyramid().setMaxXValue(newMax);
//        }    
        
            
    }
    
//    public void addChangedListener(ChangeEvent e) {
//    
//    }
//    
    @Override
    public void actionPerformed(ActionEvent e) {
        //Advance the animation frame.
        
    }
    
}
