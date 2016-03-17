package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses;

import com.taptrack.tcmptappy.tcmp.MalformedPayloadException;
import com.taptrack.tcmptappy.tcmp.StandardErrorResponse;
import com.taptrack.tcmptappy.tcmp.StandardErrorResponseDelegate;
import com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.AbstractMifareClassicMessage;

public class MifareClassicLibraryErrorResponse extends AbstractMifareClassicMessage implements StandardErrorResponse {
    public static final byte COMMAND_CODE = 0x7F;
    protected StandardErrorResponseDelegate delegate;

    public MifareClassicLibraryErrorResponse() {
        delegate = new StandardErrorResponseDelegate();
    }

    public MifareClassicLibraryErrorResponse(byte errorCode, byte internalErrorCode, byte readerStatus, String message) {
        delegate = new StandardErrorResponseDelegate(errorCode,internalErrorCode,readerStatus,message);
    }

    @Override
    public byte getErrorCode() {
        return delegate.getErrorCode();
    }

    @Override
    public void setErrorCode(byte errorCode) {
        delegate.setErrorCode(errorCode);
    }

    @Override
    public byte getInternalErrorCode() {
        return delegate.getInternalErrorCode();
    }

    @Override
    public void setInternalErrorCode(byte internalErrorCode) {
        delegate.setInternalErrorCode(internalErrorCode);
    }

    @Override
    public byte getReaderStatus() {
        return delegate.getReaderStatus();
    }

    @Override
    public void setReaderStatus(byte readerStatus) {
        delegate.setReaderStatus(readerStatus);
    }

    @Override
    public String getErrorMessage() {
        return delegate.getErrorMessage();
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        delegate.setErrorMessage(errorMessage);
    }

    @Override
    public void setErrorMessage(byte[] errorMessage) {
        delegate.setErrorMessage(errorMessage);
    }

    @Override
    public void parsePayload(byte[] payload) throws MalformedPayloadException {
        delegate.parsePayload(payload);
    }

    @Override
    public byte[] getPayload() {
        return delegate.getPayload();
    }

    @Override
    public byte getCommandCode() {
        return COMMAND_CODE;
    }
}
