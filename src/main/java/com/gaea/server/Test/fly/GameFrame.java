package com.gaea.server.Test.fly;

import javax.swing.*;

public class GameFrame extends JFrame {

    private JPanel jPanel = new JPanel();
    private JButton jButton = new JButton("按钮");

    public GameFrame() {
        this.setFrameStyle();
        this.addElement();
    }

    private void addElement(){
        jPanel.add(jButton);
        this.add(jPanel);
    }

    private void setFrameStyle(){
        this.setVisible(true);
        this.setBounds(700,200,400,600);
        this.setTitle("测试窗口");
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
