package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.TCMPMessage;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands.DetectMifareClassicCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands.GetMifareClassicLibraryVersionCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.commands.ReadMifareClassicCommand;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicDetectedResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicLibraryErrorResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicLibraryVersionResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.MifareClassicTimeoutResponse;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses.ReadSuccessResponse;
import com.taptrack.tcmptappy.tcmp.common.CommandCodeNotSupportedException;
import com.taptrack.tcmptappy.tcmp.common.CommandFamily;
import com.taptrack.tcmptappy.tcmp.common.ResponseCodeNotSupportedException;

/**
 * Command family for MIFARE Classic commands
 */
public class MifareClassicCommandLibrary implements CommandFamily {
    public static final byte[] FAMILY_ID = new byte[]{0x00,0x03};

    @Override
    public TCMPMessage parseCommand(TCMPMessage message) throws CommandCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage;
        switch(message.getCommandCode()) {
            case DetectMifareClassicCommand.COMMAND_CODE:
                parsedMessage = new DetectMifareClassicCommand();
                break;
            case GetMifareClassicLibraryVersionCommand.COMMAND_CODE:
                parsedMessage = new GetMifareClassicLibraryVersionCommand();
                break;
            case ReadMifareClassicCommand.COMMAND_CODE:
                parsedMessage = new ReadMifareClassicCommand();
                break;
            default:
                throw new CommandCodeNotSupportedException(
                        MifareClassicCommandLibrary.class.getSimpleName()+
                                " doesn't support command code "+String.format("%02X",message.getCommandCode()));
        }
        parsedMessage.parsePayload(message.getPayload());

        return parsedMessage;
    }

    @Override
    public TCMPMessage parseResponse(TCMPMessage message) throws ResponseCodeNotSupportedException, MalformedPayloadException {
        TCMPMessage parsedMessage;
        switch(message.getCommandCode()) {
            case MifareClassicDetectedResponse.COMMAND_CODE:
                parsedMessage = new MifareClassicDetectedResponse();
                break;
            case MifareClassicLibraryErrorResponse.COMMAND_CODE:
                parsedMessage = new MifareClassicLibraryErrorResponse();
                break;
            case MifareClassicLibraryVersionResponse.COMMAND_CODE:
                parsedMessage = new MifareClassicLibraryVersionResponse();
                break;
            case MifareClassicTimeoutResponse.COMMAND_CODE:
                parsedMessage = new MifareClassicTimeoutResponse();
                break;
            case ReadSuccessResponse.COMMAND_CODE:
                parsedMessage = new ReadSuccessResponse();
                break;
            default:
                throw new ResponseCodeNotSupportedException(
                        MifareClassicCommandLibrary.class.getSimpleName()+
                                " doesn't support response code "+String.format("%02X",message.getCommandCode()));
        }
        parsedMessage.parsePayload(message.getPayload());

        return parsedMessage;
    }

    @Override
    public byte[] getCommandFamilyId() {
        return FAMILY_ID;
    }
}
