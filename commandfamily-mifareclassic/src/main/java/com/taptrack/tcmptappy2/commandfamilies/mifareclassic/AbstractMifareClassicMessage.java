package com.taptrack.tcmptappy2.commandfamilies.mifareclassic;


import com.taptrack.tcmptappy2.AbstractTCMPMessage;

public abstract class AbstractMifareClassicMessage extends AbstractTCMPMessage {
    @Override
    public byte[] getCommandFamily() {
        return MifareClassicCommandResolver.FAMILY_ID;
    }
}
