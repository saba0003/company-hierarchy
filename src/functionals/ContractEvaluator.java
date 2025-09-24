package functionals;

import contract.Contract;

@FunctionalInterface
public interface ContractEvaluator {
    boolean evaluate(Contract contract);
}