package JavaClass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//Simple Class to take Text Path and return array of id in this Text
//Using BufferReader to Read line by line and put it on List
public class EmpReader
{
    //Path of Text File
    private String file_path;

    public EmpReader(String path)
    {
        this.file_path = path;
    }

    //reading Every id in Text And return array of String
    public String [] reading(){
        //Dynamic Array For Reading Every Id
        List<String> employee_id = new ArrayList<>();
        //Start Try Reading
        try
        {
            String id;
            File file = new File(file_path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            while((id = br.readLine()) != null){
                employee_id.add(id.trim());
            }
        }
        catch (IOException e)
        {
            System.out.println("Can't Reading This File");
        }
        //return Array Of String
        return employee_id.toArray(new String[0]);
    }

}
