package myintrnder;

import burp.api.montoya.MontoyaApi;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author kali
 */
public class MyUI extends JPanel  {
    MontoyaApi api;
    //共享值来传递加密用的key
    Sharevalue sharevalue;
    JTextArea publickey = new JTextArea();
    //私钥文本框
    JTextArea privatekey = new JTextArea();

    JTextArea sm4iv = new JTextArea();

    JTextArea sm4key = new JTextArea();
    JButton delpubButton = new JButton("PubClean");
    JButton savepubButto = new JButton("PubSave");
    //容器
    GridBagConstraints container = new GridBagConstraints();


    //初始化函数了，获取全局api和共享数据指针
    public MyUI(MontoyaApi api, Sharevalue share) {
        this.api = api;
        this.sharevalue =share;
        this.setLayout(new GridBagLayout());
        //container.fill=GridBagConstraints.HORIZONTAL;
        //container.fill=GridBagConstraints.VERTICAL;
        Insets insets=new Insets(1,1,1,1);
        container.insets=insets;


        addencMethod();
        addpublickey();
        sm2pubkeyCleanButton();
        sm2pubkeySaveButton();
        addprivatekey();
        addpricleanbutton();
        addpriSavebutton();
        addsm4paramter();
        addsm4padding();
        addsm4key();
        addsm4iv();
        sm4keyandivSaveButton();
    }

    //下拉列表来指定加密算法
    public void addencMethod(){
        container.gridx=0;
        container.gridy=0;
        JComboBox<String> jComboBox = new JComboBox<String>();
        jComboBox.addItem("请选择加密方法");
        jComboBox.addItem("SM2");
        jComboBox.addItem("SM3");
        jComboBox.addItem("SM4");

        jComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genderSel = (String) jComboBox.getSelectedItem();//get the selected item
                if (genderSel != null) {
                    switch (genderSel) {
                        case "SM2" -> sharevalue.setEncryptionMethod("SM2");
                        case "SM3" -> sharevalue.setEncryptionMethod("SM3");
                        case "SM4" -> sharevalue.setEncryptionMethod("SM4");
                    }
                }
            }
        });
        this.add(jComboBox, container);
    }
    //设置公钥文本框


    public void addpublickey() {
        container.gridx=1;
        container.gridy=0;

        //container.gridwidth=2;

        container.fill=GridBagConstraints.BOTH;

        publickey.setEditable(true); // 设置输入框允许编辑
        publickey.setColumns(20); // 设置输入框的长度为14个字符
        publickey.setRows(10); // 设置输入框的高度为3行字符
        publickey.setLineWrap(true); // 设置每行是否折叠。为true的话，输入字符超过每行宽度就会自动换行
        publickey.setText("使用sm2,这里填写公钥。例如：MFkwEwYHKoZIzj0CAQYIKoEcz1UBgi0DQgAEgCpxbFrFmfTuPkrmWDKWDqMnffhqogCBvPWC8vWeXFyjcriKSHnwPb+LaSDF386gX3R0QI/0lR4TW82B0vPmiQ==");

        JScrollPane scroll = new JScrollPane(publickey); // 创建一个滚动条
        this.add(scroll, container); // 在面板上添加滚动条

    }

    //删除按钮的动作
    public void sm2pubkeyCleanButton(){
        container.gridx=1;
        container.gridy=2;
        delpubButton.addActionListener(e -> publickey.setText(""));
        this.add(delpubButton, container);
    }

    //sm2 公钥保存按钮
    public void sm2pubkeySaveButton(){
        container.gridx=2;
        container.gridy=2;
        savepubButto.addActionListener(e -> {
            String a=publickey.getText();
            if(a.length() == 0) {
                api.logging().logToOutput("public key can't be null");
            } else {
                sharevalue.setPublickey(a);
            }
        });
        this.add(savepubButto, container);
    }

    //设置私钥文本框
    public void addprivatekey() {
        container.gridx=3;
        container.gridy=0;
        //container.gridwidth=4;
        container.fill=GridBagConstraints.BOTH;

        privatekey.setEditable(true); // 设置输入框允许编辑
        privatekey.setColumns(14); // 设置输入框的长度为14个字符
        privatekey.setRows(10); // 设置输入框的高度为3行字符
        privatekey.setLineWrap(true); // 设置每行是否折叠。为true的话，输入字符超过每行宽度就会自动换行
        privatekey.setText("使用sm2，这里填写私钥. 例如：MIGTAgEAMBMGByqGSM49AgEGCCqBHM9VAYItBHkwdwIBAQQgY47ie/9LI4LTWHQhDkTahsRYwwpUFgDXeIs4lOunt7egCgYIKoEcz1UBgi2hRANCAASAKnFsWsWZ9O4+SuZYMpYOoyd9+GqiAIG89YLy9Z5cXKNyuIpIefA9v4tpIMXfzqBfdHRAj/SVHhNbzYHS8+aJ");
        JScrollPane scroll1 = new JScrollPane(privatekey); // 创建一个滚动条
        this.add(scroll1, container); // 在面板上添加滚动条

    }
    //私钥清理按钮
    public void addpricleanbutton(){
        container.gridx=3;
        container.gridy=2;
        //清空文本框的按钮
        JButton deleteImgButton = new JButton("PriClean");
        //删除按钮的动作
        deleteImgButton.addActionListener(e -> privatekey.setText(""));
        this.add(deleteImgButton, container);
    }

    public void addpriSavebutton(){
        container.gridx=4;
        container.gridy=2;
        //保存按钮--把加密所需的参数发送给处理器
        JButton savethevalue = new JButton("PriSave");
        savethevalue.setHorizontalAlignment(SwingConstants.RIGHT);
        savethevalue.addActionListener(e -> {
            String a=privatekey.getText();
            if(a.length()== 0) {
                api.logging().logToOutput("private key can't be null");
            } else {
                sharevalue.setPrivateKey(a);
            }
        });
        this.add(savethevalue, container);
    }
    //设置SM4加密模式
    public void addsm4paramter() {
        //水平填充网格
        //用来指定元件放在那一行那一列
        container.gridx = 1;
        container.gridy = 5;
        //指定元件占用的行数和列数
        //c.gridwidth = 1;

        JComboBox<String> sm4blockmethod = new JComboBox<String>();
        sm4blockmethod.addItem("请选择SM4加密模式");
        sm4blockmethod.addItem("NONE");
        sm4blockmethod.addItem("CBC");
        sm4blockmethod.addItem("CFB");
        sm4blockmethod.addItem("CTR");
        sm4blockmethod.addItem("CTS");
        sm4blockmethod.addItem("ECB");
        sm4blockmethod.addItem("OFB");
        sm4blockmethod.addItem("PCBC");

        sm4blockmethod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genderSel = (String) sm4blockmethod.getSelectedItem();//get the selected item
                if (genderSel != null) {
                    switch (genderSel) {
                        case "NONE" -> sharevalue.setSM4Mode("NONE");
                        case "CBC" -> sharevalue.setSM4Mode("CBC");
                        case "CFB" -> sharevalue.setSM4Mode("CFB");
                        case "CTR" -> sharevalue.setSM4Mode("CTR");
                        case "CTS" -> sharevalue.setSM4Mode("CTS");
                        case "ECB" -> sharevalue.setSM4Mode("ECB");
                        case "OFB" -> sharevalue.setSM4Mode("OFB");
                        case "PCBC" -> sharevalue.setSM4Mode("PCBC");
                    }
                }
            }
        });
        this.add(sm4blockmethod, container);

    }
    public void addsm4padding() {
        //水平填充网格

        //用来指定元件放在那一行那一列
        container.gridx = 2;
        container.gridy = 5;

        JComboBox<String> padingComboBox = new JComboBox<String>();
        padingComboBox.addItem("请选择SM4补码方式 ");
        padingComboBox.addItem("NoPadding");
        padingComboBox.addItem("PKCS1Padding");
        padingComboBox.addItem("PKCS5Padding");
        padingComboBox.addItem("ZeroPadding");
        padingComboBox.addItem("ISO10126Padding");
        padingComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String genderSel = (String) padingComboBox.getSelectedItem();//get the selected item
                if (genderSel != null) {
                    switch (genderSel) {
                        case "NoPadding" -> sharevalue.setSM4Pading("NoPadding");
                        case "PKCS1Padding" -> sharevalue.setSM4Pading("PKCS1Padding");
                        case "PKCS5Padding" -> sharevalue.setSM4Pading("PKCS5Padding");
                        case "ZeroPadding" -> sharevalue.setSM4Pading("ZeroPadding");
                        case "ISO10126Padding" -> sharevalue.setSM4Pading("ISO10126Padding");
                    }
                }
            }
        });
        this.add(padingComboBox, container);
    }
    public void addsm4key() {
        //水平填充网格

        //用来指定元件放在那一行那一列
        container.gridx = 3;
        container.gridy = 5;

        sm4key.setOpaque(true);//设置背景为不透明
        sm4key.setEditable(true); // 设置输入框允许编辑
        sm4key.setColumns(14); // 设置输入框的长度为14个字符
        sm4key.setRows(3); // 设置输入框的高度为3行字符
        sm4key.setLineWrap(true); // 设置每行是否折叠。为true的话，输入字符超过每行宽度就会自动换行
        sm4key.setText("这里填写SM4 key.需要16Byte（128bites）。");
        JScrollPane scroll = new JScrollPane(sm4key); // 创建一个滚动条
        this.add(scroll, container); // 在面板上添加滚动条
    }

    public void addsm4iv(){
        container.gridx = 4;
        container.gridy = 5;

        sm4iv.setOpaque(true);//设置背景为不透明
        sm4iv.setEditable(true); // 设置输入框允许编辑
        sm4iv.setColumns(14); // 设置输入框的长度为14个字符
        sm4iv.setRows(3); // 设置输入框的高度为3行字符
        sm4iv.setLineWrap(true); // 设置每行是否折叠。为true的话，输入字符超过每行宽度就会自动换行
        sm4iv.setText("这里填写SM4 iv,需要在非ECB模式下填写。需要16Byte。");
        JScrollPane sm4scroll = new JScrollPane(sm4iv); // 创建一个滚动条
        this.add(sm4scroll, container); // 在面板上添加滚动条

    }
    public void sm4keyandivSaveButton(){
        container.gridx = 4;
        container.gridy = 6;
        //保存按钮--把加密所需的参数发送给处理器
        JButton savethevalue = new JButton("save");
        savethevalue.addActionListener(e -> {
            if(sm4key.getText().length()== 16 && sm4iv.getText().length()==16){
                sm4iv.setBackground(Color.GREEN);
                sm4key.setBackground(Color.GREEN);
                sharevalue.setSM4Key(sm4key.getText());
                sharevalue.setSM4IV(sm4iv.getText());

            }else{
                sm4iv.setBackground(Color.RED);
                sm4key.setBackground(Color.RED);
            }
        });
        this.add(savethevalue, container);
    }
}