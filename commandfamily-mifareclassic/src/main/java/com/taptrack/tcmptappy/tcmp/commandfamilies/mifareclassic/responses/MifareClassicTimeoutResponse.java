package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.AbstractMifareClassicMessage;

/**
 * A mifare classic operation has timed out
 */
public class MifareClassicTimeoutResponse extends AbstractMifareClassicMessage {
    public static final byte COMMAND_CODE = 0x03;

    public MifareClassicTimeoutResponse() {

    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {

    }

    @Override
    public byte[] getPayload() {
        return new byte[0];
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
