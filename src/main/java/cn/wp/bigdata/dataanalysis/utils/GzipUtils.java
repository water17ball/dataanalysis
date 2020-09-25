package cn.wp.bigdata.dataanalysis.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * @author renlc
 */
public class GzipUtils {

    public static final String GZIP_ENCODE_UTF_8 = "UTF-8";

    public static final String GZIP_ENCODE_ISO_8859_1 = "ISO-8859-1";

    /**
     * 字符串压缩为 gzip 字节数组，字符串编码默认使用 UTF-8
     *
     * @param str 要压缩的字符串
     * @return gzip 压缩后的 byte 数组；字符串为null返回 null
     * @throws IOException 压缩失败
     */
    public static byte[] compress(String str) throws IOException {
        return compress(str, GZIP_ENCODE_UTF_8);
    }

    /**
     * 字符串压缩为 gzip 字节数组
     *
     * @param str      要压缩的字符串
     * @param encoding 字符串编码
     * @return gzip 压缩后的 byte 数组；字符串为null返回 null
     * @throws IOException 压缩失败
     */
    public static byte[] compress(String str, String encoding) throws IOException {
        if (str == null) {
            return null;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(str.getBytes(encoding));
            gzip.finish();
            return out.toByteArray();
        }
    }

    /**
     * 将 gzip 字符数组解压
     *
     * @param bytes 要解压的字符数组
     * @return gzip 解压后的 byte 数组；bytes数组为null或者长度为空返回 null
     * @throws IOException 解压缩失败
     */
    public static byte[] uncompress(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        int bufferSize = 1024;
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(bytes);
             GZIPInputStream ungzip = new GZIPInputStream(in, bufferSize)) {
            byte[] buffer = new byte[bufferSize];
            int n;
            while ((n = ungzip.read(buffer)) > 0) {
                out.write(buffer, 0, n);
            }
            out.flush();
            return out.toByteArray();
        }
    }
}
