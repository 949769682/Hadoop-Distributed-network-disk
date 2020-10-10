package club.hggzs.domain;


public class FileInfo {
    private String filename;
    private String filesize;
    private String filetime;
    public FileInfo() {}
    public FileInfo(String filename, String filesize, String filetime) {
        this.filename = filename;
        this.filesize = filesize;
        this.filetime = filetime;
    }

    @Override
    public String toString() {
        return "FileInfo{" +
                "filename='" + filename + '\'' +
                ", filesize='" + filesize + '\'' +
                ", filetime='" + filetime + '\'' +
                '}';
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilesize() {
        return filesize;
    }

    public void setFilesize(String filesize) {
        this.filesize = filesize;
    }

    public String getFiletime() {
        return filetime;
    }

    public void setFiletime(String filetime) {
        this.filetime = filetime;
    }
}
