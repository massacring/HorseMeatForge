package com.mojang.blaze3d.platform;

import com.mojang.blaze3d.DontObfuscate;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.logging.LogUtils;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.Path;
import java.util.concurrent.ThreadLocalRandom;
import net.minecraft.SharedConstants;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;
import org.slf4j.Logger;

@OnlyIn(Dist.CLIENT)
@DontObfuscate
public class TextureUtil {
   private static final Logger LOGGER = LogUtils.getLogger();
   public static final int MIN_MIPMAP_LEVEL = 0;
   private static final int DEFAULT_IMAGE_BUFFER_SIZE = 8192;

   public static int generateTextureId() {
      RenderSystem.assertOnRenderThreadOrInit();
      if (SharedConstants.IS_RUNNING_IN_IDE) {
         int[] aint = new int[ThreadLocalRandom.current().nextInt(15) + 1];
         GlStateManager._genTextures(aint);
         int i = GlStateManager._genTexture();
         GlStateManager._deleteTextures(aint);
         return i;
      } else {
         return GlStateManager._genTexture();
      }
   }

   public static void releaseTextureId(int p_85282_) {
      RenderSystem.assertOnRenderThreadOrInit();
      GlStateManager._deleteTexture(p_85282_);
   }

   public static void prepareImage(int p_85284_, int p_85285_, int p_85286_) {
      prepareImage(NativeImage.InternalGlFormat.RGBA, p_85284_, 0, p_85285_, p_85286_);
   }

   public static void prepareImage(NativeImage.InternalGlFormat p_85293_, int p_85294_, int p_85295_, int p_85296_) {
      prepareImage(p_85293_, p_85294_, 0, p_85295_, p_85296_);
   }

   public static void prepareImage(int p_85288_, int p_85289_, int p_85290_, int p_85291_) {
      prepareImage(NativeImage.InternalGlFormat.RGBA, p_85288_, p_85289_, p_85290_, p_85291_);
   }

   public static void prepareImage(NativeImage.InternalGlFormat p_85298_, int p_85299_, int p_85300_, int p_85301_, int p_85302_) {
      RenderSystem.assertOnRenderThreadOrInit();
      bind(p_85299_);
      if (p_85300_ >= 0) {
         GlStateManager._texParameter(3553, 33085, p_85300_);
         GlStateManager._texParameter(3553, 33082, 0);
         GlStateManager._texParameter(3553, 33083, p_85300_);
         GlStateManager._texParameter(3553, 34049, 0.0F);
      }

      for(int i = 0; i <= p_85300_; ++i) {
         GlStateManager._texImage2D(3553, i, p_85298_.glFormat(), p_85301_ >> i, p_85302_ >> i, 0, 6408, 5121, (IntBuffer)null);
      }

   }

   private static void bind(int p_85310_) {
      RenderSystem.assertOnRenderThreadOrInit();
      GlStateManager._bindTexture(p_85310_);
   }

   public static ByteBuffer readResource(InputStream p_85304_) throws IOException {
      ByteBuffer bytebuffer;
      if (p_85304_ instanceof FileInputStream fileinputstream) {
         FileChannel filechannel = fileinputstream.getChannel();
         bytebuffer = MemoryUtil.memAlloc((int)filechannel.size() + 1);

         while(filechannel.read(bytebuffer) != -1) {
         }
      } else {
         bytebuffer = MemoryUtil.memAlloc(8192);
         ReadableByteChannel readablebytechannel = Channels.newChannel(p_85304_);

         while(readablebytechannel.read(bytebuffer) != -1) {
            if (bytebuffer.remaining() == 0) {
               bytebuffer = MemoryUtil.memRealloc(bytebuffer, bytebuffer.capacity() * 2);
            }
         }
      }

      return bytebuffer;
   }

   public static void writeAsPNG(Path p_261923_, String p_262070_, int p_261655_, int p_261576_, int p_261966_, int p_261775_) {
      RenderSystem.assertOnRenderThread();
      bind(p_261655_);

      for(int i = 0; i <= p_261576_; ++i) {
         int j = p_261966_ >> i;
         int k = p_261775_ >> i;

         try (NativeImage nativeimage = new NativeImage(j, k, false)) {
            nativeimage.downloadTexture(i, false);
            Path path = p_261923_.resolve(p_262070_ + "_" + i + ".png");
            nativeimage.writeToFile(path);
            LOGGER.debug("Exported png to: {}", (Object)path.toAbsolutePath());
         } catch (IOException ioexception) {
            LOGGER.debug("Unable to write: ", (Throwable)ioexception);
         }
      }

   }

   public static void initTexture(IntBuffer p_85306_, int p_85307_, int p_85308_) {
      RenderSystem.assertOnRenderThread();
      GL11.glPixelStorei(3312, 0);
      GL11.glPixelStorei(3313, 0);
      GL11.glPixelStorei(3314, 0);
      GL11.glPixelStorei(3315, 0);
      GL11.glPixelStorei(3316, 0);
      GL11.glPixelStorei(3317, 4);
      GL11.glTexImage2D(3553, 0, 6408, p_85307_, p_85308_, 0, 32993, 33639, p_85306_);
      GL11.glTexParameteri(3553, 10240, 9728);
      GL11.glTexParameteri(3553, 10241, 9729);
   }

   public static Path getDebugTexturePath(Path p_262015_) {
      return p_262015_.resolve("screenshots").resolve("debug");
   }

   public static Path getDebugTexturePath() {
      return getDebugTexturePath(Path.of("."));
   }
}