package cn.eric.myapplication.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.FileChannel;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import cn.eric.basiclib.global.Configurator;


public class FileHelper
{
    public static boolean fileExists(String file_path)
    {
        boolean value = false;

        File current_file = new File(file_path);
        try {
            value = current_file.exists();
        } catch (Exception e) {
            System.out.printf("check %s exists failed!", file_path);
            e.printStackTrace();
        }

        return value;
    }

    public static boolean deleteFile(String file_path)
    {
        boolean value = false;

        File current_file = new File(file_path);
        try {
            value = current_file.delete();
        } catch (Exception e) {
            System.out.printf("delete %s failed!", file_path);
            e.printStackTrace();
        }

        return value;
    }

    public static boolean copyFileToFile(String ori_file_path, String dst_file_path, boolean delete_dst_file)
    {
        if (delete_dst_file) {
            deleteFile(dst_file_path);
        }

        if (!fileExists(dst_file_path)) {
            FileChannel input_channel = null;
            FileChannel output_channel = null;
            try {
                input_channel = new FileInputStream(ori_file_path).getChannel();
                output_channel = new FileOutputStream(dst_file_path).getChannel();
                output_channel.transferFrom(input_channel, 0, input_channel.size());
            } catch (Exception e) {
                try {
                    input_channel.close();
                    output_channel.close();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }

                System.out.printf("copy %s to %s failed!", ori_file_path, dst_file_path);
                e.printStackTrace();

                deleteFile(dst_file_path);

                return false;
            }
        }

        if (!fileExists(dst_file_path)) {
            return false;
        }

        return true;
    }

    public static boolean copyFileInPackageToFile(String file_name_in_package, String dst_file_path, boolean delete_dst_file)
    {
        if (delete_dst_file) {
            deleteFile(dst_file_path);
        }

        if (!fileExists(dst_file_path))
        {
            InputStream input_stream;
            OutputStream output_stream;
            try {
                input_stream = Configurator.get().getAppContext().getAssets().open(file_name_in_package);
                output_stream = new FileOutputStream(dst_file_path);
                byte data[] = new byte[1024];
                long total = 0;
                int count;

                while ((count = input_stream.read(data)) != -1) {
                    total += count;
                    output_stream.write(data, 0, count);
                }

                output_stream.flush();
                output_stream.close();
                input_stream.close();
            } catch (IOException e) {

                System.out.printf("copy %s in package to %s failed!", file_name_in_package, dst_file_path);
                e.printStackTrace();

                deleteFile(dst_file_path);
            }
        }

        if (!fileExists(dst_file_path)) {
            return false;
        }

        return true;
    }

    public static void createDirectory(String directory)
    {
        File folder = new File(directory);
        if (!folder.isDirectory() || !folder.exists()) {
            folder.mkdir();
        }
    }

    public static void deleteAllFilesInDirectory(String directory)
    {
        File file_directory = new File(directory);
        File[] array = file_directory.listFiles();
        if (array != null) {
            for (int i = 0; i < array.length; i++){
                if(array[i].isFile()){
                    array[i].delete();
                }
            }
        }
    }

    public static boolean isFileTheSame(String file_path1, String file_path2)
    {
        boolean ret = false;

        try {
            String md5_file1 = FileHelper.getMd5StringForFile(file_path1);
            String md5_file2 = FileHelper.getMd5StringForFile(file_path2);

            ret = (md5_file1.compareToIgnoreCase(md5_file2) == 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ret;
    }

    public static String getMd5StringForFile(String file_path){
        try {
            File file = new File(file_path);
            FileInputStream fis = new FileInputStream(file);

            MessageDigest message_digest = MessageDigest.getInstance("MD5");
            DigestInputStream dis = new DigestInputStream(fis, message_digest);

            byte[] buffer = new byte[1024];
            while (dis.read(buffer) > 0) ;

            message_digest = dis.getMessageDigest();
            byte[] array = message_digest.digest();
            StringBuilder hex = new StringBuilder(array.length * 2);

            for (byte b : array) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }

            return hex.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    public static long getFileSize(File file){
        long size = 0;

        try {
            if (file.exists()) {
                FileInputStream fis = null;
                fis = new FileInputStream(file);
                size = fis.available();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return size;
    }
}