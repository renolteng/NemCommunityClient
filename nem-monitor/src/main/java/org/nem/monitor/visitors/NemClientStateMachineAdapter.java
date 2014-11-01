package org.nem.monitor.visitors;

import org.nem.core.model.NemStatus;
import org.nem.core.node.NodeEndpoint;
import org.nem.monitor.node.NemNodeType;
import org.nem.ncc.controller.viewmodels.ConfigurationViewModel;

import java.util.function.*;

import com.github.oxo42.stateless4j.*;

/**
 * Visitor that collects node status changes to derive next steps for starting nodes.
 */
public class NemClientStateMachineAdapter implements NodeStatusVisitor {
	final StateMachineConfig<State, NemStatus> nemClientNcc;
	final StateMachineConfig<State, NemStatus> nemClientNis;
	
	final StateMachine<State, NemStatus> nccStateMachine;
	final StateMachine<State, NemStatus> nisStateMachine;
	final Consumer<String> startNcc;
	final Consumer<String> startBrowser;
	final Supplier<ConfigurationViewModel> configNcc;
	final Consumer<String> startNis;
	
	/**
	 * Creates a new visitor.
	 *
	 * @param nodeType The node type being monitored.
	 * @param statusDescriptionConsumer The function to call when a description change is triggered.
	 */
	public NemClientStateMachineAdapter(final Consumer<String> startNcc, final Supplier<ConfigurationViewModel> configNcc, final Consumer<String> startBrowser, final Consumer<String> startNis) {
		this.nemClientNcc = new StateMachineConfig<>();
		this.nemClientNis = new StateMachineConfig<>();
		this.startNcc = startNcc;
		this.startBrowser = startBrowser;
		this.configNcc = configNcc;
		this.startNis = startNis;
		
		initializeStateMachine();
		
		this.nccStateMachine = new StateMachine<NemClientStateMachineAdapter.State, NemStatus>(State.Ncc_Unknown, this.nemClientNcc);
		this.nisStateMachine = new StateMachine<NemClientStateMachineAdapter.State, NemStatus>(State.Nis_Unknown, this.nemClientNis);
	}

	protected void initializeStateMachine() {

		// Ncc State Machine
		nemClientNcc.configure(State.Ncc_Unknown).permit(NemStatus.STOPPED, State.Ncc_Stopped_A);
		nemClientNcc.configure(State.Ncc_Unknown).permit(NemStatus.STARTING, State.Ncc_Starting_B);
		nemClientNcc.configure(State.Ncc_Unknown).permit(NemStatus.RUNNING, State.Ncc_Running_D);

		nemClientNcc.configure(State.Ncc_Stopped_A).permit(NemStatus.STARTING, State.Ncc_Spawned_E);
		nemClientNcc.configure(State.Ncc_Stopped_A).permit(NemStatus.RUNNING, State.Ncc_Running_D);
		nemClientNcc.configure(State.Ncc_Stopped_A).onEntryFrom(NemStatus.STOPPED, () -> {startNcc.accept(null);});

		nemClientNcc.configure(State.Ncc_Starting_B).permit(NemStatus.RUNNING, State.Ncc_Running_D);
		nemClientNcc.configure(State.Ncc_Starting_B).permit(NemStatus.STOPPED, State.Ncc_Manual_C);

		nemClientNcc.configure(State.Ncc_Running_D).permit(NemStatus.STOPPED, State.Ncc_Manual_C);
		nemClientNcc.configure(State.Ncc_Running_D).onEntryFrom(NemStatus.RUNNING, () -> {
			ConfigurationViewModel configModel = configNcc.get();
			if((configModel != null) && (configModel.getNisEndpoint().equals(NodeEndpoint.fromHost("localhost"))) && (nisStateMachine.getState() == State.Nis_Stopped_M)) {
				this.startNis.accept(null);
			} else {
				this.startBrowser.accept(null);
			}
		});

		nemClientNcc.configure(State.Ncc_Spawned_E).permit(NemStatus.RUNNING, State.Ncc_Running_D);
		nemClientNcc.configure(State.Ncc_Spawned_E).permit(NemStatus.STOPPED, State.Ncc_Manual_C);

		nemClientNcc.configure(State.Ncc_Manual_C).ignore(NemStatus.STARTING);
		nemClientNcc.configure(State.Ncc_Manual_C).ignore(NemStatus.RUNNING);
		nemClientNcc.configure(State.Ncc_Manual_C).ignore(NemStatus.STOPPED);

		// Nis State Machine
		nemClientNis.configure(State.Nis_Unknown).permit(NemStatus.STOPPED, State.Nis_Stopped_M);
		nemClientNis.configure(State.Nis_Unknown).permit(NemStatus.STARTING, State.Nis_Spawned_N);
		nemClientNis.configure(State.Nis_Unknown).permit(NemStatus.RUNNING, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Unknown).permit(NemStatus.BOOTING, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Unknown).permit(NemStatus.BOOTED, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Unknown).permit(NemStatus.SYNCHRONIZED, State.Nis_Running_O);

		nemClientNis.configure(State.Nis_Stopped_M).permit(NemStatus.STARTING, State.Nis_Spawned_N);
		nemClientNis.configure(State.Nis_Stopped_M).permit(NemStatus.RUNNING, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Stopped_M).permit(NemStatus.BOOTING, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Stopped_M).permit(NemStatus.BOOTED, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Stopped_M).permit(NemStatus.SYNCHRONIZED, State.Nis_Running_O);

		nemClientNis.configure(State.Nis_Spawned_N).permit(NemStatus.STOPPED, State.Nis_Stopped_R);
		nemClientNis.configure(State.Nis_Spawned_N).permit(NemStatus.RUNNING, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Spawned_N).permit(NemStatus.BOOTING, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Spawned_N).permit(NemStatus.BOOTED, State.Nis_Running_O);
		nemClientNis.configure(State.Nis_Spawned_N).permit(NemStatus.SYNCHRONIZED, State.Nis_Running_O);

		nemClientNis.configure(State.Nis_Running_O).permit(NemStatus.STOPPED, State.Nis_Manual_P);
		nemClientNis.configure(State.Nis_Running_O).ignore(NemStatus.RUNNING);
		nemClientNis.configure(State.Nis_Running_O).ignore(NemStatus.BOOTING);
		nemClientNis.configure(State.Nis_Running_O).ignore(NemStatus.BOOTED);
		nemClientNis.configure(State.Nis_Running_O).ignore(NemStatus.SYNCHRONIZED);
		nemClientNis.configure(State.Nis_Running_O).onEntryFrom(NemStatus.RUNNING, () -> {startBrowser.accept(null);});

		nemClientNis.configure(State.Nis_Manual_P).ignore(NemStatus.STOPPED);
		nemClientNis.configure(State.Nis_Manual_P).ignore(NemStatus.RUNNING);
		nemClientNis.configure(State.Nis_Manual_P).ignore(NemStatus.BOOTING);
		nemClientNis.configure(State.Nis_Manual_P).ignore(NemStatus.BOOTED);
		nemClientNis.configure(State.Nis_Manual_P).ignore(NemStatus.SYNCHRONIZED);
	}

	@Override
	public void notifyStatus(final NemNodeType type, final NemStatus status) {
		switch(type) {
			case NCC:
				nccStateMachine.fire(status);
				break;
			case NIS:
				nisStateMachine.fire(status);
				break;
		}
	}

	public enum State {
		Ncc_Unknown, Ncc_Stopped_A, Ncc_Spawned_E, Ncc_Starting_B, Ncc_Running_D, Ncc_Manual_C, Nis_Unknown, Nis_Stopped_M, Nis_Spawned_N, Nis_Stopped_R, Nis_Running_O, Nis_Manual_P
	}
}
