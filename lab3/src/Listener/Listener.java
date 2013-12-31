package Listener;

import java.util.Iterator;
import java.util.LinkedList;

public class Listener implements iListener {

    private static Listener instance;

    private final LinkedList<iObservable> m_observables;

    public static Listener getInstance() {
        if (instance == null) {
            instance = new Listener();
        }

        return instance;
    }

    public static void deleteListener() {
        instance = null;
    }

    private Listener() {
        m_observables = new LinkedList<>();
    }

    public void addObservable(iObservable observable) {
        m_observables.add(observable);
    }

    public void deleteObservable(iObservable observable) {
        synchronized (m_observables) {
            m_observables.remove(observable);
        }
    }

    @Override
    public void addAction(iObservable caller, Actions action) {
        Iterator<iObservable> observable = m_observables.iterator();
        while (observable.hasNext()) {
            iObservable o = observable.next();
            if (o != caller) {
                o.newAction(action);
            }
        }
    }

    @Override
    public void addAction(iObservable caller, Actions action, Object data) {
        Iterator<iObservable> observable = m_observables.iterator();
        while (observable.hasNext()) {
            iObservable o = observable.next();
            if (o != caller) {
                o.newAction(action, data);
            }
        }
    }
}
