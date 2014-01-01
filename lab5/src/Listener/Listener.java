package Listener;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Listener implements iListener
{
	private static Listener instance;

	private final HashMap<Actions, LinkedList<iObservable>> _m_observables;

	private void registerActions()
	{
		_m_observables.put(Actions.Stop,                            new LinkedList<iObservable>());
		_m_observables.put(Actions.Start,                           new LinkedList<iObservable>());
		_m_observables.put(Actions.Pause,                           new LinkedList<iObservable>());
		_m_observables.put(Actions.ShowTime,                        new LinkedList<iObservable>());
		_m_observables.put(Actions.HideTime,                        new LinkedList<iObservable>());
		_m_observables.put(Actions.ForceStop,                       new LinkedList<iObservable>());
		_m_observables.put(Actions.LiveObjects,                     new LinkedList<iObservable>());
		_m_observables.put(Actions.ShowLiveObjects,                 new LinkedList<iObservable>());
		_m_observables.put(Actions.SimulationResults,               new LinkedList<iObservable>());
		_m_observables.put(Actions.ShowSimulationInfo,              new LinkedList<iObservable>());
		_m_observables.put(Actions.HideSimulationInfo,              new LinkedList<iObservable>());
		_m_observables.put(Actions.DevLiveTimeChanged,              new LinkedList<iObservable>());
		_m_observables.put(Actions.DevPossibilityChanged,           new LinkedList<iObservable>());
		_m_observables.put(Actions.ManagerLiveTimeChanged,          new LinkedList<iObservable>());
		_m_observables.put(Actions.ManagerMaxNumberChanged,         new LinkedList<iObservable>());
		_m_observables.put(Actions.DevCreationPeriodChanged,        new LinkedList<iObservable>());
		_m_observables.put(Actions.ManagerCreationPeriodChanged,    new LinkedList<iObservable>());
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
		_m_observables = new HashMap<>();
		registerActions();
	}

	@Override
	public boolean subscribe(iObservable observable, Actions action)
	{
		return _m_observables.get(action).add(observable);
	}

	@Override
	public boolean unsubscribe(iObservable observable, Actions action)
	{
		return _m_observables.get(action).remove(observable);
	}

	@Override
	public void addAction(iObservable caller, Actions action)
	{
		Iterator<iObservable> observable = _m_observables.get(action).iterator();
		while (observable.hasNext()) {
			iObservable o = observable.next();
			if (o != caller) {
				o.newAction(action);
			}
		}
	}

	@Override
	public void addAction(iObservable caller, Actions action, Object data)
	{
		//Iterator<iObservable> observable = m_observables.iterator();
		Iterator<iObservable> observable = _m_observables.get(action).iterator();
		while (observable.hasNext()) {
			iObservable o = observable.next();
			if (o != caller) {
				o.newAction(action, data);
			}
		}
	}
}
