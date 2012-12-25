package event;

public class Event
{
    private Events eventType;

    private String message;

    public Event(Events eventType, String message)
    {
        super();
        this.eventType = eventType;
        this.message = message;
    }

    public Events getEventType()
    {
        return eventType;
    }

    public String getMessage()
    {
        return message;
    }

}
