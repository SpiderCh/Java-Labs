package Listener;

public interface iListener
{
	public void addAction(iObservable caller, Actions action);
	public void addAction(iObservable caller, Actions action, Object data);
}