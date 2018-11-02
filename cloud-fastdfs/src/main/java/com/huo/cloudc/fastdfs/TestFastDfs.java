package com.huo.cloudc.fastdfs;

/**
 * <B>系统名称：</B><BR>
 * <B>模块名称：</B><BR>
 * <B>中文类名：</B><BR>
 * <B>概要说明：</B><BR>
 *
 * @author huozhenbin
 * @since
 */

import static org.csource.fastdfs.ProtoCommon.recvHeader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.csource.common.NameValuePair;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.FileInfo;
import org.csource.fastdfs.ProtoCommon;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;

import cn.hutool.core.io.FileUtil;

public class TestFastDfs {

    public static void main(String[] args) {
        testUpload();
    }

    static void a(Integer aa) {
        aa = new Integer(1);
    }

    //fdfs_client 核心配置文件
    public static String conf_filename = "src/main/resources/fdfs_client.conf";


    public static void testUpload() {    //上传文件
        File file = FileUtil.file("classpath:fdfs_client.conf");
        String absolutePath = file.getAbsolutePath();
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            ClientGlobal.init(absolutePath);
            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            //要上传的文件路径
            String local_filename = "C:\\Users\\Huo\\Desktop\\6.jpg";
//            这个参数可以指定，也可以不指定，如果指定了，可以根据 testGetFileMate()方法来获取到这里面的值
//            NameValuePair nvp [] = new NameValuePair[]{
//                    new NameValuePair("age", "18"),
//                    new NameValuePair("sex", "male")
//            };

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
//          String fileIds[] = storageClient.upload_file(local_filename, "png", nvp);
            String fileIds[] = storageClient.upload_file(local_filename, "png", null);


            System.out.println(fileIds.length);
            System.out.println("组名：" + fileIds[0]);
            System.out.println("路径: " + fileIds[1]);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void testDownload() {    //下载文件
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            byte[] bytes = storageClient.download_file(groupName, filePath);

            String storePath = "/Users/shenwei/Desktop/download.png";
            OutputStream out = new FileOutputStream(storePath);
            out.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void testGetFileInfo() { //获取文件信息
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer, storageServer);
            FileInfo file = storageClient.get_file_info(groupName, filePath);
            System.out.println("ip--->" + file.getSourceIpAddr());
            System.out.println("文件大小--->" + file.getFileSize());
            System.out.println("文件上传时间--->" + file.getCreateTimestamp());
            System.out.println(file.getCrc32());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void testGetFileMate() { //获取文件的原数据类型
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);

            //这个值是上传的时候指定的NameValuePair
            NameValuePair nvps[] = storageClient.get_metadata(groupName, filePath);
            if (null != nvps && nvps.length > 0) {
                for (NameValuePair nvp : nvps) {
                    System.out.println(nvp.getName() + ":" + nvp.getValue());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void testDelete() { //删除文件
        TrackerServer trackerServer = null;
        StorageServer storageServer = null;

        try {
            String groupName = "group1";
            String filePath = "M00/00/00/ZGIW_lpujW-ADvpRAAblmT4ACuo125.png";
            ClientGlobal.init(conf_filename);

            TrackerClient tracker = new TrackerClient();
            trackerServer = tracker.getConnection();

            StorageClient storageClient = new StorageClient(trackerServer,
                    storageServer);
            int i = storageClient.delete_file(groupName, filePath);
            System.out.println(i == 0 ? "删除成功" : "删除失败:" + i);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != storageServer) storageServer.close();
                if (null != trackerServer) trackerServer.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public static ProtoCommon.RecvPackageInfo recvPackage(InputStream in, byte expect_cmd, long expect_body_len)
            throws IOException {
        ProtoCommon.RecvHeaderInfo header = recvHeader(in, expect_cmd, expect_body_len);
        if (header.errno != 0) {
            return new ProtoCommon.RecvPackageInfo(header.errno, null);
        }

        byte[] body = new byte[(int) header.body_len];
        int totalBytes = 0;
        int remainBytes = (int) header.body_len;
        int bytes;

        while (totalBytes < header.body_len) {
            if ((bytes = in.read(body, totalBytes, remainBytes)) < 0) {
                break;
            }

            totalBytes += bytes;
            remainBytes -= bytes;
        }

        if (totalBytes != header.body_len) {
            throw new IOException("recv package size " + totalBytes + " != " + header.body_len);
        }

        return new ProtoCommon.RecvPackageInfo((byte) 0, body);
    }

}