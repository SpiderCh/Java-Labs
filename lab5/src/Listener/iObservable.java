package Listener;

/**
 * Created by chas on 31.12.13.
 */
public interface iObservable
{
	public void newAction(Actions action);
	public void newAction(Actions action, Object data);
}
