package ca.mcmaster.se2aa4.mazerunner;

public interface MovementCommand {
        boolean execute();
        void undo();
    
}
