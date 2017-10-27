//Simple Library to make image resizer for android developer
//benfits : can make image without padding 
//xResizer Project
//input : image
//ouuput : 7 same image but with different size
// ldpi : 36 x 36
// mdpi : 48 x 48
// tvdpi : 64 x 64
// hdpi : 72 x 72
// xhdpi : 96 x 96
// xxhdpi : 144 x 144
// xxxhdpi : 192 x 192

package sample;

//Packages
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Resizer {
    
    //Array of 7 size every index is width and hight (width == hight)
    private int size_array[] = {36 , 38 , 64 , 72 , 96 , 144 , 192};
    private String type_array[] = {"-ldpi","-mdpi","-tvdpi","-hdpi","-xhdpi","-xxhdpi","-xxxhdpi"};
    //width size
    private int img_width;
    //hight size
    private int img_hight;
    //image open path
    private String img_path;
    //image save path
    private String img_save;
    //image name
    private String img_name;
    
    //Constructor
    public Resizer(String img_path , String img_save , String img_name){
        
        this.img_path = img_path;
        this.img_save = img_save;
        this.img_name = img_name;
        
    }
    
    public void start_resize(){
        //read Image
        try{    
            File img = new File(this.img_path);
            BufferedImage originalImage = ImageIO.read(img);
	    int type = originalImage.getType() == 0? BufferedImage.TYPE_INT_ARGB : originalImage.getType();
            //resize and write it 
            //number of size
            int size_num = size_array.length;
            //for every image size
            for(int i = 0 ; i < size_num ; i++){
                //set current width and hight
                img_width = size_array[i];
                img_hight = size_array[i];
                //generate different name every time
                String name = type_array[i] + ".jpg";
                BufferedImage resizeImageJpg = img_Resize(originalImage, type,img_width,img_hight);
               ImageIO.write(resizeImageJpg, "jpg", new File(this.img_save + name));
            }
        }
        //if cant read image
        catch(IOException e)
        {
            System.out.println("Cant find image to read it");
        }
    }
    

    //img_resize used to resize image with width and hight using Graphics2D
    private BufferedImage img_Resize(BufferedImage originalImage, int type , int wigth , int hight){
        //Take image with type
	BufferedImage resizedImage = new BufferedImage(wigth, hight, type);
        //drawing image 
	Graphics2D g = resizedImage.createGraphics();
        //set wight and hight
	g.drawImage(originalImage, 0, 0, wigth, hight, null);
        //end this g object
	g.dispose();
        //return image after resizerd as Buffered Image
	return resizedImage;
    }
    
}
