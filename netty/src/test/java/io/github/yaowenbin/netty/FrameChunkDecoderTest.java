package io.github.yaowenbin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import io.netty.handler.codec.TooLongFrameException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
class FrameChunkDecoderTest {

    @Test
    void frameChunkDecoder() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(9);
        }

        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(new FrameChunkDecoder(3));
        assertTrue(channel.writeInbound(input.readBytes(2)));

        try {
            channel.writeInbound(input.readBytes(4));
            fail();
        } catch (TooLongFrameException ignore) {
        }
        assertTrue(channel.writeInbound(input.readBytes(3)));
        assertTrue(channel.finish());


        assertEquals(buf.readSlice(2), channel.readInbound());
        assertEquals(buf.skipBytes(4).readSlice(3), channel.readInbound());
    }


}