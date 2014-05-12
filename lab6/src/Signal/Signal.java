package Signal;

public enum Signal
{
	//	BEGIN: SIGNAL
	Start,
	Stop,
	ForceStop,
	Pause,
    StartDevThread,
    StopDevThread,
    StartManThread,
    StopManThread,
	//	END: SIGNAL
//	BEGIN: INFO
	ShowSimulationInfo,
	HideSimulationInfo,
	ShowTime,
	HideTime,
	ShowLiveObjects,
	SimulationResults,
	//	END: INFO
//	BEGIN: DATA
	DevCreationPeriodChanged,
	ManagerCreationPeriodChanged,
	DevLiveTimeChanged,
	ManagerLiveTimeChanged,
	DevPossibilityChanged,
	ManagerMaxNumberChanged,
	DevThreadPriority,
	ManagerThreaadPriority,
    LiveObjects,
	//	END: DATA
//	BEGIN: SYSTEM
	Repaint,
    ShowConsole,
    SaveSimulation,
    LoadSimulation,
    SaveProperties,
    LoadProperties
//	END: SYSTEM
}
