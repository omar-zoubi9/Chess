package moveBehavior.validationChain;

import inputManaging.Move.Move;

public interface IValidationChain {

    boolean isValid(Move move);

    public void setNextInChain(IValidationChain nextInChain);
}
