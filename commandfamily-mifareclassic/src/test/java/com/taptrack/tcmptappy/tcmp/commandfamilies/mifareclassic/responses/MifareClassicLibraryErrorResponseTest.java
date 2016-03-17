package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MifareClassicLibraryErrorResponseTest {

    @Test
    public void testGetCommandCode() throws Exception {
        MifareClassicLibraryErrorResponse response = new MifareClassicLibraryErrorResponse();
        assertEquals(response.getCommandCode(),0x7F);
    }
}