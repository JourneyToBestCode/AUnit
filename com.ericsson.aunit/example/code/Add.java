package example.code;

public class Add
{
    private int init = 0;
    private int count = 0;

    public Add(int init)
    {
        super();
        this.init = init;
    }
    
    public int addTwo(){
        count += 2;
        return init + count;
    }
}
