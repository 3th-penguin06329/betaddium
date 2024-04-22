package me.jellysquid.mods.sodium.client.render.chunk.format.full;

import me.jellysquid.mods.sodium.client.model.vertex.buffer.VertexBufferView;
import me.jellysquid.mods.sodium.client.model.vertex.buffer.VertexBufferWriterUnsafe;
import me.jellysquid.mods.sodium.client.render.chunk.format.ChunkModelVertexFormats;
import me.jellysquid.mods.sodium.client.render.chunk.format.ModelVertexSink;
import org.lwjgl.system.MemoryUtil;

public class VanillaModelVertexBufferWriterUnsafe extends VertexBufferWriterUnsafe implements ModelVertexSink {
    public VanillaModelVertexBufferWriterUnsafe(VertexBufferView backingBuffer) {
        super(backingBuffer, ChunkModelVertexFormats.VANILLA_LIKE);
    }

    @Override
    public void writeVertex(float posX, float posY, float posZ, int color, float u, float v, int light, int chunkId) {
        long i = this.writePointer;

        MemoryUtil.memPutFloat(i + 0, posX);
        MemoryUtil.memPutFloat(i + 4, posY);
        MemoryUtil.memPutFloat(i + 8, posZ);
        MemoryUtil.memPutInt(i + 12, color);

        MemoryUtil.memPutFloat(i + 16, VanillaModelVertexType.encodeBlockTexture(u));
        MemoryUtil.memPutFloat(i + 20, VanillaModelVertexType.encodeBlockTexture(v));

        // Light, draw ID
        MemoryUtil.memPutInt(i + 24, (VanillaModelVertexType.encodeLight(light) << 16) | (chunkId & 0xFF));

        this.advance();
    }
}
