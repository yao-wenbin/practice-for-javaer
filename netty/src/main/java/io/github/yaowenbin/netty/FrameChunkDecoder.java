package io.github.yaowenbin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.TooLongFrameException;

import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
public class FrameChunkDecoder extends ByteToMessageDecoder {

    public final int maxFrameLen;

    public FrameChunkDecoder(int maxFrameLen) {
        this.maxFrameLen = maxFrameLen;
    }

    /**
     * 如果一次性写入的消息大于maxFrame则抛出异常
     */

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> list) throws Exception {
        if (in.readableBytes() > maxFrameLen) {
            in.clear();
            throw new TooLongFrameException();
        }
        ByteBuf buf = in.readBytes(in.readableBytes());
        list.add(buf);
    }
}
