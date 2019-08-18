package com.btjf.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liuyq on 2018/4/23.
 * 二维码生成工具类
 */
public class QRCodeUtil {

    //二维码颜色
    private static final int BLACK = 0xFF000000;
    //二维码颜色
    private static final int WHITE = 0xFFFFFFFF;

    /**
     * <span style="font-size:18px;font-weight:blod;">ZXing 方式生成二维码</span>
     *
     * @param text      <a href="javascript:void();">二维码内容</a>
     * @param width     二维码宽
     * @param height    二维码高
     * @param imageType 二维码生成格式
     */
    public byte[] zxingCodeCreate(String text, int width, int height, String imageType) {
        Map<EncodeHintType, String> his = new HashMap<EncodeHintType, String>();
        //设置编码字符集
        his.put(EncodeHintType.CHARACTER_SET, "utf-8");
        byte[] images = null;
        try {
            //生成二维码
            BitMatrix encode = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, his);

            //去白边
            int[] rec = encode.getEnclosingRectangle();
            int resWidth = rec[2] + 1;
            int resHeight = rec[3] + 1;
            BitMatrix resMatrix = new BitMatrix(resWidth, resHeight);
            resMatrix.clear();
            for (int i = 0; i < resWidth; i++) {
                for (int j = 0; j < resHeight; j++) {
                    if (encode.get(i + rec[0], j + rec[1])) {
                        resMatrix.set(i, j);
                    }
                }
            }

            //获取二维码宽高
            int codeWidth = resMatrix.getWidth();
            int codeHeight = resMatrix.getHeight();

            //将二维码放入缓冲流
            BufferedImage image = new BufferedImage(codeWidth, codeHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < codeWidth; i++) {
                for (int j = 0; j < codeHeight; j++) {
                    //循环将二维码内容定入图片
                    image.setRGB(i, j, resMatrix.get(i, j) ? BLACK : WHITE);
                }
            }
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            File outPutImage = new File(outPutPath);
//            //如果图片不存在创建图片
//            if(!outPutImage.exists())
//                outPutImage.createNewFile();

            //将二维码图片写入流
            ImageIO.write(image, imageType, byteArrayOutputStream);
            images = byteArrayOutputStream.toByteArray();
            System.out.println(images.length);

            //二维码流写入图片文件
            File file = new File("D://QR.jpg");
            if (!file.exists()) {
                file.createNewFile();
            }
            DataOutputStream to = new DataOutputStream(new FileOutputStream(file));
            byteArrayOutputStream.writeTo(to);

        } catch (WriterException e) {
            e.printStackTrace();
            System.out.println("二维码生成失败");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("生成二维码图片失败");
        }
        return images;
    }


    public static void main(String[] args) {

        String str = "{\n" +
                "    \"code\": 1,\n" +
                "    \"message\": \"成功!\",\n" +
                "    \"object\": 1,\n" +
                "    \"map\": {},\n" +
                "    \"handelEntity\": null\n" +
                "}";
        QRCodeUtil qrCodeUtil = new QRCodeUtil();
        qrCodeUtil.zxingCodeCreate(str, 256, 256, "png");
        System.out.println("SUCCESS");
    }
}
