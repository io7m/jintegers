/*
 * Copyright © 2014 <code@io7m.com> http://io7m.com
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

import com.io7m.jintegers.Integer16;

@SuppressWarnings({ "null", "static-method" }) public class Integer16Test
{
  @Test public void testBufferIdentityBE_0()
  {
    final int x = 0;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packBigEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackBigEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityBE_0x7fff()
  {
    final int x = 0x7fff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packBigEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackBigEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityBE_0xff()
  {
    final int x = 0xff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packBigEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackBigEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityBE_0xffff()
  {
    final int x = 0xffff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packBigEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackBigEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityBEInferred_0x7fff()
  {
    final int x = 0x7fff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    buffer.order(ByteOrder.BIG_ENDIAN);
    Integer16.packToBuffer(x, buffer, 0);
    final int y = Integer16.unpackFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityLE_0()
  {
    final int x = 0;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packLittleEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackLittleEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityLE_0x7fff()
  {
    final int x = 0x7fff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packLittleEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackLittleEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityLE_0xff()
  {
    final int x = 0xff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packLittleEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackLittleEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityLE_0xffff()
  {
    final int x = 0xffff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    Integer16.packLittleEndianToBuffer(x, buffer, 0);
    final int y = Integer16.unpackLittleEndianFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testBufferIdentityLEInferred_0x7fff()
  {
    final int x = 0x7fff;
    final ByteBuffer buffer = ByteBuffer.allocate(2);
    buffer.order(ByteOrder.LITTLE_ENDIAN);
    Integer16.packToBuffer(x, buffer, 0);
    final int y = Integer16.unpackFromBuffer(buffer, 0);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityBE_0()
  {
    final int x = 0;
    final byte[] b = Integer16.packBigEndian(x);
    final int y = Integer16.unpackBigEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityBE_0x7fff()
  {
    final int x = 0x7fff;
    final byte[] b = Integer16.packBigEndian(x);
    final int y = Integer16.unpackBigEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityBE_0xff()
  {
    final int x = 0xff;
    final byte[] b = Integer16.packBigEndian(x);
    final int y = Integer16.unpackBigEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityBE_0xffff()
  {
    final int x = 0xffff;
    final byte[] b = Integer16.packBigEndian(x);
    final int y = Integer16.unpackBigEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityLE_0()
  {
    final int x = 0;
    final byte[] b = Integer16.packLittleEndian(x);
    final int y = Integer16.unpackLittleEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityLE_0x7fff()
  {
    final int x = 0x7fff;
    final byte[] b = Integer16.packLittleEndian(x);
    final int y = Integer16.unpackLittleEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityLE_0xff()
  {
    final int x = 0xff;
    final byte[] b = Integer16.packLittleEndian(x);
    final int y = Integer16.unpackLittleEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test public void testIdentityLE_0xffff()
  {
    final int x = 0xffff;
    final byte[] b = Integer16.packLittleEndian(x);
    final int y = Integer16.unpackLittleEndian(b);
    Assert.assertEquals(x, y);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_0()
  {
    Integer16.unpackBigEndian(new byte[1]);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_1()
  {
    Integer16.unpackLittleEndian(new byte[1]);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_2()
  {
    Integer16.packBigEndianTo(0, new byte[1]);
  }

  @Test(expected = IllegalArgumentException.class) public
    void
    testTooSmall_3()
  {
    Integer16.packLittleEndianTo(0, new byte[1]);
  }
}
