import java.util.Vector;
public class Simulation
{
    private final int maxRow;
    private final int maxColumn;
    private Vector catfishes;
    public Simulation(int maxRow,int maxColumn)
    {//最大行列
        this.maxRow=maxRow;
        this.maxColumn=maxColumn;
        this.catfishes=new Vector(50);
    }
    public int getMaxRow()//返回最大值（有啥意义？)
    {
        return this.maxRow;
    }

    public int getMaxColumn()
    {
        return this.maxColumn;
    }

    public void addCatfish(Catfish paramCatfish)
    {
        if(paramCatfish == null)
        {//如果输入值为空，结束
            return;
        }
        if(this.catfishes.contains(paramCatfish)){//如果字符串包含paraCatfish，结束
            return;
        }
        this.catfishes.add(paramCatfish);//添加元素paramCatfish
    }

    public Catfish getCatfish(int paramInt)
    {
        return (Catfish)this.catfishes.get(paramInt);//将paramInt赋给catfishes
    }

    public Vector getNeighbors(int paramInt1,int paramInt2,int paramInt3)
    {
        Vector vector=new Vector();
        for (int i=0;i<this.catfishes.size();i++)
        {
            Catfish catfish=(Catfish)this.catfishes.get(i);

            if((catfish!=null)&&(catfish.getColumn()<=paramInt2+paramInt3)&&(catfish.getColumn()>=paramInt2-paramInt3))
            {
                vector.add(catfish);
            }
        }
        return vector;
    }
}
