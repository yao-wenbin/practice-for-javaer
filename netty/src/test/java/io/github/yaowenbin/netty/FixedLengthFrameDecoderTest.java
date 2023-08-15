package io.github.yaowenbin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
public class FixedLengthFrameDecoderTest {

    @Test
    void shouldDecodeAsFixedLen() {
        ByteBuf buf = Unpooled.buffer();
        for (int i = 0; i < 9; i++) {
            buf.writeByte(i);
        }
        ByteBuf input = buf.duplicate();
        EmbeddedChannel channel = new EmbeddedChannel(
            new FixedLengthFrameDecoder(3));
        // write bytes
        assertTrue(channel.writeInbound(input.retain()));
        assertTrue(channel.finish());


        assertEquals(buf.readSlice(3), channel.readInbound());
        assertEquals(buf.readSlice(3), channel.readInbound());
        assertEquals(buf.readSlice(3), channel.readInbound());

        assertNull(channel.readInbound());
        buf.release();
    }

}
