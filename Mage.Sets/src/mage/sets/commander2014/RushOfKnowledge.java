/*
 *  Copyright 2010 BetaSteward_at_googlemail.com. All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without modification, are
 *  permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright notice, this list of
 *        conditions and the following disclaimer.
 *
 *     2. Redistributions in binary form must reproduce the above copyright notice, this list
 *        of conditions and the following disclaimer in the documentation and/or other materials
 *        provided with the distribution.
 *
 *  THIS SOFTWARE IS PROVIDED BY BetaSteward_at_googlemail.com ``AS IS'' AND ANY EXPRESS OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND
 *  FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL BetaSteward_at_googlemail.com OR
 *  CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 *  CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 *  SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 *  NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF
 *  ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 *  The views and conclusions contained in the software and documentation are those of the
 *  authors and should not be interpreted as representing official policies, either expressed
 *  or implied, of BetaSteward_at_googlemail.com.
 */
package mage.sets.commander2014;

import java.util.UUID;
import mage.abilities.Ability;
import mage.abilities.effects.OneShotEffect;
import mage.cards.CardImpl;
import mage.constants.CardType;
import mage.constants.Outcome;
import mage.constants.Rarity;
import mage.game.Game;
import mage.game.permanent.Permanent;
import mage.players.Player;

/**
 *
 * @author LevelX2
 */
public class RushOfKnowledge extends CardImpl {

    public RushOfKnowledge(UUID ownerId) {
        super(ownerId, 123, "Rush of Knowledge", Rarity.COMMON, new CardType[]{CardType.SORCERY}, "{4}{U}");
        this.expansionSetCode = "C14";


        // Draw cards equal to the highest converted mana cost among permanents you control.
        this.getSpellAbility().addEffect(new RushOfKnowledgeEffect());

    }

    public RushOfKnowledge(final RushOfKnowledge card) {
        super(card);
    }

    @Override
    public RushOfKnowledge copy() {
        return new RushOfKnowledge(this);
    }
}

class RushOfKnowledgeEffect extends OneShotEffect {

    public RushOfKnowledgeEffect() {
        super(Outcome.DrawCard);
        this.staticText = "Draw cards equal to the highest converted mana cost among permanents you control";
    }

    public RushOfKnowledgeEffect(final RushOfKnowledgeEffect effect) {
        super(effect);
    }

    @Override
    public RushOfKnowledgeEffect copy() {
        return new RushOfKnowledgeEffect(this);
    }

    @Override
    public boolean apply(Game game, Ability source) {
        Player controller = game.getPlayer(source.getControllerId());
        if (controller != null) {
            int highCMC = 0;
            for (Permanent permanent : game.getBattlefield().getAllActivePermanents(controller.getId())) {
                if (permanent.getSpellAbility() != null) {
                    int cmc = permanent.getSpellAbility().getManaCosts().convertedManaCost();
                    if (cmc > highCMC) {
                        highCMC = cmc;
                    }
                }
            }
            if (highCMC > 0) {
                controller.drawCards(highCMC, game);
            }
            return true;
        }
        return false;
    }
}
