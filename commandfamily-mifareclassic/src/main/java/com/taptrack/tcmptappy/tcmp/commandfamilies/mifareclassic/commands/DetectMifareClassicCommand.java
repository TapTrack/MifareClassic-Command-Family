package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.AbstractMifareClassicMessage;

public class DetectMifareClassicCommand extends AbstractMifareClassicMessage {
    public static final byte COMMAND_CODE = 0x02;
    protected byte timeout;

    public DetectMifareClassicCommand() {
        timeout = 0x00;
    }

    public DetectMifareClassicCommand(byte timeout) {
        this.timeout = timeout;
    }

    /**
     * Set the timeout after which the Tappy will stop scanning
     *
     * 0x00 disables timeout
     * @param timeout
     */
    public void setTimeout(byte timeout) {
        this.timeout = timeout;
    }

    /**
     * Retreive the timeout after which the Tappy will stop scanning
     *
     * 0x00 disables timeout
     * @return
     */
    public byte getTimeout() {
        return timeout;
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        if (payload.length != 1)
            throw new MalformedPayloadException("Payload too short");

        timeout = payload[0];
    }

    @Override
    public byte[] getPayload() {
        return new byte[]{timeout};
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
