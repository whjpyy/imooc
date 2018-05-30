package com.imooc.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.InputBuffer;
import org.apache.hadoop.util.Progressable;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by youzeng
 * time: 2018-05-29 00:42
 */
public class HDFSAppTest {
    FileSystem fileSystem = null;
    Configuration configuration = null;

    public static final String HDFS_PATH = "hdfs://192.168.31.111:8020";

    /**
     * 创建HDFS目录
     *
     * @throws Exception
     */
    @Test
    public void mkdir() throws Exception {
        fileSystem.mkdirs(new Path("/hdfsapi/test"));
    }

    /**
     * 创建文件
     *
     * @throws Exception
     */
    @Test
    public void create() throws Exception {
        FSDataOutputStream output = fileSystem.create(new Path("/hdfsapi/test/a.txt"));
        output.write("hello hadoop".getBytes());
        output.flush();
        output.close();
    }

    /**
     * 查看文件
     * @throws Exception
     */
    @Test
    public void cat() throws Exception {
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/test/a.txt"));
        IOUtils.copyBytes(in, System.out, 1024);
        in.close();
    }

    /**
     * 重命名
     * @throws Exception
     */
    @Test
    public void rename() throws Exception {
        Path oldPath = new Path("/hdfsapi/test/a.txt");
        Path newPath = new Path("/hdfsapi/test/b.txt");
        fileSystem.rename(oldPath, newPath);
    }

    /**
     * 上传文件
     * @throws Exception
     */
    @Test
    public void copyFromLocalFile() throws Exception {
        Path localPath = new Path("C:\\Users\\YouZeng\\Desktop\\mmall.postman_collection.json");
        Path hdfsPath = new Path("/hdfsapi/test/mmall.postman_collection.json");
        fileSystem.copyFromLocalFile(localPath, hdfsPath);

    }

    /**
     * 有进度条的上传文件
     * @throws Exception
     */
    @Test
    public void copyFromLocalFileProgress() throws Exception {
        InputStream in = new BufferedInputStream(
                new FileInputStream(
                        new File("E:\\Downloads\\Develop\\VirtualBox-5.1.4-110228-Win.exe")));

        FSDataOutputStream output = fileSystem.create(new Path("/hdfsapi/test/VirtualBox")
                , new Progressable() {
                    @Override
                    public void progress() {
                        System.out.println("."); // 进度提醒信息
                    }
                });
        IOUtils.copyBytes(in, output, 4096);
    }

    /**
     * 下载文件
     * @throws Exception
     */
    @Test
    public void copyToLocalFile() throws Exception{
        Path localPath = new Path("C:\\Users\\YouZeng\\Documents\\h.txt");
        Path hdfs = new Path("/hdfsapi/test/b.txt");
        fileSystem.copyToLocalFile(false, hdfs, localPath, true);
    }

    /**
     * 查看某个目录下的文件
     * @throws Exception
     */
    @Test
    public void listFiles() throws Exception{
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/hdfsapi/test"));
        for (FileStatus fileStatus : fileStatuses){
            String isDir = fileStatus.isDirectory() ? "文件夹" : "文件";
            short replication = fileStatus.getReplication();
            long len = fileStatus.getLen();
            String path = fileStatus.getPath().toString();
            System.out.println(isDir + "\t" + replication + "\t" + len + "\t" + path);
        }
    }

    @Test
    public void delete() throws Exception {
        fileSystem.delete(new Path("/hdfsapi/test/mmall.postman_collection.json"));
    }


    @Before
    public void setUp() throws Exception {
        configuration = new Configuration();
        fileSystem = FileSystem.get(new URI(HDFS_PATH), configuration, "hadoop");
        System.out.println("HDFSAppTest.setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("HDFSAppTest.tearDown");
    }
}
