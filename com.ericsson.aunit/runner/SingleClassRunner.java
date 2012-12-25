package runner;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import listener.Listener;
import listener.PrintListener;
import event.Event;
import event.Events;
import exception.UnTestableClassException;
import facilities.ExecuteContext;
import facilities.Utils;

public class SingleClassRunner implements Runner
{
    Map<String, Listener> listeners;

    public SingleClassRunner()
    {
        super();
        listeners = new HashMap<String, Listener>();
        addListener(new PrintListener());
    }
    
    public SingleClassRunner(Map<String, Listener> listeners)
    {
        super();
        this.listeners = listeners;
    }

    @Override
    public void run(String testClazzName)
    {
        try
        {
            ExecuteContext context = prepareClazz(testClazzName);
            startToRunClass(context);
            executeBefore(context);
            executeTests(context);
            executeAfter(context);
            finishedRunClass(context);
        }
        catch (ClassNotFoundException e)
        {
            failToRunClass(e);
        }
        catch (UnTestableClassException e)
        {
            failToRunClass(e);
        }

    }

    private void startToRunClass(ExecuteContext context)
    {
        Event e = new Event(Events.START_RUN_CLASS, "TestClass[" + context.getClazz().getName() + "] starts running, "
                + context.getTestMethods().size() + " cases found");
        notifyListeners(e);
    }

    private void failToRunClass(Throwable t)
    {
        Event e = new Event(Events.FAILED_RUN_CLASS, t.getLocalizedMessage());
        notifyListeners(e);
    }
    
    private void testCaseFailed(Throwable t)
    {
        Event e = new Event(Events.FAILED_RUN_METHOD, t.getLocalizedMessage());
        notifyListeners(e);
    }
    
    private void finishedRunClass(ExecuteContext context)
    {
        Event e = new Event(Events.FINISH_RUN_CLASS, "TestClass[" + context.getClazz().getName() + "] finished running");
        notifyListeners(e);
    }
    
    private void startToRunMethod(Method m)
    {
        Event e = new Event(Events.START_RUN_METHOD, "TestCase[" + m.getName() + "] start running");
        notifyListeners(e);
    }
    
    private void finishedRunMethod(Method m)
    {
        Event e = new Event(Events.SUCCESS_RUN_METHOD, "TestCase[" + m.getName() + "] run successfully");
        notifyListeners(e);
    }

    private void notifyListeners(Event e)
    {
        for (String listernClazz : listeners.keySet())
        {
            Listener listener = listeners.get(listernClazz);
            listener.handle(e);
        }
    }

    private ExecuteContext prepareClazz(String clazzName) throws ClassNotFoundException, UnTestableClassException
    {
        Class<?> clazz = null;
        try
        {
            clazz = Class.forName(clazzName);
        }
        catch (ClassNotFoundException e)
        {
            clazz = Thread.currentThread().getContextClassLoader().loadClass(clazzName);
        }

        ExecuteContext context = new ExecuteContext();
        context.setClazz(clazz);
        context.setBeforeMethod(Utils.findBeforeMethods(clazz));
        context.setAfterMethod(Utils.findAfterMethods(clazz));
        context.setTestMethods(Utils.findTestMethods(clazz));
        context.setInstance(createInstance(clazz));

        return context;
    }

    private Object createInstance(Class<?> clazz) throws UnTestableClassException
    {
        Object instance = null;
        try
        {
            instance = clazz.newInstance();
        }
        catch (InstantiationException e)
        {
            throw new UnTestableClassException();
        }
        catch (IllegalAccessException e)
        {
            throw new UnTestableClassException();
        }
        return instance;
    }

    private void executeBefore(ExecuteContext context) throws UnTestableClassException
    {
        for(Method m:context.getBeforeMethod()){
            try
            {
                m.invoke(context.getInstance());
            }
            catch (IllegalArgumentException e)
            {
                throw new UnTestableClassException();
            }
            catch (IllegalAccessException e)
            {
                throw new UnTestableClassException();
            }
            catch (InvocationTargetException e)
            {
                throw new UnTestableClassException();
            }
        }
    }
    
    private void executeAfter(ExecuteContext context) throws UnTestableClassException
    {
        for(Method m:context.getAfterMethod()){
            try
            {
                m.invoke(context.getInstance());
            }
            catch (IllegalArgumentException e)
            {
                throw new UnTestableClassException();
            }
            catch (IllegalAccessException e)
            {
                throw new UnTestableClassException();
            }
            catch (InvocationTargetException e)
            {
                throw new UnTestableClassException();
            }
        }
    }
    
    private void executeTests(ExecuteContext context) throws UnTestableClassException
    {
        for(Method m:context.getTestMethods()){
            startToRunMethod(m);
            try
            {
                m.invoke(context.getInstance());
            }
            catch (IllegalArgumentException e)
            {
                throw new UnTestableClassException();
            }
            catch (IllegalAccessException e)
            {
                throw new UnTestableClassException();
            }
            catch (InvocationTargetException e)
            {
                testCaseFailed(e.getCause());
            }
            finishedRunMethod(m);
        }
    }

    @Override
    public void addListener(Listener listener)
    {
        this.listeners.put(listener.getClass().getName(), listener);
    }

}
