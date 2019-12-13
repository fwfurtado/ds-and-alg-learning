package me.fwfurtado.hash;

public interface HashAlgorithm<T> {

    int hashing(T input, int tableSize);

    static <T> HashAlgorithm<T> byDivision() {
        return new DivisionHashAlgorithm<>();
    }

    static <T> HashAlgorithm<T> byLinearCongruential() {
        return new LinearCongruentialHashAlgorithm<>();
    }

    static <T> HashAlgorithm<T> byFolding() {
        return new FoldHashAlgorithm<>();
    }

    static <T> HashAlgorithm<T> byDigitsMultiplication() {
        return new DigitsHashAlgorithm<>();
    }

    class DigitsHashAlgorithm<E> implements HashAlgorithm<E> {

        @Override
        public int hashing(E input, int tableSize) {
            var hashCode = input.hashCode();
            var absoluteHashCode = hashCode & 0x7FFFFFFF;

            var result = 7;

            do {
                var currentNumber = absoluteHashCode % 10;

                result = 31 * result + currentNumber;

                absoluteHashCode /= 10;

            } while (absoluteHashCode > 0);

            return result;
        }
    }

    class FoldHashAlgorithm<E> implements HashAlgorithm<E> {

        private static final int NUMBER_TO_SHIFT = 10;

        @Override
        public int hashing(E input, int tableSize) {
            var hashCode = input.hashCode();
            var firstPart = hashCode >> NUMBER_TO_SHIFT;
            var secondPart = hashCode & (tableSize - 1);

            return firstPart ^ secondPart;
        }
    }

    class DivisionHashAlgorithm<E> implements HashAlgorithm<E> {

        @Override
        public int hashing(E input, int tableSize) {
            var hashCode = input.hashCode();
            var absoluteHasCode = hashCode & 0x7FFFFFFF;

            return absoluteHasCode % tableSize;
        }
    }

    class LinearCongruentialHashAlgorithm<E> implements HashAlgorithm<E> {

        private static final double FRACTIONAL_CONST = 0.89017273;

        @Override
        public int hashing(E input, int tableSize) {
            var hashCode = input.hashCode();
            var fullResult = hashCode * FRACTIONAL_CONST;
            var integerResult = fullResult - (int) fullResult;

            return (int) (tableSize * integerResult);
        }
    }
}
