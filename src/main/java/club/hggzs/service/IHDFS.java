package club.hggzs.service;

import club.hggzs.domain.FileInfo;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IHDFS {
    void close() throws IOException;
    void upload(String resource,String target) throws IOException;
    InputStream open(String filepath) throws IOException;
    List<FileInfo> listFiles(String path) throws IOException;
    boolean delete(String filepath) throws IOException;
    boolean exists(String path) throws IOException;
    boolean mkdirs(String path) throws IOException;
}
