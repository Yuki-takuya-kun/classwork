public class Catfish 
{
    private int column=1;
    private int energyLevel=10;
    public int getColumn()
    {
        //ADD YOUR CODE HERE
        //getColumn() should return the value of the column attribute
        return this.column;


        //END
    }
    public void swimRight()
    {
        //ADD YOUR CODE HERE
        //swimRight() should increment the value of the column attribute 
        //and decrement the value of energyLevel attribute 
        //if the new value of the column attribute will be less than or equal to 10. 
        if(this.column==10)
        {
            return;
        }
        this.column+=1;
        this.energyLevel-=1;
        return;

        //END
    }
    public String getImage()
    {

        //ADD YOUR CODE HERE
        //getImage() should return the image filename that represents the catfish. 
        //The image of a tired catfish is "CatFish-tired.gif". 
        //The image of a catfish that is not tired is "CatFish.gif". 
        if(this.energyLevel>=5)
        {
            return "/CatFish";
        }
        else
            return "/CatFish-tired";


        //END
        
    }
}
