package myintrnder;

import burp.api.montoya.MontoyaApi;
import burp.api.montoya.intruder.PayloadData;
import burp.api.montoya.intruder.PayloadProcessingResult;
import burp.api.montoya.intruder.PayloadProcessor;
import cn.hutool.core.codec.Base64Encoder;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.digest.SM3;
import cn.hutool.crypto.symmetric.SM4;

/**
 * @author kali
 */
public class MyPayloadProcessor implements PayloadProcessor
{
    MontoyaApi api;
    Sharevalue sharevalue;
    MyPayloadProcessingResult mypayloadprocessingresult=new MyPayloadProcessingResult();
    public MyPayloadProcessor(MontoyaApi montoyaApi, Sharevalue share)
    {

        this.api=montoyaApi;
        this.sharevalue=share;
    }



    @Override
    //处理器的名称
    public String displayName()
    {
        return "chines encryption";
    }


    @Override
    public PayloadProcessingResult processPayload(PayloadData payloadData) {
        //根据加密方法选择怎么处理i变量

        if(!sharevalue.getEncryptionMethod().isEmpty()){
            switch (sharevalue.getEncryptionMethod()) {
                case "SM2" -> {
                    //公钥加密

//                    SM2 sm2=new SM2();
//                    String pub=sm2.initKeys().getPublicKeyBase64();
//                    String pri=sm2.initKeys().getPrivateKeyBase64();
//
//                    api.logging().logToOutput("initkeys="+pub);
//                    api.logging().logToOutput("privatekey="+pri);

                    SM2 sm2=new SM2( this.sharevalue.getPrivateKey(),this.sharevalue.getPublickey());
                    byte[] sm2jieguo=sm2.encrypt(payloadData.currentPayload().getBytes());
                    mypayloadprocessingresult.setjieguo(Base64Encoder.encode(sm2jieguo));

                }
                case "SM3" -> {
                    byte[] sm3jieguo= new SM3().digest(payloadData.currentPayload().toString());
                    mypayloadprocessingresult.setjieguo(Base64Encoder.encode(sm3jieguo));
                }
                case "SM4" -> {
                    byte[] sm4key=this.sharevalue.getSM4PKey().getBytes();
                    byte[] sm4iv=this.sharevalue.getSM4IV().getBytes();
                    Mode sm4mode=this.sharevalue.SM4mode;
                    Padding sm4padding=this.sharevalue.getSM4Pading();
                    SM4 sm4 = new SM4(sm4mode, sm4padding, sm4key, sm4iv);

                    String encryptbase64 = sm4.encryptBase64(payloadData.currentPayload().toString());
                    mypayloadprocessingresult.setjieguo(encryptbase64);
                }
            }
        }
        return mypayloadprocessingresult;
    }
}