package com.example.administrator.layout;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import androidx.appcompat.app.AppCompatActivity;

public class FileIO extends PermissionsAction {


    public void FileRead(String dirname,String postname) throws IOException {


//        File file = new File(dirname+postname);
//
//        if (file.exists()) {
//
//            FileInputStream fis = new FileInputStream(file);
//            fis.close();
//
//        } else {
//
//        }
//
//        FileInputStream fis = new FileInputStream(file);
//        file.exists();

        System.out.println("写执行了");

    }


    public void FileWrite(String dirname,String postname) throws IOException {

//        FileOutputStream file = new FileOutputStream(dirname+postname);
//
//        BufferedOutputStream fos = new BufferedOutputStream(file);

        System.out.println("读执行了");


    }


}
