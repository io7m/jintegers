/*
 * Copyright © 2014 Mark Raynsford <code@io7m.com> https://www.io7m.com
 * 
 * Permission to use, copy, modify, and/or distribute this software for any
 * purpose with or without fee is hereby granted, provided that the above
 * copyright notice and this permission notice appear in all copies.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
 * WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
 * MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
 * SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
 * WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
 * ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
 * IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 */

package com.io7m.jintegers.tests;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.junit.Assert;
import org.junit.Test;

import com.io7m.jintegers.Signed16;

@SuppressWarnings({ "null", "static-method" }) public class Signed16Test
{
  @Test public void testIdentityBEInferred_BufferAll()
  {
    for (int x = Short.MIN_VALUE; x <= Short.MAX_VALUE; ++x) {
      final ByteBuffer b = ByteBuffer.allocate(2).order(ByteOrder.BIG_ENDIAN);
      Signed16.packToBuffer(x, b, 0);
      final int y = Signed16.unpackFromBuffer(b, 0);
      Assert.assertEquals(x, y);
    }
  }

  @Test public void testIdentityLEInferred_BufferAll()
  {
    for (int x = Short.MIN_VALUE; x <= Short.MAX_VALUE; ++x) {
      final ByteBuffer b =
        ByteBuffer.allocate(2).order(ByteOrder.LITTLE_ENDIAN);
      Signed16.packToBuffer(x, b, 0);
      final int y = Signed16.unpackFromBuffer(b, 0);
      Assert.assertEquals(x, y);
    }
  }

  @Test public void testIdentityBE_BufferAll()
  {
    for (int x = Short.MIN_VALUE; x <= Short.MAX_VALUE; ++x) {
      final ByteBuffer b = ByteBuffer.allocate(2);
      Signed16.packToBufferBigEndian(x, b, 0);
      final int y = Signed16.unpackFromBufferBigEndian(b, 0);
      Assert.assertEquals(x, y);
    }
  }

  @Test public void testIdentityLE_BufferAll()
  {
    for (int x = Short.MIN_VALUE; x <= Short.MAX_VALUE; ++x) {
      final ByteBuffer b = ByteBuffer.allocate(2);
      Signed16.packToBufferLittleEndian(x, b, 0);
      final int y = Signed16.unpackFromBufferLittleEndian(b, 0);
      Assert.assertEquals(x, y);
    }
  }

  @Test public void testIdentityBE_All()
  {
    for (int x = Short.MIN_VALUE; x <= Short.MAX_VALUE; ++x) {
      final byte[] b = Signed16.packToBytesBigEndianAllocate(x);
      final int y = Signed16.unpackFromBytesBigEndian(b);
      Assert.assertEquals(x, y);
    }
  }

  @Test public void testIdentityLE_All()
  {
    for (int x = Short.MIN_VALUE; x <= Short.MAX_VALUE; ++x) {
      final byte[] b = Signed16.packToBytesLittleEndianAllocate(x);
      final int y = Signed16.unpackFromBytesLittleEndian(b);
      Assert.assertEquals(x, y);
    }
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_0()
  {
    Signed16.unpackFromBytesBigEndian(new byte[1]);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_1()
  {
    Signed16.unpackFromBytesLittleEndian(new byte[1]);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_2()
  {
    Signed16.packToBytesBigEndian(0, new byte[1]);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_3()
  {
    Signed16.packToBytesLittleEndian(0, new byte[1]);
  }
}
