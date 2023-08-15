package io.github.yaowenbin.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

import java.util.List;

/**
 * @Author yaowenbin
 * @Date 2023/7/21
 */
public class AbsIntegerEncoder extends MessageToMessageEncoder<ByteBuf> {

    public static final int INT_LEN = 4;

    /**
     * 每一个字节的写入都会流经Channel，并且加入到byteBuf中，
     * - 使用while来限制长度，只有当达到一定长度之后再做处理
     * - 要使用readableBytes() 而不是readBytes()和readByte()两个API
     */
    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> out) throws Exception {
        while (byteBuf.readableBytes() >= INT_LEN) {
            out.add(Math.abs(byteBuf.readInt()));
        }
    }
}
