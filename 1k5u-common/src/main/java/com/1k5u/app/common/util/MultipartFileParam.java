package com.zhenyulaw.jf.common.util;

import java.util.HashMap;
import org.apache.commons.fileupload.FileItem;

public class MultipartFileParam {
	//该请求是否是multipart
    private boolean isMultipart;
    //任务ID
    private String id;
    //总分片数量
    private int chunks;
    //当前为第几块分片
    private int chunk;
    //当前分片大小
    private long size = 0L;
    //文件名
    private String fileName;
    // 物理文件名
    private String absoluteFileName;
    //分片对象
    private FileItem fileItem;
    //请求中附带的自定义参数
    private HashMap<String, String> param = new HashMap<>();
    // 文件上传后的路径
    private String path;
    
    
    public boolean isMultipart() {
        return isMultipart;
    }

    public void setMultipart(boolean multipart) {
        isMultipart = multipart;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getChunks() {
        return chunks;
    }

    public void setChunks(int chunks) {
        this.chunks = chunks;
    }

    public int getChunk() {
        return chunk;
    }

    public void setChunk(int chunk) {
        this.chunk = chunk;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAbsoluteFileName() {
		return absoluteFileName;
	}

	public void setAbsoluteFileName(String absoluteFileName) {
		this.absoluteFileName = absoluteFileName;
	}

	public FileItem getFileItem() {
        return fileItem;
    }

    public void setFileItem(FileItem fileItem) {
        this.fileItem = fileItem;
    }

    public HashMap<String, String> getParam() {
        return param;
    }

    public void setParam(HashMap<String, String> param) {
        this.param = param;
    }

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
}
