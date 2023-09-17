package myintrnder;

import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;


/**
 * @author kali
 */
public class IBurpExtender implements BurpExtension {
    @Override
    public void initialize(MontoyaApi api)
    {
        java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        //安装扩展时显示的jar包
        api.extension().setName("Intruder payloads");
        Sharevalue sharevalue=new Sharevalue();
        MyUI myUI = new MyUI(api,sharevalue);

        api.userInterface().registerSuiteTab("GM", myUI);
        api.intruder().registerPayloadProcessor(new MyPayloadProcessor(api,sharevalue));
    }
}
