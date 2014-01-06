package Listener;

import Signal.SignalType;

public interface iListener
{
	public boolean subscribe(iObservable observable, SignalType action);
	public boolean unsubscribe(iObservable observable, SignalType action);
	public void signal(iObservable caller, Message mess);
}