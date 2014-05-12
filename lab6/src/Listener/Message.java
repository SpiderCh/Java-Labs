package Listener;


import Signal.SignalType;
import Signal.Signal;
import java.util.Objects;


public class Message
{
	public SignalType m_signalType;
	public Signal    m_action;
	public Object     m_data;

	public Message(SignalType signal, Signal action)
	{
		m_signalType = signal;
		m_action = action;
		m_data = null;
	}

	public Message(SignalType signal, Signal action, Object data)
	{
		m_signalType = signal;
		m_action = action;
		m_data = data;
	}
}
