package example.testcode;

import annotation.Before;
import annotation.Test;
import assertion.Assert;
import example.code.Add;

public class AddTest
{
    private Add add;

    @Before
    public void init()
    {
        add = new Add(2);
    }

    @Test
    public void addTwoTest()
    {
        int result = add.addTwo();
        Assert.assertEquals(result, 4, "error on addTwoTest!");
    }
}
