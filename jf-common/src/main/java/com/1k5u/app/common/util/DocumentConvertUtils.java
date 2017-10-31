package com.zhenyulaw.jf.common.util;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;

import javax.imageio.ImageIO;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.apache.poi.hslf.usermodel.HSLFShape;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.hslf.usermodel.HSLFSlideShowImpl;
import org.apache.poi.hslf.usermodel.HSLFTextParagraph;
import org.apache.poi.hslf.usermodel.HSLFTextRun;
import org.apache.poi.hslf.usermodel.HSLFTextShape;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.converter.PicturesManager;
import org.apache.poi.hwpf.converter.WordToHtmlConverter;
import org.apache.poi.hwpf.usermodel.PictureType;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFShape;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.util.StringUtils;
import org.w3c.dom.Document;

import com.aspose.cells.HtmlSaveOptions;
import com.aspose.cells.License;
import com.aspose.cells.SaveFormat;
import com.aspose.cells.Workbook;

public class DocumentConvertUtils {

	public static void docToHtml(String sourceFilePath, final String targetFolderPath, String fileName)
			throws Exception {

		HWPFDocument wordDocument = new HWPFDocument(new FileInputStream(sourceFilePath));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
		WordToHtmlConverter wordToHtmlConverter = new WordToHtmlConverter(document);
		File targetFolder = new File(targetFolderPath);
		File imageFolder = new File(targetFolderPath + "image/");
		if (!targetFolder.exists()) {
			targetFolder.mkdirs();
		}
		if (!imageFolder.exists()) {
			imageFolder.mkdirs();
		}
		// 保存图片，并返回图片的相对路径
		wordToHtmlConverter.setPicturesManager(new PicturesManager() {
			@Override
			public String savePicture(byte[] content, PictureType pictureType, String name, float width, float height) {
				try (FileOutputStream out = new FileOutputStream(targetFolderPath + "image/" + name)) {
					out.write(content);
				} catch (Exception e) {
					e.printStackTrace();
				}
				return "image/" + name;
			}
		});
		wordToHtmlConverter.processDocument(wordDocument);
		Document htmlDocument = wordToHtmlConverter.getDocument();
		DOMSource domSource = new DOMSource(htmlDocument);

		StreamResult streamResult = new StreamResult(new File(targetFolderPath + fileName));

		TransformerFactory tf = TransformerFactory.newInstance();
		Transformer serializer = tf.newTransformer();
		serializer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
		serializer.setOutputProperty(OutputKeys.INDENT, "yes");
		serializer.setOutputProperty(OutputKeys.METHOD, "html");
		serializer.transform(domSource, streamResult);
	}

	// docx转换为html
	public static void docxToHtml(String sourceFilePath, final String targetFolderPath, String fileName)
			throws Exception {
		OutputStreamWriter outputStreamWriter = null;
		try {
			File targetFolder = new File(targetFolderPath);
			File imageFolder = new File(targetFolderPath + "image/");
			if (!targetFolder.exists()) {
				targetFolder.mkdirs();
			}
			if (!imageFolder.exists()) {
				imageFolder.mkdirs();
			}
			XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFilePath));

			XHTMLOptions options = XHTMLOptions.create();
			// 存放图片的文件夹
			options.setExtractor(new FileImageExtractor(new File(targetFolderPath + "image/")));
			// html中图片的路径
			options.URIResolver(new BasicURIResolver("image/"));
			// options.indent(4);
			outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFolderPath + fileName), "utf-8");
			XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance();
			xhtmlConverter.convert(document, outputStreamWriter, options);
		} finally {
			if (outputStreamWriter != null) {
				outputStreamWriter.close();
			}
		}
	}

	public static void xlsToHtml(String sourceFilePath, String targetFolderPath, String fileName) throws Exception {
		if (!DocumentConvertUtils.getLicense())
			return;

		// Specify the HTML Saving Options
		HtmlSaveOptions save = new HtmlSaveOptions(SaveFormat.HTML);
		save.setExportGridLines(true);

		// Instantiate a workbook and open the template XLSX file
		Workbook book = new Workbook(sourceFilePath);

		// Save the HTML file
		book.save(targetFolderPath + fileName, save);
	}

	public static void pptToHtml(String sourcePath, String targetDir, String fileName) {
		File pptFile = new File(sourcePath);
		if (pptFile.exists()) {
			try {
				String type = StringUtils.getFilenameExtension(sourcePath).toLowerCase();

				if ("ppt".equals(type)) {
					String htmlStr = toImage2003(sourcePath, targetDir, fileName);
					FileUtils.writeStringToFile(new File(targetDir + "/" + fileName), htmlStr);
				} else if ("pptx".equals(type)) {
					String htmlStr = toImage2007(sourcePath, targetDir, fileName);
					FileUtils.writeStringToFile(new File(targetDir + "/" + fileName), htmlStr);
				} else {
					System.out.println("the file is not a ppt");
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("file does not exist!");
		}

	}

	public static String toImage2007(String sourcePath, String targetDir, String pptFileName) throws Exception {
		String htmlStr = "";
		FileInputStream is = new FileInputStream(sourcePath);
		XMLSlideShow ppt = new XMLSlideShow(is);
		is.close();
		File targetFolder = new File(targetDir);
		if (!targetFolder.exists()) {
			targetFolder.mkdirs();
		}

		Dimension pgsize = ppt.getPageSize();
		System.out.println(pgsize.width + "--" + pgsize.height);

		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < ppt.getSlides().size(); i++) {

			// ??ֹ????
			for (XSLFShape shape : ppt.getSlides().get(i).getShapes()) {
				if (shape instanceof XSLFTextShape) {
					XSLFTextShape tsh = (XSLFTextShape) shape;
					for (XSLFTextParagraph p : tsh) {
						for (XSLFTextRun r : p) {
							r.setFontFamily("黑体");
						}
					}
				}
			}
			BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
			Graphics2D graphics = img.createGraphics();
			// clear the drawing area
			graphics.setPaint(Color.white);
			graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
			// render
			ppt.getSlides().get(i).draw(graphics);
			// save the output
			String imageDir = targetDir + "/image/";
			File imageFolder = new File(imageDir);
			if (!imageFolder.exists()) {
				imageFolder.mkdirs();
			}

			String imagePath = imageDir + "image-" + (i + 1) + ".png";
			sb.append("<br>");
			sb.append("<img src=" + "\"image/image-" + (i + 1) + ".png\"" + "/>");
			FileOutputStream out = new FileOutputStream(imagePath);
			javax.imageio.ImageIO.write(img, "png", out);
			out.close();

		}
		System.out.println("success");
		htmlStr = sb.toString();

		return htmlStr;
	}

	public static String toImage2003(String sourcePath, String targetDir, String pptFileName) {
		String htmlStr = "";
		try {
			HSLFSlideShow ppt = new HSLFSlideShow(new HSLFSlideShowImpl(sourcePath));
			File targetFolder = new File(targetDir);
			if (!targetFolder.exists()) {
				targetFolder.mkdirs();
			}
			Dimension pgsize = ppt.getPageSize();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < ppt.getSlides().size(); i++) {
				// ??ֹ????
				for (HSLFShape shape : ppt.getSlides().get(i).getShapes()) {
					if (shape instanceof HSLFTextShape) {
						HSLFTextShape tsh = (HSLFTextShape) shape;
						for (HSLFTextParagraph p : tsh) {
							for (HSLFTextRun r : p) {
								r.setFontFamily("黑体");
							}
						}
					}
				}
				BufferedImage img = new BufferedImage(pgsize.width, pgsize.height, BufferedImage.TYPE_INT_RGB);
				Graphics2D graphics = img.createGraphics();
				// clear the drawing area
				graphics.setPaint(Color.white);
				graphics.fill(new Rectangle2D.Float(0, 0, pgsize.width, pgsize.height));
				// render
				ppt.getSlides().get(i).draw(graphics);
				String imageDir = targetDir + "/image/";
				File imageFolder = new File(imageDir);
				if (!imageFolder.exists()) {
					imageFolder.mkdirs();
				}
				String imagePath = imageDir + "image-" + (i + 1) + ".png";
				sb.append("<br>");
				sb.append("<img src=" + "\"image/image-" + (i + 1) + ".png\"" + "/>");
				FileOutputStream out = new FileOutputStream(imagePath);
				javax.imageio.ImageIO.write(img, "png", out);
				out.close();

			}
			System.out.println("success");
			htmlStr = sb.toString();
		} catch (Exception e) {

		}
		return htmlStr;
	}

	public static void resizeImage(String srcImgPath, String distImgPath, int width, int height) throws IOException {

		File srcFile = new File(srcImgPath);
		Image srcImg = ImageIO.read(srcFile);
		BufferedImage buffImg = null;
		buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		buffImg.getGraphics().drawImage(srcImg.getScaledInstance(width, height, Image.SCALE_SMOOTH), 0, 0, null);

		ImageIO.write(buffImg, "JPEG", new File(distImgPath));

	}

	public static void pdfToHtml(String sourcePath, String targetDir, String fileName) {
		StringBuilder sbHtml = new StringBuilder();
		sbHtml.append("<html><head></head><body style=\"background-color: #666;\">");
		File file = new File(sourcePath);
		File targetFolder = new File(targetDir);
		if(!targetFolder.exists()) {
			targetFolder.mkdirs();
		}
		File targetImageFolder = new File(targetDir+"images/");
		if(!targetImageFolder.exists()) {
			targetImageFolder.mkdirs();
		}
		try {
			PDDocument doc = PDDocument.load(file);
			PDFRenderer renderer = new PDFRenderer(doc);
			int pageCount = doc.getNumberOfPages();
			for (int i = 0; i < pageCount; i++) {
				BufferedImage image = renderer.renderImageWithDPI(i, 72, ImageType.RGB);
				ImageIO.write(image, "PNG", new File(targetDir + "images/" + String.valueOf(i) + ".png"));
				
				sbHtml.append("<img src=\"images/"+ String.valueOf(i) + ".png\" width=\"100%\" style=\"margin-bottom: 6px;\">");
			}
			sbHtml.append("</body></html>");
			FileUtils.writeStringToFile(new File(targetDir + fileName), sbHtml.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static boolean getLicense() {
		try {
			InputStream is = DocumentConvertUtils.class.getClassLoader()
					.getResourceAsStream("/config/aspose/license.xml");
			License aposeLic = new License();
			aposeLic.setLicense(is);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

//	public static void main(String[] args) throws IOException {
//		
//	}
}
