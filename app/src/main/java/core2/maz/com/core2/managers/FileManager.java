package core2.maz.com.core2.managers;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import core2.maz.com.core2.application.MyApplication;
import core2.maz.com.core2.model.Menu;
import core2.maz.com.core2.utills.AppUtils;

/**
 * Created by Ankur Jain on 18-11-2016.
 */
public class FileManager {



    public static void writeBitmapToFile( File fileObject)
    {
        try
        {
            FileOutputStream out = new FileOutputStream(fileObject);
            out.flush();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void writeObject(Menu menu,String name) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream(name+"/"+menu.getIdentifier());
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file

        objOutputStream.writeObject(menu);
        //objOutputStream.reset();
        objOutputStream.close();
    }

    public static void writeObject(Object object,String name) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream(name);
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file

        objOutputStream.writeObject(object);
        //objOutputStream.reset();
        objOutputStream.close();
    }

    public static void writeObject(String menu,String name) throws IOException {
        //Create FileOutputStream to write file
        FileOutputStream fos = new FileOutputStream(name+"/"+"Logo");
        //Create ObjectOutputStream to write object
        ObjectOutputStream objOutputStream = new ObjectOutputStream(fos);
        //Write object to file

        objOutputStream.writeObject(menu);
        //objOutputStream.reset();
        objOutputStream.close();
    }

    public static void readMenuObjectFromFile(String path)
    {
        try {
            File file = FileManager.getFolderOnExternalDirectory("Series");
            String folderDirectory = file.getAbsolutePath();
            List<Menu> menus = new ArrayList<>();
            File[] listOfFiles = file.listFiles();
            for(int i=0;i<listOfFiles.length;i++)
            {
                FileInputStream streamIn = new FileInputStream(listOfFiles[i]);
                ObjectInputStream objectinputstream = new ObjectInputStream(streamIn);
                Menu menu = (Menu) objectinputstream.readObject();
                menus.add(menu);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static File getFolderOnExternalDirectory(String folderName)
    {
        //Folder
        boolean doesFolderExists = false;
        File folderFileObject = null;

        Context context = MyApplication.getAppContext();

        //Get External Directory
        File externalFilesDirectory = context.getExternalFilesDir(null);

        if (AppUtils.containsData(folderName))
        {
            //Get App Directory
            folderFileObject = new File(externalFilesDirectory, folderName);

            //Check if App Directory exits
            doesFolderExists = folderFileObject.exists();

            //If Doesn't exist then create one (Will happen First time only)
            if (!doesFolderExists)
            {
                doesFolderExists = folderFileObject.mkdir();

                if(!doesFolderExists)
                {
                    folderFileObject = externalFilesDirectory;
                }
            }
        }
        else
        {
            folderFileObject = externalFilesDirectory;
        }

        return folderFileObject;
    }
}
