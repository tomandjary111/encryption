package myintrnder;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;

/**
 * @author kali
 */
public class Sharevalue {
    private String publickey ;
    private String privatekey;
    private String encrypmethod;

    Mode SM4mode;
    Padding SM4Pading;
    String SM4Key;
    String SM4iv;

    //公钥
    public void setPublickey(String publickey) {
        this.publickey=publickey;
    }
    public String getPublickey() {
        return this.publickey;
    }

    //私钥
    public void setPrivateKey(String publickey) {
        this.privatekey = privatekey;
    }
    public String getPrivateKey() {
        return this.privatekey;
    }


    //传递加密方法
    public void setEncryptionMethod(String encrypmethod){
        this.encrypmethod=encrypmethod;
    }
    public String getEncryptionMethod(){
        return this.encrypmethod;
    }

    public void setSM4Mode(String mode){
         this.SM4mode=Mode.valueOf(mode);
    }

    public Mode getSM4Mode(){
        return this.SM4mode;
    }

    public void setSM4Pading(String sm4Pading){
        this.SM4Pading=Padding.valueOf(sm4Pading);
    }

    public Padding getSM4Pading(){
        return this.SM4Pading;
    }


    public void setSM4Key(String sm4Key){
        this.SM4Key=sm4Key;
    }

    public String getSM4PKey(){
        return this.SM4Key;
    }

    public void setSM4IV(String sm4iv){
        this.SM4iv=sm4iv;
    }

    public String getSM4IV(){
        return this.SM4iv;
    }


}
