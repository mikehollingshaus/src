/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author u0214256
 */
public class MetricSlide extends JPanel implements ActionListener, ChangeListener {

    private DemApplet1 demApplet;
    private JSlider slider;
    private JTextField text, textLab;

    public final String jTextLabelString = "Max Value: ";

    public MetricSlide(DemApplet1 demAp) {
        this.demApplet = demAp;
        slider = new JSlider(JSlider.HORIZONTAL, 100, 200000, demAp.getPopPyramid().getMaxXValue());
        text = new JTextField(Integer.toString(demAp.getPopPyramid().getMaxXValue()));
        textLab = new JTextField(jTextLabelString);
        textLab.setEditable(false);

        setLayout(new FlowLayout());
        add(textLab);
        add(text);//, BorderLayout.EAST);
        add(slider);//,BorderLayout.WEST);

        slider.addChangeListener(this);
        
        text.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent ke) {
                String typed = text.getText();
//                slider.setValue(0);
                if(!typed.matches("\\d+")) {
                    return;
                }
                int value = Integer.parseInt(typed);
                slider.setValue(value);
            }
        });

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();
//        if (!source.getValueIsAdjusting()) {
        int newMax = (int) source.getValue();
        demApplet.getPopPyramid().setMaxXValue(newMax);
        text.setText(Integer.toString(slider.getValue()));
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
