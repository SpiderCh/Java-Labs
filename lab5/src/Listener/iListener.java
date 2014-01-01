package Listener;

public interface iListener
{
	public boolean subscribe(iObservable observable, Actions action);
	public boolean unsubscribe(iObservable observable, Actions action);
	public void addAction(iObservable caller, Actions action);
	public void addAction(iObservable caller, Actions action, Object data);
}