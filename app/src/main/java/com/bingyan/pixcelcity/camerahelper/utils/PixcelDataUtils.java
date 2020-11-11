package com.bingyan.pixcelcity.camerahelper.utils;

import android.util.Log;

public class PixcelDataUtils {



    public static float[][] calculateFingerprint(int[] data,int width,int height){

        float[][] fingerprint = new float[8][8];

        int rotatedDataColumnSize  = 64;
        int rotatedDataRawSize = 128;
        //定一个处理之后的25*50的数组存放平均后的数据
        long[][] rotatedData = new long[rotatedDataColumnSize][rotatedDataRawSize];
        //处理获取新数据
        //计算每个矩形的宽高
        int rectWidth =  width / rotatedDataColumnSize ;//向下取整
        int rectHeight = height / rotatedDataRawSize ;//向下取整

        int countPerRect = rectWidth * rectHeight;

        //转到二维数组去,转的同时变为 0 或 1
        long binaryData;
        int rectColumnIndex;
        int rectRawIndex;

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                binaryData = Integer.valueOf(Long.toHexString(data[i+j*width]).substring(10,16).toUpperCase(),16);
                //判断属于哪个小方框
                rectColumnIndex = i/rectWidth ;
                rectRawIndex = j/rectHeight ;
                //如果不在范围内则抛弃
                if (rectColumnIndex < rotatedDataColumnSize && rectRawIndex < rotatedDataRawSize){
                    rotatedData[rectColumnIndex][rectRawIndex]+=binaryData;
                }
            }
        }

        //每个大格取平均
        for (int i = 0; i < rotatedDataColumnSize; i++) {
            for (int j = 0; j < rotatedDataRawSize; j++) {
                rotatedData[i][j] = (rotatedData[i][j] / countPerRect) >8355711 ? 1:0; //这里再二值化
            }
        }

        // 转换后矩阵
        long[][] arr = new long[rotatedDataRawSize][rotatedDataColumnSize];

        // 旋转
        for (int i = 0; i < rotatedDataColumnSize; i++) {
            for (int j = 0; j < rotatedDataRawSize ; j++) {
                arr[rotatedDataRawSize - 1 - j][i] = rotatedData[i][j];
            }
        }

        //这里认为旋转之后的就是正的
        //接下来开始计算 8x8中 1在每格中的比例

        int fingerprintColumnSize = 8;
        int fingerprintRawSize = 8;

        //获得每大格的小格数
        rectWidth = rotatedDataColumnSize / fingerprintColumnSize;
        rectHeight = rotatedDataRawSize / fingerprintRawSize;


        for (int i = 0; i < rotatedDataColumnSize; i++) {
            for (int j = 0; j < rotatedDataRawSize; j++) {
                rectColumnIndex = i / rectWidth;
                rectRawIndex = j / rectHeight;
                //获取1的个数
                fingerprint[rectColumnIndex][rectRawIndex] += rotatedData[i][j];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < fingerprintColumnSize; i++) {
            for (int j = 0; j < fingerprintRawSize; j++) {
                fingerprint[i][j] /= (rectWidth * rectHeight);
                sb.append(fingerprint[i][j]).append(",");
            }
            sb.append("\n");
        }


        d("FingerprintTAG",sb.toString());


        return fingerprint;
    }


    public static void d(String tag, String msg) {  //信息太长,分段打印

        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，

        //  把4*1024的MAX字节打印长度改为2001字符数

        int max_str_length = 2001 - tag.length();

        //大于4000时

        while (msg.length() > max_str_length) {

            Log.i(tag, msg.substring(0, max_str_length));

            msg = msg.substring(max_str_length);

        }

        //剩余部分

        Log.d(tag, msg);

    }


}
