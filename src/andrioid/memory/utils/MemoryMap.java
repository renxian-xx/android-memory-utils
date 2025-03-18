package andrioid.memory.utils;

public class MemoryMap {
    private long start;
    private long end;
    private String chmod;
    private long offset;
    private String dev;
    private String inode;
    private String pathname;

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getSize() {
        return end - start;
    }

    public String getChmod() {
        return chmod;
    }

    public void setChmod(String chmod) {
        this.chmod = chmod;
    }

    public long getOffset() {
        return offset;
    }

    public void setOffset(long offset) {
        this.offset = offset;
    }

    public String getDev() {
        return dev;
    }

    public void setDev(String dev) {
        this.dev = dev;
    }

    public String getInode() {
        return inode;
    }

    public void setInode(String inode) {
        this.inode = inode;
    }

    public String getPathname() {
        return pathname;
    }

    public void setPathname(String pathname) {
        this.pathname = pathname;
    }

    @Override
    public String toString() {
        return Long.toString(start, 16) + "-" + Long.toString(end, 16) + " " + Long.toString(getSize(), 16) + " " + chmod + " " + offset + " " + dev + " " + inode + " " + pathname;
    }
}
