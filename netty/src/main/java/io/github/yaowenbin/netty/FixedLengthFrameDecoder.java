package io.github.yaowenbin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 * 入站消息是解码器Decoder，因为消息是字节数组，你需要将其解码为能够识别的消息
 */
public class FixedLengthFrameDecoder extends ByteToMessageDecoder {

    private final int frameLen;

    public FixedLengthFrameDecoder(int frameLength) {
        this.frameLen = frameLength;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        while (in.readableBytes() >= frameLen) {
            ByteBuf msg = in.readBytes(frameLen);
            out.add(msg);
        }
    }
}
