package com.example.testutils.util;

import java.io.File;

/**
 * 显示缓存文件大小和清除缓存文件工具
 * <p>
 * 注：需要在子线程中进行此操作
 */
public class CleanCacheUtil {

    /**
     * 递归删除文件和文件夹
     *
     * @param file 要删除的文件根目录
     */
    public static void recursionDeleteFile(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }

        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }

            for (File f : childFile) {
                recursionDeleteFile(f);
            }
        }
    }

    /**
     * 获取缓存文件的大小
     *
     * @param file file
     * @return Mb
     * @throws Exception e
     */
    public static long getFileSize(File file) throws Exception {
        long size = 0;
        File[] filelist = file.listFiles();
        if (filelist != null) {
            for (int i = 0; i < file.length(); i++) {
                File sunFile = filelist[i];

                if (sunFile.isDirectory()) {
                    size = size + getFileSize(sunFile);
                } else {
                    size = size + sunFile.length();
                }
            }
        }

        return size;
    }
}
