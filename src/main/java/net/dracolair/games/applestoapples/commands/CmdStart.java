package net.dracolair.games.applestoapples.commands;

import java.util.Collections;
import java.util.List;

import net.dracolair.games.applestoapples.Game;
import net.dracolair.games.applestoapples.GameManager;
import net.dracolair.games.applestoapples.Message;
import net.dracolair.games.applestoapples.State;
import net.dracolair.games.applestoapples.MessageInfo;

import static net.dracolair.games.applestoapples.Factories.*;

public class CmdStart extends Command {

	@Override
	public void run(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Message> responses) {
		if(ata.m_isRandom) {
			Collections.shuffle(ata.m_activePlayers);
		}
		responses.add(MSG(msgInfo.ROOM, "We have >=3 players, the game will begin!"));
		responses.add(MSG(msgInfo.ROOM, "Dealing out cards..."));
		
		responses.add(MSG(gameManager.getName(), "!botplay " + msgInfo.ROOM));
	}
	
	@Override
	public void getRequirements(GameManager gameManager, Game ata, MessageInfo msgInfo, List<Requirement> requirements) {
		boolean cond1 = ata.m_state == State.BEGIN;
		Message msg1 = MSG(msgInfo.ROOM, "A game has already begun!");
		
		boolean cond2 = ata.m_players.size() >= 3;
		Message msg2 = MSG(msgInfo.ROOM, msgInfo.NICK + 
					                    " is fail, needs " + 
					                    (3-ata.m_players.size()) + 
					                    " to play.");
		
		requirements.add(REQ(cond1, msg1));
		requirements.add(REQ(cond2, msg2));
	}

}
