package net.dracolair.games.applestoapples.commands;

import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.MessageInfo;
import net.dracolair.games.applestoapples.State;

import static net.dracolair.games.applestoapples.Factories.*;

public class MgrCmdPlay extends ManagerCommand {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		ata.m_state = State.PLAY;
		ata.m_waiting.clear();
		ata.m_waiting.addAll(ata.m_activePlayers);
		ata.m_judge = ata.m_waiting.remove(0);
		
		ata.m_greenCard = ata.m_greenCards.remove(0);
		
		for(String delayCmd : ata.m_delayedCommands) {
			responses.add(MSG(gameManager.getName(), delayCmd));
		}
		ata.m_delayedCommands.clear();
		
		responses.add(MSG(msgInfo.ROOM, ata.m_judge + " is the judge.  Green card is: " + ata.m_greenCard.toFormattedString()));
		responses.add(MSG(msgInfo.ROOM, "Waiting for players to play cards..."));
		responses.add(MSG(gameManager.getName(), "!botshowplayercards " + msgInfo.MESSAGE));		
		responses.add(MSG(gameManager.getName(), "!botsettime " + msgInfo.MESSAGE));
	}
	
}
