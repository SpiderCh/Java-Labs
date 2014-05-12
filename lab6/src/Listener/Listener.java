package Listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import Signal.SignalType;

class MessageWrapper
{
	public Message mess;
	public iObservable caller;

	public MessageWrapper(iObservable caller, Message mess)
	{
		this.caller = caller;
		this.mess = mess;
	}
}

public class Listener extends Thread implements iListener
{
	private static Listener instance;

	private final LinkedList<MessageWrapper> m_messageQueue;
	private final HashMap<SignalType, LinkedList<iObservable>> m_observables;
	private boolean m_continueExecution;

	private void registerActions()
	{
		m_observables.put(SignalType.DATA, new LinkedList<iObservable>());
		m_observables.put(SignalType.INFO, new LinkedList<iObservable>());
		m_observables.put(SignalType.SYSTEM, new LinkedList<iObservable>());
		m_observables.put(SignalType.SIGNAL, new LinkedList<iObservable>());
	}

	public static Listener getInstance()
	{
		if (instance == null) {
			instance = new Listener();
		}

		return instance;
	}

	public static void deleteListener()
	{
		instance = null;
	}

	private Listener()
	{
		m_messageQueue = new LinkedList<>();
		m_observables = new HashMap<>();
		m_continueExecution = true;
		registerActions();
	}

	@Override
	public boolean subscribe(iObservable observable, SignalType action)
	{
		return m_observables.get(action).add(observable);
	}

	@Override
	public boolean unsubscribe(iObservable observable, SignalType action)
	{
		return m_observables.get(action).remove(observable);
	}

	@Override
	public void signal(iObservable caller, Message mess)
	{
		m_messageQueue.addLast(new MessageWrapper(caller, mess));
		synchronized (m_messageQueue) {m_messageQueue.notifyAll();}
	}

	@Override
	public void run()
	{
		while(m_continueExecution) {
			if(m_messageQueue.isEmpty()) {
				try {
					synchronized (m_messageQueue) {
						m_messageQueue.wait();
						if(m_messageQueue.isEmpty()) {continue;}
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			MessageWrapper mess;
			synchronized (m_messageQueue) {
				mess = m_messageQueue.pop();
			}
			Iterator<iObservable> it = m_observables.get(mess.mess.m_signalType).iterator();
			while(it.hasNext()) {
				iObservable o = it.next();
				if(o != mess.caller) {
					o.signal(mess.mess);
				}
			}
		}
	}

	public void stopQueue()
	{
		m_continueExecution = false;
		synchronized (m_messageQueue) {m_messageQueue.notifyAll();}
	}
}
