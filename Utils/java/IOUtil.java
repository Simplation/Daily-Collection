package com.example.testutils.util;

import java.io.*;

/**
 * IO工具类
 */
public class IOUtil {

    // 将传入的对象转换成字节数组obj
    public static byte[] toByteArray(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;

        try {
            oos = new ObjectOutputStream(baos);
            oos.writeObject((obj));
            oos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭流操作
            if (oos != null) {
                try {
                    oos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // 将字节数组转换成对象
    public static Object toObject(byte[] data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(data);
        ObjectInputStream ois = null;

        try {
            ois = new ObjectInputStream(bais);
            ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 关闭流操作
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    // 传入输入流读取数据存储在字节数组中，并返回
    public static byte[] getByteArray(InputStream inputStream) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] bytes = new byte[1024];

        try {
            int len = inputStream.read();
            while (len != -1) {
                baos.write(bytes, 0, len);
                len = inputStream.read(bytes);
            }

            baos.flush();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
