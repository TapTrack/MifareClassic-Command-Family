package com.taptrack.tcmptappy.tcmp.commandfamilies.mifareclassic.responses;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ReadSuccessResponseTest {
    Random rand = new Random();

    private byte[] generateRandomData(byte startBlock, byte endBlock) {
        int length = (endBlock & 0xff) - (startBlock & 0xff);
        byte[] testData = new byte[length];
        rand.nextBytes(testData);
        return testData;
    }

    private byte[] generateUid(boolean sevenByte) {
        byte[] uid;
        if(sevenByte) {
            uid = new byte[7];
        }
        else {
            uid = new byte[4];
        }

        rand.nextBytes(uid);
        return uid;
    }

    private byte[] generateTestPayload(byte[] uid, byte startBlock, byte endBlock, byte[] data) {
        byte uidSize = (byte) uid.length;

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        outputStream.write(uidSize);
        try {
            outputStream.write(uid);
            outputStream.write(startBlock);
            outputStream.write(endBlock);
            outputStream.write(data);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return outputStream.toByteArray();
    }

    @Test
    public void testParsePayload() throws Exception {
        byte[] uidShort = generateUid(false);
        byte[] uidLong = generateUid(true);
        byte start = 0x04;
        byte end = 0x10;
        byte[] data = generateRandomData(start,end);

        byte[] shortPayload = generateTestPayload(uidShort, start, end, data);
        byte[] longPayload = generateTestPayload(uidLong,start,end,data);

        ReadSuccessResponse longResponse = new ReadSuccessResponse();
        longResponse.parsePayload(longPayload);

        assertEquals(longResponse.getStartBlock(), start);
        assertEquals(longResponse.getEndBlock(), end);
        assertArrayEquals(longResponse.getUid(), uidLong);
        assertArrayEquals(longResponse.getData(),data);

        ReadSuccessResponse shortResponse = new ReadSuccessResponse();
        shortResponse.parsePayload(shortPayload);

        assertEquals(shortResponse.getStartBlock(), start);
        assertEquals(shortResponse.getEndBlock(), end);
        assertArrayEquals(shortResponse.getUid(), uidShort);
        assertArrayEquals(shortResponse.getData(),data);
    }

    @Test
    public void testGetPayload() throws Exception {
        byte[] uidShort = generateUid(false);
        byte[] uidLong = generateUid(true);
        byte start = 0x04;
        byte end = 0x10;
        byte[] data = generateRandomData(start,end);

        byte[] shortPayload = generateTestPayload(uidShort, start, end, data);
        byte[] longPayload = generateTestPayload(uidLong,start,end,data);

        ReadSuccessResponse longResponse = new ReadSuccessResponse(start,end,uidLong,data);
        assertArrayEquals(longResponse.getPayload(),longPayload);

        ReadSuccessResponse shortResponse = new ReadSuccessResponse(start,end,uidShort,data);
        assertArrayEquals(shortResponse.getPayload(),shortPayload);
    }

    @Test
    public void testGetCommandCode() throws Exception {
        ReadSuccessResponse readSuccessResponse = new ReadSuccessResponse();
        assertEquals(readSuccessResponse.getCommandCode(),0x01);
    }
}