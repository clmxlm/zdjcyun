/**
 * Copyright 2016 JustWayward Team
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.zdjc.zdjcyun.util;

import android.content.Context;
import android.os.Environment;

import com.blankj.utilcode.utils.LogUtils;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yuyh.
 * @date 16/4/9.
 */
public class FileUtils {



    private String SDCardRoot;

    public FileUtils(){
        //得到当前外部存储设备的目录
        SDCardRoot= Environment.getExternalStorageDirectory()+File.separator;
        //File.separator为文件分隔符”/“,方便之后在目录下创建文件
    }

    //在SD卡上创建文件
    public File createFileInSDCard(String fileName,String dir) throws IOException {
        File file=new File(SDCardRoot+dir+File.separator+fileName);
        file.createNewFile();
        return file;
    }

    //在SD卡上创建目录
    public File createSDDir(String dir)throws IOException{
        File dirFile=new File(SDCardRoot+dir);
        dirFile.mkdir();//mkdir()只能创建一层文件目录，mkdirs()可以创建多层文件目录
        return dirFile;
    }

    //判断文件是否存在
    public boolean isFileExist(String fileName,String dir){
        File file=new File(SDCardRoot+dir+File.separator+fileName);
        return file.exists();
    }

    //将一个InoutStream里面的数据写入到SD卡中
    public File write2SDFromInput(String fileName,String dir,InputStream input){
        File file=null;
        OutputStream output=null;
        try {
            //创建目录
            createSDDir(dir);
            //创建文件
            file=createFileInSDCard(fileName,dir);
            //写数据流
            output=new FileOutputStream(file);
            //每次存4K
            byte buffer[]=new byte[4*1024];
            int temp;
            //写入数据
            while((temp=input.read(buffer))!=-1){
                output.write(buffer,0,temp);
            }
            output.flush();
        } catch (Exception e) {
            System.out.println("写数据异常："+e);
        }
        finally{
            try {
                output.close();
            } catch (Exception e2) {
                System.out.println(e2);
            }
        }
        return file;
    }


}