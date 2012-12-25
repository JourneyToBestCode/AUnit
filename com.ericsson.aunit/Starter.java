import runner.SingleClassRunner;


public class Starter
{

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        if(args.length>0)
        new SingleClassRunner().run(args[0]);
    }

}
