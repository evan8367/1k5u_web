package com.zhenyulaw.jf.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.util.StringUtils;

public class FileUploader {
	private static AtomicLong counter = new AtomicLong(0L);

	public static MultipartFileParam uploadFile(HttpServletRequest request, String targetFolderPath) throws Exception {

		String prefix = "req_count:" + counter.incrementAndGet() + ":";
		System.out.println(prefix + "start !!!");

		// 使用 工具类解析相关参数，工具类代码见下面
		MultipartFileParam param = FileUploader.parse(request);
		System.out.println(prefix + "chunks= " + param.getChunks());
		System.out.println(prefix + "chunk= " + param.getChunk());
		System.out.println(prefix + "chunkSize= " + param.getParam().get("chunkSize"));
		// 这个必须与前端设定的值一致
		long chunkSize = 512 * 1024;

		if (param.isMultipart()) {

			String finalDirPath = targetFolderPath;
			String tempDirPath = finalDirPath;
			// String tempFileName = UUIDUtils.getFileName() + "." +
			// StringUtils.getFilenameExtension(param.getFileName());
			String tempFileName = param.getFileName();
			File confFile = new File(tempDirPath, tempFileName + ".conf");

			File tmpDir = new File(tempDirPath);
			File tmpFile = new File(tempDirPath, tempFileName);
			if (!tmpDir.exists()) {
				tmpDir.mkdirs();
			}

			RandomAccessFile accessTmpFile = new RandomAccessFile(tmpFile, "rw");
			RandomAccessFile accessConfFile = new RandomAccessFile(confFile, "rw");

			long offset = chunkSize * param.getChunk();
			// 定位到该分片的偏移量
			accessTmpFile.seek(offset);
			// 写入该分片数据
			accessTmpFile.write(param.getFileItem().get());

			// 把该分段标记为 true 表示完成
			System.out.println(prefix + "set part " + param.getChunk() + " complete");
			accessConfFile.setLength(param.getChunks());
			accessConfFile.seek(param.getChunk());
			accessConfFile.write(Byte.MAX_VALUE);

			// completeList 检查是否全部完成,如果数组里是否全部都是(全部分片都成功上传)
			byte[] completeList = FileUtils.readFileToByteArray(confFile);
			byte isComplete = Byte.MAX_VALUE;
			for (int i = 0; i < completeList.length && isComplete == Byte.MAX_VALUE; i++) {
				// 与运算, 如果有部分没有完成则 isComplete 不是 Byte.MAX_VALUE
				isComplete = (byte) (isComplete & completeList[i]);
				System.out.println(prefix + "check part " + i + " complete?:" + completeList[i]);
			}

			accessTmpFile.close();
			accessConfFile.close();

			// 完成
			if (Integer.valueOf(param.getChunks()) == 0
					|| Integer.valueOf(param.getChunk()) == (Integer.valueOf(param.getChunks()) - 1)) {
				if (confFile.exists()) {
					confFile.delete();
				}
				if (tmpFile.exists()) {
					String absoluteFileName = UUIDUtils.getFileName() + "."
							+ StringUtils.getFilenameExtension(param.getFileName());
					param.setAbsoluteFileName(absoluteFileName);
					File newFile = new File(finalDirPath + absoluteFileName);
					tmpFile.renameTo(newFile);
					param.setPath(newFile.getAbsolutePath());
					return param;
				}
			}

			if (isComplete == Byte.MAX_VALUE) {

				System.out.println(prefix + "upload complete !!");

			}
		}

		System.out.println(prefix + "end !!!");

		return null;
	}

	/**
	 * 在HttpServletRequest中获取分段上传文件请求的信息
	 * 
	 * @param request
	 * @return
	 */
	private static MultipartFileParam parse(HttpServletRequest request) throws Exception {
		MultipartFileParam param = new MultipartFileParam();

		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		param.setMultipart(isMultipart);
		if (isMultipart) {
			FileItemFactory factory = new DiskFileItemFactory();
			ServletFileUpload upload = new ServletFileUpload(factory);
			// 得到所有的表单域，它们目前都被当作FileItem
			List<FileItem> fileItems = upload.parseRequest(request);
			for (FileItem fileItem : fileItems) {
				System.out.println("field name has:" + fileItem.getFieldName());
				if (!"file".equals(fileItem.getFieldName())) {
					System.out.println("field val has:" + fileItem.getString());
				}

				if (fileItem.getFieldName().equals("id")) {
					param.setId(fileItem.getString());
				} else if (fileItem.getFieldName().equals("name")) {
					param.setFileName(new String(fileItem.getString().getBytes("ISO-8859-1"), "UTF-8"));
				} else if (fileItem.getFieldName().equals("chunks")) {
					param.setChunks(NumberUtils.toInt(fileItem.getString()));
				} else if (fileItem.getFieldName().equals("chunk")) {
					param.setChunk(NumberUtils.toInt(fileItem.getString()));
				} else if (fileItem.getFieldName().equals("file")) {
					param.setFileItem(fileItem);
				} else if (fileItem.getFieldName().equals("size")) {
					param.setSize(NumberUtils.toInt(fileItem.getString()));
				} else {
					param.getParam().put(fileItem.getFieldName(), fileItem.getString());
				}
			}
		}

		return param;
	}

	public static boolean deleteFile(String fileName) {
		File file = new File(fileName);
		if (file.isFile() && file.exists()) {
			file.delete();// "删除单个文件"+name+"成功！"
			return true;
		} // "删除单个文件"+name+"失败！"
		return false;
	}

	public static void copyFile(String oldPath, String newPath) {
		try {
			String newFileFolderPath = newPath.substring(0, newPath.lastIndexOf("/")+1);
			File newFileFolder = new File(newFileFolderPath);
			if(!newFileFolder.exists()) {
				newFileFolder.mkdirs();
			}
			int bytesum = 0;
			int byteread = 0;
			File oldfile = new File(oldPath);
			if (oldfile.exists()) { // 文件存在时
				InputStream inStream = new FileInputStream(oldPath); // 读入原文件
				FileOutputStream fs = new FileOutputStream(newPath);
				byte[] buffer = new byte[1444];
				int length;
				while ((byteread = inStream.read(buffer)) != -1) {
					bytesum += byteread; // 字节数 文件大小
					System.out.println(bytesum);
					fs.write(buffer, 0, byteread);
				}
				inStream.close();
			}
		} catch (Exception e) {
			System.out.println("复制单个文件操作出错");
			e.printStackTrace();

		}

	}

	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
}
