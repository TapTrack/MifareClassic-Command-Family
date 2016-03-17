package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic;

import com.taptrack.tcmptappy.tcmp.TCMPMessage;

public abstract class AbstractMifareClassicMessage extends TCMPMessage {
    @Override
    public byte[] getCommandFamily() {
        return MifareClassicCommandLibrary.FAMILY_ID;
    }
}
