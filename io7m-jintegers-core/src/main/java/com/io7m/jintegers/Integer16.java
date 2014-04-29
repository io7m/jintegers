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

package com.io7m.jintegers;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import com.io7m.jnull.NullCheck;
import com.io7m.junreachable.UnreachableCodeException;

/**
 * 16-bit integer packing/unpacking functions.
 */

public final class Integer16
{
  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>b</code> using a big-endian
   * encoding such that the most significant byte is in <code>b[0]</code>.
   * </p>
   * 
   * @param i
   *          The value to be packed.
   * @return A byte buffer containing the packed integer data.
   */

  public static byte[] packBigEndian(
    final int i)
  {
    final byte[] r = new byte[2];
    return Integer16.packBigEndianTo(i, r);
  }

  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>r</code> using a big-endian
   * encoding such that the most significant byte is in <code>r[0]</code>.
   * </p>
   * 
   * @param r
   *          The buffer.
   * @param i
   *          The value to be packed.
   * @return <code>r</code>
   */

  public static byte[] packBigEndianTo(
    final int i,
    final byte[] r)
  {
    NullCheck.notNull(r, "Buffer");
    if (r.length < 2) {
      throw new IllegalArgumentException("Buffer.length must be >= 2 (is "
        + r.length
        + ")");
    }

    int x = i;
    r[1] = (byte) (x & 0xff);
    x >>= 8;
    r[0] = (byte) (x & 0xff);
    return r;
  }

  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>r</code> using a big-endian
   * encoding such that the most significant byte is in <code>r[index]</code>.
   * </p>
   * 
   * @param r
   *          The buffer.
   * @param i
   *          The value to be packed.
   * @param index
   *          The starting index.
   * @return <code>r</code>
   */

  public static ByteBuffer packBigEndianToBuffer(
    final int i,
    final ByteBuffer r,
    final int index)
  {
    NullCheck.notNull(r, "Buffer");

    int x = i;
    r.put(index + 1, (byte) (x & 0xff));
    x >>= 8;
    r.put(index + 0, (byte) (x & 0xff));
    x >>= 8;
    return r;
  }

  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>b</code> using a
   * little-endian encoding such that the least significant byte is in
   * <code>b[0]</code>.
   * </p>
   * 
   * @param i
   *          The value to be packed.
   * @return A byte buffer containing the packed integer data.
   */

  public static byte[] packLittleEndian(
    final int i)
  {
    final byte[] r = new byte[2];
    return Integer16.packLittleEndianTo(i, r);
  }

  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>r</code> using a
   * little-endian encoding such that the least significant byte is in
   * <code>r[0]</code>.
   * </p>
   * 
   * @param r
   *          The buffer.
   * @param i
   *          The value to be packed.
   * @return <code>r</code>
   */

  public static byte[] packLittleEndianTo(
    final int i,
    final byte[] r)
  {
    NullCheck.notNull(r, "Buffer");
    if (r.length < 2) {
      throw new IllegalArgumentException("Buffer.length must be >= 2 (is "
        + r.length
        + ")");
    }

    int x = i;
    r[0] = (byte) (x & 0xff);
    x >>= 8;
    r[1] = (byte) (x & 0xff);
    x >>= 8;
    return r;
  }

  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>r</code> using a
   * little-endian encoding such that the least significant byte is in
   * <code>r[index]</code>.
   * </p>
   * 
   * @param r
   *          The buffer.
   * @param i
   *          The value to be packed.
   * @param index
   *          The starting index.
   * @return <code>r</code>
   */

  public static ByteBuffer packLittleEndianToBuffer(
    final int i,
    final ByteBuffer r,
    final int index)
  {
    NullCheck.notNull(r, "Buffer");

    int x = i;
    r.put(index + 0, (byte) (x & 0xff));
    x >>= 8;
    r.put(index + 1, (byte) (x & 0xff));
    x >>= 8;
    return r;
  }

  /**
   * <p>
   * Pack <code>i</code> into a byte buffer <code>r</code> using the encoding
   * returned by {@link ByteBuffer#order()}, starting at <code>index</code>.
   * </p>
   * 
   * @param r
   *          The buffer.
   * @param i
   *          The value to be packed.
   * @param index
   *          The starting index.
   * @return <code>r</code>
   */

  public static ByteBuffer packToBuffer(
    final int i,
    final ByteBuffer r,
    final int index)
  {
    NullCheck.notNull(r, "Buffer");

    if (r.order().equals(ByteOrder.BIG_ENDIAN)) {
      return Integer16.packBigEndianToBuffer(i, r, index);
    }
    return Integer16.packLittleEndianToBuffer(i, r, index);
  }

  /**
   * <p>
   * Unpack an integer from <code>buffer</code> assuming a big-endian encoding
   * such that the most significant byte is in <code>b[0]</code>.
   * </p>
   * <p>
   * The function throws {@link IllegalArgumentException} if
   * <code>buffer.length</code> is too small to contain a packed integer value
   * of this size.
   * </p>
   * 
   * @param buffer
   *          The buffer from which to unpack data.
   * @return A 16 bit integer value.
   */

  public static int unpackBigEndian(
    final byte[] buffer)
  {
    NullCheck.notNull(buffer, "Buffer");
    if (buffer.length < 2) {
      throw new IllegalArgumentException("Buffer.length must be >= 2 (is "
        + buffer.length
        + ")");
    }

    int r = (buffer[0] & 0xff);
    r <<= 8;
    r += (buffer[1] & 0xff);
    return r;
  }

  /**
   * <p>
   * Unpack an integer from <code>buffer</code> assuming a big-endian encoding
   * such that the most significant byte is in <code>b[index]</code>.
   * </p>
   * 
   * @param index
   *          The starting index in the buffer.
   * @param buffer
   *          The buffer from which to unpack data.
   * @return A 16 bit integer value.
   */

  public static int unpackBigEndianFromBuffer(
    final ByteBuffer buffer,
    final int index)
  {
    NullCheck.notNull(buffer, "Buffer");

    int r = (buffer.get(index) & 0xff);
    r <<= 8;
    r += (buffer.get(index + 1) & 0xff);
    return r;
  }

  /**
   * <p>
   * Unpack an integer from <code>buffer</code> using the encoding returned by
   * {@link ByteBuffer#order()}, starting at <code>index</code>.
   * </p>
   * 
   * @param index
   *          The starting index
   * @param buffer
   *          The buffer from which to unpack data.
   * @return A 16 bit integer value.
   */

  public static int unpackFromBuffer(
    final ByteBuffer buffer,
    final int index)
  {
    NullCheck.notNull(buffer, "Buffer");

    if (buffer.order().equals(ByteOrder.BIG_ENDIAN)) {
      return Integer16.unpackBigEndianFromBuffer(buffer, index);
    }
    return Integer16.unpackLittleEndianFromBuffer(buffer, index);
  }

  /**
   * <p>
   * Unpack an integer from <code>buffer</code> assuming a little-endian
   * encoding such that the least significant byte is in <code>b[0]</code>.
   * </p>
   * <p>
   * The function throws {@link IllegalArgumentException} if
   * <code>buffer.length</code> is too small to contain a packed integer value
   * of this size.
   * </p>
   * 
   * @param buffer
   *          The buffer from which to unpack data.
   * @return A 16 bit integer value.
   */

  public static int unpackLittleEndian(
    final byte[] buffer)
  {
    NullCheck.notNull(buffer, "Buffer");
    if (buffer.length < 2) {
      throw new IllegalArgumentException("Buffer.length must be >= 2 (is "
        + buffer.length
        + ")");
    }

    int r = (buffer[1] & 0xff);
    r <<= 8;
    r += (buffer[0] & 0xff);
    return r;
  }

  /**
   * <p>
   * Unpack an integer from <code>buffer</code> assuming a little-endian
   * encoding such that the least significant byte is in <code>b[index]</code>
   * .
   * </p>
   * 
   * @param index
   *          The starting index
   * @param buffer
   *          The buffer from which to unpack data.
   * @return A 16 bit integer value.
   */

  public static int unpackLittleEndianFromBuffer(
    final ByteBuffer buffer,
    final int index)
  {
    NullCheck.notNull(buffer, "Buffer");

    int r = (buffer.get(index + 1) & 0xff);
    r <<= 8;
    r += (buffer.get(index) & 0xff);
    return r;
  }

  private Integer16()
  {
    throw new UnreachableCodeException();
  }
}
