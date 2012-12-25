package runner;

import listener.Listener;

public interface Runner
{
    void run(String clazzName);
    void addListener(Listener listener);
}
