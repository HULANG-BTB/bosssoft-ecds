package com.bosssoft.ecds.utils;


import com.bosssoft.ecds.domain.StringType;
import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.BinaryCodec;
import org.apache.commons.codec.binary.Hex;

/**
 * @author LiDaShan
 * @Version 1.0
 * @Date 2020/8/10
 * @Content: 编码，将字节数组转为字符串，使不可见字符��mZk\�转为可见字符
 */
public class ByteUtill {

    /**
     * 编码，将字节数组转为可见的字符串
     * @param data 需要编码的字节数组
     * @param type 采取何种编码方式
     * @return
     * @throws Exception
     */
    public static String encode(byte[] data, StringType type) throws Exception{
        switch (type) {
            case HEX:
                return Hex.encodeHexString(data);
            case ASCII:
                return BinaryCodec.toAsciiString(data);
            default:
                return Base64.encodeBase64String(data);
        }
    }

    /**
     * 解密，将字符串解码为原先的字节数组
     * @param data 需要解码的字符串数据
     * @param type 编码方式
     * @return
     * @throws Exception
     */
    public static byte[] decode(String data,StringType type) throws DecoderException {
        switch (type) {
            case HEX:
                return Hex.decodeHex(data.toCharArray());
            case ASCII:
                return BinaryCodec.fromAscii(data.getBytes());
            default:
                return Base64.decodeBase64(data);
        }
    }

}
