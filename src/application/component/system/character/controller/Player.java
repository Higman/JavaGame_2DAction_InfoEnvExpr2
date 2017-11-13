package application.component.system.character.controller;

import application.component.objects.character.PlayableCharacter;
import application.component.objects.character.implement_character.TMPCharacter;
import application.component.system.GameManager;
import application.component.system.InputManager;

public class Player extends CharacterController {
    // TODO PlayableCharacterが実装、要修正
    // TMPCharacterをPlayableCharacterに

//    private PlayableCharacter character;
    private TMPCharacter character;
    public Player(PlayableCharacter character) {
//        this.character = character;

    }

    public Player(TMPCharacter character) {
        this.character = character;
    }

    @Override
    public void update() {
        InputManager im = GameManager.getInputManager();
        character.setSpeed(0, 0);
        if ( im.get(InputManager.KindOfPushedKey.UP_KEY) ) {
            character.setSpeed(0, -10);
        } else if ( im.get(InputManager.KindOfPushedKey.DOWN_KEY) ) {
            character.setSpeed(0, 10);

        }

        if ( im.get(InputManager.KindOfPushedKey.LEFT_KEY) ) {
            character.setSpeed(-10, 0);

        } else if ( im.get(InputManager.KindOfPushedKey.RIGHT_KEY) ) {
            character.setSpeed(10, 0);

        }
    }

    // TODO 要修正
//    public PlayableCharacter getCharacter() {
//        return character;
//    }


    public TMPCharacter getCharacter() {
        return character;
    }
}
