package com.smile.markdown;

/**
 * 将md文件中的本地图片转化为base64格式.
 * 使用https://github.com/coobird/thumbnailator
 * @author: ayuan
 * @create: 2019-05-13 16:20
 */

import net.coobird.thumbnailator.Thumbnails;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Image2Base64OfMarkdown {
    public static final int SCALE = 1;

    /**
     * 图片输出保真度，值越大图片越清晰，串越大
     */
    public static final float QUALITY = 0.2f;
    public static Pattern compile = Pattern.compile("(!\\[.*\\])\\((.*)\\)");
    /**
     * 是否将所有的base64串放到最后
     */
    public static boolean appendLast = false;

    public static void main(String[] args) throws Exception {
        args = new String[]{"E:\\app\\youdao\\yuanxue200800@163.com\\8566B3C4ECDD4D5888CE588D7BD73207\\开发者OP代码sonar检查方法.md"};
        // 第一个入参为md的文件。
        if (args[0] == null) {
            System.out.println("wrong file");
            return;
        }
        convertBase64(args[0],appendLast);
    }

    public static void convertBase64(String srcFile,boolean appendLast) throws Exception{
        byte[] buffer = new byte[1024 * 1024];
        String filePath = srcFile;
        String fileStr;
        File markdownFile = new File(filePath);
        StringBuilder markdownFileStr = new StringBuilder();

        // 读入md文件并用正则进行搜索
        try (BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(markdownFile));
             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            while (bufferedInputStream.read(buffer) != -1) {
                byteArrayOutputStream.write(buffer);
            }
            byte[] fileByte = byteArrayOutputStream.toByteArray();
            fileStr = new String(fileByte, "utf-8");
        }
        Matcher matcher = compile.matcher(fileStr);
        StringBuffer stringBuffer = new StringBuffer();

        //处理找到的图片
        Map<String, String> imageMap = new HashMap<>();
        while (matcher.find()) {
            String imagePath = matcher.group(2);

            // 压缩并编码图片
            ByteArrayOutputStream newImage = new ByteArrayOutputStream();
            Thumbnails.of(imagePath).scale(SCALE).outputQuality(QUALITY).outputFormat("png").toOutputStream(newImage);
            BASE64Encoder encoder = new BASE64Encoder();
            String encode = encoder.encode(newImage.toByteArray()).replace("\r\n", "");

            //  转换匹配项
            String imagePathName = imagePath.hashCode() + "";
            encode = "\r\n\r\n" + "[" + imagePathName + "]:data:image/png;base64," + encode + "\r\n\r\n";
            if(appendLast){
                imageMap.put(imagePathName, encode);
                matcher.appendReplacement(stringBuffer, matcher.group(1)+"[" + imagePathName + "]\r\n\r\n");
            }else{
                matcher.appendReplacement(stringBuffer, matcher.group(1) + "[" + imagePathName + "] \r\n\r\n" + encode);
            }
        }
        matcher.appendTail(stringBuffer);
        // 如果base64都在最后
        if(appendLast){
            for (Map.Entry<String, String> entry : imageMap.entrySet()) {
                stringBuffer.append(entry.getValue());
            }
        }

        //生成新文件
        String parentPath = new File(filePath).getCanonicalPath();
        File newFile = new File(parentPath.replace(".md", "remove.md"));
        try (FileWriter fileWriter = new FileWriter(newFile)) {
            fileWriter.write(stringBuffer.toString());
        }
    }
}