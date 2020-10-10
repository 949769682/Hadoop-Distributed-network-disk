package club.hggzs.service.impl;

import club.hggzs.domain.FileInfo;
import club.hggzs.service.IHDFS;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HDFS implements IHDFS {
    private Configuration conf;
    private FileSystem fs;
    HDFS() throws IOException {
        conf = new Configuration(true);
        fs = FileSystem.get(conf);
    }
    public void close() throws IOException {
        fs.close();
    }

    public void upload(String resource,String target) throws IOException {
        fs.copyFromLocalFile(true,new Path(resource),new Path(target));

    }
    public InputStream open(String filepath) throws IOException {
        return fs.open(new Path(filepath));
    }
    public List<FileInfo> listFiles(String path) throws IOException {
        FileStatus[] fileStatus = fs.listStatus(new Path(path));
        List<FileInfo> fileInfos = new ArrayList<>();
        if(fileStatus == null){return null;}
        for(FileStatus file : fileStatus){
            FileInfo fileInfo = new FileInfo();
            fileInfo.setFilename(file.getPath().getName());
            fileInfo.setFilesize(file.getLen() / 1024 / 1024f +"M");
            String filetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(file.getModificationTime()));
            fileInfo.setFiletime(filetime);
            fileInfos.add(fileInfo);
        }

        return fileInfos;
    }
    public boolean delete(String filepath) throws IOException {
        return fs.delete(new Path(filepath),true);
    }
    public boolean exists(String path) throws IOException {
        return fs.exists(new Path(path));
    }
    public boolean mkdirs(String path) throws IOException {
        return fs.mkdirs(new Path(path));
    }
}
