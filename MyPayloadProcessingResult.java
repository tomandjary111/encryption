package myintrnder;

import burp.api.montoya.core.ByteArray;
import burp.api.montoya.intruder.PayloadProcessingAction;
import burp.api.montoya.intruder.PayloadProcessingResult;

import static burp.api.montoya.intruder.PayloadProcessingAction.USE_PAYLOAD;

/**
 * @author kali
 */
public class MyPayloadProcessingResult implements PayloadProcessingResult {

    private  ByteArray jieguo=null;
    @Override
    public ByteArray processedPayload() {
        return this.jieguo;
    }

    public void setjieguo( String str)
    {
        this.jieguo= ByteArray.byteArray(str);
    }

    @Override
    public PayloadProcessingAction action() {
        return USE_PAYLOAD;
    }
}
