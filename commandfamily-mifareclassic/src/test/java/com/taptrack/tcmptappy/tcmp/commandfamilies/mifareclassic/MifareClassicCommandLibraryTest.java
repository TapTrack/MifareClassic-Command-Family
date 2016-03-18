package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands.DetectMifareClassicCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands.GetMifareClassicLibraryVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands.ReadMifareClassicCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicDetectedResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicLibraryErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicLibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicReadSuccessResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicTimeoutResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class MifareClassicCommandLibraryTest {
    MifareClassicCommandLibrary library = new MifareClassicCommandLibrary();

    private static class FakeCommand extends AbstractMifareClassicMessage {

        @Override
        public void parsePayload(byte[] payload) throws MalformedPayloadException {

        }

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    private static class FakeResponse extends AbstractMifareClassicMessage {

        @Override
        public void parsePayload(byte[] payload) throws MalformedPayloadException {

        }

        @Override
        public byte[] getPayload() {
            return new byte[0];
        }

        @Override
        public byte getCommandCode() {
            return 0x76;
        }
    }

    @Test
    public void testParseCommand() throws Exception {
        testCommand(new DetectMifareClassicCommand(),DetectMifareClassicCommand.class);
        testCommand(new GetMifareClassicLibraryVersionCommand(),GetMifareClassicLibraryVersionCommand.class);
        testCommand(new ReadMifareClassicCommand(),ReadMifareClassicCommand.class);

        boolean commandCodeNotSupportedThrown = false;
        try {
            testCommand(new FakeCommand(),FakeCommand.class);
        }
        catch (CommandCodeNotSupportedException e) {
            commandCodeNotSupportedThrown = true;
        }

        assertTrue(commandCodeNotSupportedThrown);
    }

    private void testCommand(TCMPMessage message,Class<? extends TCMPMessage> clazz) throws CommandCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseCommand(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(), parsedMessage.getPayload());
    }

    @Test
    public void testParseResponse() throws Exception {
        testResponse(new MifareClassicDetectedResponse(), MifareClassicDetectedResponse.class);
        testResponse(new MifareClassicLibraryErrorResponse(), MifareClassicLibraryErrorResponse.class);
        testResponse(new MifareClassicLibraryVersionResponse(), MifareClassicLibraryVersionResponse.class);
        testResponse(new MifareClassicTimeoutResponse(), MifareClassicTimeoutResponse.class);
        testResponse(new MifareClassicReadSuccessResponse(), MifareClassicReadSuccessResponse.class);

        boolean responseCodeNotSupportedThrown = false;
        try {
            testResponse(new FakeResponse(), FakeResponse.class);
        }
        catch (ResponseCodeNotSupportedException e) {
            responseCodeNotSupportedThrown = true;
        }

        assertTrue(responseCodeNotSupportedThrown);
    }

    private void testResponse(TCMPMessage message,Class<? extends TCMPMessage> clazz) throws ResponseCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage = library.parseResponse(message);
        assertThat(parsedMessage,instanceOf(clazz));
        assertArrayEquals(message.getPayload(),parsedMessage.getPayload());
    }

    @Test
    public void testGetCommandFamilyId() throws Exception {
        assertArrayEquals(library.getCommandFamilyId(),new byte[]{0x00,0x03});
    }
}