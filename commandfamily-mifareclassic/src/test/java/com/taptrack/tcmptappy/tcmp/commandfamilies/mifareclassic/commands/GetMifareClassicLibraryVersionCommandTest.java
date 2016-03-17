package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GetMifareClassicLibraryVersionCommandTest {

    @Test
    public void testGetCommandCode() throws Exception {
        GetMifareClassicLibraryVersionCommand command = new GetMifareClassicLibraryVersionCommand();
        assertEquals(command.getCommandCode(),(byte)0xFF);

    }
}