package com.zhenyulaw.jf.common.file;

public interface FileClient {

	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_COMPANY = "company";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_AD = "recommend";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_CASE = "case";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_THIRD = "third";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_VIDEO = "video";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_ACTIVITY = "activity";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_TEMPLATE = "template";
	/**
	 * 业务类型：company
	 */
	public static String BIZTYPE_TEMPLATE_FILE = "template_file";

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @param asyn
	 *            是否异步 ，默认同步
	 * @param suffix
	 *            文件后缀， 默认.jpg
	 * @param mark
	 *            水印文字
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType, boolean asyn, String suffix, String mark);

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null,默认同步，.jpg后缀
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType);

	/**
	 * 
	 * 保存一个本地文件，返回文件path，失败返回null,默认同步，.jpg后缀
	 * 
	 * @param localFileName
	 *            本地文件名
	 * @param bizType
	 *            业务类型
	 * @param mark
	 *            水印文字
	 * @return 文件path
	 */
	String saveFile(String localFileName, String bizType, String mark);

	/**
	 * 删除文件
	 * 
	 * @param localPathName
	 * @return
	 */
	public boolean deleteFile(String localPathName);

	/**
	 * 
	 * 获取完整http访问路径
	 * 
	 * @param path
	 *            文件path
	 * @return 完整路径
	 */
	String getFullPath(String path);

}
